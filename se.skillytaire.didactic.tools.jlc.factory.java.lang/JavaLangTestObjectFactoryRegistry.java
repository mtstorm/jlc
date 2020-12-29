package se.skillytaire.didactic.tools.jlc.factory.java.lang;

import se.skillytaire.didactic.tools.jlc.api.TestObjectFactory;
import se.skillytaire.didactic.tools.jlc.api.TestObjectFactoryRegistry;

/**
 * 
 *
 */
public final class JavaLangTestObjectFactoryRegistry implements TestObjectFactoryRegistry{

	private static 	Class<? extends TestObjectFactory<?>>[] REGISTER=new Class[] {
			   se.skillytaire.didactic.tools.jlc.factory.java.lang.ObjectTestObjectFactory.class,
			   se.skillytaire.didactic.tools.jlc.factory.java.lang.BooleanTestObjectFactory.class,
			   se.skillytaire.didactic.tools.jlc.factory.java.lang.ByteTestObjectFactory.class,
			   se.skillytaire.didactic.tools.jlc.factory.java.lang.CharacterTestObjectFactory.class,
			   se.skillytaire.didactic.tools.jlc.factory.java.lang.DoubleTestObjectFactory.class,
			   se.skillytaire.didactic.tools.jlc.factory.java.lang.FloatTestObjectFactory.class,
			   se.skillytaire.didactic.tools.jlc.factory.java.lang.IntegerTestObjectFactory.class,
			   se.skillytaire.didactic.tools.jlc.factory.java.lang.LongTestObjectFactory.class,
			   se.skillytaire.didactic.tools.jlc.factory.java.lang.ShortTestObjectFactory.class,
			   se.skillytaire.didactic.tools.jlc.factory.java.lang.StringBuilderTestObjectFactory.class,
			   se.skillytaire.didactic.tools.jlc.factory.java.lang.StringTestObjectFactory.class			
	};
	
	@Override
	public Class<? extends TestObjectFactory<?>>[] getTestObjectFactories() {
		return REGISTER;
	}

}
