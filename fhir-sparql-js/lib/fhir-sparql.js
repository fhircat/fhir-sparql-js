const {Visitor: ShExVisitor} = require('./ShExVisitor');
const {Ns, Rdf, Fhir, FirstRest} = require('./Namespaces');

class PredicateToShapeDecl extends ShExVisitor {
  constructor (ctor_args) {
    super(ctor_args);
    this.map = new Map();
    this.curDecl = null;
  }

  visitSchema(schema, ...args) {
    if (!schema || !(typeof schema === 'object') || schema.type !== 'Schema')
      throw Error(`visitSchema argument must be a schema, got ${JSON.stringify(schema)}`);
    return super.visitSchema(schema, ...args);
  }

  visitShapeDecl(decl, ...args) {
    this.curDecl = decl;
    const ret = super.visitShapeDecl(decl, ...args);
    this.curDecl = null;
    return ret;
  }

  visitTripleConstraint(expr, ...args) {
    if (this.curDecl === null)
      throw new Error(`visiting ${JSON.stringify(expr)} while not in a ShapeDecl`);
    if (!expr.predicate.startsWith(Ns.rdf) && [Ns.fhir + 'v', Ns.fhir + 'nodeRole'].indexOf(expr.predicate) === -1) {
      if (!this.map.has(expr.predicate))
        this.map.set(expr.predicate, []);
      this.map.get(expr.predicate).push(this.curDecl);
    }
    return null;
  }

  visitNodeConstraint(shape, ...args) { // don't bother visiting NodeConstraints
    return null;
  }
}

class ArcTree {
  constructor (tp, out) {
    this.tp = tp;
    this.out = out;
  }

  getBgp () {
    const ret = [];
    if (this.tp !== null)
      ret.push(this.tp);
    this.out.forEach(tree =>
      Array.prototype.push.apply(ret, tree.getBgp())
    );
    return ret;
  }

  toString () {
    return this.tp === null ? 'null' : ToTurtle(this.tp);
  }
}

// debugging printer
function ToTurtle (x) {
  if (x === null)
    return 'null';
  if ('subject' in x)
    return `${ToTurtle(x.subject)} ${ToTurtle(x.predicate)} ${ToTurtle(x.object)} .`
  if (x.type === 'path')
    return x.value
      ? '<' + x.value + '>'
      : '(' + x.items.map(item => FhirSparql.pStr(item) + (item.pathType || '')).join('/') + ')' // TODO: not correct

  switch (x.termType) {
  case 'NamedNode': return '<' + x.value + '>';
  case 'BlankNode': return '_:' + x.value;
  case 'Variable': return '?' + x.value;
  case 'Literal': return '"' + x.value + '"' +
      (x.language
       ? '@' + x.language
       : x.datatype
       ? ToTurtle(x.datatype)
       : '');
  // istanbul ignore next
  default: throw Error(`ToTurtle - unrecognized argument ${JSON.stringify(x)}`);
  }
}

