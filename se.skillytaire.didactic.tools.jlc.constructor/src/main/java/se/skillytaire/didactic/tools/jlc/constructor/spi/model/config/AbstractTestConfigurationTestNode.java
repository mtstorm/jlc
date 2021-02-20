package se.skillytaire.didactic.tools.jlc.constructor.spi.model.config;

import java.lang.reflect.Executable;
import java.net.URI;
import java.util.Optional;

import se.skillytaire.didactic.tools.jlc.signature.spi.Signature;
import se.skillytaire.didactic.tools.jlc.signature.spi.model.config.TestSignatureConfiguration;
import se.skillytaire.didactic.tools.jlc.spi.model.structure.AbstractJLCCompositeTestNode;
@Deprecated
public abstract class AbstractTestConfigurationTestNode<T, C extends TestSignatureConfiguration<C,T, S,E,?>,S extends Signature,E extends Executable> extends AbstractJLCCompositeTestNode<T>{
	private final C testConfiguration;
	
	public AbstractTestConfigurationTestNode(C config) {
		if(config == null) {
			throw new IllegalArgumentException("Configuration is void");
		}
		this.testConfiguration = config;
		
		
	}
	@Override
	public Optional<URI> toUri() {
		return this.testConfiguration.toUri();
	}

	protected C getTestConfiguration() {
		return testConfiguration;
	}

}
