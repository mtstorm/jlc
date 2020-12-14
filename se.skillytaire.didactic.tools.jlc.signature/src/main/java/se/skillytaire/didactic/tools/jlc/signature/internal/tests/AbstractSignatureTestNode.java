package se.skillytaire.didactic.tools.jlc.signature.internal.tests;

import java.lang.reflect.Executable;
import java.net.URI;
import java.util.Optional;

import se.skillytaire.didactic.tools.jlc.signature.spi.Signature;
import se.skillytaire.didactic.tools.jlc.signature.spi.model.config.TestSignatureConfiguration;
import se.skillytaire.didactic.tools.jlc.spi.model.structure.AbstractJLCTestNode;
import se.skillytaire.didactic.tools.jlc.spi.model.structure.JLCSingleTestNode;

public abstract class AbstractSignatureTestNode <N extends TestSignatureConfiguration<N,T,S,E>,T, S extends Signature,E extends Executable> extends AbstractJLCTestNode<T> implements JLCSingleTestNode<T> {
	private N testConfiguration;

	private String assertMessage;
	public  String getAssertMessage() { //fixme niet zo mooi zo
		return assertMessage;
	}
	protected final void setAssertMessage(String assertMessage) {
		this.assertMessage = assertMessage;
	}
	public AbstractSignatureTestNode(N config) {
		if(config == null) {
			throw new IllegalArgumentException("Test Configuration is void");
		}
		this.testConfiguration = config;
	}
	protected N getTestConfiguration() {
		return testConfiguration;
	}
	@Override
	public final Optional<URI> toUri() {
		return testConfiguration.toUri();
	}
}
