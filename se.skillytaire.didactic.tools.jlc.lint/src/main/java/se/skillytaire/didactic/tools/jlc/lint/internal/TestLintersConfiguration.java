package se.skillytaire.didactic.tools.jlc.lint.internal;

import se.skillytaire.didactic.tools.jlc.api.JLC;
import se.skillytaire.didactic.tools.jlc.api.TestGroup;
import se.skillytaire.didactic.tools.jlc.constructor.api.TestConstructors;
import se.skillytaire.didactic.tools.jlc.lint.api.Lints;
import se.skillytaire.didactic.tools.jlc.signature.spi.model.config.AbstractSignatureFeatureConfiguration;
import se.skillytaire.didactic.tools.jlc.spi.e.AbstractAnnotatedConfiguration;

public class TestLintersConfiguration extends AbstractAnnotatedConfiguration<Lints>{
	


	public TestLintersConfiguration() {
		super();
		this.setMerge(JLC.MERGE);
	}

	public TestLintersConfiguration(Lints annotation) {
		super(annotation);
		this.setEnabled(annotation.enabled());
		this.setDisplayNameValue(annotation.displayName().value());
		this.setMerge(annotation.merge());		
	}

}
