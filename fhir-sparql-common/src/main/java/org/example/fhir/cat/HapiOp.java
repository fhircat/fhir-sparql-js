package org.example.fhir.cat;

import org.apache.jena.atlas.io.IndentedWriter;
import org.apache.jena.shared.PrefixMapping;
import org.apache.jena.sparql.algebra.Op;
import org.apache.jena.sparql.algebra.OpVisitor;
import org.apache.jena.sparql.algebra.op.OpExt;
import org.apache.jena.sparql.engine.ExecutionContext;
import org.apache.jena.sparql.engine.QueryIterator;
import org.apache.jena.sparql.serializer.SerializationContext;
import org.apache.jena.sparql.util.NodeIsomorphismMap;

import ca.uhn.fhir.rest.client.api.IGenericClient;

//TODO a HapiOp will be a collection of a number of BGPs that will be resolved 
// to a single HapiClient call.
//	
public class HapiOp extends OpExt {

	private final Op original;
	private final IGenericClient fhirClient;

	public HapiOp(Op original, IGenericClient fhirClient) {
		super("hapi join");
		this.original = original;
		this.fhirClient = fhirClient;
	}

	@Override
	public Op effectiveOp() {
		return original;
	}

	@Override
	public QueryIterator eval(QueryIterator input, ExecutionContext execCxt) {
		
		return null;
	}

	@Override
	public void outputArgs(IndentedWriter out, SerializationContext sCxt) {
		// TODO Auto-generated method stub

	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean equalTo(Op other, NodeIsomorphismMap labelMap) {
		// TODO Auto-generated method stub
		return false;
	}

}
