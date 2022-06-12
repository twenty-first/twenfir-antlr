package it.twenfir.antlr.tools;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import it.twenfir.antlr.exception.FileException;

public class FilesIntegrationTests {
	
    @Test
    public void readResourceTest() throws IOException {
    	String s = Files.readFile("logback.xml", Arrays.asList("classpath:"));
    	assertTrue(s.indexOf("twenfir") != -1);
    }

    @Test
    public void makeDirTest() throws FileException, IOException {
        File temp = java.nio.file.Files.createTempDirectory("twenfir").toFile();
        temp.deleteOnExit();
        String base = temp.getAbsolutePath();
        Path path = Paths.get(base, "makedir");
        path = Files.makeDir(path.toString());
        File file = path.toFile();
        assertTrue(file.exists() && file.isDirectory());
    }
}
