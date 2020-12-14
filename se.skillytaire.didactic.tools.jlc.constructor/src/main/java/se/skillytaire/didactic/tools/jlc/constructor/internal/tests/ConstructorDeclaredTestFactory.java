package se.skillytaire.didactic.tools.jlc.constructor.internal.tests;

import se.skillytaire.didactic.tools.jlc.constructor.spi.ConstructorTestFactory;
import se.skillytaire.didactic.tools.jlc.constructor.spi.model.config.TestConstructorConfiguration;
import se.skillytaire.didactic.tools.jlc.spi.model.structure.JLCTestNode;

public class ConstructorDeclaredTestFactory<T> implements ConstructorTestFactory<T>{

	@Override
	public boolean matches(TestConstructorConfiguration<T> configuration) {
		return configuration.isEnforced();
	}

	@Override
	public JLCTestNode<T> create(TestConstructorConfiguration<T> configuration) {
		return new ConstructorDeclaredTest<>(configuration);
	}

}
