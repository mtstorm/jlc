package se.skillytaire.didactic.tools.jlc.spi.array;

import java.util.Arrays;

public class DefaultArrayBuilder<E> extends AbstractArrayBuilder<DefaultArrayBuilder<E>, E>{
	
	public DefaultArrayBuilder() {
		this.array = (E[]) new Object[0];
	}
	public DefaultArrayBuilder(E[] array) {
		this.array = array.clone();
	}
	

	public DefaultArrayBuilder<E> append(E[] elements) {
		int length = this.array.length;
		int newLength = length + elements.length;
		E[] newElements = Arrays.copyOf(this.array, newLength);
		System.arraycopy(elements, 0, newElements, length, elements.length);
		this.array = newElements;
		return this;
	}



}
