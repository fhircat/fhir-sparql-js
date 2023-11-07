import {ShExVisitor} from './ShExVisitor';
import {Term} from './RdfUtils';
import {ArcTree} from './ArcTree';
import * as ShExJ from 'shexj';
import {shapeExprTest} from "@shexjs/term/shexv";
import * as SparqlJs from "sparqljs";
import type {Term as RdfJsTerm} from 'rdf-js';

import {ShExValidator, ShapeExprValidationContext} from "@shexjs/validator";
//@ts-ignore
const NoMatch = Term.blessSparqlJs({termType: 'NamedNode', value: 'should://never/match'});

/**
 * test whether an ArcTree is completely covered by a schema.
 *
 * Note that only *some* children of a ShapeAnd or EachOf must account for the ArcTree.
 * strategy: look for any children or extends, even Nots, than can match arcTree.
 *
 * Weakness: currently accepts if any {con,dis}junct accepts. Should accept only if all conjuncts that mention X accept X.
 */
export class ArcTreeFitsInShapeExpr extends ShExVisitor {
  shex: ShExJ.Schema;
  tested: Map<ArcTree, Map<ShExJ.ShapeDecl, shapeExprTest> >;
  constructor (shex: ShExJ.Schema, ...ctor_args: any[]) {
    if (!shex.shapes)
      throw Error('construct ArcTreeFitsInShapeExpr with a ShEx schema with shapes');
    super(...ctor_args);
    this.shex = shex;
    this.tested = new Map(); // straightforward results cache
  }

  visitShapeDecl(decl: ShExJ.ShapeDecl, arcTree: ArcTree, ...args: any[]) {
    let testedShapeExprs = this.tested.get(arcTree);
    if (!testedShapeExprs) {
      testedShapeExprs = new Map();
      this.tested.set(arcTree, testedShapeExprs);
    }

    let shapeExprResults = testedShapeExprs.get(decl);
    if (!shapeExprResults) {
      shapeExprResults = this.visitShapeExpr(decl.shapeExpr, arcTree, ...args);
      testedShapeExprs.set(decl, shapeExprResults!); // not sure how this could be undefined
    }

    return shapeExprResults;
  }

  visitShapeRef(reference: ShExJ.shapeDeclRef, arcTree: ArcTree, ...args: any[]) {
    const shapeDecl = this.shex.shapes!.find(decl => decl.id === reference);
    if (!shapeDecl)
      throw Error(`Shape ${reference} not found in ${this.shex.shapes!.map(decl => decl.id).join(', ')}`);
    return this.visitShapeDecl(shapeDecl, arcTree, ...args);
  }

  visitShapeAnd (expr: ShExJ.ShapeAnd, arcTree: ArcTree, ...args: any[]) {
    return !!expr.shapeExprs.find(nested => this.visitShapeExpr(nested, arcTree, ...args));
  }

  visitShapeOr (expr: ShExJ.ShapeOr, arcTree: ArcTree, ...args: any[]) {
    return !!expr.shapeExprs.find(nested => this.visitShapeExpr(nested, arcTree, ...args));
  }

  visitShapeNot (expr: ShExJ.ShapeNot, arcTree: ArcTree, ...args: any[]) {
    return this.visitShapeExpr(expr.shapeExpr, arcTree, ...args);
  }

  visitShape (shape: ShExJ.Shape, arcTree: ArcTree, ...args: any[]) {
    if (shape.extends)
      for (const ext of shape.extends)
        if (this.visitShapeExpr(ext, arcTree, ...args))
          return true;
    return shape.expression ? this.visitTripleExpr(shape.expression, arcTree, shape.closed, ...args) : true;
  }

  visitNodeConstraint (nc: ShExJ.NodeConstraint, arcTree: ArcTree, closed: Boolean, ...args: any[]) { // don't bother visiting NodeConstraints
    let focus = arcTree.tp.subject;
    if (["BlankNode", "Variable"].indexOf(focus.termType) !== -1)
      return true; // in SPARQL context, vars and bnodes match anything
    // Otherwise, perform regular NodeConstraint validation on arcTree.subject.
    // horrible js hack for efficiency requires intimate knowledge of internals:
    const res = ShExValidator.prototype.validateNodeConstraint.call({evaluateShapeExprSemActs: (ncRet: shapeExprTest, nc: ShExJ.NodeConstraint, focus: RdfJsTerm, label: ShExJ.Shape) => []}, focus, nc, new ShapeExprValidationContext(null, "asdf"));
    // less horrible hack uses published API:
    // const validator = new ShExValidator(this.shex, undefined, undefined);
    // const res = validator.validateNodeConstraint(focus, nc, {label: "asdf"});
    return !(res as any).errors;
  }

  visitEachOf (expr: ShExJ.EachOf, arcTree: ArcTree, closed: Boolean, ...args: any[]) {
    return !!expr.expressions.find(nested => this.visitTripleExpr(nested, arcTree, closed, ...args));
  }

  visitOneOf (expr: ShExJ.OneOf, arcTree: ArcTree, closed: Boolean, ...args: any[]) {
    return !!expr.expressions.find(nested => this.visitTripleExpr(nested, arcTree, closed, ...args));
  }

  visitTripleConstraint(expr: ShExJ.TripleConstraint, arcTree:ArcTree, _closed: Boolean, ...args: any[]) {
    // TODO: !closed
    // TODO: shape paths
    // hack: special case (rdf:first/rdf:rest)*/rdf:first
    let p = arcTree.tp.predicate as SparqlJs.PropertyPath; // might be SparqlJs.IriTerm
    if (p.type === "path" && p.pathType === "/") {
      const t = p.items.find(item => (item as SparqlJs.PropertyPath).pathType !== "*"); // skip past '*'s
      if (t)
        p = t as SparqlJs.PropertyPath;
      else
        throw Error(`need support for ${JSON.stringify(p)}`);
    }
    if (expr.predicate !== (p as unknown as SparqlJs.IriTerm).value) // TODO: expr.min === 0
      return false;
    if (!expr.valueExpr)
      return arcTree.out.length === 0;
    if (arcTree.out.length === 0) {
      // valueExpr could have NodeConstraints or Shapes with min card of 0.
      // Make an unmatchable Triple. This is kind of a hack to avoid creating a union type.
      return this.visitShapeExpr(expr.valueExpr, new ArcTree({
        //@ts-ignore
        subject: arcTree.tp.object, predicate: NoMatch, object: NoMatch
      }, []), ...args);
    }
    return !arcTree.out.find(childArcTree => {
      return !this.visitShapeExpr(expr.valueExpr!, childArcTree, ...args);
    });
  }
}
