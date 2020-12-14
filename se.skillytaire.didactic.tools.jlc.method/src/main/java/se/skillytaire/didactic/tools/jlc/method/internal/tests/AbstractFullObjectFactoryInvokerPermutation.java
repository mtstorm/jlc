package se.skillytaire.didactic.tools.jlc.method.internal.tests;

import se.skillytaire.didactic.tools.jlc.api.TestObjectFactory;

public abstract class AbstractFullObjectFactoryInvokerPermutation<T,R> extends AbstractObjectFactoryInvokerPermutation<T,R>{

	public AbstractFullObjectFactoryInvokerPermutation(TestObjectFactory<T> testFactory) {
		super(testFactory);
	}

	protected final int calculateSize() {
		return (int) Math.pow(2, getPower()) -1;
	}
}
