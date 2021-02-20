package se.skillytaire.didactic.tools.jlc.spi.ext.feature;

import java.util.Optional;

import se.skillytaire.didactic.tools.jlc.api.Archetype;
import se.skillytaire.didactic.tools.jlc.api.JLCConfiguration;
import se.skillytaire.didactic.tools.jlc.api.JLCConfigurationException;
import se.skillytaire.didactic.tools.jlc.spi.TestConfiguration;
import se.skillytaire.didactic.tools.jlc.spi.TestConfigurationRegistry;
import se.skillytaire.didactic.tools.jlc.spi.e.JLCFeatureConfiguration;
import se.skillytaire.didactic.tools.jlc.spi.e.JLCFeatureRegistry;
import se.skillytaire.didactic.tools.jlc.spi.ext.enforcer.MultiPartEnforcer;
import se.skillytaire.didactic.tools.jlc.spi.ext.enforcer.TestConfigurationFactory;

public final class Enforcer<T, D extends JLCFeatureConfiguration, N extends TestConfiguration<D, N, T>> {
	private final JLCConfiguration<T> configuration;
	private final D featureConfiguration;
	private final N testConfiguration;
	private final TestConfigurationRegistry<T, D, N> registry;
	



   public N getTestConfiguration() {
		return testConfiguration;
	}


	// FIXME builder!
	/**
	 * Creates an enforcer based on an already registered configuration.
	 * @param <T>
	 * @param <D>
	 * @param <N>
	 * @param jlc
	 * @param configurable
	 * @return
	 */
	  public static <T, D extends JLCFeatureConfiguration, N extends TestConfiguration<D, N, T>> Enforcer<T, D, N> of(
	         JLCConfiguration<T> jlc, Object configurable) {
	     D defaults = JLCFeatureRegistry.getInstance(jlc).get(configurable);
	     return of(jlc, defaults, configurable,true);
	  }
	  
	  /**
	   * 
	   * @param <T>
	   * @param <D>
	   * @param <N>
	   * @param jlc
	   * @param defaults
	   * @param configurable
	   * @param useExisting when set to true it will use the existing configuration, or otherwise it will fail.
	   * @return
	   */
	private static <T, D extends JLCFeatureConfiguration, N extends TestConfiguration<D, N, T>> Enforcer<T, D, N> of(
			JLCConfiguration<T> jlc, D defaults, Object configurable, boolean useExisting) {
		if (configurable == null) {
			throw new IllegalArgumentException("Configurable is void");
		}
		N testConfiguration = TestConfigurationFactory.createNew(jlc, defaults, configurable);
		if(useExisting) {
		   //welk type config hoort hierbij
		   ExtensionConfigurations ec = ExtensionConfigurations.getInstance(jlc);
		   Optional<N> existing = ec.find(testConfiguration);
		   if(existing.isPresent()) {
		      testConfiguration = existing.get();
		   } else {
		      throw new JLCConfigurationException("There is no existing configuration for type "+ configurable.getClass().getName());
		   }
		} 
		
		return new Enforcer<>(jlc, testConfiguration);
	}
	
	/**
	 * Create an enforcer based on a new configuration.
	 * @param <T>
	 * @param <D>
	 * @param <N>
	 * @param jlc
	 * @param defaults
	 * @param configurable
	 * @return
	 */
   public static <T, D extends JLCFeatureConfiguration, N extends TestConfiguration<D, N, T>> Enforcer<T, D, N> of(
         JLCConfiguration<T> jlc, D defaults, Object configurable) {
      return of(jlc, defaults, configurable, false);
   }
	private Enforcer(JLCConfiguration<T> jlc, N testConfiguration) {
		if (testConfiguration == null) {
			throw new IllegalArgumentException("test configuration is void");
		}

		this.configuration = jlc;
		ExtensionConfigurations<T> ec = ExtensionConfigurations.getInstance(jlc);
		this.testConfiguration = testConfiguration;
		this.featureConfiguration = testConfiguration.getExtentionDefaults();
		Class<D> type = (Class<D>) featureConfiguration.getClass();
		this.registry = ec.register(type);
	}

	public Optional<N> findTestConfiguration(N testConfig) {
		return registry.stream().filter(e -> testConfig.equals(e)).findFirst();

	}

	public N enforce() {
		N result;
		Optional<N> existing = findTestConfiguration(testConfiguration);
		if (existing.isPresent()) {
			result = existing.get();
		} else {
			registry.add(testConfiguration);
			result = testConfiguration;
		}

		enforceMultipart(result);
		return result;
	}

	private void enforceMultipart(N part) {
		MultiPartEnforcer.enforce(this.configuration, part);
	}
	/**
	 * Makes sure that the configuration is bound to the registry and is enforced to the archetype.
	 * @param archetype
	 * @return
	 */
	public N force(Archetype archetype) {
	     N result = enforce();
	     this.registry.enforce(result, archetype);
	     return result;
	}
	
	public N enforce(Archetype archetype) {
		N result;
		Optional<N> existing = findTestConfiguration(testConfiguration);
		if (existing.isPresent()) {
			result = existing.get();
			this.registry.enforce(result, archetype);
			enforceMultipart(result);

		} else {
			// there is no configuration found, is it enabled?

			throw new IllegalStateException(
					"When enforcing an test configuration for an archetype, it must be registered");
//				registry.add(testConfiguration, archetype);
//				result = testConfiguration;
		}

		return result;
	}

//	public Optional<N> merge(N aTestConfiguration) {
//		N merged = null;
//		Optional<N> match = findTestConfiguration(aTestConfiguration);
//		if (match.isPresent()) {
//			merged = match.get();
//			merged.merge(this.testConfiguration);
//		}
//		return Optional.ofNullable(merged);
//	}
	/**
	 * Merges the test configuration with something that has already been configured.
	 * @return 
	 * @return
	 */
	public Enforcer<T, D, N> merge() {
		N merged = null;
		Optional<N> match = findTestConfiguration(this.testConfiguration);
		if (match.isPresent()) {
			merged = match.get();
			this.testConfiguration.merge(merged);
		}
		return this;	
	}
	
	
	
	
	

}
