package se.skillytaire.didactic.tools.jlc.lint.spi;

import se.skillytaire.didactic.tools.jlc.lint.spi.model.config.TestLinterConfiguration;
import se.skillytaire.didactic.tools.jlc.spi.model.Archetype;

/**
 * Linting is normally the automated checking of source code for programmatic
 * and stylistic errors. In this case it is done after compiling and analyzing
 * the class files having tests. A linter is a lint tool for static class file
 * analyzer/tester.
 * 
 * Lint programming is a type of automated check implemented as a JUnit test
 * suite and therefore linting happens early during development.
 * 
 * @author prolector
 *
 * @param <T>
 */
public interface Linter<T> {
	Archetype getArchetype();
	/**
	 * Enforces the lint aspects.
	 * @param testConfiguration
	 */
	void enforce(TestLinterConfiguration<T> testConfiguration);
	

}
