package se.skillytaire.didactic.tools.jlc.constructor.internal.spi;

import java.lang.reflect.Constructor;

import se.skillytaire.didactic.tools.jlc.api.JLCConfiguration;
import se.skillytaire.didactic.tools.jlc.constructor.spi.model.config.TestConstructorConfiguration;
import se.skillytaire.didactic.tools.jlc.spi.ext.enforcer.AbstractTestConfigurationFactory;
import se.skillytaire.didactic.tools.jlc.spi.ext.enforcer.TestConfigurationFactory;
/**
 * Registers for the Constructor
 * @author prolector
 *
 * @param <T>
 */
public class TestConstructorTestConfigurationFactory <T> 
extends AbstractTestConfigurationFactory<TestConstructorsConfiguration, TestConstructorConfiguration<T>,T>{
	private static final Class<?> TYPE = Constructor.class;

	@Override
	public TestConstructorConfiguration<T> create(JLCConfiguration<T> configuration,TestConstructorsConfiguration defaults, Object basedOn) {
		Constructor<T> c = (Constructor<T>) basedOn;
		TestConstructorConfiguration<T> result = new TestConstructorConfiguration<>(configuration, defaults, c);
		return result;
	}

	@Override
	public Class<?> type() {
		return TYPE;
	}

}
