package se.skillytaire.didactic.tools.jlc.spi.model.config;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Optional;
import java.util.stream.Stream;

import se.skillytaire.didactic.tools.jlc.api.TestDisplayOrder;
import se.skillytaire.didactic.tools.jlc.api.TestOrder;

public final class TestOrderDescription implements Serializable, Comparable<TestOrderDescription> {

	private static final long serialVersionUID = 1L;
	private final TestDisplayOrder displayOrder;
	private final boolean reversed;
	
	public TestOrderDescription(TestOrder testOrder) {
		this(testOrder.sort(), testOrder.inverse());
	}
	
	public TestOrderDescription() {
		this(TestDisplayOrder.NONE, false);
	}
	
	private TestOrderDescription(TestDisplayOrder displayOrder, boolean reversed) {
		this.displayOrder = displayOrder;
		this.reversed = reversed;
	}

	@Override
	public int hashCode() {
		return 13 * displayOrder.hashCode() *(reversed ? 1231 : 1237);
	}

	@Override
	public boolean equals(Object obj) {
		boolean equals = super.equals(obj);
		if(!equals && obj instanceof TestOrderDescription) {
			TestOrderDescription that = (TestOrderDescription) obj;
			equals = this.compareTo(that) == 0;
		}

		return equals;
	}
	
	
	@Override
	public String toString() {
		return String.format("TestOrderDescription [displayOrder=%s, reversed=%s]", displayOrder, reversed);
	}

	@Override
	public int compareTo(TestOrderDescription that) {
		int compareTo = this.getDisplayOrder().compareTo(that.getDisplayOrder());
		if(compareTo == 0) {
			compareTo = Boolean.compare(this.isReversed(), that.isReversed());
		}
		return compareTo;
	}

	public TestDisplayOrder getDisplayOrder() {
		return displayOrder;
	}

	public boolean isReversed() {
		return reversed;
	}

	public boolean isEnabled() {
		return !this.displayOrder.equals(TestDisplayOrder.NONE);
	}
	/**
	 * When this is not enabled the description is used.
	 * When this is enable
	 * @param description
	 * @return
	 */
	public TestOrderDescription override(TestOrderDescription description) {
		TestOrderDescription result;
		if(isEnabled() && !description.isEnabled()) {
			result = this;
		} else {
			result = description;
		}
		return result;
	}
	
	
	
	public <T extends Comparable<T>> Stream<T> apply(Stream<T> stream){
		Stream<T> result;
		Optional<Comparator<T>> comparator = create();
		if(comparator.isPresent()) {
			result = stream.sorted(comparator.get());
		} else {
			result = stream;
		}
		return result;
	}
	
	public <T extends Comparable<T>> Optional<Comparator<T>> create() {
		Comparator<T> comparator = null;

		switch (this.displayOrder) {
		case Natural:
			comparator = (a, b) -> a.compareTo(b);
			if (isReversed()) {
				comparator = comparator.reversed();
			}
			break;

		default:
			break;
		}

		return Optional.ofNullable(comparator);
	}



}
