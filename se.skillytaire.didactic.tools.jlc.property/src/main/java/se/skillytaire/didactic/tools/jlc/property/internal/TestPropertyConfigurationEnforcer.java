package se.skillytaire.didactic.tools.jlc.property.internal;

import se.skillytaire.didactic.tools.jlc.api.Archetype;
import se.skillytaire.didactic.tools.jlc.api.ArchetypedCollection;
import se.skillytaire.didactic.tools.jlc.api.Enforcer;
import se.skillytaire.didactic.tools.jlc.api.JLCConfiguration;
import se.skillytaire.didactic.tools.jlc.api.TestConfiguration;

public class TestPropertyConfigurationEnforcer<T> implements Enforcer<TestPropertyConfiguration<T>,T>{
	private static final Class<?> TYPE = TestPropertiesConfiguration.class;

	@Override
	public TestConfiguration<TestPropertyConfiguration<T>, T> enfoceConfiguration(JLCConfiguration<T> jlcConfiguration,
			Object configurable, Archetype type) {
		ArchetypedCollection<T, TestPropertyConfiguration<T>> rootConfig = jlcConfiguration.getExtensionConfiguration(TYPE);
		Property<T> property = (Property<T>) configurable;
		TestPropertyConfiguration<T> config = new TestPropertyConfiguration<T>(jlcConfiguration, property);
		rootConfig.enforce(config, type);
		return config;
	}

	@Override
	public boolean matches(Class<?> configurable) {
		return Property.class.isAssignableFrom(configurable);
	}

}
