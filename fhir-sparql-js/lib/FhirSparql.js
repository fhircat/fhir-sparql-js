const {QueryAnalyzer} = require('./QueryAnalyzer');
const {Ns, Rdf, Fhir, FirstRest} = require('./Namespaces');
const {RdfUtils, SparqlQuery} = require('./RdfUtils');
const {ArcTree} = require('./ArcTree');

class ConnectingVariables {
  static toString (cvs) {
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
  constructor (fhirQuery, sparql, arg = (values) => values[0]) {
    this.fhirQuery = fhirQuery;
    const query = SparqlQuery.parse('PREFIX fhir: <http://hl7.org/fhir/> PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> SELECT ?v1 { ' + sparql + ' }');
    this.arcTree = new QueryAnalyzer(null).getArcTrees(query).arcTrees[0].out[0];
    this.arg = arg;
  }

  toString () {
    return "TODO"
  }
}

const Rule_Id = new Rule('id', '[] fhir:id [ fhir:v ?v1 ]')

const Rule_CodeWithSystem = new Rule(
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

Rule_Family = new Rule(
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
  constructor (name, value) {
    this.name = name;
    this.value = value;
  }
}

class FhirPathExecution {
  constructor (type, version, paths) {
    this.type = type;
    this.version = version;
    this.paths = paths;
  }
}

/** list of 1 or more candidate rules.
 * last one standing wins
 */
class RuleChoice {
  constructor (choices) {
    this.choices = choices;
  }

  accept (arcTrees, sparqlSolution) {
    for (let choiceNo = 0; choiceNo < this.choices.length; ++choiceNo) {
      const choice = this.choices[choiceNo];
      const values = this.parallelWalk(arcTrees, choice.arcTree, choiceNo, sparqlSolution);
      if (values !== null)
        return new QueryParam (choice.fhirQuery, choice.arg(values.map(v => v.value)));
    }
    return null;
  }

  parallelWalk (testArcTrees, myArcTree, choiceNo, sparqlSolution) {
    const needed = myArcTree.out;
    const matched = testArcTrees.map(testArcTree => {
      if (testArcTree.tp.predicate.equals(myArcTree.tp.predicate)) {
        if (myArcTree.out.length === 0) {
          // match!
          const matchedTerm = testArcTree.tp.object;
          switch (matchedTerm.termType) {
          case 'NamedNode':
            throw Error(`why are we binding ${matchedTerm}`);
          case 'BlankNode':
            return null; // this indicates we don't have a value so we can't bind it
          case 'Literal':
            return [matchedTerm]; // guessing lanuage and datatype are unimportant in FHIRPath
          case 'Variable':
            const boundValue = sparqlSolution[matchedTerm.value];
            return boundValue ? [boundValue] : null;
          default:
            throw Error(`unexpected RDF term type in ${JSON.stringify(matchedTerm)}`)
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
    const vals = matched.filter(x => !!x);
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
  "Observation": [new RuleChoice([Rule_CodeWithSystem, Rule_CodeWithOutSystem])],
  "Patient": [new RuleChoice([Rule_Given])], // new RuleChoice([Rule_NameFamily]), new RuleChoice([Rule_NameGiven])
  "Procedure": [new RuleChoice([Rule_CodeWithSystem, Rule_CodeWithOutSystem])],
  "Questionnaire": [],
}

const AllResources = [
  'Observation',
  'Patient',
  'Procedure',
  'Questionnaire'
]; // That's all of 'em; trust me.

const ResourceTypeRegexp = new RegExp('^https?://.*?/([A-Z][a-z]+)/([^/]+)(?:|(.*))$');

class FhirSparql extends QueryAnalyzer {
  constructor (shex) { super(shex); }

  opBgpToFhirPathExecutions (arcTree, referents, sparqlSolution) {
    let resourceType = null;
    let resourceId = null;
    let resourceUrl = null;
    let resourceVersion = null;

    const prefilledRules = [];
    const candidateRules = ResourceToPaths.EveryResource.slice();
    const completedRules = [];
    let candidateTypes = null; // initialized soon

    // There must be at least one Triple in the arcTree or it wouldn't exist.
    const rootTriple = arcTree.out[0].tp;

    switch (rootTriple.subject.termType) {
    case 'NamedNode':
      resourceUrl = rootTriple.subject.value;
      break;
    case 'Variable':
      if (referents.has(rootTriple.subject.value) && sparqlSolution[rootTriple.subject.value])
      // If the root node was the object of a FHIR reference
      resourceUrl = sparqlSolution[rootTriple.subject.value].value;
    }

    if (resourceUrl !== null) {
      // parse the URL according to FHIR Protocol
      const match = resourceUrl.match(ResourceTypeRegexp);
      if (!match)
        throw Error(`subject node ${resourceUrl} didn't match FHIR protocol`);
      resourceType = match[1]; // shouldn't be null
      resourceId = match[2] || null;// `|| null` changes `undefined` to `null` for consistency
      resourceVersion = match[3] || null;

      // Sanity-check parsed resourcetype
      if (AllResources.indexOf(resourceType) === -1)
        throw Error(`did not recognize FHIR Resource in ${resourceUrl}`)
      candidateTypes = [resourceType];

      // Add id QueryParam
      prefilledRules.push(new QueryParam(Rule_Id.fhirQuery, resourceId));

      // Remove Rule_Id from candidateRules
      const idRuleIdx = candidateRules.indexOf(RuleChoice_Id);
      if (idRuleIdx === -1)
        throw Error(`should have an id rule from ResourceToPaths.EveryResource: ${ResourceToPaths.EveryResource}`);
      candidateRules.splice(idRuleIdx, 1);

    } else if (rootTriple.predicate.equals(Rdf.type)) {
      // If there's a type arc, it's the first child.
      resourceType = rootTriple.object.value.substring(Ns.fhir.length);
      candidateTypes = [resourceType];

    } else {
      // could be any resource.
      candidateTypes = AllResources;
    }

    // Build list of candidate rules.
    candidateTypes.forEach(type =>
      Array.prototype.push.apply(candidateRules, ResourceToPaths[type])
    );

    const acceptedPaths = candidateRules
          .map(ruleChoice => ruleChoice.accept(arcTree.out, sparqlSolution)) // top tree has no tp
          .filter(queryParam => queryParam !== null);
    const paths = prefilledRules.concat(acceptedPaths);

    return new FhirPathExecution(resourceType, resourceVersion, paths);
  }
}

module.exports = {FhirSparql, ConnectingVariables, ArcTree, FhirPathExecution, Rule_CodeWithSystem};
