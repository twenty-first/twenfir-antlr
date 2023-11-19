package it.twenfir.antlr.ast;

/**
 * A generic AST Node.
 * Useful for inner nodes without own properties, but with interesting children.
 */
public class Node extends AstNode {

    public Node(Location location) {
        super(location);
    }
    
}
