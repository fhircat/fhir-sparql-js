/* istanbul ignore file */

import {Xsd} from './Namespaces';
import * as SparqlJs from 'sparqljs';
import {BgpPattern, IriTerm, Pattern, Query, Wildcard} from 'sparqljs';
// import {Triple, IriTerm, BlankTerm, VariableTerm, QuadTerm} from
const SparqlParser = new SparqlJs.Parser();
const SparqlGenerator = new SparqlJs.Generator();

type S = SparqlJs.IriTerm | SparqlJs.BlankTerm | SparqlJs.VariableTerm;
type P = SparqlJs.IriTerm | SparqlJs.VariableTerm | SparqlJs.PropertyPath;
type O = S | SparqlJs.LiteralTerm;
export type TTerm = P | O;
type T = SparqlJs.Triple;

export type POS = 'subject' | 'predicate' | 'object';

export class RdfUtils {

  /** find triples matching (s, p, o)
   * could move to Bgp, but is always invokes on a List
   */
  static pmatch (l: P, r: P): boolean {
    return RdfUtils.isPath(l) && RdfUtils.isPath(r)
        ? RdfUtils.pathEquals(l, r)
        : RdfUtils.isPath(l) || RdfUtils.isPath(r)
        ? false
        : l.equals(r);
  }

  static pathEquals (l: SparqlJs.PropertyPath, r: SparqlJs.PropertyPath): boolean {
    return l.type === r.type && l.pathType === r.pathType && !l.items.find(
        (il, iNo) => !RdfUtils.pmatch(il, r.items[iNo])
    );
  }

  static isPath (t: TTerm): t is SparqlJs.PropertyPath {
    return (t as SparqlJs.PropertyPath).type === "path";
  }

  static getMatching (triplePatterns: T[], s: S | null, p: P | null, o: O | null): SparqlJs.Triple[] {
    return triplePatterns.filter(tp =>
      (s === null || tp.subject.equals(s)) &&
      (p === null || RdfUtils.pmatch(tp.predicate, p)) &&
      (o === null || tp.object.equals(o))
    );
  }

  /** remove triples matching (s, p, o)
   */
  static stealMatching (triplePatterns: T[], s: S | null, p: P | null, o: O | null): SparqlJs.Triple[] {
    const ret = [];
    for (let i = 0; i < triplePatterns.length; ++i) {
      const tp = triplePatterns[i];
      if ((s === null || tp.subject.equals(s)) &&
          (p === null || RdfUtils.pmatch(tp.predicate, p)) &&
          (o === null || tp.object.equals(o))) {
        ret.push(tp);
        triplePatterns.splice(i, 1);
        --i;
      }
    }
    return ret;
  }

  /** Stringize a predicate
   * Used to sort arcs queried from graphs.
   */
  static pStr (predicate: P): string {
    return !RdfUtils.isPath(predicate)
      ? '<' + predicate.value + '>'
      : '(' + predicate.items.map(item => RdfUtils.pStr(item) + (RdfUtils.isPath(item) ? item.pathType : '')).join('/') + ')'; // TODO: not correct
  }
}

export class Term {
  constructor (
      public termType: string,
      public value: string
  ) {
  }
  equals (r: Term) {
    return this.termType === r.termType && this.value === r.value;
  }
  static blessSparqlJs (sparqlJsTerm: TTerm): Term | Path {
    if (RdfUtils.isPath(sparqlJsTerm))
      { // @ts-ignore
        return new Path(sparqlJsTerm.pathType, sparqlJsTerm.items.map(item => Term.blessSparqlJs(item)));
      }

    switch (sparqlJsTerm.termType) {
    case 'NamedNode': return new NamedNode(sparqlJsTerm.value);
    case 'BlankNode': return new BlankNode(sparqlJsTerm.value);
    case 'Literal':
      const datatype = sparqlJsTerm.datatype ? Term.blessSparqlJs(sparqlJsTerm.datatype) as NamedNode : new NamedNode(Xsd.string.value);
      return new Literal(sparqlJsTerm.value, sparqlJsTerm.language, datatype);
    case 'Variable': return new Variable(sparqlJsTerm.value);
    default: throw Error(`unknown SparqlJs term type in ${JSON.stringify(sparqlJsTerm)}`);
    }
  }
}

class NamedNode extends Term { constructor (value: string) { super('NamedNode', value); } toString() { return '<' + this.value + '>'; } }
class BlankNode extends Term { constructor (value: string) { super('BlankNode', value); } toString() { return '_:' + this.value; } }
class Variable  extends Term { constructor (value: string) { super('Variable', value); } toString() { return '?' + this.value; } }
class Literal extends Term {
  protected language: string;
  protected datatype: NamedNode | null;
  constructor (value: string, language: string, datatype: NamedNode | null) {
    super('Literal', value);
    this.language = language;
    this.datatype = datatype;
  }
  toString () {
    return '"' + this.value + '"' +
      (this.language
       ? '@' + this.language
       : this.datatype && this.datatype.value !== Xsd.string.value
       ? '^^' + this.datatype.toString()
       : '');
  }
  equals (r: Term) {
    return super.equals(r) && this.language === (r as Literal).language && this.datatype === (r as Literal).datatype;
  }
}

