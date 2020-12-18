package se.skillytaire.didactic.tools.jlc.core.sample;

import se.skillytaire.didactic.tools.jlc.api.TestObjectFactory;
import se.skillytaire.didactic.tools.jlc.api.TestObjectFactoryRegistry;

public class SampleTestObjectFactoryRegistry 
		implements TestObjectFactoryRegistry {

	@SuppressWarnings("unchecked")
	@Override
	public Class<? extends TestObjectFactory<?>>[] getTestObjectFactories() {
		return new Class[] { BeanTestObjectFactory.class };
	}
}
