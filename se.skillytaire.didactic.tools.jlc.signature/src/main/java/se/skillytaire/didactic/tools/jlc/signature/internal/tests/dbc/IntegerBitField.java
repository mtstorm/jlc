package se.skillytaire.didactic.tools.jlc.signature.internal.tests.dbc;

public class IntegerBitField implements BitField {
	private int value;

	public IntegerBitField(boolean... values) {
		this();
		this.setValue(values);
	}
	
	public IntegerBitField() {
		this(0);
	}

	public IntegerBitField(int aValue) {
		this.value = aValue;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public void setValue(boolean... values) {
		StringBuffer buffer = new StringBuffer();
		int maxPosition = values.length;
		for (int i = 0; i < maxPosition; i++) {
			if (values[i]) {
				buffer.append('1');
			} else {
				buffer.append('0');
			}
		}
		buffer.reverse();
		this.value = Integer.parseInt(buffer.toString(), 2);
	}

	/**
	 * Returns the boolean value for the position 2<sup>{@code position}</sup>.
	 * 
	 * @param position
	 *            the position.
	 * @return the boolean value of 2<sup>{@code position}</sup>.
	 */
	public boolean getValue(int position) {
		int x = (int) Math.pow(2, position);
		int temp = x & value;
		return x == temp;
	}

	public void setValue(int position, boolean value) {
		if (value) {
			int x = (int) Math.pow(2, position);
			this.value = this.value | x;
		} else {
			StringBuffer buffer = new StringBuffer();
			int maxPosition = getMaxPosition();
			if (maxPosition <= position) {
				maxPosition = position + 1;
			}
			for (int i = 0; i < maxPosition; i++) {
				if (i == position) {
					buffer.append('0');
				} else {
					buffer.append('1');
				}
			}
			buffer.reverse();
			int andOperator = Integer.parseInt(buffer.toString(), 2);
			this.value = this.value & andOperator;
		}
	}

	public void toggle(int position) {
		StringBuffer buffer = new StringBuffer();
		int maxPosition = getMaxPosition() ;
		if (maxPosition <= position) {
			maxPosition = position + 1;
		}
		for (int i = 0; i < maxPosition; i++) {
			if (i == position) {
				buffer.append('1');
			} else {
				buffer.append('0');
			}
		}
		buffer.reverse();
		int xorOperator = Integer.parseInt(buffer.toString(), 2);
		this.value = value ^ xorOperator;
	}

	public void toggle() {
		int maxPosition = getMaxPosition();
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < maxPosition; i++) {
			buffer.append('1');
		}
		int xorValue = Integer.parseInt(buffer.toString(), 2);
		this.value = this.value ^ xorValue;
	}

	protected int getMaxPosition() {
		int maxExponent = 0;
		int result = this.value;
		do {
			maxExponent++;
			result = result / 2;
		} while (result != 0);
		return maxExponent;
	}

	@Override
	public String toString() {
		return Integer.toBinaryString(getValue());
	}
}
