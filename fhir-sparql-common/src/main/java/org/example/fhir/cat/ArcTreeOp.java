package org.example.fhir.cat;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.rest.client.api.IGenericClient;
import ca.uhn.fhir.rest.gclient.IQuery;
import org.apache.jena.atlas.io.IndentedWriter;
import org.apache.jena.graph.Node;
import org.apache.jena.riot.RDFLanguages;
import org.apache.jena.riot.RDFParser;
import org.apache.jena.sparql.algebra.Op;
import org.apache.jena.sparql.algebra.op.OpExt;
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

import java.util.*;

public class ArcTreeOp extends OpExt {

    public static final String RDF_TYPE = "http://www.w3.org/1999/02/22-rdf-syntax-ns#type";
    public static final String FHIR_PREFIX = "http://hl7.org/fhir";

    private FhirContext ctx;

    private ArcTree arcTree;
    private Op sourceOp;

    public ArcTreeOp(String name, ArcTree arcTree, Op sourceOp) {
        super(name);
        this.arcTree = arcTree;
        this.sourceOp = sourceOp;
    }

    public ArcTree getArcTree() {
        return arcTree;
    }

    public void setArcTree(ArcTree arcTree) {
        this.arcTree = arcTree;
    }

    public Op getSourceOp() {
        return sourceOp;
    }

    public void setSourceOp(Op sourceOp) {
        this.sourceOp = sourceOp;
    }

    public FhirContext getCtx() {
        return ctx;
    }

    public void setCtx(FhirContext ctx) {
        this.ctx = ctx;
    }


    @Override
    public Op effectiveOp() {
        return null;
    }

    @Override
    public QueryIterator eval(QueryIterator queryIterator, ExecutionContext executionContext) {
        Map<Node, String> nodeToResourceIndex = new HashMap<>();
        Map<Node, FhirQueryContext> contextIndex = new HashMap<>();
        if (arcTree != null) {
            for (ArcTree child : arcTree.getChildren()) {
                if (isRdfType(child)) {
                    String resource = identifyResource(child);
                    nodeToResourceIndex.put(child.getCurrentNode().getTriple().getSubject(), resource);
                    contextIndex.put(child.getCurrentNode().getTriple().getSubject(), new FhirQueryContext(ctx, resource));//TODO Fix nulls
                } else if(isConceptAttribute(child)) {
                    handleConceptAttribute(child, contextIndex.get(child.getCurrentNode().getTriple().getSubject()));
                } else {
                    throw new RuntimeException("FHIR attribute not yet handled " + child);
                }
            }

            QueryIterator iterator = new QueryIterExtension(executionContext, queryIterator, contextIndex.values());
            return iterator;
        } else {
            throw new RuntimeException("Error: No arc tree found");
        }
    }

    @Override
    public void outputArgs(IndentedWriter indentedWriter, SerializationContext serializationContext) {

    }

    public boolean isRdfType(ArcTree tree) {
        return tree.isNonEmptyTerminal() &&
                tree.getCurrentNode().hasTriple() &&
                tree.getCurrentNode().getTriple().getPredicate().hasURI(RDF_TYPE);
    }

    public boolean isConceptAttribute(ArcTree tree) {
        return  tree.hasChildren() &&
                tree.getChildren().get(0).getCurrentNode().hasTriplePath() &&
                tree.getChildren().get(0).getCurrentNode().getTriplePath().getSubject().getName().equalsIgnoreCase("codeList");
    }

    public void handleConceptAttribute(ArcTree tree, FhirQueryContext context) {
        ArcTree part1 = tree.getChildren().get(0).getChildren().get(0);
        ArcTree part2 = tree.getChildren().get(0).getChildren().get(1);

        String system;
        String code;

        if(part1.getCurrentNode().getTriple().getPredicate().getURI().equalsIgnoreCase("http://hl7.org/fhir/system")) {
            system = part1.getChildren().get(0).getCurrentNode().getTriple().getObject().getLiteralValue().toString();
            code = part2.getChildren().get(0).getCurrentNode().getTriple().getObject().getLiteralValue().toString();
        } else {
            system = part2.getChildren().get(0).getCurrentNode().getTriple().getObject().getLiteralValue().toString();
            code = part1.getChildren().get(0).getCurrentNode().getTriple().getObject().getLiteralValue().toString();
        }

        context.addCodeCriterion(system, code);
    }

    private String identifyResource(ArcTree tree) {
        String uri = tree.getCurrentNode().getTriple().getObject().getURI();

        if (uri.startsWith(FHIR_PREFIX)) {
            return uri.substring(FHIR_PREFIX.length() + 1);
        } else {
            throw new RuntimeException("This is not a FHIR URI " + uri);
        }
    }

    @Override
    public int hashCode() {
        return 0;
    }

    @Override
    public boolean equalTo(Op op, NodeIsomorphismMap nodeIsomorphismMap) {
        return false;
    }

    private final class QueryIterExtension extends QueryIter {
        private IQuery<Bundle> fhirSearch;
        private List<Bundle> bundles;
        private QueryIterator results;
        private IGenericClient client;
        private Collection<FhirQueryContext> queryContexts;

        private QueryIterExtension(ExecutionContext execCxt, QueryIterator input, Collection<FhirQueryContext> queryContexts) {
            super(execCxt);
            this.queryContexts = queryContexts;
        }

        @Override
        protected boolean hasNextBinding() {
            if (bundles == null) {
                bundles = new ArrayList<>();
                if(queryContexts != null) {
                    for (FhirQueryContext fhirQueryContext : queryContexts) {
                        bundles.add((Bundle) fhirQueryContext.executeQuery());
                    }
                }
            }
            if (results == null) {
                return bundleToResultSet();
            } else if (results.hasNext()) {
                return true;
            } else {
//                if (bundle.getLink(IBaseBundle.LINK_NEXT) == null) {
//                    return false;
//                } else {
//                    bundle = client.loadPage().next(bundle).execute();
//                    return bundleToResultSet();
//                }
                return false; //TODO Handle paging issues later
            }
        }

        private boolean bundleToResultSet() {
            Bundle masterBundle;
            if(bundles.size() == 1) {
                masterBundle = bundles.get(0);
            } else {
                masterBundle = new Bundle();
                for (Bundle bundle : bundles) {//TODO Hack: merging all bundles - probably a better jena way exists.
                    for (Bundle.BundleEntryComponent entry : bundle.getEntry()) {
                        masterBundle.addEntry().setResource(entry.getResource());
                    }
                }
            }

            String encodeResourceToString = ctx.newRDFParser().encodeResourceToString(masterBundle);
            DatasetGraph dataset = RDFParser.create().fromString(encodeResourceToString).lang(RDFLanguages.TTL).build()
                    .toDatasetGraph();

            results = new QueryEngineMain(sourceOp, dataset, BindingRoot.create(), new Context()).getPlan().iterator();
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
