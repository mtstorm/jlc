package se.skillytaire.didactic.tools.jlc.spi.model.naming;

public abstract class DisplayName {

	private String feature;

	public DisplayName(String feature) {
		this.feature = feature;
	}
	public DisplayName() {
		this(null);
	}
	
	/**
	 * @return the feature
	 */
	public String getFeature() {
		return feature;
	}
	/**
	 * @return the feature
	 */
	public boolean hasFeature() {
		return feature != null;
	}
	/**
	 * @param feature the feature to set
	 */
	public void setFeature(String feature) {
		this.feature = feature.trim();
	}
	
	
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		if(hasFeature()) {
			builder.append(getFeature()).append(':');
		}
		builder.append(value());
		return builder.toString();
	}
	/**
	 * The main name of the display.
	 * @return
	 */
	public abstract String value();
	/**
	 * Checks if the display name is default named by the framework.
	 * @return
	 */
	public abstract boolean isDefault();
}
