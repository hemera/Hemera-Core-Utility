package hemera.core.utility.xml;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

/**
 * <code>XMLTag</code> defines the implementation of
 * a single XML tag that contains an unique name, a
 * list of children and a set of key value pairs.
 * <p>
 * <code>XMLTag</code> internally uses <code>List</code>
 * data structure as children tag data store, in order
 * to optimize the iteration performance. However, this
 * implies that the addition duplicate checking may be
 * slow depending the number of children tags.
 *
 * @author Yi Wang (Neakor)
 * @version Creation date: 11-06-2009 19:04 EST
 * @version Modified date: 01-08-2010 11:44 EST
 */
public class XMLTag {
	/**
	 * The <code>String</code> name of this tag.
	 */
	private final String name;
	/**
	 * The <code>Map</code> of <code>String</code> key
	 * and <code>String</code> value attributes.
	 */
	private final Map<String, String> attributes;
	/**
	 * The <code>List</code> of <code>XMLTag</code>
	 * children.
	 */
	private final List<XMLTag> children;
	/**
	 * The parent <code>XMLTag</code>.
	 */
	private XMLTag parent;

	/**
	 * Constructor of <code>XMLTag</code>.
	 * @param name The <code>String</code> name of this tag.
	 */
	public XMLTag(final String name) {
		this.name = name;
		this.attributes = new HashMap<String, String>();
		this.children = new ArrayList<XMLTag>();
	}

	/**
	 * Add the given tag as a child to this tag.
	 * @param tag The <code>XMLTag</code> to be added.
	 */
	public void addChild(final XMLTag tag) {
		if(tag == null || this.children.contains(tag)) return;
		final boolean succeeded = this.children.add(tag);
		if(succeeded) tag.parent = this;
	}

	/**
	 * Add the given key-value attribute to this tag.
	 * @param key The <code>String</code> key.
	 * @param value The <code>String</code> value.
	 */
	public void addAttribute(final String key, final String value) {
		if(this.attributes.containsKey(key) || key == null || value == null) return;
		this.attributes.put(key, value);
	}

	/**
	 * Retrieve the key-value pair keys maintains in
	 * this tag.
	 * @return The <code>Iterable</code> of all the
	 * <code>String</code> keys.
	 */
	public Iterable<String> getKeys() {
		return this.attributes.keySet();
	}

	/**
	 * Retrieve the name of this tag.
	 * @return The <code>String</code> name.
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Retrieve the parent XML tag.
	 * @return The parent <code>XMLTag</code>. If this
	 * tag the root tag, <code>null</code> is returned.
	 */
	public XMLTag getParent() {
		return this.parent;
	}

	/**
	 * Retrieve the children tags of this tag.
	 * @return The <code>Iterable</code> of all the
	 * children <code>XMLTag</code>.
	 */
	public Iterable<XMLTag> getChildren() {
		return this.children;
	}

	/**
	 * Retrieve the value in this tag with the given
	 * key.
	 * @param key The <code>String</code> key.
	 * @return The <code>String</code> value.
	 */
	public String getValue(final String key) {
		return this.attributes.get(key);
	}

	@Override
	public String toString() {
		return this.name;
	}

	/**
	 * Convert the current information into the external
	 * XML file format.
	 * @return The XML file formatted <code>String</code>
	 * representation of this tag and its children.
	 */
	public String toExternal() {
		final StringBuilder builder = new StringBuilder();
		// Indentation.
		XMLTag p = this.parent;
		while(p != null) {
			builder.append("	");
			p = p.getParent();
		}
		// Begin tag.
		builder.append("<").append(this.name);
		// Append attributes.
		final Set<Entry<String, String>> set = this.attributes.entrySet();
		for(final Entry<String, String> entry : set) {
			builder.append(" ");
			builder.append(entry.getKey()).append("=").append("\"").append(entry.getValue()).append("\"");
		}
		// Children.
		if(this.children.isEmpty()) builder.append(" />\n");
		else {
			builder.append(">\n");
			for(final XMLTag child : this.children) {
				builder.append(child.toExternal());
			}
			// Indentation.
			p = this.parent;
			while(p != null) {
				builder.append("	");
				p = p.getParent();
			}
			builder.append("</").append(this.name).append(">\n");
		}
		return builder.toString();
	}
}
