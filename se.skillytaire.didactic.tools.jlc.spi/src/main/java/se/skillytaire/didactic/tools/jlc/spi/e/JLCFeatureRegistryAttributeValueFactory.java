package se.skillytaire.didactic.tools.jlc.spi.e;

import se.skillytaire.didactic.tools.jlc.api.JLCConfiguration;
import se.skillytaire.didactic.tools.jlc.spi.ext.attribute.AttributeValue;
import se.skillytaire.didactic.tools.jlc.spi.ext.attribute.AttributeValueFactory;

public final class JLCFeatureRegistryAttributeValueFactory<T> implements AttributeValueFactory<T> {

	@Override
	public AttributeValue create(JLCConfiguration<T> config) {
		return new JLCFeatureRegistry();
	}

	
}
