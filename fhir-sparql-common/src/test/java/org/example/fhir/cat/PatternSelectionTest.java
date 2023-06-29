package org.example.fhir.cat;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.rest.client.api.IGenericClient;
import ca.uhn.fhir.rest.gclient.IFetchConformanceUntyped;
import org.apache.jena.datatypes.RDFDatatype;
import org.apache.jena.graph.Node;
import org.apache.jena.graph.NodeFactory;
import org.apache.jena.graph.Triple;
import org.apache.jena.query.Query;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.sparql.core.DatasetGraphFactory;
import org.apache.jena.sparql.engine.Plan;
import org.apache.jena.sparql.engine.QueryIterator;
import org.apache.jena.sparql.engine.binding.Binding;
import org.apache.jena.sparql.engine.binding.BindingRoot;
import org.example.fhir.cat.FhirSparqlEngine.FhirQueryEngineFactory;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class PatternSelectionTest {

    private static final String sparqlQuery = """
            PREFIX fhir: <http://hl7.org/fhir/>
            PREFIX owl: <http://www.w3.org/2002/07/owl#>
            PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
            PREFIX loinc: <http://loinc.org>
            PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>

            SELECT ?obsId WHERE {
              ?obs a fhir:Observation .
              ?obs fhir:code ?codeList .
              ?codeList rdf:first*/rdf:rest ?coding .
              ?coding fhir:code ?codeCode .
              ?codeCode fhir:v "20570-8" .
              ?coding fhir:system ?codingSystem .
              ?codingSystem fhir:v "http://loinc.org" .
            }
            """;

    private ArcTree rootTree;

    @Before
    public void setupArcTree() {
        Node obs = NodeFactory.createVariable("obs");
        rootTree = new ArcTree(null,
                Arrays.asList(
                        new ArcTree(TriplePattern.createVarPredicateObject("obs", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://hl7.org/fhir/Observation"), null),
                        new ArcTree(TriplePattern.createVarPredicateVar("obs", "http://hl7.org/fhir/code", "codeList"),
                                Arrays.asList(new ArcTree(
                                                TriplePattern.createVarPathVar("codeList", "http://www.w3.org/1999/02/22-rdf-syntax-ns#first*", "http://www.w3.org/1999/02/22-rdf-syntax-ns#rest", "coding"),
                                                Arrays.asList(
                                                        new ArcTree(
                                                                TriplePattern.createVarPredicateVar("coding", "http://hl7.org/fhir/code", "codeCode"),
                                                                Arrays.asList(
                                                                        new ArcTree(TriplePattern.createVarPredicateLiteral("codeCode", "http://hl7.org/fhir/v", "20570-8"))
                                                                )
                                                        ),
                                                        new ArcTree(
                                                                TriplePattern.createVarPredicateVar("coding", "http://hl7.org/fhir/system", "codingSystem"),
                                                                Arrays.asList(
                                                                        new ArcTree(TriplePattern.createVarPredicateLiteral("codingSystem", "http://hl7.org/fhir/v", "http://loinc.org"))
                                                                )
                                                        )
                                                )
                                        )

                                )
                        )
                )
        );
    }

    @Test
    public void identifyPatterns() {

    }
}
