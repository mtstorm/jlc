package se.skillytaire.didactic.tools.jlc.spi.model.structure;

import java.net.URI;
import java.util.Optional;

import se.skillytaire.didactic.tools.jlc.api.Archetype;
import se.skillytaire.didactic.tools.jlc.api.JLCConfiguration;
import se.skillytaire.didactic.tools.jlc.spi.model.naming.DisplayName;

/**
 * The component definition for the tests.
 * 
 * @author prolector
 *
 */
public interface JLCTestNode<T> {
	/**
	 * The display name for the tooling
	 * 
	 * @return
	 */
	DisplayName getDisplayName();

	/**
	 * The uri for this node.
	 * 
	 * @return
	 */
	Optional<URI> toUri();

	/**
	 * Initialize this node with the JLC configuration.
	 * 
	 * @param configuration
	 */
	void init(JLCConfiguration<T> configuration);

	/**
	 * When a test introduces an other TestObjectFactory and it is needed to also
	 * run this test with that other factory, this should return true. Tests using
	 * internal state checks are possible candidates. Structural tests are most
	 * likely to return false, like the parameter count check.
	 * 
	 * @return default to true.
	 */
	default public boolean isReRunnable() {
		return true;
	}

	/**
	 * Every test is configured for default execution. When applying the archetype,
	 * the configuration of this test will be moved to a subset marked by this
	 * archetype.
	 * 
	 * A linter will filter these tests.
	 * 
	 * @param archetype
	 */
	void applyFilter(Archetype archetype);

	boolean hasChildren();
}
