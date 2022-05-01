package it.twenfir.antlr.ast;

public class Location {
    
    private int line;
    private int pos;
    private int start;
    private int end;

    public Location(int line, int pos, int start, int end) {
        this.line = line;
        this.pos = pos;
        this.start = start;
        this.end = end;
    }

    public int getLine() {
        return line;
    }

    public int getPos() {
        return pos;
    }

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }
    
}
