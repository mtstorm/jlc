package se.skillytaire.didactic.tools.jlc.factory.java.lang;

import se.skillytaire.didactic.tools.jlc.api.ComparableTestObjectFactory;
import se.skillytaire.didactic.tools.jlc.api.ImmutableType;
import se.skillytaire.didactic.tools.jlc.api.JLCTestFactory;

@ImmutableType
@JLCTestFactory(path = "java/lang")
public class DoubleTestObjectFactory implements ComparableTestObjectFactory<Double> {

	@Override
	public Double createThis() {
		return 7.77D;
	}

	@Override
	public Double createThat() {
		return 13.13D;
	}


	public Class<?>[] types() {
		return new Class[] { type(), Double.TYPE };
	}
	@Override
	public Class<Double> type() {
		return Double.class;
	}

	@Override
	public Double createLessThen() {
		return Double.MIN_VALUE;
	}

	@Override
	public Double createGreaterThen() {
		return Double.MAX_VALUE;
	}

}
