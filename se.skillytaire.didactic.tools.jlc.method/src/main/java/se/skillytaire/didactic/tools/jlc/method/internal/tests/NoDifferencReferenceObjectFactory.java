package se.skillytaire.didactic.tools.jlc.method.internal.tests;

import se.skillytaire.didactic.tools.jlc.api.TestObjectFactory;

public abstract class NoDifferencReferenceObjectFactory <T,R> extends AbstractObjectFactoryInvokerPermutation<T,R>{

	public NoDifferencReferenceObjectFactory(TestObjectFactory<T> testFactory) {
		super(testFactory);
	}

	protected final int calculateSize() {
		return getPower() - 1;
	}
}
