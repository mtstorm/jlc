package se.skillytaire.didactic.tools.jlc.api;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Optional;
import java.util.stream.Stream;

@Deprecated
public final class TestOrderDescription implements Serializable, Comparable<TestOrderDescription> {

	private static final long serialVersionUID = 1L;
	private final TestDisplayOrder displayOrder;
	private final boolean reversed;

	public TestOrderDescription(final TestOrder testOrder) {
		this(testOrder.sort(), testOrder.inverse());
	}

	public TestOrderDescription() {
		this(TestDisplayOrder.NONE, false);
	}

	private TestOrderDescription(final TestDisplayOrder displayOrder, final boolean reversed) {
		this.displayOrder = displayOrder;
		this.reversed = reversed;
	}

	@Override
	public int hashCode() {
		return 13 * this.displayOrder.hashCode() * (this.reversed ? 1231 : 1237);
	}

	@Override
	public boolean equals(final Object obj) {
		boolean equals = super.equals(obj);
		if (!equals && obj instanceof TestOrderDescription) {
			final TestOrderDescription that = (TestOrderDescription) obj;
			equals = compareTo(that) == 0;
		}

		return equals;
	}

	@Override
	public String toString() {
		return String.format("TestOrderDescription [displayOrder=%s, reversed=%s]", this.displayOrder, this.reversed);
	}

	@Override
	public int compareTo(final TestOrderDescription that) {
		int compareTo = getDisplayOrder().compareTo(that.getDisplayOrder());
		if (compareTo == 0) {
			compareTo = Boolean.compare(isReversed(), that.isReversed());
		}
		return compareTo;
	}

	public TestDisplayOrder getDisplayOrder() {
		return this.displayOrder;
	}

	public boolean isReversed() {
		return this.reversed;
	}

	public boolean isEnabled() {
		return !this.displayOrder.equals(TestDisplayOrder.NONE);
	}

	/**
	 * When this is not enabled the description is used. When this is enable
	 *
	 * @param description
	 * @return
	 */
	public TestOrderDescription override(final TestOrderDescription description) {
		TestOrderDescription result;
		if (isEnabled() && !description.isEnabled()) {
			result = this;
		} else {
			result = description;
		}
		return result;
	}

	public <T extends Comparable<T>> Stream<T> apply(final Stream<T> stream) {
		Stream<T> result;
		final Optional<Comparator<T>> comparator = create();
		if (comparator.isPresent()) {
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
