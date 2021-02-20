package se.skillytaire.didactic.tools.jlc.spi.array;

public class UniqueArrayBuilder <E> extends AbstractArrayBuilder<UniqueArrayBuilder<E>, E>{


	public UniqueArrayBuilder() {
		super();
	}

	public UniqueArrayBuilder(E[] array) {
		super(array);
	}

	@Override
	public UniqueArrayBuilder<E> append(E[] elements) {
		for (E element : elements) {
			add(element);
		}
		return this;
	}

	protected void add(E element) {
		boolean exist = false;
		for (E e : array) {
			if(e.equals(element)) {
				exist = true;
				break;
			}
		}
		if(!exist) {
			super.add(element);
		}
	}
}
