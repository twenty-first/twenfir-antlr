package it.twenfir.antlr.api;

import org.antlr.v4.runtime.ANTLRErrorListener;

import it.twenfir.antlr.ast.AstNode;

/**
 * Error reporting interface for parsers that build ASTs
 */
public interface ErrorListener extends ANTLRErrorListener {

	/**
	 * Notify an AST building error.
	 * 
	 * @param node	the node that was being handled when the error occurred
	 * @param msg	the error message
	 */
	public void astError(AstNode node, String msg);

	/**
	 * Notify an AST building error in the presence of an exception.
	 * 
	 * @param node	the node that was being handled when the error occurred
	 * @param msg	the error message
	 * @param e		the exception that notified the error in the first place
	 */
	public void astError(AstNode node, String msg, RuntimeException e);

	/**
	 * Notify an AST building warning.
	 * 
	 * @param node	the node that was being handled when the warning condition occurred
	 * @param msg	the warning message
	 */
	public void astWarning(AstNode node, String msg);
	
	/**
	 * Indicate whether errors have occured.
	 * 
	 * @return true if one or more errors have occurred, false otherwise
	 */
	public boolean isErrors();
}
