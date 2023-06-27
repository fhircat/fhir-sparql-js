const {Visitor: ShExVisitor} = require('./ShExVisitor');

const Ns = {
  fhir: 'http://hl7.org/fhir/',
  rdf: 'http://www.w3.org/1999/02/22-rdf-syntax-ns#',
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
}

class FhirSparql {
  constructor (shex) {
    this.shex = shex;
    const visitor = new PredicateToShapeDecl();
    visitor.visitSchema(shex);
    this.predicateToShapeDecl = visitor.map;
  }

  opBgpToFhirPathExecutions (query) {
    const hardCoded = [];
    const triples = query.where[0].triples;
    // const obs = new FhirPathExecution();
    // obs.triplePatterns = triples.slice(0, 9);
    // hardCoded.push(obs);
    // const pat = new FhirPathExecution();
    // pat.triplePatterns = triples.slice(9);
    // hardCoded.push(pat);

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
    const usedVars = {};
    const connectingVariables = {};
    const arcTrees = [];

    while (todo.length > 0) {
      const start = todo[0];
      const treeVars = {};

      const roots = [];
      let tz = [start];
      do {
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
      console.assert(roots.length > 0, 'should have a root (if there were any triples at all)')

      // build tree and index variables
      Array.prototype.push.apply(arcTrees, roots.map(root => FhirSparql.constructArcTree(todo, null, root, treeVars)));

      for (const k in treeVars) {
        const treeNodes = treeVars[k];
        if (k in connectingVariables) {
          Array.prototype.push.apply(treeNodes);
        } else if (k in usedVars) {
          connectingVariables[k] = usedVars[k].concat(treeNodes);
          delete usedVars[k];
        } else {
          usedVars[k] = treeNodes;
        }
      }
    }
debugger
    return {arcTrees, connectingVariables};
  }

  static getMatching (triplePatterns, s, p, o) {
    return triplePatterns.filter(tp =>
      (s === null || FhirSparql.equals(tp.subject, s)) && 
      (p === null || FhirSparql.equals(tp.predicate, p)) && 
      (o === null || FhirSparql.equals(tp.object, o))
    );
  }

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

  static getChildren (triplePatterns, from) {
    const o = from.subject;
    return triplePatterns.filter(tp => FhirSparql.equals(tp.subject, o));
  }

  static equals (l, r) {
    return l.termType === r.termType && l.value === r.value;
  }

  static sortArcs (triplePatterns) {
    const ret = [];
    const Rdf = {
      type: { termType: 'NamedNode', value: 'http://www.w3.org/1999/02/22-rdf-syntax-ns#type' },
    };
    Array.prototype.push.apply(ret, FhirSparql.stealMatching(triplePatterns, null, Rdf.type, null));
    Array.prototype.push.apply(ret, triplePatterns.sort(
      (l, r) => FhirSparql.pStr(l.predicate).localeCompare(FhirSparql.pStr(r.predicate))
    ));
    return ret;
  }

  static constructArcTree (triplePatterns, forArc, node, treeVars) {
    // ArcTree's don't cross references (or canonical or ...?).
    if (forArc && forArc.predicate.value === 'http://hl7.org/fhir/reference')
      return new ArcTree(forArc, []);

    // Canonical order to match order in FhirQuery rule bodies
    const arcsOut = FhirSparql.sortArcs(FhirSparql.stealMatching(triplePatterns, node, null, null));

    const out = arcsOut.map(triplePattern => {
      return FhirSparql.constructArcTree(triplePatterns, triplePattern, triplePattern.object, treeVars);
    });
    const ret = new ArcTree(forArc, out);

    // Index the variables that connect the trees.
    // Ignore connections formed by IRIs, BNodes or Literals.
    arcsOut.forEach(triplePattern => {
      (['subject', 'object']).forEach(pos => {
        const v = triplePattern[pos];
        if (v.termType === 'Variable') {
          if (!treeVars[v.value]) {
            treeVars[v.value] = [];
          }
          treeVars[v.value].push(ret);
        }
      });
    });

    return ret;
  }

  static pStr (predicate) {
    return predicate.value
      ? '<' + predicate.value + '>'
      : predicate.items.map(item => FhirSparql.pStr(item) + (item.pathType || '')).join('/')
  }
}

module.exports = {FhirSparql, PredicateToShapeDecl}
