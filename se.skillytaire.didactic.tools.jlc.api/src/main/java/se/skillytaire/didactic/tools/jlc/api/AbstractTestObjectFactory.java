package se.skillytaire.didactic.tools.jlc.api;

import java.lang.reflect.ParameterizedType;

public abstract class AbstractTestObjectFactory<T> implements TestObjectFactory<T> {

	private final Class<T> testType;

	@SuppressWarnings("unchecked")
	protected AbstractTestObjectFactory() {
		super();
		this.testType = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}

	@Override
	public final Class<T> type() {
		return this.testType;
	}

}
