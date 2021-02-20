package se.skillytaire.didactic.tools.jlc.method.internal.spi;

import se.skillytaire.didactic.tools.jlc.api.JLCConfiguration;
import se.skillytaire.didactic.tools.jlc.method.api.TestMethods;
import se.skillytaire.didactic.tools.jlc.spi.e.AbstractAnnotatedConfigurationFactory;

public class TestMethodSettingsFactory<T> extends AbstractAnnotatedConfigurationFactory<T,TestMethodsConfiguration, TestMethods>{

	public TestMethodSettingsFactory() {
		super();
	}

	@Override
	public TestMethodsConfiguration create(JLCConfiguration<T> jlConfiguration) {
		return new TestMethodsConfiguration();
	}

	@Override
	public TestMethodsConfiguration create(JLCConfiguration<T> jlConfiguration, TestMethods annotation) {
		return new TestMethodsConfiguration(annotation);
	}


}
