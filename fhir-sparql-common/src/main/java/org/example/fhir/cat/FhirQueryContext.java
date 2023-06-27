package org.example.fhir.cat;

import org.apache.jena.sparql.util.Context;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.rest.client.api.IGenericClient;

public class FhirQueryContext extends Context {
	private final IGenericClient client;
	private final FhirContext fhir;

	public FhirQueryContext(IGenericClient client, FhirContext fhir) {
		super();
		this.client = client;
		this.fhir = fhir;
	}

	public IGenericClient client() {
		return client;
	}
	
	public FhirContext fhirContext() {
		return fhir;
	}
}
