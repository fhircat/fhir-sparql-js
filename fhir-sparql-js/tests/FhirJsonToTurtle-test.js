/**
 *
 *
 * Todo:
 *   Move to separate npm module.
 */

const Fs = require('fs');
const Path = require('path');
const Resources = Path.join(__dirname, '../../fhir-sparql-common/src/test/resources/org/uu3/fhirServerResources');
const Bunyan = require('bunyan');
const log = Bunyan.createLogger({name: 'CD-test', level: process.env.LOGLEVEL || 'info'});

const {FhirJsonToTurtle} = require('../dist/FhirJsonToTurtle');

const TESTS = [
  { filename: 'Observation/smoker-1-smoking-2022-05-19', description: "Observation smoker-1-smoking-2022-05-19" },
  { filename: 'Patient/smoker-1', description: "Patient smoker-1" },
  { filename: 'Observation/smoker-1-smoking-2023-06-20', description: "Observation smoker-1-smoking-2023-06-20" },
  { filename: 'Procedure/smoker-1-lung-screening-2022-12-20', description: "Procedure smoker-1-lung-screening-2022-12-20" },
]

describe('FhirJsonToTurtle-test', () => {
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


async function executeTest (fhirResourceName) {
  const jsonFilePath = Path.join(Resources, fhirResourceName + ".json");
  log.trace('jsonFilePath:', jsonFilePath);
  const resource = JSON.parse(Fs.readFileSync(jsonFilePath, 'utf-8'));
  const ttl = new FhirJsonToTurtle().prettyPrint(resource);
  log.debug("as Turtle results:", ttl);

  const expectedResultsFilePath = Path.join(Resources, fhirResourceName + ".ttl");
  log.trace('expectedResultsFilePath:', expectedResultsFilePath);
  const expectedResults = Fs.readFileSync(expectedResultsFilePath, 'utf-8');
  // log.debug("expected results:", expectedResults);
  expect(ttl).toEqual(expectedResults);
}
