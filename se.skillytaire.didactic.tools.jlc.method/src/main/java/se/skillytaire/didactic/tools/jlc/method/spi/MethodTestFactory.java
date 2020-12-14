package se.skillytaire.didactic.tools.jlc.method.spi;

import java.util.ArrayList;
import java.util.ServiceLoader;
import java.util.stream.Stream;

import se.skillytaire.didactic.tools.jlc.method.spi.model.config.TestMethodConfiguration;
import se.skillytaire.didactic.tools.jlc.spi.model.structure.JLCTestNode;

/**
 * The method test factory is enabled for zero or more signatures. When
 * 
 * @author prolector
 *
 */
public interface MethodTestFactory<T> {
	boolean matches(TestMethodConfiguration<T> configuration);

	/**
	 * Creates a new test for the method having the test method configuration.
	 */
	JLCTestNode<T> create(TestMethodConfiguration<T> configuration);

	@SuppressWarnings("unchecked")
	static <T> Stream<JLCTestNode<T>> find(TestMethodConfiguration<T> configuration) {
		if (configuration == null) {
			throw new IllegalArgumentException("configuration is void");
		}
		Stream<JLCTestNode<T>> result;
		if (configuration.isEnabled()) {
			ClassLoader loader = Thread.currentThread().getContextClassLoader();
			@SuppressWarnings("rawtypes")
			ServiceLoader<MethodTestFactory> serviceLoader = ServiceLoader.load(MethodTestFactory.class, loader);

			ArrayList<JLCTestNode<T>> nodes = new ArrayList<>();
			for (MethodTestFactory<T> factory : serviceLoader) {
				if (factory.matches(configuration)) {
					nodes.add((JLCTestNode<T>) factory.create(configuration));
				}
			}

			result = nodes.stream();
		} else {
			result = Stream.empty();
		}
		return result;

	}
}
