package se.skillytaire.didactic.tools.jlc.method.internal.tests.tostring;

import org.junit.jupiter.api.Assertions;

import se.skillytaire.didactic.tools.jlc.method.spi.model.config.TestMethodConfiguration;
import se.skillytaire.didactic.tools.jlc.method.spi.model.structure.AbstractSingleMethodTestNode;
import se.skillytaire.didactic.tools.jlc.spi.model.naming.BasicDisplayName;
import se.skillytaire.didactic.tools.jlc.spi.model.naming.DisplayName;

public class OverrideToStringContentTest<T> extends  AbstractSingleMethodTestNode<T> {

	public OverrideToStringContentTest(TestMethodConfiguration testConfiguration) {
		super(testConfiguration);
	}

	@Override
	public String getAssertMessage() {
	      String msg = String
	              .format("The toString method of the class %s should be overriden.", getObjectFactory()
	                    .type().getName());
	        return msg;
	}

	@Override
	public DisplayName getDisplayName() {
		return new BasicDisplayName("Changed result");
	}

	@Override
	public void execute() throws Throwable {
	      T object = createThis();
	      String actual = object.toString();
	      String hex = Integer.toHexString(object.hashCode());
	      String unexpected =
	            String.format("%s@%s", object.getClass().getName(), hex);
	      Assertions.assertNotEquals(unexpected, actual);
		
	}

}
