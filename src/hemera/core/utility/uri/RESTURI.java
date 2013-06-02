package hemera.core.utility.uri;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayDeque;
import java.util.Queue;

/**
 * <code>RESTURI</code> defines the immutable data
 * structure of a REST URI that contains an array
 * of URI elements.
 *
 * @author Yi Wang (Neakor)
 * @version 1.0.4
 */
public class RESTURI {
	/**
	 * The <code>Queue</code> <code>String</code> array
	 * of URI elements.
	 */
	public final Queue<String> elements;
	
	/**
	 * Constructor of <code>RESTURI</code>.
	 * @param value The <code>String</code> URI to be
	 * parsed. This is not to be confused with an
	 * URL. It should not contain the domain portion
	 * of the address, but starts and includes the
	 * first <code>/</code> of the address.
	 * @throws UnsupportedEncodingException If UTF-8
	 * encoding is not supported.
	 */
	public RESTURI(final String value) throws UnsupportedEncodingException {
		final int index = value.indexOf("?");
		final String uri = (index < 0) ? value.substring(1) : value.substring(1, index);
		final String[] array = uri.split("/");
		this.elements = new ArrayDeque<String>(array.length);
		for (int i = 0; i < array.length; i++) {
			final String element = URLDecoder.decode(array[i], "UTF-8");
			this.elements.add(element);
		}
	}
	
	/**
	 * Retrieve the array representation of the current
	 * elements of this URI.
	 * @return The <code>String</code> array. If there
	 * are no elements for the URI, <code>null</code>.
	 */
	public String[] getElementArray() {
		if (this.elements.isEmpty()) {
			return null;
		} else {
			final int size = this.elements.size();
			final String[] array = new String[size];
			int index = 0;
			for (final String element : this.elements) {
				array[index] = element;
				index++;
			}
			return array;
		}
	}
}
