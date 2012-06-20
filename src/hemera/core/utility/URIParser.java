package hemera.core.utility;

import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * <code>URIParser</code> defines a singleton utility
 * unit that provides the functionality to parse an
 * URI and look for embedded arguments.
 *
 * @author Yi Wang (Neakor)
 * @version 1.0.0
 */
public enum URIParser {
	/**
	 * The singleton instance.
	 */
	instance;
	
	/**
	 * Parse out the access path of the given URI.
	 * @param uri The <code>String</code> URI to be
	 * parsed. This is not to be confused with an
	 * URL. It should not contain the domain portion
	 * of the address, but starts and includes the
	 * first <code>/</code> of the address.
	 * @return The <code>String</code> access path
	 * without any arguments.
	 */
	public String parsePath(final String uri) {
		final int index = uri.indexOf("?");
		if (index < 0) return uri;
		return uri.substring(0, index);
	}
	
	/**
	 * Parse the given URI string into a map of key
	 * value <code>String</code> pairs.
	 * <p>
	 * This method uses the standard separators. The
	 * <code>?</code> character as the beginning of
	 * a set of arguments. Each key value pair is in
	 * the format of <code>key=value</code>, and all
	 * pairs are separated by <code>&</code> character.
	 * @param uri The <code>String</code> URI to be
	 * parsed. This is not to be confused with an
	 * URL. It should not contain the domain portion
	 * of the address, but starts and includes the
	 * first <code>/</code> of the address.
	 * @return The <code>Map</code> of arguments in
	 * the URI. <code>null</code> if there are none.
	 */
	public Map<String, String> parseArguments(final String uri) {
		// No arguments.
		final int index = uri.indexOf("?");
		if (index < 0) return null;
		// Parse.
		final String contentstr = uri.substring(index+1);
		final Map<String, String> arguments = new HashMap<String, String>();
		// Separate argument pairs.
		final StringTokenizer pairtokenizer = new StringTokenizer(contentstr, "&");
		while (pairtokenizer.hasMoreTokens()) {
			final String pair = pairtokenizer.nextToken();
			// Separate key and value.
			final int pairindex = pair.indexOf("=");
			final String key = pair.substring(0, pairindex);
			final String value = pair.substring(pairindex+1);
			arguments.put(key, value);
		}
		// Catch empty arguments.
		if (arguments.isEmpty()) return null;
		return arguments;
	}
}
