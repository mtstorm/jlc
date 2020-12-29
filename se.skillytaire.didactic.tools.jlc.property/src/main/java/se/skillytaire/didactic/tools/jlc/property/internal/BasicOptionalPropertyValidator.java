package se.skillytaire.didactic.tools.jlc.property.internal;

import se.skillytaire.didactic.tools.jlc.property.spi.OptionalPropertyValidator;
import se.skillytaire.didactic.tools.jlc.spi.ext.field.PropertyType;
import se.skillytaire.didactic.tools.jlc.spi.util.ClassProperty;

public class BasicOptionalPropertyValidator<T> implements OptionalPropertyValidator {

	@Override
	public PropertyType getType(ClassProperty property) {
		PropertyType type;
		if(property.hasHasMethod()) {
			type = PropertyType.Optional;
		} else {
			type = PropertyType.Required;
		}
		return type;
	}

}
