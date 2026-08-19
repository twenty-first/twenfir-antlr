package it.twenfir.antlr.parser;

import it.twenfir.antlr.api.FileErrorListener;

/**
 * Helper implementation of the {@link it.twenfir.antlr.api.ErrorListener}
 */
public abstract class FileErrorListenerBase implements FileErrorListener {

	@Override
	public void readError(String name, String msg) {
	}

	@Override
	public void readError(String name, String msg, RuntimeException e) {
	}

	@Override
	public void readWarning(String name, String msg) {
	}

	@Override
	public boolean isErrors() {
		return false;
	}
}
