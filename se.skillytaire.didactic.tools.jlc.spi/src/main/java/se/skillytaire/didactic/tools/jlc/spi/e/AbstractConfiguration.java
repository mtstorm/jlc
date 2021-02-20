package se.skillytaire.didactic.tools.jlc.spi.e;

public abstract class AbstractConfiguration implements Configuration {
	private boolean enabled;
	private String displayNameValue;
	
	protected AbstractConfiguration() {
		this.enabled = true;
	}
	@Override
	public final boolean isEnabled() {
		return enabled;
	}
	/**
	 * @param enabled the enabled to set
	 */
	public final void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	@Override
	public final String getDisplayNameValue() {
		return displayNameValue;
	}
	public final boolean hasDisplayNameValue() {
		return displayNameValue != null && !displayNameValue.trim().isEmpty();
	}

	public final void setDisplayNameValue(String displayNameValue) {
		this.displayNameValue = displayNameValue;
	}
	
}
