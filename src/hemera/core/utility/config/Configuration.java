package hemera.core.utility.config;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * <code>Configuration</code> defines implementation
 * of a read-only data structure that encapsulates a
 * set of key-values pairs as configuration data. A
 * single property in a configuration consists of a
 * single key and a set of values.
 *
 * @author Yi Wang (Neakor)
 * @version 1.0.0
 */
public abstract class Configuration {
	/**
	 * The <code>Map</code> that maps configuration
	 * key <code>String</code> to <code>List</code>
	 * of values.
	 */
	final Map<String, List<String>> properties;
	
	/**
	 * Constructor of <code>Configuration</code>.
	 */
	public Configuration() {
		this.properties = new HashMap<String, List<String>>();
	}
	
	/**
	 * Retrieve the property value associated with the
	 * given key.
	 * @param key The <code>String</code> key to check.
	 * @return The <code>String</code> property value.
	 * <code>null</code> if no such key in loaded data.
	 */
	public String getStringValue(final String key) {
		final List<String> list = this.properties.get(key);
		if (list == null || list.isEmpty()) return null;
		return list.get(0);
	}
	
	/**
	 * Retrieve all the property values associated with
	 * the given key.
	 * @param key The <code>String</code> key to check.
	 * @return The <code>Collection</code> of all the
	 * <code>String</code> values associated with key.
	 * <code>null</code> if no such key in loaded data.
	 */
	public Collection<String> getStringValues(final String key) {
		return this.properties.get(key);
	}
	
	/**
	 * Retrieve the property value associated with the
	 * given key.
	 * @param key The <code>String</code> key to check.
	 * @return The <code>Integer</code> property value.
	 * <code>null</code> if no such key in loaded data.
	 */
	public Integer getIntegerValue(final String key) {
		final String value = this.getStringValue(key);
		if (value == null) return null;
		return Integer.valueOf(value);
	}
	
	/**
	 * Retrieve all the property values associated with
	 * the given key.
	 * @param key The <code>String</code> key to check.
	 * @return The <code>Collection</code> of all the
	 * <code>Integer</code> values associated with key.
	 * <code>null</code> if no such key in loaded data.
	 */
	public Collection<Integer> getIntegerValues(final String key) {
		final Collection<String> strings = this.getStringValues(key);
		if (strings == null) return null;
		final ArrayList<Integer> list = new ArrayList<Integer>();
		for (final String str : strings) {
			final Integer i = Integer.valueOf(str);
			list.add(i);
		}
		return list;
	}
	
	/**
	 * Retrieve the property value associated with the
	 * given key.
	 * @param key The <code>String</code> key to check.
	 * @return The <code>Long</code> property value.
	 * <code>null</code> if no such key in loaded data.
	 */
	public Long getLongValue(final String key) {
		final String value = this.getStringValue(key);
		if (value == null) return null;
		return Long.valueOf(value);
	}
	
	/**
	 * Retrieve all the property values associated with
	 * the given key.
	 * @param key The <code>String</code> key to check.
	 * @return The <code>Collection</code> of all the
	 * <code>Long</code> values associated with key.
	 * <code>null</code> if no such key in loaded data.
	 */
	public Collection<Long> getLongValues(final String key) {
		final Collection<String> strings = this.getStringValues(key);
		if (strings == null) return null;
		final ArrayList<Long> list = new ArrayList<Long>();
		for (final String str : strings) {
			final Long i = Long.valueOf(str);
			list.add(i);
		}
		return list;
	}
	
	/**
	 * Retrieve the property value associated with the
	 * given key.
	 * @param key The <code>String</code> key to check.
	 * @return The <code>Float</code> property value.
	 * <code>null</code> if no such key in loaded data.
	 */
	public Float getFloatValue(final String key) {
		final String value = this.getStringValue(key);
		if (value == null) return null;
		return Float.valueOf(value);
	}
	
	/**
	 * Retrieve all the property values associated with
	 * the given key.
	 * @param key The <code>String</code> key to check.
	 * @return The <code>Collection</code> of all the
	 * <code>Float</code> values associated with key.
	 * <code>null</code> if no such key in loaded data.
	 */
	public Collection<Float> getFloatValues(final String key) {
		final Collection<String> strings = this.getStringValues(key);
		if (strings == null) return null;
		final ArrayList<Float> list = new ArrayList<Float>();
		for (final String str : strings) {
			final Float i = Float.valueOf(str);
			list.add(i);
		}
		return list;
	}
	
