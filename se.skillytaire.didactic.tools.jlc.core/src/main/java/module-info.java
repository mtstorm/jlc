module se.skillytaire.didactic.tools.jlc.core {
   /**
    * Minimal dependencies for the API and the SPI.
    */
   requires java.logging;
   requires org.junit.jupiter.api;

   /*
    * For test users.
    */
   exports se.skillytaire.didactic.tools.jlc.api;
   /*
    * The rest is for developers who with to extend the implementations having
    * service providers.
    */
   exports se.skillytaire.didactic.tools.jlc.spi;
   exports se.skillytaire.didactic.tools.jlc.spi.model;
   exports se.skillytaire.didactic.tools.jlc.spi.model.config;
   exports se.skillytaire.didactic.tools.jlc.spi.model.naming;
   exports se.skillytaire.didactic.tools.jlc.spi.model.structure;

   exports se.skillytaire.didactic.tools.jlc.spi.ext.enforcer;
   /**
    * 
    * 
    */
   //@Pro
   uses se.skillytaire.didactic.tools.jlc.spi.ext.enforcer.Enforcer;
   
   exports se.skillytaire.didactic.tools.jlc.spi.ext.field;
   uses se.skillytaire.didactic.tools.jlc.spi.ext.field.FieldTypeValidator;
   
   exports se.skillytaire.didactic.tools.jlc.spi.ext.feature;
   uses se.skillytaire.didactic.tools.jlc.spi.ext.feature.FeatureTestNodeFactory;
   
   uses se.skillytaire.didactic.tools.jlc.api.TestObjectFactory;
   
   
   provides se.skillytaire.didactic.tools.jlc.api.TestObjectFactory with 
   
   se.skillytaire.didactic.tools.jlc.spi.internal.ObjectTestObjectFactory,
   se.skillytaire.didactic.tools.jlc.spi.internal.BooleanTestObjectFactory,
   se.skillytaire.didactic.tools.jlc.spi.internal.ByteTestObjectFactory,
   se.skillytaire.didactic.tools.jlc.spi.internal.CharacterTestObjectFactory,
   se.skillytaire.didactic.tools.jlc.spi.internal.DoubleTestObjectFactory,
   se.skillytaire.didactic.tools.jlc.spi.internal.FloatTestObjectFactory,
   se.skillytaire.didactic.tools.jlc.spi.internal.IntegerTestObjectFactory,
   se.skillytaire.didactic.tools.jlc.spi.internal.LongTestObjectFactory,
   se.skillytaire.didactic.tools.jlc.spi.internal.ShortTestObjectFactory,
   se.skillytaire.didactic.tools.jlc.spi.internal.StringBuilderTestObjectFactory,
   se.skillytaire.didactic.tools.jlc.spi.internal.StringTestObjectFactory;
   
   exports se.skillytaire.didactic.tools.jlc.spi.internal to  se.skillytaire.didactic.tools.jlc.signature;
}