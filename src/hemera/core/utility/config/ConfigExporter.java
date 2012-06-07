package hemera.core.utility.config;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import hemera.core.utility.enumn.EConfigTag;
import hemera.core.utility.xml.XMLTag;

/**
 * <code>ConfigExporter</code> defines implementation
 * of an utility unit that is responsible for forming
 * appropriate XML structure of <code>String</code>
 * key-values pairs defining the properties of an unit
 * configuration and export the data into a disk file.
 * <p>
 * <code>ConfigExporter</code> does not maintain any
 * internal cache, thus can be used by multiple threads
 * concurrently.
 *
 * @author Yi Wang (Neakor)
 * @version 1.0.0
 */
public class ConfigExporter {

	/**
	 * Export the given configuration data into given
	 * disk file.
	 * <p>
	 * If the given file does not exist, a new file
	 * with the specified file name is created. If
	 * the given file does exist, its content will
	 * be erased and replaced by the configuration.
	 * <p>
	 * This method only operates on local variables,
	 * making is thread-safe by nature.
	 * @param config The <code>WritableConfiguration</code>
	 * containing the data to be exported.
	 * @param file The <code>File</code> to save the
	 * data into.
	 * @throws IOException If file handling failed.
	 */
	public void export(final WritableConfiguration config, final File file) throws IOException {
		// Check file.
		if(file.exists()) file.delete();
		file.createNewFile();
		// Write out.
		final PrintWriter writer = new PrintWriter(file);
		final XMLTag root = new XMLTag(EConfigTag.Root.getValue());
		final Iterable<String> keys = config.properties.keySet();
		for (final String key : keys) {
			final List<String> values = config.properties.get(key);
			for (final String value : values) {
				final XMLTag ptag = new XMLTag(EConfigTag.Property.getValue());
				ptag.addAttribute(key, value);
				root.addChild(ptag);
			}
		}
		writer.println(root.toExternal());
		writer.close();
	}
}
