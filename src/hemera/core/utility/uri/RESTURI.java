package hemera.core.utility.uri;

/**
 * <code>RESTURI</code> defines the immutable data
 * structure of a REST URI that contains a resource
 * name and an optional array of URI elements.
 *
 * @author Yi Wang (Neakor)
 * @version 1.0.0
 */
public class RESTURI {
	/**
	 * The <code>String</code> resource name.
	 */
	public final String resource;
	/**
	 * The optional <code>String</code> array of URI
	 * elements.
	 */
	public final String[] elements;
	
	/**
	 * Constructor of <code>RESTURI</code>.
	 * @param resource The <code>String</code> resource
	 * name.
	 * @param elements The optional <code>String</code>
	 * array of URI elements.
	 */
	RESTURI(final String resource, final String[] elements) {
		this.resource = resource;
		this.elements = elements;
	}
}
