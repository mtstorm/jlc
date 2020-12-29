package se.skillytaire.didactic.tools.jlc.factory.java.lang;

import se.skillytaire.didactic.tools.jlc.api.ComparableTestObjectFactory;
import se.skillytaire.didactic.tools.jlc.api.ImmutableType;
import se.skillytaire.didactic.tools.jlc.api.JLCTestFactory;
import se.skillytaire.didactic.tools.jlc.api.Pooled;

@ImmutableType
@JLCTestFactory(path = "java/lang")
public class IntegerTestObjectFactory implements ComparableTestObjectFactory<Integer> {
	@Pooled
	@Override
	public Integer createThis() {
		return 7;
	}
	@Pooled
	@Override
	public Integer createThat() {
		return 13;
	}

	@Override
	public boolean isTypeFor(Class<?> type) {
		return Integer.class == type || Integer.TYPE == type;
	}
	public Class<?>[] types() {
		return new Class[] { type(), Integer.TYPE };
	}
	@Override
	public Class<Integer> type() {
		return Integer.class;
	}

	@Override
	public Integer createLessThen() {
		return Integer.MIN_VALUE;
	}

	@Override
	public Integer createGreaterThen() {
		return Integer.MAX_VALUE;
	}

}
