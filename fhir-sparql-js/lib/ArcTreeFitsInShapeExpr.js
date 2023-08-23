const {Visitor: ShExVisitor} = require('./ShExVisitor');

/**
 * test whether an ArcTree is completely covered by a schema.
 *
 * Note that only *some* children of a ShapeAnd or EachOf must account for the ArcTree.
 * strategy: look for any children or extends, even Nots, than can match arcTree.
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
    return expr.shapeExprs.find(nested => this.visitShapeExpr(nested, arcTree, ...args));
  }

  visitShapeOr (expr, arcTree, ...args) {
    return expr.shapeExprs.find(nested => this.visitShapeExpr(nested, arcTree, ...args));
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

  visitNodeConstraint (shape, arcTree, closed, ...args) { // don't bother visiting NodeConstraints
    return true;
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
    if (arcTree.out.length === 0)
      return true;
    if (!expr.valueExpr)
      return false;
    return !arcTree.out.find(childArcTree => {
      return !this.visitShapeExpr(expr.valueExpr, childArcTree, ...args);
    });
  }
}

module.exports = {ArcTreeFitsInShapeExpr};
