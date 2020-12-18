
module se.skillytaire.didactic.tools.jlc.signature {
   requires se.skillytaire.didactic.tools.jlc.core;
 //  requires se.skillytaire.didactic.tools.junit.core.spi.java.lang;
   requires org.junit.jupiter.api;

   
   exports se.skillytaire.didactic.tools.jlc.signature.spi;
   exports se.skillytaire.didactic.tools.jlc.signature.spi.model.config;
   exports se.skillytaire.didactic.tools.jlc.signature.spi.model.naming;
   
   uses se.skillytaire.didactic.tools.jlc.signature.spi.SignatureTestFactory;
   
   provides se.skillytaire.didactic.tools.jlc.signature.spi.SignatureTestFactory 
      with 
      	se.skillytaire.didactic.tools.jlc.signature.internal.tests.dbc.DBCSignatureTestFactory,
      	se.skillytaire.didactic.tools.jlc.signature.internal.tests.ExecutorDeclaredTestFactory,
      	se.skillytaire.didactic.tools.jlc.signature.internal.tests.FlagParameterTestFactory,
      	se.skillytaire.didactic.tools.jlc.signature.internal.tests.MethodParameterCountTestFactory,
      	se.skillytaire.didactic.tools.jlc.signature.internal.tests.OptionalParameterTestFactory;
}