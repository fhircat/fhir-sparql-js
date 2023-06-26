package org.example.fhir.cat;

import static org.junit.Assert.assertNotNull;

import org.apache.jena.query.Query;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.sparql.core.DatasetGraphFactory;
import org.apache.jena.sparql.engine.Plan;
import org.apache.jena.sparql.engine.QueryIterator;
import org.apache.jena.sparql.engine.binding.Binding;
import org.apache.jena.sparql.engine.binding.BindingRoot;
import org.example.fhir.cat.FhirSparqlEngine.FhirQueryEngineFactory;
import org.junit.Test;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.rest.client.api.IGenericClient;

public class FhirEgineTest {
	public static final String observation = "prefix fhir: <http://hl7.org/fhir/> \n"
			+ "prefix loinc: <https://loinc.org/rdf/> \n" + "prefix owl: <http://www.w3.org/2002/07/owl#> \n"
			+ "prefix rdfs: <http://www.w3.org/2000/01/rdf-schema#> \n" + "prefix sct: <http://snomed.info/id/> \n"
			+ "prefix xsd: <http://www.w3.org/2001/XMLSchema#> \n" + "\n"
			+ "# - resource -------------------------------------------------------------------\n"
			+ "SELECT ?observation WHERE " + "{" + "?observation a fhir:Observation ;\n"
			+ "  fhir:nodeRole fhir:treeRoot ;\n" + "  fhir:id [ fhir:v \"example\"] ; # \n" + "  fhir:text [\n"
			+ "     fhir:status [ fhir:v \"generated\" ] ;\n"
			+ "     fhir:div \"<div xmlns=\\\"http://www.w3.org/1999/xhtml\\\"><p><b>Generated Narrative: Observation</b><a name=\\\"example\\\"> </a></p><div style=\\\"display: inline-block; background-color: #d9e0e7; padding: 6px; margin: 4px; border: 1px solid #8da1b4; border-radius: 5px; line-height: 60%\\\"><p style=\\\"margin-bottom: 0px\\\">Resource Observation &quot;example&quot; </p></div><p><b>status</b>: <span title=\\\"   the mandatory quality flags:   \\\">final</span></p><p><b>category</b>: <span title=\\\"  category code is A code that classifies the general type of observation being made. This is used for searching, sorting and display purposes. \\\">Vital Signs <span style=\\\"background: LightGoldenRodYellow; margin: 4px; border: 1px solid khaki\\\"> (<a href=\\\"http://terminology.hl7.org/5.1.0/CodeSystem-observation-category.html\\\">Observation Category Codes</a>#vital-signs)</span></span></p><p><b>code</b>: <span title=\\\"  \\n    Observations are often coded in multiple code systems.\\n      - LOINC provides codes of varying granularity (though not usefully more specific in this particular case) and more generic LOINCs  can be mapped to more specific codes as shown here\\n      - snomed provides a clinically relevant code that is usually less granular than LOINC\\n      - the source system provides its own code, which may be less or more granular than LOINC\\n    \\\">Body Weight <span style=\\\"background: LightGoldenRodYellow; margin: 4px; border: 1px solid khaki\\\"> (<a href=\\\"https://loinc.org/\\\">LOINC</a>#29463-7; <a href=\\\"https://loinc.org/\\\">LOINC</a>#3141-9 &quot;Body weight Measured&quot;; <a href=\\\"https://browser.ihtsdotools.org/\\\">SNOMED CT</a>#27113001 &quot;Body weight&quot;; clinical-codes#body-weight)</span></span></p><p><b>subject</b>: <a href=\\\"patient-example.html\\\">Patient/example</a> &quot;Peter CHALMERS&quot;</p><p><b>encounter</b>: <a href=\\\"encounter-example.html\\\">Encounter/example</a></p><p><b>effective</b>: 2016-03-28</p><p><b>value</b>: <span title=\\\"   In FHIR, units may be represented twice. Once in the\\n    agreed human representation, and once in a coded form.\\n    Both is best, since it's not always possible to infer\\n    one from the other in code.\\n\\n    When a computable unit is provided, UCUM (http://unitsofmeasure.org)\\n    is always preferred, but it doesn't provide notional units (such as\\n    &quot;tablet&quot;), etc. For these, something else is required (e.g. SNOMED CT)\\n     \\\">185 lbs<span style=\\\"background: LightGoldenRodYellow\\\"> (Details: UCUM code [lb_av] = 'lb_av')</span></span></p></div>\"\n"
			+ "  ] ; #     the mandatory quality flags:    \n" + "  fhir:status [ fhir:v \"final\"] ; # \n"
			+ "  fhir:category ( [\n" + "     fhir:coding ( [\n"
			+ "       fhir:system [ fhir:v \"http://terminology.hl7.org/CodeSystem/observation-category\"^^xsd:anyURI ] ;\n"
			+ "       fhir:code [ fhir:v \"vital-signs\" ] ;\n" + "       fhir:display [ fhir:v \"Vital Signs\" ]\n"
			+ "     ] )\n"
			+ "  ] ) ; #    category code is A code that classifies the general type of observation being made. This is used for searching, sorting and display purposes.  \n"
			+ "  fhir:code [\n" + "     fhir:coding ( [\n" + "       a loinc:29463-7 ;\n"
			+ "       fhir:system [ fhir:v \"http://loinc.org\"^^xsd:anyURI ] ;\n"
			+ "       fhir:code [ fhir:v \"29463-7\" ] ;\n"
			+ "       fhir:display [ fhir:v \"Body Weight\" ] #   more generic methodless LOINC  \n" + "     ] [\n"
			+ "       a loinc:3141-9 ;\n" + "       fhir:system [ fhir:v \"http://loinc.org\"^^xsd:anyURI ] ;\n"
			+ "       fhir:code [ fhir:v \"3141-9\" ] ;\n"
			+ "       fhir:display [ fhir:v \"Body weight Measured\" ] #   translation is more specific method = measured LOINC  \n"
			+ "     ] [\n" + "       a sct:27113001 ;\n"
			+ "       fhir:system [ fhir:v \"http://snomed.info/sct\"^^xsd:anyURI ] ;\n"
			+ "       fhir:code [ fhir:v \"27113001\" ] ;\n" + "       fhir:display [ fhir:v \"Body weight\" ]\n"
			+ "     ] [\n" + "       fhir:system [ fhir:v \"http://acme.org/devices/clinical-codes\"^^xsd:anyURI ] ;\n"
			+ "       fhir:code [ fhir:v \"body-weight\" ] ;\n" + "       fhir:display [ fhir:v \"Body Weight\" ]\n"
			+ "     ] ) #     LOINC - always recommended to have a LOINC code    \n" + "  ] ; #    \n"
			+ "#    Observations are often coded in multiple code systems.\n"
			+ "#      - LOINC provides codes of varying granularity (though not usefully more specific in this particular case) and more generic LOINCs  can be mapped to more specific codes as shown here\n"
			+ "#      - snomed provides a clinically relevant code that is usually less granular than LOINC\n"
			+ "#      - the source system provides its own code, which may be less or more granular than LOINC\n"
			+ "#     \n" + "  fhir:subject [\n" + "     fhir:reference [ fhir:v \"Patient/example\" ]\n" + "  ] ; # \n"
			+ "  fhir:encounter [\n" + "     fhir:reference [ fhir:v \"Encounter/example\" ]\n" + "  ] ; # \n"
			+ "  fhir:effective [ fhir:v \"2016-03-28\"^^xsd:date] ; # \n" + "  fhir:value [\n"
			+ "     a fhir:Quantity ;\n" + "     fhir:value [ fhir:v \"185\"^^xsd:decimal ] ;\n"
			+ "     fhir:unit [ fhir:v \"lbs\" ] ;\n"
			+ "     fhir:system [ fhir:v \"http://unitsofmeasure.org\"^^xsd:anyURI ] ;\n"
			+ "     fhir:code [ fhir:v \"[lb_av]\" ]\n"
			+ "  ] . #     In FHIR, units may be represented twice. Once in the\n"
			+ "#    agreed human representation, and once in a coded form.\n"
			+ "#    Both is best, since it's not always possible to infer\n" + "#    one from the other in code.\n"
			+ "#\n" + "#    When a computable unit is provided, UCUM (http://unitsofmeasure.org)\n"
			+ "#    is always preferred, but it doesn't provide notional units (such as\n"
			+ "#    \"tablet\"), etc. For these, something else is required (e.g. SNOMED CT)\n" + "#      \n" + "\n"
			+ "# -------------------------------------------------------------------------------------\n" + "}";

	@Test
	public void observationQuery() {
		FhirContext ctx = FhirContext.forR5();
		String serverBase = "http://fhirtest.uhn.ca/r5";

		IGenericClient client = ctx.newRestfulGenericClient(serverBase);
		Query q = QueryFactory.create(observation);
		Model model = null;
		Plan plan = new FhirQueryEngineFactory().create(q, DatasetGraphFactory.create(), BindingRoot.create(), new FhirQueryContext(client));

		QueryIterator results = plan.iterator();

		for (; results.hasNext();) {
			Binding soln = results.next();
			String r = soln.get("observation").getNameSpace(); // Get a result variable - must be a resource
			assertNotNull(r);
		}

	}
}
