package se.skillytaire.didactic.tools.jlc.spi.internal;

import java.util.function.Function;

import se.skillytaire.didactic.tools.jlc.api.TestObjectFactory;

public class ThatReference extends TestFactoryInvoker<TestObjectFactory<?>> {
	public ThatReference(TestObjectFactory<?> factory) {
		super(factory);
	}


	protected String getMessageName() {
		return "createThat";
	}

	@Override
	protected Object invoke(TestObjectFactory<?> factory) {
		return factory.createThat();
	}

}
