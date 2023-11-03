/* istanbul ignore file */
import * as ShExJ from 'shexj';

export class ShExVisitor {
  ctor_args: any[];
  constructor (...ctor_args: any[]) {
    this.ctor_args = ctor_args;

    // A lot of ShExVisitor's functions are the same. This creates them.
    /*
    const reusedMethods: Record<string, string[]> = {
      '_visitValue': [
        "visit@context", "visitBase", "visitInclude", "visitStart",
        "visitAbstract", "visitClosed",
        "visitInverse", "visitPredicate", "visitName", "visitId", "visitCode", "visitMin", "visitMax",
        "visitType", "visitNodeKind", "visitDatatype", "visitPattern", "visitFlags",
        "visitLength", "visitMinlength", "visitMaxlength",
        "visitMininclusive", "visitMinexclusive", "visitMaxinclusive", "visitMaxexclusive",
        "visitTotaldigits", "visitFractiondigits",
      ],
      '_visitList': [ "visitExtra", "visitAnnotations", ],
    };
    for (const reuseMe in reusedMethods) {
      const toFill = reusedMethods[reuseMe];
      for (const needed of toFill)
        if (!(needed in this)) {
          (this as Record<string, any>)[needed] = (this as Record<string, any>)[reuseMe];
        }
    }
    */
  }

  static isTerm (t: ShExJ.valueSetValue): t is ShExJ.objectValue {
    return typeof t !== "object" || "value" in t && Object.keys(t).reduce((r, k) => {
      return r === false ? r : ["value", "type", "language"].indexOf(k) !== -1;
    }, true);
  }

  static isShapeRef (expr: ShExJ.shapeExprOrRef): expr is ShExJ.shapeDeclRef {
    return typeof expr === "string" // test for JSON-LD @ID
  }
/*
  static visitMap (map, val) {
    const ret = {};
    Object.keys(map).forEach(function (item) {
      ret[item] = val(map[item]);
    });
    return ret;
  }
*/
  runtimeError (e: Error): void {
    throw e;
  }

  visitSchema (schema: ShExJ.Schema, ...args: any[]): any {
    const ret = { type: "Schema" };
    this._expect(schema, "type", "Schema");
    this._maybeSet(schema, ret, "Schema",
                   ["@context", "prefixes", "base", "imports", "startActs", "start", "shapes"],
                   ["_base", "_prefixes", "_index", "_sourceMap", "_locations"],
                   ...args
                  );
    return ret;
  }
/*
  visitPrefixes (prefixes, ...args: any[]) {
    return prefixes === undefined ?
      undefined :
      ShExVisitor.visitMap(prefixes, function (val) {
        return val;
      });
  }
*/
  visitIRI (i: ShExJ.IRIREF, ...args: any[]): any {
    return i;
  }

  visitImports (imports: ShExJ.IRIREF[], ...args: any[]): any {
    return imports.map((imp) => {
      return this.visitIRI(imp, args);
    });
  }

  visitStartActs (startActs: ShExJ.SemAct[], ...args: any[]): any {
    return startActs === undefined ?
      undefined :
      startActs.map((act) => {
        return this.visitSemAct(act, ...args);
      });
  }

  visitSemActs (semActs: ShExJ.SemAct[], ...args: any[]): any {
    if (semActs === undefined)
      return undefined;
    const ret: ShExJ.SemAct[] = []
    semActs.forEach(semAct => {
      ret.push(this.visitSemAct(semAct, ...args) as ShExJ.SemAct);
    });
    return ret;
  }

  visitSemAct (semAct: ShExJ.SemAct, ...args: any[]): any {
    const ret = { type: "SemAct" };
    this._expect(semAct, "type", "SemAct");

    this._maybeSet(semAct, ret, "SemAct",
                   ["name", "code"], null, ...args);
    return ret;
  }

  visitShapes (shapes: ShExJ.ShapeDecl[], ...args: any[]): any {
    if (shapes === undefined)
      return undefined;
    return shapes.map(
      shapeExpr =>
      this.visitShapeDecl(shapeExpr, ...args)
    );
  }

  visitShapeDecl (decl: ShExJ.ShapeDecl, ...args: any[]): any {
    return this._maybeSet(decl, { type: "ShapeDecl" }, "ShapeDecl",
                          ["id", "abstract", "restricts", "shapeExpr"], null, ...args);
  }

  visitShapeExpr (expr: ShExJ.shapeExprOrRef, ...args: any[]): any {
    if (ShExVisitor.isShapeRef(expr))
      return this.visitShapeRef(expr, ...args)
    switch (expr.type) {
    case "Shape": return this.visitShape(expr, ...args);
    case "NodeConstraint": return this.visitNodeConstraint(expr, ...args);
    case "ShapeAnd": return this.visitShapeAnd(expr, ...args);
    case "ShapeOr": return this.visitShapeOr(expr, ...args);
    case "ShapeNot": return this.visitShapeNot(expr, ...args);
    case "ShapeExternal": return this.visitShapeExternal(expr, ...args);
    default:
      throw Error("unexpected shapeExpr type: " + (expr as any).type);
    }
  }

