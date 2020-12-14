package se.skillytaire.didactic.tools.jlc.method.internal.tests.equals;

import se.skillytaire.didactic.tools.jlc.method.spi.MethodTestFactory;
import se.skillytaire.didactic.tools.jlc.method.spi.model.config.TestMethodConfiguration;
import se.skillytaire.didactic.tools.jlc.signature.spi.MethodSignature;

public abstract class AbstractEqualsTestMethodFactory<T> implements MethodTestFactory<T>{

	@Override
	public boolean matches(TestMethodConfiguration<T> configuration) {
		return configuration.isForSignature(MethodSignature.EQUALS_METHOD_SIGNATURE);
	}


}
