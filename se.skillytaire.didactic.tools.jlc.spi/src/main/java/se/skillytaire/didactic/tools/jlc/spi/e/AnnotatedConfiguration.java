package se.skillytaire.didactic.tools.jlc.spi.e;

import java.lang.annotation.Annotation;

public interface AnnotatedConfiguration<A extends Annotation>  extends JLCFeatureConfiguration {
	
	/**
	 * Checks if the configuration is based on default values.
	 * @return true when the configuration is based on default values.
	 */
	boolean isDefault();

//	Optional<N> merge(N n);
	
}
