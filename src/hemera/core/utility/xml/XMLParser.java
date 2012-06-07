package hemera.core.utility.xml;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

/**
 * <code>XMLParser</code> defines the implementation of
 * the utility XML file parser unit that is responsible
 * for parsing out all the tags within a XML file.
 *
 * @author Yi Wang (Neakor)
 * @version 1.0.0
 */
public class XMLParser {
	/**
	 * The root <code>XMLTag</code>.
	 */
	private XMLTag root;

	/**
	 * Constructor of <code>XMLParser</code>.
	 */
	public XMLParser() {
		this.root = null;
	}
	
	/**
	 * Parse the XML linked by the given file.
	 * @param file The XML <code>File</code> to be parsed.
	 * @throws IOException If file reading is interrupted.
	 */
	public void process(final File file) throws IOException {
		final FileReader reader = new FileReader(file);
		try {
			final StreamTokenizer parser = new StreamTokenizer(reader);
			this.setupParser(parser);
			this.root = this.nextTag(parser, null);
		} finally {
			reader.close();
		}
	}

	/**
	 * Parse the XML in the given input stream.
	 * @param file The XML <code>InputStream</code> to
	 * be parsed.
	 * @throws IOException If reading is interrupted.
	 */
	public void process(final InputStream stream) throws IOException {
		final BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
		try {
			final StreamTokenizer parser = new StreamTokenizer(reader);
			this.setupParser(parser);
			this.root = this.nextTag(parser, null);
		} finally {
			reader.close();
		}
	}
	
	/**
	 * Set the parser with its properties.
	 * @param parser The <code>StreamTokenizer</code>
	 * to be setup.
	 */
	private void setupParser(final StreamTokenizer parser) {
		parser.eolIsSignificant(true);
		parser.slashSlashComments(true);
		parser.slashStarComments(true);
		parser.ordinaryChar('<');
		parser.ordinaryChar('>');
		parser.ordinaryChar('/');
		parser.ordinaryChar('=');
		parser.wordChars('_', '_');
		parser.quoteChar('\"');
	}

	private XMLTag nextTag(final StreamTokenizer parser, final XMLTag parent) throws IOException {
		// Advance to the tag name.
		while(parser.ttype != StreamTokenizer.TT_WORD) parser.nextToken();
		final String name = parser.sval;
		// Create tag.
		final XMLTag tag = new XMLTag(name);
		if(parent != null) parent.addChild(tag);
		// Parse tag.
		boolean hasChildren = true;
		while(parser.nextToken() != '>') {
			final int type = parser.ttype;
			// If a string, may be a key.
			if(type == StreamTokenizer.TT_WORD) {
				final String key = parser.sval;
				// It is a key, add key-value pair.
				if(parser.nextToken() == '=') {
					while(parser.nextToken() != '\"');
					final String value = parser.sval;
					tag.addAttribute(key, value);
				}
			// If it is '/', no children.
			} else if(type == '/') {
				hasChildren = false;
			}
		}
		while(parser.nextToken() != StreamTokenizer.TT_EOL);
		// Parse children recursively.
		if(hasChildren) {
			while(true) {
				this.nextTag(parser, tag);
				while(parser.nextToken() != '<');
				final int type = parser.nextToken();
				if(type == '/') break;
			}
		}
		return tag;
	}

	/**
	 * Retrieve the root tag in the processed XML file.
	 * @return The root <code>XMLTag</code>.
	 */
	public XMLTag getRoot() {
		return this.root;
	}

	/**
	 * Clean up the cached XML data.
	 */
	public void cleanup() {
		this.root = null;
	}
}
