const {Visitor: ShExVisitor} = require('./ShExVisitor');

// Namespaces
const Ns = {
  fhir: 'http://hl7.org/fhir/',
  rdf: 'http://www.w3.org/1999/02/22-rdf-syntax-ns#',
};

// Re-used RDF nodes
const Rdf = {
  type: { termType: 'NamedNode', value: Ns.rdf + 'type' },
  first: { termType: 'NamedNode', value: Ns.rdf + 'first' },
  rest: { termType: 'NamedNode', value: Ns.rdf + 'rest' },
};

class PredicateToShapeDecl extends ShExVisitor {
  constructor (ctor_args) {
    super(ctor_args);
    this.map = {};
    this.curDecl = null;
  }

  visitSchema(schema, ...args) {
    if (!schema || !(typeof schema === 'object') || schema.type !== 'Schema')
      throw Error(`visitSchema argument must be a schema, got ${JSON.stringify(schema)}`);
    return super.visitSchema(schema, ...args);
  }

  visitShapeDecl(decl, ...args) {
    this.curDecl = decl;
    const ret = super.visitShapeDecl(decl, ...args);
    this.curDecl = null;
    return ret;
  }

  visitTripleConstraint(expr, ...args) {
    if (this.curDecl === null)
      throw new Error(`visiting ${JSON.stringify(expr)} while not in a ShapeDecl`);
    if (!expr.predicate.startsWith(Ns.rdf) && [Ns.fhir + 'v', Ns.fhir + 'nodeRole'].indexOf(expr.predicate) === -1) {
      if (!this.map[expr.predicate])
        this.map[expr.predicate] = [];
      this.map[expr.predicate].push(this.curDecl);
    }
    return null;
  }

  visitNodeConstraint(shape, ...args) { // don't bother visiting NodeConstraints
    return null;
  }
}

class ArcTree {
  constructor (tp, out) {
    this.tp = tp;
    this.out = out;
  }

  getBgp () {
    const ret = [];
    if (this.tp !== null)
      ret.push(this.tp);
    this.out.forEach(tree =>
      Array.prototype.push.apply(ret, tree.getBgp())
    );
    return ret;
  }

  toString () {
    return this.tp === null ? 'null' : ToTurtle(this.tp);
  }
}

// debugging printer
function ToTurtle (x) {
  if ('subject' in x)
    return `${ToTurtle(x.subject)} ${ToTurtle(x.predicate)} ${ToTurtle(x.object)}`
  switch (x.termType) {
  case 'NamedNode': return '<' + x.value + '>';
  case 'BlankNode': return '_:' + x.value;
  case 'Variable': return '?' + x.value;
  case 'Literal': return '"' + x.value + '"' +
      x.language
      ? '@' + x.language
      : x.datatype
      ? ToTurtle(x.datatype)
      : '';
  default: throw Exception(`ToTurtle - unrecognized argument ${JSON.stringify(x)}`);
  }
}

class ConnectingVariables {
  static toString (cvs, a, b, c) {
    const lines = [];
    for (const variable in cvs) {
      lines.push(variable);
      cvs[variable].forEach((tree, i) =>
        lines.push(` ${i} ${tree.pos} ${tree.arcTree.toString()}`)
      );
    }
    return lines.join('\n');
  }
}

const FirstRest = { type: 'path', pathType: '/', items: [
  { "type": "path",
    "pathType": "*",
    "items": [ { "termType": "NamedNode", "value": Rdf.first } ] },
  { "termType": "NamedNode",
    "value": Rdf.rest },
] };

