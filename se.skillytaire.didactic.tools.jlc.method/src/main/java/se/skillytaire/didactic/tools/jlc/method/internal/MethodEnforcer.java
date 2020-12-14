package se.skillytaire.didactic.tools.jlc.method.internal;

import se.skillytaire.didactic.tools.jlc.method.spi.model.config.TestMethodConfiguration;
import se.skillytaire.didactic.tools.jlc.method.spi.model.config.TestMethodsConfiguration;
import se.skillytaire.didactic.tools.jlc.spi.ext.enforcer.Enforcer;
import se.skillytaire.didactic.tools.jlc.spi.model.Archetype;
import se.skillytaire.didactic.tools.jlc.spi.model.config.ArchetypedCollection;
import se.skillytaire.didactic.tools.jlc.spi.model.config.JLCConfiguration;

public class MethodEnforcer<T> implements Enforcer<TestMethodConfiguration<T>,T>{
	private static final Class<?> TYPE = TestMethodsConfiguration.class;
	@Override
	public TestMethodConfiguration<T> enfoceConfiguration(JLCConfiguration<T> jlcConfiguration, Object configurable,
			Archetype type) {
		ArchetypedCollection<T, TestMethodConfiguration<T>> rootConfig = jlcConfiguration.getExtensionConfiguration(TYPE);
		TestMethodConfiguration<T> config = (TestMethodConfiguration<T>) configurable;
		rootConfig.enforce(config, type);
		return config;
	}

	@Override
	public boolean matches(Class<?> configurable) {
		return TYPE == configurable;
	}

}
