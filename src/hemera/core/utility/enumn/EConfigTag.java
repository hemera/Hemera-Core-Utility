package hemera.core.utility.enumn;

/**
 * <code>EConfigTag</code> defines the enumerations
 * of the static tag names used in configuration
 * XML structure. Use <code>getValue</code> method
 * to retrieve the <code>String</code> value used.
 *
 * @author Yi Wang (Neakor)
 * @version 1.0.0
 */
public enum EConfigTag {
	/**
	 * The configuration XML structure root tag.
	 */
	Root("configuration"),
	/**
	 * The individual property tag.
	 */
	Property("property");
	
	/**
	 * The <code>String</code> name value of the tag.
	 */
	private final String value;
	
	/**
	 * Constructor of <code>EConfigTag</code>.
	 * @param value The <code>String</code> name value
	 * of the tag.
	 */
	private EConfigTag(final String value) {
		this.value = value;
	}
	
	/**
	 * Retrieve the name value of the tag.
	 * @return The <code>String</code> name value.
	 */
	public String getValue() {
		return this.value;
	}
}
