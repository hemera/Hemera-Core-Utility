package hemera.core.utility.uri;

/**
 * <code>RESTURI</code> defines the immutable data
 * structure of a REST URI that contains a resource
 * name, an optional resource ID, and an optional
 * custom action.
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
	 * The <code>long</code> optional resource ID.
	 * <code>Long.MIN_VALUE</code> if there is no
	 * ID specified.
	 */
	public final long id;
	/**
	 * The optional <code>String</code> action name.
	 * <code>null</code> if there are no custom action
	 * specified.
	 */
	public final String action;
	
	/**
	 * Constructor of <code>RESTURI</code>.
	 * @param resource The <code>String</code> resource
	 * name.
	 * @param id The <code>long</code> optional ID.
	 * @param action The <code>String</code> optional
	 * custom action.
	 */
	RESTURI(final String resource, final long id, final String action) {
		this.resource = resource;
		this.id = id;
		this.action = action;
	}
}
