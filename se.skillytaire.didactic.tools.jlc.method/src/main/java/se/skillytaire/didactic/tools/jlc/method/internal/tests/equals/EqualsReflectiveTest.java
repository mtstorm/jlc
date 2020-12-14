package se.skillytaire.didactic.tools.jlc.method.internal.tests.equals;

import static org.junit.jupiter.api.Assertions.*;

import se.skillytaire.didactic.tools.jlc.method.internal.tests.MethodTestDisplayName;
import se.skillytaire.didactic.tools.jlc.method.spi.model.config.TestMethodConfiguration;
import se.skillytaire.didactic.tools.jlc.method.spi.model.structure.AbstractSingleMethodTestNode;
import se.skillytaire.didactic.tools.jlc.spi.model.naming.DisplayName;

public class EqualsReflectiveTest<T> extends  AbstractSingleMethodTestNode<T> {

	public EqualsReflectiveTest(TestMethodConfiguration<T> testConfiguration) {
		super(testConfiguration);
	}


	@Override
	public DisplayName getDisplayName() {
		MethodTestDisplayName dn = 
				new MethodTestDisplayName(
						"Reflective",
						getTestConfiguration().getSignature(),
						"x",
						"x"
						);

		
		return dn;
	}



	


	@Override
	public String getAssertMessage() {
		return "for any non-null reference value x, x.equals(x) should return true.";	}
	

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
