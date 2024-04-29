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
const { RelativeSparqlJsonParser } = require('../util/RelativeSparqlJsonParser');
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
const MySparqlJsonParser = new RelativeSparqlJsonParser({baseIRI: FhirServerAddr});

const TESTS = [
  { filename: 'obs-pat', description: "Obs-Patient ref" },
  { filename: 'trimmed-use-case-query', description: "trimed smoker use case" },
  { filename: 'obs-proc', description: "Obs-Procedure ref" },
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
    await executeTest(filename);
  });
}

async function executeTest (queryFileName) {
  const queryFilePath = Path.join(Resources, queryFileName + ".srq");
  log.trace('queryFilePath:', queryFilePath);
  const sparqlQuery = Fs.readFileSync(queryFilePath, 'utf-8');
  const rewriter = new FhirSparql(FhirShEx);
  const results = canonicalizeResultSet(await rewriter.executeFhirQuery(FhirServerAddr, sparqlQuery, log));
  log.debug("query results:", renderResultSet(results).join("\n"));

  const expectedResultsFilePath = Path.join(Resources, queryFileName + "-results.json");
  log.trace('expectedResultsFilePath:', expectedResultsFilePath);
  const expectedResultsJson = Fs.readFileSync(expectedResultsFilePath, 'utf-8');
  const expectedResults = MySparqlJsonParser.parseJsonResults(JSON.parse(expectedResultsJson));
  // log.debug("expected results:", renderResultSet(expectedResults).join("\n"));
  expect(renderResultSet(results)).toEqual(renderResultSet(expectedResults));
  return results;
}

/**
 * renumber bnodes to make it easy to compare two result sets.
 * This assumes that the keys in the hash tables are in the order of the selects,
 * which makes it easier for humans to read but risks being fragile WRT the JSON
 * parser behavior. If this becomes a problem, uncomment the sort() on the
 * Object.keys below and either canonicalize the expected results or tweak their
 * order in the tests.
 *
 * @param results ResultSet to be canonicalized
 * @returns ResultSet
 */
function canonicalizeResultSet (results) {
  const bnodeMap = new Map();
  return results.map(row =>
    Object.fromEntries(Object.keys(row)/*.sort()*/.map(key => {
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
