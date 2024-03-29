package it.twenfir.antlr.parser;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenFactory;
import org.antlr.v4.runtime.TokenSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A TokenSource that logs tokens to SLF4J.
 */
public class LoggingTokenSource implements TokenSource {

    private Logger log = LoggerFactory.getLogger(getClass());

    private Lexer lexer;

	/**
	 * Constructor.
	 * 
	 * @param lexer the lexer from which to extract tokens
	 */
	public LoggingTokenSource(Lexer lexer) {
		this.lexer = lexer;
	}

	@Override
	public Token nextToken() {
        Token t = lexer.nextToken();
        if ( log.isTraceEnabled() ) {
            log.trace(t.toString());
        }
        return t;
	}

	@Override
	public int getLine() {
		return lexer.getLine();
	}

	@Override
	public int getCharPositionInLine() {
		return lexer.getCharPositionInLine();
	}

	@Override
	public CharStream getInputStream() {
		return lexer.getInputStream();
	}

	@Override
	public String getSourceName() {
		return lexer.getSourceName();
	}

	@Override
	public void setTokenFactory(TokenFactory<?> factory) {
		lexer.setTokenFactory(factory);
	}

	@Override
	public TokenFactory<?> getTokenFactory() {
		return lexer.getTokenFactory();
	}

}
