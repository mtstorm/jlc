package se.skillytaire.didactic.tools.jlc.apiold;

import java.util.Optional;

public interface TestObjectFactory<T> {
	
	/**
	 * This should not be equals to that.
	 * 
	 * @return
	 */
	T createThis();

	/**
	 * That should not be equals to this.
	 * 
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


}
