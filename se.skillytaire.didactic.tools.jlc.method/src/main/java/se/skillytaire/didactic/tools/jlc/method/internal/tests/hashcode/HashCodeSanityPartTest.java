package se.skillytaire.didactic.tools.jlc.method.internal.tests.hashcode;

import static org.junit.jupiter.api.Assertions.*;

import se.skillytaire.didactic.tools.jlc.method.internal.tests.AbstractSanityPartTest;
import se.skillytaire.didactic.tools.jlc.method.internal.tests.TestPartObjectFactory;
import se.skillytaire.didactic.tools.jlc.method.spi.model.config.TestMethodConfiguration;

public class HashCodeSanityPartTest<T> extends  AbstractSanityPartTest<T, Boolean> {

	protected HashCodeSanityPartTest(TestMethodConfiguration testConfiguration,
			TestPartObjectFactory<T, Boolean> factory) {
		super(testConfiguration, factory);
	}
//fixme invoker als generiek
	@Override
	public void execute() throws Throwable {
		boolean actual = x.hashCode() == y.hashCode();
		assertEquals(expected, actual, getAssertMessage());
		
	}

//	public HashCodeReferenceTest(TestMethodConfiguration testConfiguration) {
//		super(testConfiguration);
//	}
//
//	@Override
//	public String getAssertMessage() {
//	        return "Two different objects references with the same contents should have the same hashcode.";
//	}
//
//	@Override
//	public DisplayName getDisplayName() {
//		return new BasicDisplayName("[x.equals(y) == true] => [x.hashCode() == y.hashCode() == true]");
//	}
//
//	@Override
//	public void execute() throws Throwable {
//	      Object object1 = createThis();
//	      Object object2 = createThis();
//	      Assertions.assertEquals(
//	            object1.hashCode(), object2.hashCode(),getAssertMessage());
//
//	}

}
