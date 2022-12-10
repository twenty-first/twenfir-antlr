package it.twenfir.antlr.ast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * The base class of AST nodes.
 * Provides access to children nodes and to source code location information.
 * Position is expressed as source code line and character as well as start and end tokens.
 */
public abstract class AstNode {
	
    private Location location;
    private List<AstNode> children = new ArrayList<>();

    /**
     * Constructor.
     * 
     * @param location the node's position within the source file
     */
    public AstNode(Location location) {
        this.location = location;
    }

    /** 
     * Return the line where the node's text is located in the source code.
     * 
     * @return the line number
     */
    public int getLine() {
        return location.getLine();
    }

    /** 
     * Return the position in the line where the node's text is located in the source code.
     * 
     * @return the position in the line
     */
    public int getPos() {
        return location.getPos();
    }

    /** 
     * Return the start token in the ANTLR token stream.
     * 
     * @return the start token
     */
    public int getStart() {
        return location.getStart();
    }

    /** 
     * Return the end token in the ANTLR token stream.
     * 
     * @return the end token
     */
    public int getEnd() {
        return location.getEnd();
    }

    /** 
     * Add a child node.
     * 
     * @param child the child node
     */
    public void addChild(AstNode child) {
    	if ( child != null ) {
        	children.add(child);
    	}
    }
    
    /** 
     * Add a collection of children nodes.
     * 
     * @param <N>      the children's type
     * @param children the children nodes
     */
    public <N extends AstNode> void addChildren(Collection<N> children) {
    	if ( children != null ) {
        	this.children.addAll(children);
    	}
    }
    
    /** 
     * Return all the node's children.
     * 
     * @return an iterator over the node's children
     */
    public Iterator<AstNode> getChildren() {
    	return children.iterator();
    }
    
    /** 
     * Return a single child node of the specified type, assumed to be the only such node.
     * 
     * @param <ChildT> the type of the requested child
     * @param clazz    the class object of the requested child node
     * @return         the child node, or <code>null</code> if none exists
     */
    public <ChildT extends AstNode> ChildT getChild(Class<ChildT> clazz) {
    	ChildrenIterator<ChildT> iter = new ChildrenIterator<ChildT>(getChildren(), clazz);
    	return iter.hasNext() ? iter.next() : null;
    }
    
    /** 
     * Return all the children nodes of the specified type.
     * 
     * @param <ChildT> the type of the requested children
     * @param clazz    the class object of the requested children
     * @return         an iterator over children of the specified type
     */
    public <ChildT extends AstNode> Iterator<ChildT> getChildren(Class<ChildT> clazz) {
    	return new ChildrenIterator<ChildT>(getChildren(), clazz);
    }
    
    /** 
     * Return a single descendant node of the specified type, assumed to be the only such node.
     * 
     * @param <ChildT> the type of the requested child
     * @param clazz    the class object of the requested child node
     * @return         the descendant node, or <code>null</code> if none exists
     */
    public <ChildT extends AstNode> ChildT getDescendant(Class<ChildT> clazz) {
    	ChildrenIterator<ChildT> iter = new ChildrenIterator<ChildT>(new WalkerIterator(this), clazz);
    	return iter.hasNext() ? iter.next() : null;
    }
    
    /** 
     * Return all the descendant nodes of the specified type.
     * 
     * @param <ChildT> the type of the requested children
     * @param clazz    the class object of the requested children
     * @return         an iterator over descendants of the specified type
     */
    public <ChildT extends AstNode> Iterator<ChildT> getDescendants(Class<ChildT> clazz) {
    	return new ChildrenIterator<ChildT>(new WalkerIterator(this), clazz);
    }
	
    /** 
     * Default implementation of <code>accept</code> for <code>AstVisitor</code>'s.
     * 
     * @param <ValueT> the type returned by the visitor
     * @param visitor  the visitor
     * @return         the result of the visit
     */
    public <ValueT> ValueT accept(AstVisitor<? extends ValueT> visitor) {
    	return visitor.visit(this);
    }

    
    /** 
     * Return the class name of the node.
     * 
     * @return the class name
     */
    public String getType() {
        return this.getClass().getSimpleName();
    }
}
