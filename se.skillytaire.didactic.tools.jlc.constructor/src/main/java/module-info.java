module se.skillytaire.didactic.tools.jlc.constructor {
   requires transitive se.skillytaire.didactic.tools.jlc.spi;
   requires transitive org.junit.jupiter.api;
   requires se.skillytaire.didactic.tools.jlc.signature;
   requires java.logging;
   requires se.skillytaire.didactic.tools.jlc.api;
   exports se.skillytaire.didactic.tools.jlc.constructor.api;
   exports se.skillytaire.didactic.tools.jlc.constructor.spi;
   exports se.skillytaire.didactic.tools.jlc.constructor.spi.model.config;
   exports se.skillytaire.didactic.tools.jlc.constructor.spi.model.structure;
   exports se.skillytaire.didactic.tools.jlc.constructor.spi.util;
   exports se.skillytaire.didactic.tools.jlc.constructor.internal.spi;
   provides se.skillytaire.didactic.tools.jlc.spi.e.AnnotatedConfigurationFactory
   		with se.skillytaire.didactic.tools.jlc.constructor.internal.spi.TestConstructorsConfigurationFactory;
//   provides se.skillytaire.didactic.tools.jlc.spi.ext.enforcer.Enforcer 
//      with se.skillytaire.didactic.tools.jlc.constructor.internal.ConstructorSignatureEnforcer;

   provides se.skillytaire.didactic.tools.jlc.spi.ext.feature.FeatureTestNodeFactory 
      with se.skillytaire.didactic.tools.jlc.constructor.internal.ConstructorsTestNodeFactory;
   
   provides se.skillytaire.didactic.tools.jlc.spi.ext.enforcer.TestConfigurationFactory
   	with se.skillytaire.didactic.tools.jlc.constructor.internal.spi.TestConstructorTestConfigurationFactory,
   	se.skillytaire.didactic.tools.jlc.constructor.internal.spi.TestConstructorConfigurationTestConfigurationFactory,
   	se.skillytaire.didactic.tools.jlc.constructor.internal.spi.ConstructorSignatureTestConfigurationFactory;
   

   uses se.skillytaire.didactic.tools.jlc.constructor.spi.ConstructorTestFactory;

   provides se.skillytaire.didactic.tools.jlc.constructor.spi.ConstructorTestFactory 
      with se.skillytaire.didactic.tools.jlc.constructor.internal.tests.ConstructorDeclaredTestFactory,
           se.skillytaire.didactic.tools.jlc.constructor.internal.tests.ConstructorSignatureTestFactory,
           se.skillytaire.didactic.tools.jlc.constructor.internal.tests.NoArgumentInvokeTestFactory;
}