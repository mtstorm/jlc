package se.skillytaire.didactic.tools.jlc.factory.java.lang;

import se.skillytaire.didactic.tools.jlc.api.ComparableTestObjectFactory;
import se.skillytaire.didactic.tools.jlc.api.ImmutableType;
import se.skillytaire.didactic.tools.jlc.api.JLCTestFactory;
import se.skillytaire.didactic.tools.jlc.api.Pooled;

@ImmutableType
@JLCTestFactory(path = "java/lang")
@Pooled 
public class ByteTestObjectFactory implements ComparableTestObjectFactory<Byte> {

	@Override
	public Byte createThis() {
		return 7;
	}

	@Override
	public Byte createThat() {
		return 13;
	}

	@Override
	public boolean isTypeFor(Class<?> type) {
		return Byte.class == type || Byte.TYPE == type;
	}
	public Class<?>[] types() {
		return new Class[] { type(), Byte.TYPE };
	}
	@Override
	public Class<Byte> type() {
		return Byte.class;
	}

	@Override
	public Byte createLessThen() {
		return Byte.MIN_VALUE;
	}

	@Override
	public Byte createGreaterThen() {
		return Byte.MAX_VALUE;
	}

}
