package it.twenfir.antlr.ast;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;
import org.antlr.v4.runtime.tree.RuleNode;

public class AstHelper {

    public static <V extends ParseTreeVisitor<? extends AstNode>> void visitChildren(V visitor, RuleNode node, AstNode parent) {
		int n = node.getChildCount();
		for (int i=0; i<n; i++) {

			ParseTree c = node.getChild(i);
			AstNode child = c.accept(visitor);
			if ( child != null ) {
				parent.addChild(child);
			}
		}
	}

    public static Location location(ParserRuleContext context) {
        Token start = context.getStart();
        return new Location(start.getLine(), start.getCharPositionInLine(), start.getTokenIndex(), 
                context.getStop().getTokenIndex());
    }
}
