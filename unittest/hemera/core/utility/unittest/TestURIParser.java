package hemera.core.utility.unittest;

import hemera.core.utility.uri.RESTURI;
import hemera.core.utility.uri.URIParser;

import java.util.UUID;

import junit.framework.TestCase;

public class TestURIParser extends TestCase {

	public void testURIFull() {
		final String id = UUID.randomUUID().toString().replace("-", "");
		final String uri = "/resource/"+id+"/action";
		final RESTURI parsed = URIParser.instance.parseURI(uri);
		assertEquals("resource", parsed.resource);
		assertEquals(id, parsed.elements[0]);
		assertEquals("action", parsed.elements[1]);
	}
	
	public void testURIResourceOnly() {
		final String uri = "/resource/";
		final RESTURI parsed = URIParser.instance.parseURI(uri);
		assertEquals("resource", parsed.resource);
		assertEquals(null, parsed.elements);
	}
	
	public void testURIResourceIDOnly() {
		final String id = UUID.randomUUID().toString().replace("-", "");
		final String uri = "/resource/"+id;
		final RESTURI parsed = URIParser.instance.parseURI(uri);
		assertEquals("resource", parsed.resource);
		assertEquals(1, parsed.elements.length);
		assertEquals(id, parsed.elements[0]);
	}
	
	public void testURIIDMultiAction() {
		final String id = UUID.randomUUID().toString().replace("-", "");
		final String uri = "/resource/"+id+"/action1/action2/action3/";
		final RESTURI parsed = URIParser.instance.parseURI(uri);
		assertEquals("resource", parsed.resource);
		assertEquals(id, parsed.elements[0]);
		assertEquals("action1", parsed.elements[1]);
		assertEquals("action2", parsed.elements[2]);
		assertEquals("action3", parsed.elements[3]);
	}
	
	public void testURINoIDMultiAction() {
		final String uri = "/resource/action1/action2/action3/";
		final RESTURI parsed = URIParser.instance.parseURI(uri);
		assertEquals("resource", parsed.resource);
		assertEquals("action1", parsed.elements[0]);
		assertEquals("action2", parsed.elements[1]);
		assertEquals("action3", parsed.elements[2]);
	}
}
