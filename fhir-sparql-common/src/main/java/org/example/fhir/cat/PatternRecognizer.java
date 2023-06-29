package org.example.fhir.cat;

import ca.uhn.fhir.rest.client.api.IGenericClient;

public abstract class PatternRecognizer implements TreePatternRecognizer {

    public FhirPattern recognize(IGenericClient fhirClient, ArcTree tree) {
        return null;
    }
}