class ConnectingVariables {
  static toString (cvs, a, b, c) {
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

const Rule_CodeWithSystem = {
  arcTree: {tp: {subject: null, predicate: {termType: 'NamedNode', value: Ns.fhir + 'code'}, object: null}, out: [
    {tp: {subject: null, predicate: {termType: 'NamedNode', value: Ns.fhir + 'coding'}, object: null}, out: [
      {tp: {subject: null, predicate: FirstRest, object: null}, out: [
        {tp: {subject: null, predicate: {termType: 'NamedNode', value: Ns.fhir + 'code'}, object: null}, out: [
          {tp: {subject: null, predicate: {termType: 'NamedNode', value: Ns.fhir + 'v'}, object: null}, out: []}
        ]},
        {tp: {subject: null, predicate: {termType: 'NamedNode', value: Ns.fhir + 'system'}, object: null}, out: [
          {tp: {subject: null, predicate: {termType: 'NamedNode', value: Ns.fhir + 'v'}, object: null}, out: []}
        ]},
      ]}
    ]}
  ]},
  fhirQuery: 'code',
  arg: (values) => values[0] + '|' + values[1]
};

const Rule_CodeWithOutSystem = {
  arcTree: {tp: {subject: null, predicate: {termType: 'NamedNode', value: Ns.fhir + 'code'}, object: null}, out: [
    {tp: {subject: null, predicate: FirstRest, object: null}, out: [
      {tp: {subject: null, predicate: {termType: 'NamedNode', value: Ns.fhir + 'code'}, object: null}, out: [
        {tp: {subject: null, predicate: {termType: 'NamedNode', value: Ns.fhir + 'v'}, object: null}, out: []}
      ]},
    ]}
  ]},
  fhirQuery: 'code',
  arg: (values) => values[0]
};

const Rule_Id = {
  arcTree: {tp: {subject: null, predicate: {termType: 'NamedNode', value: Ns.fhir + 'id'}, object: null}, out: [
    {tp: {subject: null, predicate: Fhir.v, object: null }, out: []}
  ]},
  fhirQuery: 'id',
  arg: (values) => values[0]
}

/* e.g.
  name looks for: family, given, prefix, suffix, text
  fhir:name ( [] [
     fhir:use [ fhir:v "official" ] ;
     fhir:family [ fhir:v "Chalmers" ] ;
     fhir:given ( [ fhir:v "Peter" ] [ fhir:v "James" ] )
  ] [] )
*/

/* probably `name` parameters are superceded by `family` and `given`
const Rule_NameFamily = {
  arcTree: {tp: {subject: null, predicate: {termType: 'NamedNode', value: Ns.fhir + 'name'}, object: null}, out: [
    {tp: {subject: null, predicate: {termType: 'NamedNode', value: Ns.fhir + 'family'}, object: null}, out: [
      {tp: {subject: null, predicate: Fhir.v, object: null }, out: []}
    ]}
  ]},
  fhirQuery: 'name',
  arg: (values) => values[0]
}

const Rule_NameGiven = {
  arcTree: {tp: {subject: null, predicate: {termType: 'NamedNode', value: Ns.fhir + 'name'}, object: null}, out: [
    {tp: {subject: null, predicate: {termType: 'NamedNode', value: Ns.fhir + 'family'}, object: null}, out: [
      {tp: {subject: null, predicate: Fhir.v, object: null }, out: []}
    ]}
  ]},
  fhirQuery: 'name',
  arg: (values) => values[0]
}
*/

const Rule_Family = {
  arcTree: {tp: {subject: null, predicate: {termType: 'NamedNode', value: Ns.fhir + 'name'}, object: null}, out: [
    {tp: {subject: null, predicate: {termType: 'NamedNode', value: Ns.fhir + 'family'}, object: null}, out: [
      {tp: {subject: null, predicate: Fhir.v, object: null }, out: []}
    ]}
  ]},
  fhirQuery: 'family',
  arg: (values) => values[0]
}

const Rule_Given = {
  arcTree: {tp: {subject: null, predicate: {termType: 'NamedNode', value: Ns.fhir + 'name'}, object: null}, out: [
    {tp: {subject: null, predicate: {termType: 'NamedNode', value: Ns.fhir + 'given'}, object: null}, out: [
      {tp: {subject: null, predicate: Fhir.v, object: null }, out: []}
    ]}
  ]},
  fhirQuery: 'given',
  arg: (values) => values[0]
}

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
      if (Equals(testArcTree.tp.predicate, myArcTree.tp.predicate)) {
        if (myArcTree.out.length === 0) {
          // match!
          const matchedTerm = testArcTree.tp.object;
          switch (matchedTerm.termType) {
          case 'NamedNode':
          case 'BlankNode':
            throw Error(`binding ${ToTurtle(matchedTerm)} seems unlikely`);
          case 'Literal':
            return [matchedTerm];
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

/** Rdf node === Rdf node
 * will be specialized for every graph API
 */
function Equals (l, r) {
  return l.termType === r.termType && l.value === r.value;
}

const ResourceTypeRegexp = new RegExp('^https?://.*?/([A-Z][a-z]+)/([^/]+)(?:|(.*))$');

class FhirSparql {
  constructor (shex) {
    this.shex = shex;
    const visitor = new PredicateToShapeDecl();
    visitor.visitSchema(shex);
    this.predicateToShapeDecl = visitor.map;
  }

  getArcTrees (query) {
    const triples = query.where[0].triples;

    const todo = triples.slice().sort((l, r) => FhirSparql.pStr(l.predicate).localeCompare(FhirSparql.pStr(r.predicate)));
    /*
      console.log(todo.map(t => t.subject.value + ' ' + FhirSparql.pStr(t.predicate) + ' ' + t.object.value).join("\n"));
       1 ?obs fhir:code ?codeList
       3 ?coding fhir:code ?codeCode
      10 ?subject fhir:id ?patIdElt
       8 ?subjectRef fhir:reference ?subject
       7 ?obs fhir:subject ?subjectRef
       5 ?coding fhir:system ?codingSystem
       4 ?codeCode fhir:v 1234567
       6 ?codingSystem fhir:v "http://snomed.info/id"
      11 ?patIdElt fhir:v ?patId
       2 ?codeList rdf:first* /rdf:rest ?coding
       0 ?obs a fhir:Observation
       9 ?subject a fhir:Patient
     */

    // All known ArcTrees
    const arcTrees = [];
    // Variables connecting ArcTrees
    const connectingVariables = new Map();

    // All variables in starting operation (like a BGP, but with path expressions included)
    const usedVars = new Map();

    const referents = new Set();

    while (todo.length > 0) {
      // Pick a starting triple from remaining triples
      const start = todo[0];

      // Index from variable name to ArcTree
      const treeVars = new Map();

      // Terminal ancesters of start.subject
      const roots = [];

      // Working list of triples in start's tree.
      let tz = [start];
      do {
        // tz for the next pass
        const newTz = [];

        tz.forEach(t => {
          // get the node's incoming arcs (actually, just one in every scenario I've imagined).
          const arcsIn = FhirSparql.getMatching(todo, null, null, t.subject);

          // it either has incoming arcs or it's a root.
          if (arcsIn.length === 0) {
            roots.push(t.subject);
          } else {
            // barf if there's a cycle
            arcsIn.forEach(p => {
              if (Equals(p.subject, start.subject))
                throw Error(`can't handle cycle involving ${ToTurtle(p.subject)}`);
            });

            // find parents of these arcs on next iteration
            Array.prototype.push.apply(newTz, arcsIn);
          }
        });
        tz = newTz;
      } while (tz.length > 0);
      console.assert(roots.length > 0, 'should have a root (if there were any triples at all)');

      // build tree and index variables
      Array.prototype.push.apply(arcTrees, roots.map(root =>
        FhirSparql.constructArcTree(todo, null, root, treeVars, referents)
      ));

      // Sort treeVars for this tree
      for (const [k, treeNodes] of treeVars) {
        if (connectingVariables.has(k)) {
          // Already known ConnectingVariable
          Array.prototype.push.apply(treeNodes);
        } else if (usedVars.has(k)) {
          // Already used in previous ArcTree so it's now a ConnectingVariabel
          connectingVariables.set(k, usedVars.get(k).concat(treeNodes));
          usedVars.delete(k);
        } else {
          // First tree in which this variable appears
          usedVars.set(k, treeNodes);
        }
      }
    }

    return {arcTrees, connectingVariables, referents};
  }

  opBgpToFhirPathExecutions (arcTree, referents, sparqlSolution) {
    let resourceType = null;
    let resourceId = null;
    let resourceVersion = null;

    const prefilledRules = [];
    const candidateRules = ResourceToPaths.EveryResource.slice();
    const completedRules = [];
    let candidateTypes = null; // initialized soon

    // There must be at least one Triple in the arcTree or it wouldn't exist.
    const rootTriple = arcTree.out[0].tp;

    if (rootTriple.subject.termType === 'Variable' &&
        referents.has(rootTriple.subject.value) &&
        sparqlSolution[rootTriple.subject.value]) {

      // If the root node was the object of a FHIR reference
      const subjectBinding = sparqlSolution[rootTriple.subject.value].value;
      // parse the URL according to FHIR Protocol
      const match = subjectBinding.match(ResourceTypeRegexp);
      if (!match)
        throw Error(`subject node ${subjectBinding} didn't match FHIR protocol`);
      resourceType = match[1]; // shouldn't be null
      resourceId = match[2] || null;// `|| null` changes `undefined` to `null` for consistency
      resourceVersion = match[3] || null;

      // Sanity-check parsed resourcetype
      if (AllResources.indexOf(resourceType) === -1)
        throw Error(`did not recognize FHIR Resource in ${ToTurtle(subjectBinding)}`)
      candidateTypes = [resourceType];

      // Add id QueryParam
      prefilledRules.push(new QueryParam(Rule_Id.fhirQuery, resourceId));

      // Remove Rule_Id from candidateRules
      const idRuleIdx = candidateRules.indexOf(RuleChoice_Id);
      if (idRuleIdx === -1)
        throw Error(`should have an id rule from ResourceToPaths.EveryResource: ${ResourceToPaths.EveryResource}`);
      candidateRules.splice(idRuleIdx, 1);
    } else if (Equals(rootTriple.predicate, Rdf.type)) {
      // If there's a type arc, it's the first child.
      resourceType = rootTriple.object.value.substring(Ns.fhir.length);
      candidateTypes = [resourceType];

      // could be any resource.
    } else {
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

  /** find triples matching (s, p, o)
   */
  static getMatching (triplePatterns, s, p, o) {
    return triplePatterns.filter(tp =>
      (s === null || Equals(tp.subject, s)) &&
      (p === null || Equals(tp.predicate, p)) &&
      (o === null || Equals(tp.object, o))
    );
  }

  /** remove triples matching (s, p, o)
   */
  static stealMatching (triplePatterns, s, p, o) {
    const ret = [];
    for (let i = 0; i < triplePatterns.length; ++i) {
      const tp = triplePatterns[i];
      if ((s === null || Equals(tp.subject, s)) &&
          (p === null || Equals(tp.predicate, p)) &&
          (o === null || Equals(tp.object, o))) {
        ret.push(tp);
        triplePatterns.splice(i, 1);
        --i;
      }
    }
    return ret;
  }

  /** sort a list of triple (patterns), AKA arcs
   * Bubble rdf:type to the top
   * Sort remaining by predicate name.
   *   Since all have same subject and there are no repeated properties in
   *   FHIR/RDF, we can assume that that localeCompare will never return 0
   */
  static sortArcs (triplePatterns) {
    const ret = [];
    Array.prototype.push.apply(ret, FhirSparql.stealMatching(triplePatterns, null, Rdf.type, null));
    Array.prototype.push.apply(ret, triplePatterns.sort(
      (l, r) => FhirSparql.pStr(l.predicate).localeCompare(FhirSparql.pStr(r.predicate))
    ));
    return ret;
  }

  /** Construct an ArcTree for an arc and all arcs it reaches
   * Index variables in the same pass for efficiency.
   */
  static constructArcTree (triplePatterns, forArc, node, treeVars, referents) {
    // ArcTree's don't cross references (or canonical or ...?).
    if (forArc && forArc.predicate.value === 'http://hl7.org/fhir/reference') {
      const object = forArc.object;
      if (object.termType === 'Variable' && !referents.has(object.value))
        referents.add(object.value); // mark as referent
      return new ArcTree(forArc, []);
    }

    // Canonical order to match order in FhirQuery rule bodies
    const arcsOut = FhirSparql.sortArcs(FhirSparql.stealMatching(triplePatterns, node, null, null));

    const out = arcsOut.map(triplePattern => {
      const arcTree = FhirSparql.constructArcTree(triplePatterns, triplePattern, triplePattern.object, treeVars, referents);

      // Index the variables that connect the trees.
      (['subject', 'object']).forEach(pos => {
        const v = triplePattern[pos];
        // Ignore connections formed by IRIs, BNodes or Literals.
        if (v.termType === 'Variable') {
          if (!treeVars.has(v.value)) {
            treeVars.set(v.value, []);
          };
          treeVars.get(v.value).push({pos, arcTree});
        }
      });

      return arcTree;
    });

    return new ArcTree(forArc, out);
  }

  /** Stringize a predicate
   * Used to sort arcs queried from graphs.
   */
  static pStr (predicate) {
    return predicate.value
      ? '<' + predicate.value + '>'
      : '(' + predicate.items.map(item => FhirSparql.pStr(item) + (item.pathType || '')).join('/') + ')'; // TODO: not correct
  }
}

module.exports = {FhirSparql, ConnectingVariables, PredicateToShapeDecl, ArcTree, FhirPathExecution, ToTurtle};
