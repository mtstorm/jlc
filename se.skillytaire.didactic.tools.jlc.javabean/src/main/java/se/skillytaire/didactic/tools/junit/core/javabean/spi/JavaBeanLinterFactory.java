package se.skillytaire.didactic.tools.junit.core.javabean.spi;

import se.skillytaire.didactic.tools.jlc.api.Archetype;
import se.skillytaire.didactic.tools.jlc.lint.spi.Linter;
import se.skillytaire.didactic.tools.jlc.lint.spi.LinterFactory;

public class JavaBeanLinterFactory<T> implements LinterFactory<T>{ 

	@Override
	public Linter<T> create() {
		return new JavaBeanLinter<T>();
	}

	@Override
	public boolean matches(Archetype archetype) {
		return JavaBeanLinter.ARCHETYPE.equals(archetype);
	}

}
