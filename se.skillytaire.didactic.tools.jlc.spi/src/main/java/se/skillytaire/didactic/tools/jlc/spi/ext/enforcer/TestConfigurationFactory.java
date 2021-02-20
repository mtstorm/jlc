package se.skillytaire.didactic.tools.jlc.spi.ext.enforcer;

import java.util.Arrays;
import java.util.Optional;
import java.util.ServiceLoader;
import java.util.ServiceLoader.Provider;
import java.util.stream.Stream;

import se.skillytaire.didactic.tools.jlc.api.ClassifiedFactory;
import se.skillytaire.didactic.tools.jlc.api.JLCConfiguration;
import se.skillytaire.didactic.tools.jlc.api.JLCConfigurationException;
import se.skillytaire.didactic.tools.jlc.spi.TestConfiguration;
import se.skillytaire.didactic.tools.jlc.spi.e.JLCFeatureConfiguration;
import se.skillytaire.didactic.tools.jlc.spi.e.JLCFeatureRegistry;

public interface TestConfigurationFactory<D extends JLCFeatureConfiguration, N extends TestConfiguration<D,N,T>,T> extends ClassifiedFactory{
	/**
	 * Creates the test configuration for a given reference.
	 * @param configuration The general configuration of JLC
	 * @param basedOn The reference that must have a test configuration.
	 * @return
	 */
	N create(JLCConfiguration<T> configuration, D defaults, Object basedOn);
	
//	boolean isFor(Class<?> aType);
	
	boolean defaults(Class<? extends JLCFeatureConfiguration> aType);
	/**
	 * Creates a new TestConfiguration
	 * @param <N>
	 * @param <T>
	 * @param configuration
	 * @param configurable
	 * @return
	 */
	public static <D extends JLCFeatureConfiguration,N extends TestConfiguration<D,N,T>,T> N createNew(JLCConfiguration<T> configuration,D defaults, Object configurable) {
		N testConfiguration;
		Optional<TestConfigurationFactory> possibleFactory= ServiceLoader.load(TestConfigurationFactory.class)
			         .stream()
			         .map(Provider::get)
			         .filter(f -> f.isFor(configurable.getClass()))
			         .findFirst();
		if(possibleFactory.isEmpty()) {
			String msg = String.format("There is no test configuration factory for the configurable type %s", configurable.getClass().getName());
			throw new JLCConfigurationException(msg);
		}
		
		testConfiguration = (N) possibleFactory.get().create(configuration,defaults, configurable);
		//te eenzijdig.
		JLCFeatureRegistry.getInstance(configuration).register(defaults);
		return testConfiguration;
	}

	public static Stream<Class<?>> configuratableTypes(JLCFeatureConfiguration defaults) {
		return ServiceLoader.load(TestConfigurationFactory.class)
		         .stream()
		         .map(Provider::get)
		         .filter( f -> f.defaults(defaults.getClass()))
		         .flatMap(f -> Arrays.stream(f.types()))
		         ;

	}

	
	//JLCFea
}
