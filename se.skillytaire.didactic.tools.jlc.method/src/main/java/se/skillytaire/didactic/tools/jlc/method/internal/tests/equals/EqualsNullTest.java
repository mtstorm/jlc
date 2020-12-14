package se.skillytaire.didactic.tools.jlc.method.internal.tests.equals;

import static org.junit.jupiter.api.Assertions.*;

import se.skillytaire.didactic.tools.jlc.method.internal.tests.MethodTestDisplayName;
import se.skillytaire.didactic.tools.jlc.method.spi.model.config.TestMethodConfiguration;
import se.skillytaire.didactic.tools.jlc.method.spi.model.structure.AbstractSingleMethodTestNode;
import se.skillytaire.didactic.tools.jlc.spi.model.naming.DisplayName;

public class EqualsNullTest<T> extends  AbstractSingleMethodTestNode<T> {

	public EqualsNullTest(TestMethodConfiguration<T> testConfiguration) {
		super(testConfiguration);
	}


	@Override
	public DisplayName getDisplayName() {
		MethodTestDisplayName dn = 
				new MethodTestDisplayName(
						"Null Check",
						getTestConfiguration().getSignature(),
						"x",
						"null"
						);

		
		return dn;
	}



	


	@Override
	public String getAssertMessage() {
		return "For any non-null reference value x, x.equals(null) should return false.";
	}


	@Override
	public void execute() throws Throwable {
		assertFalse(this.createThis().equals(null),getAssertMessage());
		
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
