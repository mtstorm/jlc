package se.skillytaire.didactic.tools.jlc.spi.ext.enforcer;

import java.lang.reflect.ParameterizedType;

import se.skillytaire.didactic.tools.jlc.spi.TestConfiguration;
import se.skillytaire.didactic.tools.jlc.spi.e.JLCFeatureConfiguration;

public abstract class AbstractMultiPartEnforcer<D extends JLCFeatureConfiguration, N extends TestConfiguration<D,N, T>, T> 
implements MultiPartEnforcer<D,N,T> {
	private final Class<N> type;
	@SuppressWarnings("unchecked")
	public AbstractMultiPartEnforcer() {
		ParameterizedType f = (ParameterizedType) ((ParameterizedType) getClass().getGenericSuperclass())
				.getActualTypeArguments()[1];
		type = (Class<N>) f.getRawType();
	}

	@Override
	public final boolean isFor(Class<?> aType) {
		return this.type == aType;
	}

}
