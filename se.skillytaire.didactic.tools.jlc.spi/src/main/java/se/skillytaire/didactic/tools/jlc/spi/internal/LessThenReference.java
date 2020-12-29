package se.skillytaire.didactic.tools.jlc.spi.internal;

import se.skillytaire.didactic.tools.jlc.api.ComparableTestObjectFactory;

public class LessThenReference extends TestFactoryInvoker<ComparableTestObjectFactory<?>> {
	public LessThenReference(ComparableTestObjectFactory<?> factory) {
		super(factory);
	}


	protected String getMessageName() {
		return "createLessThen";
	}

	@Override
	protected Object invoke(ComparableTestObjectFactory<?> factory) {
		return factory.createLessThen();
	}

}
