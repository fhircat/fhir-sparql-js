package org.example.fhir.cat;

import org.apache.jena.query.Query;
import org.apache.jena.sparql.ARQInternalErrorException;
import org.apache.jena.sparql.algebra.Op;
import org.apache.jena.sparql.core.DatasetGraph;
import org.apache.jena.sparql.engine.Plan;
import org.apache.jena.sparql.engine.QueryEngineFactory;
import org.apache.jena.sparql.engine.QueryEngineRegistry;
import org.apache.jena.sparql.engine.binding.Binding;
import org.apache.jena.sparql.engine.main.QueryEngineMain;
import org.apache.jena.sparql.util.Context;

public class FhirEngine extends QueryEngineMain {

	public FhirEngine(Op op, DatasetGraph dataset, Binding input, Context context) {
		super(op, dataset, input, context);
	}

	public FhirEngine(Query query, DatasetGraph dataset, Binding initial, Context context) {
		super(query, dataset, initial, context);
	}

	@Override
	protected Op modifyOp(Op op) {
		// We are going to modify the algebra here.

		op = super.modifyOp(op);
		// op = Algebra.toQuadForm(op) ;
		return op;
	}

	static QueryEngineFactory factory = new FhirQueryEngineFactory();

	static public QueryEngineFactory getFactory() {
		return factory;
	}

	static public void register() {
		QueryEngineRegistry.addFactory(factory);
	}

	static public void unregister() {
		QueryEngineRegistry.removeFactory(factory);
	}

	public static class FhirQueryEngineFactory implements QueryEngineFactory {
		// Accept any dataset for query execution
		@Override
		public boolean accept(Query query, DatasetGraph dataset, Context context) {
			return true;
		}

		@Override
		public Plan create(Query query, DatasetGraph dataset, Binding initial, Context context) {
			// Create a query engine instance.
			FhirEngine engine = new FhirEngine(query, dataset, initial, context);
			return engine.getPlan();
		}

		@Override
		public boolean accept(Op op, DatasetGraph dataset, Context context) { // Refuse to accept algebra expressions
																				// directly.
			return false;
		}

		@Override
		public Plan create(Op op, DatasetGraph dataset, Binding inputBinding, Context context) { // Should not be called
																									// because
																									// acccept/Op is
																									// false
			throw new ARQInternalErrorException("MyQueryEngine: factory calleddirectly with an algebra expression");
		}
	}
}
