package se.skillytaire.didactic.tools.jlc.spi.model.naming;

public abstract class SimpleDisplayName extends DisplayName{
	private final String name;
	protected SimpleDisplayName(String name) {
		this(name, null);

	}
	
	protected SimpleDisplayName(String name, String feature) {
		super(feature);
		if(name == null) {
			throw new IllegalArgumentException("The name is void");
		}
		this.name = name;
	}


	@Override
	public String value() {
		return name;
	}

	@Override
	public boolean isDefault() {
		return true;
	}
	
	
}
