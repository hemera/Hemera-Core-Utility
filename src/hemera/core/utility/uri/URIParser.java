package hemera.core.utility.uri;

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
	 */
	public RESTURI parseURI(final String value) {
		final int index = value.indexOf("?");
		final String uri = (index < 0) ? value.substring(1) : value.substring(1, index);
		final String[] array = uri.split("/");
		// First element must be resource.
		final String resource = array[0];
		// Rest are elements.
		final int count = array.length-1;
		if (count > 0) {
			final String[] elements = new String[count];
			for (int i = 0; i < count; i++) {
				elements[i] = array[i+1];
			}
			return new RESTURI(resource, elements);
		} else {
			return new RESTURI(resource, null);
		}
	}
}
