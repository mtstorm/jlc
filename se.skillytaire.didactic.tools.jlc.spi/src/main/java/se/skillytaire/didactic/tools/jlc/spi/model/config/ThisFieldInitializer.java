package se.skillytaire.didactic.tools.jlc.spi.model.config;

import java.lang.reflect.Field;

import se.skillytaire.didactic.tools.jlc.api.TestObjectFactory;
import se.skillytaire.didactic.tools.jlc.api.This;

public final class ThisFieldInitializer extends AutoFieldInitializer<This>{

	public ThisFieldInitializer(Field field) {
		super(field);
	}

	@Override
	protected Object invoke(TestObjectFactory<?> factory) {
		return factory.createThis();
	}



}
