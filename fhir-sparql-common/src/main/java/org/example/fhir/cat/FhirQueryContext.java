package org.example.fhir.cat;

import ca.uhn.fhir.rest.gclient.IQuery;
import org.apache.jena.sparql.util.Context;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.rest.client.api.IGenericClient;
import org.hl7.fhir.instance.model.api.IBaseBundle;
import org.hl7.fhir.r5.model.Bundle;
import org.hl7.fhir.r5.model.Observation;

public class FhirQueryContext extends Context {
	private final IGenericClient client;
	private final FhirContext ctx;

	private IQuery<IBaseBundle> searchQuery = null;

	public FhirQueryContext(FhirContext ctx, String resource) {
		super();
		this.ctx = ctx;
		this.client = this.ctx.newRestfulGenericClient("http://localhost:8080/hapi/fhir");
		if(resource != null) {
			searchQuery = this.client.search().forResource(resource);
		}
	}

	public IGenericClient client() {
		return client;
	}
	
	public FhirContext fhirContext() {
		return ctx;
	}

	public void addCodeCriterion(String system, String code) {
		searchQuery.where(Observation.CODE.exactly().systemAndCode(system,code));
	}

	public IBaseBundle executeQuery() {
		return searchQuery.execute();
	}
}
