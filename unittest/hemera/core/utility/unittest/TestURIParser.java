package hemera.core.utility.unittest;

import hemera.core.utility.URIParser;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import junit.framework.TestCase;

public class TestURIParser extends TestCase {

	public void testNoArguments() throws Exception {
		final String uri = "http://myapi.myserver.com/module/submodule/processor";
		final Map<String, String> arguments = URIParser.instance.parse(uri);
		assertEquals(null, arguments);
	}
	
	public void testInvalidArguments() throws Exception {
		final String uri = "http://myapi.myserver.com/module/submodule/processor?";
		final Map<String, String> arguments = URIParser.instance.parse(uri);
		assertEquals(null, arguments);
	}
	
	public void testHasArguments() throws Exception {
		String uri = "http://myapi.myserver.com/module/submodule/processor?";
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
		
		final Map<String, String> arguments = URIParser.instance.parse(uri);
		final Iterable<String> keys = arguments.keySet();
		for (final String key : keys) {
			final String value = arguments.get(key);
			final String expectedvalue = expected.get(key);
			assertEquals(expectedvalue, value);
		}
	}
}
