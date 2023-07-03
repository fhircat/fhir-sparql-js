const {Visitor: ShExVisitor} = require('./ShExVisitor');
const {Ns} = require('./Namespaces');
const {RdfUtils} = require('./RdfUtils');
const {ArcTree} = require('./ArcTree');

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

class QueryAnalyzer {
  constructor (shex) {
    this.shex = shex;
    if (shex) { // allow for shex-less invocation for rule compilation
      const visitor = new PredicateToShapeDecl();
      visitor.visitSchema(shex);
      this.predicateToShapeDecl = visitor.map;
    }
  }

  getArcTrees (query) {
    const triples = query.getWhere()[0].triples;

    const todo = triples.slice().sort((l, r) => RdfUtils.pStr(l.predicate).localeCompare(RdfUtils.pStr(r.predicate)));
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

    const referents = new Set();

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
          const arcsIn = RdfUtils.getMatching(todo, null, null, t.subject);

          // it either has incoming arcs or it's a root.
          if (arcsIn.length === 0) {
            roots.push(t.subject);
          } else {
            // barf if there's a cycle
            arcsIn.forEach(p => {
              if (p.subject.equals(start.subject))
                throw Error(`can't handle cycle involving ${RdfUtils.ToTurtle(p.subject)}`);
            });

            // find parents of these arcs on next iteration
            Array.prototype.push.apply(newTz, arcsIn);
          }
        });
        tz = newTz;
      } while (tz.length > 0);
      console.assert(roots.length > 0, 'should have a root (if there were any triples at all)');

      // build tree and index variables
      Array.prototype.push.apply(arcTrees, roots.map(root =>
        ArcTree.constructArcTree(todo, null, root, treeVars, referents)
      ));

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

    return {arcTrees, connectingVariables, referents};
  }
}

module.exports = {QueryAnalyzer, PredicateToShapeDecl}
