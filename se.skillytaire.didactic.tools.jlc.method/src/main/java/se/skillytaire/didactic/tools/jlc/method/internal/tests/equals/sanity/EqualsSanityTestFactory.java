package se.skillytaire.didactic.tools.jlc.method.internal.tests.equals.sanity;

import se.skillytaire.didactic.tools.jlc.method.internal.tests.equals.AbstractEqualsTestMethodFactory;
import se.skillytaire.didactic.tools.jlc.method.spi.model.config.TestMethodConfiguration;
import se.skillytaire.didactic.tools.jlc.spi.model.structure.JLCTestNode;

public class EqualsSanityTestFactory <T> extends AbstractEqualsTestMethodFactory<T>{



	@Override
	public JLCTestNode<T> create(TestMethodConfiguration<T> configuration) {
		return new EqualsSanityTest<>(configuration);
	}

}
