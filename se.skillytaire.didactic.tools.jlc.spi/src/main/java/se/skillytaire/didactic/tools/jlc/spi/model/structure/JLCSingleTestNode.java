package se.skillytaire.didactic.tools.jlc.spi.model.structure;

import org.junit.jupiter.api.function.Executable;
/**
 * For single tests (Leaf components)
 * @author prolector
 *
 * @param <T>
 */
public interface JLCSingleTestNode<T> extends JLCTestNode<T>,Executable{
		/**
		 * Gets the assert message used for this test.
		 * @return
		 */
	   String getAssertMessage();
//	   /**
//	    * The display name for visual tools
//	    * @return
//	    */
//	   String getDefaultDisplayName();
	   
	   default boolean hasChildren() {
		   return false;
	   }
}
