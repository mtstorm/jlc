package se.skillytaire.didactic.tools.jlc.method.internal.tests.hashcode;

import se.skillytaire.didactic.tools.jlc.api.TestObjectFactory;
import se.skillytaire.didactic.tools.jlc.method.internal.tests.AbstractSanityTest;
import se.skillytaire.didactic.tools.jlc.method.internal.tests.TestPartObjectFactory;
import se.skillytaire.didactic.tools.jlc.method.spi.model.config.TestMethodConfiguration;

public class HashCodeSanityTest <T> extends AbstractSanityTest<T, Boolean, HashCodeSanityPartTest<T>> {
	public HashCodeSanityTest(TestMethodConfiguration config) {
		super(config);
	}

	@Override
	public TestPartObjectFactory<T, Boolean> get() {
		TestObjectFactory<T> testFactory = getConfiguration().getObjectFactory();
		return new HashCodeSanityObjectFactory<T>(testFactory);
	}

	@Override
	public HashCodeSanityPartTest<T> apply(TestPartObjectFactory<T, Boolean> factory) {
		return new HashCodeSanityPartTest<T>(getTestConfiguration(), factory);
	}

}
