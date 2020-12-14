package se.skillytaire.didactic.tools.jlc.method.internal.tests;

import se.skillytaire.didactic.tools.jlc.spi.model.naming.DisplayName;

public class MethodTestValidityDisplayName extends DisplayName {
	private final MethodTestDisplayName first;
	private final MethodTestDisplayName inversed;
	
	public MethodTestValidityDisplayName(MethodTestDisplayName first, MethodTestDisplayName inversed) {
		super(null);
		this.first = first;
		this.inversed = inversed;
	}
	@Override
	public String value() {
		return this.first.value() +" == "+ inversed.value();
	}
	@Override
	public boolean isDefault() {
		// TODO Auto-generated method stub
		return true;
	}
	
}
