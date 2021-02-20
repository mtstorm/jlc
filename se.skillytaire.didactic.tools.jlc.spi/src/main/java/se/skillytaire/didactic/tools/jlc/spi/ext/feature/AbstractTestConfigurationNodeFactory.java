package se.skillytaire.didactic.tools.jlc.spi.ext.feature;

import java.lang.reflect.ParameterizedType;

import se.skillytaire.didactic.tools.jlc.spi.TestConfiguration;
import se.skillytaire.didactic.tools.jlc.spi.e.JLCFeatureConfiguration;

public abstract class AbstractTestConfigurationNodeFactory<D extends JLCFeatureConfiguration, N extends TestConfiguration<D,N, T>, T> 
   implements TestConfigurationNodeFactory<D,N,T>{
	//FIXME SPI interface
	private final Class<N> type;
	public AbstractTestConfigurationNodeFactory() {
		ParameterizedType f = (ParameterizedType) ((ParameterizedType) getClass().getGenericSuperclass())
				.getActualTypeArguments()[1];
		type = (Class<N>) f.getRawType();

	}
   @Override
   public Class<?> type() {
      return type;
   }




}
