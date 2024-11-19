const File = require('fs');
const Path = require('path');
const Tests = __dirname;
const Resources = Path.join(__dirname, '../fhir-sparql-test/src/test/resources/org/uu3/');

const {RdfUtils, Bgp, SparqlQuery} = require('../dist/RdfUtils');
const {QueryAnalyzer, PredicateToShapeDecls} = require('../dist/QueryAnalyzer');
const {Ns, Rdf, Xsd, Fhir, FirstRest} = require('../dist/Namespaces');

const ShExParser = require("@shexjs/parser").construct();
const FhirShEx = ShExParser.parse(File.readFileSync(Path.join(Resources, 'ShEx-mini-terse.shex'), 'utf-8'));

describe('QueryAnalyzer', () => {
  describe('PredicateToShapeDecl', () => {
    it('should work from ShapeDecl', () => {
      const visitor = new PredicateToShapeDecls();
      expect(visitor.visitShapeDecl(FhirShEx.shapes[0]).type).toEqual("ShapeDecl");
    });

    it('should throw with bad arguments', () => {
      const visitor = new PredicateToShapeDecls();
      expect(() => visitor.visitSchema(null)).toThrow(/got null/);
      expect(() => visitor.visitSchema("schema")).toThrow(/got "schema"/);
      expect(() => visitor.visitSchema({type: "schema999"})).toThrow(/got {"type":"schema999"}/);
      expect(() => visitor.visitShapeExpr(FhirShEx.shapes[0].shapeExpr)).toThrow(/while not in a ShapeDecl/);
    });
  });

  describe('QueryAnalyzer', () => {
    it('should index predicates', () => {
      const rewriter = new QueryAnalyzer(FhirShEx);
      expect(rewriter.predicateToShapeDecls.get('http://hl7.org/fhir/item').map(d => d.id)).toEqual([ 'Questionnaire', 'Questionnaire.item' ]);
    });

    it('should barf on cycles', () => {
      expect(() => new QueryAnalyzer(FhirShEx).getArcTrees(SparqlQuery.parse(
        `PREFIX fhir: <http://hl7.org/fhir/>
ASK {?obs fhir:subject ?subjectRef . ?subjectRef fhir:reference ?obs}`
      ))).toThrow("can't handle cycle involving ?subjectRef <http://hl7.org/fhir/reference> ?obs .");
    });

    it('should handle internal graph connections', () => {
      expect(new QueryAnalyzer(FhirShEx).getArcTrees(SparqlQuery.parse(
        `PREFIX fhir: <http://hl7.org/fhir/>
ASK {?obs fhir:triggeredBy
  [ fhir:reference [ fhir:link ?trigger ] ; fhir:type [ fhir:v "reflex" ] ],
  [ fhir:reference [ fhir:link ?trigger ] ; fhir:type [ fhir:v "repeat" ] ],
  [ fhir:reference [ fhir:link ?trigger ] ; fhir:type [ fhir:v "re-run" ] ]
}`
      ))[0].arcTrees[0].out.length).toBe(3);
    });

    it('should handle external graph connections', () => {
      expect(new QueryAnalyzer(FhirShEx).getArcTrees(SparqlQuery.parse(
        `PREFIX fhir: <http://hl7.org/fhir/>
ASK {
  ?obs fhir:triggeredBy
    [ fhir:reference [ fhir:link ?trigger1 ] ; fhir:type [ fhir:v "reflex" ] ],
    [ fhir:reference [ fhir:link ?trigger2 ] ; fhir:type [ fhir:v "repeat" ] ],
    [ fhir:reference [ fhir:link ?trigger3 ] ; fhir:type [ fhir:v "re-run" ] ].
  ?trigger1 fhir:triggeredBy
    [ fhir:reference [ fhir:link ?trigger3 ] ; fhir:type [ fhir:v "reflex" ] ] .
  ?trigger2 fhir:triggeredBy
    [ fhir:reference [ fhir:link ?trigger3 ] ; fhir:type [ fhir:v "reflex" ] ] .
}`
      ))[0].arcTrees.length).toBe(3);
    });

    it('should handle nested BGPs', () => {
      const iQuery = SparqlQuery.parse(
          `
PREFIX fhir: <http://hl7.org/fhir/>
PREFIX sct: <http://snomed.info/id/>
PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>
PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>

SELECT ?obs ?patId WHERE {
  {
    # Observation
    ?obs a fhir:Observation .

    # Observation.code
    ?obs fhir:code ?code .
      ?code fhir:coding ?codeList .
        ?codeList rdf:rest*/rdf:first ?codeElt .
          ?codeElt fhir:code ?codeCode .
            ?codeCode fhir:v "72166-2" .
          ?codeElt fhir:system ?codingSystem .
            ?codingSystem fhir:v "http://loinc.org"^^xsd:anyURI .
  }

  {
  # Observation.subject
  ?obs fhir:subject ?subjectRef .
    ?subjectRef fhir:reference ?subjectBNode .
      ?subjectBNode fhir:link ?patRsrc .
  }

  OPTIONAL {
    {
      # Patient
      ?patRsrc a fhir:Patient .
    }

    {
      # Patient.id
      ?patRsrc fhir:id ?patIdElt .
        ?patIdElt fhir:v ?patId .
    } UNION {
      # Patient.id
      ?patRsrc fhir:language ?langElt .
        ?langElt fhir:v "ES" .
    }
  }
}
          `
      );
      let arcTrees = new QueryAnalyzer(FhirShEx).getArcTrees(iQuery);
      expect(arcTrees[0].bgp).toBe(iQuery.getWhere()[0]);
      expect(arcTrees[0].arcTrees[0].out.length).toBe(2);
    });
  });
});
