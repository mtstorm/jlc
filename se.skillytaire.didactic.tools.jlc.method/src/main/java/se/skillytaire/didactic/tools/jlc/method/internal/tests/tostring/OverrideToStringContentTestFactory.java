package se.skillytaire.didactic.tools.jlc.method.internal.tests.tostring;

import se.skillytaire.didactic.tools.jlc.method.spi.model.config.TestMethodConfiguration;
import se.skillytaire.didactic.tools.jlc.spi.model.structure.JLCTestNode;

public class OverrideToStringContentTestFactory<T> extends AbstractToStringTestFactory<T>{



	@Override
	public JLCTestNode<T> create(TestMethodConfiguration<T> configuration) {
		return new OverrideToStringContentTest<>(configuration);
	}

}
