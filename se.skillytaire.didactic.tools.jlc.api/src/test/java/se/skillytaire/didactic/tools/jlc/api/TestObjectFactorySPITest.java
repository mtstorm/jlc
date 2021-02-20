package se.skillytaire.didactic.tools.jlc.api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.stream.Stream;

import org.junit.jupiter.api.Test;

public class TestObjectFactorySPITest {
	@Test
	public void testCount() {
		long actual = TestObjectFactory.resolve().count();
		long expected = 1;//there is only the void factory
		assertEquals(expected, actual);
	}
	
	@Test
	public void testType() {
		Stream<TestObjectFactory<?>> actual = TestObjectFactory.resolve();
		assertTrue(actual.findFirst().get().getClass() == VoidTestObjectFactory.class);
	}
}
