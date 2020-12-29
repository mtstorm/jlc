module se.skillytaire.didactic.tools.jlc.spi.jcip.annotations {
	requires se.skillytaire.didactic.tools.jlc.api;
	requires se.skillytaire.didactic.tools.jlc.spi;
	requires jcip.annotations;
	
	   provides se.skillytaire.didactic.tools.jlc.spi.ext.immutable.ImmutableObject with
	   se.skillytaire.didactic.tools.jlc.jcip.annotations.JcipImmutableObject;

	   opens se.skillytaire.didactic.tools.jlc.jcip.annotations to  
	   	se.skillytaire.didactic.tools.jlc.spi,
	   	org.junit.platform.commons;

}