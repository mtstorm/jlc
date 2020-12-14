package se.skillytaire.didactic.tools.jlc.signature.internal.tests.dbc;

import java.lang.reflect.Executable;

import se.skillytaire.didactic.tools.jlc.signature.spi.MethodSignature;
import se.skillytaire.didactic.tools.jlc.signature.spi.Signature;
import se.skillytaire.didactic.tools.jlc.signature.spi.SignatureTestFactory;
import se.skillytaire.didactic.tools.jlc.signature.spi.model.config.TestSignatureConfiguration;
import se.skillytaire.didactic.tools.jlc.spi.model.structure.JLCTestNode;

public class DBCSignatureTestFactory<N extends TestSignatureConfiguration<N,T,S,E>,T, S extends Signature,E extends Executable> implements SignatureTestFactory<N,T,S,E>{

	@Override
	public boolean matches(N configuration) {
		boolean matches = false;
		if(configuration.isDbcEnabled()) {
			Signature signature = configuration.getSignature();
			if(signature.hasNonPrimitiveParameters()) {
				if(!signature.equals(MethodSignature.EQUALS_METHOD_SIGNATURE)) {
					matches = true;
				}
			}
		}
		return matches;
	}

	@Override
	public JLCTestNode<T> create(N configuration) {
		return new DBCSignatureTest<>(configuration);
	}

}
