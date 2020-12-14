package se.skillytaire.didactic.tools.jlc.spi.ext.feature;

import java.util.Comparator;
import java.util.ServiceLoader;
import java.util.function.Predicate;
import java.util.stream.Stream;
import java.util.stream.Stream.Builder;

import se.skillytaire.didactic.tools.jlc.spi.model.config.JLCConfiguration;

public interface FeatureTestNodeFactory<T> {
	/**
	 * Return true when this feature is rerunnable.
	 * @return
	 */
	boolean isRerunnable();
	
	JLCFeatereTestNode<T> create();


	public static <T> Stream<JLCFeatereTestNode<T>> structure(JLCConfiguration<T> configuration) {
		if(configuration == null) {
			throw new IllegalArgumentException("config is void");
		}
//		Builder<JLCFeatereTestNode> result = Stream.builder();
//		ClassLoader loader = Thread.currentThread().getContextClassLoader();
//		// loader = String.class.getClassLoader();
//		ServiceLoader<FeatureTestNodeFactory> serviceLoader = ServiceLoader.load(FeatureTestNodeFactory.class, loader);
//		for (FeatureTestNodeFactory factory : serviceLoader) {
//			//System.out.println("found factory "+ factory.getClass().getName());
//			result.add(factory.create(configuration));
//		}
//		//return result.build();
		Stream<FeatureTestNodeFactory<T>> stream = factories();
		return stream.map(f -> f.create());
		
	}
	public static <T> Stream<FeatureTestNodeFactory<T>> factories(Predicate<FeatureTestNodeFactory<T>> filter) {
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		// loader = String.class.getClassLoader();
		ServiceLoader<FeatureTestNodeFactory> serviceLoader = ServiceLoader.load(FeatureTestNodeFactory.class, loader);
		Builder<FeatureTestNodeFactory<T>> result = Stream.builder();
		for (FeatureTestNodeFactory<T> factory : serviceLoader) {
			//System.out.println("Featerure test factory "+ factory.getClass().getName());
			if(filter.test(factory)) {
				result.add(factory);
			}
		}
		return result.build();
	}
	public static <T> Stream<FeatureTestNodeFactory<T>> factories() {

		return FeatureTestNodeFactory.factories( (e)-> true);//no filtering
	}
	
	
	public static <T> Comparator<JLCFeatereTestNode<T>> getComperator() {
		Comparator<JLCFeatereTestNode<T>> comperator = (a,b) -> Integer.compare(b.getWeight(), a.getWeight());
		return comperator;
	}
}
