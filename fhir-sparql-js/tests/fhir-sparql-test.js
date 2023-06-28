const File = require('fs');
const Path = require('path');
const SparqlJs = require('sparqljs');
const SparqlParser = new SparqlJs.Parser();
const ShExParser = require("@shexjs/parser").construct();
const Tests = __dirname;
const Resources = Path.join(__dirname, '../../fhir-sparql-common/src/main/resources/');
const FhirShEx = ShExParser.parse(File.readFileSync(Path.join(Resources, 'org/uu3/ShEx-mini-terse.shex'), 'utf-8'));
const {FhirSparql, PredicateToShapeDecl} = require('../lib/fhir-sparql');

// Namepaced terms
const Xsd_ = 'http://www.w3.org/2001/XMLSchema#';
const Xsd = {
  integer: { termType: 'NamedNode', value: Xsd_ + 'integer' },
  string: { termType: 'NamedNode', value: Xsd_ + 'string' },
};
const Rdf_ = 'http://www.w3.org/1999/02/22-rdf-syntax-ns#';
const Rdf = {
  type: { termType: 'NamedNode', value: Rdf_ + 'type' },
};
const Fhir_ = 'http://hl7.org/fhir/';
const Fhir = {
  v: { termType: 'NamedNode', value: Fhir_ + 'v' },
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
    const iQuery = SparqlParser.parse(File.readFileSync(Path.join(Tests, '../../notes/obs-pat-mid.srq'), 'utf-8'));debugger
    const queryPlan = rewriter.opBgpToFhirPathExecutions(iQuery);
    expect(queryPlan.arcTrees).toEqual([ArcTree_obs, ArcTree_subject]);
    expect(queryPlan.connectingVariables).toEqual(SubjectConnectingVariable);
    expect(queryPlan.arcTrees[0].getBgp()).toEqual(BGP_obs);
    expect(queryPlan.arcTrees[1].getBgp()).toEqual(BGP_subject);
  });
});

/*
  Reference values for tests above.
 */

// Triples
const T_obs_A_Observation = {
  subject: { termType: 'Variable', value: 'obs' },
  predicate: Rdf.type,
  object: { termType: 'NamedNode', value: 'http://hl7.org/fhir/Observation' }
};
const T_obs_code_codeList = {
  subject: { termType: 'Variable', value: 'obs' },
  predicate: { termType: 'NamedNode', value: 'http://hl7.org/fhir/code' },
  object: { termType: 'Variable', value: 'codeList' }
};
const T_codeList_FirstRest_coding = {
  subject: { termType: 'Variable', value: 'codeList' },
  predicate: FirstRest,
  object: { termType: 'Variable', value: 'coding' }
};
const T_coding_code_codeCode = {
  subject: { termType: 'Variable', value: 'coding' },
  predicate: { termType: 'NamedNode', value: 'http://hl7.org/fhir/code' },
  object: { termType: 'Variable', value: 'codeCode' }
};
const T_codeCode_v_1234567 = {
  subject: { termType: 'Variable', value: 'codeCode' },
  predicate: Fhir.v,
  object: { termType: 'Literal', value: '1234567', language: '', datatype: Xsd.integer }
};
const T_coding_sytem_codingSystem = {
  subject: { termType: 'Variable', value: 'coding' },
  predicate: { termType: 'NamedNode', value: 'http://hl7.org/fhir/system' },
  object: { termType: 'Variable', value: 'codingSystem' }
};
const T_codingSystem_v_snomed = {
  subject: { termType: 'Variable', value: 'codingSystem' },
  predicate: Fhir.v,
  object: { termType: 'Literal', value: 'http://snomed.info/id', language: '', datatype: Xsd.string }
};
const T_obs_subject_subjectRef = {
  subject: { termType: 'Variable', value: 'obs' },
  predicate: { termType: 'NamedNode', value: 'http://hl7.org/fhir/subject' },
  object: { termType: 'Variable', value: 'subjectRef' }
};
const T_subjectRef_reference_subject = {
  subject: { termType: 'Variable', value: 'subjectRef' },
  predicate: { termType: 'NamedNode', value: 'http://hl7.org/fhir/reference' },
  object: { termType: 'Variable', value: 'subject' }
};
const T_subject_a_Patient = {
  subject: { termType: 'Variable', value: 'subject' },
  predicate: Rdf.type,
  object: { termType: 'NamedNode', value: 'http://hl7.org/fhir/Patient' }
};
const T_subject_id_patIdElt = {
  subject: { termType: 'Variable', value: 'subject' },
  predicate: { termType: 'NamedNode', value: 'http://hl7.org/fhir/id' },
  object: { termType: 'Variable', value: 'patIdElt' }
};
const T_patIdElt_v_patId = {
  subject: { termType: 'Variable', value: 'patIdElt' },
  predicate: Fhir.v,
  object: { termType: 'Variable', value: 'patId' }
};

// ArcTrees
const ArcTree_obs = {tp: null, out: [
  {tp: T_obs_A_Observation, out: []},
  {tp: T_obs_code_codeList, out: [
    {tp: T_codeList_FirstRest_coding, out: [
      {tp: T_coding_code_codeCode, out: [
        {tp: T_codeCode_v_1234567, out: []}
      ]},
      {tp: T_coding_sytem_codingSystem, out: [
        {tp: T_codingSystem_v_snomed, out: []}
      ]}
    ]}
  ]},
  {tp: T_obs_subject_subjectRef, out: [
    {tp: T_subjectRef_reference_subject, out: []}
  ]}
]};
const ArcTree_subject = {tp: null, out: [
  {tp: T_subject_a_Patient, out: []},
  {tp: T_subject_id_patIdElt, out: [
    {tp: T_patIdElt_v_patId, out: []}
  ]}
]};

// ConnectingVariables
const SubjectConnectingVariable = {
  subject: [ArcTree_obs.out[2], ArcTree_subject, ArcTree_subject]
};

// BGPs
const BGP_obs = [
  T_obs_A_Observation,
  T_obs_code_codeList,
  T_codeList_FirstRest_coding,
  T_coding_code_codeCode,
  T_codeCode_v_1234567,
  T_coding_sytem_codingSystem,
  T_codingSystem_v_snomed,
  T_obs_subject_subjectRef,
  T_subjectRef_reference_subject,
];
const BGP_subject = [
  T_subject_a_Patient,
  T_subject_id_patIdElt,
  T_patIdElt_v_patId,
];
