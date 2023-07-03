// const File = require('fs');
// const Path = require('path');
// const Tests = __dirname;
// const Resources = Path.join(__dirname, '../../fhir-sparql-common/src/test/resources/org/uu3/');

const {Triple} = require('../lib/RdfUtils');
const {ArcTree} = require('../lib/ArcTree');

// const ShExParser = require("@shexjs/parser").construct();
// const FhirShEx = ShExParser.parse(File.readFileSync(Path.join(Resources, 'ShEx-mini-terse.shex'), 'utf-8'));

describe('ArcTree', () => {
  describe('constructor', () => {
    it('should whine on no outs', () => {
      expect(() => new ArcTree(Triple.blessSparqlJs({
        subject: {termType: 'BlankNode', value: 'b1'},
        predicate: {termType: 'NamedNode', value: 'http://a.example/p1'},
        object: {termType: 'Variable', value: 'x'}
      }), null)).toThrow("_:b1 <http://a.example/p1> ?x . has no out rule array");
    });
  });

  it(`should construct and render ArcTrees`, () => {
    expect(new ArcTree(Triple.blessSparqlJs({
      subject: {termType: 'BlankNode', value: 'b1'},
      predicate: {termType: 'NamedNode', value: 'http://hl7.org/fhir/v'},
      object: {termType: 'BlankNode', value: 'b2'}
    }), []).toString()).toEqual('_:b1 <http://hl7.org/fhir/v> _:b2 .');
    expect(new ArcTree(Triple.blessSparqlJs({
      subject: {termType: 'NamedNode', value: 'http://a.example/#a'},
      predicate: {termType: 'NamedNode', value: 'http://hl7.org/fhir/v'},
      object: {termType: 'Literal', value: 'a'}
    }), []).toString()).toEqual('<http://a.example/#a> <http://hl7.org/fhir/v> "a" .');
  })
});
