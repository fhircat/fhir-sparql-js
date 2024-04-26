/**
 * classes:
 *   FhirSparql: execute SPARQL queries over FHIR Resources
 *   FhirPathExecution: Info needed to construct call with FHIR REST API parameter
 *   RestParameterTree: map a REST API parameter to tree paths that it queries
 *   RestParameterChoice: mutually-exclusive voice between REST parameters
 */
import {QueryAnalyzer, PredicateToShapeDecls} from './QueryAnalyzer';
import {Ns, Rdf} from './Namespaces';
import {RdfUtils, SparqlQuery, TTerm, SparqlSolution, Meta, renderResultSet} from './RdfUtils';
import {ArcTree, PosArcTree} from './ArcTree';
import * as ShExJ from 'shexj';
import * as SparqlJs from "sparqljs";
import {ArcTreeFitsInShapeExpr} from './ArcTreeFitsInShapeExpr';
import type { IDataSource } from '@comunica/types';

import * as N3 from 'n3';
import {QueryEngine} from '@comunica/query-sparql-rdfjs';

import {FhirJsonToTurtle} from './FhirJsonToTurtle';

const SystemBases = {
  'http://terminology.hl7.org/CodeSystem/observation-category': 'http://terminology.hl7.org/CodeSystem/observation-category/',
  'http://loinc.org': 'http://loinc.org/rdf#',
  'http://snomed.info/sct': 'http://snomed.info/id/',
}

/**
 * Map a REST API parameter to tree paths that it queries.
 * A library of rules is constructed before query processing.
 */
class RestParameterTree {
  arcTree: ArcTree;

  /**
   *
   * @param restParameterName e.g. `code` as in http…Observation/code=http…loinc.org|72166-2
   * @param correspondingSparql a SPARQL BGP with variables corresponding to the (components of) the REST parameter, e.g.
   *   [] fhir:code [
   *     fhir:coding [
   *       rdf:rest* /rdf:first [
   *         fhir:code [ fhir:v ?v1 ] ;
   *         fhir:system [ fhir:v ?v2 ]
   *       ]
   *     ]
   *   ]
   * @param lexicalTransformer map variable(s) to lexical form, e.g. (values) => values[1] + '|' + values[0]
   */
  constructor (
      public restParameterName: string,
      correspondingSparql: string,
      public lexicalTransformer = (values: string[]) => values[0]
  ) {
    const query = SparqlQuery.parse('PREFIX fhir: <http://hl7.org/fhir/> PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> SELECT ?v1 { ' + correspondingSparql + ' }');
    this.arcTree = new QueryAnalyzer(null as unknown as ShExJ.Schema).getArcTrees(query).arcTrees[0].out[0];
    this.lexicalTransformer = lexicalTransformer;
  }

  toString () {
    return "TODO"
  }
}

  /**
   * convenience function for converting known FHIR Coding type
   * @param typeIriString IRI for Coding type, e.g. http…loinc.org/72166-2
   */
  function parseCodingType (typeIriString: string): string | null {
    for (const [system, namespace] of Object.entries(SystemBases)) {
      if (typeIriString.startsWith(namespace)) {
        const code = typeIriString.substring(namespace.length);
        return system + '|' + code;
      }
    }
    return null;
  }

/**
 * Simple CGI parameter name.value pair
 */
class QueryParam {
  /**
   * Construct parms like `http://…foo?p1=v1&p2=v2`
   * @param name parameter name
   * @param value (un-encoded) parameter value
   */
  constructor (
      public name: string,
      public value: string
  ) {
  }
}

/**
 * Info needed to construct call with FHIR REST API parameter
 */
export class FhirPathExecution {
  /**
   * Construct a FhirPathExecution like
   *   new FhirPathExecution('Patient', null, [ new QueryParam('code', 'http…loinc.org/72166-2') ])
   * @param type Resource type, e.g. Observation
   * @param version document version to be hard-coded into request
   * @param paths n QueryParams (attr/value pairs)
   */
  constructor (
      public type: string,
      public version: string | null,
      public paths: QueryParam[],
      ) {}
}

/**
 * mutually-exclusive voice between REST parameters
 * Two reasons for constructing choices:
 *    Multiple ways to calculate the same REST parameter, e.g. a code from:
 *      a Coding type like http…loinc.org/72166-2
 *      a pair of coding system and code
 *      a single code with no system
 *    Mutually-exclusive parameters like `name` and `family name`
 */
