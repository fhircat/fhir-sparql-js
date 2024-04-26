/** FhirJsonToTurtle - convert FHIR JSON to FHIR RDF written out as Turtle
 *
 * Status:
 *   Implementation has been driven by fhircat use cases.
 *
 * Todo:
 *   Test against FHIR examples.
 *   Move to separate npm module.
 */

// Types needed to for primitive data representation
declare type TypeRepresentation = { label: string; microparse?: (x: string) => string; };
declare type TypeReprMap = {[key: string]: TypeRepresentation};

export class FhirJsonToTurtle {

  // Parse the polymorphic fhir:data datatypes.
  static parseDateType (x: string): string {
    const m = x.match(/([0-9]([0-9]([0-9][1-9]|[1-9]0)|[1-9]00)|[1-9]000)(-(0[1-9]|1[0-2])(-(0[1-9]|[1-2][0-9]|3[0-1])(T([01][0-9]|2[0-3]):[0-5][0-9]:([0-5][0-9]|60)(\.[0-9]{1,9})?)?)?(Z|(\+|-)((0[0-9]|1[0-3]):[0-5][0-9]|14:00)?)?)?/);
    if (!m) throw new Error(`Couldn\'t parse date from "${x}"`);
    return m[4]
      ? 'dateTime'
      : m[3]
      ? 'date'
      : m[2]
      ? 'gYearMonth'
      : 'gYear'
  }

  // FHIR primitive datatypes
  static PrimitiveTypes: TypeReprMap = {
    AnyURI  : { label: 'anyURI'},

    Base64Binary: { label: 'base64Binary' },
    Boolean: { label: 'boolean' },
    Canonical: { label: 'canonical' },
    Code: { label: 'code' },
    Date: { label: 'date', microparse: FhirJsonToTurtle.parseDateType },
    DateTime: { label: 'dateTime', microparse: FhirJsonToTurtle.parseDateType },
    Decimal: { label: 'decimal' },
    Id: { label: 'id' },
    Instant: { label: 'instant' },
    Integer: { label: 'integer' },
    Integer64: { label: 'integer64' },
    Markdown: { label: 'markdown' },
    Oid: { label: 'oid' },
    String: { label: 'string' },
    PositiveInt: { label: 'positiveInt' },
    Time: { label: 'time' },
    UnsignedInt: { label: 'unsignedInt' },
    Uri: { label: 'uri' },
    Url: { label: 'url' },
    Uuid: { label: 'uuid' },
  };

  // FHIR complex datatypes
  static ComplexTypes: TypeReprMap = {
    Address: { label: 'Address' },
    Age: { label: 'Age' },
    Annotation: { label: 'Annotation' },
    Attachment: { label: 'Attachment' },
    CodeableConcept: { label: 'CodeableConcept' },
    CodeableReference: { label: 'CodeableReference' },
    Coding: { label: 'Coding' },
    ContactPoint: { label: 'ContactPoint' },
    Count: { label: 'Count' },
    Distance: { label: 'Distance' },
    Duration: { label: 'Duration' },
    HumanName: { label: 'HumanName' },
    Identifier: { label: 'Identifier' },
    Money: { label: 'Money' },
    Period: { label: 'Period' },
    Quantity: { label: 'Quantity' },
    Range: { label: 'Range' },
    Ratio: { label: 'Ratio' },
    RatioRange: { label: 'RatioRange' },
    Reference: { label: 'Reference' },
    SampledData: { label: 'SampledData' },
    Signature: { label: 'Signature' },
    Timing: { label: 'Timing' },
  };

  // FHIR complex datatypes not appearing https://build.fhir.org/resource.html#Meta
  static SpecialTypes: TypeReprMap = {
    Dosage: { label: 'Dosage' }, // https://build.fhir.org/dosage.html#Dosage
    Meta: { label: 'Meta' }, // https://build.fhir.org/resource.html#Meta
  }

