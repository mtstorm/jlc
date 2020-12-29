package se.skillytaire.didactic.tools.jlc.spi.model.config;

public abstract class AbstractTestGroupConfiguration implements TestGroupConfiguration{
	private boolean groupFeature;
	private boolean groupArchetype;
	private boolean groupApi;
	private boolean groupOverload;
	public boolean groupOverload() {
		return groupOverload;
	}

	protected void setGroupOverload(boolean groupOverload) {
		this.groupOverload = groupOverload;
	}

	@Override
	public boolean groupApi() {
		return groupApi;
	}

	protected void setGroupApi(boolean groupApi) {
		this.groupApi = groupApi;
	}

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
