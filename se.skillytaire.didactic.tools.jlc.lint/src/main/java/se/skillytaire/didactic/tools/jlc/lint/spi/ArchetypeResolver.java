package se.skillytaire.didactic.tools.jlc.lint.spi;

import java.util.Optional;
import java.util.ServiceLoader;
import java.util.stream.Stream;
import java.util.stream.Stream.Builder;

import se.skillytaire.didactic.tools.jlc.api.Archetype;
import se.skillytaire.didactic.tools.jlc.api.JLCConfiguration;

public interface ArchetypeResolver<T> {
	Optional<Archetype> resolve(Class<?> testClass, Class<T> beanUnderTest);
	

	static <T> Stream<Archetype> find(JLCConfiguration<T> jlc){
	   Class<?> testClass = jlc.getTestInstance().getClass();
	   Class<T> beanUnderTest = jlc.getBeanUnderTestType();
	   return find(testClass, beanUnderTest);
	}
	
	private static <T> Stream<Archetype> find(Class<?> testClass, Class<T> beanUnderTest){
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		@SuppressWarnings("rawtypes")
		ServiceLoader<ArchetypeResolver> serviceLoader = ServiceLoader.load(ArchetypeResolver.class,
				loader);

		Builder<Archetype> result = Stream.builder();
		for (ArchetypeResolver<T> factory : serviceLoader) {
		   System.out.println("factory "+ factory.getClass().getName());
			Optional<Archetype> possibleMatch = factory.resolve(testClass, beanUnderTest);
			if(possibleMatch.isPresent()) {
				result.add(possibleMatch.get());
			}
		}
		return result.build();
	}
	
	
	
}
