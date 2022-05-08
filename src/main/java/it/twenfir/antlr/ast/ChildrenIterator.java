package it.twenfir.antlr.ast;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * An iterator that only returns elements of the specified class
 */
public class ChildrenIterator<NodeT extends AstNode> implements Iterator<NodeT> {

	Iterator<AstNode> iter;
	NodeT current;
	Class<? extends NodeT> clazz;
	
	/**
	 * Constructor
	 * 
	 * @param iter an iterator from which to extract elements
	 * @param clazz the class object of the type elements must belong to
	 */
	public ChildrenIterator(Iterator<AstNode> iter, Class<? extends NodeT> clazz) {
		this.iter = iter;
		this.clazz = clazz;
	}

	@SuppressWarnings("unchecked")
	private void advance() {
		while ( iter.hasNext() ) {
			if ( current != null ) {
				break;
			}
			AstNode n = iter.next();
			if ( clazz.isInstance(n) ) {
				current = (NodeT) n;
			}
		}
	}
	
	
	/** 
	 * Whether the iterator has more elements
	 * 
	 * @return true if there are elements available, false otherwise
	 */
	@Override
	public boolean hasNext() {
		advance();
		return current != null;
	}

	
	/** 
	 * Return the current element and advance to the next position
	 * 
	 * @return the current element 
	 */
	@Override
	public NodeT next() {
		advance();
		if ( current == null ) {
			throw new NoSuchElementException();
		}
		NodeT value = current;
		current = null;
		return value;
	}

}
