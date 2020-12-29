package se.skillytaire.didactic.tools.jlc.signature.internal.tests.dbc;

import java.lang.reflect.Executable;

import se.skillytaire.didactic.tools.jlc.api.JLCConfiguration;
import se.skillytaire.didactic.tools.jlc.signature.spi.Signature;
import se.skillytaire.didactic.tools.jlc.signature.spi.model.config.TestSignatureConfiguration;
import se.skillytaire.didactic.tools.jlc.spi.model.naming.BasicDisplayName;
import se.skillytaire.didactic.tools.jlc.spi.model.naming.DisplayName;

public class DBCSignatureTest<N extends TestSignatureConfiguration<N,T,S,E>,T, S extends Signature,E extends Executable> extends AbstractTestSignatureConfigurationTestNode<N,T,S,E> {

	private static final DisplayName DN = new BasicDisplayName("Design By Contract");
	public DBCSignatureTest(N testConfiguration) {
		super(testConfiguration);
	}
	@Override
	public void init(JLCConfiguration<T> configuration) {
		super.init(configuration);
		
		ArrayPermutation.asStream(configuration,getTestConfiguration().getSignature())
			.map((e) -> new SignatureParameterNullsStepTest<N,T,S,E>(this.getTestConfiguration(), e))
			.peek(n->n.init(configuration))
			.forEach(this::add);
	}
	@Override
	public DisplayName getDisplayName() {
		return DN;
	}


}
