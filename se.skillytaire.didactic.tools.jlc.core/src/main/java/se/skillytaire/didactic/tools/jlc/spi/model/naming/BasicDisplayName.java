package se.skillytaire.didactic.tools.jlc.spi.model.naming;

public final class BasicDisplayName extends SimpleDisplayName {

	public BasicDisplayName(String name, String feature) {
		super(name, feature);
	}

	public BasicDisplayName(String name) {
		super(name);
	}

	@Override
	public boolean isDefault() {
		return false;
	}


}
