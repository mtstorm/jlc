package se.skillytaire.didactic.tools.jlc.method.internal.tests.equals.sym;

import static org.junit.jupiter.api.Assertions.assertTrue;

import se.skillytaire.didactic.tools.jlc.method.internal.tests.MethodTestDisplayName;
import se.skillytaire.didactic.tools.jlc.method.internal.tests.MethodTestValidityDisplayName;
import se.skillytaire.didactic.tools.jlc.method.spi.model.config.TestMethodConfiguration;
import se.skillytaire.didactic.tools.jlc.method.spi.model.structure.AbstractPartialSingleMethodTestNode;
import se.skillytaire.didactic.tools.jlc.spi.model.naming.DisplayName;

public class SymmetricEqualsTestAObject<T>  extends  AbstractPartialSingleMethodTestNode<T> {

		public SymmetricEqualsTestAObject(TestMethodConfiguration testConfiguration, String assertMessage) {
			super(testConfiguration, assertMessage);
		}


	@Override
	public DisplayName getDisplayName() {
		MethodTestDisplayName first = 
				new MethodTestDisplayName(
						
						getTestConfiguration().getSignature(),
						"this1",
						"new Object()"
						);
		MethodTestDisplayName inversed = 
				new MethodTestDisplayName(
						getTestConfiguration().getSignature(),
						"new Object()",
						"this1"
						);
		return new MethodTestValidityDisplayName( first, inversed);
	}

	@Override
	public void execute() throws Throwable {
		Object this1 = createThis();
		Object that1 = new Object();
		assertTrue(this1.equals(that1) == that1.equals(this1),getAssertMessage());
	}




}
