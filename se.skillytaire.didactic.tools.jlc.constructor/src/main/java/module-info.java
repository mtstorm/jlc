module se.skillytaire.didactic.tools.jlc.constructor {
   requires transitive se.skillytaire.didactic.tools.jlc.core;
   requires se.skillytaire.didactic.tools.jlc.signature;
   requires java.logging;
   requires transitive org.junit.jupiter.api;
   exports se.skillytaire.didactic.tools.jlc.constructor.api;
   exports se.skillytaire.didactic.tools.jlc.constructor.spi;
   exports se.skillytaire.didactic.tools.jlc.constructor.spi.model.config;
   exports se.skillytaire.didactic.tools.jlc.constructor.spi.model.structure;
   exports se.skillytaire.didactic.tools.jlc.constructor.spi.util;
   
   provides se.skillytaire.didactic.tools.jlc.spi.ext.enforcer.Enforcer 
      with se.skillytaire.didactic.tools.jlc.constructor.internal.ConstructorSignatureEnforcer;

   provides se.skillytaire.didactic.tools.jlc.spi.ext.feature.FeatureTestNodeFactory 
      with se.skillytaire.didactic.tools.jlc.constructor.internal.ConstructorsTestNodeFactory;
   
   
   uses se.skillytaire.didactic.tools.jlc.constructor.spi.ConstructorTestFactory;

   provides se.skillytaire.didactic.tools.jlc.constructor.spi.ConstructorTestFactory 
      with se.skillytaire.didactic.tools.jlc.constructor.internal.tests.ConstructorDeclaredTestFactory,
           se.skillytaire.didactic.tools.jlc.constructor.internal.tests.ConstructorSignatureTestFactory,
           se.skillytaire.didactic.tools.jlc.constructor.internal.tests.NoArgumentInvokeTestFactory;
}