class RestParameterChoice {
  /**
   * Construct a list of parameter choices like
   *   new RestParameterChoice([Rule_CodeFromType, Rule_CodeWithSystem, Rule_CodeWithOutSystem])
   * @param choices
   */
  constructor (
      public choices: RestParameterTree[]
  ) {}

  /**
   * Test to see if the supplied ArcTrees match any of this's choices.
   * @param arcTrees list of ArcTrees that this could match.
   * @param sparqlSolution possible bindings for variables.
   * TODO: this is called with all of the ArcTrees coming off the root resource. What if it were just called with the root resource?
   */
  accept (arcTrees: ArcTree[], sparqlSolution: SparqlSolution) {
    for (let choiceNo = 0; choiceNo < this.choices.length; ++choiceNo) {
      const choice = this.choices[choiceNo];
      const values = this._parallelWalk(arcTrees, choice.arcTree, choiceNo, sparqlSolution);
      if (values !== null) {
        const mappedValue = choice.lexicalTransformer(values.map(v => (v as SparqlJs.IriTerm).value));
        if (mappedValue) {
          return new QueryParam (choice.restParameterName, mappedValue);
        }
      }
    }
    return null;
  }

  /**
   * Called iteratively while analyzing each component in a query tree for matching rules.
   * @param testArcTrees
   * @param myArcTree
   * @param choiceNo
   * @param sparqlSolution
   */
  protected _parallelWalk (testArcTrees: ArcTree[], myArcTree: ArcTree, choiceNo: number, sparqlSolution: SparqlSolution): (TTerm | null)[] | null {
    const needed = myArcTree.out.slice(); // copy because needed gets spliced if members are matched
    const matched: (TTerm | null)[]  = testArcTrees.map(testArcTree => {
      if (RdfUtils.pmatch(testArcTree.tp.predicate, myArcTree.tp.predicate)) {
        if (myArcTree.out.length === 0) {
          // match!
          let matchedTerm: TTerm = testArcTree.tp.object;
          if (['Variable', 'BlankNode'].indexOf(matchedTerm.termType) !== -1) {
            if (!sparqlSolution[matchedTerm.value])
              return null;
            matchedTerm = sparqlSolution[matchedTerm.value]
          }
          // istanbul ignore next line -- otherwise flags next line as uncovered though tests show it isn't.
          if (RdfUtils.isPath(matchedTerm)) {
            throw Error(`unexpected RDF Property Path in ${JSON.stringify(matchedTerm)}`)
          } else {
            switch (matchedTerm.termType) {
                // case 'BlankNode':
                //   return null; // this indicates we don't have a value so we can't bind it
              case 'NamedNode':
              case 'Literal':
                return [matchedTerm]; // guessing lanuage and datatype are unimportant in FHIRPath
                // case 'Variable':
                //   const boundValue = sparqlSolution[matchedTerm.value];
                //   return boundValue ? [boundValue] : null;
                // istanbul ignore next line
              default: // istanbul ignore next line
                throw Error(`unexpected RDF term type in ${JSON.stringify(matchedTerm)}`)
            }
          }
        } else {
          // this.choices[choiceNo] // advance me
          const nestedRet = [];
          for (let myOutIdx = 0; myOutIdx < needed.length; ++myOutIdx) {
            const ret = this._parallelWalk(testArcTree.out, needed[myOutIdx], choiceNo, sparqlSolution);
            if (ret !== null) {
              needed.splice(myOutIdx, 1); // we matched a needed arcTree
              --myOutIdx;
              nestedRet.push(ret);
            }
          }
          return nestedRet.length === 0 ? null : nestedRet.flat();
        }
      } else {
        // not this testArcTree; try again
        return null;
      }
    }).flat();
    const vals = matched!.filter(x => !!x);
    if (vals.length && needed.length === 0) {
      // log.trace('matched', JSON.stringify(vals));
      return vals;
    } else {
      return null;
    }
  }
}

/* some hard-coded REST parameters.
 * TODO: read from spec somewhere; maybe Claude has ideas.
 */
const Rule_Id = new RestParameterTree('id', '[] fhir:id [ fhir:v ?v1 ]')

const Rule_Subject = new RestParameterTree('subject', '[] fhir:subject [ fhir:reference [ fhir:link ?v1 ] ]')

