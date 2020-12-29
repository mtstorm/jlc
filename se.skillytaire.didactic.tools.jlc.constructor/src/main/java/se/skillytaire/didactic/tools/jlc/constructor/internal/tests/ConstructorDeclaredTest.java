package se.skillytaire.didactic.tools.jlc.constructor.internal.tests;

import static org.junit.jupiter.api.Assertions.assertTrue;

import se.skillytaire.didactic.tools.jlc.constructor.spi.model.config.TestConstructorConfiguration;
import se.skillytaire.didactic.tools.jlc.spi.model.naming.BasicDisplayName;
import se.skillytaire.didactic.tools.jlc.spi.model.naming.DisplayName;

public class ConstructorDeclaredTest<T> extends AbstractSingleConstructorTestNode<T>{

	ConstructorDeclaredTest(TestConstructorConfiguration<T> testConfiguration) {
		super(testConfiguration);
	}

	@Override
	public String getAssertMessage() {
		return String.format("The constructor '%s' is not declared, but it is required.", getTestConfiguration().getSignature());
	}

	@Override
	public DisplayName getDisplayName() {
		return new BasicDisplayName("Declared");
	}

	@Override
	public void execute() throws Throwable {
		assertTrue(getTestConfiguration().hasExecutor(), getAssertMessage());
	}

}
