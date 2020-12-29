package se.skillytaire.didactic.tools.junit.core.internal.tools;

import java.util.Iterator;
import java.util.ListIterator;
import java.util.function.Consumer;

public class SweepIterator<E> implements Iterator<E> {

	private final ListIterator<E> listIterator;

	private final Consumer<E> start;
	private final Consumer<E> back;
	private boolean backwards;
	private Consumer<E> peek;

	public SweepIterator(ListIterator<E> listIterator, Consumer<E> start, Consumer<E> back) {
		if (listIterator == null) {
			throw new IllegalArgumentException("List iterator is void");
		}
		this.listIterator = listIterator;
		this.start = start;
		this.back = back;
	}

	@Override
	public boolean hasNext() {
		return backwards ? listIterator.hasPrevious() : listIterator.hasNext();
	}

	@Override
	public E next() {
		E element = backwards ? listIterator.previous() : listIterator.next();
		apply(element);
		if (!backwards && !hasNext()) {
			backwards = true;
		}
		return element;
	}

	private void apply(E e) {
		if (e == null) {
			throw new IllegalArgumentException("Element is void " + listIterator.previousIndex());
		}
		if (backwards){
			this.back.accept(e);
		} else {
			this.start.accept(e);
		}
		if (hasPeek()) {
			getPeek().accept(e);
		}
	}

	public Consumer<E> getPeek() {
		return peek;
	}

	public boolean hasPeek() {
		return peek != null;
	}

	public void setPeek(Consumer<E> peek) {
		this.peek = peek;
	}
}
