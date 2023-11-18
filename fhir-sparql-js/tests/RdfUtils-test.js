const File = require('fs');
const Path = require('path');
const Tests = __dirname;
const Resources = Path.join(__dirname, '../../fhir-sparql-common/src/test/resources/org/uu3/');

const {RdfUtils, Term, SparqlQuery} = require('../dist/RdfUtils');

const ShExParser = require("@shexjs/parser").construct();
const FhirShEx = ShExParser.parse(File.readFileSync(Path.join(Resources, 'ShEx-mini-terse.shex'), 'utf-8'));

describe('RdfUtils', () => {
  describe('parsers', () => {
    it('should parse SPARQL', () => {
      const iFileName = Path.join(Resources, 'obs-pat.srq');
      const iText = File.readFileSync(iFileName, 'utf-8');
      const iSparql = SparqlQuery.parse(iText);
      const iQuery = iSparql.getQuery();
      const refSparqlObj = JSON.parse(File.readFileSync(Path.join(Tests, 'obs-path-sparqljs.json')));
      expect(iQuery).toEqual(refSparqlObj);
      // iQuery expanded to first-level components:
      // expect(iQuery.queryType).toEqual(refSparqlObj.queryType);
      // expect(iQuery.prefixes).toEqual(refSparqlObj.prefixes);
      // expect(iQuery.variables).toEqual(refSparqlObj.variables);
      // expect(iQuery.where[0].type).toEqual(refSparqlObj.where[0].type);
      // expect(iQuery.where[0].triples.slice(5,6)).toEqual(refSparqlObj.where[0].triples.slice(5,6));

      expect(iSparql.getWhere()[0].toString('XX')).toEqual(`XX{
XX  ?obs <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://hl7.org/fhir/Observation> .
XX  ?obs <http://hl7.org/fhir/code> ?code .
XX  ?code <http://hl7.org/fhir/coding> ?codeList .
XX  ?codeList <http://www.w3.org/1999/02/22-rdf-syntax-ns#rest>*/<http://www.w3.org/1999/02/22-rdf-syntax-ns#first> ?codeElt .
XX  ?codeElt <http://hl7.org/fhir/code> ?codeCode .
XX  ?codeCode <http://hl7.org/fhir/v> "72166-2" .
XX  ?codeElt <http://hl7.org/fhir/system> ?codingSystem .
XX  ?codingSystem <http://hl7.org/fhir/v> "http://loinc.org"^^<http://www.w3.org/2001/XMLSchema#anyURI> .
XX  ?obs <http://hl7.org/fhir/subject> ?subjectRef .
XX  ?subjectRef <http://hl7.org/fhir/reference> ?subject .
XX  ?subject <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://hl7.org/fhir/Patient> .
XX  ?subject <http://hl7.org/fhir/id> ?patIdElt .
XX  ?patIdElt <http://hl7.org/fhir/v> ?patId .
}`);
    });

    it('should parse ShEx', () => {
      const refFhirShExObj = JSON.parse(File.readFileSync(Path.join(Resources, 'ShEx-mini-terse.json'), 'utf-8'));
      expect(FhirShEx).toEqual(refFhirShExObj);
    });
  });

  describe('Path', () => {
    it('should fail some equals', () => {
      const p0 = SparqlQuery.parse(`
PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
ASK WHERE {?codeList rdf:rest*/rdf:first ?codeElt}
`).getWhere()[0].triples[0];
      const p1 = SparqlQuery.parse(`
PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
ASK WHERE {?codeList (rdf:first/rdf:tres)*/rdf:first ?codeElt}
`).getWhere()[0].triples[0];
      const p2 = SparqlQuery.parse(`
PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
ASK WHERE {?codeList (rdf:first/rdf:rest)+/rdf:first ?codeElt}
`).getWhere()[0].triples[0];
      expect(p0.equals(p1)).toBe(false);
      expect(p0.equals(p2)).toBe(false);
    });
  });

  describe('blessers', () => {
    it('should whine on bad SparqlJs input', () => {
      expect(() => Term.blessSparqlJs({bad: 1})).toThrow('unknown SparqlJs term type in {"bad":1}');
    });
  });
});
