package se.skillytaire.didactic.tools.jlc.core.sample;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DynamicNode;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;

import se.skillytaire.didactic.tools.jlc.api.AbstractTestObjectFactory;
import se.skillytaire.didactic.tools.jlc.api.JLC;
import se.skillytaire.didactic.tools.jlc.api.This;
/**
 * This sample test shows how to integrate JLC to your tests. Remember that this is only the core
 * and does not provide additional tests.
 *
 */
@JLC(value = JLCTestClassSampleTest.JLCDemo.class, testFactory = JLCTestClassSampleTest.JLCDemoTestObjectFactory.class)
public class JLCTestClassSampleTest {
	@This
	private JLCDemo inject;
	
	@Test
	public void thisInject() {
		assertNotNull(inject, "JLC should injected here a value");
	}
	/** Class under test */
	public static class JLCDemo {
		public JLCDemo() {}
	}
	/** Class under test factory implementation*/
	public static class JLCDemoTestObjectFactory extends AbstractTestObjectFactory<JLCDemo> {
		@Override
		public JLCDemo createThis() {
			return new JLCDemo();
		}
		@Override
		public JLCDemo createThat() {
			return new JLCDemo();
		}
	}
}

