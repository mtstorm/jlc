package se.skillytaire.didactic.tools.jlc.method.internal;

import se.skillytaire.didactic.tools.jlc.api.Archetype;
import se.skillytaire.didactic.tools.jlc.api.ArchetypedCollection;
import se.skillytaire.didactic.tools.jlc.api.Enforcer;
import se.skillytaire.didactic.tools.jlc.api.JLCConfiguration;
import se.skillytaire.didactic.tools.jlc.method.spi.model.config.TestMethodConfiguration;
import se.skillytaire.didactic.tools.jlc.method.spi.model.config.TestMethodsConfiguration;

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
