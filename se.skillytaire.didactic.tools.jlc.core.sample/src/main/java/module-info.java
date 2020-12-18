
module se.skillytaire.didactic.tools.jlc.core.sample {
	
//	//FIXME uses is niet aan dus geen SPI!!! GRRRjunit could not load service provider
	uses se.skillytaire.didactic.tools.jlc.api.TestObjectFactory;
	requires se.skillytaire.didactic.tools.jlc.core;
	
	
//	uses org.junit.jupiter.api.extension.Extension;

//	
	opens se.skillytaire.didactic.tools.jlc.core.sample 
		to org.junit.platform.commons,se.skillytaire.didactic.tools.jlc.core;
	
	
	// not possible in this configuration
	provides se.skillytaire.didactic.tools.jlc.api.TestObjectFactory with
	se.skillytaire.didactic.tools.jlc.core.sample.NameTestFactory;
	
	
	
	//test//
}
//--add-modules se.skillytaire.didactic.tools.jlc.core
//--add-opens se.skillytaire.didactic.tools.jlc.core.sample=ALL-UNNAMED