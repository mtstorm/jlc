package se.skillytaire.didactic.tools.jlc.spi.ext.feature;

import java.net.URI;
import java.util.Optional;

import se.skillytaire.didactic.tools.jlc.spi.TestConfiguration;
import se.skillytaire.didactic.tools.jlc.spi.e.JLCFeatureConfiguration;
import se.skillytaire.didactic.tools.jlc.spi.model.naming.BasicDisplayName;
import se.skillytaire.didactic.tools.jlc.spi.model.naming.DisplayName;
import se.skillytaire.didactic.tools.jlc.spi.model.structure.CompositeTestNode;
/**
 * Represents a root node for a given single configuration element. 
 *
 * @param <D>
 * @param <C>
 * @param <T>
 */
public interface TestConfigurationTestNode <D extends JLCFeatureConfiguration, C extends TestConfiguration<D,C, T>, T> 
 		extends CompositeTestNode<T>,BuildableTestNode{
	C getTestConfiguration();
	@Override
	default Optional<URI> toUri() {
		return this.getTestConfiguration().toUri();
	}

	@Override
	default DisplayName getDisplayName() {
		return new BasicDisplayName(getTestConfiguration().getName());
	}
}
