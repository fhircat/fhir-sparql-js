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
  describe('', () => {
    it('should throw with bad arguments', () => {
      expect(() => new FhirJsonToTurtle().prettyPrint(null)).toThrow(/got null/);

      expect(() => new FhirJsonToTurtle().prettyPrint(undefined)).toThrow(/got undefined/);

      expect(() => new FhirJsonToTurtle().prettyPrint({
        resourceType: 'Observation',
        status: null,
      })).toThrow(/got null/);

      expect(() => new FhirJsonToTurtle().prettyPrint({
        resourceType: 'Observation',
        status: undefined,
      })).toThrow(/not expecting undefined/);

      const badDateTimes = [
        ' 2018', '2018 ', '2018-05:0', '2018+05:00.', '2018X',
      ];
      badDateTimes.forEach(lexicalForm => {
        expect(() => new FhirJsonToTurtle().prettyPrint({
          resourceType: 'Observation',
          effectiveDateTime: lexicalForm,
        })).toThrow(`Couldn't parse FHIR dateTime from "${lexicalForm}"`);
      });

      const badDecimals = [
        ' 2018', '2018 ', '+2018', '2018+05.', '2018X',
      ];
      badDecimals.forEach(lexicalForm => {
        expect(() => new FhirJsonToTurtle().prettyPrint({
          resourceType: 'Observation',
          valueQuantity: {
            value: lexicalForm,
          }
        })).toThrow(`Couldn't parse FHIR decimal from "${lexicalForm}"`);
      });

      /* TODO: validate while Turtle-izing
      const schema = ...
      expect(() => new FhirJsonToTurtle(schema).prettyPrint({
        resourceType: 'Observation',
        component: { notRecognized: {bar: "foo"} },
      })).toThrow(/not expecting foo/);
      */
    });

    it('should parse FHIR dateTime formats', () => {
      const fhirDateTimes = {
        gYear: ['2018', '2018-05:00', '2018+05:00', '2018Z'],
        gYearMonth: ['1973-06', '1973-06-05:00', '1973-06+05:00', '1973-06Z'],
        date: ['1905-08-23', '1905-08-23-05:00', '1905-08-23+05:00', '1905-08-23Z'],
        dateTime: ['2015-02-07T13:28:17-05:00', '2017-01-01T00:00:00.000Z'],
      };
      Object.entries(fhirDateTimes).forEach(([xsdType, lexicalForms]) => {
        lexicalForms.forEach(lexicalForm => {
          expect(new FhirJsonToTurtle().prettyPrint({
            resourceType: 'Observation',
            effectiveDateTime: lexicalForm,
          })).toMatch(`"${lexicalForm}"^^xsd:${xsdType}\n`);
        });
      });
    });

    it('should parse FHIR decimal formats', () => {
      const fhirDecimals = {
        double: ['2018E0', '2018E-2'],
        decimal: ['1973', '1973.06', '1973.060'],
      };
      Object.entries(fhirDecimals).forEach(([xsdType, lexicalForms]) => {
        lexicalForms.forEach(lexicalForm => {
          expect(new FhirJsonToTurtle().prettyPrint({
            resourceType: 'Observation',
            valueQuantity: {
              value: lexicalForm
            },
          })).toMatch(`"${lexicalForm}"^^xsd:${xsdType} `);
        });
      });
    });
  });

  describe('string tests', () => {
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
