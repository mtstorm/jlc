package se.skillytaire.didactic.tools.jlc.spi.ext.feature;

import java.util.Comparator;
import java.util.ServiceLoader;
import java.util.logging.Logger;
import java.util.stream.Stream;
import java.util.stream.Stream.Builder;

import se.skillytaire.didactic.tools.jlc.api.JLCConfiguration;

public interface FeatureTestNodeFactory<T> {
	   Logger log = Logger.getLogger(FeatureTestNodeFactory.class.getName());

	/**
	 * Return true when this feature is rerunnable.
	 * @return
	 */
	boolean isRerunnable();
	
	JLCFeatereTestNode<T> create();
	/**
	 * Is this feature enabled having the configuration. It defaults when JLC has a value.
	 * @param configuration
	 * @return
	 */
	default boolean isEnabledFor(JLCConfiguration<T> configuration) {
		
		return configuration.hasBeanUnderTestType();
	}

	public static <T> Stream<JLCFeatereTestNode<T>> structure(JLCConfiguration<T> configuration) {
		if(configuration == null) {
			throw new IllegalArgumentException("config is void");
		}
		Stream<FeatureTestNodeFactory<T>> stream = factories(configuration);
		return stream.map(f -> f.create());//.peek(f-> System.out.println(f.getClass()));
		
	}
	public static <T> Stream<FeatureTestNodeFactory<T>> factories(JLCConfiguration<T> configuration) {
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		// loader = String.class.getClassLoader();
		ServiceLoader<FeatureTestNodeFactory> serviceLoader = ServiceLoader.load(FeatureTestNodeFactory.class, loader);
		Builder<FeatureTestNodeFactory<T>> result = Stream.builder();
		for (FeatureTestNodeFactory<T> factory : serviceLoader) {
			log.config("Feature test factory "+ factory.getClass().getName());
			System.out.println("Featerure test factory "+ factory.getClass().getName());
			if(factory.isEnabledFor(configuration)) {
				result.add(factory);
				log.config("Feature test factory "+ factory.getClass().getName() + " is enabled");
			}
		}
		return result.build();
	}
//	public static <T> Stream<FeatureTestNodeFactory<T>> factories() {
//
//		return FeatureTestNodeFactory.factories( (e)-> true);//no filtering
//	}
	
	
	

	public static <T> Comparator<JLCFeatereTestNode<T>> getComperator() {
		Comparator<JLCFeatereTestNode<T>> comperator = (a,b) -> Integer.compare(b.getWeight(), a.getWeight());
		return comperator;
	}
}
