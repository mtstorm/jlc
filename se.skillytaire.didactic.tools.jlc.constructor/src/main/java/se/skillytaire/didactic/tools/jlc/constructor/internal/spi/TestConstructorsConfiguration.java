package se.skillytaire.didactic.tools.jlc.constructor.internal.spi;

import se.skillytaire.didactic.tools.jlc.api.JLC;
import se.skillytaire.didactic.tools.jlc.api.TestGroup;
import se.skillytaire.didactic.tools.jlc.constructor.api.TestConstructors;
import se.skillytaire.didactic.tools.jlc.signature.spi.model.config.AbstractSignatureFeatureConfiguration;

public class TestConstructorsConfiguration extends AbstractSignatureFeatureConfiguration<TestConstructors>{
	


	public TestConstructorsConfiguration() {
		super();
		
		this.setMaxParameterCount(TestConstructors.DEFAULT_PARAM_COUNT);
		this.setSimpleName(TestConstructors.SIMPLE_NAME);
		this.setMerge(JLC.MERGE);
	}

	public TestConstructorsConfiguration(TestConstructors annotation) {
		super(annotation);
		this.setEnabled(annotation.enabled());
		this.setMaxParameterCount(annotation.parameterCount());
		this.setSimpleName(annotation.simpleName());
		this.setGrouping(annotation.grouping());
		this.setMerge(annotation.merge());	
		
	}

}