  // All fo the above FHIR datatypes
  static AllDatatypes: TypeReprMap = {
    ...FhirJsonToTurtle.PrimitiveTypes,
    ...FhirJsonToTurtle.ComplexTypes,
    ...FhirJsonToTurtle.SpecialTypes,
  }

  // TODO: Revisit if needed after adding FHIR example tests.
  static TypedAttributes: TypeReprMap = {
    lastUpdated: FhirJsonToTurtle.PrimitiveTypes.DateTime,
    // effectiveDateTime: FhirJsonToTurtle.PrimitiveTypes.DateTime,
    issued: FhirJsonToTurtle.PrimitiveTypes.DateTime,
    source: { label: 'anyURI'},
    system: { label: 'anyURI'},
  };

  // TODO: Revisit if needed after adding FHIR example tests.
  static PolymorphicAttributes = {
    // versionId: { label: 'versionId' },
    value: { label: 'value' },
    effective: { label: 'effective' },
    issued: { label: 'issued' },
  }

  static Ns = {
    fhir: 'http://hl7.org/fhir/',
    xsd: 'http://www.w3.org/2001/XMLSchema#',
  }

  // IRI stems for select terminology systems
  static SystemBases: {[key: string]: string} = {
    'http://terminology.hl7.org/CodeSystem/observation-category': 'http://terminology.hl7.org/CodeSystem/observation-category/',
    'http://loinc.org': 'http://loinc.org/rdf#',
    'http://snomed.info/sct': 'http://snomed.info/id/',
  }

  /**
   * Serialize `resource` as an RDF 1.1 Turtle string
   * @param resource FHIR Resource as JSON object
   */
  prettyPrint (resource: {[key: string]: any}): string { // TODO: type FHIR Resources
    const root = resource.id
          ? `<${resource.id}>`
          : '[]';
    const namespacePrefixes = new Set(['fhir', 'xsd']);

    // Lines of output Turtle string
    const out: Array<string> = [];

    // PREFIXes
    for (const p of namespacePrefixes) {
      // @ts-ignore
      out.push(`PREFIX ${p}: <${FhirJsonToTurtle.Ns[p]}>`)
    }
    out.push('');

    // node, type, nodeRole
    const skips = new Set(['resourceType']); // handled here
    out.push(`${root} a fhir:${resource.resourceType};`)
    out.push(`  fhir:nodeRole fhir:treeRoot;`)

    // RDF resources which need a type arc
    const typedRdfNodes: {[key: string]: string} = {};
    Array.prototype.push.apply(out, this._visit('  ', resource, skips, true, typedRdfNodes));

    // Add required type arcs
    if (Object.keys(typedRdfNodes).length) {
      out.push('');
      out.push('# Triples not in FHIR Resource:');
      Array.prototype.push.apply(out, Object.values(typedRdfNodes))
    }

    // Return Turtle as a string
    return out.map(l => l + '\n').join('');
  }

