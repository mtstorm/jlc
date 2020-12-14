package se.skillytaire.didactic.tools.jlc.method.internal.tests.equals.sanity;

import static org.junit.jupiter.api.Assertions.*;

import se.skillytaire.didactic.tools.jlc.method.internal.tests.AbstractSanityPartTest;
import se.skillytaire.didactic.tools.jlc.method.internal.tests.TestPartObjectFactory;
import se.skillytaire.didactic.tools.jlc.method.spi.model.config.TestMethodConfiguration;

public class EqualsSanityPartTest<T> extends  AbstractSanityPartTest<T, Boolean> {

	public EqualsSanityPartTest(TestMethodConfiguration testConfiguration,TestPartObjectFactory<T, Boolean> factory) {
		super(testConfiguration, factory);

	}

	@Override
	public void execute() throws Throwable {
		boolean actual = x.equals(y);
		assertEquals(expected, actual, getAssertMessage());
	}
	
}
