package se.skillytaire.didactic.tools.jlc.api;

public interface Archetypeable {
	/**
	 * Checks if the type enables this.
	 * @param type
	 * @return
	 */
   boolean enabledForType(Archetype type) ;
   boolean hasArchetype();
   Archetype getArchetype();
}
