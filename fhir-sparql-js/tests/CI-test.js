/**

   logging:
     fhir-sparql-js uses bunyan for logging so you can pipe stdout through the bunyan CLI:
       LOGLEVEL=trace ./node_modules/.bin/jest tests/CI-test.js | ./node_modules/.bin/bunyan

   environment variables:
     LOGLEVEL: (trace|debug|info|warn|error|fatal) [info]
     FHIR_SERVER_ADDR: address of already-running FHIR server, e.g. http://localhost:8080/hapi/fhir/
 */
const Fs = require('fs');
const Path = require('path');
const JsYaml = require('js-yaml');
const { SparqlJsonParser } = require('sparqljson-parse');
const Tests = __dirname;
const Resources = Path.join(__dirname, '../../fhir-sparql-common/src/test/resources/org/uu3/');

const {FhirSparql, Rule_CodeWithSystem} = require('../dist/FhirSparql');
const {renderResultSet} = require('../dist/RdfUtils');

const ShExParser = require("@shexjs/parser").construct();
const FhirShEx = ShExParser.parse(Fs.readFileSync(Path.join(Resources, 'ShEx-mini-terse.shex'), 'utf-8'));

const Bunyan = require('bunyan');
const log = Bunyan.createLogger({name: 'CD-test', level: process.env.LOGLEVEL || 'info'});

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
    log.debug(`fetch is hard-wired to handle calls to http://${host}:${port}`);
  } else {
    // Or use the server in the regular way.
    beforeAll(async () => {
      await fhirServer.start();
      log.debug(`Server is running on http://${host}:${port}`);
    });

    afterAll(() => {
      fhirServer.stop();
    });
  }
}

const TESTS = [
  { filename: 'obs-pat', description: "Obs-Patient ref" },
  { filename: 'trimmed-use-case-query', description: "trimed smoker use case" },
]

describe('CI', () => {
  describe('Rule', () => {
    it('should serialize Rule_CodeWithSystem', () => {
      expect(Rule_CodeWithSystem.toString()).toEqual("TODO");
    });
  });

  describe('FhirSparql/', () => {
    TESTS.forEach(setupTest);
  });
});

function setupTest (test) {
  const {filename, description} = test;
  it(`should execute (${filename}) ${description} `, async () => {
    await loadAndExecuteQuery(filename);
  });
}

async function loadAndExecuteQuery (queryFileName) {
  const queryFilePath = Path.join(Resources, queryFileName + ".srq");
  log.trace('queryFilePath:', queryFilePath);
  const sparqlQuery = Fs.readFileSync(queryFilePath, 'utf-8');
  const rewriter = new FhirSparql(FhirShEx);
  const results = canonicalizeResultSet(await rewriter.executeFhirQuery(FhirServerAddr, sparqlQuery, log));
  log.debug("query results:", renderResultSet(results).join("\n"));

  const resultsFilePath = Path.join(Resources, queryFileName + "-results.json");
  log.trace('resultsFilePath:', resultsFilePath);
  const expectedResultsJson = Fs.readFileSync(resultsFilePath, 'utf-8');
  const sparqlJsonParser = new SparqlJsonParser({});
  const expectedResults = sparqlJsonParser.parseJsonResults(JSON.parse(expectedResultsJson));
  expectedResults.forEach(
    row => Object.entries(row).forEach(
      ([variable, binding]) => {
        if (binding.termType === 'NamedNode')
          binding.value = new URL(binding.value, FhirServerAddr).href;
        return [variable, binding]
      }
    )
  );
  // log.debug("expected results:", renderResultSet(expectedResults).join("\n"));
  expect(renderResultSet(results)).toEqual(renderResultSet(expectedResults));
  return results;
}

function canonicalizeResultSet (results) {
  const bnodeMap = new Map();
  return results.map(row =>
    Object.fromEntries(Object.keys(row).map(key => {
      const val = row[key];
      if (val.termType === 'BlankNode') {
        if (!bnodeMap.has(val.value))
          bnodeMap.set(val.value, {termType: 'BlankNode', value: 'b' + bnodeMap.size});
        return [key, bnodeMap.get(val.value)];
      } else {
        return [key, val];
      }
    }))
  );
}
