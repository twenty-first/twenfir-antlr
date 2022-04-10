package io.github.twenty_first.antlr.parser.test;

import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import org.slf4j.Logger;

public class Helper extends BaseErrorListener {

	Logger log;
	boolean failed = false;
	
	public Helper(Logger log) {
		this.log = log;
	}
	
	@Override
	public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line,
			int charPositionInLine, String msg, RecognitionException e) {
		log.error(String.format("(%d, %d): %s", line, charPositionInLine, msg));
		failed = true;
	}
	
	boolean isFailed() {
		return failed;
	}

}
