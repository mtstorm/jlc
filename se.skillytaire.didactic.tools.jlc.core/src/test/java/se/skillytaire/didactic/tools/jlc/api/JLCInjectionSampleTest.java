package se.skillytaire.didactic.tools.jlc.api;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DynamicNode;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;
/**
 * This sample test shows how to integrate JLC to your tests. Remember that this is only the core
 * and does not provide additional tests.
 *
 */
@JLC(value = JLCInjectionSampleTest.JLCDemo.class, testFactory = JLCInjectionSampleTest.JLCDemoTestObjectFactory.class)
public class JLCInjectionSampleTest {
	@This
	//@That
	private String thisString;
	@TestFactory
	@DisplayName("JLC")
	public Stream<DynamicNode> jlc() {
		return JLCTestBuilder.build(this);
	}
	
	@Test
	public void thisStringTest() {
		assertNotNull(thisString, "JLC should injected here a value");
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

