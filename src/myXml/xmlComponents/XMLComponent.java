package myXml.xmlComponents;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public abstract class XMLComponent {
    protected String tag;
    protected Set<Attribute> attributes = new LinkedHashSet<>();
    protected List<XMLComponent> children = new ArrayList<>();
    protected XMLComponent parent = null;

    public XMLComponent(String tag) {
        this.tag = tag;
    }

    abstract String generateXml(int depth);

    public void addAttribute(String name, String val) {
        attributes.add(new Attribute(name, val));
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public List<XMLComponent> getSiblings() {
        if (parent == null) {
            return new ArrayList<>();
        }
        List<XMLComponent> siblings = parent.getChildren();
        siblings.remove(this);
        return siblings;
    }

    public XMLComponent getNext() {
        if (parent == null) return null;
        List<XMLComponent> siblings = parent.getChildren();
        int idx = siblings.indexOf(this);
        if (++idx > siblings.size() - 1) idx = 0;
        return siblings.get(idx);

    }

    public List<XMLComponent> getChildren() {
        return children;
    }

    public void setChildren(List<XMLComponent> children) {
        this.children = children;
    }

    public XMLComponent getParent() {
        if (parent == null) return this;
        return parent;
    }

    public void setParent(XMLComponent parent) {
        this.parent = parent;
    }

//    public List<XMLLeaf> getElements() {
//        return elements;
//    }

    public void addChild(XMLComponent node) {
        node.setParent(this);
        children.add(node);
    }

    public XMLComponent getFirstChild() {
        if (children.isEmpty()) return this;
        return children.getFirst();
    }

    public void removeChildNode(XMLComponent current) {
        children.remove(current);
    }

}

