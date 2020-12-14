package se.skillytaire.didactic.tools.jlc.method.internal.tests.tostring;

import se.skillytaire.didactic.tools.jlc.method.internal.tests.ConcreteOverrideTest;
import se.skillytaire.didactic.tools.jlc.method.spi.model.config.TestMethodConfiguration;
import se.skillytaire.didactic.tools.jlc.spi.model.structure.JLCTestNode;

public class OverrideToStringTestFactory <T> extends AbstractToStringTestFactory<T>{

	@Override
	public JLCTestNode<T> create(TestMethodConfiguration<T> configuration) {
		return new ConcreteOverrideTest<>(configuration);
	}

}
