package se.skillytaire.didactic.tools.jlc.constructor.internal;

import se.skillytaire.didactic.tools.jlc.constructor.spi.model.config.TestConstructorConfiguration;
import se.skillytaire.didactic.tools.jlc.constructor.spi.model.config.TestConstructorsConfiguration;
import se.skillytaire.didactic.tools.jlc.signature.spi.ConstructorSignature;
import se.skillytaire.didactic.tools.jlc.spi.ext.enforcer.Enforcer;
import se.skillytaire.didactic.tools.jlc.spi.model.Archetype;
import se.skillytaire.didactic.tools.jlc.spi.model.config.ArchetypedCollection;
import se.skillytaire.didactic.tools.jlc.spi.model.config.JLCConfiguration;

public class ConstructorSignatureEnforcer<T> implements Enforcer<TestConstructorConfiguration<T>,T>{
	private static final Class<?> TYPE = TestConstructorsConfiguration.class;
	@Override
	public TestConstructorConfiguration<T> enfoceConfiguration(JLCConfiguration<T> jlcConfiguration, Object configurable,
			Archetype type) {
		ArchetypedCollection<T, TestConstructorConfiguration<T>> rootConfig = jlcConfiguration.getExtensionConfiguration(TYPE);
		ConstructorSignature<T> signature = (ConstructorSignature<T>) configurable;
		TestConstructorConfiguration<T> config = new TestConstructorConfiguration<T>(jlcConfiguration, signature);
		rootConfig.enforce(config, type);
		System.out.println(rootConfig);
		return config;
	}

	@Override
	public boolean matches(Class<?> configurable) {
		return ConstructorSignature.class.isAssignableFrom(configurable);
	}

}
