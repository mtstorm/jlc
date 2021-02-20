package se.skillytaire.didactic.tools.jlc.api;

import java.util.Arrays;

/**
 *
 *
 */
public interface ClassifiedFactory {
	/**
	 * A single type
	 *
	 * @return
	 */
	Class<?> type();

	/**
	 * Some factories supporting multiple types.
	 *
	 * @return
	 */
	default Class<?>[] types() {
		return new Class[] { type() };
	}

	default boolean isFor(final Class<?> type) {
		return Arrays.stream(types()).filter(c -> c.equals(type)).findFirst().isPresent();
	}

}
