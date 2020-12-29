package se.skillytaire.didactic.tools.jlc.property.internal;

import se.skillytaire.didactic.tools.jlc.spi.ext.feature.AbstractFeatureTestNodeFactory;
import se.skillytaire.didactic.tools.jlc.spi.ext.feature.JLCFeatereTestNode;

public class PropertyFeatureNodeFactory<T> extends AbstractFeatureTestNodeFactory<T>{

	@Override
	public JLCFeatereTestNode<T> create() {
		return new TestPropertiesConfiguration<>();
	}

}
