package se.skillytaire.didactic.tools.jlc.spi.ext.immutable;

import se.skillytaire.didactic.tools.jlc.api.ImmutableType;
import se.skillytaire.didactic.tools.jlc.api.TestObjectFactory;

public class ImmutableObjectType implements ImmutableObject{

	@Override
	public boolean isImmutable(TestObjectFactory<?> factory) {
		return factory.getClass().isAnnotationPresent(ImmutableType.class);
	}

}
