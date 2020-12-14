package se.skillytaire.didactic.tools.jlc.signature.internal.tests.dbc;

public interface BitField {
	/**
	 * Returns the boolean value for the position 2<sup>{@code position}</sup>.
	 * 
	 * @param position
	 *            the position.
	 * @return the boolean value of 2<sup>{@code position}</sup>.
	 */
	boolean getValue(int position);	
	
	void setValue(int position, boolean value);
	
	void setValue(boolean... values);
		
	void toggle(int position);
	
	void toggle();
	
	
}
