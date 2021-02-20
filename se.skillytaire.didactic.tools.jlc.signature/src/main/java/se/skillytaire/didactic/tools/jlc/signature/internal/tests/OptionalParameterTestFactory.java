package se.skillytaire.didactic.tools.jlc.signature.internal.tests;

import java.lang.reflect.Executable;

import se.skillytaire.didactic.tools.jlc.signature.spi.Signature;
import se.skillytaire.didactic.tools.jlc.signature.spi.SignatureTestFactory;
import se.skillytaire.didactic.tools.jlc.signature.spi.model.config.TestSignatureConfiguration;
import se.skillytaire.didactic.tools.jlc.spi.e.JLCFeatureConfiguration;
import se.skillytaire.didactic.tools.jlc.spi.model.structure.JLCTestNode;

public class OptionalParameterTestFactory<N extends TestSignatureConfiguration<N,T,S,E,D>,T, S extends Signature,E extends Executable,D extends JLCFeatureConfiguration> implements SignatureTestFactory<N,T,S,E,D>{

	@Override
	public boolean matches(N configuration) {
		return configuration.getSignature().hasParameters();
	}

	@Override
	public JLCTestNode<T> create(N configuration) {
		return new OptionalParameterTest<>(configuration);
	}

}