  /**
   * recursively walk through FHIR Resource as JSON object
   * @param leader string with which to indent new lines
   * @param resourceComponent initially, the FHIR Resource, then each object contained therein
   * @param skips which FHIR elements don't beget an RDF triple
   * @param outer whether `resourceComponent` is an outermost resource
   * @param typedRdfNodes RDF resources which need a type arc
   */
  _visit (leader: string, resourceComponent: {[key: string]: any}, skips: Set<string>, outer: boolean, typedRdfNodes: {[key: string]: string}) {
    const ret: Array<string> = [];

    // Walk the current FHIR Resource component
    const entries = Object.entries(resourceComponent);
    for (let entryNo = 0; entryNo < entries.length; ++entryNo) {
      const [fhirElementName, value] = entries[entryNo];

      // Calculate predicate and type
      const polymorphicAttr = Object.keys(FhirJsonToTurtle.PolymorphicAttributes).find(attr => fhirElementName.startsWith(attr));
      const [property, overloadedType] = polymorphicAttr ? [polymorphicAttr, fhirElementName.substring(polymorphicAttr.length)] : [fhirElementName, null];

      // Calculate punctuation for nth element in resourceComponent
      const punct = entryNo < entries.length - 1
            ? ';'
            : outer
            ? '.'
            : '';

      // Add appropriate triple for current element.
      if (skips.has(property)) {
        // skip it
      } else if (typeof value === 'boolean' || typeof value === 'number') {
        ret.push(`${leader}fhir:value [ fhir:v ${value} ]${punct}`)
      } else if (typeof value === 'string') {

        // Elements with string values serialized here.
        if (property === 'system') {
          // system gets an in-line type-arc as the object is a bnode.
          const base = FhirJsonToTurtle.SystemBases[value];
          if (base)
            ret.push(`${leader}a <${base}${resourceComponent.code}>;`)
        } else if (property === 'reference') {
          // reference's type is added to typedRdfNodes to be appended to document.
          if (!(value in typedRdfNodes))
            typedRdfNodes[value] = `<../${value}> a fhir:${value.substring(0, value.indexOf('/'))}.`
          ret.push(`${leader}fhir:OBSOLETE_link <../${value}>;`)
        }

        let valueStr = null;
        const typed = FhirJsonToTurtle.TypedAttributes[property] || (overloadedType === null ? undefined : FhirJsonToTurtle.AllDatatypes[overloadedType]);
        if (typed) {
          const dt = typed.microparse
                ? typed.microparse(value)
                : typed.label
          valueStr = this.quote(value) + '^^xsd:' + dt;
        } else {
          valueStr = this.quote(value);
        }
        if (overloadedType) { // TODO: not sure if this is necessary
          ret.push(`${leader}fhir:${property} [`);
          ret.push(`${leader}  a fhir:${overloadedType};`);
          ret.push(`${leader}  fhir:v ${valueStr}`);
          ret.push(`${leader}]${punct}`);
        } else {
          if (property === 'reference') {
            ret.push(`${leader}fhir:${property} [`);
            ret.push(`${leader}  fhir:link <../${value}>;`)
            ret.push(`${leader}  fhir:v ${valueStr}`);
            ret.push(`${leader}]${punct}`);
          } else {
            ret.push(`${leader}fhir:${property} [ fhir:v ${valueStr} ]${punct}`);
          }
        }
      } else if (Array.isArray(value)) {

        // Elements with array values are iterated across.
        // TODO: leverage string serialization above?
        for (let i = 0; i < value.length; ++i) {
          if (typeof value[i] === 'string') {
            if (i === 0)
              ret.push(`${leader}fhir:${property} (`);
            ret.push(`${leader}  [ fhir:v ${this.quote(value[i])} ]`)
            if (i === value.length - 1)
              ret.push(`${leader})${punct}`);
          } else {
            ret.push(i === 0 ? `${leader}fhir:${property} ( [` : `${leader}[`)
            Array.prototype.push.apply(ret, this._visit(leader + '  ', value[i], skips, false, typedRdfNodes));
            ret.push(i < value.length - 1 ? `${leader}]` : `${leader}] )${punct}`)
          }
        }
      } else if (typeof value === 'object') {

        // Nested components are handled with recursive call to this._visit
        ret.push(`${leader}fhir:${property} [`)
        if (overloadedType) {
          ret.push(`${leader}  a fhir:${overloadedType};`);
        }
        Array.prototype.push.apply(ret, this._visit(leader + '  ', value, skips, false, typedRdfNodes));
        ret.push(`${leader}]${punct}`)
      } else {

        // Anything else is an error (is it even JSON?)
        throw Error(`visit not expecting ${JSON.stringify(value)}`);
      }
    }
    return ret;
  }

  quote (str: string): string {
    return '"' + str.replace(/\\/g, '\\\\').replace(/\n/g, '\\n').replace(/\"/g, '\\"') + '"';
  }
}
