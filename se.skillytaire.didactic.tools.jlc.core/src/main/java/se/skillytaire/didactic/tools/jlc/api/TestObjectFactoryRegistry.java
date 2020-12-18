package se.skillytaire.didactic.tools.jlc.api;

public interface TestObjectFactoryRegistry {
	Class<? extends TestObjectFactory<?>>[] getTestObjectFactories();
}
