/**
 * enhance application/sparql-results+json with relative links
 */
const { SparqlJsonParser } = require('sparqljson-parse');

class RelativeSparqlJsonParser extends SparqlJsonParser {
  constructor (opts) {
    super(opts);
    this.baseIRI = opts.baseIRI;
  }

  parseJsonResults (obj) {
    const res = super.parseJsonResults (obj);
    if (this.baseIRI) {
      res.forEach(
        row => Object.entries(row).forEach(
          ([variable, binding]) => {
            if (binding.termType === 'NamedNode')
              binding.value = new URL(binding.value, this.baseIRI).href;
            return [variable, binding]
          }
        )
      );
    }
    return res;
  }
}

module.exports = { RelativeSparqlJsonParser };
