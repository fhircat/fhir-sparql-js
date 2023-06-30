const File = require('fs');
const Path = require('path');
const SparqlJs = require('sparqljs');
const SparqlParser = new SparqlJs.Parser();
const ShExParser = require("@shexjs/parser").construct();
const Tests = __dirname;
const Resources = Path.join(__dirname, '../../fhir-sparql-common/src/main/resources/');
const FhirShEx = ShExParser.parse(File.readFileSync(Path.join(Resources, 'org/uu3/ShEx-mini-terse.shex'), 'utf-8'));
const {FhirSparql, ConnectingVariables, PredicateToShapeDecl, ArcTree, ToTurtle} = require('../lib/fhir-sparql');
// const X = require('../lib/Namespaces');
const {Ns, Rdf, Xsd, Fhir, FirstRest} = require('../lib/Namespaces');

const HapiServerAddr = 'http://localhost:8080/hapi/fhir/';

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

  it(`should construct and render ArcTrees`, () => {
    expect(new ArcTree({
      subject: {termType: 'BlankNode', value: 'b1'},
      predicate: Fhir.v,
      object: null
    }).toString()).toEqual('_:b1 <http://hl7.org/fhir/v> null .');
    expect(new ArcTree({
      subject: {termType: 'NamedNode', value: 'http://a.example/#a'},
      predicate: Fhir.v,
      object: {termType: 'Literal', value: 'a'}
    }).toString()).toEqual('<http://a.example/#a> <http://hl7.org/fhir/v> "a" .');
  })
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
    expect(rewriter.predicateToShapeDecl.get('http://hl7.org/fhir/item').map(d => d.id)).toEqual([ 'Questionnaire', 'Questionnaire.item' ]);
  });

  it('should translate obs-path', () => {
    const rewriter = new FhirSparql(FhirShEx);
    const iQuery = SparqlParser.parse(File.readFileSync(Path.join(Tests, '../../notes/obs-pat-mid.srq'), 'utf-8'));
    const {arcTrees, connectingVariables} = rewriter.getArcTrees(iQuery);

    // test arcTrees
    expect(arcTrees[0].getBgp().map(ToTurtle)).toEqual(BGP_obs.map(ToTurtle)); // ToTurtle helps with debugging
    expect(arcTrees[0].getBgp()).toEqual(BGP_obs);
    expect(arcTrees[1].getBgp().map(ToTurtle)).toEqual(BGP_subject.map(ToTurtle));
    expect(arcTrees[1].getBgp()).toEqual(BGP_subject);
    expect(arcTrees).toEqual([ArcTree_obs, ArcTree_subject]);

    // test connectingVariables
    expect(Object.fromEntries(connectingVariables)).toEqual(ConnectingVariables_obs_pat_mid);

    // generate FHIR Paths for the Observation ArcTree
    const obsPaths = rewriter.opBgpToFhirPathExecutions(arcTrees[0], connectingVariables, {});
    expect(obsPaths).toEqual([ { name: 'code', value: '789-8|http://loinc.org' } ]);

    // generate FHIR Paths for the first Patient ArcTree
    const patPath1 = rewriter.opBgpToFhirPathExecutions(arcTrees[1], connectingVariables, {
      subject: {termType: 'NamedNode', value: HapiServerAddr + 'Patient/1'}
    });
    console.log(patPath1);

    // generate FHIR Paths for the second Patient ArcTree
    const patPath2 = rewriter.opBgpToFhirPathExecutions(arcTrees[1], connectingVariables, {
      subject: {termType: 'NamedNode', value: HapiServerAddr + 'Patient/2'}
    });
  });

  it('should barf on cycles', () => {
    expect(() => new FhirSparql(FhirShEx).getArcTrees(SparqlParser.parse(
      `PREFIX fhir: <http://hl7.org/fhir/>
ASK {?obs fhir:subject ?subjectRef . ?subjectRef fhir:reference ?obs}`
    ))).toThrow("can't handle cycle involving ?subjectRef");
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
const T_obs_code_code = {
  subject: { termType: 'Variable', value: 'obs' },
  predicate: { termType: 'NamedNode', value: 'http://hl7.org/fhir/code' },
  object: { termType: 'Variable', value: 'code' }
};
const T_code_coding_codeList = {
  subject: { termType: 'Variable', value: 'code' },
  predicate: { termType: 'NamedNode', value: 'http://hl7.org/fhir/coding' },
  object: { termType: 'Variable', value: 'codeList' }
};
const T_codeList_FirstRest_codeElt = {
  subject: { termType: 'Variable', value: 'codeList' },
  predicate: FirstRest,
  object: { termType: 'Variable', value: 'codeElt' }
};
const T_codeElt_code_codeCode = {
  subject: { termType: 'Variable', value: 'codeElt' },
  predicate: { termType: 'NamedNode', value: 'http://hl7.org/fhir/code' },
  object: { termType: 'Variable', value: 'codeCode' }
};
const T_codeCode_v_789_8 = {
  subject: { termType: 'Variable', value: 'codeCode' },
  predicate: Fhir.v,
  object: { termType: 'Literal', value: '789-8', language: '', datatype: Xsd.string }
};
const T_codeElt_sytem_codingSystem = {
  subject: { termType: 'Variable', value: 'codeElt' },
  predicate: { termType: 'NamedNode', value: 'http://hl7.org/fhir/system' },
  object: { termType: 'Variable', value: 'codingSystem' }
};
const T_codingSystem_v_snomed = {
  subject: { termType: 'Variable', value: 'codingSystem' },
  predicate: Fhir.v,
  object: { termType: 'Literal', value: 'http://loinc.org', language: '', datatype: Xsd.anyURI }
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
  {tp: T_obs_code_code, out: [
    {tp: T_code_coding_codeList, out: [
      {tp: T_codeList_FirstRest_codeElt, out: [
        {tp: T_codeElt_code_codeCode, out: [
          {tp: T_codeCode_v_789_8, out: []}
        ]},
        {tp: T_codeElt_sytem_codingSystem, out: [
          {tp: T_codingSystem_v_snomed, out: []}
        ]}
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
const ConnectingVariables_obs_pat_mid = {
  subject: [
    {pos: 'object', arcTree: ArcTree_obs.out[2].out[0]}, // ArcTree_obs ArcTree with tp=T_subjectRef_reference_subject
    {pos: 'subject', arcTree: ArcTree_subject.out[0]}, // ArcTree_subject ArcTree with tp=T_subject_a_Patient
    {pos: 'subject', arcTree: ArcTree_subject.out[1]}, // ArcTree_subject ArcTree with tp=T_subject_id_patIdElt
  ]
};

// BGPs
const BGP_obs = [
  T_obs_A_Observation,
  T_obs_code_code,
  T_code_coding_codeList,
  T_codeList_FirstRest_codeElt,
  T_codeElt_code_codeCode,
  T_codeCode_v_789_8,
  T_codeElt_sytem_codingSystem,
  T_codingSystem_v_snomed,
  T_obs_subject_subjectRef,
  T_subjectRef_reference_subject,
];
const BGP_subject = [
  T_subject_a_Patient,
  T_subject_id_patIdElt,
  T_patIdElt_v_patId,
];
