package it.twenfir.antlr.ast;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;
import org.antlr.v4.runtime.tree.RuleNode;

import it.twenfir.antlr.exception.AstException;

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
	 * Visit the single child of an ANTLR parse tree node and return the corresponding AST node.
	 * Assumes that the default implementation of <code>visit</code> returns null making it possible
	 * to override it only for interesting nodes.
	 * 
	 * @param <V> 	  the visitor's type
	 * @param visitor the visitor implementation
	 * @param node    the current parse tree node 
	 * @return        the AST node corresponding to the current parse tree node
	 */
	public static <V extends ParseTreeVisitor<? extends AstNode>> AstNode visitChild(V visitor, RuleNode node) {
		int n = node.getChildCount();
		if ( n > 1 ) {
			throw new AstException("Parse tree node has multiple children");
		}
		else if ( n == 1 ) {
			ParseTree c = node.getChild(0);
			AstNode child = c.accept(visitor);
			if ( child != null ) {
				return child;
			}
		} 
		return null;
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

	public static <V extends ParseTreeVisitor<? extends AstNode>> AstNode visit(V visitor, ParserRuleContext ctx) {
		int n = ctx.getChildCount();
		if ( n == 1 ) {
			ParseTree c = ctx.getChild(0);
			return c.accept(visitor);
		}
		else {
			Location location = location(ctx);
			Node node = new Node(location);
			for (int i=0; i<n; i++) {
				ParseTree c = ctx.getChild(i);
				AstNode child = c.accept(visitor);
				if ( child != null ) {
					node.addChild(child);
				}
			}
			return node;
		}
	}

}
