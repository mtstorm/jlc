package se.skillytaire.didactic.tools.jlc.lint.spi;

import java.util.Optional;
import java.util.ServiceLoader;
import java.util.stream.Stream;
import java.util.stream.Stream.Builder;

import se.skillytaire.didactic.tools.jlc.api.Archetype;

public interface ArchetypeResolver<T> {
	Optional<Archetype> resolve(Class<?> testClass, Class<T> beanUnderTest);
	
	
	static <T> Stream<Archetype> find(Class<?> testClass, Class<T> beanUnderTest){
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		@SuppressWarnings("rawtypes")
		ServiceLoader<ArchetypeResolver> serviceLoader = ServiceLoader.load(ArchetypeResolver.class,
				loader);

		Builder<Archetype> result = Stream.builder();
		for (ArchetypeResolver<T> factory : serviceLoader) {
			Optional<Archetype> possibleMatch = factory.resolve(testClass, beanUnderTest);
			if(possibleMatch.isPresent()) {
				result.add(possibleMatch.get());
			}
		}
		return result.build();
	}
	
	
	
}
