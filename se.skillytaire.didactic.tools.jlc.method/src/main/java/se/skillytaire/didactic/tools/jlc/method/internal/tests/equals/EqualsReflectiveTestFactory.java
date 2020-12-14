package se.skillytaire.didactic.tools.jlc.method.internal.tests.equals;

import se.skillytaire.didactic.tools.jlc.method.spi.model.config.TestMethodConfiguration;
import se.skillytaire.didactic.tools.jlc.spi.model.structure.JLCTestNode;

public class EqualsReflectiveTestFactory<T> extends AbstractEqualsTestMethodFactory<T>{

	@Override
	public JLCTestNode<T> create(TestMethodConfiguration<T> configuration) {
		return new EqualsReflectiveTest(configuration);
	}

}
