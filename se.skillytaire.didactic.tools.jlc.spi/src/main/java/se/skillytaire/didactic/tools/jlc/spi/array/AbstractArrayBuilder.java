package se.skillytaire.didactic.tools.jlc.spi.array;

import java.util.Arrays;
import java.util.stream.Stream;

public abstract class AbstractArrayBuilder<T extends ArrayBuilder<T,E>, E> implements ArrayBuilder<T,E>{
	protected E[] array;//fixme
	protected AbstractArrayBuilder() {
		this.array = (E[]) new Object[0];
	}
	protected AbstractArrayBuilder(E[] array) {
		this.array = array.clone();
	}
	public final E[] toArray() {
		return this.array.clone();
	}
	
	public final Stream<E> stream(){
		return Arrays.stream(this.array);
	}
	public T append(E element) {
		add(element);
		return (T) this;
		
	}
	/**
	 * Appends the element to the end of the array.
	 * @param element
	 */
	protected void add(E element) {
		int length = this.array.length;
		E[] newElements = Arrays.copyOf(this.array, length+1);
		newElements[length] = element;
		this.array = newElements;
	}
}
