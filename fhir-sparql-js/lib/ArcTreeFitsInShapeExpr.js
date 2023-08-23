const {Visitor: ShExVisitor} = require('./ShExVisitor');

/**
 * test whether an ArcTree is completely covered by a schema.
 *
 * Note that only *some* children of a ShapeAnd or EachOf must account for the ArcTree.
 */
class ArcTreeFitsInShapeExpr extends ShExVisitor {
  constructor (shex, ...ctor_args) {
    super(...ctor_args);
    this.tested = new Map(); // straightforward results cache
  }

  visitShapeAnd (expr, arcTree, ...args) {
    return expr.shapeExprs.find(nested => this.visitShapeExpr(nested, arcTree, ...args));
  }

  visitShapeOr (expr, arcTree, ...args) {
    return expr.shapeExprs.find(nested => this.visitShapeExpr(nested, arcTree, ...args));
  }

  visitShapeNot (expr, arcTree, ...args) {
    return this.visitShapeExpr(expr.shapeExpr, arcTree, ...args);
  }

  visitShape (shape, arcTree, ...args) {
    return this.visitTripleExpr(shape.expression, arcTree, shape.closed, ...args);
  }

  visitNodeConstraint (shape, arcTree, closed, ...args) { // don't bother visiting NodeConstraints
    return true;
  }

  visitEachOf (expr, arcTree, closed, ...args) {
    return !!expr.expressions.find(nested => this.visitTripleExpr(nested, arcTree, closed, ...args));
  }

  visitOneOf (expr, arcTree, closed, ...args) {
    return !!expr.expressions.find(nested => this.visitTripleExpr(nested, arcTree, closed, ...args));
  }

  visitTripleConstraint(expr, arcTree, closed, ...args) {
    // TODO: !closed
    if (expr.predicate !== arcTree.tp.predicate.value) // TODO: expr.min === 0
      return false;
    if (arcTree.out.length === 0)
      return true;
    if (!expr.valueExpr)
      return false;
    return !arcTree.out.find(childArcTree => {
      return !this.test(childArcTree, expr.valueExpr, ...args);
    });
  }

  test (arcTree, shapeExpr, ...args) {
    let testedShapeExprs = this.tested.get(arcTree);
    if (!testedShapeExprs || true) {
      testedShapeExprs = new Map();
      this.tested.set(arcTree, testedShapeExprs);
    }

    let shapeExprResults = testedShapeExprs.get(arcTree);
    if (!shapeExprResults || true) {
      shapeExprResults = this.visitShapeExpr(shapeExpr, arcTree, ...args);
      testedShapeExprs.set(arcTree, shapeExprResults);
    }

    return shapeExprResults;
  }
}

module.exports = {ArcTreeFitsInShapeExpr};
