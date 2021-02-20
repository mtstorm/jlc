package se.skillytaire.didactic.tools.jlc.spi.ext.attribute;

import se.skillytaire.didactic.tools.jlc.api.JLCConfigurationException;

public final class JLCAttributeValueNotFoundException extends JLCConfigurationException{


	private static final long serialVersionUID = 1L;

	public JLCAttributeValueNotFoundException(String attributeName) {
		super(String.format("Attribute '%s' is not found in the configuration.", attributeName));
	}
}
