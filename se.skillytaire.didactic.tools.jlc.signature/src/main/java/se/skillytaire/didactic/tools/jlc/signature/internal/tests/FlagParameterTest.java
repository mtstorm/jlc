package se.skillytaire.didactic.tools.jlc.signature.internal.tests;

import java.lang.reflect.Executable;

import org.junit.jupiter.api.Assertions;

import se.skillytaire.didactic.tools.jlc.signature.spi.Signature;
import se.skillytaire.didactic.tools.jlc.signature.spi.model.config.TestSignatureConfiguration;
import se.skillytaire.didactic.tools.jlc.spi.BooleanTestObjectFactory;
import se.skillytaire.didactic.tools.jlc.spi.e.JLCFeatureConfiguration;
import se.skillytaire.didactic.tools.jlc.spi.model.naming.BasicDisplayName;
import se.skillytaire.didactic.tools.jlc.spi.model.naming.DisplayName;


//Object oo design principles
public class FlagParameterTest<N extends TestSignatureConfiguration<N,T,S,E,D>,T, S extends Signature,E extends Executable,D extends JLCFeatureConfiguration> extends AbstractSignatureTestNode<N,T,S,E,D> {

	private final DisplayName dn = new BasicDisplayName("Parameters flag");
	public FlagParameterTest(N config) {
		super(config);
	}

	@Override
	public String getAssertMessage() {
		String msg = String.format("There are flags in the message");
		return msg;
	}

	@Override
	public DisplayName getDisplayName() {
		return dn;
	}

	@Override
	public void execute() throws Throwable {
		S signature = getTestConfiguration().getSignature();
		int paramCount = signature.getParameterCount();
		boolean result = true;
		if(paramCount > 1) {
			result = getTestConfiguration().getSignature().parameters().filter(BooleanTestObjectFactory.BOOLEAN_TYPE).count() == 1;
		}
		Assertions.assertTrue(result, getAssertMessage());
	}



}
