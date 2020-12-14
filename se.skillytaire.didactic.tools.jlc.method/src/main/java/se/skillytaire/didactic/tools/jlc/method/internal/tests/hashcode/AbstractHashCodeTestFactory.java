package se.skillytaire.didactic.tools.jlc.method.internal.tests.hashcode;

import se.skillytaire.didactic.tools.jlc.method.spi.MethodTestFactory;
import se.skillytaire.didactic.tools.jlc.method.spi.model.config.TestMethodConfiguration;
import se.skillytaire.didactic.tools.jlc.signature.spi.MethodSignature;

public abstract class AbstractHashCodeTestFactory<T> implements MethodTestFactory<T>{

	@Override
	public final boolean matches(TestMethodConfiguration<T> configuration) {
		return configuration.isForSignature(MethodSignature.HASH_CODE_METHOD_SIGNATURE);
	}

}