  // visitValueExpr (expr: ShExJ.shapeExprOrRef, ...args: any[]):any {
  //   return this.visitShapeExpr(expr, ...args); // call potentially overloaded visitShapeExpr
  // }

  // _visitShapeGroup: visit a grouping expression (shapeAnd, shapeOr)
  _visitShapeGroup (expr: ShExJ.ShapeAnd | ShExJ.ShapeOr, ...args: any[]): any {
    this._testUnknownAttributes(expr, ["shapeExprs"], expr.type, this.visitShapeNot)
    const r = { type: expr.type } as ShExJ.ShapeAnd | ShExJ.ShapeOr;
    r.shapeExprs = expr.shapeExprs.map((nested) => {
      return this.visitShapeExpr(nested, ...args);
    });
    return r;
  }

  visitShapeAnd (expr: ShExJ.ShapeAnd, ...args: any[]): any { return this._visitShapeGroup(expr, ...args); }
  visitShapeOr (expr: ShExJ.ShapeOr, ...args: any[]): any { return this._visitShapeGroup(expr, ...args); }

  // _visitShapeNot: visit negated shape
  visitShapeNot (expr: ShExJ.ShapeNot, ...args: any[]): any {
    this._testUnknownAttributes(expr, ["shapeExpr"], "ShapeNot", this.visitShapeNot)
    const r = { type: expr.type } as ShExJ.ShapeNot;
    r.shapeExpr = this.visitShapeExpr(expr.shapeExpr, ...args);
    return r;
  }

  // ### `visitNodeConstraint` deep-copies the structure of a shape
  visitShape (shape: ShExJ.Shape, ...args: any[]): any {
    const ret = { type: "Shape" };
    this._expect(shape, "type", "Shape");

    this._maybeSet(shape, ret, "Shape",
                   [ "abstract", "extends",
                     "closed",
                     "expression", "extra", "semActs", "annotations"], null, ...args);
    return ret;
  }

  _visitShapeExprList (ext: /* ShExJ.Restricts | */ShExJ.shapeExpr[], ...args: any[]) {
    return ext.map((t) => {
      return this.visitShapeExpr(t, ...args);
    });
  }

  // visitRestricts (restricts: ShExJ.Restricts, ...args: any[]): any { return this._visitShapeExprList(restricts, ...args); }
  visitExtends (ext: ShExJ.shapeExpr[], ...args: any[]): any { return this._visitShapeExprList(ext, ...args); }

  // ### `visitNodeConstraint` deep-copies the structure of a shape
  visitNodeConstraint (nodeConstraint: ShExJ.NodeConstraint, ...args: any[]): any {
    const ret = { type: "NodeConstraint" };
    this._expect(nodeConstraint, "type", "NodeConstraint");

    this._maybeSet(nodeConstraint, ret, "NodeConstraint",
                   [ "nodeKind", "datatype", "pattern", "flags", "length",
                     "reference", "minlength", "maxlength",
                     "mininclusive", "minexclusive", "maxinclusive", "maxexclusive",
                     "totaldigits", "fractiondigits", "values", "annotations", "semActs"], null, ...args);
    return ret;
  }

  visitShapeRef (reference: ShExJ.shapeDeclRef, ...args: any[]): any {
    if (typeof reference !== "string")
      throw Error("visitShapeRef expected a string, not " + JSON.stringify(reference));
    return reference;
  }

  visitShapeExternal (expr: ShExJ.ShapeExternal, ...args: any[]): any {
    this._testUnknownAttributes(expr, [], "ShapeExternal", this.visitShapeNot)
    return { type: "ShapeExternal" };
  }

  // _visitGroup: visit a grouping expression (someOf or eachOf)
  _visitGroup (expr: ShExJ.OneOf | ShExJ.EachOf, ...args: any[]): any {
    const r = Object.assign(
      // pre-declare an id so it sorts to the top
      "id" in expr ? { id: null } : { },
      { type: expr.type }
    ) as ShExJ.OneOf | ShExJ.EachOf;
    r.expressions = expr.expressions.map((nested) => {
      return this.visitExpression(nested, ...args);
    });
    return this._maybeSet(expr, r, "expr",
                          ["id", "min", "max", "annotations", "semActs"], ["expressions"], ...args);
  }

  visitOneOf (expr: ShExJ.OneOf, ...args: any[]): any { return this._visitGroup(expr, ...args); }
  visitEachOf (expr: ShExJ.EachOf, ...args: any[]): any { return this._visitGroup(expr, ...args); }

