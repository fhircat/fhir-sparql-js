const File = require('fs');
const Path = require('path');
const Tests = __dirname;
const Resources = Path.join(__dirname, '../../fhir-sparql-common/src/test/resources/org/uu3/');

const {RdfUtils, Bgp, SparqlQuery} = require('../dist/RdfUtils');
const {ArcTree} = require('../dist/ArcTree.js');
const {FhirSparql, ConnectingVariables, FhirPathExecution, Rule_CodeWithSystem} = require('../dist/FhirSparql');
const {QueryAnalyzer, PredicateToShapeDecl} = require('../dist/QueryAnalyzer');
const {Ns, Rdf, Xsd, Fhir, FirstRest} = require('../dist/Namespaces');
const N3 = require('n3');
const {QueryEngine} = require('@comunica/query-sparql-rdfjs');

const {FhirJsonToTurtle} = require('../FhirJsonToTurtle');

const HapiServerAddr = 'http://localhost:8080/hapi/fhir/';

const ShExParser = require("@shexjs/parser").construct();
const FhirShEx = ShExParser.parse(File.readFileSync(Path.join(Resources, 'ShEx-mini-terse.shex'), 'utf-8'));

describe('FhirSparql', () => {
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
      // const queryStr = File.readFileSync(Path.join(Resources, 'trimmed-use-case-query.srq'), 'utf-8');
      const queryStr = File.readFileSync(Path.join(Resources, 'obs-pat.srq'), 'utf-8');
      const iQuery = SparqlQuery.parse(queryStr, parserOpts);
      const {arcTrees, connectingVariables, referents} = rewriter.getArcTrees(iQuery);
      console.log(new Date(), {arcTrees, connectingVariables, referents});

      const sources = [];
      let results = [{}];
      for (const arcTree of arcTrees) {
        console.log(arcTree.toString());
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
              throw Error(`Unable to fetch ${urlStr} at <${url}>:\n${body}`);
            const bundle = JSON.parse(body);
            console.log(`<${decodeURIComponent(searchUrl.href)}> => ${bundle.entry.map((e, i) => `\n  ${i}: <${e.fullUrl}>`).join('')}`);
            /*
              {
              "resourceType": "Bundle", "id": "5183b131-5b14-47e3-84c6-2e0d398e10d3",
              "meta": { "lastUpdated": "2023-11-07T16:38:22.656+00:00" }, "type": "searchset",
              "link": [
              { "relation": "self", "url": "./fhir/Observation?code=http%3A%2F%2Floinc.org%7C72166-2" },
              { "relation": "next", "url": "./fhir?_getpages=518...0d3&_getpagesoffset=20&_count=20&_pretty=true&_bundletype=searchset" }
              ],
              "entry": [ {
              "fullUrl": "./fhir/Observation/58157",
              "resource": {
              "resourceType": "Observation",
              "id": "58157",
              ... } } ] }
            */
            for (const {fullUrl, resource} of bundle.entry) {
              // const xlator = new FhirJsonToTurtle();
              // const ttl = xlator.prettyPrint(resource);
              const url = new URL(fullUrl);
              const ttl = new FhirJsonToTurtle().prettyPrint(resource);
              const db = parseTurtle(fullUrl, ttl, 'Turtle');
              const src = { url, body: ttl, db };
              sources.push(src);
              const queryStr = SparqlQuery.selectStar(arcTree.getBgp());
              const bindings = await executeQuery([db], queryStr);
              Array.prototype.push.apply(newResults, bindings);
            }
          }
        }
        results = newResults;
        console.log(results);
      }
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
