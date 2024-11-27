/* istanbul ignore file */

import {Xsd} from './Namespaces';
import * as SparqlJs from 'sparqljs';
import {BgpPattern, IriTerm, Pattern, Query, Wildcard} from 'sparqljs';
// import {Triple, IriTerm, BlankTerm, VariableTerm, QuadTerm} from
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
      { // @ts-ignore - '(Term | Path)[]' is not assignable to parameter of type '(IriTerm | Path)[]'
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
  toString (): string {
    return this.items[0].toString() + this.pathType + (
      this.items.length === 1
        ? ''
        : this.items[1].toString()
    );
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

export class SparqlPattern /* implements SparqlJs.Pattern */ {
  constructor (
    public type: string
  ) {
  }

  static bless (bgps: SparqlJs.Pattern[]): Array<SparqlPattern> {
    return bgps.reduce<SparqlPattern[]>((acc, elt) => {
      switch (elt.type) {
          // | BgpPattern
        case 'bgp':
          return acc.concat([Bgp.blessSparqlJs(elt)]);
          // | BlockPattern
          //   | OptionalPattern
        case 'optional':
          return acc.concat([Optional.blessSparqlJs(elt)]);
          //   | UnionPattern
        case 'union':
          return acc.concat([Union.blessSparqlJs(elt)]);
          //   | GroupPattern
        case 'group':
          return acc.concat([Group.blessSparqlJs(elt)]);
          //   | GraphPattern
        case 'graph':
          return acc.concat([Graph.blessSparqlJs(elt)]);
          //   | MinusPattern
        case 'minus':
          return acc.concat([Minus.blessSparqlJs(elt)]);
          //   | ServicePattern;
        case 'service':
          return acc.concat([Service.blessSparqlJs(elt)]);
          // | SelectQuery
        case 'query':
          return acc.concat(SparqlPattern.bless(elt.where || []));
          // | FilterPattern
        case 'filter':
          // @ts-ignore !!
          return acc.concat(elt);
          // | BindPattern
        case 'bind':
          // @ts-ignore !!
          return acc.concat(elt);
          // | ValuesPattern
        case 'values':
          // @ts-ignore !!
          return acc.concat(elt);
        default:
          // @ts-ignore -- js code in case elt doesn't match TS type
          throw Error(`unknown SparqlJs term type ${elt!.type || 'NULL'} in ${JSON.stringify(elt)}`);
      }
    }, [] as SparqlPattern[]);
  }

  static findBgps (bgps: Array<SparqlPattern>): Array<Bgp> {
    return bgps.reduce<Bgp[]>((acc, elt) => {
      switch (elt.type) {
          // | BgpPattern
        case 'bgp':
          return acc.concat([<Bgp>(elt)]);
          // | BlockPattern
          //   | OptionalPattern
        case 'optional':
          return acc.concat(SparqlPattern.findBgps((elt as Optional).patterns));
          //   | UnionPattern
        case 'union':
          return acc.concat(SparqlPattern.findBgps((elt as Union).patterns));
          //   | GroupPattern
        case 'group':
          return acc.concat(SparqlPattern.findBgps((elt as Group).patterns));
          //   | GraphPattern
        case 'graph':
          return acc.concat(SparqlPattern.findBgps((elt as Graph).patterns));
          //   | MinusPattern
        case 'minus':
          return acc.concat(SparqlPattern.findBgps((elt as Minus).patterns));
          //   | ServicePattern;
        case 'service':
          return acc.concat(SparqlPattern.findBgps((elt as Service).patterns));
          // | FilterPattern
        case 'filter':
          return acc; // !! TODO: look for that alternative to MINUS
          // | BindPattern
        case 'bind':
          return acc;
          // | ValuesPattern
        case 'values':
          return acc;
          // | SelectQuery
        case 'query':
          return acc.concat(SparqlPattern.findBgps((elt as SparqlQuery).where || []));
        default:
          // @ts-ignore -- js code in case elt doesn't match TS type
          throw Error(`unknown SparqlJs term type ${elt!.type || 'NULL'} in ${JSON.stringify(elt)}`);
      }
    }, [] as Bgp[]);
  }
}

export class Bgp extends SparqlPattern /* implements SparqlJs.BgpPattern */ {
  constructor (
    public triples: Triple[]
  ) {
    super('bgp');
  }
  toString (indent = '') {
    return indent + '{\n' + this.triples.map(t => indent + '  ' + t.toString() + '\n').join('') + '}';
  }
  static blessSparqlJs (sparqlJsBgp: SparqlJs.BgpPattern) {
    if (sparqlJsBgp.type !== 'bgp') throw Error(`expected to bless something with .type=bgp in ${JSON.stringify(sparqlJsBgp)}`);
    return new Bgp(sparqlJsBgp.triples.map(t => Triple.blessSparqlJs(t)));
  }
}

class PatternPattern extends SparqlPattern /* implements SparqlJs.Pattern */ {
  constructor (
    type: string,
    public patterns: SparqlPattern[]
  ) {
    super(type)
  }
}

export class Optional extends PatternPattern /* implements SparqlJs.OptionalPattern */ {
  type: 'optional' = 'optional';
  constructor (
    patterns: SparqlPattern[]
  ) {
    super('optional', patterns);
  }
  toString (indent = '') {
    return indent + 'OPTIONAL {\n' + this.patterns.map(t => indent + '  ' + t.toString() + '\n').join('') + '}';
  }
  static blessSparqlJs (sparqlJsOptional: SparqlJs.OptionalPattern) {
    if (sparqlJsOptional.type !== 'optional') throw Error(`expected to bless something with .type=optional in ${JSON.stringify(sparqlJsOptional)}`);
    return new Optional(SparqlPattern.bless(sparqlJsOptional.patterns));
  }
}

export class Group extends PatternPattern /* implements SparqlJs.OptionalPattern */ {
  type: 'group' = 'group';
  constructor (
    patterns: SparqlPattern[]
  ) {
    super('group', patterns);
  }
  toString (indent = '') {
    return indent + ' {\n' + this.patterns.map(t => indent + '  ' + t.toString() + '\n').join('') + '}';
  }
  static blessSparqlJs (sparqlJsGroup: SparqlJs.GroupPattern) {
    if (sparqlJsGroup.type !== 'group') throw Error(`expected to bless something with .type=group in ${JSON.stringify(sparqlJsGroup)}`);
    return new Group(SparqlPattern.bless(sparqlJsGroup.patterns));
  }
}

export class Union extends PatternPattern /* implements SparqlJs.UnionPattern */ {
  type: 'union' = 'union';
  constructor (
    patterns: SparqlPattern[]
  ) {
    super('union', patterns);
  }
  toString (indent = '') {
    return indent + 'UNION {\n' + this.patterns.map(t => indent + '  ' + t.toString() + '\n').join('') + '}';
  }
  static blessSparqlJs (sparqlJsUnion: SparqlJs.UnionPattern) {
    if (sparqlJsUnion.type !== 'union') throw Error(`expected to bless something with .type=union in ${JSON.stringify(sparqlJsUnion)}`);
    return new Union(SparqlPattern.bless(sparqlJsUnion.patterns));
  }
}

export class Graph extends PatternPattern /* implements SparqlJs.GraphPattern */ {
  type: 'graph' = 'graph';
  constructor (
    patterns: SparqlPattern[]
  ) {
    super('graph', patterns);
  }
  toString (indent = '') {
    return indent + 'GRAPH {\n' + this.patterns.map(t => indent + '  ' + t.toString() + '\n').join('') + '}';
  }
  static blessSparqlJs (sparqlJsGraph: SparqlJs.GraphPattern) {
    if (sparqlJsGraph.type !== 'graph') throw Error(`expected to bless something with .type=graph in ${JSON.stringify(sparqlJsGraph)}`);
    return new Graph(SparqlPattern.bless(sparqlJsGraph.patterns));
  }
}

export class Minus extends PatternPattern /* implements SparqlJs.MinusPattern */ {
  type: 'minus' = 'minus';
  constructor (
    patterns: SparqlPattern[]
  ) {
    super('minus', patterns);
  }
  toString (indent = '') {
    return indent + 'MINUS {\n' + this.patterns.map(t => indent + '  ' + t.toString() + '\n').join('') + '}';
  }
  static blessSparqlJs (sparqlJsMinus: SparqlJs.MinusPattern) {
    if (sparqlJsMinus.type !== 'minus') throw Error(`expected to bless something with .type=minus in ${JSON.stringify(sparqlJsMinus)}`);
    return new Minus(SparqlPattern.bless(sparqlJsMinus.patterns));
  }
}

export class Service extends PatternPattern /* implements SparqlJs.ServicePattern */ {
  type: 'service' = 'service';
  constructor (
    patterns: SparqlPattern[]
  ) {
    super('service', patterns);
  }
  toString (indent = '') {
    return indent + 'SERVICE {\n' + this.patterns.map(t => indent + '  ' + t.toString() + '\n').join('') + '}';
  }
  static blessSparqlJs (sparqlJsService: SparqlJs.ServicePattern) {
    if (sparqlJsService.type !== 'service') throw Error(`expected to bless something with .type=service in ${JSON.stringify(sparqlJsService)}`);
    return new Service(SparqlPattern.bless(sparqlJsService.patterns));
  }
}

export class SparqlQuery extends SparqlPattern /* implements SparqlJs.SelectQuery */ {
  queryType: 'SELECT' = 'SELECT';
  constructor (
    public where: SparqlPattern[],
    public prefixes: { [prefix: string]: string; },
    public variables: [SparqlJs.Wildcard] | SparqlJs.Variable[]
  ) {
    super('query');
  }

  static blessSparqlJs (sparqlJsQuery: SparqlJs.SelectQuery) {
    if (sparqlJsQuery.type !== 'query') throw Error(`expected to bless something with .type=sparql in ${JSON.stringify(sparqlJsQuery)}`);
    return new SparqlQuery(sparqlJsQuery.where ? SparqlPattern.bless(sparqlJsQuery.where) : [], sparqlJsQuery.prefixes, sparqlJsQuery.variables);
  }

  getQuery () { return this; }
  getWhere () { return this.where; };

  static parse (parseMe: string | SparqlJs.SelectQuery, opts?: any) {
    const SparqlParser = new SparqlJs.Parser(opts);
    const sparqlJsObj = typeof parseMe === 'string' ? SparqlParser.parse(parseMe) as SparqlJs.SelectQuery : parseMe
    return SparqlQuery.blessSparqlJs(sparqlJsObj); // !! Construct
  }

  static selectStar (bgp: Bgp) {
    const where: /*SparqlPattern*/SparqlJs.Pattern[] = [bgp as SparqlJs.BgpPattern]; // couldn't convince tsc that Bgp implements BgpPattern
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

export function renderResultSet (sparqlSolutions: SparqlSolution[]): string[] {
  return sparqlSolutions.map(
    (sparqlSolution): string => '{'
      + Object.keys(sparqlSolution).map(
        b =>
          `\n  ?${b} => ${Term.blessSparqlJs(sparqlSolution[b])}`
      ).join('')
      + '\n}'
  );
}
