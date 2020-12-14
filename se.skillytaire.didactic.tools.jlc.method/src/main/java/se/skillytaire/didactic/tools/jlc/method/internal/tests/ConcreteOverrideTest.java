package se.skillytaire.didactic.tools.jlc.method.internal.tests;

import org.junit.jupiter.api.Assertions;

import se.skillytaire.didactic.tools.jlc.method.spi.model.config.TestMethodConfiguration;
import se.skillytaire.didactic.tools.jlc.method.spi.model.structure.AbstractSingleMethodTestNode;
import se.skillytaire.didactic.tools.jlc.spi.model.naming.DisplayName;

/**
 * Checks if the signature is declared at the concrete class level.
 * @author prolector
 *
 */
public class ConcreteOverrideTest<T> extends  AbstractSingleMethodTestNode<T> {

	public ConcreteOverrideTest(TestMethodConfiguration testConfiguration) {
		super(testConfiguration);
	}

	@Override
	public String getAssertMessage() {
		String msg = String.format("The method %s should be overridden in the class %s.",
				this.getTestConfiguration().getSignature(),type());
		return msg;
	}

	@Override
	public DisplayName getDisplayName() {
		return new OverrideDisplayName();
	}

	@Override
	public void execute() throws Throwable {
		Assertions.assertTrue(this.getTestConfiguration().hasExecutor(), getAssertMessage());
		Class<?> expected = type();
		Class<?> actual =this.getTestConfiguration().declaringClass().get();
		Assertions.assertEquals(expected,actual, getAssertMessage());
		
	}

}
