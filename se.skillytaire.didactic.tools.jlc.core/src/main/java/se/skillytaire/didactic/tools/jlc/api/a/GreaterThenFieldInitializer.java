package se.skillytaire.didactic.tools.jlc.api.a;

import java.lang.reflect.Field;

import se.skillytaire.didactic.tools.jlc.api.ComparableTestObjectFactory;
import se.skillytaire.didactic.tools.jlc.api.GreaterThen;
import se.skillytaire.didactic.tools.jlc.api.LessThen;
import se.skillytaire.didactic.tools.jlc.api.TestObjectFactory;
import se.skillytaire.didactic.tools.jlc.api.That;

public final class GreaterThenFieldInitializer extends AutoFieldInitializer<GreaterThen>{

	public GreaterThenFieldInitializer(Field field) {
		super(field);
	}

	@Override
	protected Object invoke(TestObjectFactory<?> factory) {
		return ((ComparableTestObjectFactory<?>) factory).createGreaterThen();
	}



}
