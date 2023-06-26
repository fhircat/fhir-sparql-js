const File = require('fs');
const Path = require('path');
const SparqlJs = require('sparqljs');
const SparqlParser = new SparqlJs.Parser();
const ShExParser = require("@shexjs/parser").construct();
const Tests = __dirname;
const Resources = Path.join(__dirname, '../../fhir-sparql-common/src/main/resources/');

describe('sparql-json', () => {
  it('should parse SPARQL', () => {
    const iFileName = Path.join(Tests, '../../notes/obs-pat.srq');
    const iText = File.readFileSync(iFileName, 'utf-8');
    const iQuery = SparqlParser.parse(iText);
    const refSparqlObj = JSON.parse(File.readFileSync(Path.join(Tests, 'obs-path-sparqljs.json')));
    expect(iQuery).toEqual(refSparqlObj);
  });

  it('should parse ShEx', () => {
    const FhirShEx = ShExParser.parse(File.readFileSync(Path.join(Resources, 'org/uu3/ShEx-mini-terse.shex'), 'utf-8'));
    const refFhirShExObj = JSON.parse(File.readFileSync(Path.join(Tests, 'ShEx-mini-terse.json'), 'utf-8'));
    expect(FhirShEx).toEqual(refFhirShExObj);
  });

  it('should translate obs-path', () => {
    const iQuery = SparqlParser.parse(File.readFileSync(Path.join(Tests, '../../notes/obs-pat.srq'), 'utf-8'));
    const FhirShEx = ShExParser.parse(File.readFileSync(Path.join(Resources, 'org/uu3/ShEx-mini-terse.shex'), 'utf-8'));
  });
});
