package hemera.core.utility.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import hemera.core.utility.config.xml.XMLParser;
import hemera.core.utility.config.xml.XMLTag;

/**
 * <code>ConfigImporter</code> defines implementation
 * of an utility unit that is responsible for loading
 * XML structured configuration file and return the
 * data as a <code>Configuration</code> instance.
 * <p>
 * The processing of given file only processes the
 * key-value pairs of all tags. All other information
 * is ignored.
 * <p>
 * <code>ConfigImporter</code> does not maintain any
 * internal cache, thus can be used by multiple threads
 * concurrently.
 *
 * @author Yi Wang (Neakor)
 * @version 1.0.0
 */
public class ConfigImporter {

	/**
	 * Load the configuration data from given file and
	 * return a configuration object instance.
	 * <p>
	 * The given file is assumed to be in the correct
	 * XML structure containing key-values pairs.
	 * <p>
	 * This method only operates on local variables,
	 * making is thread-safe by nature.
	 * @param file The XML configuration <code>File</code>.
	 * @return The <code>Configuration</code> object
	 * instance containing loaded data.
	 * @throws IOException If file processing failed.
	 */
	public Configuration load(final File file) throws IOException {
		return this.load(new FileInputStream(file));
	}
	
	/**
	 * Load the configuration data from given stream
	 * and return a configuration object instance.
	 * <p>
	 * The given stream is assumed to be in the correct
	 * XML structure containing key-values pairs.
	 * <p>
	 * This method only operates on local variables,
	 * making is thread-safe by nature.
	 * @param file The XML <code>InputStream</code> of
	 * the configuration file.
	 * @return The <code>Configuration</code> object
	 * instance containing loaded data.
	 * @throws IOException If stream processing failed.
	 */
	public Configuration load(final InputStream stream) throws IOException {
		final WritableConfiguration config = new WritableConfiguration();
		// Parse XML.
		final XMLParser parser = new XMLParser();
		try {
			parser.process(stream);
		} finally {
			stream.close();
		}
		final XMLTag root = parser.getRoot();
		// Check format.
		final String rootname = root.getName();
		if(!rootname.equals(EConfigTag.Root.getValue())) throw new IllegalArgumentException("Invalid configuration file.");
		// Insert children data. Start on children level,
		// since root is not a property tag.
		final Iterable<XMLTag> children = root.getChildren();
		for(final XMLTag child : children) this.insert(child, config);
		// Return.
		return config;
	}
	
	/**
	 * Insert all the key-values pairs of given tag and
	 * all its children tags into given configuration.
	 * @param tag The <code>XMLTag</code> to read from.
	 * @param config The <code>WritableConfiguration</code>
	 * to insert the data into.
	 */
	private void insert(final XMLTag tag, final WritableConfiguration config) {
		final String name = tag.getName();
		if(!name.equals(EConfigTag.Property.getValue())) return;
		// Tag attributes.
		final Iterable<String> keys = tag.getKeys();
		for(final String key : keys) {
			final String value = tag.getValue(key);
			config.insert(key, value);
		}
		// Recurse for children.
		final Iterable<XMLTag> children = tag.getChildren();
		for(final XMLTag child : children) {
			this.insert(child, config);
		}
	}
}
