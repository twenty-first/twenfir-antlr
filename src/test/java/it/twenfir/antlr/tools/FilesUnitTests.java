package it.twenfir.antlr.tools;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.Arrays;

import org.junit.Test;

public class FilesUnitTests {
	
    @Test
    public void readResourceTest() throws IOException {
    	String s = Files.readFile("logback.xml", Arrays.asList("classpath:"));
    	assertTrue(s.indexOf("twenfir") != -1);
    }

}
