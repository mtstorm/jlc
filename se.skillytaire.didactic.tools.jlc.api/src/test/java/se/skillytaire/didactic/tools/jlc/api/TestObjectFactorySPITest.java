package se.skillytaire.didactic.tools.jlc.api;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TestObjectFactorySPITest {
	@Test
	public void spiTest() {
		long actual = TestObjectFactory.resolve().count();
		long expected = 1;//there is only the void factory
		assertEquals(expected, actual);
	}
}