  visitTripleConstraint (expr: ShExJ.TripleConstraint, ...args: any[]): any {
    return this._maybeSet(expr,
                          Object.assign(
                            // pre-declare an id so it sorts to the top
                            "id" in expr ? { id: null } : { },
                            { type: "TripleConstraint" }
                          ),
                          "TripleConstraint",
                          ["id", "inverse", "predicate", "valueExpr",
                           "min", "max", "annotations", "semActs"], null, ...args)
  }

  visitTripleExpr (expr: ShExJ.tripleExprOrRef, ...args: any[]): any {
    if (typeof expr === "string")
      return this.visitInclusion(expr);
    switch (expr.type) {
    case "TripleConstraint": return this.visitTripleConstraint(expr, ...args);
    case "OneOf": return this.visitOneOf(expr, ...args);
    case "EachOf": return this.visitEachOf(expr, ...args);
    default:
      throw Error("unexpected expression type: " + (expr as any).type);
    }
  }

  visitExpression (expr: ShExJ.tripleExprOrRef, ...args: any[]): any {
    return this.visitTripleExpr(expr, ...args); // call potentially overloaded visitTripleExpr
  }

  visitValues (values: ShExJ.valueSetValue[], ...args: any[]): any {
    return values.map((t) => {
      return ShExVisitor.isTerm(t) || t.type === "Language" ?
        t :
        this.visitStemRange(t, ...args);
    });
  }

  visitStemRange (t: ShExJ.IriStem | ShExJ.IriStemRange | ShExJ.LiteralStem | ShExJ.LiteralStemRange | ShExJ.LanguageStem | ShExJ.LanguageStemRange, ...args: any[]): any {
    // this._expect(t, "type", "IriStemRange");
    if (!("type" in t))
      this.runtimeError(Error("expected "+JSON.stringify(t)+" to have a 'type' attribute."));
    const stemRangeTypes = ["IriStem", "LiteralStem", "LanguageStem", "IriStemRange", "LiteralStemRange", "LanguageStemRange"];
    if (stemRangeTypes.indexOf(t.type) === -1)
      this.runtimeError(Error("expected type attribute '"+t.type+"' to be in '"+stemRangeTypes+"'."));
    let stem;
    if (ShExVisitor.isTerm(t)) {
      this._expect(t.stem, "type", "Wildcard");
      stem = { type: t.type, stem: { type: "Wildcard" } };
    } else {
      stem = { type: t.type, stem: t.stem };
    }
    if ((t as ShExJ.IriStemRange | ShExJ.LiteralStemRange | ShExJ.LanguageStemRange).exclusions) {
      (stem as any).exclusions = (t as ShExJ.IriStemRange | ShExJ.LiteralStemRange | ShExJ.LanguageStemRange).exclusions.map((c) => {
        return this.visitExclusion(c, ...args);
      });
    }
    return stem;
  }

  visitExclusion (c: ShExJ.iriRangeExclusion | ShExJ.literalRangeExclusion | ShExJ.languageRangeExclusion, ...args: any[]): any {
    if (!ShExVisitor.isTerm(c)) {
      // this._expect(c, "type", "IriStem");
      if (!("type" in c))
        this.runtimeError(Error("expected "+JSON.stringify(c)+" to have a 'type' attribute."));
      const stemTypes = ["IriStem", "LiteralStem", "LanguageStem"];
      if (stemTypes.indexOf(c.type) === -1)
        this.runtimeError(Error("expected type attribute '"+c.type+"' to be in '"+stemTypes+"'."));
      return { type: c.type, stem: c.stem };
    } else {
      return c;
    }
  }

  visitInclusion (inclusion: ShExJ.tripleExprRef, ...args: any[]): any {
    if (typeof inclusion !== "string")
      throw Error("visitInclusion expected a string, not " + JSON.stringify(inclusion));
    return inclusion;
  }

  /** internal generic handler for visiting ShExJ structure members
   * treats members as keys on object
   */
  _maybeSet (obj: object, ret: object, context: string, members: string[], ignore: string[] | null, ...args: any[]) {
    this._testUnknownAttributes(obj, ignore ? members.concat(ignore) : members, context, this._maybeSet)
    members.forEach((member) => {
      const methodName = "visit" + member.charAt(0).toUpperCase() + member.slice(1);
      if (member in obj) {
        const f = (this as any)[methodName];
        if (typeof f !== "function") {
          throw Error(methodName + " not found in Visitor");
        }
        const t = f.call(this, (obj as any)[member], ...args);
        if (t !== undefined) {
          (ret as any)[member] = t;
        }
      }
    });
    return ret;
  }

  _visitValue (v: string, ...args: any[]): any {
    return v;
  }

