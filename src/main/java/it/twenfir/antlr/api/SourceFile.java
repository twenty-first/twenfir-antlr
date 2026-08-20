package it.twenfir.antlr.api;

/**
 * A source file as read by Files.readFile()
 */
public class SourceFile {
	
	private String path;
	private String text;

	/*
	 * Constructor
	 * 
	 * @param path	the file's path
	 * @param text	the file's contents
	 */
	public SourceFile(String path, String text) {
		this.path = path;
		this.text = text;
	}

	/**
	 * Getter
	 * 
	 * @return the source file path
	 */
	public String getPath() {
		return path;
	}
	
	/**
	 * Getter
	 * 
	 * @return the source file text
	 */
	public String getText() {
		return text;
	}
}
