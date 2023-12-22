package it.twenfir.antlr.parser;

import org.antlr.v4.runtime.BaseErrorListener;

import it.twenfir.antlr.api.ErrorListener;
import it.twenfir.antlr.ast.AstNode;

public class ErrorListenerBase extends BaseErrorListener implements ErrorListener {

	@Override
	public void astError(AstNode node, String msg) {
	}

	@Override
	public void astError(AstNode node, String msg, RuntimeException e) {
	}

	@Override
	public void astWarning(AstNode node, String msg) {
	}

}
