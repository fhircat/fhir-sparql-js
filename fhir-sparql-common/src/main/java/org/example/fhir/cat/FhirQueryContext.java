package org.example.fhir.cat;

import org.apache.jena.sparql.util.Context;

import ca.uhn.fhir.rest.client.api.IGenericClient;

public class FhirQueryContext extends Context {
	private final IGenericClient client;

	public FhirQueryContext(IGenericClient client) {
		super();
		this.client = client;
	}

	public IGenericClient client() {
		return client;
	}
}
