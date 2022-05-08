package it.twenfir.antlr.ast;

import java.util.Iterator;

/**
 * A helper base class for concrete visitor implementations.
 * Subclasses should provide a default visit() override for each of the types in the AST.
 * 
 * @param <ValueT> the type of the value returned by this visitor
 */
public abstract class BaseAstVisitor<ValueT> implements AstVisitor<ValueT> {

	/**
	 * Default constructor.
	 */
	public BaseAstVisitor() {
	}

	/**
	 * Default leaf node implementation for visitors that compute aggregate values over nodes.
	 * 
	 * @return <code>null</code> 
	 */
	@Override
	public ValueT defaultValue() {
		return null;
	}

	/**
	 * Default internal node implementation for visitors that compute aggregate values over nodes.
	 * 
	 * @param accumulator the running result of previous visits
	 * @param value       a value to add, e.g. the result of the last visit
	 * @return            the passed value
	 */
	@Override
	public ValueT aggregate(ValueT accumulator, ValueT value) {
		return value;
	}

	/**
	 * Default implementation of visiting a node.
	 * Visit children and return their aggregated value.
	 * @param node the node to be visited
	 * @return     the aggregated value
	 */
	@Override
	public ValueT visit(AstNode node) {
		return visitChildren(node);
	}

	/**
	 * Helper method that visits children and computes their aggregated value.
	 * @param node the node whose children should be visited
	 * @return     the children's aggregated value
	 */
	@Override
	public ValueT visitChildren(AstNode node) {
		ValueT result = defaultValue();
		Iterator<AstNode> i = node.getChildren();
		while ( i.hasNext() ) {
			AstNode c = i.next();
			result = aggregate(result, c.accept(this));
		}
		return result;
	}

}
