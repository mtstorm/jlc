package se.skillytaire.didactic.tools.jlc.spi;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DynamicNode;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;

import se.skillytaire.didactic.tools.jlc.api.AbstractTestObjectFactory;
import se.skillytaire.didactic.tools.jlc.api.JLC;
import se.skillytaire.didactic.tools.jlc.api.JLCConfigurationTest;
import se.skillytaire.didactic.tools.jlc.api.JLCTestBuilder;
import se.skillytaire.didactic.tools.jlc.api.JLCTestFactory;
import se.skillytaire.didactic.tools.jlc.api.PackagePresentation;
import se.skillytaire.didactic.tools.jlc.api.This;
/**
 * This sample test shows how to integrate JLC to your tests. Remember that this is only the core
 * and does not provide additional tests.
 *
 */
@JLC(showEmptyTests = true, value = JLCInjectionSampleTest.JLCDemo.class, testFactory = JLCInjectionSampleTest.JLCDemoTestObjectFactory.class)
@JLCConfigurationTest
public class JLCInjectionSampleTest {
	@This
	//@That
	private JLCDemo obj;
	@TestFactory
	@DisplayName("JLC")
	public Stream<DynamicNode> jlc() {
		return JLCTestBuilder.build(this);
	}
	
	@Test
	public void thisObjectTest() {
		assertNotNull(obj, "JLC should injected here a value");
	}
	
	
	/** Class under test */
	public static class JLCDemo {
		public JLCDemo() {}
	}
	/** Class under test factory implementation*/
	//@JLCTestFactory(path = "a/b/c",displayName = @DisplayName("LUL"))
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

