package org.example.fhir.cat;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.apache.jena.graph.Node;
import org.apache.jena.query.Query;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.sparql.core.DatasetGraphFactory;
import org.apache.jena.sparql.engine.Plan;
import org.apache.jena.sparql.engine.QueryIterator;
import org.apache.jena.sparql.engine.binding.Binding;
import org.apache.jena.sparql.engine.binding.BindingRoot;
import org.example.fhir.cat.FhirSparqlEngine.FhirQueryEngineFactory;
import org.junit.Test;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.rest.client.api.IGenericClient;
import ca.uhn.fhir.rest.gclient.IFetchConformanceUntyped;

public class FhirEgineTest {
	public static final String observation = """
PREFIX fhir: <http://hl7.org/fhir/>
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

	@Test
	public void observationQuery() {
		FhirContext ctx = FhirContext.forR5();
		String serverBase = "http://hapi.fhir.org/baseR5";

		IGenericClient client = ctx.newRestfulGenericClient(serverBase);
		IFetchConformanceUntyped capabilities = client.capabilities();
		assertNotNull(capabilities);
		Query q = QueryFactory.create(observation);

		Plan plan = new FhirQueryEngineFactory().create(q, DatasetGraphFactory.create(), BindingRoot.create(),
				new FhirQueryContext(client, ctx));

		QueryIterator results = plan.iterator();

		assertTrue(results.hasNext());
		Binding soln = results.next();
		Node node = soln.get("observation");
		String r;
		if(node.isBlank()) {
			r = node.getBlankNodeLabel();
		} else {
			r = node.getNameSpace(); //Get a result variable - must be a resource
		}
		assertNotNull(r);

	}
}
