package se.skillytaire.didactic.tools.jlc.method.internal.tests.equals;

import se.skillytaire.didactic.tools.jlc.method.spi.model.config.TestMethodConfiguration;
import se.skillytaire.didactic.tools.jlc.spi.model.structure.JLCSingleTestNode;

public class EqualsNullTestFactory<T> extends AbstractEqualsTestMethodFactory<T>{

	@Override
	public JLCSingleTestNode<T> create(TestMethodConfiguration<T> configuration) {
		return new EqualsNullTest<>(configuration);
	}

}
