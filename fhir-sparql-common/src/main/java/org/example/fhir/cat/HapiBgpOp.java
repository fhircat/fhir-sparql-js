package org.example.fhir.cat;

import org.apache.jena.atlas.io.IndentedWriter;
import org.apache.jena.graph.Triple;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.riot.RDFLanguages;
import org.apache.jena.riot.RDFParser;
import org.apache.jena.sparql.algebra.Op;
import org.apache.jena.sparql.algebra.op.OpBGP;
import org.apache.jena.sparql.algebra.op.OpExt;
import org.apache.jena.sparql.core.BasicPattern;
import org.apache.jena.sparql.core.DatasetGraph;
import org.apache.jena.sparql.engine.ExecutionContext;
import org.apache.jena.sparql.engine.QueryIterator;
import org.apache.jena.sparql.engine.binding.Binding;
import org.apache.jena.sparql.engine.binding.BindingRoot;
import org.apache.jena.sparql.engine.iterator.QueryIter;
import org.apache.jena.sparql.engine.main.QueryEngineMain;
import org.apache.jena.sparql.serializer.SerializationContext;
import org.apache.jena.sparql.util.Context;
import org.apache.jena.sparql.util.NodeIsomorphismMap;
import org.hl7.fhir.instance.model.api.IBaseBundle;
import org.hl7.fhir.r5.model.Bundle;
import org.hl7.fhir.r5.model.Observation;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.rest.client.api.IGenericClient;
import ca.uhn.fhir.rest.gclient.IQuery;

//TODO a HapiOp will be a collection of a number of BGPs that will be resolved 
// to a single HapiClient call.
//	
public class HapiBgpOp extends OpExt {

	private final OpBGP original;
	private final IGenericClient fhirClient;
	private final FhirContext ctx;

	public HapiBgpOp(OpBGP original, IGenericClient fhirClient, FhirContext ctx) {
		super("hapi bgp");
		this.original = original;
		this.fhirClient = fhirClient;
		this.ctx = ctx;
	}

	@Override
	public Op effectiveOp() {
		return original;
	}

	@Override
	public QueryIterator eval(QueryIterator input, ExecutionContext execCxt) {

		return new QueryIterExtension(execCxt, input, fhirClient);
	}

	private IQuery<Bundle> buildFhirSearch(QueryIterator input) {
		Class<Observation> findResource = findResource(original.getPattern());
		return fhirClient.search().forResource(findResource)
//		   .where(Patient.NAME.matches().value("smith"))
				.returnBundle(Bundle.class);

	}

	@Override
	public void outputArgs(IndentedWriter out, SerializationContext sCxt) {
		original.output(out);

	}

	@Override
	public int hashCode() {
		return original.hashCode();
	}

	@Override
	public boolean equalTo(Op other, NodeIsomorphismMap labelMap) {
		if (other instanceof HapiBgpOp otherHapi) {
			return this.fhirClient == otherHapi.fhirClient && original.equals(otherHapi.original);
		} else {
			return false;
		}
	}

	private Class<Observation> findResource(BasicPattern pattern) {
		for (Triple t : pattern) {
			if (t.getPredicate().isURI()) {
				if (match(t.getPredicate().getURI(), FhirRdf.code)) {
					return Observation.class;
				}
			}
		}
		return null;
	}

	private boolean match(String uri, Resource code) {

		return uri.equals(code.getURI());
	}

	private final class QueryIterExtension extends QueryIter {
		private IQuery<Bundle> fhirSearch;
		private Bundle bundle;
		private QueryIterator results;
		private IGenericClient client;

		private QueryIterExtension(ExecutionContext execCxt, QueryIterator input, IGenericClient client) {
			super(execCxt);
			this.client = client;
			fhirSearch = buildFhirSearch(input);
		}

		@Override
		protected boolean hasNextBinding() {
			if (bundle == null) {
				bundle = fhirSearch.execute();
			}
			if (results == null) {
				return bundleToResultSet();
			} else if (results.hasNext()) {
				return true;
			} else {
				if (bundle.getLink(IBaseBundle.LINK_NEXT) == null) {
					return false;
				} else {
					bundle = client.loadPage().next(bundle).execute();
					return bundleToResultSet();
				}
			}
		}

		private boolean bundleToResultSet() {
			String encodeResourceToString = ctx.newRDFParser().encodeResourceToString(bundle);
			DatasetGraph dataset = RDFParser.create().fromString(encodeResourceToString).lang(RDFLanguages.TTL).build()
					.toDatasetGraph();

			results = new QueryEngineMain(original, dataset, BindingRoot.create(), new Context()).getPlan().iterator();
			return results.hasNext();
		}

		@Override
		protected Binding moveToNextBinding() {

			return results.nextBinding();
		}

		@Override
		protected void closeIterator() {
			results.close();
		}

		@Override
		protected void requestCancel() {
			// TODO Auto-generated method stub

		}
	}

}
