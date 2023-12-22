package it.twenfir.antlr.api;

import org.antlr.v4.runtime.ANTLRErrorListener;

import it.twenfir.antlr.ast.AstNode;

public interface ErrorListener extends ANTLRErrorListener {

	/**
	 * Notify an AST building error.
	 * 
	 * @param node 
	 * @param msg
	 */
	public void astError(AstNode node, String msg);
	public void astError(AstNode node, String msg, RuntimeException e);
	public void astWarning(AstNode node, String msg);
}
