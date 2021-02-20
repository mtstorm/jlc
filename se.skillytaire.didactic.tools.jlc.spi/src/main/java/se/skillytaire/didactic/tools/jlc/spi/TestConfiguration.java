package se.skillytaire.didactic.tools.jlc.spi;

import java.net.URI;
import java.util.Optional;

import se.skillytaire.didactic.tools.jlc.api.Archetype;
import se.skillytaire.didactic.tools.jlc.api.Archetypeable;
import se.skillytaire.didactic.tools.jlc.api.JLCConfiguration;
import se.skillytaire.didactic.tools.jlc.spi.e.Configuration;
import se.skillytaire.didactic.tools.jlc.spi.e.JLCFeatureConfiguration;


/**
 * 
 *
 * @param <N>
 * @param <T>
 */
public interface TestConfiguration<E extends JLCFeatureConfiguration, N extends TestConfiguration<E,N,T>,T> extends Configuration, Archetypeable, Comparable<N> {

	/**
	 * Get the extension defaults.
	 * @return
	 */
	E getExtentionDefaults();

	
//	boolean isEnabled();
//FIXME nadenken over getName versus getDisplayNameValue
	String getName();

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
