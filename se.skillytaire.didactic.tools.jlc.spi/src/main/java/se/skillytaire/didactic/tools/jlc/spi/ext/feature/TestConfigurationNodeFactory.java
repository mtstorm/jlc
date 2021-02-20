package se.skillytaire.didactic.tools.jlc.spi.ext.feature;

import java.util.Optional;
import java.util.ServiceLoader;
import java.util.ServiceLoader.Provider;

import se.skillytaire.didactic.tools.jlc.api.ClassifiedFactory;
import se.skillytaire.didactic.tools.jlc.api.JLCConfiguration;
import se.skillytaire.didactic.tools.jlc.spi.TestConfiguration;
import se.skillytaire.didactic.tools.jlc.spi.e.JLCFeatureConfiguration;
import se.skillytaire.didactic.tools.jlc.spi.e.JLCFeatureRegistry;
import se.skillytaire.didactic.tools.jlc.spi.ext.enforcer.TestConfigurationFactory;

/**
 * Creates a TestConfigurationTestNode based on a single test configuration.
 *
 * @param <D>
 * @param <N>
 * @param <T>
 */
public interface TestConfigurationNodeFactory<D extends JLCFeatureConfiguration, N extends TestConfiguration<D, N, T>, T>
		extends ClassifiedFactory {

	TestConfigurationTestNode<D, N, T> create(N testConfiguration);

	/**
	 * Creates the service provider interface node for a given configuration, that
	 * should be loaded in the system.
	 * 
	 * For configuratables you can think of differed kind of things representing
	 * the same test configuration. For instance Method, MethodSignate of
	 * TestMethodConfiguration represents all the same TestMethodConfiguration.
	 * 
	 * @param <N>
	 * @param <T>
	 * @param configuration The JLC configuration
	 * @param configurable  A reference that represents a test configuration.
	 * @return
	 */
	static <
		D extends JLCFeatureConfiguration, 
		N extends TestConfiguration<D, N, T>, 
		T
		> TestConfigurationTestNode<D, N, T> createNew(
			JLCConfiguration<T> configuration, 
			D defaults, 
			Object configurable, 
			boolean useExisting) {
		//defaults....
	//	N possibleTestConfig = TestConfigurationFactory.createNew(configuration, defaults, configurable);
//		if (possibleTestConfig.isEmpty()) {
//			throw new IllegalArgumentException(
//					"Unable to create a test configuration for type " + configurable.getClass().getName());
//		}
		N newTestConfiguration = TestConfigurationFactory.createNew(configuration, defaults, configurable); //possibleTestConfig.get();
		Optional<TestConfigurationNodeFactory> possibleFactory = ServiceLoader.load(TestConfigurationNodeFactory.class)
				.stream().map(Provider::get).filter(f -> f.isFor(newTestConfiguration.getClass())).findFirst();
		if (possibleFactory.isEmpty()) {
			throw new IllegalArgumentException(
					"There is no TestConfigurationNodeFactory resolved for configuration type  "
							+ newTestConfiguration.getClass().getName());
		}
		ExtensionConfigurations<T> ec = ExtensionConfigurations.getInstance(configuration);
		Optional<N> possibleExisting = ec.find(newTestConfiguration);
		N existing;
		if (possibleExisting.isEmpty()) {
			if (useExisting) {
				String msg = String.format("There is no existing configuration found for %s", newTestConfiguration);
				throw new IllegalArgumentException(msg);
			} else {
				existing = newTestConfiguration;
			}
		} else {
			existing = possibleExisting.get();
		}

		TestConfigurationNodeFactory<D, N, T> factory = possibleFactory.get();
		return factory.create(existing);

	}
//	public <N extends TestConfiguration<N, T>> Optional<N> findTestConfiguration(N testConfig) {
//	Optional<TestConfigurationRegistry<T, N>> registry = this.find(testConfig.getClass());
//	if(!registry.isPresent()) {
//		throw new IllegalStateException("there is no test config registry");
//	}
//	TestConfigurationRegistry<T, N> reg = registry.get();
//	return reg.stream().filter( e -> testConfig.equals(e)).findFirst();
//
//}

   static <
   N extends TestConfiguration<JLCFeatureConfiguration, N, T>, 
   T
   > TestConfigurationTestNode<JLCFeatureConfiguration, N, T> create(JLCConfiguration<T> configuration, Object configuratable) {
      JLCFeatureRegistry registry = JLCFeatureRegistry.getInstance(configuration);
      JLCFeatureConfiguration featureConfiguration =  registry.get(configuratable);
      
      return TestConfigurationNodeFactory.createNew(configuration, featureConfiguration, configuratable, true);
   }
}
