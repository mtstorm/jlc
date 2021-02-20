package se.skillytaire.didactic.tools.jlc.property.internal;

import se.skillytaire.didactic.tools.jlc.api.JLCConfiguration;
import se.skillytaire.didactic.tools.jlc.property.api.TestProperties;
import se.skillytaire.didactic.tools.jlc.spi.e.AbstractAnnotatedConfigurationFactory;
import se.skillytaire.didactic.tools.jlc.spi.e.AnnotatedConfigurationFactory;

public class TestPropertiesConfigurationFactory<T> extends AbstractAnnotatedConfigurationFactory<T, TestPropertiesConfiguration, TestProperties>{

	@Override
	public TestPropertiesConfiguration create(JLCConfiguration<T> jlConfiguration) {
		TestPropertiesConfiguration config = new TestPropertiesConfiguration();
		return config;
	}

	@Override
	public TestPropertiesConfiguration create(JLCConfiguration<T> jlConfiguration, TestProperties annotation) {
		TestPropertiesConfiguration config = new TestPropertiesConfiguration(annotation);
		return config;
	}



}
