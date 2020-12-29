package se.skillytaire.didactic.tools.jlc.jcip.annotations;


import net.jcip.annotations.Immutable;
import se.skillytaire.didactic.tools.jlc.api.TestObjectFactory;
import se.skillytaire.didactic.tools.jlc.spi.ext.immutable.ImmutableObject;

public class JcipImmutableObject implements ImmutableObject{

	@Override
	public boolean isImmutable(TestObjectFactory<?> factory) {
		return factory.getClass().isAnnotationPresent(Immutable.class);
	}

}
