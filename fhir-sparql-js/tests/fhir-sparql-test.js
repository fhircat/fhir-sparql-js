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

const ObsAObservation = {
  subject: { termType: 'Variable', value: 'obs' },
  predicate: Rdf.type,
  object: { termType: 'NamedNode', value: 'http://hl7.org/fhir/Observation' }
};
const ObsCodeCodeList = {
  subject: { termType: 'Variable', value: 'obs' },
  predicate: { termType: 'NamedNode', value: 'http://hl7.org/fhir/code' },
  object: { termType: 'Variable', value: 'codeList' }
};

describe('FhirSparql', () => {
  it('should index predicates', () => {
    const rewriter = new FhirSparql(FhirShEx);
    expect(rewriter.predicateToShapeDecl['http://hl7.org/fhir/item'].map(d => d.id)).toEqual([ 'Questionnaire', 'Questionnaire.item' ]);
  });
  const CodeListFirstRestCoding = {
    subject: { termType: 'Variable', value: 'codeList' },
    predicate: FirstRest,
    object: { termType: 'Variable', value: 'coding' }
  };
  const CodingCodeCodeCode = {
    subject: { termType: 'Variable', value: 'coding' },
    predicate: { termType: 'NamedNode', value: 'http://hl7.org/fhir/code' },
    object: { termType: 'Variable', value: 'codeCode' }
  };
  const CodeCodeV1234567 = {
    subject: { termType: 'Variable', value: 'codeCode' },
    predicate: Fhir.v,
    object: { termType: 'Literal', value: '1234567', language: '', datatype: Xsd.integer }
  };
  const CodingSytemCodingSystem = {
    subject: { termType: 'Variable', value: 'coding' },
    predicate: { termType: 'NamedNode', value: 'http://hl7.org/fhir/system' },
    object: { termType: 'Variable', value: 'codingSystem' }
  };
  const CodingSystemVSnomed = {
    subject: { termType: 'Variable', value: 'codingSystem' },
    predicate: Fhir.v,
    object: { termType: 'Literal', value: 'http://snomed.info/id', language: '', datatype: Xsd.string }
  };
  const ObsSubjectSubjectRef = {
    subject: { termType: 'Variable', value: 'obs' },
    predicate: { termType: 'NamedNode', value: 'http://hl7.org/fhir/subject' },
    object: { termType: 'Variable', value: 'subjectRef' }
  };
  const SubjectRefReferenceSubject = {
    subject: { termType: 'Variable', value: 'subjectRef' },
    predicate: { termType: 'NamedNode', value: 'http://hl7.org/fhir/reference' },
    object: { termType: 'Variable', value: 'subject' }
  };
  const SubjectAPatient = {
    subject: { termType: 'Variable', value: 'subject' },
    predicate: Rdf.type,
    object: { termType: 'NamedNode', value: 'http://hl7.org/fhir/Patient' }
  };
  const SubjectIdPatIdElt = {
    subject: { termType: 'Variable', value: 'subject' },
    predicate: { termType: 'NamedNode', value: 'http://hl7.org/fhir/id' },
    object: { termType: 'Variable', value: 'patIdElt' }
  };
  const PatIdEltVPatId = {
    subject: { termType: 'Variable', value: 'patIdElt' },
    predicate: Fhir.v,
    object: { termType: 'Variable', value: 'patId' }
  };
  const ObsTreeBgp = [
    ObsAObservation,
    ObsCodeCodeList,
    CodeListFirstRestCoding,
    CodingCodeCodeCode,
    CodeCodeV1234567,
    CodingSytemCodingSystem,
    CodingSystemVSnomed,
    ObsSubjectSubjectRef,
    SubjectRefReferenceSubject,
  ];
  const SubjectTreeBgp = [
    SubjectAPatient,
    SubjectIdPatIdElt,
    PatIdEltVPatId,
  ];
  const ObsArcTree = {tp: null, out: [
    {tp: ObsAObservation, out: []},
    {tp: ObsCodeCodeList, out: [
      {tp: CodeListFirstRestCoding, out: [
        {tp: CodingCodeCodeCode, out: [
          {tp: CodeCodeV1234567, out: []}
        ]},
        {tp: CodingSytemCodingSystem, out: [
          {tp: CodingSystemVSnomed, out: []}
        ]}
      ]}
    ]},
    {tp: ObsSubjectSubjectRef, out: [
      {tp: SubjectRefReferenceSubject, out: []}
    ]}          
  ]};
  const SubjectArcTree = {tp: null, out: [
    {tp: SubjectAPatient, out: []},
    {tp: SubjectIdPatIdElt, out: [
      {tp: PatIdEltVPatId, out: []}
    ]}
  ]};
  const SubjectConnectingVariable = {
    variable: SubjectRefReferenceSubject.object.value, // or just the object?
    arcTrees: [ObsArcTree.out[2].out[0], SubjectArcTree]
  };

  it('should translate obs-path', () => {
    const rewriter = new FhirSparql(FhirShEx);
    const iQuery = SparqlParser.parse(File.readFileSync(Path.join(Tests, '../../notes/obs-pat-mid.srq'), 'utf-8'));debugger
    const queryPlan = rewriter.opBgpToFhirPathExecutions(iQuery);
    expect(queryPlan.arcTrees).toEqual([ObsArcTree, SubjectArcTree]);
    // expect(queryPlan.connectingVariables).toEqual([SubjectConnectingVariable]);
    expect(queryPlan.arcTrees[0].getBgp()).toEqual(ObsTreeBgp);
    expect(queryPlan.arcTrees[1].getBgp()).toEqual(SubjectTreeBgp);
  });
});
