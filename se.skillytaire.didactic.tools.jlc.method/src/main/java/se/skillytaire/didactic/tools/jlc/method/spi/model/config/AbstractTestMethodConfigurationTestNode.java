package se.skillytaire.didactic.tools.jlc.method.spi.model.config;

import java.net.URI;
import java.util.Optional;

import se.skillytaire.didactic.tools.jlc.spi.model.structure.AbstractJLCCompositeTestNode;
@Deprecated
/**
 * check AbstractConfigurationTestNode
 * @author prolector
 *
 * @param <T>
 */
public abstract class AbstractTestMethodConfigurationTestNode<T> extends AbstractJLCCompositeTestNode<T>{
	private final TestMethodConfiguration<T> testConfiguration;
	
	public AbstractTestMethodConfigurationTestNode(TestMethodConfiguration<T> config) {
		if(config == null) {
			throw new IllegalArgumentException("Configuration is void");
		}
		this.testConfiguration = config;
		
		
	}
	@Override
	public Optional<URI> toUri() {
		return this.testConfiguration.toUri();
	}
//fixme niet netjes oo dit
	protected TestMethodConfiguration<T> getTestConfiguration() {
		return testConfiguration;
	}

}
