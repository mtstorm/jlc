package se.skillytaire.didactic.tools.jlc.method.internal.tests.equals;

import static org.junit.jupiter.api.Assertions.*;

import se.skillytaire.didactic.tools.jlc.method.internal.tests.MethodTestDisplayName;
import se.skillytaire.didactic.tools.jlc.method.spi.model.config.TestMethodConfiguration;
import se.skillytaire.didactic.tools.jlc.method.spi.model.structure.AbstractSingleMethodTestNode;
import se.skillytaire.didactic.tools.jlc.spi.model.naming.DisplayName;

public class EqualsConsistencyTest<T> extends  AbstractSingleMethodTestNode<T> {

	public EqualsConsistencyTest(TestMethodConfiguration<T> testConfiguration) {
		super(testConfiguration);
	}


	@Override
	public DisplayName getDisplayName() {
		MethodTestDisplayName dn = 
				new MethodTestDisplayName(
						"Consistency",
						getTestConfiguration().getSignature(),
						"x",
						"x..10"
						);

		
		return dn;
	}



	


	@Override
	public String getAssertMessage() {
		return "It is consistent: for any non-null reference values x and y, multiple invocations of x.equals(y)consistently return true or consistently return false, provided no information used in equals comparisons on the objects is modified.";
	}


	@Override
	public void execute() throws Throwable {
		T x = createThis();
		for (int i = 0; i < 10; i++) {
			assertEquals( x, x,getAssertMessage());
		}
	}





//
//extends AbstractEqualsMethodSpi {
//
//	@Override
//	public JLCSingleTestNode create(TestMethodConfiguration configuration) {
//		// TODO Auto-generated method stub
//		return null;
//	}




}
