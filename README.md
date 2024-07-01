# fhir-sparql-js
Translate SPARQL queries over a FHIR data to FHIR REST API invocations, execute, and integrate response into query orchestration.

[FhirSparql](src/FhirSparql.ts) converts a [query](../fhir-sparql-test/src/test/resources/org/uu3/obs-proc.srq) that spans FHIR Resources, e.g. Observation and Procedure:
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


## Installation

Do the usual, with the small twist that `fhir-sparql-js is one level down in the repo:
``` shell
git clone git@github.com:fhircat/fhir-sparql # or use http(s)
cd fhir-sparql/fhir-sparql-js # 'cause the repo also houses fhir-sparql-java and common tests.
npm ci
npm run build
npm run test # not strictly needed, but a good idea
```

## Execution

Tou can play with this in the [debugger](#debugger) or with [CLI tools](#cli-tools).


### debugger

You can run the [CI tests](tests/FhirSparql-server-test.js) to see that it works:
``` sh
set -eo pipefail; LOGLEVEL=trace FHIR_SERVER_ADDR999=http://localhost:8080/hapi/fhir/ ./node_modules/.bin/jest tests/FhirSparql-server-test.js | ./node_modules/.bin/bunyan 
```

For more excitement, run it with a loglevel of debug (a bit of noise), or trace (lots of noise) and pipe the output through [bunyan]():
```
set -eo pipefail; LOGLEVEL=trace FHIR_SERVER_ADDR999=http://localhost:8080/hapi/fhir/ ./node_modules/.bin/jest tests/FhirSparql-server-test.js | ./node_modules/.bin/bunyan
```

Do to an unfortunate default behavior in unix shell scripts, it's always a good idea to `set -eo pipefail` so that failed tests tell the calling program that they failed.


### CLI tools

You can run a hard-coded FHIR server and a sparql request client.


#### canned-server


arg | type | desc | example
-|-|-|-
u | URL string | port and path (host is ignored) for the canned server to bind. | -u http://localhost:8080/hapi/fhir/
r | file path | where to find an index and hard-coded FHIR responses | -r ../fhir-sparql-test/src/test/resources/org/uu3/fhirServerResources/
d | info\|debug\|trace | how much logging noise to show | -d trace

If you set a debugging level above info, you probably want to pipe it through buyan:
``` sh
./bin/canned-server \
  -u http://localhost:8080/hapi/fhir/ \
  -r ../fhir-sparql-test/src/test/resources/org/uu3/fhirServerResources/ \
  -d trace | ./node_modules/.bin/bunyan
```

Verify that its working by running curl from another shell:
``` sh
curl -s http://localhost:8080/hapi/fhir/Observation?code=http%3A%2F%2Floinc.org%7C72166-2
```
(The `-s` keeps it from showing the progress bar.)


#### query-fhir

If you're running the [canned server](#canned-server) or some other FHIR server, you can run a query with the `query-fhir` cli tool.

arg | type | desc | example
-|-|-|-
fhirSchema | file path | ShEx schema for the FHIR Resources | --fhirSchema ../fhir-sparql-test/src/test/resources/org/uu3/ShEx-mini-terse.shex
fhirEndpoint | URL string | port and path (host is ignored) for the canned server to bind. | --fhirEndpoint http://localhost:8080/hapi/fhir/
queryFilePath | file path | file containing SPARQL query | -r ../fhir-sparql-test/src/test/resources/org/uu3/obs-pat.srq
debug | info\|debug\|trace | how much logging noise to show | --debug trace

If you set a debugging level above info, you probably want to pipe it through buyan:
``` sh
./bin/query-fhir \
  --fhirSchema ../fhir-sparql-test/src/test/resources/org/uu3/ShEx-mini-terse.shex \
  --fhirEndpoint http://localhost:8080/hapi/fhir/ \
  --queryFilePath ../fhir-sparql-test/src/test/resources/org/uu3/obs-pat.srq \
  --debug trace | ./node_modules/.bin/bunyan
```

You should a SPARQL result set in a human-readable format like:
```
{
  ?patId => "smoker-1"
  ?patIdElt => _:bc_0_n3-218
  ?patRsrc => <http://localhost:8080/hapi/fhir/Patient/smoker-1>
  ?obs => <http://localhost:8080/hapi/fhir/Observation/smoker-1-smoking-2022-05-19>
}
{
  ?patId => "smoker-1"
  ?patIdElt => _:bc_0_n3-385
  ?patRsrc => <http://localhost:8080/hapi/fhir/Patient/smoker-1>
  ?obs => <http://localhost:8080/hapi/fhir/Observation/smoker-1-smoking-2023-06-20>
}
```


## The FhirSparql class

The FhirSparql class executes SPARQL queries over FHIR Resources.

Its methods are described in at a high level; the source is at [FhirSparql](src/FhirSparql.ts).


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