const CodeWithSystem = {
  arcTree: {tp: {subject: null, predicate: { termType: Ns.fhir + 'code'}, object: null}, out: [
    {tp: {subject: null, predicate: FirstRest, object: null}, out: [
      {tp: {subject: null, predicate: { termType: Ns.fhir + 'code'}, object: null}, out: [
        {tp: {subject: null, predicate: { termType: Ns.fhir + 'v'}, object: null}, out: []}
      ]},
      {tp: {subject: null, predicate: { termType: Ns.fhir + 'system'}, object: null}, out: [
        {tp: {subject: null, predicate: { termType: Ns.fhir + 'v'}, object: null}, out: []}
      ]},
    ]}
  ]},
  fhirQuery: 'code',
  arg: (arcTree) => arcTree.out.out[0].out[0].out[0].out[0] + arcTree.out.out[0].out[1].out[0].out[0]
};

const CodeWithOutSystem = {
  arcTree: {tp: {subject: null, predicate: { termType: Ns.fhir + 'code'}, object: null}, out: [
    {tp: {subject: null, predicate: FirstRest, object: null}, out: [
      {tp: {subject: null, predicate: { termType: Ns.fhir + 'code'}, object: null}, out: [
        {tp: {subject: null, predicate: { termType: Ns.fhir + 'v'}, object: null}, out: []}
      ]},
    ]}
  ]},
  fhirQuery: 'code',
  arg: (arcTree) => arcTree.out.out[0].out[0].out[0].out[0]
};


class FhirSparql {
  constructor (shex) {
    this.shex = shex;
    const visitor = new PredicateToShapeDecl();
    visitor.visitSchema(shex);
    this.predicateToShapeDecl = visitor.map;
  }

  getArcTrees (query) {
    const triples = query.where[0].triples;

    const todo = triples.slice().sort((l, r) => FhirSparql.pStr(l.predicate).localeCompare(FhirSparql.pStr(r.predicate)));
    /*
      console.log(todo.map(t => t.subject.value + ' ' + FhirSparql.pStr(t.predicate) + ' ' + t.object.value).join("\n"));
       1 ?obs fhir:code ?codeList
       3 ?coding fhir:code ?codeCode
      10 ?subject fhir:id ?patIdElt
       8 ?subjectRef fhir:reference ?subject
       7 ?obs fhir:subject ?subjectRef
       5 ?coding fhir:system ?codingSystem
       4 ?codeCode fhir:v 1234567
       6 ?codingSystem fhir:v "http://snomed.info/id"
      11 ?patIdElt fhir:v ?patId
       2 ?codeList rdf:first* /rdf:rest ?coding
       0 ?obs a fhir:Observation
       9 ?subject a fhir:Patient
     */

    // All known ArcTrees
    const arcTrees = [];
    // Variables connecting ArcTrees
    const connectingVariables = {};

    // All variables in starting operation (like a BGP, but with path expressions included)
    const usedVars = {};

    while (todo.length > 0) {
      // Pick a starting triple from remaining triples
      const start = todo[0];

      // Index from variable name to ArcTree
      const treeVars = {};

      // Terminal ancesters of start.subject
      const roots = [];

      // Working list of triples in start's tree.
      let tz = [start];
      do {
        // tz for the next pass
        const newTz = [];

        tz.forEach(t => {
          // get the node's incoming arcs (actually, just one in every scenario I've imagined).
          const arcsIn = FhirSparql.getMatching(todo, null, null, t.subject);

          // it either has incoming arcs or it's a root.
          if (arcsIn.length === 0) {
            roots.push(t.subject);
          } else {
            // barf if there's a cycle
            arcsIn.forEach(p => {
              if (FhirSparql.equals(p.subject, start.subject))
                throw Error(``);
            });

            // find parents of these arcs on next iteration
            Array.prototype.push.apply(newTz, arcsIn);
          }
        });
        tz = newTz;
      } while (tz.length > 0);
      console.assert(roots.length > 0, 'should have a root (if there were any triples at all)');

      // build tree and index variables
      Array.prototype.push.apply(arcTrees, roots.map(root => FhirSparql.constructArcTree(todo, null, root, treeVars)));

      // Sort treeVars for this tree
      for (const k in treeVars) {
        const treeNodes = treeVars[k];
        if (k in connectingVariables) {
          // Already known ConnectingVariable
          Array.prototype.push.apply(treeNodes);
        } else if (k in usedVars) {
          // Already used in previous ArcTree so it's now a ConnectingVariabel
          connectingVariables[k] = usedVars[k].concat(treeNodes);
          delete usedVars[k];
        } else {
          // First tree in which this variable appears
          usedVars[k] = treeNodes;
        }
      }
    }

    return {arcTrees, connectingVariables};
  }

