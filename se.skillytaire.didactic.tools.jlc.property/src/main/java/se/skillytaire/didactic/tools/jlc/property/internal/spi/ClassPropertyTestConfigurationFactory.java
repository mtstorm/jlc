package se.skillytaire.didactic.tools.jlc.property.internal.spi;

import se.skillytaire.didactic.tools.jlc.api.JLCConfiguration;
import se.skillytaire.didactic.tools.jlc.property.internal.TestPropertiesConfiguration;
import se.skillytaire.didactic.tools.jlc.property.internal.TestPropertyConfiguration;
import se.skillytaire.didactic.tools.jlc.spi.ext.enforcer.AbstractTestConfigurationFactory;
import se.skillytaire.didactic.tools.jlc.spi.util.ClassProperty;

public class ClassPropertyTestConfigurationFactory<T> 
extends AbstractTestConfigurationFactory<TestPropertiesConfiguration, TestPropertyConfiguration<T>,T>{

private static final Class<?> TYPE = ClassProperty.class;

@Override
public TestPropertyConfiguration<T> create(JLCConfiguration<T> configuration,TestPropertiesConfiguration defaults, Object basedOn) {
	ClassProperty classProperty = (ClassProperty) basedOn;
	TestPropertyConfiguration<T> conf = new TestPropertyConfiguration<>(configuration, defaults, classProperty);
	return conf;
}

@Override
public Class<?> type() {
	return TYPE;
}
}
