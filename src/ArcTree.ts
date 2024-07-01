import {RdfUtils, Bgp, Triple, Term, POS, TTerm} from './RdfUtils';
import {Rdf} from './Namespaces';
import * as SparqlJs from "sparqljs";

/**
 * Pairing of ArcTree with a location in a SPARQL triple pattern.
 */
export class PosArcTree {
  /**
   * @param pos 'subject', 'predicate' or 'object'
   * @param arcTree nested ArcTreed
   */
  constructor(
      public pos: POS,
      public arcTree: ArcTree,
  ) {}
}

/**
 * mapping from variable name to ocurances in PosArcTrees
 */
export class ConnectingVariables {
  static toString (cvs: Map<string, PosArcTree[]>) {
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

/**
 * Tree representation of a hierarchy captured in a SPARQL query, e.g.
 *   ?p :p1 ?b .
 *   ?b :p2 c.
 */
export class ArcTree {
  /**
   * @param tp SPARQL triple pattern
   * @param out nested ArcTrees
   */
  constructor (
      public tp: Triple,
      public out: ArcTree[]) {
    if (!out) throw Error(`${this.tp} has no out rule array`);
  }

  /**
   * Construct an ArcTree for an arc and all arcs it reaches
   * Index variables in the same pass for efficiency.
   *
   * @param triplePatterns list of SPARQL triple patterns
   * @param forArc starting triple pattern
   * @param node subject node - TODO: redundant against `forArc`?
   * @param treeVars mapping of variable names to lists of ArcTrees that include that variable
   * @param referents objects of fhir:links
   */
  static constructArcTree (triplePatterns: Array<Triple>, forArc: Triple | null, node: TTerm, treeVars: Map<string, Array<PosArcTree>>, referents: Set<string>): ArcTree {
    // Canonical order to match order in FhirQuery rule bodies
    const arcsOut = ArcTree.sortArcs(RdfUtils.stealMatching(triplePatterns, node as SparqlJs.IriTerm | SparqlJs.BlankTerm | SparqlJs.VariableTerm, null, null));

    const out = arcsOut.map(triplePattern => {
      const arcTree = forArc
        // Skip this check because fhir:link's semantics matter more than its appearance in reference or canonical:
        // && ['reference', 'canonical'].find(p => (forArc!.predicate as SparqlJs.IriTerm).value === 'http://hl7.org/fhir/' + p)
        && (triplePattern.predicate as SparqlJs.IriTerm).value === 'http://hl7.org/fhir/link'
        ? new ArcTree(Triple.blessSparqlJs(triplePattern), []) // don't recurse on fhir:links
        : ArcTree.constructArcTree(triplePatterns, triplePattern as Triple, triplePattern.object as TTerm, treeVars, referents);

      // Index the variables that connect the trees.
      (['subject', 'object']).forEach(pos => {
        const v = triplePattern[pos as POS] as SparqlJs.Term;
        // Ignore connections formed by IRIs, BNodes or Literals.
        if (v.termType === 'Variable') {
          if (!treeVars.has(v.value)) {
            treeVars.set(v.value, []);
          }
          treeVars.get(v.value)!.push(new PosArcTree(pos as POS, arcTree));
        }
      });

      // If it's a variable and the object of a fhir:link, it's a referent.
      if ((triplePattern.predicate as SparqlJs.IriTerm).value === 'http://hl7.org/fhir/link'
          && triplePattern.object.termType === 'Variable'
          && !referents.has(triplePattern.object.value)) {
        referents.add(triplePattern.object.value);
      }

      return arcTree;
    });

    return new ArcTree(forArc!, out);
  }

  /**
   * construct a SPARQL BGP for this ArcTree
   */
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

  /**
   * serialize this ArcTRee
   * @param indent (indentation) prefix for each line
   */
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
   *   FHIR/RDF, we can assume that localeCompare will never return 0
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
