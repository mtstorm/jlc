package se.skillytaire.didactic.tools.jlc.spi.internal.invoke;

import se.skillytaire.didactic.tools.jlc.spi.model.config.JLCConfiguration;

public interface Invoker<T>{
	T create(JLCConfiguration<?> configuration, Class<?> clazz);
}
