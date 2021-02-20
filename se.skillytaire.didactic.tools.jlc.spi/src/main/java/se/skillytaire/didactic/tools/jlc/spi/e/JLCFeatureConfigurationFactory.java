package se.skillytaire.didactic.tools.jlc.spi.e;

import se.skillytaire.didactic.tools.jlc.api.ClassifiedFactory;
import se.skillytaire.didactic.tools.jlc.api.JLCConfiguration;

public interface JLCFeatureConfigurationFactory<T, C extends JLCFeatureConfiguration> extends ClassifiedFactory{
	/**
	 * Creates the default configuration based on the jlc configuration only.
	 * @param jlConfiguration the configuration of JLC.
	 * @return
	 */
	C create(JLCConfiguration<T> jlConfiguration);
	
	
//	public static <C extends JLCFeatureConfiguration,N extends TestConfiguration<N, T>,T>  Optional<C> find(Class<?> testConfigurationType) {
//		if(testConfigurationType != null) {
//			throw new IllegalArgumentException("testConfiguration is void");
//		}
//		if(testConfigurationType.isAssignableFrom(TestConfiguration.class)) {
//			throw new IllegalArgumentException("type is not assignable from test configuration");
//		}
//
//		//lookup van N naar C
//		Optional<JLCFeatureConfigurationFactory> possibleFactory = ServiceLoader.load(JLCFeatureConfigurationFactory.class)
//				.stream().map(Provider::get).filter(f -> f.isFor(testConfigurationType)).findFirst();		
//		if(possibleFactory.isEmpty()) {
//			throw new IllegalStateException("There is no service implementation found for JLC feature configuration having the element type "+ type.getName());
//		}
//		//hier gebleven, geen method config.
//		//nieuwe config maken met relatie naar parent TestMethods
//		return null;
//	}
}
