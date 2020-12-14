//package se.skillytaire.didactic.tools.junit.core.internal.tools;
//
//import java.lang.reflect.Field;
//
//import se.skillytaire.didactic.tools.junit.core.builder.TestObjectFactoryException;
//
//public final class InstanceFieldInitializer {
//	private Field field;
//	private Object instance;
//	private Invoker<?> invoker;
//
//	private InstanceFieldInitializer() {
//	}
//
//	
//	public void invoke() throws TestObjectFactoryException {
//		field.setAccessible(true);
//		try {
//			Object value = invoker.create(field.getType());
//			field.set(instance, value);
//		} catch (IllegalArgumentException | IllegalAccessException e) {
//			String msg = String.format("Unable to set the instance field '%s' in instance of class '%s'",
//					field.getName(), instance.getClass().getName());
//			throw new TestObjectFactoryException(msg, e);
//		}
//	}
//
//	public static FieldBuilder forInstance(Object instance) {
//		return new InstanceFieldBuilderImpl(instance);
//	}
//
//	public static interface FieldBuilder {
//		InitializerBuilder using(Field field);
//	}
//
//	public static interface InitializerBuilder {
//		InstanceFieldInitializer initialize(Invoker<?> invoker);
//	}
//
//	public static interface InstanceFieldBuilder extends FieldBuilder, InitializerBuilder {
//
//	}
//
//	public static class InstanceFieldBuilderImpl implements InstanceFieldBuilder {
//		private InstanceFieldInitializer product = new InstanceFieldInitializer();
//
//		public InstanceFieldBuilderImpl(Object instance) {
//			if (instance == null) {
//				throw new IllegalArgumentException("instance of void");
//			}
//			product.setInstance(instance);
//		}
//
//		@Override
//		public InitializerBuilder using(Field field) {
//			if (field == null) {
//				throw new IllegalArgumentException("field of void");
//			}
//			product.setField(field);
//			return this;
//		}
//
//		@Override
//		public InstanceFieldInitializer initialize(Invoker<?> invoker) {
//			if (invoker == null) {
//				throw new IllegalArgumentException("invoker of void");
//			}
//			product.setInvoker(invoker);
//			return product;
//		}
//
//
//
//	}
//	// public Field getField() {
//
//	private void setField(Field field) {
//		this.field = field;
//	}
//
//	public Object getInstance() {
//		return instance;
//	}
//
//	private void setInstance(Object instance) {
//		this.instance = instance;
//	}
//
//	public Invoker<?> getInvoker() {
//		return invoker;
//	}
//
//	private void setInvoker(Invoker<?> invoker) {
//		this.invoker = invoker;
//	}
//
//	public static void main(String[] args) {
//		Object instance = null;
//		Field field = null;
//		Invoker<Object> invoker = null;
//		InstanceFieldInitializer.forInstance(null).using(field).initialize(invoker );
//	}
//}
