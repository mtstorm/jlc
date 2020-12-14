package se.skillytaire.didactic.tools.jlc.method.internal.tests.equals.sanity;

import se.skillytaire.didactic.tools.jlc.api.TestObjectFactory;
import se.skillytaire.didactic.tools.jlc.method.internal.tests.AbstractSanityTest;
import se.skillytaire.didactic.tools.jlc.method.internal.tests.TestPartObjectFactory;
import se.skillytaire.didactic.tools.jlc.method.spi.model.config.TestMethodConfiguration;

public final class EqualsSanityTest<T> extends AbstractSanityTest<T, Boolean, EqualsSanityPartTest<T>> {
	public EqualsSanityTest(TestMethodConfiguration<T> config) {
		super(config);
	}

	@Override
	public TestPartObjectFactory<T, Boolean> get() {
		TestObjectFactory<T> testFactory = getConfiguration().getObjectFactory();
		return new EqualsObjectFactoryInvokerPermutation<T>(testFactory);
	}

	@Override
	public EqualsSanityPartTest<T> apply(TestPartObjectFactory<T, Boolean> factory) {
		return new EqualsSanityPartTest<T>(getTestConfiguration(), factory);
	}



}
