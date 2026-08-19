package it.twenfir.antlr.api;

/**
 * A source file as read by Files.readFile()
 * @param path	the file's path
 * @param text	the file's contents
 */
public class SourceFile {
	
	private String path;
	private String text;
	
	public SourceFile(String path, String text) {
		this.path = path;
		this.text = text;
	}

	public String getPath() {
		return path;
	}
	
	public String getText() {
		return text;
	}
}
