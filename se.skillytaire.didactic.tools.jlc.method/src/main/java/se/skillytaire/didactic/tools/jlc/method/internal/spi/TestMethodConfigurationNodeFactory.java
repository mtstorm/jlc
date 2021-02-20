package se.skillytaire.didactic.tools.jlc.method.internal.spi;

import se.skillytaire.didactic.tools.jlc.method.spi.model.config.TestMethodConfiguration;
import se.skillytaire.didactic.tools.jlc.method.spi.model.config.TestMethodConfigurationTestSPINode;
import se.skillytaire.didactic.tools.jlc.spi.ext.feature.AbstractTestConfigurationNodeFactory;
import se.skillytaire.didactic.tools.jlc.spi.ext.feature.TestConfigurationTestNode;

public class TestMethodConfigurationNodeFactory<T> extends AbstractTestConfigurationNodeFactory<TestMethodsConfiguration, TestMethodConfiguration<T>, T>{

	@Override
	public TestConfigurationTestNode<TestMethodsConfiguration,TestMethodConfiguration<T>, T> create(
			TestMethodConfiguration<T> testConfiguration) {
		return new TestMethodConfigurationTestSPINode<>(testConfiguration);
	}




}
