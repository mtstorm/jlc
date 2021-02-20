package se.skillytaire.didactic.tools.jlc.api;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;

public class VoidTestObjectFactoryTest {
	@Test
	public void testCreateThis() {
		VoidTestObjectFactory beanUnderTest = new VoidTestObjectFactory();
		assertNull(beanUnderTest.createThis());
	}

	@Test
	public void testCreateThat() {
		VoidTestObjectFactory beanUnderTest = new VoidTestObjectFactory();
		assertNull(beanUnderTest.createThat());
	}

	public void testIsForVoid() {
		VoidTestObjectFactory beanUnderTest = new VoidTestObjectFactory();
		assertTrue(beanUnderTest.isFor(Void.class));
	}

	@Test
	public void testIsTypeVoid2() {
		VoidTestObjectFactory beanUnderTest = new VoidTestObjectFactory();
		assertTrue(beanUnderTest.isFor(void.class));
	}
	@Test
	public void testIsForObject() {
		VoidTestObjectFactory beanUnderTest = new VoidTestObjectFactory();
		assertFalse(beanUnderTest.isFor(Object.class));
	}
	@Test
	public void testCreate() {
		VoidTestObjectFactory beanUnderTest = new VoidTestObjectFactory();
		Optional<Void> result = beanUnderTest.create(12);
		assertTrue(result.isEmpty());
	}
	
	@Test
	public void testType() {
		VoidTestObjectFactory beanUnderTest = new VoidTestObjectFactory();
		Class<Void> result = beanUnderTest.type();
		assertTrue(result == Void.class);
	}
	
	@Test
	public void testTypes() {
		VoidTestObjectFactory beanUnderTest = new VoidTestObjectFactory();
		Class<?>[] actual = beanUnderTest.types();
		Class<?>[] expected = new Class[] {Void.class,Void.TYPE};
		assertArrayEquals(expected, actual);
		
	}
}
