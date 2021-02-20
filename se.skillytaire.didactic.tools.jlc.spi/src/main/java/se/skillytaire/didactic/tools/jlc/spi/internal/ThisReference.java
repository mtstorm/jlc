package se.skillytaire.didactic.tools.jlc.spi.internal;

import se.skillytaire.didactic.tools.jlc.api.TestObjectFactory;

public class ThisReference extends TestFactoryInvoker<TestObjectFactory<?>> {
	public ThisReference(TestObjectFactory<?> factory) {
		super(factory);
	}


	protected String getMessageName() {
		return "createThis";
	}

	@Override
	protected Object invoke(TestObjectFactory<?> factory) {
		return factory.createThis();
	}

}
