package se.skillytaire.didactic.tools.jlc.spi.internal.invoke;

import java.lang.reflect.Field;

import se.skillytaire.didactic.tools.jlc.spi.internal.JLCConfigurationImpl;
@Deprecated
public class InstanceFieldInitializer {
	private Field field;
	private Object instance;
	private Invoker<?> invoker;
	private JLCConfigurationImpl<?> configuration;


	InstanceFieldInitializer() {
	}

	public void invoke() {
		field.setAccessible(true);
		try {
			Object value = invoker.create(configuration, field.getType());
			field.set(instance, value);
		} catch (IllegalArgumentException | IllegalAccessException e) {
			String msg = String.format("Unable to set the instance field '%s' in instance of class '%s'",
					field.getName(), instance.getClass().getName());
			throw new AssertionError(msg, e);
		}
	}
//
	public static FieldBuilder forInstance(Object instance) {
		return new InstanceFieldBuilderImpl(instance);
	}

	// public Field getField() {
	public void setConfiguration(JLCConfigurationImpl<Object> configuration) {
		this.configuration = configuration;
	}
    void setField(Field field) {
		this.field = field;
	}
	void setInstance(Object instance) {
		this.instance = instance;
	}
	void setInvoker(Invoker<?> invoker) {
		this.invoker = invoker;
	}

}
