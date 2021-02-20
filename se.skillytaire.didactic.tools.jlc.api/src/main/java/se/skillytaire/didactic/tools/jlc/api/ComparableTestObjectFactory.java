package se.skillytaire.didactic.tools.jlc.api;

public interface ComparableTestObjectFactory<T> extends TestObjectFactory<T> {
	/**
	 * That should create an instance less then that.
	 * 
	 * The result should never be null, and should result for every call in a new
	 * reference.
	 *
	 * @return
	 */
	T createLessThen();

	/**
	 * That should create an instance greater then that. The result should never be
	 * null, and should result for every call in a new reference.
	 *
	 * @return
	 */
	T createGreaterThen();

}
