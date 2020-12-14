package se.skillytaire.didactic.tools.jlc.spi.model.config;

public abstract class AbstractTestGroupConfiguration implements TestGroupConfiguration{
	private boolean groupFeature;
	private boolean groupArchetype;
	
	protected void setGroupFeature(boolean groupFeature) {
		this.groupFeature = groupFeature;
	}

	protected void setGroupArchetype(boolean groupArchetype) {
		this.groupArchetype = groupArchetype;
	}

	@Override
	public boolean groupFeature() {
		return groupFeature;
	}

	@Override
	public boolean groupArchetype() {
		return groupArchetype;
	}
	public boolean isEnabled() {
		return groupFeature() || groupArchetype();
	}
}
