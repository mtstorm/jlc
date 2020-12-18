package se.skillytaire.didactic.tools.jlc.spi;

import se.skillytaire.didactic.tools.jlc.api.TestObjectFactory;
import se.skillytaire.didactic.tools.jlc.api.TestObjectFactoryRegistry;
/**
 * 
 *
 */
public final class JavaLangTestObjectFactoryRegistry implements TestObjectFactoryRegistry{

	private static 	Class<? extends TestObjectFactory<?>>[] REGISTER=new Class[] {
			   se.skillytaire.didactic.tools.jlc.spi.internal.ObjectTestObjectFactory.class,
			   se.skillytaire.didactic.tools.jlc.spi.internal.BooleanTestObjectFactory.class,
			   se.skillytaire.didactic.tools.jlc.spi.internal.ByteTestObjectFactory.class,
			   se.skillytaire.didactic.tools.jlc.spi.internal.CharacterTestObjectFactory.class,
			   se.skillytaire.didactic.tools.jlc.spi.internal.DoubleTestObjectFactory.class,
			   se.skillytaire.didactic.tools.jlc.spi.internal.FloatTestObjectFactory.class,
			   se.skillytaire.didactic.tools.jlc.spi.internal.IntegerTestObjectFactory.class,
			   se.skillytaire.didactic.tools.jlc.spi.internal.LongTestObjectFactory.class,
			   se.skillytaire.didactic.tools.jlc.spi.internal.ShortTestObjectFactory.class,
			   se.skillytaire.didactic.tools.jlc.spi.internal.StringBuilderTestObjectFactory.class,
			   se.skillytaire.didactic.tools.jlc.spi.internal.StringTestObjectFactory.class			
	};
	
	@Override
	public Class<? extends TestObjectFactory<?>>[] getTestObjectFactories() {
		return REGISTER;
	}

}
