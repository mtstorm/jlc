package se.skillytaire.didactic.tools.jlc.spi.ext.feature;

public abstract class AbstractFeatureTestNodeFactory<T> implements FeatureTestNodeFactory<T>{

	@Override
	public boolean isRerunnable() {
		return true;
	}



}
