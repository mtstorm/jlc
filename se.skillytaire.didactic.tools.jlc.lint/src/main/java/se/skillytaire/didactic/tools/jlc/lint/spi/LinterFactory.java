package se.skillytaire.didactic.tools.jlc.lint.spi;

import java.util.ArrayList;
import java.util.Optional;
import java.util.ServiceLoader;

import se.skillytaire.didactic.tools.jlc.api.Archetype;
import se.skillytaire.didactic.tools.jlc.lint.spi.model.config.TestLinterConfiguration;
import se.skillytaire.didactic.tools.jlc.spi.model.structure.JLCTestNode;
/**
 * The linter factory will create a linter for a given archetype.
 *
 * @param <T>
 */
public interface LinterFactory<T> {

	Linter<T> create();
	
	boolean matches(Archetype archetype);
	
	@SuppressWarnings("unchecked")
	static <T> Optional<Linter<T>> find(TestLinterConfiguration<T> config) {
		if (config == null) {
			throw new IllegalArgumentException("config is void");
		}

		   Linter<T> result = null;
			ClassLoader loader = Thread.currentThread().getContextClassLoader();
			@SuppressWarnings("rawtypes")
			ServiceLoader<LinterFactory> serviceLoader = ServiceLoader.load(LinterFactory.class,
					loader);

			ArrayList<JLCTestNode<T>> nodes = new ArrayList<>();
			for (LinterFactory<T> factory : serviceLoader) {
				if (factory.matches(config.getArchetype())) {
					result = factory.create();
					break;
				}
			}

	

		return Optional.ofNullable(result);

	}

	

	
	
}
