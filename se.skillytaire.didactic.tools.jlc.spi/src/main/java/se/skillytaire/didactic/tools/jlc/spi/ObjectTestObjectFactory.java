package se.skillytaire.didactic.tools.jlc.spi;

import se.skillytaire.didactic.tools.jlc.api.AbstractTestObjectFactory;
import se.skillytaire.didactic.tools.jlc.api.ImmutableType;
import se.skillytaire.didactic.tools.jlc.api.JLCTestFactory;

@ImmutableType
@JLCTestFactory(path = "java/lang")
public class ObjectTestObjectFactory extends AbstractTestObjectFactory<Object> {

	@Override
	public Object createThis() {
		return new Object();
	}

	@Override
	public Object createThat() {
		return new Object();
	}

}
