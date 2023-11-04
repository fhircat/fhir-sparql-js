const File = require('fs');
const Path = require('path');
const Tests = __dirname;
const Resources = Path.join(__dirname, '../../fhir-sparql-common/src/test/resources/org/uu3/');

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
  [ fhir:reference ?trigger ; fhir:type [ fhir:v "reflex" ] ],
  [ fhir:reference ?trigger ; fhir:type [ fhir:v "repeat" ] ],
  [ fhir:reference ?trigger ; fhir:type [ fhir:v "re-run" ] ]
}`
      )).arcTrees[0].out.length).toBe(3);
    });

    it('should handle external graph connections', () => {
      expect(new QueryAnalyzer(FhirShEx).getArcTrees(SparqlQuery.parse(
        `PREFIX fhir: <http://hl7.org/fhir/>
ASK {
  ?obs fhir:triggeredBy
    [ fhir:reference ?trigger1 ; fhir:type [ fhir:v "reflex" ] ],
    [ fhir:reference ?trigger2 ; fhir:type [ fhir:v "repeat" ] ],
    [ fhir:reference ?trigger3 ; fhir:type [ fhir:v "re-run" ] ].
  ?trigger1 fhir:triggeredBy
    [ fhir:reference ?trigger3 ; fhir:type [ fhir:v "reflex" ] ] .
  ?trigger2 fhir:triggeredBy
    [ fhir:reference ?trigger3 ; fhir:type [ fhir:v "reflex" ] ] .
}`
      )).arcTrees.length).toBe(3);
    });
  });
});
