const {Ns, Rdf, Fhir, FirstRest} = require('./Namespaces');

class RdfUtils {

  // debugging printer
  static ToTurtle (x) {
    if (x === null)
      return 'null';
    if ('subject' in x)
      return `${RdfUtils.ToTurtle(x.subject)} ${RdfUtils.ToTurtle(x.predicate)} ${RdfUtils.ToTurtle(x.object)} .`
    if (x.type === 'path')
      return x.value
      ? '<' + x.value + '>'
      : '(' + x.items.map(item => RdfUtils.pStr(item) + (item.pathType || '')).join('/') + ')' // TODO: not correct

    switch (x.termType) {
    case 'NamedNode': return '<' + x.value + '>';
    case 'BlankNode': return '_:' + x.value;
    case 'Variable': return '?' + x.value;
    case 'Literal': return '"' + x.value + '"' +
        (x.language
         ? '@' + x.language
         : x.datatype
         ? RdfUtils.ToTurtle(x.datatype)
         : '');
      // istanbul ignore next
    default: throw Error(`ToTurtle - unrecognized argument ${JSON.stringify(x)}`);
    }
  }

  /** find triples matching (s, p, o)
   */
  static getMatching (triplePatterns, s, p, o) {
    return triplePatterns.filter(tp =>
      (s === null || RdfUtils.Equals(tp.subject, s)) &&
      (p === null || RdfUtils.Equals(tp.predicate, p)) &&
      (o === null || RdfUtils.Equals(tp.object, o))
    );
  }

  /** remove triples matching (s, p, o)
   */
  static stealMatching (triplePatterns, s, p, o) {
    const ret = [];
    for (let i = 0; i < triplePatterns.length; ++i) {
      const tp = triplePatterns[i];
      if ((s === null || RdfUtils.Equals(tp.subject, s)) &&
          (p === null || RdfUtils.Equals(tp.predicate, p)) &&
          (o === null || RdfUtils.Equals(tp.object, o))) {
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
  static Equals (l, r) {
    return l.termType === r.termType && l.value === r.value;
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

module.exports = {RdfUtils};
