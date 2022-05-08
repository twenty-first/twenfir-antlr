package it.twenfir.antlr.ast;

/**
 * A position in a source file, both as line and character, and as start and end token index
 */
public class Location {
    
    private int line;
    private int pos;
    private int start;
    private int end;

    /**
     * Constructor
     * 
     * @param line the line in the source file
     * @param pos the character position in the line
     * @param start the start token
     * @param end the end token
     */
    public Location(int line, int pos, int start, int end) {
        this.line = line;
        this.pos = pos;
        this.start = start;
        this.end = end;
    }

    
    /** 
     * Return the source file line
     * 
     * @return the line number
     */
    public int getLine() {
        return line;
    }

    
    /** 
     * Return the position in the line
     * 
     * @return the position
     */
    public int getPos() {
        return pos;
    }

    
    /** 
     * Return the start token
     * 
     * @return the start token
     */
    public int getStart() {
        return start;
    }

    
    /** 
     * Return the end token
     * 
     * @return the end token
     */
    public int getEnd() {
        return end;
    }
    
}
