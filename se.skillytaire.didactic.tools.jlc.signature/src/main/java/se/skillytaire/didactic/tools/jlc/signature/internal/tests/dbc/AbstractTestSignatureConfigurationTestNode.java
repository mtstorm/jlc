package se.skillytaire.didactic.tools.jlc.signature.internal.tests.dbc;

import java.lang.reflect.Executable;
import java.net.URI;
import java.util.Optional;

import se.skillytaire.didactic.tools.jlc.signature.spi.Signature;
import se.skillytaire.didactic.tools.jlc.signature.spi.model.config.TestSignatureConfiguration;
import se.skillytaire.didactic.tools.jlc.spi.model.structure.AbstractJLCCompositeTestNode;

public abstract class AbstractTestSignatureConfigurationTestNode<N extends TestSignatureConfiguration<N,T,S,E>,T, S extends Signature,E extends Executable> extends AbstractJLCCompositeTestNode<T>{
	protected final N testConfiguration;
	
	public AbstractTestSignatureConfigurationTestNode(N config) {
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
	protected N getTestConfiguration() {
		return testConfiguration;
	}

}
