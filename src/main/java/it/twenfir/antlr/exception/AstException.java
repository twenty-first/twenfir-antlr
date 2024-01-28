package it.twenfir.antlr.exception;

/**
 * Exception thrown on AST building errors.
 */
public class AstException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public AstException(String message) {
		super(message);
	}

	public AstException(String message, Throwable cause) {
		super(message, cause);
	}

}
