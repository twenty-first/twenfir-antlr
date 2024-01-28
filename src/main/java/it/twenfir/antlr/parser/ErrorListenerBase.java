package it.twenfir.antlr.parser;

import org.antlr.v4.runtime.BaseErrorListener;

import it.twenfir.antlr.api.ErrorListener;
import it.twenfir.antlr.ast.AstNode;

/**
 * Helper implementation of the {@link it.twenfir.antlr.api.ErrorListener}
 */
public abstract class ErrorListenerBase extends BaseErrorListener implements ErrorListener {

	@Override
	public void astError(AstNode node, String msg) {
	}

	@Override
	public void astError(AstNode node, String msg, RuntimeException e) {
	}

	@Override
	public void astWarning(AstNode node, String msg) {
	}

	@Override
	public boolean isErrors() {
		return false;
	}
}
