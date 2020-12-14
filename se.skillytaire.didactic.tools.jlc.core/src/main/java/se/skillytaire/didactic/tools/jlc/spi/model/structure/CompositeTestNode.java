package se.skillytaire.didactic.tools.jlc.spi.model.structure;

import java.util.stream.Stream;

public interface CompositeTestNode<T> extends Iterable<JLCTestNode<T>>, JLCTestNode<T>{	
	void add(JLCTestNode<T> childNode);
	boolean remove(JLCTestNode<T> childNode);
	Stream<JLCTestNode<T>> children();
	void clear();
}
