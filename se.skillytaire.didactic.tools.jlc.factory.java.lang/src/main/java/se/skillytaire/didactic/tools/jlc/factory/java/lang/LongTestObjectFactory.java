package se.skillytaire.didactic.tools.jlc.factory.java.lang;

import se.skillytaire.didactic.tools.jlc.api.ComparableTestObjectFactory;
import se.skillytaire.didactic.tools.jlc.api.ImmutableType;
import se.skillytaire.didactic.tools.jlc.api.JLCTestFactory;
import se.skillytaire.didactic.tools.jlc.api.Pooled;

@ImmutableType
@JLCTestFactory(path = "java/lang")
public class LongTestObjectFactory implements ComparableTestObjectFactory<Long> {
	@Pooled
	@Override
	public Long createThis() {
		return 7L;
	}
	@Pooled
	@Override
	public Long createThat() {
		return 13L;
	}


	public Class<?>[] types() {
		return new Class[] { type(), Long.TYPE };
	}
	@Override
	public Class<Long> type() {
		return Long.class;
	}

	@Override
	public Long createLessThen() {
		return Long.MIN_VALUE;
	}

	@Override
	public Long createGreaterThen() {
		return Long.MAX_VALUE;
	}

}
