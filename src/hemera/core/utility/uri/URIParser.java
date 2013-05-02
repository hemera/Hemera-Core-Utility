package hemera.core.utility.uri;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * <code>URIParser</code> defines a singleton utility
 * unit that provides the functionality to parse an
 * URI and look for embedded arguments.
 *
 * @author Yi Wang (Neakor)
 * @version 1.0.3
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
	 * @throws UnsupportedEncodingException If UTF-8
	 * encoding is not supported.
	 */
	public RESTURI parseURI(final String value) throws UnsupportedEncodingException {
		final int index = value.indexOf("?");
		final String uri = (index < 0) ? value.substring(1) : value.substring(1, index);
		final String[] array = uri.split("/");
		// First element must be resource.
		final String resource = URLDecoder.decode(array[0], "UTF-8");
		// Rest are elements.
		final int count = array.length-1;
		if (count > 0) {
			final String[] elements = new String[count];
			for (int i = 0; i < count; i++) {
				elements[i] = URLDecoder.decode(array[i+1], "UTF-8");
			}
			return new RESTURI(resource, elements);
		} else {
			return new RESTURI(resource, null);
		}
	}
}
