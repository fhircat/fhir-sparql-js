const {Visitor: ShExVisitor} = require('./ShExVisitor');
const {Term} = require('../lib/RdfUtils');
const {ArcTree} = require('../lib/ArcTree');
const {ShExValidator} = require("@shexjs/validator");

const NoMatch = Term.blessSparqlJs({termType: 'NamedNode', value: 'should://never/match'});

/**
 * test whether an ArcTree is completely covered by a schema.
 *
 * Note that only *some* children of a ShapeAnd or EachOf must account for the ArcTree.
 * strategy: look for any children or extends, even Nots, than can match arcTree.
 *
 * Weakness: currently accepts if any {con,dis}junect accepts. Should accept only of all conjuncts that mention X accept X.
 */
class ArcTreeFitsInShapeExpr extends ShExVisitor {
  constructor (shex, ...ctor_args) {
    super(...ctor_args);
    this.shex = shex;
    this.tested = new Map(); // straightforward results cache
  }

  visitShapeDecl(decl, arcTree, ...args) {
    let testedShapeExprs = this.tested.get(arcTree);
    if (!testedShapeExprs || true) {
      testedShapeExprs = new Map();
      this.tested.set(arcTree, testedShapeExprs);
    }

    let shapeExprResults = testedShapeExprs.get(arcTree);
    if (!shapeExprResults || true) {
      shapeExprResults = this.visitShapeExpr(decl.shapeExpr, arcTree, ...args);
      testedShapeExprs.set(arcTree, shapeExprResults);
    }

    return shapeExprResults;
  }

  visitShapeRef(reference, arcTree, ...args) {
    const shapeDecl = this.shex.shapes.find(decl => decl.id === reference);
    return this.visitShapeDecl(shapeDecl, arcTree, ...args);
  }

  visitShapeAnd (expr, arcTree, ...args) {
    return !!expr.shapeExprs.find(nested => this.visitShapeExpr(nested, arcTree, ...args));
  }

  visitShapeOr (expr, arcTree, ...args) {
    return !!expr.shapeExprs.find(nested => this.visitShapeExpr(nested, arcTree, ...args));
  }

  visitShapeNot (expr, arcTree, ...args) {
    return this.visitShapeExpr(expr.shapeExpr, arcTree, ...args);
  }

  visitShape (shape, arcTree, ...args) {
    if (shape.extends)
      for (const ext of shape.extends)
        if (this.visitShapeExpr(ext, arcTree, ...args))
          return true;
    return shape.expression ? this.visitTripleExpr(shape.expression, arcTree, shape.closed, ...args) : true;
  }

  visitNodeConstraint (nc, arcTree, closed, ...args) { // don't bother visiting NodeConstraints
    let focus = arcTree.tp.subject;
    if (["BlankNode", "Variable"].indexOf(focus.termType) !== -1)
      return true; // in SPARQL context, vars and bnodes match anything
    // Otherwise, perform regular NodeConstraint validation on arcTree.subject.
    // horrible js hack for efficiency requires intimate knowledge of internals:
    const res = ShExValidator.prototype.validateNodeConstraint.call({evaluateShapeExprSemActs: (ncRet, nc, focus, label) => []}, focus, nc, {label: "asdf"});
    // less horrible hack uses published API:
    // const validator = new ShExValidator(this.shex, undefined, undefined);
    // const res = validator.validateNodeConstraint(focus, nc, {label: "asdf"});
    return !res.errors;
  }

  visitEachOf (expr, arcTree, closed, ...args) {
    return !!expr.expressions.find(nested => this.visitTripleExpr(nested, arcTree, closed, ...args));
  }

  visitOneOf (expr, arcTree, closed, ...args) {
    return !!expr.expressions.find(nested => this.visitTripleExpr(nested, arcTree, closed, ...args));
  }

  visitTripleConstraint(expr, arcTree, _closed, ...args) {
    // TODO: !closed
    // TODO: shape paths
    // hack: special case (rdf:first/rdf:rest)*/rdf:first
    let p = arcTree.tp.predicate;
    if (p.type === "path" && p.pathType === "/") {
      const t = p.items.find(item => item.pathType !== "*"); // skip past '*'s
      if (t)
        p = t;
      else
        throw Error(`need support for ${JSON.stringify(p)}`);
    }
    if (expr.predicate !== p.value) // TODO: expr.min === 0
      return false;
    if (!expr.valueExpr)
      return arcTree.out.length === 0;
    if (arcTree.out.length === 0) {
      // valueExpr could have NodeConstraints or Shapes with min card of 0.
      // Make an unmatchable Triple. This is kind of a hack to avoid creating a union type.
      return this.visitShapeExpr(expr.valueExpr, new ArcTree({
        subject: arcTree.tp.object,
        predicate: NoMatch,
        object: NoMatch
      }, []), ...args);
    }
    return !arcTree.out.find(childArcTree => {
      return !this.visitShapeExpr(expr.valueExpr, childArcTree, ...args);
    });
  }
}

module.exports = {ArcTreeFitsInShapeExpr};
