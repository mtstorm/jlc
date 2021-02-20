package se.skillytaire.didactic.tools.jlc.signature.spi;

import java.lang.reflect.Executable;
import java.util.ArrayList;
import java.util.ServiceLoader;
import java.util.stream.Stream;

import se.skillytaire.didactic.tools.jlc.signature.spi.model.config.TestSignatureConfiguration;
import se.skillytaire.didactic.tools.jlc.spi.e.JLCFeatureConfiguration;
import se.skillytaire.didactic.tools.jlc.spi.model.structure.JLCTestNode;

/**
 * The method test factory is enabled for zero or more signatures. When
 * 
 * @author prolector
 *
 */
public interface SignatureTestFactory<
N extends TestSignatureConfiguration<N,T,S,E,D>,
T, 
S extends Signature, 
E extends Executable,
D extends JLCFeatureConfiguration> {
	boolean matches(N configuration);

	/**
	 * Creates a new test for the method having the test method configuration.
	 */
	JLCTestNode<T> create(N configuration);

	@SuppressWarnings("unchecked")
	static <N extends TestSignatureConfiguration<N,T,S,E,D>,T, S extends Signature, E extends Executable,D extends JLCFeatureConfiguration> Stream<JLCTestNode<T>> find(
			N configuration) {
		if (configuration == null) {
			throw new IllegalArgumentException("configuration is void");
		}
		Stream<JLCTestNode<T>> result;
		if (configuration.isEnabled()) {
			ClassLoader loader = Thread.currentThread().getContextClassLoader();
			@SuppressWarnings("rawtypes")
			ServiceLoader<SignatureTestFactory> serviceLoader = ServiceLoader.load(SignatureTestFactory.class, loader);

			ArrayList<JLCTestNode<T>> nodes = new ArrayList<>();
			for (SignatureTestFactory<N,T, S, E,D> factory : serviceLoader) {
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
