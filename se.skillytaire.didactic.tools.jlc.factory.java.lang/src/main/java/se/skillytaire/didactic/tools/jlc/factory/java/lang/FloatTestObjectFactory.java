package se.skillytaire.didactic.tools.jlc.factory.java.lang;

import se.skillytaire.didactic.tools.jlc.api.ComparableTestObjectFactory;
import se.skillytaire.didactic.tools.jlc.api.ImmutableType;
import se.skillytaire.didactic.tools.jlc.api.JLCTestFactory;

@ImmutableType
@JLCTestFactory(path = "java/lang")
public class FloatTestObjectFactory implements ComparableTestObjectFactory<Float> {

	@Override
	public Float createThis() {
		return 7.77F;
	}

	@Override
	public Float createThat() {
		return 13.13F;
	}


	public Class<?>[] types() {
		return new Class[] { type(), Float.TYPE };
	}
	@Override
	public Class<Float> type() {
		return Float.class;
	}

	@Override
	public Float createLessThen() {
		return Float.MIN_VALUE;
	}

	@Override
	public Float createGreaterThen() {
		return Float.MAX_VALUE;
	}

}
