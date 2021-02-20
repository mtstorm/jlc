package se.skillytaire.didactic.tools.jlc.method.internal;

import se.skillytaire.didactic.tools.jlc.method.spi.model.config.TestMethodsFeatereTestNode;
import se.skillytaire.didactic.tools.jlc.spi.ext.feature.AbstractFeatureTestNodeFactory;
import se.skillytaire.didactic.tools.jlc.spi.ext.feature.JLCFeatereTestNode;

public class MethodsTestNodeFactory<T> extends AbstractFeatureTestNodeFactory<T>{

	@Override
	public JLCFeatereTestNode<T> create() {
		return new TestMethodsFeatereTestNode<>();
	}

}
