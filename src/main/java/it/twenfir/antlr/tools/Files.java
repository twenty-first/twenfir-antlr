package it.twenfir.antlr.tools;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import it.twenfir.antlr.exception.FileException;

/**
 * Utility functions to work with files.
 */
public class Files {

	private static Logger log = LoggerFactory.getLogger(Files.class);

	/**
	 * Search for a source file in a set of directories and/or classpath locations.
	 * When searching inside directories filenames are treated as case-insensitive.
	 * Return the file contents as a string. Assume the ISO 8859/1 charset.
	 * 
	 * @param name the name of the source file to be read
	 * @param path a list of directories or classpath locations where the file may be found
	 * @return a string containing the file's text
	 * @throws FileNotFoundException if the file is not found
	 * @throws IOException in case of unexpected I/O errors
	 */	public static String readFile(String name, List<String> path) throws IOException {
		return readFile(name, path, StandardCharsets.ISO_8859_1);
	}

	/**
	 * Search for a source file in a set of directories and/or classpath locations.
	 * When searching inside directories filenames are treated as case-insensitive.
	 * Return the file contents as a string.
	 * 
	 * @param name the name of the source file to be read
	 * @param path a list of directories or classpath locations where the file may be found
	 * @param charset the character set in which the file is encoded
	 * @return a string containing the file's text
	 * @throws FileNotFoundException if the file is not found
	 * @throws IOException in case of unexpected I/O errors
	 */
	public static String readFile(String name, List<String> path, Charset charset) throws IOException {
		for ( String d: path ) {
			if ( d.startsWith("classpath:") ) {
				String resource = d.substring("classpath:".length());
				if ( resource.length() > 0 ) {
					resource += "/";
				}
				resource += name;
				ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
				try ( InputStream is = classLoader.getResourceAsStream(resource) ) {
					List<String> lines = 
							new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8))
							.lines()
							.collect(Collectors.toList());
					StringBuilder read = new StringBuilder();
					for ( String l: lines ) {
						read.append(l);
						read.append('\n');
					}
					log.debug("Read resource " + resource);
					return read.toString();
				}
				catch ( Exception e ) {
					log.warn("Error reading resource: " + e.getMessage());
				}
			}
			else {
				try ( Stream<Path> files = java.nio.file.Files.list(
						FileSystems.getDefault().getPath(d)) ) {
					Optional<Path> o = files
							.filter(p -> p.getFileName().toString().compareToIgnoreCase(name) == 0)
							.findFirst();
					if ( o.isPresent() ) {
						Path p = o.get();
						// JDK 11
						// String read = java.nio.file.Files.readString(p, charset);
						StringBuilder read = new StringBuilder();
						List<String> lines = java.nio.file.Files.readAllLines(p, charset);
						for ( String l: lines ) {
							read.append(l);
							read.append('\n');
						}
						log.debug("Read file " + p.toString());
						return read.toString();
					}
				}
				catch ( Exception e ) {
					log.warn("Error reading file: " + e.getMessage());
				}
			}
		}
		throw new FileNotFoundException("File " + name + " not found");
	}

	
	/** 
	 * Ensures that the given path is an existing directory.
	 * 
	 * @param path a directory path to verify and, if necessary, create.
	 * @return the corresponding java.nio.file.Path object
	 * @throws FileException if the given path is not a directory
	 */
	public static Path makeDir(String path) {
		Path p = FileSystems.getDefault().getPath(path != null ? path : ".");
		File dir = p.toFile();
		if ( ! dir.exists() ) {
			dir.mkdirs();
		}
		else if ( ! dir.isDirectory() ) {
			throw new FileException("Not a directory");
		}
		return p;
	}
}
