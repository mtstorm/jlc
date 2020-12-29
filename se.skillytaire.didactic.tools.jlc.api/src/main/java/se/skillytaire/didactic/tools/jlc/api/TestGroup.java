package se.skillytaire.didactic.tools.jlc.api;
/**
 * When you have a huge number of methods, it can help to group them.
 * 
 * 
 */
public enum TestGroup {
	/**
	 * No grouping.
	 */
	None,
	/**
	 * Test features can be marked as overloaded features.
	 */
	Overload,
	/**
	 * Test features can be marked as API features.
	 */
	Api,
	/**
	 * Test features can be marked having a feature
	 */
	Feature,
	/**
	 * Test features can be marked having an archetype.
	 */
	Archetype,
	/**
	 * Propagates all the grouping operations.
	 */
	All
}