const Rule_CodeFromType = new RestParameterTree( // exported for tests/FhirSparq-test
  'code',
  `
[] fhir:code [
  fhir:coding [
    rdf:rest*/rdf:first [
      a ?v1
    ]
  ]
]`,
  (values) => parseCodingType(values[0])!
);

export const Rule_CodeWithSystem = new RestParameterTree( // exported for tests/FhirSparq-test
  'code',
  `
[] fhir:code [
  fhir:coding [
    rdf:rest*/rdf:first [
      fhir:code [ fhir:v ?v1 ] ;
      fhir:system [ fhir:v ?v2 ]
    ]
  ]
]`,
  (values) => values[1] + '|' + values[0]
);

const Rule_CodeWithOutSystem = new RestParameterTree(
  'code',
  `
[] fhir:code [
    fhir:coding [
       rdf:rest*/rdf:first [
        fhir:code [
          fhir:v ?v1
        ]
      ]
    ]
  ]`
);

/* e.g.
  name looks for: family, given, prefix, suffix, text
  fhir:name ( [] [
     fhir:use [ fhir:v "official" ] ;
     fhir:family [ fhir:v "Chalmers" ] ;
     fhir:given ( [ fhir:v "Peter" ] [ fhir:v "James" ] )
  ] [] )
*/

/* probably `name` parameters are superceded by `family` and `given`
const Rule_NameFamily = new Rule(
  'name',
  `
[] fhir:name [
  fhir:family [
    fhir:v ?v1
    ]}
  ]}`
)
}

const Rule_NameGiven = new Rule(
  'name',
  `
[] fhir:name [
  fhir:family [
    fhir:v ?v1
  ]
]`;
}
*/

const Rule_Family = new RestParameterTree(
  'family',
  `
[] fhir:name [
  fhir:family [
    fhir:v ?v1
  ]
]`
);

const Rule_Given = new RestParameterTree(
  'given',
  `
[] fhir:name [
  fhir:given [
    fhir:v ?v1
  ]
]`
);

const RuleChoice_Id = new RestParameterChoice([Rule_Id]); // gets removed if id supplied by root URL

/* some hard-coded FHIR Resource descriptions.
 * TODO: read from spec somewhere; maybe Claude has ideas.
 */
const ResourceToPaths = {
  "EveryResource": [RuleChoice_Id],
  "Observation": [new RestParameterChoice([Rule_Subject]), new RestParameterChoice([Rule_CodeFromType, Rule_CodeWithSystem, Rule_CodeWithOutSystem])],
  "Patient": [new RestParameterChoice([Rule_Given]), new RestParameterChoice([Rule_Family])], // new RestParameterChoice([Rule_NameFamily]), new RestParameterChoice([Rule_NameGiven])
  "Procedure": [new RestParameterChoice([Rule_Subject]), new RestParameterChoice([Rule_CodeFromType, Rule_CodeWithSystem, Rule_CodeWithOutSystem])],
  "Questionnaire": [],
}

const AllResources = [
  'Observation',
  'Patient',
  'Procedure',
  'Questionnaire'
]; // That's all of 'em; trust me.

const ResourceTypeRegexp = new RegExp(
            '^https?://.*?/([A-Z][a-z]+)/([^/|]+)(?:\\|(.*))?$'
);

declare type DataFormats = 'Turtle' | 'JSON';

/**
 * Execute SPARQL queries over FHIR Resources.
 */
export class FhirSparql extends QueryAnalyzer {
  tester: ArcTreeFitsInShapeExpr;

  /**
   * Construct an object to execute SPARQL queries.
   * @param shex ShEx schema which provides the structures of known FHIR Resources.
   */
  constructor (shex: ShExJ.Schema) {
    super(shex);
    this.tester = new ArcTreeFitsInShapeExpr(shex);
  }

