package se.skillytaire.didactic.tools.jlc.spi.model.config;

import java.net.URI;
import java.util.Optional;

import se.skillytaire.didactic.tools.jlc.spi.model.Archetype;
import se.skillytaire.didactic.tools.jlc.spi.model.Archetypeable;
import se.skillytaire.didactic.tools.jlc.spi.model.naming.DisplayName;



public interface TestConfiguration<N extends TestConfiguration<N,T>,T> extends Archetypeable, Comparable<N> {
	boolean isEnabled();

	/**
	 * Get the dispay name of the configuration.
	 * 
	 * @return
	 */
	DisplayName getDisplayName();

	Optional<URI> toUri();

	/**
	 * Get the parent configuration.
	 * 
	 * @return
	 */
	JLCConfiguration<T> getParent();

	/**
	 * When declared using annotations, the configuration is enforced.
	 * 
	 * @return
	 */
	boolean isEnforced();

	/**
	 * When enforcing, the configuration itself will be enabled and enforces having
	 * the archetype that causes the enforcement.
	 */
	void enforce(Archetype archetype);

	/**
	 * Merge this with an other configuration.
	 * @param <N>
	 * @param n
	 */
	void merge(N n);
	boolean hasFeature();
	String getFeature();
	
	boolean hasArchetype();
	Archetype getArchetype();
}