export class Path implements SparqlJs.PropertyPath {
  public type: 'path' = 'path';
  constructor (
      public pathType: '|' | '/' | '^' | '+' | '*' | '!',
      public items: Array<IriTerm | Path>,
      // pathType, items
  ) {
  }
  equals (r: Path) {
    if (this.type !== r.type) return false;
    if (this.pathType !== r.pathType) return false;
    if (this.items.length !== r.items.length) return false;
    for (let i = 0; i < this.items.length; ++i)
      { // @ts-ignore
        if (!this.items[i].equals(r.items[i]))
                return false;
      }
    return true;
  }
}

export class Triple implements SparqlJs.Triple {
  constructor (
    public subject: S,
    public predicate: P,
    public object: O,
  ) {}
  toString () {
    return `${this.subject} ${this.predicate} ${this.object} .`;
  }
  equals (r: Triple) {
    return this.subject.equals(r.subject) &&
      RdfUtils.pmatch(this.predicate, r.predicate) &&
      this.object.equals(r.object);
  }
  static blessSparqlJs (triple: SparqlJs.Triple) {
    return new Triple(
      Term.blessSparqlJs(triple.subject as TTerm) as S,
      Term.blessSparqlJs(triple.predicate as TTerm) as P,
      Term.blessSparqlJs(triple.object as TTerm) as O,
    );
  }
}

export class Bgp implements SparqlJs.BgpPattern {
  type: 'bgp' = 'bgp';
  constructor (
      public triples: SparqlJs.Triple[]) {
  }
  toString (indent = '') {
    return indent + '{\n' + this.triples.map(t => indent + '  ' + t.toString() + '\n').join('') + '}';
  }
  static blessSparqlJs (sparqlJsBgp: SparqlJs.BgpPattern) {
    if (sparqlJsBgp.type !== 'bgp') throw Error(`expected to bless something with .type=bgp in ${JSON.stringify(sparqlJsBgp)}`);
    return new Bgp(sparqlJsBgp.triples.map(t => Triple.blessSparqlJs(t)));
  }
}

export class SparqlQuery implements SparqlJs.SelectQuery {
  type: 'query' = 'query';
  // base: string | undefined;
  prefixes: { [prefix: string]: string; } = {};
  queryType: 'SELECT' = 'SELECT';
  variables: [SparqlJs.Wildcard] = [new SparqlJs.Wildcard()];
  where: SparqlJs.BgpPattern[];
  constructor (query: SparqlJs.Query) {
    // query.where[0].triples = query.where[0].triples.map(t => Triple.blessSparqlJs(t));
    this.prefixes = query.prefixes;
    //@ts-ignore
    this.variables = query.variables;
    /* This isn't *really* the BGPs; it's flattened (without checking for
       reassignment in the projection or in BINDs, e.g.
       SELECT ?obs ?patient ?birthdate {
         { SELECT (?subject AS ?patient) ?name  {
           ?obs fhir:subject [ fhir:link ?subject ] }
         ?patient fhir:status [ fhir:v ?isActive ] ;
           fhir:birthDate [ fhir:v ?bdate ] .
         BIND (?bdate AS ?birthdate)
       }
       This will require restructuring to remain compatible with Jena.
     */
    this.where = this.findBgps(query).map(bgp => Bgp.blessSparqlJs(bgp as BgpPattern));
  }

  findBgps (q: SparqlJs.Query): Array<SparqlJs.BgpPattern> {
    if (q.type !== 'query')
      throw Error(`Expected type: "query"; got ${JSON.stringify(q)}`);
    return q.where!.reduce<SparqlJs.BgpPattern[]>((acc, elt) => {
      if (elt.type === 'group')
        return acc.concat(this.findBgps(elt.patterns[0] as SparqlJs.Query));
      if (elt.type === 'bgp')
        return acc.concat([elt]);
      console.log(`skipping ${elt.type}`);
      return acc;
    }, []);
  }

  getQuery () { return this; }
  getWhere () { return this.where; };

  static parse (text: string) {
    return new SparqlQuery(SparqlParser.parse(text) as Query);
  }

  static selectStar (triples: Triple[]) {
    const where: Pattern[] = [{type: "bgp", triples}];
    return SparqlGenerator.stringify({
      "type": 'query',
      "prefixes": {},
      "queryType": 'SELECT',
      "variables": [ new Wildcard() ],
      where,
    }) as string;
  }
}

// export type SparqlSolution = Map<string, TTerm>;
export interface SparqlSolution {
  [variable: string]: TTerm;
}

export interface Meta {
  base: string;
  prefixes: {[prefix: string]: string};
}
