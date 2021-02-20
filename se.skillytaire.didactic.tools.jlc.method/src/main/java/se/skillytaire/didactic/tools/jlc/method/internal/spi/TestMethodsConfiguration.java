package se.skillytaire.didactic.tools.jlc.method.internal.spi;

import se.skillytaire.didactic.tools.jlc.api.JLC;
import se.skillytaire.didactic.tools.jlc.method.api.TestMethods;
import se.skillytaire.didactic.tools.jlc.signature.spi.model.config.AbstractSignatureFeatureConfiguration;

public class TestMethodsConfiguration extends AbstractSignatureFeatureConfiguration<TestMethods>{
	private String excludePatterns;


	public TestMethodsConfiguration() {
		this.setMaxParameterCount(TestMethods.DEFAULT_PARAM_COUNT);
		this.setSimpleName(TestMethods.SIMPLE_NAME);
		this.setExcludePatterns(TestMethods.DEFAULT_EXCLUDE_PATTERN);
		this.setMerge(JLC.MERGE);
	}

	public TestMethodsConfiguration(TestMethods annotation) {
		super(annotation);
		setMaxParameterCount(annotation.parameterCount());
		setSimpleName(annotation.simpleName());
		setGrouping(annotation.grouping());
		excludePatterns = annotation.excludePattern();
		if(excludePatterns.trim().length() == 0) {
			throw new IllegalStateException("exclude pattern is empty");
		}
		setMerge(annotation.merge());	
		this.setEnabled(annotation.enabled());
	}

	public String getExcludePatterns() {
		return excludePatterns;
	}

	public void setExcludePatterns(String excludePatterns) {
		this.excludePatterns = excludePatterns;
	}



}