  /**
   * Find all FHIR REST API parameters that can be fulfilled by the arcTree with
   * the bindings in the sparqlSolution.
   *
   * @param arcTree query pattern to match (expressed as an ArcTree).
   * @param referents variables referenced from outside `arcTree`.
   * @param sparqlSolution mapping of variables name to RdfJs terms.
   */
  extractRestParameters(arcTree: ArcTree, referents: Set<string>, sparqlSolution: SparqlSolution): Array<FhirPathExecution> {
    let resourceType = null;
    let resourceId = null;
    let resourceUrl = null;
    let resourceVersion: string | null = null;

    const prefilledRules: QueryParam[] = [];
    const allResourceRules = ResourceToPaths.EveryResource.slice();
    let candidateTypes = null; // initialized soon

    // There must be at least one Triple in the arcTree or it wouldn't exist.
    const rootTriple = arcTree.out[0].tp;

    switch (rootTriple.subject.termType) {
    case 'NamedNode':
      resourceUrl = rootTriple.subject.value;
      break;
    case 'Variable':
      // If the root node was the object of a FHIR reference
      if (referents.has(rootTriple.subject.value) && sparqlSolution[rootTriple.subject.value])
        resourceUrl = (sparqlSolution[rootTriple.subject.value] as SparqlJs.IriTerm).value;
    }

    if (resourceUrl !== null) {
      // parse the URL according to FHIR Protocol
      let match = resourceUrl.match(ResourceTypeRegexp); // !!! HORRIBLE HACK
      if (!match) {
        if (!match) {
          throw Error(`subject node ${resourceUrl} didn\'t match FHIR protocol`);
        }
      }
      resourceType = match[1];
      resourceId = match[2];
      resourceVersion = match[3] || null;

      // Sanity-check parsed resourcetype
      // istanbul ignore next line
      if (AllResources.indexOf(resourceType) === -1) // istanbul ignore next line
        throw Error(`did not recognize FHIR Resource in ${resourceUrl}`)
      candidateTypes = [resourceType];

      // Add id QueryParam
      prefilledRules.push(new QueryParam(Rule_Id.restParameterName, resourceId));

      // Remove Rule_Id from candidateRules
      const idRuleIdx = allResourceRules.indexOf(RuleChoice_Id);
      // istanbul ignore next line
      if (idRuleIdx === -1) // istanbul ignore next line
        throw Error(`should have an id rule from ResourceToPaths.EveryResource: ${ResourceToPaths.EveryResource}`);
      allResourceRules.splice(idRuleIdx, 1);

    } else if (RdfUtils.pmatch(rootTriple.predicate, Rdf.type as SparqlJs.IriTerm)) {
      // If there's a type arc, it's the first child.
      resourceType = rootTriple.object.value.substring(Ns.fhir.length);
      candidateTypes = [resourceType];

    } else {
      // could be any resource.
      candidateTypes = AllResources;
    }

    // Build list of candidate rules.
    return candidateTypes.filter(type => {
      const candidateShapeLabels = this.resourceTypeToShapeDeclIds.get(type);
      return candidateShapeLabels!.find(label => { // stop on first match in canidate shape even if it fits in multiple places
        // istanbul ignore next line
        if (arcTree.tp !== null) // istanbul ignore next line
          throw Error(`Expected root of ArcTree to be null: ${arcTree.toString()}`)
        return !arcTree.out.find(child => !this.tester.visitShapeRef(label, child));
      });
    }).map(type => {
      const myResourceRules = allResourceRules.slice();
      Array.prototype.push.apply(myResourceRules, ResourceToPaths[type as keyof typeof ResourceToPaths]);
      const acceptedPaths = myResourceRules
          .map(ruleChoice => ruleChoice.accept(arcTree.out, sparqlSolution)) // top tree has no tp
          .filter(queryParam => queryParam !== null) as QueryParam[];
      const paths = prefilledRules.concat(acceptedPaths);

      return new FhirPathExecution(type, resourceVersion, paths);
    })
  }

