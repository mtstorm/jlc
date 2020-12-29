package se.skillytaire.didactic.tools.jlc.spi.model.config;

import java.lang.reflect.Field;

import se.skillytaire.didactic.tools.jlc.api.TestObjectFactory;
import se.skillytaire.didactic.tools.jlc.api.That;

public final class ThatFieldInitializer extends AutoFieldInitializer<That>{

	public ThatFieldInitializer(Field field) {
		super(field);
	}

	@Override
	protected Object invoke(TestObjectFactory<?> factory) {
		return factory.createThat();
	}



}
