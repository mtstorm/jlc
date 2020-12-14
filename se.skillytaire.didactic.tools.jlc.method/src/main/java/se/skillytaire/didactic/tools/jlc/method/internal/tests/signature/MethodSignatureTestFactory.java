package se.skillytaire.didactic.tools.jlc.method.internal.tests.signature;

import se.skillytaire.didactic.tools.jlc.method.spi.MethodTestFactory;
import se.skillytaire.didactic.tools.jlc.method.spi.model.config.TestMethodConfiguration;
import se.skillytaire.didactic.tools.jlc.spi.model.structure.JLCTestNode;

public class MethodSignatureTestFactory <T> implements MethodTestFactory<T>{



	@Override
	public JLCTestNode<T> create(TestMethodConfiguration<T> configuration) {
		return new MethodSignatureTest<>(configuration);
	}

	@Override
	public boolean matches(TestMethodConfiguration<T> configuration) {
		return !configuration.getSignature().isApi();
	
	}

}
