package hemera.core.utility.unittest;

import hemera.core.utility.uri.RESTURI;
import hemera.core.utility.uri.URIParser;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import junit.framework.TestCase;

public class TestURIParser extends TestCase {

	public void testNoArguments() throws Exception {
		final String uri = "/resource/123/action";
		final Map<String, String> arguments = URIParser.instance.parseURIArguments(uri);
		assertEquals(null, arguments);
	}
	
	public void testInvalidArguments() throws Exception {
		final String uri = "/resource/123/action?";
		final Map<String, String> arguments = URIParser.instance.parseURIArguments(uri);
		assertEquals(null, arguments);
	}
	
	public void testHasArguments() throws Exception {
		String uri = "/resource/123/action?";
		final Random random = new Random();
		final Map<String, String> expected = new HashMap<String, String>();
		final int count = 17;
		for (int i = 0; i < count; i++) {
			final String key = String.valueOf(random.nextDouble());
			final String value = String.valueOf(random.nextDouble());
			expected.put(key, value);
			uri += key + "=" + value;
			if (i != count-1) uri += "&";
		}
		
		final Map<String, String> arguments = URIParser.instance.parseURIArguments(uri);
		final Iterable<String> keys = arguments.keySet();
		for (final String key : keys) {
			final String value = arguments.get(key);
			final String expectedvalue = expected.get(key);
			assertEquals(expectedvalue, value);
		}
	}
	
	public void testURIFull() {
		final String uri = "/resource/123/action";
		final RESTURI parsed = URIParser.instance.parseURI(uri);
		assertEquals("resource", parsed.resource);
		assertEquals((long)123, parsed.id);
		assertEquals("action", parsed.action);
	}
	
	public void testURIResourceOnly() {
		final String uri = "/resource/";
		final RESTURI parsed = URIParser.instance.parseURI(uri);
		assertEquals("resource", parsed.resource);
		assertEquals(Long.MIN_VALUE, parsed.id);
		assertEquals(null, parsed.action);
	}
	
	public void testURIResourceIDOnly() {
		final String uri = "/resource/999";
		final RESTURI parsed = URIParser.instance.parseURI(uri);
		assertEquals("resource", parsed.resource);
		assertEquals((long)999, parsed.id);
		assertEquals(null, parsed.action);
	}
	
	public void testURIIDMultiAction() {
		final String uri = "/resource/123/action1/action2/action3/";
		final RESTURI parsed = URIParser.instance.parseURI(uri);
		assertEquals("resource", parsed.resource);
		assertEquals((long)123, parsed.id);
		assertEquals("action1/action2/action3", parsed.action);
	}
	
	public void testURINoIDMultiAction() {
		final String uri = "/resource/action1/action2/action3/";
		final RESTURI parsed = URIParser.instance.parseURI(uri);
		assertEquals("resource", parsed.resource);
		assertEquals(Long.MIN_VALUE, parsed.id);
		assertEquals("action1/action2/action3", parsed.action);
	}
}
