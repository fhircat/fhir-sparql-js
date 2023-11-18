package org.example.fhir.cat;

import org.apache.jena.query.Query;
import org.apache.jena.sparql.ARQInternalErrorException;
import org.apache.jena.sparql.algebra.Op;
import org.apache.jena.sparql.algebra.Transform;
import org.apache.jena.sparql.algebra.Transformer;
import org.apache.jena.sparql.algebra.op.OpJoin;
import org.apache.jena.sparql.algebra.op.OpProject;
import org.apache.jena.sparql.algebra.op.OpSequence;
import org.apache.jena.sparql.core.DatasetGraph;
import org.apache.jena.sparql.engine.Plan;
import org.apache.jena.sparql.engine.QueryEngineFactory;
import org.apache.jena.sparql.engine.QueryEngineRegistry;
import org.apache.jena.sparql.engine.QueryIterator;
import org.apache.jena.sparql.engine.binding.Binding;
import org.apache.jena.sparql.engine.main.QueryEngineMain;
import org.apache.jena.sparql.util.Context;

import java.util.Arrays;

public class FhirSparqlEngine extends QueryEngineMain {

	public FhirSparqlEngine(Op op, DatasetGraph dataset, Binding input, Context context) {
		super(op, dataset, input, context);
	}

	public FhirSparqlEngine(Query query, DatasetGraph dataset, Binding initial, Context context) {
		super(query, dataset, initial, context);
	}

	@Override
	public QueryIterator eval(Op op, DatasetGraph dsg, Binding input, Context context) {
//		if (Op )
		return super.eval(op, dsg, input, context);
	}

	@Override
	protected Op modifyOp(Op op) {
		// We are going to modify the algebra here.

		op = super.modifyOp(op);

		ArcTree tree = null;
		OpProject x = (OpProject) op;
		Op subOp = x.getSubOp();
		if(subOp instanceof OpSequence) {
			tree = createArcTree((OpSequence)subOp);
		} else if(subOp instanceof OpJoin) {
			throw new RuntimeException("Not implemented yet");
		} else {
			throw new RuntimeException("Unrecognized operator " + subOp.getClass().getName());
		}

		OpProject transformedOp = new OpProject(new ArcTreeOp("Waldo", tree, op), x.getVars());


		// bgp(triple(?subjectRef fhir:reference ?patient .)
		// triple(?patient a fhir:Patient),
		// triple(?patient fhir:id ?patIdElt))
		//
		// ->
		// bgp(hapiJoin (triple(?patient a fhir:Patient),
		// triple(?patient fhir:id ?patIdElt )),
		// triple(?patient fhir:id ?patIdElt)))
		// we want to combine parts of the query in one bgp that have the same ?subject
		// into a single
		// hapi client operation.
		// op = Algebra.toQuadForm(op) ;
		FhirQueryContext fqc = (FhirQueryContext) context;
		Transform someTransform = new OpSparqlToFhirTransform(fqc.client(), fqc.fhirContext());
		op = Transformer.transform(someTransform, transformedOp);

		return op;
	}

	//TODO Eric, please implement
	public ArcTree createArcTree(OpSequence sequence) {
		return new ArcTree(null,
				Arrays.asList(
						new ArcTree(TriplePattern.createVarPredicateObject("obs", "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "http://hl7.org/fhir/Observation"), null),
						new ArcTree(TriplePattern.createVarPredicateVar("obs", "http://hl7.org/fhir/code", "codeList"),
								Arrays.asList(new ArcTree(
												TriplePattern.createVarPathVar("codeList", "http://www.w3.org/1999/02/22-rdf-syntax-ns#first*", "http://www.w3.org/1999/02/22-rdf-syntax-ns#rest", "coding"),
												Arrays.asList(
														new ArcTree(
																TriplePattern.createVarPredicateVar("coding", "http://hl7.org/fhir/code", "codeCode"),
																Arrays.asList(
																		new ArcTree(TriplePattern.createVarPredicateLiteral("codeCode", "http://hl7.org/fhir/v", "72166-2"))
																)
														),
														new ArcTree(
																TriplePattern.createVarPredicateVar("coding", "http://hl7.org/fhir/system", "codingSystem"),
																Arrays.asList(
																		new ArcTree(TriplePattern.createVarPredicateLiteral("codingSystem", "http://hl7.org/fhir/v", "http://loinc.org"))
																)
														)
												)
										)

								)
						)
				)
		);
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
			FhirSparqlEngine engine = new FhirSparqlEngine(query, dataset, initial, context);
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
