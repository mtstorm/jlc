package se.skillytaire.didactic.tools.jlc.api;

import java.util.Optional;
import java.util.ServiceLoader;
import java.util.stream.Stream;

public interface TestObjectFactory<T> {
	
	/**
	 * This should not be equals to that.
	 * The result should never be null, and should result for every call in a new reference.
	 * @return
	 */
	T createThis();

	/**
	 * That should not be equals to this.
	 * The result should never be null, and should result for every call in a new reference.
	 * @return
	 */
	T createThat();

	/**
	 * The type
	 * 
	 * @return
	 */
	Class<T> type();
	
	/**
	 * Some object factories supporting multiple types, like boolean and Boolean.
	 * @return
	 */
	default Class<?>[] types(){
		return new Class[] {type()};
	}

	/**
	 * Override this when your T is numeric value based.
	 * 
	 * @param numericValue
	 * @return
	 */
	default Optional<T> create(long numericValue) {
		return Optional.ofNullable(null);
	}

	boolean isTypeFor(Class<?> type);

	public static Stream<TestObjectFactory<?>> resolve() {
		ServiceLoader<TestObjectFactory> serviceLoader = ServiceLoader.load(TestObjectFactory.class);
		return serviceLoader.stream().map( x-> x.get() );
	}
}
