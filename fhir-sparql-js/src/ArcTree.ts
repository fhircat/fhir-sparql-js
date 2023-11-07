import {RdfUtils, Bgp, Triple, Term, POS, TTerm} from './RdfUtils';
import {Rdf} from './Namespaces';
import * as SparqlJs from "sparqljs";

export class PosArcTree {
  constructor(
      public pos: POS,
      public arcTree: ArcTree,

  ) {}
}
export class ArcTree {
  constructor (
      public tp: Triple,
      public out: ArcTree[]) {
    if (!out) throw Error(`${this.tp} has no out rule array`);
  }

  /** Construct an ArcTree for an arc and all arcs it reaches
   * Index variables in the same pass for efficiency.
   */
  static constructArcTree (triplePatterns: Triple[], forArc: Triple | null, node: TTerm, treeVars: Map<string, PosArcTree[]>, referents: Set<string>): ArcTree {
    // ArcTree's don't cross references (or canonical or ...?).
    if (forArc && (forArc.predicate as SparqlJs.IriTerm).value === 'http://hl7.org/fhir/reference') {
      const object = forArc.object;
      if (object.termType === 'Variable' && !referents.has(object.value))
        referents.add(object.value); // mark as referent
      return new ArcTree(forArc, []);
    }

    // Canonical order to match order in FhirQuery rule bodies
    // @ts-ignore
    const arcsOut = ArcTree.sortArcs(RdfUtils.stealMatching(triplePatterns, node, null, null));

    const out = arcsOut.map(triplePattern => {
      const arcTree = ArcTree.constructArcTree(triplePatterns, triplePattern as Triple, triplePattern.object as TTerm, treeVars, referents);

      // Index the variables that connect the trees.
      (['subject', 'object']).forEach(pos => {
        const v = triplePattern[pos as POS] as SparqlJs.Term;
        // Ignore connections formed by IRIs, BNodes or Literals.
        if (v.termType === 'Variable') {
          if (!treeVars.has(v.value)) {
            treeVars.set(v.value, []);
          };
          treeVars.get(v.value)!.push(new PosArcTree(pos as POS, arcTree));
        }
      });

      return arcTree;
    });

    return new ArcTree(forArc!, out);
  }

  getBgp () {
    const ret: SparqlJs.Triple[] = [];
    if (this.tp !== null)
      ret.push(this.tp);
    this.out.forEach(tree =>
      Array.prototype.push.apply(ret, tree.getBgp().triples)
    );
    return new Bgp(ret);
  }

  // not used
  toSparqlTriplePatterns (): Triple[] {
    return (this.tp ? [this.tp] : []).concat((this.out).flatMap(child => child.toSparqlTriplePatterns()));
  }

  toString (indent = ''): string {
    const tpStr = this.tp === null ? '<root>' : this.tp.toString();
    return this.out.length === 0
      ? indent + tpStr
      : indent + tpStr + ' [\n' + this.out.map(out => out.toString(indent + '  ')).join('\n') + '\n' + indent + ']';
  }

  /** sort a list of triple (patterns), AKA arcs
   * Bubble rdf:type to the top
   * Sort remaining by predicate name.
   *   Since all have same subject and there are no repeated properties in
   *   FHIR/RDF, we can assume that that localeCompare will never return 0
   */
  static sortArcs (triplePatterns: SparqlJs.Triple[]): SparqlJs.Triple[] {
    const ret: SparqlJs.Triple[] = [];
    // @ts-ignore
    Array.prototype.push.apply(ret, RdfUtils.stealMatching(triplePatterns, null, Term.blessSparqlJs(Rdf.type), null)); // TODO: accept blessed
    Array.prototype.push.apply(ret, triplePatterns.sort(
      (l, r) => RdfUtils.pStr(l.predicate).localeCompare(RdfUtils.pStr(r.predicate))
    ));
    return ret;
  }
}
