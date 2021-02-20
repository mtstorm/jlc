module se.skillytaire.didactic.tools.jlc.api {
	requires transitive org.junit.jupiter.api;
	uses se.skillytaire.didactic.tools.jlc.api.JLCConfigurationFactory;
	uses se.skillytaire.didactic.tools.jlc.api.TestObjectFactory;
	
	provides org.junit.jupiter.api.extension.Extension with se.skillytaire.didactic.tools.jlc.api.JLCTestBuilder;
	exports se.skillytaire.didactic.tools.jlc.api;
	
	provides se.skillytaire.didactic.tools.jlc.api.TestObjectFactory with se.skillytaire.didactic.tools.jlc.api.VoidTestObjectFactory;
}