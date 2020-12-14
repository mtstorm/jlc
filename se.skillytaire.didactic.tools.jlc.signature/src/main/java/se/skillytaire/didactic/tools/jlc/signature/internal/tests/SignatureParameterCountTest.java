package se.skillytaire.didactic.tools.jlc.signature.internal.tests;

import java.lang.reflect.Executable;

import org.junit.jupiter.api.Assertions;

import se.skillytaire.didactic.tools.jlc.signature.spi.Signature;
import se.skillytaire.didactic.tools.jlc.signature.spi.model.config.TestSignatureConfiguration;
import se.skillytaire.didactic.tools.jlc.spi.model.naming.BasicDisplayName;
import se.skillytaire.didactic.tools.jlc.spi.model.naming.DisplayName;

public class SignatureParameterCountTest<N extends TestSignatureConfiguration<N,T,S,E>,T, S extends Signature,E extends Executable> extends AbstractSignatureTestNode<N,T,S,E> {

	private final static DisplayName DN = new BasicDisplayName("Parameter count");

	public SignatureParameterCountTest(N testConfiguration) {
		super(testConfiguration);
	}

	@Override
	public String getAssertMessage() {
		String msg = String.format("There are %d parameters in the the signature '%s' expected an maximum parameter count of %d",
				this.getTestConfiguration().getSignature().getParameterCount(),
				this.getTestConfiguration().getSignature().toString(true),
				this.getTestConfiguration().getMaximalParameterCount());
		return msg;
	}

	@Override
	public DisplayName getDisplayName() {
		return DN;
	}

	@Override
	public void execute() throws Throwable {
		int actualParameterCount = this.getTestConfiguration().getSignature().getParameterCount();
		int expectedParameterCount = this.getTestConfiguration().getMaximalParameterCount();
		boolean succes = actualParameterCount <= expectedParameterCount;
		Assertions.assertTrue(succes, getAssertMessage());
	}

}