	/**
	 * Retrieve the property value associated with the
	 * given key.
	 * @param key The <code>String</code> key to check.
	 * @return The <code>Double</code> property value.
	 * <code>null</code> if no such key in loaded data.
	 */
	public Double getDoubleValue(final String key) {
		final String value = this.getStringValue(key);
		if (value == null) return null;
		return Double.valueOf(value);
	}
	
	/**
	 * Retrieve all the property values associated with
	 * the given key.
	 * @param key The <code>String</code> key to check.
	 * @return The <code>Collection</code> of all the
	 * <code>Double</code> values associated with key.
	 * <code>null</code> if no such key in loaded data.
	 */
	public Collection<Double> getDoubleValues(final String key) {
		final Collection<String> strings = this.getStringValues(key);
		if (strings == null) return null;
		final ArrayList<Double> list = new ArrayList<Double>();
		for (final String str : strings) {
			final Double i = Double.valueOf(str);
			list.add(i);
		}
		return list;
	}
	
	/**
	 * Retrieve the property value associated with the
	 * given key.
	 * @param key The <code>String</code> key to check.
	 * @return The <code>Boolean</code> property value.
	 * <code>null</code> if no such key in loaded data.
	 */
	public Boolean getBooleanValue(final String key) {
		final String value = this.getStringValue(key);
		if (value == null) return null;
		return Boolean.valueOf(value);
	}
	
	/**
	 * Retrieve all the property values associated with
	 * the given key.
	 * @param key The <code>String</code> key to check.
	 * @return The <code>Collection</code> of all the
	 * <code>Boolean</code> values associated with key.
	 * <code>null</code> if no such key in loaded data.
	 */
	public Collection<Boolean> getBooleanValues(final String key) {
		final Collection<String> strings = this.getStringValues(key);
		if (strings == null) return null;
		final ArrayList<Boolean> list = new ArrayList<Boolean>();
		for (final String str : strings) {
			final Boolean i = Boolean.valueOf(str);
			list.add(i);
		}
		return list;
	}
	
	/**
	 * Retrieve the property value associated with the
	 * given key.
	 * @param key The <code>String</code> key to check.
	 * @return The <code>TimeData</code> property value.
	 * <code>null</code> if no such key in loaded data.
	 */
	public TimeData getTimeData(final String key) {
		final String value = this.getStringValue(key);
		if (value == null) return null;
		final String[] array = value.split(" ");
		final Long time = Long.valueOf(array[0]);
		final TimeUnit unit = TimeUnit.valueOf(array[1]);
		return new TimeData(time, unit);
	}
	
	/**
	 * Retrieve all the property values associated with
	 * the given key.
	 * @param key The <code>String</code> key to check.
	 * @return The <code>Collection</code> of all the
	 * <code>TimeData</code> values associated with key.
	 * <code>null</code> if no such key in loaded data.
	 */
	public Collection<TimeData> getTimeDatas(final String key) {
		final Collection<String> strings = this.getStringValues(key);
		if (strings == null) return null;
		final ArrayList<TimeData> list = new ArrayList<TimeData>();
		for (final String str : strings) {
			final String[] array = str.split(" ");
			final Long time = Long.valueOf(array[0]);
			final TimeUnit unit = TimeUnit.valueOf(array[1]);
			final TimeData timeData = new TimeData(time, unit);
			list.add(timeData);
		}
		return list;
	}

	/**
	 * Retrieve the property value associated with the
	 * given key.
	 * @param key The <code>Enum</code> key to check.
	 * @return The <code>String</code> property value.
	 * <code>null</code> if no such key in loaded data.
	 */
	public String getStringValue(final Enum<?> key) {
		return this.getStringValue(key.name());
	}
	
	/**
	 * Retrieve all the property values associated with
	 * the given key.
	 * @param key The <code>String</code> key to check.
	 * @return The <code>Collection</code> of all the
	 * <code>String</code> values associated with key.
	 * <code>null</code> if no such key in loaded data.
	 */
	public Collection<String> getStringValues(final Enum<?> key) {
		return this.getStringValues(key.name());
	}
	
