package se.skillytaire.didactic.tools.jlc.constructor.internal.tests;

import java.net.URI;
import java.util.Optional;

import se.skillytaire.didactic.tools.jlc.constructor.spi.model.config.TestConstructorConfiguration;
import se.skillytaire.didactic.tools.jlc.spi.model.structure.AbstractJLCTestNode;
import se.skillytaire.didactic.tools.jlc.spi.model.structure.JLCSingleTestNode;

/**
 * Prefab implementation for a single method test call.
 * 
 * @author prolector
 *
 * @param <T>
 */
public abstract class AbstractSingleConstructorTestNode<T> extends AbstractJLCTestNode<T> implements JLCSingleTestNode<T> {
	

	private final TestConstructorConfiguration<T> testConfiguration;

	public AbstractSingleConstructorTestNode(TestConstructorConfiguration<T> testConfiguration) {
		if (testConfiguration == null) {
			throw new AssertionError("test constructor configuration is void");
		}
		this.testConfiguration = testConfiguration;
	}

	@Override
	public final Optional<URI> toUri() {
		return this.testConfiguration.toUri();
	}



	protected final TestConstructorConfiguration<T> getTestConfiguration() {
		return testConfiguration;
	}




	

}
