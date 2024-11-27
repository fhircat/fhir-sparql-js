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

    it('should handle empty WHERE', () => {
      expect(new QueryAnalyzer(FhirShEx).getArcTrees(SparqlQuery.parse(
          `ASK {}`
      ))[0]).toBe(undefined);
    });

    it('should handle missing WHERE', () => {
      expect(new QueryAnalyzer(FhirShEx).getArcTrees(SparqlQuery.parse(
        { "queryType": "ASK",
          "type": "query",
          "prefixes": {}
        }
      ))[0]).toBe(undefined);
    });

    it('should handle object arguments', () => {
      expect(new QueryAnalyzer(FhirShEx).getArcTrees(SparqlQuery.parse(
        { "type": "query",
          "queryType": "ASK",
          "prefixes": { "fhir": "http://hl7.org/fhir/" },
          "where": [
            { "type": "bgp",
              "triples": [
                { "subject": { "termType": "Variable", "value": "obs" },
                  "predicate": { "termType": "NamedNode", "value": "http://hl7.org/fhir/triggeredBy" },
                  "object": { "termType": "BlankNode", "value": "g_2" } },
                { "subject": { "termType": "Variable", "value": "obs" },
                  "predicate": { "termType": "NamedNode", "value": "http://hl7.org/fhir/triggeredBy" },
                  "object": { "termType": "BlankNode", "value": "g_5" }
                },
                { "subject": { "termType": "Variable", "value": "obs" },
                  "predicate": { "termType": "NamedNode", "value": "http://hl7.org/fhir/triggeredBy" },
                  "object": { "termType": "BlankNode", "value": "g_8" }
                },
                { "subject": { "termType": "BlankNode", "value": "g_2" },
                  "predicate": { "termType": "NamedNode", "value": "http://hl7.org/fhir/reference" },
                  "object": { "termType": "BlankNode", "value": "g_0" }
                },
                { "subject": { "termType": "BlankNode", "value": "g_2" },
                  "predicate": { "termType": "NamedNode", "value": "http://hl7.org/fhir/type" },
                  "object": { "termType": "BlankNode", "value": "g_1" }
                },
                { "subject": { "termType": "BlankNode", "value": "g_1" },
                  "predicate": { "termType": "NamedNode", "value": "http://hl7.org/fhir/v" },
                  "object": { "termType": "Literal", "value": "reflex", "language": "",
                              "datatype": { "termType": "NamedNode", "value": "http://www.w3.org/2001/XMLSchema#string" } }
                },
                { "subject": { "termType": "BlankNode", "value": "g_0" },
                  "predicate": { "termType": "NamedNode", "value": "http://hl7.org/fhir/link" },
                  "object": { "termType": "Variable", "value": "trigger" }
                },
                { "subject": { "termType": "BlankNode", "value": "g_5" },
                  "predicate": { "termType": "NamedNode", "value": "http://hl7.org/fhir/reference" },
                  "object": { "termType": "BlankNode", "value": "g_3" }
                },
                { "subject": { "termType": "BlankNode", "value": "g_5" },
                  "predicate": { "termType": "NamedNode", "value": "http://hl7.org/fhir/type" },
                  "object": { "termType": "BlankNode", "value": "g_4" }
                },
                { "subject": { "termType": "BlankNode", "value": "g_4" },
                  "predicate": { "termType": "NamedNode", "value": "http://hl7.org/fhir/v" },
                  "object": { "termType": "Literal", "value": "repeat", "language": "",
                              "datatype": { "termType": "NamedNode", "value": "http://www.w3.org/2001/XMLSchema#string" } }
                },
                { "subject": { "termType": "BlankNode", "value": "g_3" },
                  "predicate": { "termType": "NamedNode", "value": "http://hl7.org/fhir/link" },
                  "object": { "termType": "Variable", "value": "trigger" }
                },
                { "subject": { "termType": "BlankNode", "value": "g_8" },
                  "predicate": { "termType": "NamedNode", "value": "http://hl7.org/fhir/reference" },
                  "object": { "termType": "BlankNode", "value": "g_6" }
                },
                { "subject": { "termType": "BlankNode", "value": "g_8" },
                  "predicate": { "termType": "NamedNode", "value": "http://hl7.org/fhir/type" },
                  "object": { "termType": "BlankNode", "value": "g_7" }
                },
                { "subject": { "termType": "BlankNode", "value": "g_7" },
                  "predicate": { "termType": "NamedNode", "value": "http://hl7.org/fhir/v" },
                  "object": { "termType": "Literal", "value": "re-run", "language": "",
                              "datatype": { "termType": "NamedNode", "value": "http://www.w3.org/2001/XMLSchema#string" } }
                },
                { "subject": { "termType": "BlankNode", "value": "g_6" },
                  "predicate": { "termType": "NamedNode", "value": "http://hl7.org/fhir/link" },
                  "object": { "termType": "Variable", "value": "trigger" }
                }
              ]
            }
          ]
        }
      ))[0].arcTrees[0].out.length).toBe(3);
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
      const sQuery = File.readFileSync(Path.join(Resources, 'trimmed-deep-use-case-query.srq'), 'utf-8');
      const iQuery = SparqlQuery.parse(sQuery);
      let arcTrees = new QueryAnalyzer(FhirShEx).getArcTrees(iQuery);
      expect(arcTrees[0].bgp).toBe(iQuery.getWhere()[0].patterns[0]);
      expect(arcTrees[0].arcTrees[0].out.length).toBe(2);
    });

    /* TODO:
       SELECT ?obs ?patient ?birthdate {
         { SELECT (?subject AS ?patient) ?name  {
           ?obs fhir:subject [ fhir:link ?subject ] }
         ?patient fhir:status [ fhir:v ?isActive ] ;
           fhir:birthDate [ fhir:v ?bdate ] .
         BIND (?bdate AS ?birthdate)
       }
     */
  });
});
