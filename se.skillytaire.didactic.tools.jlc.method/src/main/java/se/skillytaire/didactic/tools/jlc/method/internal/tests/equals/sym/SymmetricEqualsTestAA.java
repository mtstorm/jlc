package se.skillytaire.didactic.tools.jlc.method.internal.tests.equals.sym;

import static org.junit.jupiter.api.Assertions.assertTrue;

import se.skillytaire.didactic.tools.jlc.method.internal.tests.MethodTestDisplayName;
import se.skillytaire.didactic.tools.jlc.method.internal.tests.MethodTestValidityDisplayName;
import se.skillytaire.didactic.tools.jlc.method.spi.model.config.TestMethodConfiguration;
import se.skillytaire.didactic.tools.jlc.method.spi.model.structure.AbstractPartialSingleMethodTestNode;
import se.skillytaire.didactic.tools.jlc.spi.model.naming.DisplayName;

public class SymmetricEqualsTestAA<T>  extends  AbstractPartialSingleMethodTestNode<T> {

	public SymmetricEqualsTestAA(TestMethodConfiguration testConfiguration, String assertMessage) {
		super(testConfiguration, assertMessage);
	}



	@Override
	public DisplayName getDisplayName() {
		MethodTestDisplayName first = 
				new MethodTestDisplayName(
						getTestConfiguration().getSignature(),
						"this1",
						"this2"
						);
		MethodTestDisplayName inversed = 
				new MethodTestDisplayName(
						getTestConfiguration().getSignature(),
						"this2",
						"this1"
						);
		return new MethodTestValidityDisplayName( first, inversed);
	}

	@Override
	public void execute() throws Throwable {
		Object this1 = createThis();
		Object this2 = createThis();
		assertTrue(this1.equals(this2) == this2.equals(this1),getAssertMessage());
	}




}
