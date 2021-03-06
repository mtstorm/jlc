package se.skillytaire.didactic.tools.jlc.method.internal.spi;

import se.skillytaire.didactic.tools.jlc.api.JLCConfiguration;
import se.skillytaire.didactic.tools.jlc.method.api.TestMethod;
import se.skillytaire.didactic.tools.jlc.method.spi.model.config.TestMethodConfiguration;
import se.skillytaire.didactic.tools.jlc.signature.spi.MethodSignature;
import se.skillytaire.didactic.tools.jlc.spi.ext.enforcer.AbstractTestConfigurationFactory;
import se.skillytaire.didactic.tools.jlc.spi.ext.enforcer.TestConfigurationFactory;

public class TestMethodAnnotationTestConfigurationFactory <T> 
extends AbstractTestConfigurationFactory<TestMethodsConfiguration, TestMethodConfiguration<T>,T>{
	private static final Class<?> TYPE = TestMethod.class;
	
	   public static MethodSignature of(TestMethod testMethod) {
		   return new MethodSignature(null, testMethod.returnType(), testMethod.name(), testMethod.api(), testMethod.parameters());
	   }

	@Override
	public TestMethodConfiguration<T> create(JLCConfiguration<T> configuration,TestMethodsConfiguration defaults, Object basedOn) {
		TestMethod method = (TestMethod) basedOn;
		MethodSignature signature = TestMethodAnnotationTestConfigurationFactory.of(method);
		return new TestMethodConfiguration<>(configuration,defaults, signature);
	}

	@Override
	public Class<?> type() {
		return TYPE;
	}

}