  /**
   * Analyze `sparqlQuery`, execute corresponding FHIR REST API queries against
   * `fhirEndpoint`, and (re-)execute `sparqlQuery` components as FHIR Resources
   * are returned.
   *
   * The following unification strategy executes queries which span multiple
   * types of FHIR Resources:
   *   for each arcTree extracted from `sparqlQuery`
   *     for each solution in the current results (initialized to one solution with no bindings)
   *       for each set of extracted REST parameters (`extractRestParameters` returns a disjunct of possible fulfillments)
   *         construct and execute a FHIR REST API query
   *         for each response, re-execute the portion of the SPARQL query related to that Resource type
   *           push each query solution into the results for the next iteration
   *
   * @param fhirEndpoint URL of a FHIR REST API endpoint with queryable FHIR Resources.
   * @param sparqlQuery SPARQL query expected to match Resources at `fhirEnpoint`
   * @param log implementation of Bunyan logger interface (`log.info()`, `log.debug()`...)
   */
  async executeFhirQuery (fhirEndpoint: string, sparqlQuery: string, log: any): Promise<Array<SparqlSolution>> {
    log.trace('executing', sparqlQuery, 'on', fhirEndpoint);
    const parserOpts = {
      prefixes: undefined,
      baseIRI: 'http://localhost/some/path/and/file.txt',
      factory: N3.DataFactory,
      skipValidation: false,
      skipUngroupedVariableCheck: false,
      pathOnly: false,
    }

    const iQuery = SparqlQuery.parse(sparqlQuery, parserOpts);
    const {arcTrees, connectingVariables, referents} = this.getArcTrees(iQuery);
    log.trace({arcTrees: arcTrees.map((t, i) => `\n[${i}]: ` + t).join("\n--"), connectingVariables, referents});

    const sources = [];
    let resultSet = [{}];

    // For each arcTree
    for (const arcTree of arcTrees) {
      log.trace('procesing arcTrees[' + arcTrees.indexOf(arcTree) + ']');
      const newResults: Array<SparqlSolution> = [];

      // For each solution in the current resultSet
      for (const solution of resultSet) {

        // for possible way to fulfill the extracted arcTree by executing REST calls
        const fhirPathExecutions = this.extractRestParameters(arcTree, referents, solution);
        for (const fhirPathExecution of fhirPathExecutions) {
          // a fhirPathExecution will have n bindings like:
          //   {name: 'code', value: 'http://loinc.org|72166-2'} -> code=http%3A%2F%2Floinc.org%7C72166-2

          // construct and execute query URL
          const searchUrl = new URL(fhirPathExecution.type, fhirEndpoint);
          for (const {name, value} of fhirPathExecution.paths)
            searchUrl.searchParams.set(name, value);
          const resp = await fetch(searchUrl, { headers: { Accept: 'application/json+fhir' } });
          const body = await resp.text();
          if (!resp.ok)
            throw Error(`Got ${resp.status} response to query for a ${fhirPathExecution.type} with [${fhirPathExecution.paths.map(p => p.name + ':' + p.value).join(', ')}] at FHIR endpoint <${fhirEndpoint}>:\n${body}`);
          const bundle = JSON.parse(body);
          log.trace(`<${decodeURIComponent(searchUrl.href)}> => ${bundle.entry.map((e: {[key: string]: any}, i: number) => `\n  ${i}: <${e.fullUrl}>`).join('')}`);

          // for each response
          for (const {fullUrl, resource} of bundle.entry) {
            const url = new URL(fullUrl);
            const ttl = new FhirJsonToTurtle().prettyPrint(resource);
            const db = parseTurtle(fullUrl, ttl, 'Turtle');
            const src = { url, body: ttl, db };
            sources.push(src);

            // execute the part of the SPARQL query related to this resource type
            const queryStr = SparqlQuery.selectStar(arcTree.getBgp());
            const bindings = await executeQuery([db], queryStr);
            const newResult = bindings.map(r => Object.assign(r, solution));

            // push all solutions into the resultSet for the next iteration
            Array.prototype.push.apply(newResults, newResult);
          }
        }
      }
      resultSet = newResults;
    }
    return resultSet;

    /* helper functions
     */
    function parseTurtle (baseIRI: string, text: string, dataFormat: DataFormats = 'Turtle') {
      if (dataFormat === 'JSON')
        text = new FhirJsonToTurtle().prettyPrint(JSON.parse(text));

      const db = new N3.Store();
      const parser = new N3.Parser({baseIRI})
      log.trace({baseIRI, text, dataFormat});
      db.addQuads(parser.parse(text));
      log.trace('=> ', db.size, 'triples');
      return db;
    }

    async function executeQuery (sources: [IDataSource, ...Array<IDataSource>], queryStr: string) {
      log.trace('sparql query:', queryStr);
      const myEngine = new QueryEngine();
      const typedStream = await myEngine.queryBindings(queryStr, {sources});
      const asArray = await typedStream.toArray();
      const rows = asArray.map(
        // @ts-ignore
        b => Object.fromEntries(b.entries)
      );
      log.trace('=> ', renderResultSet(rows).join(''));
      return rows;
    }
  }
}
