# fhir-sparql-js
Translate SPARQL queries over a FHIR data to FHIR REST API invocations, execute, and integrate response into query orchestration.

[FhirSparql](src/FhirSparql.ts) converts a [query](../fhir-sparql-common/src/test/resources/org/uu3/obs-proc.srq) that spans FHIR Resources, e.g. Observation and Procedure:
``` SPARQL
SELECT ?obs ?proc WHERE {
  # Observation
  ?obs a fhir:Observation ;

    #   code
    fhir:code [
      fhir:coding [
        rdf:rest*/rdf:first [
          fhir:code [ fhir:v "72166-2" ] ;
          fhir:system [ fhir:v "http://loinc.org"^^xsd:anyURI ]
        ]
      ]
    ] ;

  #   subject
    fhir:subject [ fhir:reference ?subject ] .

  # Procedure
  ?proc a fhir:Procedure ;

    #   code
    fhir:code [
      fhir:coding [
        rdf:rest*/rdf:first [
          fhir:code [ fhir:v "72166-2" ] ;
          fhir:system [ fhir:v "http://loinc.org"^^xsd:anyURI ]
        ]
      ]
    ] ;

  #   subject
    fhir:subject [ fhir:reference ?subject ] .
}
```
and breaks it up into multiple FHIR REST API queries that honor where possible the original constraints, e.g.
```
GET <FHIR endpoint>/Observation?code=http://loinc.org|72166-2
GET <FHIR endpoint>Procedure?subject=http://localhost:8080/hapi/fhir/Patient/2&code=http://loinc.org|72166-2
```
(assuming `Patient/2` was the only patient in the results that came back from the 1st GET).

[FhirSparql](src/FhirSparql.ts) operations:
1. `A` is a set of "ArcTrees" (nested tree structures) in the SPARQL query.
2. `RAs` is a list of sets of ArcTrees that fit in some FHIR Resource (as captured in the ShEx schema).
3. Isolate `AR1` the set of ArcTrees at the start of `RAs`; judicious selection of AR1 is an optimization.
4. Match `AR` against a library of FHIR REST API defintions (e.g. code=<code.coding.system>|<code.coding.code>) to generate a FHIR GET operation `GET1`.
5. Interpret results `GET1` as RDF graph `G1`.
6. (re-)Generate (Resource-specific) SPARQL Query `SR1` from `AR`.
7. `RS` is the SPARQL Result Set obtained by issuing `SR1` on `G1`.
8. For each result `R1` in `RS`, prepare a query `AR2` from `AR2`, the next in the list of `RAs`, given the bindings in `R1` (e.g. `Patient/2`)
9. rinse, lather, repeat
