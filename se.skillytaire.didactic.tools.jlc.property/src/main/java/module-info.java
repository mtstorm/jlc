module se.skillytaire.didactic.tools.jlc.property {
	   requires transitive se.skillytaire.didactic.tools.jlc.spi;
	   requires transitive org.junit.jupiter.api;
	requires java.logging;
	requires se.skillytaire.didactic.tools.jlc.signature;
	requires se.skillytaire.didactic.tools.jlc.method;
	exports se.skillytaire.didactic.tools.jlc.property.api;
	uses se.skillytaire.didactic.tools.jlc.property.spi.OptionalPropertyValidator;
	provides se.skillytaire.didactic.tools.jlc.spi.ext.feature.FeatureTestNodeFactory with se.skillytaire.didactic.tools.jlc.property.internal.PropertyFeatureNodeFactory;
//	provides se.skillytaire.didactic.tools.jlc.spi.ext.enforcer.Enforcer with se.skillytaire.didactic.tools.jlc.property.internal.TestPropertyConfigurationEnforcer;
    provides se.skillytaire.didactic.tools.jlc.spi.e.AnnotatedConfigurationFactory
    	with se.skillytaire.didactic.tools.jlc.property.internal.TestPropertiesConfigurationFactory;
	provides se.skillytaire.didactic.tools.jlc.spi.ext.enforcer.TestConfigurationFactory
		with se.skillytaire.didactic.tools.jlc.property.internal.spi.ClassPropertyTestConfigurationFactory;
	provides se.skillytaire.didactic.tools.jlc.spi.ext.enforcer.MultiPartEnforcer
		with se.skillytaire.didactic.tools.jlc.property.internal.TestPropertyMultiPartEnforcer;
	provides se.skillytaire.didactic.tools.jlc.property.spi.OptionalPropertyValidator
	   with se.skillytaire.didactic.tools.jlc.property.internal.BasicOptionalPropertyValidator;

	

}