  // "visit@context",
  visitBase (x: string, ...args: any[]): any { return this._visitValue(x, ...args); }
  visitInclude (x: string, ...args: any[]): any { return this._visitValue(x, ...args); }
  visitStart (x: string, ...args: any[]): any { return this._visitValue(x, ...args); }
  visitAbstract (x: string, ...args: any[]): any { return this._visitValue(x, ...args); }
  visitClosed (x: string, ...args: any[]): any { return this._visitValue(x, ...args); }
  visitInverse (x: string, ...args: any[]): any { return this._visitValue(x, ...args); }
  visitPredicate (x: string, ...args: any[]): any { return this._visitValue(x, ...args); }
  visitName (x: string, ...args: any[]): any { return this._visitValue(x, ...args); }
  visitId (x: string, ...args: any[]): any { return this._visitValue(x, ...args); }
  visitCode (x: string, ...args: any[]): any { return this._visitValue(x, ...args); }
  visitMin (x: string, ...args: any[]): any { return this._visitValue(x, ...args); }
  visitMax (x: string, ...args: any[]): any { return this._visitValue(x, ...args); }
  visitType (x: string, ...args: any[]): any { return this._visitValue(x, ...args); }
  visitNodeKind (x: string, ...args: any[]): any { return this._visitValue(x, ...args); }
  visitDatatype (x: string, ...args: any[]): any { return this._visitValue(x, ...args); }
  visitPattern (x: string, ...args: any[]): any { return this._visitValue(x, ...args); }
  visitFlags (x: string, ...args: any[]): any { return this._visitValue(x, ...args); }
  visitLength (x: string, ...args: any[]): any { return this._visitValue(x, ...args); }
  visitMinlength (x: string, ...args: any[]): any { return this._visitValue(x, ...args); }
  visitMaxlength (x: string, ...args: any[]): any { return this._visitValue(x, ...args); }
  visitMininclusive (x: string, ...args: any[]): any { return this._visitValue(x, ...args); }
  visitMinexclusive (x: string, ...args: any[]): any { return this._visitValue(x, ...args); }
  visitMaxinclusive (x: string, ...args: any[]): any { return this._visitValue(x, ...args); }
  visitMaxexclusive (x: string, ...args: any[]): any { return this._visitValue(x, ...args); }
  visitTotaldigits (x: string, ...args: any[]): any { return this._visitValue(x, ...args); }
  visitFractiondigits (x: string, ...args: any[]): any { return this._visitValue(x, ...args); }

  _visitList (l: ShExJ.IRIREF[] | ShExJ.Annotation[], ...args: any[]) {
    return l.slice();
  }

  visitExtra (extra: ShExJ.IRIREF[]) { return this._visitList(extra); }
  visitAnnotations (annotations: ShExJ.Annotation[]) { return this._visitList(annotations); }

  _testUnknownAttributes (obj: object, expected: string[], context: string, captureFrame: Function) {
    const unknownMembers = Object.keys(obj).reduce(function (ret, k) {
      return k !== "type" && expected.indexOf(k) === -1
        //@ts-ignore
        ? ret.concat(k)
        : ret;
    }, []);
    if (unknownMembers.length > 0) {
      const e = Error("unknown propert" + (unknownMembers.length > 1 ? "ies" : "y") + ": " +
                      unknownMembers.map(function (p) {
                        return "\"" + p + "\"";
                      }).join(",") +
                      " in " + context + ": " + JSON.stringify(obj));
      Error.captureStackTrace(e, captureFrame);
      throw e;
    }
  }

  _expect (o: any, p: string, v: any) {
    if (!(p in o))
      this.runtimeError(Error("expected "+JSON.stringify(o)+" to have a ."+p));
    if (arguments.length > 2 && o[p] !== v)
      this.runtimeError(Error("expected "+o[p]+" to equal "+v));
  }
}

// The ShEx Vistor is here to minimize deps for ShExValidator.
/** create indexes for schema
 */
export function index (schema: ShExJ.Schema) {
  let index = {
    shapeExprs: {},
    tripleExprs: {}
  };
  let v = new ShExVisitor();

  let oldVisitExpression = v.visitTripleExpr;
  v.visitTripleExpr = function (expression, ...args: any[]) {
    if (typeof expression === "object" && "id" in expression)
      //@ts-ignore
      index.tripleExprs[expression.id] = expression;
    return oldVisitExpression.call(v, expression, ...args);
  };

  let oldVisitShapeDecl = v.visitShapeDecl;
  v.visitShapeDecl = function (shapeExpr, ...args: any[]) {
    if (typeof shapeExpr === "object" && "id" in shapeExpr)
      //@ts-ignore
      index.shapeExprs[shapeExpr.id] = shapeExpr;
    return oldVisitShapeDecl.call(v, shapeExpr, ...args);
  };

  v.visitSchema(schema);
  return index;
}
