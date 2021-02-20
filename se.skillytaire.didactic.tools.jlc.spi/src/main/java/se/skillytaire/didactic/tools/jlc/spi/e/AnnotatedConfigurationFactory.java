package se.skillytaire.didactic.tools.jlc.spi.e;

import java.lang.annotation.Annotation;
import java.util.Optional;
import java.util.ServiceLoader;
import java.util.ServiceLoader.Provider;

import se.skillytaire.didactic.tools.jlc.api.JLCConfiguration;
import se.skillytaire.didactic.tools.jlc.api.JLCConfigurationException;

public interface AnnotatedConfigurationFactory<T, C extends AnnotatedConfiguration<A>, A extends Annotation> 
	extends JLCFeatureConfigurationFactory<T,C> {

	/**
	 * Creates the default configuration based on the jlc configuration and a given annotation.
	 * @param jlConfiguration the configuration of JLC.
	 * @param annotation the annotation that should be used in the configuration.
	 * @return
	 */
	C create(JLCConfiguration<T> jlConfiguration, A annotation);

	
	private static <T,C extends AnnotatedConfiguration<A>, A extends Annotation> AnnotatedConfigurationFactory<T,C,A> find(Class<?> type){
		if(!Annotation.class.isAssignableFrom(type)) {
			throw new IllegalArgumentException("The type to lookup the annotation configuration factory is not a type of annotation "+ type.getName());
		}
		Optional<AnnotatedConfigurationFactory> possibleFactory = ServiceLoader.load(AnnotatedConfigurationFactory.class)
				.stream().map(Provider::get).filter(f -> f.isFor(type)).findFirst();
		if(possibleFactory.isEmpty()) {
		   String all = ServiceLoader.load(AnnotatedConfigurationFactory.class).stream().map(Provider::get).map(e -> "[ "+ e.getClass().getName() +" -> "+ e.type().getName()+"]").reduce(", ", String::concat);
		   String msg = String.format("There is no annotated configuration factory found for annotation type %s.  %s", type.getName(), all);
			throw new JLCConfigurationException( msg);
		}
		return possibleFactory.get();
	}
	
	public static <T,C extends AnnotatedConfiguration<A>, A extends Annotation> C resolve(JLCConfiguration<T> jlConfiguration, Class<A> type) {
		if(jlConfiguration == null) {
			throw new IllegalArgumentException("jlConfiguration is void");
		}
		AnnotatedConfigurationFactory<T,C,A> factory = AnnotatedConfigurationFactory.find(type);
		return factory.create(jlConfiguration);
	}
	
	public static <T,C extends AnnotatedConfiguration<A>, A extends Annotation> C resolve(JLCConfiguration<T> jlConfiguration,A annotation) {
		if(jlConfiguration == null) {
			throw new IllegalArgumentException("jlConfiguration is void");
		}
		AnnotatedConfigurationFactory<T,C,A> factory = AnnotatedConfigurationFactory.find(annotation.annotationType());
		return factory.create(jlConfiguration,annotation);
	}	
	
	

}
