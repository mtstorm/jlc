package se.skillytaire.didactic.tools.jlc.method.spi.model.structure;

import java.net.URI;
import java.util.Optional;

import se.skillytaire.didactic.tools.jlc.method.spi.model.config.TestMethodConfiguration;
import se.skillytaire.didactic.tools.jlc.spi.model.structure.AbstractJLCTestNode;
import se.skillytaire.didactic.tools.jlc.spi.model.structure.JLCSingleTestNode;

/**
 * Prefab implementation for a single method test call.
 * 
 * @author prolector
 *
 * @param <T>
 */
public abstract class AbstractSingleMethodTestNode<T> extends AbstractJLCTestNode<T> implements JLCSingleTestNode<T> {
	

	private final TestMethodConfiguration<T> testConfiguration;

	public AbstractSingleMethodTestNode(TestMethodConfiguration<T> testConfiguration) {
		if (testConfiguration == null) {
			throw new AssertionError("test method configuration is void");
		}
		this.testConfiguration = testConfiguration;
	}

	@Override
	public final Optional<URI> toUri() {
		return this.testConfiguration.toUri();
	}



	protected final TestMethodConfiguration<T> getTestConfiguration() {
		return testConfiguration;
	}




	

}
