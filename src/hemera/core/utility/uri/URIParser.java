package hemera.core.utility.uri;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
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
	 * Parse the given URI string value into a REST
	 * URI structure.
	 * @param value The <code>String</code> URI to be
	 * parsed. This is not to be confused with an
	 * URL. It should not contain the domain portion
	 * of the address, but starts and includes the
	 * first <code>/</code> of the address.
	 * @return The <code>RESTURI</code> instance.
	 */
	public RESTURI parseURI(final String value) {
		final int index = value.indexOf("?");
		final String uri = (index < 0) ? value.substring(1) : value.substring(1, index);
		final String[] array = uri.split("/");
		final String resource = array[0];
		long id = Long.MIN_VALUE;
		String action = null;
		// Second value may be ID or action.
		if (array.length == 2) {
			if (array[1].matches("-?\\d+(\\.\\d+)?")) {
				id = Long.valueOf(array[1]);
			} else {
				action = array[1];
			}
		}
		// If there are three values, then second one is ID, last one is action.
		else if (array.length == 3) {
			id = Long.valueOf(array[1]);
			action = array[2];
		}
		return new RESTURI(resource, id, action);
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
	 * @throws UnsupportedEncodingException If UTF-8
	 * URL decoding is not supported. 
	 */
	public Map<String, String> parseURIArguments(final String uri) throws UnsupportedEncodingException {
		// No arguments.
		final int index = uri.indexOf("?");
		if (index < 0) return null;
		// Parse contents.
		final String uricontents = uri.substring(index+1);
		return this.parseURIContentsArguments(uricontents);
	}
	
	/**
	 * Parse the given URI contents string into a map
	 * of key value <code>String</code> pairs.
	 * <p>
	 * This method uses the standard separators. Each
	 * key value pair is in the format of
	 * <code>key=value</code>, with a separator of the
	 * <code>=</code> character. All key and value
	 * pairs are separated by a single <code>&</code>
	 * character.
	 * @param uricontents The <code>String</code> URI
	 * contents to be parsed. This should only be the
	 * contents portion of a URI or URL. The contents
	 * portion of a URI or URL starts after the
	 * <code>?</code> character.
	 * @return The <code>Map</code> of arguments in
	 * the URI. <code>null</code> if there are none.
	 * @throws UnsupportedEncodingException If UTF-8
	 * URL decoding is not supported.
	 */
	public Map<String, String> parseURIContentsArguments(final String uricontents) throws UnsupportedEncodingException {
		// Minimum must have a equals sign, a key and a value.
		if (uricontents.length() < 3) return null;
		final Map<String, String> arguments = new HashMap<String, String>();
		// Separate argument pairs.
		final StringTokenizer pairtokenizer = new StringTokenizer(uricontents, "&");
		while (pairtokenizer.hasMoreTokens()) {
			final String pair = pairtokenizer.nextToken();
			// Separate key and value.
			final int pairindex = pair.indexOf("=");
			if (pairindex < 0) continue;
			final String key = pair.substring(0, pairindex);
			final String value = pair.substring(pairindex+1);
			final String decodedkey = URLDecoder.decode(key, "UTF-8");
			final String decodedvalue = URLDecoder.decode(value, "UTF-8");
			arguments.put(decodedkey, decodedvalue);
		}
		// Catch empty arguments.
		if (arguments.isEmpty()) return null;
		return arguments;
	}
}
