package se.skillytaire.didactic.tools.jlc.constructor.internal.spi;

import se.skillytaire.didactic.tools.jlc.api.JLCConfiguration;
import se.skillytaire.didactic.tools.jlc.constructor.api.TestConstructors;
import se.skillytaire.didactic.tools.jlc.spi.e.AbstractAnnotatedConfigurationFactory;

public class TestConstructorsConfigurationFactory<T> extends AbstractAnnotatedConfigurationFactory<T,TestConstructorsConfiguration, TestConstructors>{

	@Override
	public TestConstructorsConfiguration create(JLCConfiguration<T> jlConfiguration) {
		return new TestConstructorsConfiguration();
	}

	@Override
	public TestConstructorsConfiguration create(JLCConfiguration<T> jlConfiguration, TestConstructors annotation) {
		return new TestConstructorsConfiguration(annotation);
	}




}
