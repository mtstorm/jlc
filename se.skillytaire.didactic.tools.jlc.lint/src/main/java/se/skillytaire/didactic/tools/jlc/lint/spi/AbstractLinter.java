package se.skillytaire.didactic.tools.jlc.lint.spi;

import se.skillytaire.didactic.tools.jlc.lint.spi.model.config.TestLinterConfiguration;
import se.skillytaire.didactic.tools.jlc.signature.spi.ConstructorSignature;
import se.skillytaire.didactic.tools.jlc.spi.model.config.TestConfiguration;

public abstract class AbstractLinter<T> implements Linter<T> {
	private TestLinterConfiguration<T> testConfiguration;


	@Override
	public void enforce(TestLinterConfiguration<T> testConfiguration) {
		if(testConfiguration == null) {
			throw new IllegalArgumentException("Test configuration for linter is void");
		}
		this.testConfiguration = testConfiguration;
		enforce();
	}
	/**
	 * Enforce here.
	 */
	protected abstract void enforce();
	
	protected ConstructorSignature<T> create(Class<?>... paramTypes){
		Class<T> clazz = testConfiguration.getParent().getBeanUnderTestType();
		return new ConstructorSignature<T>(clazz, paramTypes);
	}
	
	protected TestConfiguration<?,T> enforce(ConstructorSignature<T> constructor){
		return testConfiguration.getParent().enfoce(constructor, getArchetype());
	}
}
