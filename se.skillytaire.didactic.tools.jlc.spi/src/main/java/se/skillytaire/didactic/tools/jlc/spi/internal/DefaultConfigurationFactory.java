package se.skillytaire.didactic.tools.jlc.spi.internal;

import se.skillytaire.didactic.tools.jlc.api.JLCConfiguration;
import se.skillytaire.didactic.tools.jlc.api.JLCConfigurationFactory;

public final class DefaultConfigurationFactory implements JLCConfigurationFactory {

	@Override
	public <T> JLCConfiguration<T> create(Object testInstance) {
		return new JLCConfigurationImpl<>(testInstance);
	}

}