	/**
	 * Retrieve the property value associated with the
	 * given key.
	 * @param key The <code>Enum</code> key to check.
	 * @return The <code>Integer</code> property value.
	 * <code>null</code> if no such key in loaded data.
	 */
	public Integer getIntegerValue(final Enum<?> key) {
		return this.getIntegerValue(key.name());
	}
	
	/**
	 * Retrieve all the property values associated with
	 * the given key.
	 * @param key The <code>String</code> key to check.
	 * @return The <code>Collection</code> of all the
	 * <code>Integer</code> values associated with key.
	 * <code>null</code> if no such key in loaded data.
	 */
	public Collection<Integer> getIntegerValues(final Enum<?> key) {
		return this.getIntegerValues(key.name());
	}
	
	/**
	 * Retrieve the property value associated with the
	 * given key.
	 * @param key The <code>Enum</code> key to check.
	 * @return The <code>Long</code> property value.
	 * <code>null</code> if no such key in loaded data.
	 */
	public Long getLongValue(final Enum<?> key) {
		return this.getLongValue(key.name());
	}
	
	/**
	 * Retrieve all the property values associated with
	 * the given key.
	 * @param key The <code>String</code> key to check.
	 * @return The <code>Collection</code> of all the
	 * <code>Long</code> values associated with key.
	 * <code>null</code> if no such key in loaded data.
	 */
	public Collection<Long> getLongValues(final Enum<?> key) {
		return this.getLongValues(key.name());
	}
	
	/**
	 * Retrieve the property value associated with the
	 * given key.
	 * @param key The <code>Enum</code> key to check.
	 * @return The <code>Float</code> property value.
	 * <code>null</code> if no such key in loaded data.
	 */
	public Float getFloatValue(final Enum<?> key) {
		return this.getFloatValue(key.name());
	}
	
	/**
	 * Retrieve all the property values associated with
	 * the given key.
	 * @param key The <code>String</code> key to check.
	 * @return The <code>Collection</code> of all the
	 * <code>Float</code> values associated with key.
	 * <code>null</code> if no such key in loaded data.
	 */
	public Collection<Float> getFloatValues(final Enum<?> key) {
		return this.getFloatValues(key.name());
	}
	
	/**
	 * Retrieve the property value associated with the
	 * given key.
	 * @param key The <code>Enum</code> key to check.
	 * @return The <code>Double</code> property value.
	 * <code>null</code> if no such key in loaded data.
	 */
	public Double getDoubleValue(final Enum<?> key) {
		return this.getDoubleValue(key.name());
	}
	
	/**
	 * Retrieve all the property values associated with
	 * the given key.
	 * @param key The <code>String</code> key to check.
	 * @return The <code>Collection</code> of all the
	 * <code>Double</code> values associated with key.
	 * <code>null</code> if no such key in loaded data.
	 */
	public Collection<Double> getDoubleValues(final Enum<?> key) {
		return this.getDoubleValues(key.name());
	}
	
	/**
	 * Retrieve the property value associated with the
	 * given key.
	 * @param key The <code>Enum</code> key to check.
	 * @return The <code>Boolean</code> property value.
	 * <code>null</code> if no such key in loaded data.
	 */
	public Boolean getBooleanValue(final Enum<?> key) {
		return this.getBooleanValue(key.name());
	}
	
	/**
	 * Retrieve all the property values associated with
	 * the given key.
	 * @param key The <code>Enum</code> key to check.
	 * @return The <code>Collection</code> of all the
	 * <code>Boolean</code> values associated with key.
	 * <code>null</code> if no such key in loaded data.
	 */
	public Collection<Boolean> getBooleanValues(final Enum<?> key) {
		return this.getBooleanValues(key.name());
	}
	
	/**
	 * Retrieve the property value associated with the
	 * given key.
	 * @param key The <code>Enum</code> key to check.
	 * @return The <code>TimeData</code> property value.
	 * <code>null</code> if no such key in loaded data.
	 */
	public TimeData getTimeData(final Enum<?> key) {
		return this.getTimeData(key.name());
	}
	
	/**
	 * Retrieve all the property values associated with
	 * the given key.
	 * @param key The <code>Enum</code> key to check.
	 * @return The <code>Collection</code> of all the
	 * <code>TimeData</code> values associated with key.
	 * <code>null</code> if no such key in loaded data.
	 */
	public Collection<TimeData> getTimeDatas(final Enum<?> key) {
		return this.getTimeDatas(key.name());
	}
}