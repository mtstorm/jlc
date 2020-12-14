package se.skillytaire.didactic.tools.jlc.method.internal.tests.equals.trans;

import static org.junit.jupiter.api.Assertions.assertTrue;

import se.skillytaire.didactic.tools.jlc.method.spi.model.config.TestMethodConfiguration;
import se.skillytaire.didactic.tools.jlc.method.spi.model.structure.AbstractPartialSingleMethodTestNode;
import se.skillytaire.didactic.tools.jlc.spi.model.naming.DisplayName;

public class EqualsTransiveTester<T> extends  AbstractPartialSingleMethodTestNode<T> {



	private final T x;
	private final T y;
	private final T z;
	private final DisplayName displayName;
	
	   public EqualsTransiveTester(TestMethodConfiguration testConfiguration, String assertMessage, T x, T y, T z,DisplayName displayName) {
		super(testConfiguration, assertMessage);
		this.x = x;
		this.y = y;
		this.z = z;
		this.displayName = displayName;
	}


	private final static <T> boolean testTransitiefEquality(T obj1, T obj2, T obj3) {
		      boolean isTransitief;
		      boolean stap1 = obj1.equals(obj2);
		      boolean stap2 = obj2.equals(obj3);
		      boolean stap3 = obj1.equals(obj3);
		      int value = 0;
		      value += stap1 ? 1 : 0;
		      value += stap2 ? 1 : 0;
		      value += stap3 ? 1 : 0;
		      isTransitief = value != 2;
		      return isTransitief;
		   }


	@Override
	public DisplayName getDisplayName() {

		return displayName;
	}


	@Override
	public void execute() throws Throwable {
		assertTrue(testTransitiefEquality(x, y, z));
		
	} 
}
