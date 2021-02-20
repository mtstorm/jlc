package se.skillytaire.didactic.tools.jlc.signature.spi.model.config;

import java.lang.annotation.Annotation;

import se.skillytaire.didactic.tools.jlc.api.JLC;
import se.skillytaire.didactic.tools.jlc.api.TestGroup;
import se.skillytaire.didactic.tools.jlc.spi.e.AbstractAnnotatedConfiguration;

public class AbstractSignatureFeatureConfiguration<A extends Annotation> extends
AbstractAnnotatedConfiguration<A> {
	private int maxParameterCount;
	
	private boolean simpleName;

	public AbstractSignatureFeatureConfiguration() {
		super();
		
	}
	public AbstractSignatureFeatureConfiguration(A annotation) {
		super(annotation);
	}
	public int getMaxParameterCount() {
		return maxParameterCount;
	}
	public void setMaxParameterCount(int maxParameterCount) {
		this.maxParameterCount = maxParameterCount;
	}

	public boolean isSimpleName() {
		return simpleName;
	}
	public void setSimpleName(boolean simpleName) {
		this.simpleName = simpleName;
	}
	
	
}
