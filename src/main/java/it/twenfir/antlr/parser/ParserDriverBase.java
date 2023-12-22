package it.twenfir.antlr.parser;

import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import org.slf4j.Logger;

import it.twenfir.antlr.api.ErrorListener;
import it.twenfir.antlr.ast.AstNode;
import it.twenfir.antlr.exception.AstException;
import it.twenfir.antlr.exception.ParseException;

/**
 * Base class for parser drivers
 */
public class ParserDriverBase extends BaseErrorListener implements ErrorListener {
    
	private Logger log;
	private boolean throwOnError;
	private String name;
	private String fileName;
	private boolean errors = false;

	/**
	 * Constructor.
	 * 
	 * @param name the name of this parser
	 * @param log  an SLF4J logger
	 */
	public ParserDriverBase(String name, Logger log) {
		this(name, "input", false, log);
	}

	/**
	 * Constructor.
	 * 
	 * @param name         the name of this parser
	 * @param fileName     the name of the input file, "input" if missing
	 * @param throwOnError whether to throw exceptions in case of parsing errors
	 * @param log          an SLF4J logger
	 */
	public ParserDriverBase(String name, String fileName, boolean throwOnError, Logger log) {
        this.name = name;
        this.fileName = fileName;
		this.throwOnError = throwOnError;
		this.log = log;
	}
	
	@Override
	public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, 
			int charPositionInLine, String msg, RecognitionException e) {
		errors = true;
		log.error(String.format("%s: %s(%d, %d): %s", name, fileName, line, charPositionInLine, msg));
		if ( throwOnError && e != null ) {
			throw new ParseException("Parse error", e);
		}
	}

	@Override
	public void astError(AstNode node, String msg) {
		errors = true;
		log.error(String.format("%s: %s(%d, %d): %s", name, fileName, node.getLine(), node.getPos(), msg));
	}

	@Override
	public void astError(AstNode node, String msg, RuntimeException e) {
		astError(node, msg);
		if ( throwOnError && e != null ) {
			throw new AstException("AST error", e);
		}
	}

	@Override
	public void astWarning(AstNode node, String msg) {
		log.warn(String.format("%s: %s(%d, %d): %s", name, fileName, node.getLine(), node.getPos(), msg));
	}

	/**
	 * Whether to throw exceptions in case of parsing errors.
	 * 
	 * @return true if exceptions should be thrown, false otherwise
	 */
	public boolean isThrowOnError() {
		return throwOnError;
	}

	/**
	 * The name of the current parser
	 * 
	 * @return the parser's name
	 */
	public String getName() {
		return name;
	}

	/**
	 * The name of the input file.
	 * 
	 * @return the input filename
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * whether parsing errors occurred.
	 * 
	 * @return true if parsing caused errors, false otherwise
	 */
	public boolean isErrors() {
		return errors;
	}

}
