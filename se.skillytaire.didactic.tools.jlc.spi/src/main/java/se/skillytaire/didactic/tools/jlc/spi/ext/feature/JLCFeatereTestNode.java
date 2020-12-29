package se.skillytaire.didactic.tools.jlc.spi.ext.feature;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;

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
	
	/**
	 * The uri for this node.
	 * 
	 * @return
	 */
	@Override
	default public  Optional<URI> toUri() {
		URI uri = null;
		Class<?> declaringClass = getClass();
		
		StringBuilder builder = new StringBuilder();
//append(type.getName()).append('#')
		builder.append("method:")
		.append(declaringClass.getName())
		.append("#getDisplayName()");
		URI result = null;
		try {
			result = new URI(builder.toString());
		} catch (URISyntaxException e) {
			throw new AssertionError(e);
		}
		// System.out.println("URI in signature "+ uri);

		return Optional.ofNullable(uri);
	}
}
