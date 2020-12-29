package se.skillytaire.didactic.tools.jlc.spi.model.config;

import java.lang.reflect.Field;

import se.skillytaire.didactic.tools.jlc.api.ComparableTestObjectFactory;
import se.skillytaire.didactic.tools.jlc.api.LessThen;
import se.skillytaire.didactic.tools.jlc.api.TestObjectFactory;

public final class LessThenFieldInitializer extends AutoFieldInitializer<LessThen>{

	public LessThenFieldInitializer(Field field) {
		super(field);
	}

	@Override
	protected Object invoke(TestObjectFactory<?> factory) {
		return ((ComparableTestObjectFactory<?>) factory).createLessThen();
	}



}
