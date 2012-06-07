package hemera.core.utility.enumn;

/**
 * <code>ELoggingConfig</code> defines the enumeration
 * of logging related configuration values. All these
 * values can be modified at runtime to adjust system
 * logging behavior.
 * <p>
 * Each <code>ELoggingConfig</code> enumeration has an
 * <code>Object</code> value associated with it. This
 * value is of a type that is specific to the usage of
 * the enumeration.
 *
 * @author Yi Wang (Neakor)
 * @version Creation date: 08-08-2010 15:24 EST
 * @version Modified date: 08-13-2010 12:57 EST
 */
public enum ELoggingConfig {
	/**
	 * The <code>Boolean</code> value indicating if the
	 * system logging is enabled.
	 * <p>
	 * Default value is <code>true</code>.
	 */
	Enabled(true),
	/**
	 * The <code>String</code> value indicating the
	 * logging file directory.
	 * <p>
	 * Default value is <code>/nyx/log/</code>.
	 */
	Directory("/nyx/log/"),
	/**
	 * The <code>Integer</code> value indicating the
	 * maximum size of a single log file before a new
	 * log file is rotated in.
	 * <p>
	 * Default value is <code>1048576</code> or 1MB.
	 */
	FileSize(1048576),
	/**
	 * The <code>Integer</code> value indicating the
	 * total number of log files to use for rotation.
	 * <p>
	 * Default value is <code>1</code>.
	 */
	FileCount(1);
	
	/**
	 * The type specific <code>Object</code> value.
	 */
	private Object value;
	
	/**
	 * Constructor of <code>ELoggingConfig</code>.
	 * @param value The default <code>Object</code> value.
	 */
	private ELoggingConfig(final Object value) {
		this.value = value;
	}
	
	/**
	 * Set the type specific value.
	 * @param value The <code>Object</code> value.
	 */
	public void setValue(final Object value) {
		this.value = value;
	}
	
	/**
	 * Retrieve the type specific value.
	 * @return The <code>Object</code> value.
	 */
	public Object getValue() {
		return this.value;
	}
}
