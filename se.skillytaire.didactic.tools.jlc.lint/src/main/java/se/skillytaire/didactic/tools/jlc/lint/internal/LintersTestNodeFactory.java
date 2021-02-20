package se.skillytaire.didactic.tools.jlc.lint.internal;

import se.skillytaire.didactic.tools.jlc.spi.ext.feature.AbstractFeatureTestNodeFactory;
import se.skillytaire.didactic.tools.jlc.spi.ext.feature.JLCFeatereTestNode;
/**
 * Registers the linter to the core.
 *
 * @param <T>
 */
public class LintersTestNodeFactory<T> extends AbstractFeatureTestNodeFactory<T>{

	@Override
	public JLCFeatereTestNode<T> create() {
		return new TestLintersFeatereTestNode<>();
	}

	@Override
	public boolean isRerunnable() {
		return false;
	}

}
