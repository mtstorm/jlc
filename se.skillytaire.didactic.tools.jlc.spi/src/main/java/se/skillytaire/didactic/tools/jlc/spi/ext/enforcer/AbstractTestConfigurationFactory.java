package se.skillytaire.didactic.tools.jlc.spi.ext.enforcer;

import java.lang.reflect.ParameterizedType;

import se.skillytaire.didactic.tools.jlc.spi.TestConfiguration;
import se.skillytaire.didactic.tools.jlc.spi.e.JLCFeatureConfiguration;

public abstract class AbstractTestConfigurationFactory<D extends JLCFeatureConfiguration, N extends TestConfiguration<D,N,T>,T>  implements  TestConfigurationFactory<D,N,T>{

	private final Class<? extends JLCFeatureConfiguration> defaultType;
	protected AbstractTestConfigurationFactory() {
		ParameterizedType x = (ParameterizedType) getClass().getGenericSuperclass();
		defaultType =  (Class<? extends JLCFeatureConfiguration>) x.getActualTypeArguments()[0];
	}



	@Override
	public boolean defaults(Class<? extends JLCFeatureConfiguration> aType) {
		return defaultType == aType;
	}
	
}
