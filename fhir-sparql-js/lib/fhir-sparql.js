const {Visitor: ShExVisitor} = require('./ShExVisitor');
const {Ns, Rdf, Fhir, FirstRest} = require('./Namespaces');

class PredicateToShapeDecl extends ShExVisitor {
  constructor (ctor_args) {
    super(ctor_args);
    this.map = new Map();
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
      if (!this.map.has(expr.predicate))
        this.map.set(expr.predicate, []);
      this.map.get(expr.predicate).push(this.curDecl);
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
  if (x === null)
    return 'null';
  if ('subject' in x)
    return `${ToTurtle(x.subject)} ${ToTurtle(x.predicate)} ${ToTurtle(x.object)} .`
  if (x.type === 'path')
    return x.value
      ? '<' + x.value + '>'
      : '(' + x.items.map(item => FhirSparql.pStr(item) + (item.pathType || '')).join('/') + ')'

  switch (x.termType) {
  case 'NamedNode': return '<' + x.value + '>';
  case 'BlankNode': return '_:' + x.value;
  case 'Variable': return '?' + x.value;
  case 'Literal': return '"' + x.value + '"' +
      (x.language
       ? '@' + x.language
       : x.datatype
       ? ToTurtle(x.datatype)
       : '');
  // istanbul ignore next
  default: throw Error(`ToTurtle - unrecognized argument ${JSON.stringify(x)}`);
  }
}

class ConnectingVariables {
  static toString (cvs, a, b, c) {
    const lines = [];
    for (const [variable, trees] of cvs) {
      lines.push(variable);
      trees.forEach((tree, i) =>
        lines.push(` ${i}: ${tree.pos} of { ${tree.arcTree.toString()} }`)
      );
    }
    return lines.join('\n');
  }
}

const Rule_CodeWithSystem = {
  arcTree: {tp: {subject: null, predicate: { termType: Ns.fhir + 'code'}, object: null}, out: [
    {tp: {subject: null, predicate: { termType: Ns.fhir + 'coding'}, object: null}, out: [
      {tp: {subject: null, predicate: FirstRest, object: null}, out: [
        {tp: {subject: null, predicate: { termType: Ns.fhir + 'code'}, object: null}, out: [
          {tp: {subject: null, predicate: { termType: Ns.fhir + 'v'}, object: null}, out: []}
        ]},
        {tp: {subject: null, predicate: { termType: Ns.fhir + 'system'}, object: null}, out: [
          {tp: {subject: null, predicate: { termType: Ns.fhir + 'v'}, object: null}, out: []}
        ]},
      ]}
    ]}
  ]},
  fhirQuery: 'code',
  arg: (arcTree) => evalObject([0,0,0]) + '|' + evalObject([0,1,0])
};

const Rule_CodeWithOutSystem = {
  arcTree: {tp: {subject: null, predicate: { termType: Ns.fhir + 'code'}, object: null}, out: [
    {tp: {subject: null, predicate: FirstRest, object: null}, out: [
      {tp: {subject: null, predicate: { termType: Ns.fhir + 'code'}, object: null}, out: [
        {tp: {subject: null, predicate: { termType: Ns.fhir + 'v'}, object: null}, out: []}
      ]},
    ]}
  ]},
  fhirQuery: 'code',
  arg: (arcTree) => evalObject([0,0,0])
};

const Rule_Id = {
  arcTree: {tp: {subject: null, predicate: Ns.fhir + 'id', object: null}, out: [
    {tp: {subject: null, predicate: Fhir.v, object: null }, out: []}
  ]},
  fhirQuery: 'id',
  arg: (arcTree) => evalObject([0])
}

/* e.g.
  name looks for: family, given, prefix, suffix, text
  fhir:name ( [] [
     fhir:use [ fhir:v "official" ] ;
     fhir:family [ fhir:v "Chalmers" ] ;
     fhir:given ( [ fhir:v "Peter" ] [ fhir:v "James" ] )
  ] [] )
*/
const Rule_NameFamily = {
  arcTree: {tp: {subject: null, predicate: Ns.fhir + 'name', object: null}, out: [
    {tp: {subject: null, predicate: Ns.fhir + 'family', object: null}, out: [
      {tp: {subject: null, predicate: Fhir.v, object: null }, out: []}
    ]}
  ]},
  fhirQuery: 'name',
  arg: (arcTree) => evalObject([0])
}

const Rule_NameGiven = {
  arcTree: {tp: {subject: null, predicate: Ns.fhir + 'name', object: null}, out: [
    {tp: {subject: null, predicate: Ns.fhir + 'family', object: null}, out: [
      {tp: {subject: null, predicate: Fhir.v, object: null }, out: []}
    ]}
  ]},
  fhirQuery: 'given',
  arg: (arcTree) => evalObject([0])
}

const Rule_Given = {
  arcTree: {tp: {subject: null, predicate: Ns.fhir + 'name', object: null}, out: [
    {tp: {subject: null, predicate: Ns.fhir + 'family', object: null}, out: [
      {tp: {subject: null, predicate: Fhir.v, object: null }, out: []}
    ]}
  ]},
  fhirQuery: 'given',
  arg: (arcTree) => evalObject([0])
}

const ResourceToPaths = {
  "": [Rule_Id],
  "Observation": [[Rule_CodeWithSystem, Rule_CodeWithOutSystem]],
  "Patient": [Rule_NameFamily, Rule_NameGiven, Rule_Given],
}

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
    const connectingVariables = new Map();

    // All variables in starting operation (like a BGP, but with path expressions included)
    const usedVars = new Map();

    while (todo.length > 0) {
      // Pick a starting triple from remaining triples
      const start = todo[0];

      // Index from variable name to ArcTree
      const treeVars = new Map();

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
                throw Error(`can't handle cycle involving ${ToTurtle(p.subject)}`);
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
      for (const [k, treeNodes] of treeVars) {
        if (connectingVariables.has(k)) {
          // Already known ConnectingVariable
          Array.prototype.push.apply(treeNodes);
        } else if (usedVars.has(k)) {
          // Already used in previous ArcTree so it's now a ConnectingVariabel
          connectingVariables.set(k, usedVars.get(k).concat(treeNodes));
          usedVars.delete(k);
        } else {
          // First tree in which this variable appears
          usedVars.set(k, treeNodes);
        }
      }
    }

    return {arcTrees, connectingVariables};
  }

  opBgpToFhirPathExecutions (arcTree, connectingVariables, SparqlSolution) {
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
          if (!treeVars.has(v.value)) {
            treeVars.set(v.value, []);
          };
          treeVars.get(v.value).push({pos, arcTree});
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

module.exports = {FhirSparql, ConnectingVariables, PredicateToShapeDecl, ArcTree, ToTurtle};
