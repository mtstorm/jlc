package se.skillytaire.didactic.tools.jlc.method.internal.tests.tostring;

import se.skillytaire.didactic.tools.jlc.method.spi.MethodTestFactory;
import se.skillytaire.didactic.tools.jlc.method.spi.model.config.TestMethodConfiguration;
import se.skillytaire.didactic.tools.jlc.signature.spi.MethodSignature;

public abstract class AbstractToStringTestFactory<T> implements MethodTestFactory<T>{

	@Override
	public final boolean matches(TestMethodConfiguration<T> configuration) {
		return configuration.isForSignature(MethodSignature.TO_STRING_METHOD_SIGNATURE);
	}

}
