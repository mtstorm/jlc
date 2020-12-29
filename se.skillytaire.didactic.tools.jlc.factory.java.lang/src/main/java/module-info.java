module se.skillytaire.didactic.tools.jlc.factory.java.lang {
	requires se.skillytaire.didactic.tools.jlc.api;
	requires org.junit.jupiter.api;
	requires static se.skillytaire.didactic.tools.jlc.spi;
//	uses se.skillytaire.didactic.tools.jlc.api.TestObjectFactory;
//	uses se.skillytaire.didactic.tools.jlc.api.JLCConfigurationFactory;
	provides se.skillytaire.didactic.tools.jlc.api.TestObjectFactory
			with se.skillytaire.didactic.tools.jlc.factory.java.lang.ByteTestObjectFactory,
			se.skillytaire.didactic.tools.jlc.factory.java.lang.CharacterTestObjectFactory,
			se.skillytaire.didactic.tools.jlc.factory.java.lang.DoubleTestObjectFactory,
			se.skillytaire.didactic.tools.jlc.factory.java.lang.FloatTestObjectFactory,
			se.skillytaire.didactic.tools.jlc.factory.java.lang.IntegerTestObjectFactory,
			se.skillytaire.didactic.tools.jlc.factory.java.lang.LongTestObjectFactory,
			se.skillytaire.didactic.tools.jlc.factory.java.lang.ShortTestObjectFactory,
			se.skillytaire.didactic.tools.jlc.factory.java.lang.StringBuilderTestObjectFactory,
			se.skillytaire.didactic.tools.jlc.factory.java.lang.StringTestObjectFactory;
	
	exports se.skillytaire.didactic.tools.jlc.factory.java.lang to org.junit.platform.commons;
}