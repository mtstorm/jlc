package se.skillytaire.didactic.tools.jlc.spi.internal.invoke;

import se.skillytaire.didactic.tools.jlc.spi.internal.JLCConfigurationImpl;

public interface Invoker<T>{
	T create(JLCConfigurationImpl<?> configuration, Class<?> clazz);
}
