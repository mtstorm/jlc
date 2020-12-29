package se.skillytaire.didactic.tools.jlc.api;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class EnforcerSPITest {
	@Test
	public void spiTest() {
		long actual = Enforcer.stream().count();
		long expected = 0;//there is no enforcer in the the api.
		assertEquals(expected, actual);
	}
}
