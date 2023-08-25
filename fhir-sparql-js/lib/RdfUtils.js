/* istanbul ignore file */

const {Ns, Rdf, Xsd, Fhir, FirstRest} = require('./Namespaces');
const SparqlJs = require('sparqljs');
const SparqlParser = new SparqlJs.Parser();

class RdfUtils {

  /** find triples matching (s, p, o)
   * could move to Bgp, but is always invokes on a List
   */
  static getMatching (triplePatterns, s, p, o) {
    return triplePatterns.filter(tp =>
      (s === null || tp.subject.equals(s)) &&
      (p === null || tp.predicate.equals(p)) &&
      (o === null || tp.object.equals(o))
    );
  }

  /** remove triples matching (s, p, o)
   */
  static stealMatching (triplePatterns, s, p, o) {
    const ret = [];
    for (let i = 0; i < triplePatterns.length; ++i) {
      const tp = triplePatterns[i];
      if ((s === null || tp.subject.equals(s)) &&
          (p === null || tp.predicate.equals(p)) &&
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
  static pStr (predicate) {
    return predicate.value
      ? '<' + predicate.value + '>'
      : '(' + predicate.items.map(item => RdfUtils.pStr(item) + (item.pathType || '')).join('/') + ')'; // TODO: not correct
  }
}

class Term {
  constructor (termType, value) {
    this.termType = termType;
    this.value = value;
  }
  equals (r) {
    return this.termType === r.termType && this.value === r.value;
  }
  static blessSparqlJs (sparqlJsTerm) {
    if (sparqlJsTerm.type === 'path')
      return new Path(sparqlJsTerm.pathType, sparqlJsTerm.items.map(item => Term.blessSparqlJs(item)));

    switch (sparqlJsTerm.termType) {
    case 'NamedNode': return new NamedNode(sparqlJsTerm.value);
    case 'BlankNode': return new BlankNode(sparqlJsTerm.value);
    case 'Literal':
      const datatype = sparqlJsTerm.datatype ? Term.blessSparqlJs(sparqlJsTerm.datatype) : new NamedNode(Xsd.string);
      return new Literal(sparqlJsTerm.value, sparqlJsTerm.language, datatype);
    case 'Variable': return new Variable(sparqlJsTerm.value);
    default: throw Error(`unknown SparqlJs term type in ${JSON.stringify(sparqlJsTerm)}`);
    }
  }
}

class NamedNode extends Term { constructor (value) { super('NamedNode', value); } toString() { return '<' + this.value + '>'; } }
class BlankNode extends Term { constructor (value) { super('BlankNode', value); } toString() { return '_:' + this.value; } }
class Variable  extends Term { constructor (value) { super('Variable', value); } toString() { return '?' + this.value; } }
class Literal   extends Term {
  constructor (value, language, datatype) {
    super('Literal', value);
    this.language = language;
    this.datatype = datatype;
  }
  toString () {
    return '"' + this.value + '"' +
      (this.language
       ? '@' + this.language
       : this.datatype && this.datatype.value !== Xsd.string
       ? '^^' + this.datatype.toString()
       : '');
  }
  equals (r) {
    return super.equals(r) && this.language === r.language && this.datatype === r.datatype;
  }
}

class Path {
  constructor (pathType, items) {
    this.type = 'path';
    this.pathType = pathType;
    this.items = items;
  }
  equals (r) {
    if (this.type !== r.type) return false;
    if (this.pathType !== r.pathType) return false;
    if (this.items.length !== r.items.length) return false;
    for (let i = 0; i < this.items.length; ++i)
      if (!this.items[i].equals(r.items[i]))
        return false;
    return true;
  }
}

class Triple {
  constructor (subject, predicate, object) {
    this.subject   = (subject);
    this.predicate = (predicate);
    this.object    = (object);
  }
  toString () {
    return `${this.subject} ${this.predicate} ${this.object} .`;
  }
  equals (r) {
    return this.subject.equals(r.subject) &&
      this.predicate.equals(r.predicate) &&
      this.object.equals(r.object);
  }
  static blessSparqlJs (sparqlJsTriple) {
    return new Triple(
      Term.blessSparqlJs(sparqlJsTriple.subject),
      Term.blessSparqlJs(sparqlJsTriple.predicate),
      Term.blessSparqlJs(sparqlJsTriple.object)
    );
  }
}

class Bgp {
  constructor (triples) {
    this.type = 'bgp';
    this.triples = triples;
  }
  toString (indent = '') {
    return indent + '{\n' + this.triples.map(t => indent + '  ' + t.toString() + '\n').join('') + '}';
  }
  static blessSparqlJs (sparqlJsBgp) {
    if (sparqlJsBgp.type !== 'bgp') throw Error(`expected to bless something with .type=bgp in ${JSON.stringify(sparqlJsBgp)}`);
    return new Bgp(sparqlJsBgp.triples.map(t => Triple.blessSparqlJs(t)));
  }
}

class SparqlQuery {
  constructor (query) {
    // query.where[0].triples = query.where[0].triples.map(t => Triple.blessSparqlJs(t));
    query.where = query.where.map(bgp => Bgp.blessSparqlJs(bgp));
    this.query = query;
  }

  getQuery () { return this.query; }
  getWhere () { return this.query.where; };

  static parse (text) {
    return new SparqlQuery(SparqlParser.parse(text));
  }
}

module.exports = {RdfUtils, Bgp, Triple, Path, Term, SparqlQuery};
