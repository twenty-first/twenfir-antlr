package it.twenfir.antlr.ast;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;
import org.antlr.v4.runtime.tree.RuleNode;

/**
 * Provide methods to help building AST's 
 */
public class AstHelper {

	/** 
	 * Visit children of an ANTLR parse tree node and add them as children of the current AST node.
	 * Assumes that the default implementation of <code>visit</code> returns null making it possible
	 * to override it only for interesting nodes.
	 * 
	 * @param <V> 	  the visitor's type
	 * @param visitor the visitor implementation
	 * @param node    the current parse tree node 
	 * @param parent  the AST node to which children will be added
	 */
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

	/** 
	 * Create a Location instance from an ANTLR rule context.
	 * 
	 * @param context the current ANTLR context
	 * @return        the newly created instance
	 */
	public static Location location(ParserRuleContext context) {
        Token start = context.getStart();
        return new Location(start.getLine(), start.getCharPositionInLine(), start.getTokenIndex(), 
                context.getStop().getTokenIndex());
    }
}
