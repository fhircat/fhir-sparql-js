package org.example.fhir.cat;

import org.apache.jena.atlas.io.IndentedWriter;
import org.apache.jena.shared.PrefixMapping;
import org.apache.jena.sparql.algebra.Op;
import org.apache.jena.sparql.algebra.OpVisitor;
import org.apache.jena.sparql.algebra.op.OpExt;
import org.apache.jena.sparql.engine.ExecutionContext;
import org.apache.jena.sparql.engine.QueryIterator;
import org.apache.jena.sparql.engine.binding.Binding;
import org.apache.jena.sparql.engine.iterator.QueryIter;
import org.apache.jena.sparql.engine.iterator.QueryIteratorBase;
import org.apache.jena.sparql.serializer.SerializationContext;
import org.apache.jena.sparql.util.NodeIsomorphismMap;

import ca.uhn.fhir.rest.client.api.IGenericClient;

//TODO a HapiOp will be a collection of a number of BGPs that will be resolved 
// to a single HapiClient call.
//	
public class HapiBgpOp extends OpExt {

	private final Op original;
	private final IGenericClient fhirClient;

	public HapiBgpOp(Op original, IGenericClient fhirClient) {
		super("hapi bgp");
		this.original = original;
		this.fhirClient = fhirClient;
	}

	@Override
	public Op effectiveOp() {
		return original;
	}

	@Override
	public QueryIterator eval(QueryIterator input, ExecutionContext execCxt) {

		return new QueryIter(execCxt) {

			@Override
			protected boolean hasNextBinding() {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			protected Binding moveToNextBinding() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			protected void closeIterator() {
				// TODO Auto-generated method stub

			}

			@Override
			protected void requestCancel() {
				// TODO Auto-generated method stub

			}

		};
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
