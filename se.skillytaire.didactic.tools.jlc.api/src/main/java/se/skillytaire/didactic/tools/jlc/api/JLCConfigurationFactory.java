package se.skillytaire.didactic.tools.jlc.api;

/**
 * Allows other developers to incorporate there own implementation for the
 * configuration of JLC.
 *
 * This is possible overdesign, but it allows the JLC api clean of
 * implementation.
 */
public interface JLCConfigurationFactory {
	/**
	 * Creates a new instance of the JLC configuration based on a test instance (aka
	 * JUnit instance)
	 *
	 * @param <T>          The type of the thing that is under test
	 * @param testInstance The JUnit test instance
	 * @return A new configuration based on the test instance and instance under
	 *         test.
	 */
	<T> JLCConfiguration<T> create(Object testInstance);
}
