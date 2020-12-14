package se.skillytaire.didactic.tools.jlc.spi.ext.feature;

import se.skillytaire.didactic.tools.jlc.spi.model.structure.CompositeTestNode;
import se.skillytaire.didactic.tools.jlc.spi.model.structure.JLCSingleTestNode;
import se.skillytaire.didactic.tools.jlc.spi.model.structure.JLCTestNode;

public interface JLCFeatereTestNode<T> extends CompositeTestNode<T>{
	/**
	 * Determines the position in the features.
	 * @return
	 */
	public int getWeight();
	/**
	 * Checks if the node contains an executable test in the structure.
	 * @return
	 */
	public default boolean hasExecutableTest() {
		return hasExecutableTest(this);

	}
	
	public static <T> boolean hasExecutableTest(JLCTestNode<T> node) {
		boolean hasExecutableTest = false;
		if(node instanceof JLCSingleTestNode) {
			hasExecutableTest = true;
		}else {
			//meaning composite
			CompositeTestNode<T> composite = (CompositeTestNode<T>) node;
			for (JLCTestNode<T> child : composite) {
				hasExecutableTest = hasExecutableTest(child);
				if(hasExecutableTest) {
					break;
				}
			}
		}
		return hasExecutableTest;
	}
	
	
	void build();
	public void peek();
	
}
