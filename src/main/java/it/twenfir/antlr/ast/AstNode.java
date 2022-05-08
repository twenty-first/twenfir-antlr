package it.twenfir.antlr.ast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * The base class of AST nodes
 * 
 * Provides access to children nodes and to source code location information.
 * Position is expressed as source code line and character as well as start and end tokens.
 */
public abstract class AstNode {
	
    private Location location;
    private List<AstNode> children = new ArrayList<>();

    public AstNode(Location location) {
        this.location = location;
    }

    
    /** 
     * Return the line where the node's text is located in the source code
     * 
     * @return the line number
     */
    public int getLine() {
        return location.getLine();
    }

    
    /** 
     * Return the position in the line where the node's text is located in the source code
     * 
     * @return the position in the line
     */
    public int getPos() {
        return location.getPos();
    }

    
    /** 
     * Return the start token in the ANTLR token stream
     * 
     * @return the start token
     */
    public int getStart() {
        return location.getStart();
    }

    
    /** 
     * Return the end token in the ANTLR token stream
     * 
     * @return the end token
     */
    public int getEnd() {
        return location.getEnd();
    }

    
    /** 
     * Add a child node
     * 
     * @param child the child node
     */
    public void addChild(AstNode child) {
    	if ( child != null ) {
        	children.add(child);
    	}
    }
    
    
    /** 
     * Add a collection of children nodes
     * 
     * @param children the children nodes
     */
    public <N extends AstNode> void addChildren(Collection<N> children) {
    	if ( children != null ) {
        	this.children.addAll(children);
    	}
    }

    
    /** 
     * Return a single child node of the specified type, assumed to be the only such node
     * 
     * @param clazz the class of the requested child node
     * @return the child node, or null if none exists
     */
    public <ChildT extends AstNode> ChildT getChild(Class<ChildT> clazz) {
    	ChildrenIterator<ChildT> iter = new ChildrenIterator<ChildT>(getChildren(), clazz);
    	return iter.hasNext() ? iter.next() : null;
    }
    
    
    /** 
     * Return all the node's children
     * 
     * @return an iterator over the node's children
     */
    public Iterator<AstNode> getChildren() {
    	return children.iterator();
    }
    
    
    /** 
     * Return all the children nodes of the specified type
     * 
     * @param clazz the class of the requested children
     * @return an iterator over children of the specified type
     */
    public <ChildT extends AstNode> Iterator<ChildT> getChildren(Class<ChildT> clazz) {
    	return new ChildrenIterator<ChildT>(getChildren(), clazz);
    }
    
	
    /** 
     * Default implementation of accept() for AstVisitor's
     * 
     * @param visitor the visitor
     * @return the result of the visit
     */
    public <ValueT> ValueT accept(AstVisitor<? extends ValueT> visitor) {
    	return visitor.visit(this);
    }

    
    /** 
     * Return the class name of the node
     * 
     * @return the class name
     */
    public String getType() {
        return this.getClass().getSimpleName();
    }
}
