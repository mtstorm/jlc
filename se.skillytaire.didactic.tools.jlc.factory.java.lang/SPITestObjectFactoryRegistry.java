package se.skillytaire.didactic.tools.jlc.factory.java.lang;

import java.util.ServiceLoader;

import se.skillytaire.didactic.tools.jlc.api.TestObjectFactory;

public class SPITestObjectFactoryRegistry {
	private static TestObjectFactory<?>[] TEST_OBJECT_FACTORIES = ServiceLoader.load(TestObjectFactory.class).stream()
			.map(ServiceLoader.Provider::get).filter(TestObjectFactory.class::isInstance)
			.map(TestObjectFactory.class::cast).toArray(size -> new TestObjectFactory[size]);

	public TestObjectFactory<?>[] getTestObjectFactories() {
		return TEST_OBJECT_FACTORIES;
	}

}
