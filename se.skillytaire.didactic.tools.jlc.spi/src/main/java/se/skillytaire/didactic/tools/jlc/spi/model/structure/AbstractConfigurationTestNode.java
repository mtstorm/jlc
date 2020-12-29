package se.skillytaire.didactic.tools.jlc.spi.model.structure;

import java.net.URI;
import java.util.Optional;

import se.skillytaire.didactic.tools.jlc.api.TestConfiguration;
import se.skillytaire.didactic.tools.jlc.spi.model.naming.BasicDisplayName;
import se.skillytaire.didactic.tools.jlc.spi.model.naming.DisplayName;
/**
 * This is a composite node for adding specified test based on the configuration.
 * @author prolector
 *
 * @param <T>
 * @param <C>
 */
public abstract class AbstractConfigurationTestNode<T, C extends TestConfiguration<C,T> > extends AbstractJLCCompositeTestNode<T>{
	protected final C config;
	
	public AbstractConfigurationTestNode(C config) {
		if(config == null) {
			throw new IllegalArgumentException("Configuration is void");
		}
		this.config = config;
		
		
	}
	@Override
	public Optional<URI> toUri() {
		return this.config.toUri();
	}

	protected final C getTestConfiguration() {
		return config;
	}
	@Override
	public DisplayName getDisplayName() {
		return new BasicDisplayName(config.getName());
	}
}
