module se.skillytaire.didactic.tools.jlc.method {
   requires transitive se.skillytaire.didactic.tools.jlc.spi;
   requires transitive org.junit.jupiter.api;
   requires se.skillytaire.didactic.tools.jlc.signature;
   requires java.logging;
   requires se.skillytaire.didactic.tools.jlc.api;
	   
   exports se.skillytaire.didactic.tools.jlc.method.api;
   exports se.skillytaire.didactic.tools.jlc.method.spi;
   exports se.skillytaire.didactic.tools.jlc.method.spi.util;
   exports se.skillytaire.didactic.tools.jlc.method.spi.model.config;
   exports se.skillytaire.didactic.tools.jlc.method.spi.model.structure;

   provides se.skillytaire.didactic.tools.jlc.api.Enforcer 
   with se.skillytaire.didactic.tools.jlc.method.internal.MethodEnforcer;

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

}