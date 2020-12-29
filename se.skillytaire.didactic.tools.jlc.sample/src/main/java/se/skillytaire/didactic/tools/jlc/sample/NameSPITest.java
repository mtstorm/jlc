package se.skillytaire.didactic.tools.jlc.sample;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import se.skillytaire.didactic.tools.jlc.api.JLC;
import se.skillytaire.didactic.tools.jlc.api.JLCConfigurationTest;
import se.skillytaire.didactic.tools.jlc.api.This;
@JLC
public class NameSPITest {
	@This
	private Name thisName;
	@Test
	public void thisNameExternalFactoryTest() {
		assertNotNull(thisName, "JLC should injected here a value");
	}
}
