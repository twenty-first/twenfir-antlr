package it.twenfir.antlr.parser;

import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;

import it.twenfir.antlr.ast.AstNode;

/**
 * A default implementation of the {@link it.twentfir.antlr.api.ErrorListener} interface that just
 * keeps track of whether errors have occurred.
 */
public class DefaultErrorListener extends ErrorListenerBase {

	private boolean errors = false;
	
	public DefaultErrorListener() {
	}

	@Override
	public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine,
			String msg, RecognitionException e) {
		errors = true;
	}
	
	@Override
	public void astError(AstNode node, String msg) {
		errors = true;
	}

	@Override
	public void astError(AstNode node, String msg, RuntimeException e) {
		errors = true;
	}
	
	@Override
	public boolean isErrors() {
		return errors;
	}
}
