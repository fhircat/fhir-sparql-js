package org.example.fhir.cat;

import org.apache.jena.atlas.io.IndentedWriter;
import org.apache.jena.shared.PrefixMapping;
import org.apache.jena.sparql.algebra.Op;
import org.apache.jena.sparql.algebra.OpVisitor;
import org.apache.jena.sparql.serializer.SerializationContext;
import org.apache.jena.sparql.util.NodeIsomorphismMap;

//TODO a HapiOp will be a collection of a number of BGPs that will be resolved 
// to a single HapiClient call.
//	
public class HapiOp implements Op {

	@Override
	public void output(IndentedWriter out, SerializationContext sCxt) {
		// TODO Auto-generated method stub

	}

	@Override
	public String toString(PrefixMapping pmap) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void output(IndentedWriter out) {
		// TODO Auto-generated method stub

	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void visit(OpVisitor opVisitor) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean equalTo(Op other, NodeIsomorphismMap labelMap) {
		// TODO Auto-generated method stub
		return false;
	}

}
