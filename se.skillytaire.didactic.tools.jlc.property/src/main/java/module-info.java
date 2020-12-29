module se.skillytaire.didactic.tools.jlc.property {
	   requires transitive se.skillytaire.didactic.tools.jlc.spi;
	   requires transitive org.junit.jupiter.api;
	requires java.logging;
	exports se.skillytaire.didactic.tools.jlc.property.api;
	provides se.skillytaire.didactic.tools.jlc.spi.ext.feature.FeatureTestNodeFactory with se.skillytaire.didactic.tools.jlc.property.internal.PropertyFeatureNodeFactory;
	provides se.skillytaire.didactic.tools.jlc.api.Enforcer with se.skillytaire.didactic.tools.jlc.property.internal.TestPropertyConfigurationEnforcer;
    
	
	uses se.skillytaire.didactic.tools.jlc.property.spi.OptionalPropertyValidator;
	provides se.skillytaire.didactic.tools.jlc.property.spi.OptionalPropertyValidator
	   with se.skillytaire.didactic.tools.jlc.property.internal.BasicOptionalPropertyValidator;
}