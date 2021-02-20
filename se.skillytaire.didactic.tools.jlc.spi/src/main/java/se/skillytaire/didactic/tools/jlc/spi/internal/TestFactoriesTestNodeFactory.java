package se.skillytaire.didactic.tools.jlc.spi.internal;

import se.skillytaire.didactic.tools.jlc.api.JLCConfiguration;
import se.skillytaire.didactic.tools.jlc.api.JLCConfigurationTest;
import se.skillytaire.didactic.tools.jlc.spi.ext.feature.AbstractFeatureTestNodeFactory;
import se.skillytaire.didactic.tools.jlc.spi.ext.feature.JLCFeatereTestNode;

/**
 * Adds the constructor feature to the core using spi of the core.
 * 
 * @author prolector
 *
 * @param <T>
 */
public class TestFactoriesTestNodeFactory<T> extends AbstractFeatureTestNodeFactory<T> {

	@Override
	public JLCFeatereTestNode<T> create() {
		return new TestFactoriesTestNode<>();
	}

	public boolean isEnabledFor(JLCConfiguration<T> configuration) {
		return configuration.getTestInstance().getClass().isAnnotationPresent(JLCConfigurationTest.class);
	}
}
