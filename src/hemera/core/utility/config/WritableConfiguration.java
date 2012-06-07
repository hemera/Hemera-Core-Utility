package hemera.core.utility.config;

import java.util.ArrayList;
import java.util.List;

/**
 * <code>WritableConfiguration</code> defines the
 * implementation of a mutable configuration data
 * structure that allows insertion of key-values
 * pairs for export purposes.
 *
 * @author Yi Wang (Neakor)
 * @version 1.0.0
 */
public final class WritableConfiguration extends Configuration {

	/**
	 * Insert the given key-value pair as a property
	 * to the configuration data cache for export.
	 * <p>
	 * Insertion guarantees the ordering. However there
	 * is no duplication check made on the key.
	 * @param key The <code>String</code> key.
	 * @param value The <code>int</code> value.
	 */
	public void insert(final String key, final int value) {
		this.insert(key, String.valueOf(value));
	}

	/**
	 * Insert the given key-value pair as a property
	 * to the configuration data cache for export.
	 * <p>
	 * Insertion guarantees the ordering. However there
	 * is no duplication check made on the key.
	 * @param key The <code>String</code> key.
	 * @param value The <code>long</code> value.
	 */
	public void insert(final String key, final long value) {
		this.insert(key, String.valueOf(value));
	}

	/**
	 * Insert the given key-value pair as a property
	 * to the configuration data cache for export.
	 * <p>
	 * Insertion guarantees the ordering. However there
	 * is no duplication check made on the key.
	 * @param key The <code>String</code> key.
	 * @param value The <code>float</code> value.
	 */
	public void insert(final String key, final float value) {
		this.insert(key, String.valueOf(value));
	}
	
	/**
	 * Insert the given key-value pair as a property
	 * to the configuration data cache for export.
	 * <p>
	 * Insertion guarantees the ordering. However there
	 * is no duplication check made on the key.
	 * @param key The <code>String</code> key.
	 * @param value The <code>double</code> value.
	 */
	public void insert(final String key, final double value) {
		this.insert(key, String.valueOf(value));
	}
	
	/**
	 * Insert the given key-value pair as a property
	 * to the configuration data cache for export.
	 * <p>
	 * Insertion guarantees the ordering. However there
	 * is no duplication check made on the key.
	 * @param key The <code>String</code> key.
	 * @param value The <code>boolean</code> value.
	 */
	public void insert(final String key, final boolean value) {
		this.insert(key, String.valueOf(value));
	}
	
	/**
	 * Insert the given key-value pair as a property
	 * to the configuration data cache for export.
	 * <p>
	 * Insertion guarantees the ordering. However there
	 * is no duplication check made on the key.
	 * @param key The <code>String</code> key.
	 * @param value The <code>String</code> value.
	 */
	public void insert(final String key, final String value) {
		List<String> values = this.properties.get(key);
		if (values == null) {
			values = new ArrayList<String>();
			this.properties.put(key, values);
		}
		values.add(value);
	}
	
	/**
	 * Insert the given key-value pair as a property
	 * to the configuration data cache for export.
	 * <p>
	 * Insertion guarantees the ordering. However there
	 * is no duplication check made on the key.
	 * @param key The <code>String</code> key.
	 * @param value The <code>TimeData</code> value.
	 */
	public void insert(final String key, final TimeData data) {
		final StringBuilder builder = new StringBuilder();
		builder.append(data.value).append(" ").append(data.unit.name());
		this.insert(key, builder.toString());
	}
	
	/**
	 * Insert the given key-value pair as a property
	 * to the configuration data cache for export.
	 * <p>
	 * Insertion guarantees the ordering. However there
	 * is no duplication check made on the key.
	 * @param key The <code>Enum</code> key.
	 * @param value The <code>int</code> value.
	 */
	public void insert(final Enum<?> key, final int value) {
		this.insert(key.name(), value);
	}
	
	/**
	 * Insert the given key-value pair as a property
	 * to the configuration data cache for export.
	 * <p>
	 * Insertion guarantees the ordering. However there
	 * is no duplication check made on the key.
	 * @param key The <code>Enum</code> key.
	 * @param value The <code>long</code> value.
	 */
	public void insert(final Enum<?> key, final long value) {
		this.insert(key.name(), value);
	}
	
	/**
	 * Insert the given key-value pair as a property
	 * to the configuration data cache for export.
	 * <p>
	 * Insertion guarantees the ordering. However there
	 * is no duplication check made on the key.
	 * @param key The <code>Enum</code> key.
	 * @param value The <code>float</code> value.
	 */
	public void insert(final Enum<?> key, final float value) {
		this.insert(key.name(), value);
	}
	
	/**
	 * Insert the given key-value pair as a property
	 * to the configuration data cache for export.
	 * <p>
	 * Insertion guarantees the ordering. However there
	 * is no duplication check made on the key.
	 * @param key The <code>Enum</code> key.
	 * @param value The <code>double</code> value.
	 */
	public void insert(final Enum<?> key, final double value) {
		this.insert(key.name(), value);
	}
	
	/**
	 * Insert the given key-value pair as a property
	 * to the configuration data cache for export.
	 * <p>
	 * Insertion guarantees the ordering. However there
	 * is no duplication check made on the key.
	 * @param key The <code>Enum</code> key.
	 * @param value The <code>boolean</code> value.
	 */
	public void insert(final Enum<?> key, final boolean value) {
		this.insert(key.name(), value);
	}
	
	/**
	 * Insert the given key-value pair as a property
	 * to the configuration data cache for export.
	 * <p>
	 * Insertion guarantees the ordering. However there
	 * is no duplication check made on the key.
	 * @param key The <code>Enum</code> key.
	 * @param value The <code>String</code> value.
	 */
	public void insert(final Enum<?> key, final String value) {
		this.insert(key.name(), value);
	}
	
	/**
	 * Insert the given key-value pair as a property
	 * to the configuration data cache for export.
	 * <p>
	 * Insertion guarantees the ordering. However there
	 * is no duplication check made on the key.
	 * @param key The <code>Enum</code> key.
	 * @param value The <code>Enum</code> value.
	 */
	public void insert(final Enum<?> key, final Enum<?> value) {
		this.insert(key.name(), value.name());
	}
	
	/**
	 * Insert the given key-value pair as a property
	 * to the configuration data cache for export.
	 * <p>
	 * Insertion guarantees the ordering. However there
	 * is no duplication check made on the key.
	 * @param key The <code>Enum</code> key.
	 * @param value The <code>TimeData</code> value.
	 */
	public void insert(final Enum<?> key, final TimeData data) {
		final StringBuilder builder = new StringBuilder();
		builder.append(data.value).append(" ").append(data.unit.name());
		this.insert(key.name(), builder.toString());
	}
}
