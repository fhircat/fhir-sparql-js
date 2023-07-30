const File = require('fs');
const Path = require('path');
const Tests = __dirname;
const Resources = Path.join(__dirname, '../../fhir-sparql-common/src/test/resources/org/uu3/');

const {RdfUtils, Bgp, SparqlQuery} = require('../lib/RdfUtils');
const {ArcTree} = require('../lib/ArcTree.js');
const {FhirSparql, ConnectingVariables, FhirPathExecution, Rule_CodeWithSystem} = require('../lib/FhirSparql');
const {QueryAnalyzer, PredicateToShapeDecl} = require('../lib/QueryAnalyzer');
const {Ns, Rdf, Xsd, Fhir, FirstRest} = require('../lib/Namespaces');

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
    it('should translate obs-pat', () => {
      const rewriter = new FhirSparql(FhirShEx);
      const iQuery = SparqlQuery.parse(File.readFileSync(Path.join(Resources, 'obs-pat.srq'), 'utf-8'));
      const {arcTrees, connectingVariables, referents} = rewriter.getArcTrees(iQuery);

      // test arcTrees
      expect(arcTrees[0].getBgp().toString()).toEqual(BGP_obs.toString());
      expect(arcTrees[0].getBgp()).toEqual(BGP_obs);
      expect(arcTrees[1].getBgp().toString()).toEqual(BGP_subject.toString());
      expect(arcTrees[1].getBgp()).toEqual(BGP_subject);
      expect(arcTrees).toEqual([ArcTree_obs, ArcTree_subject]);

      // connectingVariables
      expect(Object.fromEntries(connectingVariables)).toEqual(ConnectingVariables_obs_pat_mid);
      expect(ConnectingVariables.toString(connectingVariables)).toEqual(`subject
 0: object of { ?subjectRef <http://hl7.org/fhir/reference> ?subject . }
 1: subject of { ?subject <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://hl7.org/fhir/Patient> . }
 2: subject of { ?subject <http://hl7.org/fhir/id> ?patIdElt . [
  ?patIdElt <http://hl7.org/fhir/v> ?patId .
] }`)

      // referents
      expect(referents).toEqual(new Set(['subject']));

      // generate FHIR Paths for the Observation ArcTree
      const obsPaths = rewriter.opBgpToFhirPathExecutions(arcTrees[0], referents, {});
      expect(obsPaths).toEqual([new FhirPathExecution(
        'Observation', // type
        null, // version
        [ // paths
          { name: 'code', value: '789-8|http://loinc.org' }
        ]
      )]);

      // generate FHIR Paths for the first Patient ArcTree
      const patPaths1 = rewriter.opBgpToFhirPathExecutions(arcTrees[1], referents, {
        subject: {termType: 'NamedNode', value: HapiServerAddr + 'Patient/1'}
      });
      expect(patPaths1).toEqual([new FhirPathExecution('Patient', null, [ { name: 'id', value: '1' } ])]);

      // generate FHIR Paths for the second Patient ArcTree
      const patPaths2 = rewriter.opBgpToFhirPathExecutions(arcTrees[1], referents, {
        subject: {termType: 'NamedNode', value: HapiServerAddr + 'Patient/2'}
      });
      expect(patPaths2).toEqual([new FhirPathExecution('Patient', null, [ { name: 'id', value: '2' } ])]);
    });

    it('should translate obs-pat again (i.e. no side effects from previous run)', () => {
      const rewriter = new FhirSparql(FhirShEx);
      const iQuery = SparqlQuery.parse(File.readFileSync(Path.join(Resources, 'obs-pat.srq'), 'utf-8'));
      const {arcTrees, connectingVariables, referents} = rewriter.getArcTrees(iQuery);

      // test arcTrees
      expect(arcTrees[0].getBgp().toString()).toEqual(BGP_obs.toString());
      expect(arcTrees[0].getBgp()).toEqual(BGP_obs);
      expect(arcTrees[1].getBgp().toString()).toEqual(BGP_subject.toString());
      expect(arcTrees[1].getBgp()).toEqual(BGP_subject);
      expect(arcTrees).toEqual([ArcTree_obs, ArcTree_subject]);

      // connectingVariables
      expect(Object.fromEntries(connectingVariables)).toEqual(ConnectingVariables_obs_pat_mid);
      expect(ConnectingVariables.toString(connectingVariables)).toEqual(`subject
 0: object of { ?subjectRef <http://hl7.org/fhir/reference> ?subject . }
 1: subject of { ?subject <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://hl7.org/fhir/Patient> . }
 2: subject of { ?subject <http://hl7.org/fhir/id> ?patIdElt . [
  ?patIdElt <http://hl7.org/fhir/v> ?patId .
] }`)

      // referents
      expect(referents).toEqual(new Set(['subject']));

      // generate FHIR Paths for the Observation ArcTree
      const obsPaths = rewriter.opBgpToFhirPathExecutions(arcTrees[0], referents, {});
      expect(obsPaths).toEqual([new FhirPathExecution(
        'Observation', // type
        null, // version
        [ // paths
          { name: 'code', value: '789-8|http://loinc.org' }
        ]
      )]);

      // generate FHIR Paths for the first Patient ArcTree
      const patPaths1 = rewriter.opBgpToFhirPathExecutions(arcTrees[1], referents, {
        subject: {termType: 'NamedNode', value: HapiServerAddr + 'Patient/1'}
      });
      expect(patPaths1).toEqual([new FhirPathExecution('Patient', null, [ { name: 'id', value: '1' } ])]);

      // generate FHIR Paths for the second Patient ArcTree
      const patPaths2 = rewriter.opBgpToFhirPathExecutions(arcTrees[1], referents, {
        subject: {termType: 'NamedNode', value: HapiServerAddr + 'Patient/2'}
      });
      expect(patPaths2).toEqual([new FhirPathExecution('Patient', null, [ { name: 'id', value: '2' } ])]);
    });

    it('should translate pat-obs', () => {
      const rewriter = new FhirSparql(FhirShEx);
      const iQuery = SparqlQuery.parse(File.readFileSync(Path.join(Resources, 'obs-pat-disordered.srq'), 'utf-8'));
      const {arcTrees, connectingVariables, referents} = rewriter.getArcTrees(iQuery);

      // test arcTrees
      expect(arcTrees[0].getBgp().toString()).toEqual(BGP_obs.toString());
      expect(arcTrees[0].getBgp()).toEqual(BGP_obs);
      expect(arcTrees[1].getBgp().toString()).toEqual(BGP_subject.toString());
      expect(arcTrees[1].getBgp()).toEqual(BGP_subject);
      expect(arcTrees).toEqual([ArcTree_obs, ArcTree_subject]);

      // connectingVariables
      expect(Object.fromEntries(connectingVariables)).toEqual(ConnectingVariables_obs_pat_mid);
      expect(ConnectingVariables.toString(connectingVariables)).toEqual(`subject
 0: object of { ?subjectRef <http://hl7.org/fhir/reference> ?subject . }
 1: subject of { ?subject <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://hl7.org/fhir/Patient> . }
 2: subject of { ?subject <http://hl7.org/fhir/id> ?patIdElt . [
  ?patIdElt <http://hl7.org/fhir/v> ?patId .
] }`)

      // referents
      expect(referents).toEqual(new Set(['subject']));

      // generate FHIR Paths for the Observation ArcTree
      const obsPaths = rewriter.opBgpToFhirPathExecutions(arcTrees[0], referents, {});
      expect(obsPaths).toEqual([new FhirPathExecution(
        'Observation', // type
        null, // version
        [ // paths
          { name: 'code', value: '789-8|http://loinc.org' }
        ]
      )]);

      // generate FHIR Paths for the first Patient ArcTree
      const patPaths1 = rewriter.opBgpToFhirPathExecutions(arcTrees[1], referents, {
        subject: {termType: 'NamedNode', value: HapiServerAddr + 'Patient/1'}
      });
      expect(patPaths1).toEqual([new FhirPathExecution('Patient', null, [ { name: 'id', value: '1' } ])]);

      // generate FHIR Paths for the second Patient ArcTree
      const patPaths2 = rewriter.opBgpToFhirPathExecutions(arcTrees[1], referents, {
        subject: {termType: 'NamedNode', value: HapiServerAddr + 'Patient/2'}
      });
      expect(patPaths2).toEqual([new FhirPathExecution('Patient', null, [ { name: 'id', value: '2' } ])]);
    });

    it('should translate obs-pat-disordered - same but with query triples in arbitrary order', () => {
      const rewriter = new FhirSparql(FhirShEx);
      const iQuery = SparqlQuery.parse(File.readFileSync(Path.join(Resources, 'obs-pat-disordered.srq'), 'utf-8'));
      const {arcTrees, connectingVariables, referents} = rewriter.getArcTrees(iQuery);

      // test arcTrees
      expect(arcTrees[0].getBgp().toString()).toEqual(BGP_obs.toString());
      expect(arcTrees[0].getBgp()).toEqual(BGP_obs);
      expect(arcTrees[1].getBgp().toString()).toEqual(BGP_subject.toString());
      expect(arcTrees[1].getBgp()).toEqual(BGP_subject);
      expect(arcTrees).toEqual([ArcTree_obs, ArcTree_subject]);

      // connectingVariables
      expect(Object.fromEntries(connectingVariables)).toEqual(ConnectingVariables_obs_pat_mid);
      expect(ConnectingVariables.toString(connectingVariables)).toEqual(`subject
 0: object of { ?subjectRef <http://hl7.org/fhir/reference> ?subject . }
 1: subject of { ?subject <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://hl7.org/fhir/Patient> . }
 2: subject of { ?subject <http://hl7.org/fhir/id> ?patIdElt . [
  ?patIdElt <http://hl7.org/fhir/v> ?patId .
] }`)

      // referents
      expect(referents).toEqual(new Set(['subject']));

      // generate FHIR Paths for the Observation ArcTree
      const obsPaths = rewriter.opBgpToFhirPathExecutions(arcTrees[0], referents, {});
      expect(obsPaths).toEqual([new FhirPathExecution(
        'Observation', // type
        null, // version
        [ // paths
          { name: 'code', value: '789-8|http://loinc.org' }
        ]
      )]);

      // generate FHIR Paths for the first Patient ArcTree
      const patPaths1 = rewriter.opBgpToFhirPathExecutions(arcTrees[1], referents, {
        subject: {termType: 'NamedNode', value: HapiServerAddr + 'Patient/1'}
      });
      expect(patPaths1).toEqual([new FhirPathExecution('Patient', null, [ { name: 'id', value: '1' } ])]);

      // generate FHIR Paths for the second Patient ArcTree
      const patPaths2 = rewriter.opBgpToFhirPathExecutions(arcTrees[1], referents, {
        subject: {termType: 'NamedNode', value: HapiServerAddr + 'Patient/2'}
      });
      expect(patPaths2).toEqual([new FhirPathExecution('Patient', null, [ { name: 'id', value: '2' } ])]);
    });

    it('should translate obs-pat-maxBnodes - same but with blank nodes where possible', () => {
      const rewriter = new FhirSparql(FhirShEx);
      const iQuery = SparqlQuery.parse(File.readFileSync(Path.join(Resources, 'obs-pat-maxBnodes.srq'), 'utf-8'));
      const {arcTrees, connectingVariables, referents} = rewriter.getArcTrees(iQuery);

      // connectingVariables
      expect([...connectingVariables.keys()]).toEqual(["subject"]);
      expect(connectingVariables.get('subject').map(elt =>
        ({pos: elt.pos, p: elt.arcTree.tp.predicate.value})
      )).toEqual([
        {pos: "object", p: "http://hl7.org/fhir/reference"},
        {pos: "subject", p: "http://www.w3.org/1999/02/22-rdf-syntax-ns#type"},
        {pos: "subject", p: "http://hl7.org/fhir/id"},
      ]);

      // referents
      expect(referents).toEqual(new Set(['subject']));

      // generate FHIR Paths for the Observation ArcTree
      const obsPaths = rewriter.opBgpToFhirPathExecutions(arcTrees[0], referents, {});
      expect(obsPaths).toEqual([new FhirPathExecution(
        'Observation', // type
        null, // version
        [ // paths
          { name: 'code', value: '789-8|http://loinc.org' }
        ]
      )]);

      // generate FHIR Paths for the first Patient ArcTree
      const patPaths1 = rewriter.opBgpToFhirPathExecutions(arcTrees[1], referents, {
        subject: {termType: 'NamedNode', value: HapiServerAddr + 'Patient/1'}
      });
      expect(patPaths1).toEqual([new FhirPathExecution('Patient', null, [ { name: 'id', value: '1' } ])]);

      // generate FHIR Paths for the second Patient ArcTree
      const patPaths2 = rewriter.opBgpToFhirPathExecutions(arcTrees[1], referents, {
        subject: {termType: 'NamedNode', value: HapiServerAddr + 'Patient/2'}
      });
      expect(patPaths2).toEqual([new FhirPathExecution('Patient', null, [ { name: 'id', value: '2' } ])]);
    });

    it('should translate obs-code', () => {
      const rewriter = new FhirSparql(FhirShEx);
      const iQuery = SparqlQuery.parse(File.readFileSync(Path.join(Resources, 'obs-code.srq'), 'utf-8'));
      const {arcTrees, connectingVariables, referents} = rewriter.getArcTrees(iQuery);

      // referents
      expect(referents).toEqual(new Set([]));

      // test connectingVariables
      expect(connectingVariables).toEqual(new Map());

      // generate FHIR Paths for the Observation ArcTree
      const obsPaths = rewriter.opBgpToFhirPathExecutions(arcTrees[0], referents, {});
      expect(obsPaths).toEqual([new FhirPathExecution(
        'Observation', // type
        null, // version
        [ // paths
          { name: 'code', value: '789-8' }
        ]
      )]);
    });

    it('should translate obs-id', () => {
      const rewriter = new FhirSparql(FhirShEx);
      const iQuery = SparqlQuery.parse(File.readFileSync(Path.join(Resources, 'obs-id.srq'), 'utf-8'));
      const {arcTrees, connectingVariables, referents} = rewriter.getArcTrees(iQuery);
      expect(arcTrees[0].getBgp().triples.length).toEqual(8);
      expect(connectingVariables).toEqual(new Map([]))
      expect(referents).toEqual(new Set());
      const obsPaths = rewriter.opBgpToFhirPathExecutions(arcTrees[0], referents, {});
      expect(obsPaths).toEqual([new FhirPathExecution('Observation', null, [{ name: 'id', value: '789' }])]);
    });

    xit('should translate obs-fixed-pat', () => {
      const rewriter = new FhirSparql(FhirShEx);
      const iQuery = SparqlQuery.parse(File.readFileSync(Path.join(Resources, 'obs-fixed-pat.srq'), 'utf-8'));
      const {arcTrees, connectingVariables, referents} = rewriter.getArcTrees(iQuery);
      expect(arcTrees[0].getBgp().triples.length).toEqual(10);
      expect(connectingVariables).toEqual(new Map([]))
      expect(referents).toEqual(new Set());
      const obsPaths = rewriter.opBgpToFhirPathExecutions(arcTrees[0], referents, {});
      expect(obsPaths).toEqual([new FhirPathExecution('Observation', null, [])]);
    });

    it('should translate obs-pat-noType', () => {
      const rewriter = new FhirSparql(FhirShEx);
      const iQuery = SparqlQuery.parse(File.readFileSync(Path.join(Resources, 'obs-pat-noType.srq'), 'utf-8'));
      const {arcTrees, connectingVariables, referents} = rewriter.getArcTrees(iQuery);

      // test arcTrees
      expect(arcTrees[0].getBgp().toString()).toEqual(BGP_obs_noType.toString());
      expect(arcTrees[0].getBgp()).toEqual(BGP_obs_noType);
      expect(arcTrees[1].getBgp().toString()).toEqual(BGP_subject.toString());
      expect(arcTrees[1].getBgp()).toEqual(BGP_subject);
      expect(arcTrees).toEqual([ArcTree_obs_noType, ArcTree_subject]);

      // connectingVariables
      expect(Object.fromEntries(connectingVariables)).toEqual(ConnectingVariables_obs_pat_mid);
      expect(ConnectingVariables.toString(connectingVariables)).toEqual(`subject
 0: object of { ?subjectRef <http://hl7.org/fhir/reference> ?subject . }
 1: subject of { ?subject <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://hl7.org/fhir/Patient> . }
 2: subject of { ?subject <http://hl7.org/fhir/id> ?patIdElt . [
  ?patIdElt <http://hl7.org/fhir/v> ?patId .
] }`)

      // referents
      expect(referents).toEqual(new Set(['subject']));

      // generate FHIR Paths for the Observation ArcTree
      const obsPaths = rewriter.opBgpToFhirPathExecutions(arcTrees[0], referents, {});
      expect(obsPaths).toEqual([
        new FhirPathExecution(
          'Observation', // type
          null, // version
          [ // paths
            { name: 'code', value: '789-8|http://loinc.org' }
          ]
        ),
        new FhirPathExecution(
          'Procedure', // type
          null, // version
          [ // paths
            { name: 'code', value: '789-8|http://loinc.org' }
          ]
        )]);

      // generate FHIR Paths for the first Patient ArcTree
      const patPaths1 = rewriter.opBgpToFhirPathExecutions(arcTrees[1], referents, {
        subject: {termType: 'NamedNode', value: HapiServerAddr + 'Patient/1'}
      });
      expect(patPaths1).toEqual([new FhirPathExecution('Patient', null, [ { name: 'id', value: '1' } ])]);

      // generate FHIR Paths for the second Patient ArcTree
      const patPaths2 = rewriter.opBgpToFhirPathExecutions(arcTrees[1], referents, {
        subject: {termType: 'NamedNode', value: HapiServerAddr + 'Patient/2'}
      });
      expect(patPaths2).toEqual([new FhirPathExecution('Patient', null, [ { name: 'id', value: '2' } ])]);
    });

    it('should translate obs-proc', () => {
      const rewriter = new FhirSparql(FhirShEx);
      const iQuery = SparqlQuery.parse(File.readFileSync(Path.join(Resources, 'obs-proc.srq'), 'utf-8'));
      const {arcTrees, connectingVariables, referents} = rewriter.getArcTrees(iQuery);
      // console.log(arcTrees.map(arcTree => arcTree.toString()));

      // referents
      expect(referents).toEqual(new Set(['subject']));

      // generate FHIR Paths for the Observation ArcTree
      const obsPaths = rewriter.opBgpToFhirPathExecutions(arcTrees[0], referents, {});
      expect(obsPaths).toEqual([
        new FhirPathExecution(
          'Observation', // type
          null, // version
          [ // paths
            { name: 'code', value: '789-8|http://loinc.org' }
          ]
        )]);
      const procPaths = rewriter.opBgpToFhirPathExecutions(arcTrees[1], referents, {});
      expect(procPaths).toEqual([
        new FhirPathExecution(
          'Procedure', // type
          null, // version
          [ // paths
            { name: 'code', value: '789-8|http://loinc.org' }
          ]
        )]);

      // generate FHIR Paths for the first Patient ArcTree
      const patPaths1 = rewriter.opBgpToFhirPathExecutions(arcTrees[0], referents, {
      });
      expect(patPaths1).toEqual([new FhirPathExecution('Observation', null, [ { name: 'code', value: '789-8|http://loinc.org' } ])]);

      // generate FHIR Paths for the second Patient ArcTree
      const patPaths2 = rewriter.opBgpToFhirPathExecutions(arcTrees[1], referents, {
        subject: {termType: 'NamedNode', value: HapiServerAddr + 'Patient/2'}
      });
      expect(patPaths2).toEqual([new FhirPathExecution('Procedure', null, [
        { name: 'subject', value: 'http://localhost:8080/hapi/fhir/Patient/2' },
        { name: 'code', value: '789-8|http://loinc.org' },
      ])]);
    });
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
const ArcTree_obs_noType = {tp: null, out: [
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
const ArcTree_obs = {tp: null, out: [
  {tp: T_obs_A_Observation, out: []},
].concat(ArcTree_obs_noType.out)};
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
const BGP_obs_noType = Bgp.blessSparqlJs({type: 'bgp', triples: [
  T_obs_code_code,
  T_code_coding_codeList,
  T_codeList_FirstRest_codeElt,
  T_codeElt_code_codeCode,
  T_codeCode_v_789_8,
  T_codeElt_sytem_codingSystem,
  T_codingSystem_v_snomed,
  T_obs_subject_subjectRef,
  T_subjectRef_reference_subject,
]});
const BGP_obs = Bgp.blessSparqlJs({type: 'bgp', triples: [
  T_obs_A_Observation,
].concat(BGP_obs_noType.triples)});
const BGP_subject = Bgp.blessSparqlJs({type: 'bgp', triples: [
  T_subject_a_Patient,
  T_subject_id_patIdElt,
  T_patIdElt_v_patId,
]});
