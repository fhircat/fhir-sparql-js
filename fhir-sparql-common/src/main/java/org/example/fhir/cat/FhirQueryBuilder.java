package org.example.fhir.cat;

import ca.uhn.fhir.rest.client.api.IGenericClient;
import org.apache.jena.query.ResultSet;

import java.util.ArrayList;
import java.util.List;

/**
 * Prototype for a translator from SPARQL to FHIR Query(s)
 * Note that a single SPARQL query may result in one or more
 * corresponding FHIR queries
 */
public class FhirQueryBuilder {
    private final List<IGenericClient> fhirQueries = new ArrayList<>();

    public FhirQueryBuilder() {
    }

    public List<IGenericClient> getFhirQueries() {
        return fhirQueries;
    }

    public void processTree(List<ArcTree> trees) {
        //Convert triple path tree into one or more FHIR queries
    }

    public void processTree(ArcTree tree) {
        List<ArcTree> trees = new ArrayList<>();
        trees.add(tree);
        processTree(trees);
    }

    public ResultSet execute() {
        //Execute the fhir queries
        return null;
    }
}
