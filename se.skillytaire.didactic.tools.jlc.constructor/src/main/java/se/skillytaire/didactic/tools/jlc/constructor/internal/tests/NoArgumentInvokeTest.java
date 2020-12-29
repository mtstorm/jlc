package se.skillytaire.didactic.tools.jlc.constructor.internal.tests;

import static org.junit.jupiter.api.Assertions.fail;

import se.skillytaire.didactic.tools.jlc.constructor.spi.model.config.TestConstructorConfiguration;
import se.skillytaire.didactic.tools.jlc.spi.model.naming.BasicDisplayName;
import se.skillytaire.didactic.tools.jlc.spi.model.naming.DisplayName;

public class NoArgumentInvokeTest<T> extends AbstractSingleConstructorTestNode<T>{

	NoArgumentInvokeTest(TestConstructorConfiguration<T> testConfiguration) {
		super(testConfiguration);
	}

	@Override
	public String getAssertMessage() {
		return "Invoking a no argument constructor may not result in an exception";
	}

	@Override
	public DisplayName getDisplayName() {
		return new BasicDisplayName("Auto invoke");
	}

	@Override
	public void execute() throws Throwable {
		try {
			getTestConfiguration().getExecutor().newInstance();
		}catch(Throwable e) {
			fail(getAssertMessage(), e);
		}
	}

}
