package se.skillytaire.didactic.tools.jlc.api;

public interface JLCConfigurationFactory {
	public <T> JLCConfiguration<T> create(Object testInstance);
}
