package se.skillytaire.didactic.tools.jlc.spi.ext.enforcer;

import java.util.ServiceLoader;
import java.util.ServiceLoader.Provider;

import se.skillytaire.didactic.tools.jlc.api.JLCConfiguration;
import se.skillytaire.didactic.tools.jlc.spi.TestConfiguration;
import se.skillytaire.didactic.tools.jlc.spi.e.JLCFeatureConfiguration;

/**
 * The multi part enforces allows you to hava a single test Configuration, that
 * has multiple parts that also represents other test configurations.
 * 
 * For instance a property contains multiple methods and a field, so you can
 * push that to an other archetype.
 * 
 * @author prolector
 *
 * @param <N>
 * @param <T>
 */
public interface MultiPartEnforcer<D extends JLCFeatureConfiguration, N extends TestConfiguration<D,N, T>, T> {
	void enfoce(JLCConfiguration<T> jlcConfiguration, N testConfiguration);
	boolean isFor(Class<?> type);
	

	public static <D extends JLCFeatureConfiguration,N extends TestConfiguration<D,N, T>, T> void enforce(JLCConfiguration<T> jlcConfiguration, N testConfiguration) {
		ServiceLoader.load(MultiPartEnforcer.class)
			         .stream()
			         .map(Provider::get)
			         .filter(f -> f.isFor(testConfiguration.getClass()))
			         .forEach(f-> f.enfoce(jlcConfiguration, testConfiguration));

	}
}
