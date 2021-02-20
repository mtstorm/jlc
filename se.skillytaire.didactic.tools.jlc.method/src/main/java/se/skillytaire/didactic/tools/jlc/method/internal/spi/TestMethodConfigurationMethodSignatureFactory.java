package se.skillytaire.didactic.tools.jlc.method.internal.spi;

import se.skillytaire.didactic.tools.jlc.api.JLCConfiguration;
import se.skillytaire.didactic.tools.jlc.method.spi.model.config.TestMethodConfiguration;
import se.skillytaire.didactic.tools.jlc.signature.spi.MethodSignature;
import se.skillytaire.didactic.tools.jlc.spi.ext.enforcer.AbstractTestConfigurationFactory;
import se.skillytaire.didactic.tools.jlc.spi.ext.enforcer.TestConfigurationFactory;

public class TestMethodConfigurationMethodSignatureFactory<T> 
extends AbstractTestConfigurationFactory<TestMethodsConfiguration, TestMethodConfiguration<T>,T>{

	private static final Class<?> TYPE = MethodSignature.class;
	@Override
	public TestMethodConfiguration<T> create(JLCConfiguration<T> configuration,TestMethodsConfiguration defaults, Object basedOn) {
		MethodSignature signature = (MethodSignature) basedOn;
		return new TestMethodConfiguration<>(configuration,defaults, signature);
	}

	@Override
	public Class<?> type() {
		return TYPE;
	}
}
