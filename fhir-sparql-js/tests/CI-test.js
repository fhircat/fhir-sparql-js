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

const Host = 'localhost';
const Port = 8080;
const HapiServerPath = `/hapi/fhir/`;
const HapiServerAddr = `http://${Host}:${Port}${HapiServerPath}`;
const CannedRespDir = Path.join(Resources, 'fhirServerResources');
const ResourceIndex = JsYaml.load(Fs.readFileSync(Path.join(CannedRespDir, 'index.yaml'), 'utf8'));

const ShExParser = require("@shexjs/parser").construct();
const FhirShEx = ShExParser.parse(Fs.readFileSync(Path.join(Resources, 'ShEx-mini-terse.shex'), 'utf-8'));

/*
const winston = require('winston');

const logFormat = winston.format.printf(function(info) {
  return `${info.timestamp}-${info.level}: ${JSON.stringify(info.message, null, 4)}`;
});

const logger = winston.createLogger({
  transports: [
    new winston.transports.Console({
      format: winston.format.combine(winston.format.timestamp(),
                                     winston.format.colorize(),
                                     logFormat)
    })
  ]
});
*/

function handleFhirApiReq (url) {
  if (!url.pathname.startsWith(HapiServerPath))
    throw Error(`only hanndling FHIR queries on ${HapiServerPath}`);
  const resourcePath = url.pathname.substring(HapiServerPath.length);
  const [resourceType, resourceName] = resourcePath.split('/');

  if (resourceName) {

    // HapiServerPath '/Observation/123'
    const filename = Path.join(CannedRespDir, resourceType);
    try {
      console.log(`GET <${url.href}>`);
      const body = Fs.readFileSync(filename, 'utf-8');
      return body;
    } catch (e) {
      e.message += ' on ' + filename;
      throw e;
    }
  } else {

    // HapiServerPath '/Observation' ?search...
    const resourceBase = new URL(resourceType + '/', url);
    const resourceDir = Path.join(CannedRespDir, resourceType);
    let candidates = Fs.readdirSync(resourceDir).map(fn => fn.substring(0, fn.lastIndexOf('.')) || fn);
    const index = ResourceIndex[resourceType];
    for (const [attr, value] of Array.from(url.searchParams.entries())) {
      const hits = ((ResourceIndex[resourceType] || [])[attr] || [])[value];
      if (hits !== undefined) {
        for (let i = 0; i < candidates.length; ++i) {
          if (hits.indexOf(candidates[i]) === -1) {
            candidates.splice(i--, 1);
          }
        }
      }
    }

    // write bundle
    const resp = {
      type: 'Bundle',
      entry: candidates.map(filename => createEntry(resourceBase, resourceDir, filename))
    };
    return JSON.stringify(resp, null, 2);
  }
};

function createEntry (resourceBase, resourceDir, filename) {
  const fullUrl = new URL(filename, resourceBase).href;
  const resource = JSON.parse(Fs.readFileSync(Path.join(resourceDir, filename + '.json'), 'utf-8'));
  return { fullUrl, resource }
}


if (true) {
  fetch = (url, parms) => {
    const body = handleFhirApiReq(url);
    return {ok: true, text: () => Promise.resolve(body) };
  }
} else {
  const requestListener = function (req, res) {
    const url = new URL(`http://${Host}:${Port}${req.url}`);
    const body = handleFhirApiReq(url);
    res.writeHead(200);
    res.end(body);
  };

  let FakeServer = null;
  beforeAll(() => {
    FakeServer = require('http').createServer(requestListener);
    FakeServer.listen(Port, Host, () => {
      console.log(`Server is running on http://${Host}:${Port}`);
    });
  });

  afterAll(() => {
    FakeServer.close();
  });
}

describe('CI', () => {
  describe('Rule', () => {
    it('should serialize Rule_CodeWithSystem', () => {
      expect(Rule_CodeWithSystem.toString()).toEqual("TODO");
    });
  });

  describe('FhirSparql', () => {
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
      // const queryStr = Fs.readFileSync(Path.join(Resources, 'trimmed-use-case-query.srq'), 'utf-8');
      const queryStr = Fs.readFileSync(Path.join(Resources, 'obs-pat.srq'), 'utf-8');
      const iQuery = SparqlQuery.parse(queryStr, parserOpts);
      const {arcTrees, connectingVariables, referents} = rewriter.getArcTrees(iQuery);
      console.log({arcTrees: arcTrees.map((t, i) => `[${i}]: ` + String(t)), connectingVariables, referents});

      const sources = [];
      let results = [{}];
      for (const arcTree of arcTrees) {
        console.log('arcTrees[' + arcTrees.indexOf(arcTree) + ']');
        const newResults = [];
        for (const result of results) {
          // opBgpToFhirPathExecutions returns disjuncts
          const fhirPathExecutions = rewriter.opBgpToFhirPathExecutions(arcTree, referents, result);
          for (const fhirPathExecution of fhirPathExecutions) {
            // {name: 'code', value: 'http://loinc.org|72166-2'} -> code=http%3A%2F%2Floinc.org%7C72166-2
            // const paths = fhirPathExecution.paths.map(qp => encodeURIComponent(qp.name) + '=' + encodeURIComponent(qp.value)).join('&') || '';
            const searchUrl = new URL(fhirPathExecution.type, HapiServerAddr);
            for (const {name, value} of fhirPathExecution.paths)
              searchUrl.searchParams.set(name, value);

            // const urlStr = HapiServerAddr + fhirPathExecution.type + paths;

            const resp = await fetch(searchUrl, { headers: { Accept: 'application/json+fhir' } });
            const body = await resp.text();
            if (!resp.ok)
              throw Error(`Got ${resp.status} response to query for a ${fhirPathExecution.type} with [${fhirPathExecution.paths.map(p => p.name + ':' + p.value).join(', ')}] at FHIR endpoint <${HapiServerAddr}>:\n${body}`);
            const bundle = JSON.parse(body);
            console.log(`<${decodeURIComponent(searchUrl.href)}> => ${bundle.entry.map((e, i) => `\n  ${i}: <${e.fullUrl}>`).join('')}`);

            for (const {fullUrl, resource} of bundle.entry) {
              // const xlator = new FhirJsonToTurtle();
              // const ttl = xlator.prettyPrint(resource);
              const url = new URL(fullUrl);
              const ttl = new FhirJsonToTurtle().prettyPrint(resource);// console.log(ttl);
              const db = parseTurtle(fullUrl, ttl, 'Turtle');
              const src = { url, body: ttl, db };
              sources.push(src);
              const queryStr = SparqlQuery.selectStar(arcTree.getBgp()); console.log('queryStr:', queryStr);
              const bindings = await executeQuery([db], queryStr);console.log('bindings:', renderResultSet(bindings).join(''))
              const newResult = bindings.map(r => Object.assign(r, result));
              Array.prototype.push.apply(newResults, newResult);
            }
          }
        }
        results = newResults;
      }
      console.log(renderResultSet(results).join("\n"));
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
