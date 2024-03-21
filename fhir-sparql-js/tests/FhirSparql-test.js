const File = require('fs');
const Path = require('path');
const Tests = __dirname;
const Resources = Path.join(__dirname, '../../fhir-sparql-common/src/test/resources/org/uu3/');

const {RdfUtils, Bgp, SparqlQuery} = require('../dist/RdfUtils');
const {ArcTree} = require('../dist/ArcTree.js');
const {FhirSparql, ConnectingVariables, FhirPathExecution, Rule_CodeWithSystem} = require('../dist/FhirSparql');
const {QueryAnalyzer, PredicateToShapeDecl} = require('../dist/QueryAnalyzer');
const {Ns, Rdf, Xsd, Fhir, FirstRest} = require('../dist/Namespaces');

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
    it('should handle Obs-Patient ref', () => {
      const rewriter = new FhirSparql(FhirShEx);
      const iQuery = SparqlQuery.parse(File.readFileSync(Path.join(Resources, 'obs-pat.srq'), 'utf-8'));
      const {arcTrees, connectingVariables, referents} = rewriter.getArcTrees(iQuery);
      expect(arcTrees.map(at => at.toString()).join('\n')).toBe(`<root> [
  ?obs <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://hl7.org/fhir/Observation> .
  ?obs <http://hl7.org/fhir/code> ?code . [
    ?code <http://hl7.org/fhir/coding> ?codeList . [
      ?codeList <http://www.w3.org/1999/02/22-rdf-syntax-ns#rest>*/<http://www.w3.org/1999/02/22-rdf-syntax-ns#first> ?codeElt . [
        ?codeElt <http://hl7.org/fhir/code> ?codeCode . [
          ?codeCode <http://hl7.org/fhir/v> "72166-2" .
        ]
        ?codeElt <http://hl7.org/fhir/system> ?codingSystem . [
          ?codingSystem <http://hl7.org/fhir/v> "http://loinc.org"^^<http://www.w3.org/2001/XMLSchema#anyURI> .
        ]
      ]
    ]
  ]
  ?obs <http://hl7.org/fhir/subject> ?subjectRef . [
    ?subjectRef <http://hl7.org/fhir/reference> ?subjectBNode . [
      ?subjectBNode <http://hl7.org/fhir/link> ?subject .
    ]
  ]
]
<root> [
  ?subject <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://hl7.org/fhir/Patient> .
  ?subject <http://hl7.org/fhir/id> ?patIdElt . [
    ?patIdElt <http://hl7.org/fhir/v> ?patId .
  ]
]`);
      // test arcTrees
      expect(arcTrees[0].getBgp().toString()).toEqual(BGP_obs.toString());
      expect(arcTrees[0].getBgp()).toEqual(BGP_obs);
      expect(arcTrees[1].getBgp().toString()).toEqual(BGP_subject.toString());
      expect(arcTrees[1].getBgp()).toEqual(BGP_subject);
      expect(arcTrees).toEqual([ArcTree_obs, ArcTree_subject]);

      // connectingVariables
      expect(Object.fromEntries(connectingVariables)).toEqual(ConnectingVariables_obs_pat_mid);
      expect(ConnectingVariables.toString(connectingVariables)).toEqual(`subject
 0: object of { ?subjectRef <http://hl7.org/fhir/reference> ?subjectBNode . [
  ?subjectBNode <http://hl7.org/fhir/link> ?subject .
] }
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
          { name: 'code', value: 'http://loinc.org|72166-2' }
        ]
      )]);

      // generate FHIR Paths for the first Patient ArcTree
      const patPaths1 = rewriter.opBgpToFhirPathExecutions(arcTrees[1], referents, {
        subject: {termType: 'NamedNode', value: HapiServerAddr + 'Patient/1'}
      });
      expect(patPaths1).toEqual([new FhirPathExecution('Patient', null, [ { name: 'id', value: '1' } ])]);

      // hack: test parser for canonical( URL)s even though subects aren't canonicals
      const patPathsCanonical = rewriter.opBgpToFhirPathExecutions(arcTrees[1], referents, {
        subject: {termType: 'NamedNode', value: HapiServerAddr + 'Patient/1|123'}
      });
      expect(patPathsCanonical).toEqual([new FhirPathExecution('Patient', '123', [ { name: 'id', value: '1' } ])]);
      // Does FHIRPath reach any attributes with type canonical?

      expect(() => rewriter.opBgpToFhirPathExecutions(arcTrees[1], referents, {
        subject: {termType: 'NamedNode', value: 'ftp://localhost:8080/hapi/fhir/' + 'Patient/1'}
      })).toThrow('subject node ftp://localhost:8080/hapi/fhir/Patient/1 didn\'t match FHIR protocol');

      // generate FHIR Paths for the second Patient ArcTree
      const patPaths2 = rewriter.opBgpToFhirPathExecutions(arcTrees[1], referents, {
        subject: {termType: 'NamedNode', value: HapiServerAddr + 'Patient/2'}
      });
      expect(patPaths2).toEqual([new FhirPathExecution('Patient', null, [ { name: 'id', value: '2' } ])]);
    });

    it('should execute last test again (i.e. no side effects from previous run)', () => {
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
 0: object of { ?subjectRef <http://hl7.org/fhir/reference> ?subjectBNode . [
  ?subjectBNode <http://hl7.org/fhir/link> ?subject .
] }
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
          { name: 'code', value: 'http://loinc.org|72166-2' }
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

    it('should handle reference from Patient to Observation', () => {
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
 0: object of { ?subjectRef <http://hl7.org/fhir/reference> ?subjectBNode . [
  ?subjectBNode <http://hl7.org/fhir/link> ?subject .
] }
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
          { name: 'code', value: 'http://loinc.org|72166-2' }
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

    it('should handle reference from Patient to Observation with pesimized query triples order', () => {
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
 0: object of { ?subjectRef <http://hl7.org/fhir/reference> ?subjectBNode . [
  ?subjectBNode <http://hl7.org/fhir/link> ?subject .
] }
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
          { name: 'code', value: 'http://loinc.org|72166-2' }
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

    it('should handle Obs-Patient ref with blank nodes where possible', () => {
      const rewriter = new FhirSparql(FhirShEx);
      const iQuery = SparqlQuery.parse(File.readFileSync(Path.join(Resources, 'obs-pat-anons.srq'), 'utf-8'));
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
          { name: 'code', value: 'http://loinc.org|72166-2' }
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

    it('should recognize fall-back (simple) path rule', () => {
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
          { name: 'code', value: '72166-2' }
        ]
      )]);
    });

    it('should translate a structure of nested (anonymous) BNodes', () => {
      const rewriter = new FhirSparql(FhirShEx);
      const iQuery = SparqlQuery.parse(File.readFileSync(Path.join(Resources, 'obs-anons-id.srq'), 'utf-8'));
      const {arcTrees, connectingVariables, referents} = rewriter.getArcTrees(iQuery);
      expect(arcTrees[0].getBgp().triples.length).toEqual(8);
      expect(connectingVariables).toEqual(new Map([]))
      expect(referents).toEqual(new Set());
      const obsPaths = rewriter.opBgpToFhirPathExecutions(arcTrees[0], referents, {});
      expect(obsPaths).toEqual([new FhirPathExecution('Observation', null, [{ name: 'id', value: '789' }])]);
    });

    it('should deconstruct type arc', () => {
      const rewriter = new FhirSparql(FhirShEx);
      const iQuery = SparqlQuery.parse(File.readFileSync(Path.join(Resources, 'obs-code-type.srq'), 'utf-8'));
      const {arcTrees, connectingVariables, referents} = rewriter.getArcTrees(iQuery);
      expect(connectingVariables).toEqual(new Map([]))
      expect(referents).toEqual(new Set());
      const obsPaths = rewriter.opBgpToFhirPathExecutions(arcTrees[0], referents, {});
      expect(obsPaths).toEqual([new FhirPathExecution('Observation', null, [ {
        name: 'code',
        value: 'http://loinc.org|72166-2'
      } ])]);
    });

    it('should inject multiple literal values', () => {
      const rewriter = new FhirSparql(FhirShEx);
      const iQuery = SparqlQuery.parse(File.readFileSync(Path.join(Resources, 'obs-fixed-pat.srq'), 'utf-8'));
      const {arcTrees, connectingVariables, referents} = rewriter.getArcTrees(iQuery);
      expect(arcTrees[0].getBgp().triples.length).toEqual(11);
      expect(connectingVariables).toEqual(new Map([]))
      expect(referents).toEqual(new Set());
      const obsPaths = rewriter.opBgpToFhirPathExecutions(arcTrees[0], referents, {});
      expect(obsPaths).toEqual([new FhirPathExecution('Observation', null, [
        {
          "name": "subject",
          "value": "http://localhost:8080/hapi/fhir/Patient/1"
        },
        {
          "name": "code",
          "value": "http://loinc.org|72166-2"
        }
      ])]);
    });

    it('should handle lack of Resource type', () => {
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
 0: object of { ?subjectRef <http://hl7.org/fhir/reference> ?subjectBNode . [
  ?subjectBNode <http://hl7.org/fhir/link> ?subject .
] }
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
            { name: 'code', value: 'http://loinc.org|72166-2' }
          ]
        ),
        new FhirPathExecution(
          'Procedure', // type
          null, // version
          [ // paths
            { name: 'code', value: 'http://loinc.org|72166-2' }
          ]
        ),
      ]);

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

    it('should unify coincident variables', () => {
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
            { name: 'code', value: 'http://loinc.org|72166-2' }
          ]
        )]);
      const procPaths = rewriter.opBgpToFhirPathExecutions(arcTrees[1], referents, {});
      expect(procPaths).toEqual([
        new FhirPathExecution(
          'Procedure', // type
          null, // version
          [ // paths
            { name: 'code', value: 'http://loinc.org|72166-2' }
          ]
        )]);

      // generate FHIR Paths for the first Patient ArcTree
      const patPaths1 = rewriter.opBgpToFhirPathExecutions(arcTrees[0], referents, {
      });
      expect(patPaths1).toEqual([new FhirPathExecution('Observation', null, [ { name: 'code', value: 'http://loinc.org|72166-2' } ])]);

      // generate FHIR Paths for the second Patient ArcTree
      const patPaths2 = rewriter.opBgpToFhirPathExecutions(arcTrees[1], referents, {
        subject: {termType: 'NamedNode', value: HapiServerAddr + 'Patient/2'}
      });
      expect(patPaths2).toEqual([new FhirPathExecution('Procedure', null, [
        { name: 'subject', value: 'http://localhost:8080/hapi/fhir/Patient/2' },
        { name: 'code', value: 'http://loinc.org|72166-2' },
      ])]);
    });

    it('should dive into extended and datatypes', () => {
      const rewriter = new FhirSparql(FhirShEx);
      const iQuery = SparqlQuery.parse(File.readFileSync(Path.join(Resources, 'resource-anons-text.srq'), 'utf-8'));
      const {arcTrees, connectingVariables, referents} = rewriter.getArcTrees(iQuery);
      expect(arcTrees[0].getBgp().triples.length).toEqual(2);
      expect(connectingVariables).toEqual(new Map([]))
      expect(referents).toEqual(new Set());
      const obsPaths = rewriter.opBgpToFhirPathExecutions(arcTrees[0], referents, {});
      expect(obsPaths).toEqual([
        new FhirPathExecution('Observation', null, []),
        new FhirPathExecution('Patient', null, []),
        new FhirPathExecution('Procedure', null, []),
        new FhirPathExecution('Questionnaire', null, []),
      ]);
    });

    it('should restrict types with each successive ArcTree', () => {
      const rewriter = new FhirSparql(FhirShEx);
      const iQuery = SparqlQuery.parse(File.readFileSync(Path.join(Resources, 'resource-anons-text-var.srq'), 'utf-8'));
      const {arcTrees, connectingVariables, referents} = rewriter.getArcTrees(iQuery);
      expect(arcTrees[0].getBgp().triples.length).toEqual(8);
      expect(connectingVariables).toEqual(new Map([]))
      expect(referents).toEqual(new Set());
      const obsPaths = rewriter.opBgpToFhirPathExecutions(arcTrees[0], referents, {});
      expect(obsPaths).toEqual([new FhirPathExecution('Questionnaire', null, [])]);
    });

    it('should accept valid value set values', () => {
      const rewriter = new FhirSparql(FhirShEx);
      const iQuery = SparqlQuery.parse(File.readFileSync(Path.join(Resources, 'resource-anons-text-valid.srq'), 'utf-8'));
      const {arcTrees, connectingVariables, referents} = rewriter.getArcTrees(iQuery);
      expect(arcTrees[0].getBgp().triples.length).toEqual(8);
      expect(connectingVariables).toEqual(new Map([]))
      expect(referents).toEqual(new Set());
      const obsPaths = rewriter.opBgpToFhirPathExecutions(arcTrees[0], referents, {});
      expect(obsPaths).toEqual([new FhirPathExecution('Questionnaire', null, [])]);
    });

    xit('should reject invalid value set values', () => {
      // per ArcTreeFitsInShapeExpr:
      // Weakness: currently accepts if any {con,dis}junct accepts. Should accept only if all conjuncts that mention X accept X.
      const rewriter = new FhirSparql(FhirShEx);
      const iQuery = SparqlQuery.parse(File.readFileSync(Path.join(Resources, 'resource-anons-text-invalid.srq'), 'utf-8'));
      const {arcTrees, connectingVariables, referents} = rewriter.getArcTrees(iQuery);
      expect(arcTrees[0].getBgp().triples.length).toEqual(8);
      expect(connectingVariables).toEqual(new Map([]))
      expect(referents).toEqual(new Set());
      const obsPaths = rewriter.opBgpToFhirPathExecutions(arcTrees[0], referents, {});
      expect(obsPaths).toEqual([]);
    });
  });
});

/*
  Reference values for tests above.
 */

// Triples
const Triples = {
  obs_A_Observation: {
    subject: { termType: 'Variable', value: 'obs' },
    predicate: Rdf.type,
    object: { termType: 'NamedNode', value: 'http://hl7.org/fhir/Observation' } },
  obs_code_code: {
    subject: { termType: 'Variable', value: 'obs' },
    predicate: { termType: 'NamedNode', value: 'http://hl7.org/fhir/code' },
    object: { termType: 'Variable', value: 'code' } },
  code_coding_codeList: {
    subject: { termType: 'Variable', value: 'code' },
    predicate: { termType: 'NamedNode', value: 'http://hl7.org/fhir/coding' },
    object: { termType: 'Variable', value: 'codeList' } },
  codeList_FirstRest_codeElt: {
    subject: { termType: 'Variable', value: 'codeList' },
    predicate: FirstRest,
    object: { termType: 'Variable', value: 'codeElt' } },
  codeElt_code_codeCode: {
    subject: { termType: 'Variable', value: 'codeElt' },
    predicate: { termType: 'NamedNode', value: 'http://hl7.org/fhir/code' },
    object: { termType: 'Variable', value: 'codeCode' } },
  codeCode_v_72166_2: {
    subject: { termType: 'Variable', value: 'codeCode' },
    predicate: Fhir.v,
    object: { termType: 'Literal', value: '72166-2', language: '', datatype: Xsd.string } },
  codeElt_sytem_codingSystem: {
    subject: { termType: 'Variable', value: 'codeElt' },
    predicate: { termType: 'NamedNode', value: 'http://hl7.org/fhir/system' },
    object: { termType: 'Variable', value: 'codingSystem' } },
  codingSystem_v_snomed: {
    subject: { termType: 'Variable', value: 'codingSystem' },
    predicate: Fhir.v,
    object: { termType: 'Literal', value: 'http://loinc.org', language: '', datatype: Xsd.anyURI } },
  obs_subject_subjectRef: {
    subject: { termType: 'Variable', value: 'obs' },
    predicate: { termType: 'NamedNode', value: 'http://hl7.org/fhir/subject' },
    object: { termType: 'Variable', value: 'subjectRef' } },
  subjectRef_reference_subjectBNode: {
    subject: { termType: 'Variable', value: 'subjectRef' },
    predicate: { termType: 'NamedNode', value: 'http://hl7.org/fhir/reference' },
    object: { termType: 'Variable', value: 'subjectBNode' } },
  subjectBNode_link_subject: {
    subject: { termType: 'Variable', value: 'subjectBNode' },
    predicate: { termType: 'NamedNode', value: 'http://hl7.org/fhir/link' },
    object: { termType: 'Variable', value: 'subject' } },
  subject_a_Patient: {
    subject: { termType: 'Variable', value: 'subject' },
    predicate: Rdf.type,
    object: { termType: 'NamedNode', value: 'http://hl7.org/fhir/Patient' } },
  subject_id_patIdElt: {
    subject: { termType: 'Variable', value: 'subject' },
    predicate: { termType: 'NamedNode', value: 'http://hl7.org/fhir/id' },
    object: { termType: 'Variable', value: 'patIdElt' } },
  patIdElt_v_patId: {
    subject: { termType: 'Variable', value: 'patIdElt' },
    predicate: Fhir.v,
    object: { termType: 'Variable', value: 'patId' } },
}

// ArcTrees
const ArcTree_obs_noType = {tp: null, out: [
  {tp: Triples.obs_code_code, out: [
    {tp: Triples.code_coding_codeList, out: [
      {tp: Triples.codeList_FirstRest_codeElt, out: [
        {tp: Triples.codeElt_code_codeCode, out: [
          {tp: Triples.codeCode_v_72166_2, out: []}
        ]},
        {tp: Triples.codeElt_sytem_codingSystem, out: [
          {tp: Triples.codingSystem_v_snomed, out: []}
        ]}
      ]}
    ]}
  ]},
  {tp: Triples.obs_subject_subjectRef, out: [
    {tp: Triples.subjectRef_reference_subjectBNode, out: [
      {tp: Triples.subjectBNode_link_subject, out: []}
    ]}
  ]}
]};
const ArcTree_obs = {tp: null, out: [
  {tp: Triples.obs_A_Observation, out: []},
].concat(ArcTree_obs_noType.out)};
const ArcTree_subject = {tp: null, out: [
  {tp: Triples.subject_a_Patient, out: []},
  {tp: Triples.subject_id_patIdElt, out: [
    {tp: Triples.patIdElt_v_patId, out: []}
  ]}
]};

// ConnectingVariables
const ConnectingVariables_obs_pat_mid = {
  subject: [
    {pos: 'object', arcTree: ArcTree_obs.out[2].out[0]}, // ArcTree_obs ArcTree with tp=Triples.subjectRef_reference_subject
    {pos: 'subject', arcTree: ArcTree_subject.out[0]}, // ArcTree_subject ArcTree with tp=Triples.subject_a_Patient
    {pos: 'subject', arcTree: ArcTree_subject.out[1]}, // ArcTree_subject ArcTree with tp=Triples.subject_id_patIdElt
  ]
};

// BGPs
const BGP_obs_noType = Bgp.blessSparqlJs({type: 'bgp', triples: [
  Triples.obs_code_code,
  Triples.code_coding_codeList,
  Triples.codeList_FirstRest_codeElt,
  Triples.codeElt_code_codeCode,
  Triples.codeCode_v_72166_2,
  Triples.codeElt_sytem_codingSystem,
  Triples.codingSystem_v_snomed,
  Triples.obs_subject_subjectRef,
  Triples.subjectRef_reference_subjectBNode,
  Triples.subjectBNode_link_subject,
]});
const BGP_obs = Bgp.blessSparqlJs({type: 'bgp', triples: [
  Triples.obs_A_Observation,
].concat(BGP_obs_noType.triples)});
const BGP_subject = Bgp.blessSparqlJs({type: 'bgp', triples: [
  Triples.subject_a_Patient,
  Triples.subject_id_patIdElt,
  Triples.patIdElt_v_patId,
]});
