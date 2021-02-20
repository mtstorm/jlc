
module se.skillytaire.didactic.tools.jlc.sample {
	requires transitive org.junit.jupiter.api;
	requires se.skillytaire.didactic.tools.jlc.api;
	requires se.skillytaire.didactic.tools.jlc.method;
	requires se.skillytaire.didactic.tools.jlc.constructor;
	requires se.skillytaire.didactic.tools.jlc.property;
	requires se.skillytaire.didactic.tools.jlc.lint;
   requires se.skillytaire.didactic.tools.junit.javabean;

//	uses org.junit.jupiter.api.extension.Extension;

//	
	
	//se.skillytaire.didactic.tools.jlc.spi
	// not possible in this configuration
	provides se.skillytaire.didactic.tools.jlc.api.TestObjectFactory with
			se.skillytaire.didactic.tools.jlc.sample.NameTestFactory;
	exports se.skillytaire.didactic.tools.jlc.sample
	     to org.junit.platform.commons,
	        se.skillytaire.didactic.tools.jlc.spi,
	        se.skillytaire.didactic.tools.jlc.constructor,
	        se.skillytaire.didactic.tools.jlc.method,
	        se.skillytaire.didactic.tools.jlc.property;
	
	opens se.skillytaire.didactic.tools.jlc.sample 
	     to org.junit.platform.commons,
	        se.skillytaire.didactic.tools.jlc.spi,
	        se.skillytaire.didactic.tools.jlc.constructor,
	        se.skillytaire.didactic.tools.jlc.property;
	
	//test//
	
}
//--add-modules se.skillytaire.didactic.tools.jlc.core
//--add-opens se.skillytaire.didactic.tools.jlc.core.sample=ALL-UNNAMED