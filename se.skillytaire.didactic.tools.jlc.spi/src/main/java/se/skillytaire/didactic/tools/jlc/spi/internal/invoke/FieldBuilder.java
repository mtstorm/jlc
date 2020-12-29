package se.skillytaire.didactic.tools.jlc.spi.internal.invoke;

import java.lang.reflect.Field;

public interface FieldBuilder {
	InitializerBuilder using(Field field);
}
