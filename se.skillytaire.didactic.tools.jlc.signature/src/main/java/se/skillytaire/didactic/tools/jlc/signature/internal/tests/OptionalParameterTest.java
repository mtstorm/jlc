package se.skillytaire.didactic.tools.jlc.signature.internal.tests;

import java.lang.reflect.Executable;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;

import se.skillytaire.didactic.tools.jlc.signature.spi.Signature;
import se.skillytaire.didactic.tools.jlc.signature.spi.model.config.TestSignatureConfiguration;
import se.skillytaire.didactic.tools.jlc.spi.model.naming.BasicDisplayName;
import se.skillytaire.didactic.tools.jlc.spi.model.naming.DisplayName;
//Object oo design principles
public class OptionalParameterTest<N extends TestSignatureConfiguration<N,T,S,E>,T, S extends Signature,E extends Executable> extends AbstractSignatureTestNode<N,T,S,E> {

	private final DisplayName dn = new BasicDisplayName("Optional Parameter");

	public OptionalParameterTest(N testConfiguration) {
		super(testConfiguration);
	
	}

	@Override
	public String getAssertMessage() {
      String msg = String.format("There are optional parameters in the message");
      return msg;
	}

	@Override
	public DisplayName getDisplayName() {
		return dn;
	}

	@Override
	public void execute() throws Throwable {
		boolean result = getTestConfiguration().getSignature().parameters().filter((t)->t == Optional.class).count() == 0 ;
		Assertions.assertTrue(result,getAssertMessage());
	}

}
