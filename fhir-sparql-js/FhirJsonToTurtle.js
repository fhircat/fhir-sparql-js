/** FhirJsonToTurtle - convert FHIR JSON to FHIR RDF written out as Turtle
 * TODO: move to its own module
 */

class FhirJsonToTurtle {

  static parseDateType (x) {
    const m = x.match(/([0-9]([0-9]([0-9][1-9]|[1-9]0)|[1-9]00)|[1-9]000)(-(0[1-9]|1[0-2])(-(0[1-9]|[1-2][0-9]|3[0-1])(T([01][0-9]|2[0-3]):[0-5][0-9]:([0-5][0-9]|60)(\.[0-9]{1,9})?)?)?(Z|(\+|-)((0[0-9]|1[0-3]):[0-5][0-9]|14:00)?)?)?/);
    return m[4]
      ? 'dateTime'
      : m[3]
      ? 'date'
      : m[2]
      ? 'gYearMonth'
      : 'gYear'
  }

  static PrimitiveTypes = {
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

  static ComplexTypes = {
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

  static SpecialTypes = {
    Dosage: { label: 'Dosage' },
    Meta: { label: 'Meta' },
  }

  static AllDatatypes = {
    ...FhirJsonToTurtle.PrimitiveTypes,
    ...FhirJsonToTurtle.ComplexTypes,
    ...FhirJsonToTurtle.SpecialTypes,
  }

  static TypedAttributes = {
    lastUpdated: FhirJsonToTurtle.PrimitiveTypes.DateTime,
    // effectiveDateTime: FhirJsonToTurtle.PrimitiveTypes.DateTime,
    issued: FhirJsonToTurtle.PrimitiveTypes.DateTime,
    source: { label: 'anyURI'},
    system: { label: 'anyURI'},
  };

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

  static SystemBases = {
    'http://terminology.hl7.org/CodeSystem/observation-category': 'http://terminology.hl7.org/CodeSystem/observation-category/',
    'http://loinc.org': 'http://loinc.org/rdf#',
    'http://snomed.info/sct': 'http://snomed.info/id/',
  }

  prettyPrint (resource) {
    const root = resource.id
          ? `<${resource.id}>`
          : '[]';
    const namespacePrefixes = new Set(['fhir', 'xsd']);

    // PREFIXes
    const out = [];
    for (const p of namespacePrefixes) {
      out.push(`PREFIX ${p}: <${FhirJsonToTurtle.Ns[p]}>`)
    }
    out.push('');

    // node, type, nodeRole
    const skips = new Set(['resourceType']); // handled here
    out.push(`${root} a fhir:${resource.resourceType};`)
    out.push(`  fhir:nodeRole fhir:treeRoot;`)

    const types = [];
    Array.prototype.push.apply(out, this.visit('  ', resource, skips, true, types))
    if (Object.keys(types.length)) {
      out.push('');
      out.push('# Triples not in FHIR Resource:');
      Array.prototype.push.apply(out, Object.values(types))
    }

    return out.map(l => l + '\n').join('');
  }

  visit (leader, obj, skips, outer, types) {
    const ret = [];
    const entries = Object.entries(obj);
    for (let entryNo = 0; entryNo < entries.length; ++entryNo) {
      const [key, value] = entries[entryNo];
      // const [property, dataType] = key.split(/([A-Z].*)/);
      // const dataType = dataType1 ? dataType1.substring(0, 1).toLowerCase() + dataType1.substring(1) : null;
      // const overloadedType = Object.keys(FhirJsonToTurtle.AllDatatypes).find(dt => key.endsWith(dt));
      // const property = overloadedType ? key.substring(0, key.length - overloadedType.lentgth) : key;
      const polymorphicAttr = Object.keys(FhirJsonToTurtle.PolymorphicAttributes).find(attr => key.startsWith(attr));
      const [property, overloadedType] = polymorphicAttr ? [polymorphicAttr, key.substring(polymorphicAttr.length)] : [key, null];
      const punct = entryNo < entries.length - 1
            ? ';'
            : outer
            ? '.'
            : '';
      if (skips.has(property)) {
        // skip it
      } else if (typeof value === 'boolean' || typeof value === 'number') {
        ret.push(`${leader}fhir:value [ fhir:v ${value} ]${punct}`)
      } else if (typeof value === 'string') {
        if (property === 'system') {
          const base = FhirJsonToTurtle.SystemBases[value];
          if (base)
            ret.push(`${leader}a <${base}${obj.code}>;`)
        } else if (property === 'reference') {
          if (!(value in types))
            types[value] = `<../${value}> a fhir:${value.substring(0, value.indexOf('/'))}.`
          ret.push(`${leader}fhir:OBSOLETE_link <../${value}>;`)
        }
        let valueStr = null;
        const typed = FhirJsonToTurtle.TypedAttributes[property] || FhirJsonToTurtle.AllDatatypes[overloadedType];
        if (typed) {
          const dt = typed.microparse
                ? typed.microparse(value)
                : typed.label
          valueStr = this.quote(value) + '^^xsd:' + dt;
        } else {
          valueStr = this.quote(value);
        }
        if (overloadedType) { // not sure this is necessary
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
        for (let i = 0; i < value.length; ++i) {
          if (typeof value[i] === 'string') {
            if (i === 0)
              ret.push(`${leader}fhir:${property} (`);
            ret.push(`${leader}  [ fhir:v ${this.quote(value[i])} ]`)
            if (i === value.length - 1)
              ret.push(`${leader})${punct}`);
          } else {
            ret.push(i === 0 ? `${leader}fhir:${property} ( [` : `${leader}[`)
            Array.prototype.push.apply(ret, this.visit(leader + '  ', value[i], skips, false, types));
            ret.push(i < value.length - 1 ? `${leader}]` : `${leader}] )${punct}`)
          }
        }
      } else if (typeof value === 'object') {
        ret.push(`${leader}fhir:${property} [`)
        if (overloadedType) {
          ret.push(`${leader}  a fhir:${overloadedType};`);
        }
        Array.prototype.push.apply(ret, this.visit(leader + '  ', value, skips, false, types));
        ret.push(`${leader}]${punct}`)
      } else { throw Error(`visit not expecting ${JSON.stringify(value)}`); }
    }
    return ret;
  }

  quote (str) {
    return '"' + str.replace(/\\/g, '\\\\').replace(/\n/g, '\\n').replace(/\"/g, '\\"') + '"';
  }
}

if (typeof module !== undefined)
  module.exports = {FhirJsonToTurtle};
