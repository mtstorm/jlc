package se.skillytaire.didactic.tools.jlc.lint.internal;

import se.skillytaire.didactic.tools.jlc.api.JLCConfiguration;
import se.skillytaire.didactic.tools.jlc.constructor.api.TestConstructors;
import se.skillytaire.didactic.tools.jlc.lint.api.Lints;
import se.skillytaire.didactic.tools.jlc.spi.e.AbstractAnnotatedConfigurationFactory;

public class TestLintsConfigurationFactory<T> extends AbstractAnnotatedConfigurationFactory<T,TestLintersConfiguration, Lints>{

	@Override
	public TestLintersConfiguration create(JLCConfiguration<T> jlConfiguration) {
		return new TestLintersConfiguration();
	}

	@Override
	public TestLintersConfiguration create(JLCConfiguration<T> jlConfiguration, Lints annotation) {
		return new TestLintersConfiguration(annotation);
	}




}
