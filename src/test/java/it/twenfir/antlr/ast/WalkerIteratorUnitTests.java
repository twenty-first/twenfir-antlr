package it.twenfir.antlr.ast;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class WalkerIteratorUnitTests {

    @Test
    public void test() {
        Node node = new Node(null);
        node.addChild(new Node(null));
        node.addChild(new Node(null));
        node.getChildren().next().addChild(new Node(null));

        int count = 0;
        WalkerIterator iter = new WalkerIterator(node);
        while ( iter.hasNext() ) {
            iter.next();
            count++;
        }
        assertEquals(3, count);
    }
}
