package org.example.fhir.cat;

import org.apache.jena.datatypes.RDFDatatype;
import org.apache.jena.graph.NodeFactory;
import org.apache.jena.graph.Triple;
import org.apache.jena.sparql.core.TriplePath;
import org.apache.jena.sparql.path.Path;
import org.apache.jena.sparql.path.PathFactory;

public class TriplePattern {
    private Triple triple;
    private TriplePath triplePath;

    public TriplePattern(Triple triple) {
        this.triple = triple;
    }

    public TriplePattern(TriplePath triplePath) {
        this.triplePath = triplePath;
    }

    public Triple getTriple() {
        return triple;
    }

    public TriplePath getTriplePath() {
        return triplePath;
    }

    public boolean hasTriple() {
        return triple != null;
    }

    public boolean hasTriplePath() {
        return triplePath != null;
    }
    /**
     *
     * @param subjectLabel VariableName
     * @param predicateUri Predicate URI
     * @param objectUri Predicate URI
     * @return TriplePattern
     */
    public static TriplePattern createVarPredicateObject(String subjectLabel, String predicateUri, String objectUri) {
        return new TriplePattern(
                Triple.create(
                        NodeFactory.createVariable(subjectLabel),
                        NodeFactory.createURI(predicateUri),
                        NodeFactory.createURI(objectUri)
                ));
    }

    public static TriplePattern createVarPredicateVar(String subjectLabel, String predicateUri, String objectLabel) {
        return new TriplePattern(
                Triple.create(
                        NodeFactory.createVariable(subjectLabel),
                        NodeFactory.createURI(predicateUri),
                        NodeFactory.createURI(objectLabel)
                ));
    }

    public static TriplePattern createVarPredicateLiteral(String subjectLabel, String predicateUri, String literal) {
        return new TriplePattern(
                Triple.create(
                        NodeFactory.createVariable(subjectLabel),
                        NodeFactory.createURI(predicateUri),
                        NodeFactory.createLiteral(literal)
                ));
    }

    public static TriplePattern createVarPathVar(String subjectLabel, String uri1, String uri2, String objectLabel) {
        return new TriplePattern(
                new TriplePath(
                    NodeFactory.createVariable(subjectLabel),
                    PathFactory.pathSeq(PathFactory.pathLink(NodeFactory.createURI(uri1)), PathFactory.pathLink(NodeFactory.createURI(uri2))),
                    NodeFactory.createVariable(objectLabel)
                )
        );
    }
}
