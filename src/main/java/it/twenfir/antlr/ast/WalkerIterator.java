package it.twenfir.antlr.ast;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Stack;

public class WalkerIterator implements Iterator<AstNode> {

    private Stack<Iterator<AstNode>> stack = new Stack<>();
    private AstNode current;
    
    public WalkerIterator(AstNode node) {
        stack.push(node.getChildren());
    }

	private void advance() {
        while ( current == null && ! stack.empty() ) {
            if ( stack.peek().hasNext() ) {
                current = stack.peek().next();
                stack.push(current.getChildren());
            }
            else {
                stack.pop();
            }
        }
    }

    @Override
    public boolean hasNext() {
		advance();
		return current != null;
    }

    @Override
    public AstNode next() {
		advance();
		if ( current == null ) {
			throw new NoSuchElementException();
		}
		AstNode value = current;
		current = null;
		return value;
    }
    
}
