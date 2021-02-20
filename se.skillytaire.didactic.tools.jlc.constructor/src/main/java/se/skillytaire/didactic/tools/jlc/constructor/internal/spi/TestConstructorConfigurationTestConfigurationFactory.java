package se.skillytaire.didactic.tools.jlc.constructor.internal.spi;

import se.skillytaire.didactic.tools.jlc.api.JLCConfiguration;
import se.skillytaire.didactic.tools.jlc.constructor.spi.model.config.TestConstructorConfiguration;
import se.skillytaire.didactic.tools.jlc.spi.ext.enforcer.AbstractTestConfigurationFactory;

public class TestConstructorConfigurationTestConfigurationFactory <T> 
extends AbstractTestConfigurationFactory<TestConstructorsConfiguration, TestConstructorConfiguration<T>,T>{
	private static final Class<?> TYPE = TestConstructorConfiguration.class;

	@Override
	public TestConstructorConfiguration<T> create(JLCConfiguration<T> configuration,TestConstructorsConfiguration defaults, Object basedOn) {
		return (TestConstructorConfiguration<T>) basedOn;
	}

	@Override
	public Class<?> type() {
		return TYPE;
	}

}
