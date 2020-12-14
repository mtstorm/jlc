package se.skillytaire.didactic.tools.jlc.method.internal.tests.hashcode;

import se.skillytaire.didactic.tools.jlc.method.spi.model.config.TestMethodConfiguration;
import se.skillytaire.didactic.tools.jlc.spi.model.structure.JLCTestNode;

public class HashCodeSanityTestFactory<T> extends AbstractHashCodeTestFactory<T>{


	@Override
	public JLCTestNode<T> create(TestMethodConfiguration<T> configuration) {
		return new HashCodeSanityTest<>(configuration);
	}

}
