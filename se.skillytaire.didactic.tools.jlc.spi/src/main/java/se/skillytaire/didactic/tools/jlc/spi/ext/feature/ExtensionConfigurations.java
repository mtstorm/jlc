package se.skillytaire.didactic.tools.jlc.spi.ext.feature;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

import se.skillytaire.didactic.tools.jlc.api.Archetype;
import se.skillytaire.didactic.tools.jlc.api.JLCConfiguration;
import se.skillytaire.didactic.tools.jlc.spi.TestConfiguration;
import se.skillytaire.didactic.tools.jlc.spi.TestConfigurationRegistry;
import se.skillytaire.didactic.tools.jlc.spi.e.JLCFeatureConfiguration;
import se.skillytaire.didactic.tools.jlc.spi.ext.attribute.AttributeValue;

/**
 * This is the central registration point for all the extentions that have
 * multiple test configurations as a registry.
 * 
 * @author prolector
 *
 * @param <T>
 */
public final class ExtensionConfigurations<T> implements AttributeValue {
	public static final String ATTRIBUTE_NAME = ExtensionConfigurations.class.getName();
	private final Map<String, TestConfigurationRegistry<T, ? , ?>> extensionConfigurations = new HashMap<>();

	ExtensionConfigurations(){}
	/**
	 * initializes a test configuration registry for the given
	 * testConfigurationType.
	 */
	// @Override
	public <D extends JLCFeatureConfiguration, N extends TestConfiguration<D, N, T>> void init(
			Class<? extends JLCFeatureConfiguration> testConfigurationType) {
		if (isRegistered(testConfigurationType)) {
			// FIMXE change exception
			throw new IllegalArgumentException("testConfigurationType is already registered");
		}
		TestConfigurationRegistry<T, D, N> registry = new TestConfigurationRegistry<>();
		this.extensionConfigurations.put(testConfigurationType.getName(), registry);
	}

	// @Override
	private <D extends JLCFeatureConfiguration, N extends TestConfiguration<D, N, T>> boolean isRegistered(
			Class<? extends JLCFeatureConfiguration> testConfigurationType) {
		if (testConfigurationType == null) {
			throw new IllegalArgumentException("testConfigurationType is void");
		}
		return this.extensionConfigurations.containsKey(testConfigurationType.getName());
	}

	// @Override
	public <D extends JLCFeatureConfiguration, N extends TestConfiguration<D, N, T>> Optional<TestConfigurationRegistry<T, D, N>> find(
			Class<? extends JLCFeatureConfiguration> testConfigurationType) {
		if (testConfigurationType == null) {
			throw new IllegalArgumentException("testConfigurationType is void");
		}
		// FIXME remove this
		@SuppressWarnings("unchecked")
		TestConfigurationRegistry<T, D, N> registry = (TestConfigurationRegistry<T, D, N>) this.extensionConfigurations
				.get(testConfigurationType.getName());
		return Optional.ofNullable(registry);
	}

	/**
	 * Ensures the registry for the feature configuration.
	 * 
	 * @param <D>
	 * @param <N>
	 * @param type
	 * @return
	 */
	public <D extends JLCFeatureConfiguration, N extends TestConfiguration<D, N, T>> TestConfigurationRegistry<T, D, N> register(
			Class<? extends JLCFeatureConfiguration> type) {
		if (!isRegistered(type)) {
			init(type);
		}
	 TestConfigurationRegistry<T, D, N> r = (TestConfigurationRegistry<T, D, N>) this.find(type).get();
	 return r;
	}

	@Override
	public String getAttributeName() {
		return ATTRIBUTE_NAME;
	}
	/**
	 * Finds an existing testConfig based on the testConfig.
	 * @param <D>
	 * @param <N>
	 * @param testConfig
	 * @return an optional test configuration.
	 */
	public <D extends JLCFeatureConfiguration, N extends TestConfiguration<D, N, T>> Optional<N> find(N testConfig) {
		N result = null;
		D defaults =testConfig.getExtentionDefaults();
		Optional<TestConfigurationRegistry<T, D, N>> registryResult = find(defaults.getClass());
		if(registryResult.isPresent()) {
			TestConfigurationRegistry<T, D, N> registry = registryResult.get();
			Optional<N> configResult = registry.resolve(testConfig);
			if(configResult.isPresent()) {
				result = configResult.get();
			}
		}
		return Optional.ofNullable(result);
	}

//	public Stream<N> configurations(Class<N> testConfigType, Archetype filter) {
//		// TODO Auto-generated method stub
//		return null;
//	}
	public <D extends JLCFeatureConfiguration, N extends TestConfiguration<D, N, T>> Stream<N> configurations(Class<? extends JLCFeatureConfiguration> testConfigType, Archetype archetype) {
		Stream<N> result;
		Optional< TestConfigurationRegistry<T,D, N> >  registry = this.find(testConfigType);
		if(registry.isPresent()) {
			TestConfigurationRegistry<T,D, N> reg = registry.get();
			result = reg.archetypes(archetype);
		} else {
			result = empty();
		}
		return result;
	}
	public <D extends JLCFeatureConfiguration, N extends TestConfiguration<D, N, T>> Stream<N> configurations(Class<? extends JLCFeatureConfiguration> testConfigType) {
		Stream<N> result;
		Optional<TestConfigurationRegistry<T,D, N>> registry = this.find(testConfigType);
		if(registry.isPresent()) {
			TestConfigurationRegistry<T,D, N> reg = registry.get();
			result = reg.defaults();
		} else {
			result = empty();
		}
		return result;
	}

	private <D extends JLCFeatureConfiguration, N extends TestConfiguration<D, N, T>> Stream<N> empty() {
		return Stream.empty();
	}
	
	
	public static <T> ExtensionConfigurations<T> getInstance(JLCConfiguration<T> config){
		return config.getAttribute(ATTRIBUTE_NAME);
	}
	
	
	

	

}
