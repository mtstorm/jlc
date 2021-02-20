package se.skillytaire.didactic.tools.jlc.api;

import java.util.Optional;
import java.util.stream.Stream;

import org.junit.jupiter.api.DynamicNode;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestInstancePostProcessor;

/**
 * The JLCTestBuilder is the hook between JUnit and the JLC didactic framework.
 *
 */
public final class JLCTestBuilder implements BeforeEachCallback, TestInstancePostProcessor {
	private JLCConfiguration<Object> configuration;

	@Override
	public void postProcessTestInstance(final Object testInstance, final ExtensionContext context) throws Exception {
	   configuration = JLCConfiguration.of(testInstance); // new JLCConfigurationImpl<>(testInstance);
	}

	@Override
	public void beforeEach(final ExtensionContext context) throws Exception {
		final Optional<Object> optionalTestInstance = context.getTestInstance();
		if (optionalTestInstance.isPresent()) {
		   configuration.autowire();
		}
	}

	/**
	 * Note: As of JUnit Jupiter 5.4.2, dynamic tests must always be created by
	 * factory methods; however, this might be complemented by a registration
	 * facility in a later release.
	 *
	 * @param testInstance
	 * @return
	 */
	public static Stream<DynamicNode> build(final Object testInstance) {
		return JLCConfiguration.of(testInstance).stream();
	}
}
