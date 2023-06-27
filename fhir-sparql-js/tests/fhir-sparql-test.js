const File = require('fs');
const Path = require('path');
const SparqlJs = require('sparqljs');
const SparqlParser = new SparqlJs.Parser();
const ShExParser = require("@shexjs/parser").construct();
const Tests = __dirname;
const Resources = Path.join(__dirname, '../../fhir-sparql-common/src/main/resources/');
const FhirShEx = ShExParser.parse(File.readFileSync(Path.join(Resources, 'org/uu3/ShEx-mini-terse.shex'), 'utf-8'));
const {FhirSparql, PredicateToShapeDecl} = require('../lib/fhir-sparql');

const Xsd = {
  integer: { termType: 'NamedNode', value: 'http://www.w3.org/2001/XMLSchema#integer' },
  string: { termType: 'NamedNode', value: 'http://www.w3.org/2001/XMLSchema#string' },
};
const Rdf = {
  type: { termType: 'NamedNode', value: 'http://www.w3.org/1999/02/22-rdf-syntax-ns#type' },
};
const Fhir = {
  v: { termType: 'NamedNode', value: 'http://hl7.org/fhir/v' },
}

const FirstRest = { type: 'path', pathType: '/', items: [
  {
    "type": "path",
    "items": [
      {
        "termType": "NamedNode",
        "value": "http://www.w3.org/1999/02/22-rdf-syntax-ns#first",
      }
    ],
    "pathType": "*"
  },
  {
    "termType": "NamedNode",
    "value": "http://www.w3.org/1999/02/22-rdf-syntax-ns#rest"
  },
] };

describe('PredicateToShapeDecl', () => {
  it('should work from ShapeDecl', () => {
    const visitor = new PredicateToShapeDecl();
    expect(visitor.visitShapeDecl(FhirShEx.shapes[0]).type).toEqual("ShapeDecl");
  });

  it('should throw with bad arguments', () => {
    const visitor = new PredicateToShapeDecl();
    expect(() => visitor.visitSchema(null)).toThrow(/got null/);
    expect(() => visitor.visitSchema("schema")).toThrow(/got "schema"/);
    expect(() => visitor.visitSchema({type: "schema999"})).toThrow(/got {"type":"schema999"}/);
    expect(() => visitor.visitShapeExpr(FhirShEx.shapes[0].shapeExpr)).toThrow(/while not in a ShapeDecl/);
  });
});

describe('parsers', () => {
  it('should parse SPARQL', () => {
    const iFileName = Path.join(Tests, '../../notes/obs-pat.srq');
    const iText = File.readFileSync(iFileName, 'utf-8');
    const iQuery = SparqlParser.parse(iText);
    const refSparqlObj = JSON.parse(File.readFileSync(Path.join(Tests, 'obs-path-sparqljs.json')));
    expect(iQuery).toEqual(refSparqlObj);
  });

  it('should parse ShEx', () => {
    const refFhirShExObj = JSON.parse(File.readFileSync(Path.join(Tests, 'ShEx-mini-terse.json'), 'utf-8'));
    expect(FhirShEx).toEqual(refFhirShExObj);
  });
});

describe('FhirSparql', () => {
  it('should index predicates', () => {
    const rewriter = new FhirSparql(FhirShEx);
    expect(rewriter.predicateToShapeDecl['http://hl7.org/fhir/item'].map(d => d.id)).toEqual([ 'Questionnaire', 'Questionnaire.item' ]);
  });

  it('should translate obs-path', () => {
    const rewriter = new FhirSparql(FhirShEx);
    const iQuery = SparqlParser.parse(File.readFileSync(Path.join(Tests, '../../notes/obs-pat.srq'), 'utf-8'));
    const queryPlan = rewriter.opBgpToFhirPathExecutions(iQuery);
    expect(JSON.parse(JSON.stringify(queryPlan))).toEqual([
      {
        outgoingArcTree: null,
        connectingVariables: [],
        triplePatterns: [
          {
            subject: { termType: 'Variable', value: 'obs' },
            predicate: Rdf.type,
            object: { termType: 'NamedNode', value: 'http://hl7.org/fhir/Observation' }
          },
          {
            subject: { termType: 'Variable', value: 'obs' },
            predicate: { termType: 'NamedNode', value: 'http://hl7.org/fhir/code' },
            object: { termType: 'Variable', value: 'codeList' }
          },
          {
            subject: { termType: 'Variable', value: 'codeList' },
            predicate: FirstRest,
            object: { termType: 'Variable', value: 'coding' }
          },
          {
            subject: { termType: 'Variable', value: 'coding' },
            predicate: { termType: 'NamedNode', value: 'http://hl7.org/fhir/code' },
            object: { termType: 'Variable', value: 'codeCode' }
          },
          {
            subject: { termType: 'Variable', value: 'codeCode' },
            predicate: Fhir.v,
            object: { termType: 'Literal', value: '1234567', language: '', datatype: Xsd.integer }
          },
          {
            subject: { termType: 'Variable', value: 'coding' },
            predicate: { termType: 'NamedNode', value: 'http://hl7.org/fhir/system' },
            object: { termType: 'Variable', value: 'codingSystem' }
          },
          {
            subject: { termType: 'Variable', value: 'codingSystem' },
            predicate: Fhir.v,
            object: { termType: 'Literal', value: 'http://snomed.info/id', language: '', datatype: Xsd.string }
          },
          {
            subject: { termType: 'Variable', value: 'obs' },
            predicate: { termType: 'NamedNode', value: 'http://hl7.org/fhir/subject' },
            object: { termType: 'Variable', value: 'subjectRef' }
          },
          {
            subject: { termType: 'Variable', value: 'subjectRef' },
            predicate: { termType: 'NamedNode', value: 'http://hl7.org/fhir/reference' },
            object: { termType: 'Variable', value: 'subject' }
          },
          {
            subject: { termType: 'Variable', value: 'subject' },
            predicate: Rdf.type,
            object: { termType: 'NamedNode', value: 'http://hl7.org/fhir/Patient' }
          },
          {
            subject: { termType: 'Variable', value: 'subject' },
            predicate: { termType: 'NamedNode', value: 'http://hl7.org/fhir/id' },
            object: { termType: 'Variable', value: 'patIdElt' }
          },
          {
            subject: { termType: 'Variable', value: 'patIdElt' },
            predicate: Fhir.v,
            object: { termType: 'Variable', value: 'patId' }
          }
        ]
      }
    ]);
  });
});
