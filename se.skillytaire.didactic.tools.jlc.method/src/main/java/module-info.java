module se.skillytaire.didactic.tools.jlc.method {
   requires transitive se.skillytaire.didactic.tools.jlc.spi;
   requires transitive org.junit.jupiter.api;
   requires transitive se.skillytaire.didactic.tools.jlc.signature;
   requires java.logging;
   requires se.skillytaire.didactic.tools.jlc.api;
	   
   exports se.skillytaire.didactic.tools.jlc.method.api;
   exports se.skillytaire.didactic.tools.jlc.method.spi;
   exports se.skillytaire.didactic.tools.jlc.method.spi.util;
   exports se.skillytaire.didactic.tools.jlc.method.spi.model.config;
   exports se.skillytaire.didactic.tools.jlc.method.spi.model.structure;
   exports se.skillytaire.didactic.tools.jlc.method.internal.spi;
          
//   provides se.skillytaire.didactic.tools.jlc.spi.ext.enforcer.Enforcer 
//   with se.skillytaire.didactic.tools.jlc.method.internal.MethodEnforcer;

   provides se.skillytaire.didactic.tools.jlc.spi.ext.feature.FeatureTestNodeFactory 
   with se.skillytaire.didactic.tools.jlc.method.internal.MethodsTestNodeFactory;
   
   uses se.skillytaire.didactic.tools.jlc.method.spi.MethodTestFactory;
   
   provides se.skillytaire.didactic.tools.jlc.method.spi.MethodTestFactory with 
   se.skillytaire.didactic.tools.jlc.method.internal.tests.signature.MethodSignatureTestFactory,
   se.skillytaire.didactic.tools.jlc.method.internal.tests.equals.EqualsReflectiveTestFactory,
   se.skillytaire.didactic.tools.jlc.method.internal.tests.equals.sym.EqualsSymetrixTestFactory,
   se.skillytaire.didactic.tools.jlc.method.internal.tests.equals.trans.EqualsTransiveTestFactory,
   se.skillytaire.didactic.tools.jlc.method.internal.tests.equals.EqualsConsistencyTestFactory,
   se.skillytaire.didactic.tools.jlc.method.internal.tests.equals.EqualsNullTestFactory,
   se.skillytaire.didactic.tools.jlc.method.internal.tests.equals.EqualsOtherTypeParameterTestFactory,
   se.skillytaire.didactic.tools.jlc.method.internal.tests.equals.sanity.EqualsSanityTestFactory,
   se.skillytaire.didactic.tools.jlc.method.internal.tests.hashcode.OverrideHashCodeTestFactory,
   se.skillytaire.didactic.tools.jlc.method.internal.tests.hashcode.HashCodeSanityTestFactory,
   se.skillytaire.didactic.tools.jlc.method.internal.tests.tostring.OverrideToStringTestFactory,
   se.skillytaire.didactic.tools.jlc.method.internal.tests.tostring.OverrideToStringContentTestFactory;

   provides se.skillytaire.didactic.tools.jlc.spi.ext.enforcer.TestConfigurationFactory
   	with se.skillytaire.didactic.tools.jlc.method.internal.spi.TestMethodConfigurationMethodFactory,
   	se.skillytaire.didactic.tools.jlc.method.internal.spi.TestMethodConfigurationMethodSignatureFactory,
   se.skillytaire.didactic.tools.jlc.method.internal.spi.PassTestConfigurationFactory,
   se.skillytaire.didactic.tools.jlc.method.internal.spi.TestMethodAnnotationTestConfigurationFactory,
   se.skillytaire.didactic.tools.jlc.method.internal.spi.TestMethodConfigurationTestConfigurationFactory;

   provides se.skillytaire.didactic.tools.jlc.spi.ext.feature.TestConfigurationNodeFactory
   		with se.skillytaire.didactic.tools.jlc.method.internal.spi.TestMethodConfigurationNodeFactory;

   provides se.skillytaire.didactic.tools.jlc.spi.e.AnnotatedConfigurationFactory
   		with se.skillytaire.didactic.tools.jlc.method.internal.spi.TestMethodSettingsFactory;
}