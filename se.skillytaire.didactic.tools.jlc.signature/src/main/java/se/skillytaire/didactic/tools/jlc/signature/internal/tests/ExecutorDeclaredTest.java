package se.skillytaire.didactic.tools.jlc.signature.internal.tests;



import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.reflect.Executable;

import se.skillytaire.didactic.tools.jlc.signature.spi.Signature;
import se.skillytaire.didactic.tools.jlc.signature.spi.model.config.TestSignatureConfiguration;
import se.skillytaire.didactic.tools.jlc.spi.e.JLCFeatureConfiguration;
import se.skillytaire.didactic.tools.jlc.spi.model.naming.BasicDisplayName;
import se.skillytaire.didactic.tools.jlc.spi.model.naming.DisplayName;

public class ExecutorDeclaredTest<N extends TestSignatureConfiguration<N,T,S,E,D>,T, S extends Signature,E extends Executable,D extends JLCFeatureConfiguration> extends AbstractSignatureTestNode<N,T,S,E,D> {

	ExecutorDeclaredTest(N testConfiguration) {
		super(testConfiguration);
	}

	@Override
	public String getAssertMessage() {
		return String.format("Signature %s is not declared", getTestConfiguration().getSignature());
	}

	@Override
	public DisplayName getDisplayName() {
		return new BasicDisplayName("Missing");
	}

	@Override
	public void execute() throws Throwable {
		assertTrue( getTestConfiguration().hasExecutor(),getAssertMessage());
	}

}
