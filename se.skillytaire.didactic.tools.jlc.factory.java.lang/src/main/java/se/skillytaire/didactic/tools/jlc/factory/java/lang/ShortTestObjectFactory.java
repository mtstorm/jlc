package se.skillytaire.didactic.tools.jlc.factory.java.lang;

import se.skillytaire.didactic.tools.jlc.api.ComparableTestObjectFactory;
import se.skillytaire.didactic.tools.jlc.api.ImmutableType;
import se.skillytaire.didactic.tools.jlc.api.JLCTestFactory;
import se.skillytaire.didactic.tools.jlc.api.Pooled;

@ImmutableType
@JLCTestFactory(path = "java/lang")
public class ShortTestObjectFactory implements ComparableTestObjectFactory<Short> {
	@Pooled
	@Override
	public Short createThis() {
		return 7;
	}
	@Pooled
	@Override
	public Short createThat() {
		return 13;
	}

	public Class<?>[] types() {
		return new Class[] { type(), Short.TYPE };
	}
	@Override
	public Class<Short> type() {
		return Short.class;
	}

	@Override
	public Short createLessThen() {
		return Short.MIN_VALUE;
	}

	@Override
	public Short createGreaterThen() {
		return Short.MAX_VALUE;
	}

}
