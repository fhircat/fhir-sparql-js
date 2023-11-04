const File = require('fs');
const Path = require('path');
const Tests = __dirname;
const Resources = Path.join(__dirname, '../../fhir-sparql-common/src/test/resources/org/uu3/');

const {Triple} = require('../dist/RdfUtils');
const {ArcTree} = require('../dist/ArcTree.js');
const {ArcTreeFitsInShapeExpr} = require('../dist/ArcTreeFitsInShapeExpr');
const {FirstRest} = require('../dist/Namespaces');

const Ns = {
  's': 'http://a.example/schema#',
  'v': 'http://a.example/vocabulary#',
};

const ShExParser = require("@shexjs/parser").construct();

describe('ArcTreeFitsInShapeExpr-test', () => {
  describe('ArcTree', () => {
    it('should construct and toString', () => {
      expect(ArcTrees.b1_p1_v1_b1_p1_v3.toString()).toEqual(`<root> [
  _:b1 <${Ns.v}p1> "v1" .
  _:b1 <${Ns.v}p2> "v3" .
]`);
      expect(ArcTrees.b1_p1_v1_b1_p1_v3.toSparqlTriplePatterns()).toEqual([Triples.b1_p1_v1, Triples.b1_p2_v3]);
    });
  });

  describe('ArcTreeFitsInShapeExpr', () => {
    it('should accept valid value set values', () => {
      expect(!ArcTrees.b1_p1_v1_b1_p1_v3.out.find(child => !new ArcTreeFitsInShapeExpr(Schemas.S1).visitShapeRef(`${Ns.s}S1`, child))).toBe(true);
    });
    it('should throw on no shapes', () => {
      expect(() => {
        ArcTrees.b1_p1_v1_b1_p1_v3.out.find(child => !new ArcTreeFitsInShapeExpr(Schemas.S0));
      }).toThrow("construct ArcTreeFitsInShapeExpr with a ShEx schema with shapes");
    });
    it('should throw on bad reference', () => {
      expect(() => {
        ArcTrees.b1_p1_v1_b1_p1_v3.out.find(child => !new ArcTreeFitsInShapeExpr(Schemas.S1).visitShapeRef(`${Ns.s}S999`, child));
      }).toThrow("Shape http://a.example/schema#S999 not found in http://a.example/schema#S1");
    });
    it('should throw on unhandled path expression', () => {
      expect(() => {
        ArcTrees.b1_p1_p2_star_v1_b1_p1_v3.out.find(child => !new ArcTreeFitsInShapeExpr(Schemas.S1).visitShapeRef(`${Ns.s}S1`, child));
      }).toThrow(/need support for/);
    });
  });
});


const p1_p2_star = { type: "path", pathType: "/", items: [
  { type: "path",
    pathType: "*",
    items: [
      { type: "path", pathType: "/", items: [
        { termType: "NamedNode", value: `${Ns.v}p1` },
        { termType: "NamedNode", value: `${Ns.v}p2` }
      ]}
    ]},
  // { type: "path",
  //   pathType: "*",
  //   items: [
  //     { type: "path", pathType: "/", items: [
  //       { termType: "NamedNode", value: `${Ns.v}p1` },
  //       { termType: "NamedNode", value: `${Ns.v}p2` }
  //     ]}
  //   ]},
]};

const Triples = {
  'b1_p1_v1': Triple.blessSparqlJs({
    subject: {termType: 'BlankNode', value: 'b1'},
    predicate: {termType: 'NamedNode', value: `${Ns.v}p1`},
    object: {termType: 'Literal', value: 'v1'}
  }),
  'b1_p2_v3': Triple.blessSparqlJs({
    subject: {termType: 'BlankNode', value: 'b1'},
    predicate: {termType: 'NamedNode', value: `${Ns.v}p2`},
    object: {termType: 'Literal', value: 'v3'}
  }),
  'b1_p1_p2_star_v1': Triple.blessSparqlJs({
    subject: {termType: 'BlankNode', value: 'b1'},
    predicate: p1_p2_star,
    object: {termType: 'Literal', value: 'v1'}
  }),
};

const Schemas = {
  'S0': { type: "Schema" },
  'S1': ShExParser.parse(`
PREFIX : <${Ns.s}>
PREFIX v: <${Ns.v}>

:S1 {
  v:p1 ['v1' 'v2'] ;
  v:p2 .
}
`)
};

const ArcTrees = {
  'b1_p1_v1_b1_p1_v3': new ArcTree(null, [
    new ArcTree(Triples.b1_p1_v1, []),
    new ArcTree(Triples.b1_p2_v3, []),
  ]),
  'b1_p1_p2_star_v1_b1_p1_v3': new ArcTree(null, [
    new ArcTree(Triples.b1_p1_p2_star_v1, []),
    new ArcTree(Triples.b1_p2_v3, []),
  ]),
};
