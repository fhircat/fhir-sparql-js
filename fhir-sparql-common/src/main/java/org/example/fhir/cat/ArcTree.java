package org.example.fhir.cat;

import java.util.List;

public class ArcTree {
    private TriplePattern currentNode;
    private List<ArcTree> children;

    public ArcTree() {
    }

    public ArcTree(TriplePattern currentNode) {
        this.currentNode = currentNode;
    }

    public ArcTree(TriplePattern currentNode, List<ArcTree> children) {
        this.currentNode = currentNode;
        this.children = children;
    }

    public TriplePattern getCurrentNode() {
        return currentNode;
    }

    public void setCurrentNode(TriplePattern currentNode) {
        this.currentNode = currentNode;
    }

    public List<ArcTree> getChildren() {
        return children;
    }

    public void setChildren(List<ArcTree> children) {
        this.children = children;
    }

    public void addChild(ArcTree child) {
        this.children.add(child);
    }

    public boolean hasChildren() {
        return children != null && children.size() > 0;
    }

    public boolean isRoot() {
        return currentNode == null;
    }

    public boolean isTerminal() {
        return !hasChildren();
    }

    public boolean isNonEmptyTerminal() {
        return isTerminal() && hasNode();
    }

    public boolean hasNode() {
        return currentNode != null;
    }
}
