package it.twenfir.antlr.ast;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class WalkerIteratorUnitTests {
    static class Node extends AstNode {

        public Node() {
            super(null);
        }
        
    }

    @Test
    public void test() {
        Node node = new Node();
        node.addChild(new Node());
        node.addChild(new Node());
        node.getChildren().next().addChild(new Node());

        int count = 0;
        WalkerIterator iter = new WalkerIterator(node);
        while ( iter.hasNext() ) {
            iter.next();
            count++;
        }
        assertEquals(3, count);
    }
}
