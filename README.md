# fhir-sparql-
Translate SPARQL queries over a FHIR data to FHIR REST API invocations, execute, and integrate response into query orchestration.

This overview page covers the algorithm but the specific links to functions in the implementation(s) are found in their respecive READMEs:
* [fhir-sparql-js](fhir-sparql-js#fhir-sparql-js) typescript implementation
* [fhir-sparql-java](fhir-sparql-common/src/main/java/org/example/fhir/cat) (not done) Java implementation

The algorithm converts a [query](./fhir-sparql-common/src/test/resources/org/uu3/obs-proc.srq) that spans FHIR Resources, e.g. Observation and Procedure:
``` SPARQL
SELECT ?obs ?proc ?patRsrc WHERE {
  # Observation
  ?obs a fhir:Observation ;                                           #  0

    # Observation.code
    fhir:code [                                                       #  1
      fhir:coding [                                                   #  2
        rdf:rest*/rdf:first [                                         #  3
          fhir:code [ fhir:v "72166-2" ] ;                            #  4/5
          fhir:system [ fhir:v "http://loinc.org"^^xsd:anyURI ]       #  6/7
        ]
      ]
    ] ;

      # Observation.subject
    fhir:subject [ fhir:reference [ fhir:link ?patRsrc ] ] .          #  8/9/10

  # Procedure
  ?proc a fhir:Procedure ;                                            # 11

    # Procedure.code
    fhir:code [                                                       # 12
      fhir:coding [                                                   # 13
        rdf:rest*/rdf:first [                                         # 14
          fhir:code [ fhir:v "724167008" ] ;                          # 15/16
          fhir:system [ fhir:v "http://snomed.info/sct"^^xsd:anyURI ] # 17/18
        ]
      ]
    ] ;

    # Procedure.subject   
    fhir:subject [ fhir:reference [ fhir:link ?patRsrc ] ] .          # 19/20/21
}
```
and breaks it up into multiple FHIR REST API queries that honor where possible the original constraints, e.g.
```
GET <FHIR endpoint>/Observation?code=http://loinc.org|72166-2
GET <FHIR endpoint>Procedure?subject=http://localhost:8080/hapi/fhir/Patient/2&code=http://snomed.info/sct|724167008
```
(assuming `Patient/2` was the only patient in the results that came back from the 1st GET).




## The FhirSparql class

The FhirSparql class executes SPARQL queries over FHIR Resources.

Its methods are described in at a high level; the source is available in the implementation subdirectories.


### constructor

A FhirSparql object is constructed with a ShEx schema which tells it what properties appear in what FHIR Resources.

``` js
const rewriter = new FhirSparql(FhirShEx);
```

### executeFhirQuery

The principle interface is through `executeFhirQuery` which takes a FHIR endpoint and a parsed (following SPARQL.js's JSON representation of SPARQL queries) SPARQL query:
``` js
const results = await rewriter.executeFhirQuery(FhirServerAddr, sparqlQuery, log);
```

* get the [set of ArcTrees](#getArcTrees), which will reflect the types of FHIR Resources in the query
* for each arcTree extracted from `sparqlQuery`
  * for each solution in the current results (initialized to one solution with no bindings)
    * for each set of extracted REST parameters (`[extractRestParameters](#extractRestParameters)` returns a disjunct of possible fulfillments)
      * construct and execute a FHIR REST API query
      * for each response, re-execute the portion of the SPARQL query related to that Resource type
        * push each query solution into the results for the next iteration

This is effectively a join operation, which performs two tasks:
1. enforce constraints that didn't have a corresponding FHIR REST expression
2. unify variables use with and outside of the arctree.



### getArcTrees

The `getArcTrees` function sorts the triple patterns into trees
(called "arcTrees"), the variables connecting those trees, and the
positions in the triple expressions of those variables (a graph
representation that's more convenient for what's to come).

This test/example whitespace to show that triple patterns 0-9 will
match some set of Observations and 10-12 will match some set of
corresponding Patient resources. (obs-pat-disordered shows that the
algorithm is insensitive to order, modulo the somewhat arbitrary
choice of which resources to start wtih.)


### extractRestParameters

The `extractRestParameters` function unifies the variables in the
arcTree against a set of variable bindings (empty on the first call)
and matches the arcTrees against a library of known FHIR REST API
functions (a short list used for tests
  <https://github.com/fhircat/fhir-sparql/blob/15fd469/fhir-sparql-js/src/FhirSparql.ts#L59-L150>
The result is a components needed to assemble a FHIR REST API call
that will return every FHIR Resource which matches that arcTree with
those varaible bindings.


## Join operation

The algorithm mimics a SPARQL engine's join operations:
1. `A` is a set of "ArcTrees" (nested tree structures) in the SPARQL query.
2. `RAs` is a list of sets of ArcTrees that fit in some FHIR Resource (as captured in the ShEx schema).
3. Isolate `AR1` the set of ArcTrees at the start of `RAs`; judicious selection of AR1 is an optimization.
4. Match `AR` against a library of FHIR REST API defintions (e.g. code=<code.coding.system>|<code.coding.code>) to generate a FHIR GET operation `GET1`.
5. Interpret results `GET1` as RDF graph `G1`.
6. (re-)Generate (Resource-specific) SPARQL Query `SR1` from `AR`.
7. `RS` is the SPARQL Result Set obtained by issuing `SR1` on `G1`.
8. For each result `R1` in `RS`, prepare a query `AR2` from `AR2`, the next in the list of `RAs`, given the bindings in `R1` (e.g. `Patient/2`)
9. rinse, lather, repeat
