package se.skillytaire.didactic.tools.jlc.spi.internal.invoke;

import se.skillytaire.didactic.tools.jlc.api.ComparableTestObjectFactory;
import se.skillytaire.didactic.tools.jlc.api.GreaterThen;
import se.skillytaire.didactic.tools.jlc.spi.internal.JLCConfigurationImpl;

public final class GreaterThenInvoker<T> extends AnnotatedInvoker<GreaterThen, T, ComparableTestObjectFactory<T>> {
	public GreaterThenInvoker(ComparableTestObjectFactory<T> override) {
		super(GreaterThen.class, override);
	}

	public GreaterThenInvoker() {
		super(GreaterThen.class);
	}

	public T doCreate(JLCConfigurationImpl<?> configuration, Class<?> type) {
		return configuration.getGreaterThenInstance(type);
	}

	@Override
	protected T createOverride() {
		return this.getOverride().createGreaterThen();
	}

}