  opBgpToFhirPathExecutions ({arcTrees, connectingVariables}) {
    return 1;
  }

  /** find triples matching (s, p, o)
   */
  static getMatching (triplePatterns, s, p, o) {
    return triplePatterns.filter(tp =>
      (s === null || FhirSparql.equals(tp.subject, s)) &&
      (p === null || FhirSparql.equals(tp.predicate, p)) &&
      (o === null || FhirSparql.equals(tp.object, o))
    );
  }

  /** remove triples matching (s, p, o)
   */
  static stealMatching (triplePatterns, s, p, o) {
    const ret = [];
    for (let i = 0; i < triplePatterns.length; ++i) {
      const tp = triplePatterns[i];
      if ((s === null || FhirSparql.equals(tp.subject, s)) &&
          (p === null || FhirSparql.equals(tp.predicate, p)) &&
          (o === null || FhirSparql.equals(tp.object, o))) {
        ret.push(tp);
        triplePatterns.splice(i, 1);
        --i;
      }
    }
    return ret;
  }

  /** Rdf node === Rdf node
   * will be specialized for every graph API
   */
  static equals (l, r) {
    return l.termType === r.termType && l.value === r.value;
  }

  /** sort a list of triple (patterns), AKA arcs
   * Bubble rdf:type to the top
   * Sort remaining by predicate name.
   *   Since all have same subject and there are no repeated properties in
   *   FHIR/RDF, we can assume that that localeCompare will never return 0
   */
  static sortArcs (triplePatterns) {
    const ret = [];
    Array.prototype.push.apply(ret, FhirSparql.stealMatching(triplePatterns, null, Rdf.type, null));
    Array.prototype.push.apply(ret, triplePatterns.sort(
      (l, r) => FhirSparql.pStr(l.predicate).localeCompare(FhirSparql.pStr(r.predicate))
    ));
    return ret;
  }

  /** Construct an ArcTree for an arc and all arcs it reaches
   * Index variables in the same pass for efficiency.
   */
  static constructArcTree (triplePatterns, forArc, node, treeVars) {
    // ArcTree's don't cross references (or canonical or ...?).
    if (forArc && forArc.predicate.value === 'http://hl7.org/fhir/reference')
      return new ArcTree(forArc, []);

    // Canonical order to match order in FhirQuery rule bodies
    const arcsOut = FhirSparql.sortArcs(FhirSparql.stealMatching(triplePatterns, node, null, null));

    const out = arcsOut.map(triplePattern => {
      const arcTree = FhirSparql.constructArcTree(triplePatterns, triplePattern, triplePattern.object, treeVars);

      // Index the variables that connect the trees.
      (['subject', 'object']).forEach(pos => {
        const v = triplePattern[pos];
        // Ignore connections formed by IRIs, BNodes or Literals.
        if (v.termType === 'Variable') {
          if (!treeVars[v.value]) {
            treeVars[v.value] = [];
          };
          treeVars[v.value].push({pos, arcTree});
        }
      });

      return arcTree;
    });

    return new ArcTree(forArc, out);
  }

  /** Stringize a predicate
   * Used to sort arcs queried from graphs.
   */
  static pStr (predicate) {
    return predicate.value
      ? '<' + predicate.value + '>'
      : predicate.items.map(item => FhirSparql.pStr(item) + (item.pathType || '')).join('/')
  }
}

module.exports = {FhirSparql, ConnectingVariables, PredicateToShapeDecl}
