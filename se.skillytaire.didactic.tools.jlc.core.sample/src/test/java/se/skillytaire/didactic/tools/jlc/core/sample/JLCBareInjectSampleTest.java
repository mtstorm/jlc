package se.skillytaire.didactic.tools.jlc.core.sample;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import se.skillytaire.didactic.tools.jlc.api.JLC;
import se.skillytaire.didactic.tools.jlc.api.This;
/**
 * This sample test shows how to integrate JLC to your tests. Remember that this is only the core
 * and does not provide additional tests.
 *
 */
@JLC
public class JLCBareInjectSampleTest {

	@This
	private String thisString;
	
	@Test
	public void thisStringTest() {
		assertNotNull(thisString, "JLC should injected here a value");
	}
	
}

