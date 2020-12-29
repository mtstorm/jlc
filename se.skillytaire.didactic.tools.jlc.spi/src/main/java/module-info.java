module se.skillytaire.didactic.tools.jlc.spi {
   /**
    * Minimal dependencies for the API and the SPI.
    */
   requires java.logging;
   requires transitive org.junit.jupiter.api;
   requires transitive se.skillytaire.didactic.tools.jlc.api;
   
   
   
   
   uses se.skillytaire.didactic.tools.jlc.spi.ext.feature.FeatureTestNodeFactory;
   uses se.skillytaire.didactic.tools.jlc.spi.ext.immutable.ImmutableObject;

   exports se.skillytaire.didactic.tools.jlc.spi;
   exports se.skillytaire.didactic.tools.jlc.spi.model.config;
   exports se.skillytaire.didactic.tools.jlc.spi.model.naming;
   exports se.skillytaire.didactic.tools.jlc.spi.model.structure;
   exports se.skillytaire.didactic.tools.jlc.spi.ext.field;
   exports se.skillytaire.didactic.tools.jlc.spi.ext.feature;
   exports se.skillytaire.didactic.tools.jlc.spi.ext.immutable;
   exports se.skillytaire.didactic.tools.jlc.spi.util;
   

   
   provides se.skillytaire.didactic.tools.jlc.api.JLCConfigurationFactory 
		with se.skillytaire.didactic.tools.jlc.spi.internal.DefaultConfigurationFactory;
   provides se.skillytaire.didactic.tools.jlc.spi.ext.feature.FeatureTestNodeFactory 
	   	with se.skillytaire.didactic.tools.jlc.spi.internal.TestFactoriesTestNodeFactory;
   provides se.skillytaire.didactic.tools.jlc.api.TestObjectFactory
		with se.skillytaire.didactic.tools.jlc.spi.BooleanTestObjectFactory,
	         se.skillytaire.didactic.tools.jlc.spi.ObjectTestObjectFactory;
   provides se.skillytaire.didactic.tools.jlc.spi.ext.immutable.ImmutableObject 
   		with se.skillytaire.didactic.tools.jlc.spi.ext.immutable.ImmutableObjectType;
}