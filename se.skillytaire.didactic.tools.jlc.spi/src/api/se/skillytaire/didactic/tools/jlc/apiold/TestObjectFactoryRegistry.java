package se.skillytaire.didactic.tools.jlc.apiold;

import se.skillytaire.didactic.tools.jlc.api.TestObjectFactory;

public interface TestObjectFactoryRegistry {
	Class<? extends TestObjectFactory<?>>[] getTestObjectFactories();
}
