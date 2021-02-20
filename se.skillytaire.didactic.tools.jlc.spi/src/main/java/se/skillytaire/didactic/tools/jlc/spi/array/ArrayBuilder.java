package se.skillytaire.didactic.tools.jlc.spi.array;

import java.util.stream.Stream;

public interface ArrayBuilder<T extends ArrayBuilder<T,E>, E> {
	T append(E element);
	T append(E[] element);
	E[] toArray();
	Stream<E> stream();
}
