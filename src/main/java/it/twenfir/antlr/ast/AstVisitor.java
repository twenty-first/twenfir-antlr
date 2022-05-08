package it.twenfir.antlr.ast;

/**
 * Base interface for visitors.
 * Provides an interface to compute aggregate values over AST's. Specific visitors should extends
 * this interface and declare a <code>visit</code> override method for each node type in their
 * AST's.
 * 
 * @param <ValueT> the type of the value returned by this visitor
 */
public interface AstVisitor<ValueT> {

	/**
	 * Default result of visiting a leaf node.
	 * 
	 * @return the default result of a visit
	 */
	ValueT defaultValue();

	/**
	 * Default method to aggregate visit results, e.g. over a node's children.
	 * 
	 * @param accumulator the running result of previous visits
	 * @param value       a value to add, e.g. the result of the last visit
	 * @return            the combination of aggregator and value
	 */
	ValueT aggregate(ValueT accumulator, ValueT value);

	/**
	 * Generic visit to the current node.
	 * @param node the node to visit
	 * @return     the result of the visit
	 */
	ValueT visit(AstNode node);

	/**
	 * Generic visit over a node's children.
	 * 
	 * @param node the node whose children should be visited
	 * @return     the aggregated result of visiting the node's children
	 */
	ValueT visitChildren(AstNode node);

}
