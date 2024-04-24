/**
   environment variables:
     FHIR_SERVER_ADDR: address of already-running FHIR server, e.g. http://localhost:8080/hapi/fhir/

   logging:
     fhir-sparql-js uses bunyan for logging so you can pipe stdout through the bunyan CLI:
       ./node_modules/.bin/jest tests/CI-test.js | ./node_modules/.bin/bunyan
 */
const Fs = require('fs');
const Path = require('path');
const JsYaml = require('js-yaml');
const Tests = __dirname;
const Resources = Path.join(__dirname, '../../fhir-sparql-common/src/test/resources/org/uu3/');

const {RdfUtils, Bgp, SparqlQuery, renderResultSet} = require('../dist/RdfUtils');
const {ArcTree} = require('../dist/ArcTree.js');
const {FhirSparql, ConnectingVariables, FhirPathExecution, Rule_CodeWithSystem} = require('../dist/FhirSparql');
const {QueryAnalyzer, PredicateToShapeDecl} = require('../dist/QueryAnalyzer');
const {Ns, Rdf, Xsd, Fhir, FirstRest} = require('../dist/Namespaces');
const N3 = require('n3');
const {QueryEngine} = require('@comunica/query-sparql-rdfjs');

const {FhirJsonToTurtle} = require('../FhirJsonToTurtle');

const ShExParser = require("@shexjs/parser").construct();
const FhirShEx = ShExParser.parse(Fs.readFileSync(Path.join(Resources, 'ShEx-mini-terse.shex'), 'utf-8'));

const Bunyan = require('bunyan');
const log = Bunyan.createLogger({name: 'CD-test', level: 'trace'});

let FhirServerAddr = process.env.FHIR_SERVER_ADDR;
if (!FhirServerAddr) {
  // use canned FHIR server
  const host = 'localhost';
  const port = 8080;
  const serverPath = `/hapi/fhir/`; // conveient for HAPI default setup
  FhirServerAddr = `http://${host}:${port}${serverPath}`;

  const cannedRespDir = Path.join(Resources, 'fhirServerResources');
  const resourceIndex = JsYaml.load(Fs.readFileSync(Path.join(cannedRespDir, 'index.yaml'), 'utf8'));

  // Create a server.
  const {MinimalFhirServer} = require('../util/MinimalFhirServer.js');
  const fhirServer = new MinimalFhirServer(host, port, serverPath, cannedRespDir, resourceIndex, log);

  if (true) {
    // Either make `fetch` call the server's handler directly
    // (makes debugging a lot easier)
    fetch = (url, parms) => {
      const body = fhirServer.handleFhirApiReq(url);
      return {ok: true, text: () => Promise.resolve(body) };
    }
    log.info(`fetch is hard-wired to handle calls to http://${host}:${port}`);
  } else {
    // Or use the server in the regular way.
    beforeAll(async () => {
      await fhirServer.start();
      log.info(`Server is running on http://${host}:${port}`);
    });

    afterAll(() => {
      fhirServer.stop();
    });
  }
}

describe('CI', () => {
  describe('Rule', () => {
    it('should serialize Rule_CodeWithSystem', () => {
      expect(Rule_CodeWithSystem.toString()).toEqual("TODO");
    });
  });

  describe('FhirSparql/', () => {
    it('should handle Obs-Patient ref', async () => {
      const parserOpts = {
        prefixes: undefined,
        baseIRI: 'http://localhost/some/path/and/file.txt',
        factory: N3.DataFactory,
        skipValidation: false,
        skipUngroupedVariableCheck: false,
        pathOnly: false,
      }

      const rewriter = new FhirSparql(FhirShEx);
      // const sparqlQuery = Fs.readFileSync(Path.join(Resources, 'trimmed-use-case-query.srq'), 'utf-8');
      const sparqlQuery = Fs.readFileSync(Path.join(Resources, 'obs-pat.srq'), 'utf-8');
      const iQuery = SparqlQuery.parse(sparqlQuery, parserOpts);
      const {arcTrees, connectingVariables, referents} = rewriter.getArcTrees(iQuery);
      log.trace({arcTrees: arcTrees.map((t, i) => `\n[${i}]: ` + t).join("\n--"), connectingVariables, referents});

      const sources = [];
      let results = [{}];
      for (const arcTree of arcTrees) {
        log.trace('procesing arcTrees[' + arcTrees.indexOf(arcTree) + ']');
        const newResults = [];
        for (const result of results) {
          // opBgpToFhirPathExecutions returns disjuncts
          const fhirPathExecutions = rewriter.opBgpToFhirPathExecutions(arcTree, referents, result);
          for (const fhirPathExecution of fhirPathExecutions) {
            // {name: 'code', value: 'http://loinc.org|72166-2'} -> code=http%3A%2F%2Floinc.org%7C72166-2
            // const paths = fhirPathExecution.paths.map(qp => encodeURIComponent(qp.name) + '=' + encodeURIComponent(qp.value)).join('&') || '';
            const searchUrl = new URL(fhirPathExecution.type, FhirServerAddr);
            for (const {name, value} of fhirPathExecution.paths)
              searchUrl.searchParams.set(name, value);

            // const urlStr = FhirServerAddr + fhirPathExecution.type + paths;

            const resp = await fetch(searchUrl, { headers: { Accept: 'application/json+fhir' } });
            const body = await resp.text();
            if (!resp.ok)
              throw Error(`Got ${resp.status} response to query for a ${fhirPathExecution.type} with [${fhirPathExecution.paths.map(p => p.name + ':' + p.value).join(', ')}] at FHIR endpoint <${FhirServerAddr}>:\n${body}`);
            const bundle = JSON.parse(body);
            log.trace(`<${decodeURIComponent(searchUrl.href)}> => ${bundle.entry.map((e, i) => `\n  ${i}: <${e.fullUrl}>`).join('')}`);

            for (const {fullUrl, resource} of bundle.entry) {
              // const xlator = new FhirJsonToTurtle();
              // const ttl = xlator.prettyPrint(resource);
              const url = new URL(fullUrl);
              const ttl = new FhirJsonToTurtle().prettyPrint(resource);// console.log(ttl);
              const db = parseTurtle(fullUrl, ttl, 'Turtle');
              const src = { url, body: ttl, db };
              sources.push(src);
              const queryStr = SparqlQuery.selectStar(arcTree.getBgp()); log.trace('queryStr:', queryStr);
              const bindings = await executeQuery([db], queryStr);log.trace('bindings:', renderResultSet(bindings).join(''))
              const newResult = bindings.map(r => Object.assign(r, result));
              Array.prototype.push.apply(newResults, newResult);
            }
          }
        }
        results = newResults;
      }
      log.info("query results:", renderResultSet(results).join("\n"));
    });
  });
});

function parseTurtle (baseIRI, text, dataFormat = 'Turtle') {
  if (dataFormat === 'JSON')
    text = new FhirJsonToTurtle().prettyPrint(JSON.parse(text));

  const db = new N3.Store();
  const parser = new N3.Parser({baseIRI})
  db.addQuads(parser.parse(text));
  return db;
}

async function executeQuery (sources, query) {
  const myEngine = new QueryEngine();
  const typedStream = await myEngine.queryBindings(query, {sources});
  const asArray = await typedStream.toArray();
  const rows = asArray.map(
    b => Object.fromEntries(b.entries)
  );
  // console.log('Query result rows:', rows);
  return rows;
}
