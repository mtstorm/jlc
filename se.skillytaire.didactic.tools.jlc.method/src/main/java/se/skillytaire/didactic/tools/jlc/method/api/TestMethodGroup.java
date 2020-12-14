package se.skillytaire.didactic.tools.jlc.method.api;
/**
 * When you have a huge number of methods, it can help to group them.
 * 
 * 
 * @author prolector
 *
 */
public enum TestMethodGroup {
	/**
	 * Makes an extra level for methods with the same name, having differend parameter. (Overloading)
	 */
	Overload,
	/**
	 * Methods can be marked as API methods, like equals and hashCode
	 */
	Api,
	/**
	 * Methods can be marked having a feature
	 */
	Feature,
	/**
	 * Method can be marked having an archetype.
	 */
	Archetype,
	/**
	 * Propagates all the grouping operations
	 */
	ALL
}
