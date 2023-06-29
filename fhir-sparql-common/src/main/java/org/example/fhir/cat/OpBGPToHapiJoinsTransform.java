package org.example.fhir.cat;

import java.util.*;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.apache.jena.graph.Node;
import org.apache.jena.graph.Triple;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.sparql.algebra.Op;
import org.apache.jena.sparql.algebra.TransformCopy;
import org.apache.jena.sparql.algebra.op.OpBGP;
import org.apache.jena.sparql.algebra.op.OpJoin;
import org.apache.jena.sparql.core.BasicPattern;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.rest.client.api.IGenericClient;
import org.hl7.fhir.r5.model.Observation;

public class OpBGPToHapiJoinsTransform extends TransformCopy {

	public static final String RDF_TYPE = "http://www.w3.org/1999/02/22-rdf-syntax-ns#type";
	public static final String FHIR_PREFIX = "http://hl7.org/fhir";

	private Stack<OpBGP> stack = new Stack<>();
	private final IGenericClient client;
	private final FhirContext ctx;

	Map<Node, String> subjectResourceMap = new HashMap<>();

	public OpBGPToHapiJoinsTransform(IGenericClient client, FhirContext ctx) {
		super();
		this.client = client;
		this.ctx = ctx;
	}

	@Override
	public Op transform(OpBGP opBGP) {
		BasicPattern bgp = opBGP.getPattern();

		//Keep for now
		Map<Node, List<Triple>> collect = bgp.getList().stream().collect(Collectors.groupingBy(Triple::getSubject));

		for(Triple triple : bgp.getList()) {
			if(isRdfType(triple)) {
				String resourceName = identifyResource(triple);
				if(resourceName != null) {
					subjectResourceMap.put(triple.getSubject(), resourceName);
				} else {
					throw new RuntimeException("Unknown resource: " + resourceName);
				}
			} else {
				Node subject = triple.getSubject();
				String resource = subjectResourceMap.get(subject);
			}
		}

		Op sequence = null;
		Op join = null;

		if (collect.size() == 0) {
			return new HapiBgpOp(opBGP, client, ctx);
		} else {
			Iterator<Entry<Node, List<Triple>>> iterator = collect.entrySet().iterator();

			join = newHapiBgpOpFrom(iterator.next(), client, ctx);
			while (iterator.hasNext()) {
				join = OpJoin.create(join, newHapiBgpOpFrom(iterator.next(), client, ctx));
			}
		}
		if (join == null) {
			return opBGP;
		} else {
			return join;
		}
	}

	private HapiBgpOp newHapiBgpOpFrom(Entry<Node, List<Triple>> next, IGenericClient client2, FhirContext ctx) {

		return new HapiBgpOp(new OpBGP(BasicPattern.wrap(next.getValue())), client2, ctx);
	}

	public boolean isRdfType(Triple triple) {
		boolean returnValue = false;
		if(triple.getPredicate().isURI()) {
			if(triple.getPredicate().getURI().equalsIgnoreCase(RDF_TYPE)) {
				returnValue = true;
			}
		}
		return returnValue;
	}

	private String identifyResource(Triple triple) {
		String resourceName = null;

		if(triple.getObject() != null && triple.getObject().isURI()) {
			if(triple.getObject().getURI().startsWith(FHIR_PREFIX)) {
				resourceName = triple.getObject().getURI().substring(FHIR_PREFIX.length() + 1);
			}
		}
		return resourceName;
	}

}
