import {QueryAnalyzer} from './QueryAnalyzer';
import {Ns, Rdf} from './Namespaces';
import {RdfUtils, SparqlQuery, TTerm, SparqlSolution, Meta} from './RdfUtils';
import {ArcTree, PosArcTree} from './ArcTree';
import * as ShExJ from 'shexj';
import * as SparqlJs from "sparqljs";
import {ArcTreeFitsInShapeExpr} from './ArcTreeFitsInShapeExpr';

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

class Rule {
  arcTree: ArcTree;
  constructor (
      public fhirQuery: string,
      sparql: string,
      public arg = (values: string[]) => values[0]
  ) {
    const query = SparqlQuery.parse('PREFIX fhir: <http://hl7.org/fhir/> PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> SELECT ?v1 { ' + sparql + ' }');
    this.arcTree = new QueryAnalyzer(null as unknown as ShExJ.Schema).getArcTrees(query).arcTrees[0].out[0];
    this.arg = arg;
  }

  toString () {
    return "TODO"
  }
}

const Rule_Id = new Rule('id', '[] fhir:id [ fhir:v ?v1 ]')

const Rule_Subject = new Rule('subject', '[] fhir:subject [ fhir:reference ?v1 ]')

export const Rule_CodeWithSystem = new Rule( // exported for tests/FhirSparq-test
  'code',
  `
[] fhir:code [
  fhir:coding [
    (rdf:first/rdf:rest)*/rdf:first [
      fhir:code [ fhir:v ?v1 ] ;
      fhir:system [ fhir:v ?v2 ]
    ]
  ]
]`,
  (values) => values[0] + '|' + values[1]
);

const Rule_CodeWithOutSystem = new Rule(
  'code',
  `
[] fhir:code [
    fhir:coding [
       (rdf:first/rdf:rest)*/rdf:first [
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

const Rule_Family = new Rule(
  'family',
  `
[] fhir:name [
  fhir:family [
    fhir:v ?v1
  ]
]`
);

const Rule_Given = new Rule(
  'given',
  `
[] fhir:name [
  fhir:given [
    fhir:v ?v1
  ]
]`
);

class QueryParam {
  constructor (
      public name: string,
      public value: string
  ) {
  }
}

export class FhirPathExecution {
  constructor (
      public type: string,
      public version: string | null,
      public paths: QueryParam[],
      ) {}
}

/** list of 1 or more candidate rules.
 * last one standing wins
 */
class RuleChoice {
  constructor (
      public choices: Rule[]
  ) {}

  accept (arcTrees: ArcTree[], sparqlSolution: SparqlSolution) {
    for (let choiceNo = 0; choiceNo < this.choices.length; ++choiceNo) {
      const choice = this.choices[choiceNo];
      const values = this.parallelWalk(arcTrees, choice.arcTree, choiceNo, sparqlSolution);
      if (values !== null)
        return new QueryParam (choice.fhirQuery, choice.arg(values.map(v => (v as SparqlJs.IriTerm).value)));
    }
    return null;
  }

  parallelWalk (testArcTrees: ArcTree[], myArcTree: ArcTree, choiceNo: number, sparqlSolution: SparqlSolution): (TTerm | null)[] | null {
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
            const ret = this.parallelWalk(testArcTree.out, needed[myOutIdx], choiceNo, sparqlSolution);
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
      // console.log(`matched ${JSON.stringify(vals)}`);
      return vals;
    } else {
      return null;
    }
  }
}

const RuleChoice_Id = new RuleChoice([Rule_Id]); // gets removed if id supplied by root URL

const ResourceToPaths = {
  "EveryResource": [RuleChoice_Id],
  "Observation": [new RuleChoice([Rule_Subject]), new RuleChoice([Rule_CodeWithSystem, Rule_CodeWithOutSystem])],
  "Patient": [new RuleChoice([Rule_Given]), new RuleChoice([Rule_Family])], // new RuleChoice([Rule_NameFamily]), new RuleChoice([Rule_NameGiven])
  "Procedure": [new RuleChoice([Rule_Subject]), new RuleChoice([Rule_CodeWithSystem, Rule_CodeWithOutSystem])],
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

export class FhirSparql extends QueryAnalyzer {
  tester: ArcTreeFitsInShapeExpr;
  constructor (shex: ShExJ.Schema) {
    super(shex);
    this.tester = new ArcTreeFitsInShapeExpr(shex);
  }

  opBgpToFhirPathExecutions (arcTree: ArcTree, referents: Set<string>, sparqlSolution: SparqlSolution, meta: Meta = {base: '', prefixes: {}}) {
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
      const match = resourceUrl.match(ResourceTypeRegexp);
      if (!match)
        throw Error(`subject node ${resourceUrl} didn\'t match FHIR protocol`);
      resourceType = match[1];
      resourceId = match[2];
      resourceVersion = match[3] || null;

      // Sanity-check parsed resourcetype
      // istanbul ignore next line
      if (AllResources.indexOf(resourceType) === -1) // istanbul ignore next line
        throw Error(`did not recognize FHIR Resource in ${resourceUrl}`)
      candidateTypes = [resourceType];

      // Add id QueryParam
      prefilledRules.push(new QueryParam(Rule_Id.fhirQuery, resourceId));

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
      // const tpz = arcTree.toSparqlTriplePatterns(sparqlSolution, meta);
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
}

// module.exports = {FhirSparql, ConnectingVariables, ArcTree, FhirPathExecution, Rule_CodeWithSystem};
