import {ShExVisitor} from './ShExVisitor';
import {Ns} from './Namespaces';
import {RdfUtils, SparqlQuery, Triple, TTerm} from './RdfUtils';
import {ArcTree, PosArcTree} from './ArcTree'
import * as ShExJ from 'shexj';

export class PredicateToShapeDecls extends ShExVisitor {
  predicateToShapeDecls: Map<string, ShExJ.ShapeDecl[]>;
  resourceTypeToShapeDeclIds: Map<string, string[]>;
  curDecl: ShExJ.ShapeDecl | null;

  constructor (...ctor_args: any[]) {
    super(...ctor_args);
    this.predicateToShapeDecls = new Map(); // not used
    this.resourceTypeToShapeDeclIds = new Map();
    this.curDecl = null;
  }

  visitSchema(schema: ShExJ.Schema, ...args: any[]) {
    if (!schema || !(typeof schema === 'object') || schema.type !== 'Schema')
      throw Error(`visitSchema argument must be a schema, got ${JSON.stringify(schema)}`);
    return super.visitSchema(schema, ...args);
  }

  visitShapeDecl(decl: ShExJ.ShapeDecl, ...args: any[]) {

    // capture hierarchy implied by shape labels text
    let resourceType = (decl.id.split(/\./))[0];
    let ids = this.resourceTypeToShapeDeclIds.get(resourceType);
    if (!ids) {
      ids = [];
      this.resourceTypeToShapeDeclIds.set(resourceType, ids);
    }
    ids.push(decl.id);

    // index nested tripleExprs
    this.curDecl = decl;
    const ret = super.visitShapeDecl(decl, ...args);
    this.curDecl = null;
    return ret;
  }

  visitTripleConstraint(expr: ShExJ.TripleConstraint, ...args: any[]) {
    if (this.curDecl === null)
      throw new Error(`visiting ${JSON.stringify(expr)} while not in a ShapeDecl`);
    if (!expr.predicate.startsWith(Ns.rdf) && [Ns.fhir + 'v', Ns.fhir + 'nodeRole'].indexOf(expr.predicate) === -1) {
      if (!this.predicateToShapeDecls.has(expr.predicate))
        this.predicateToShapeDecls.set(expr.predicate, []);
      this.predicateToShapeDecls.get(expr.predicate)!.push(this.curDecl);
    }
    return null;
  }

  visitNodeConstraint(nc: ShExJ.NodeConstraint, ...args: any[]) { // don't bother visiting NodeConstraints
    return null;
  }
}

export class QueryAnalyzer {
  predicateToShapeDecls: Map<string, ShExJ.ShapeDecl[]>;
  resourceTypeToShapeDeclIds: Map<string, string[]>;
  constructor (
      public shex: ShExJ.Schema
  ) {
    if (shex) { // allow for shex-less invocation for rule compilation
      const visitor = new PredicateToShapeDecls();
      visitor.visitSchema(shex);
      this.predicateToShapeDecls = visitor.predicateToShapeDecls; // not used
      this.resourceTypeToShapeDeclIds = visitor.resourceTypeToShapeDeclIds;
    } else {
      this.predicateToShapeDecls = new Map();
      this.resourceTypeToShapeDeclIds = new Map();
    }
  }

  getArcTrees (query: SparqlQuery) {
    const triples = query.getWhere()[0].triples as Triple[];

    const todo: Triple[] = triples.slice().sort((l, r) => RdfUtils.pStr(l.predicate).localeCompare(RdfUtils.pStr(r.predicate)));
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
    const arcTrees: ArcTree[] = [];
    // Variables connecting ArcTrees
    const connectingVariables: Map<string, PosArcTree[]> = new Map();

    // All variables in starting operation (like a BGP, but with path expressions included)
    const usedVars = new Map();

    const referents: Set<string> = new Set();

    while (todo.length > 0) {
      // Pick a starting triple from remaining triples
      const start = todo[0];

      // Index from variable name to ArcTree
      const treeVars: Map<string, PosArcTree[]> = new Map();

      // Terminal ancesters of start.subject
      const roots: TTerm[] = [];

      // Working list of triples in start's tree.
      let tz = [start];
      do {
        // tz for the next pass
        const newTz: Triple[] = [];

        tz.forEach(t => {
          // get the node's incoming arcs (actually, just one in every scenario I've imagined).
          // @ts-ignore
          const arcsIn = RdfUtils.getMatching(todo, null, null, t.subject);

          // it either has incoming arcs or it's a root.
          if (arcsIn.length === 0) {
            // @ts-ignore
            roots.push(t.subject);
          } else {
            // barf if there's a cycle
            arcsIn.forEach(p => {
              if (p.subject.equals(start.subject))
                throw Error(`can't handle cycle involving ${p}`);
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
