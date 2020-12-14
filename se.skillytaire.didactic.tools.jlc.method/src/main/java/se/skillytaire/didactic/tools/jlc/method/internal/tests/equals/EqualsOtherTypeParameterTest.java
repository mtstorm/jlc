package se.skillytaire.didactic.tools.jlc.method.internal.tests.equals;

import static org.junit.jupiter.api.Assertions.*;

import se.skillytaire.didactic.tools.jlc.method.internal.tests.MethodTestDisplayName;
import se.skillytaire.didactic.tools.jlc.method.spi.model.config.TestMethodConfiguration;
import se.skillytaire.didactic.tools.jlc.method.spi.model.structure.AbstractSingleMethodTestNode;
import se.skillytaire.didactic.tools.jlc.spi.model.naming.DisplayName;

public class EqualsOtherTypeParameterTest<T> extends AbstractSingleMethodTestNode<T> {

	public EqualsOtherTypeParameterTest(TestMethodConfiguration<T> testConfiguration) {
		super(testConfiguration);
	}

	@Override
	public String getAssertMessage() {

		return "For any non-null reference value x, x.equals( new Object() ) should return false.";
	}

	@Override
	public DisplayName getDisplayName() {
		MethodTestDisplayName dn = new MethodTestDisplayName("Type Check", getTestConfiguration().getSignature(), "x",
				"new Object()");
		return dn;
	}

	@Override
	public void execute() throws Throwable {
		Object object = new Object();
		boolean condition = this.createThis().equals(object);
		assertFalse(condition, getAssertMessage());

	}

}
