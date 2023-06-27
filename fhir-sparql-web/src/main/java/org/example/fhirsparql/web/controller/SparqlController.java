package org.example.fhirsparql.web.controller;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.rest.client.api.IGenericClient;
import org.apache.jena.query.*;
import org.apache.jena.sparql.core.DatasetGraphFactory;
import org.apache.jena.sparql.engine.Plan;
import org.apache.jena.sparql.engine.QueryIterator;
import org.apache.jena.sparql.engine.binding.BindingRoot;
import org.example.fhir.cat.FhirQueryContext;
import org.example.fhir.cat.FhirSparqlEngine;
import org.hl7.fhir.r5.model.Bundle;
import org.hl7.fhir.r5.model.Patient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayOutputStream;


@RestController
@RequestMapping("/sparql")
public class SparqlController {

    @Value("${fhir.base}")
    private String fhirBase;
    private final FhirContext ctx = FhirContext.forR5();

    private IGenericClient newClient() {
        return ctx.newRestfulGenericClient(fhirBase);
    }

    @PostMapping(value="/search/query", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String executeSearchWithQueryParam(@RequestBody MultiValueMap<String, String> formData) {
        System.out.println("Echo query: " + formData.get("query"));
        Bundle bundle = newClient().search().forResource(Patient.class).returnBundle(Bundle.class).execute();
        return ctx.newRDFParser().encodeResourceToString(bundle);
    }

    @PostMapping(value="/search", consumes = {"text/plain", "application/sparql-query"})
    public String executeSearch(@RequestBody String query) {
        return executeSparqlQuery(query);
    }

    @GetMapping(value="/search", consumes = {"text/plain", "application/sparql-query"})
    public String executeQuery(@RequestParam  String query) {
        return "Echo query: " + query;
    }

    protected String executeSparqlQuery(String sparqlQuery) {
        IGenericClient client = ctx.newRestfulGenericClient(fhirBase);
        Query query = QueryFactory.create(sparqlQuery);
        Plan plan = new FhirSparqlEngine.FhirQueryEngineFactory().create(query, DatasetGraphFactory.create(), BindingRoot.create(), new FhirQueryContext(client, ctx));
        QueryIterator results = plan.iterator();
        ResultSet resultSet = ResultSetFactory.create(results, query.getResultVars());
        //return new SPARQLResult(resultSet);
        return accumulateAsString(resultSet);
    }

    protected String accumulateAsString(ResultSet result) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ResultSetFormatter.outputAsJSON(outputStream, result);
        return outputStream.toString();
    }
}
