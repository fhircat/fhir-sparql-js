
// Namespaces
export const Ns = {
  rdf: 'http://www.w3.org/1999/02/22-rdf-syntax-ns#',
  xsd: 'http://www.w3.org/2001/XMLSchema#',
  fhir: 'http://hl7.org/fhir/',
};

// Re-used RDF nodes
export const Rdf = {
  type: { termType: 'NamedNode', value: Ns.rdf + 'type' },
  first: { termType: 'NamedNode', value: Ns.rdf + 'first' },
  rest: { termType: 'NamedNode', value: Ns.rdf + 'rest' },
};

export const Xsd = {
  integer: { termType: 'NamedNode', value: Ns.xsd + 'integer' },
  string: { termType: 'NamedNode', value: Ns.xsd + 'string' },
  anyURI: { termType: 'NamedNode', value: Ns.xsd + 'anyURI' },
};

export const Fhir = {
  v: { termType: 'NamedNode', value: Ns.fhir + 'v' },
}

export const FirstRest = { type: "path", pathType: "/", items: [
  { type: "path",
    pathType: "*",
    items: [
      { type: "path", pathType: "/", items: [
        { termType: "NamedNode", value: Rdf.first.value },
        { termType: "NamedNode", value: Rdf.rest.value }
      ]}
    ]},
  { termType: "NamedNode", value: Rdf.first.value }
]};
