package se.skillytaire.didactic.tools.jlc.api;

import java.util.Optional;
import java.util.ServiceLoader;
import java.util.stream.Stream;

/**
 * JLC allows you to create instances of the thing that is under test in your
 * test case. When implementing this interface, the creation of different
 * instances is managed here.
 *
 *
 * @param <T> The type of the thing that should be created.
 */
public interface TestObjectFactory<T> extends ClassifiedFactory {

	/**
	 * This should not be equals to that. The result should never be null, and
	 * should result for every call in a new reference.
	 *
	 * @return
	 */
	T createThis();

	/**
	 * That should not be equals to this. The result should never be null, and
	 * should result for every call in a new reference.
	 *
	 * @return
	 */
	T createThat();

	/**
	 * Override this when your T is numeric value based.
	 *
	 * @param numericValue
	 * @return
	 */
	default Optional<T> create(final long numericValue) {
		return Optional.ofNullable(null);
	}

	static Stream<TestObjectFactory<?>> resolve() {
		final ServiceLoader<TestObjectFactory> serviceLoader = ServiceLoader.load(TestObjectFactory.class);
		return serviceLoader.stream().map(x -> x.get());
	}
}
