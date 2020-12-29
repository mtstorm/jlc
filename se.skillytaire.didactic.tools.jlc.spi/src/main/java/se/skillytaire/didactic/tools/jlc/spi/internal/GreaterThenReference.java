package se.skillytaire.didactic.tools.jlc.spi.internal;

import se.skillytaire.didactic.tools.jlc.api.ComparableTestObjectFactory;

public class GreaterThenReference extends TestFactoryInvoker<ComparableTestObjectFactory<?>> {
	public GreaterThenReference(ComparableTestObjectFactory<?> factory) {
		super(factory);
	}


	protected String getMessageName() {
		return "createGreaterThen";
	}

	@Override
	protected Object invoke(ComparableTestObjectFactory<?> factory) {
		return factory.createGreaterThen();
	}

}
