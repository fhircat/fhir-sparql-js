package org.example.fhir.cat;

import java.util.Iterator;

import org.apache.jena.atlas.io.IndentedWriter;
import org.apache.jena.graph.Triple;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.sparql.algebra.Op;
import org.apache.jena.sparql.algebra.op.OpBGP;
import org.apache.jena.sparql.algebra.op.OpExt;
import org.apache.jena.sparql.core.BasicPattern;
import org.apache.jena.sparql.engine.ExecutionContext;
import org.apache.jena.sparql.engine.QueryIterator;
import org.apache.jena.sparql.engine.binding.Binding;
import org.apache.jena.sparql.engine.iterator.QueryIter;
import org.apache.jena.sparql.serializer.SerializationContext;
import org.apache.jena.sparql.util.NodeIsomorphismMap;
import org.hl7.fhir.instance.model.api.IBaseResource;
import org.hl7.fhir.r5.model.Bundle;
import org.hl7.fhir.r5.model.Observation;
import org.hl7.fhir.r5.model.Patient;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.rest.client.api.IGenericClient;
import ca.uhn.fhir.rest.gclient.IQuery;
import ca.uhn.fhir.util.BundleUtil;

//TODO a HapiOp will be a collection of a number of BGPs that will be resolved 
// to a single HapiClient call.
//	
public class HapiBgpOp extends OpExt {

	private final class QueryIterExtension extends QueryIter {
		private QueryIterator input;
		private IQuery<Bundle> fhirSearch;
		private Bundle bundle;
		private Iterator<IBaseResource> iterator;
		
		private QueryIterExtension(ExecutionContext execCxt, QueryIterator input) {
			super(execCxt);
			this.input = input;
			fhirSearch = buildFhirSearch(input);
		}

		@Override
		protected boolean hasNextBinding() {
			if (bundle == null) {
				bundle = fhirSearch.execute();
				iterator = BundleUtil.toListOfResources(ctx, bundle).iterator();
				iterator.hasNext();
			}
			return false;
		}

		@Override
		protected Binding moveToNextBinding() {
			
			return null;
		}

		@Override
		protected void closeIterator() {
			
		}

		@Override
		protected void requestCancel() {
			// TODO Auto-generated method stub

		}
	}

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
		
		return new QueryIterExtension(execCxt, input);
	}

	private IQuery<Bundle> buildFhirSearch(QueryIterator input) {
		return fhirClient
		   .search()
		   .forResource(findResource(original.getPattern()))
//		   .where(Patient.NAME.matches().value("smith"))
		   .returnBundle(Bundle.class);
		
	}

	private Class<Observation> findResource(BasicPattern pattern) {
		for (Triple t:pattern) {
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

}
