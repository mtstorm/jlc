//package se.skillytaire.didactic.tools.junit.core.internal.tools;
//
//import se.skillytaire.didactic.tools.junit.core.api.TestObjectFactories;
//import se.skillytaire.didactic.tools.junit.core.api.TestObjectFactory;
//import se.skillytaire.didactic.tools.junit.core.api.This;
//
//public final class ThisInvoker<T> extends AnnotatedInvoker<This, T, TestObjectFactory<T>>{
//	
//	public ThisInvoker(TestObjectFactory<T> override) {
//		super(This.class, override);
//	}
//
//	public ThisInvoker() {
//		super(This.class);
//	}
//	@SuppressWarnings("unchecked")
//	public T doCreate(Class<?> type) {
//		return (T) TestObjectFactories.getThisInstance(type);
//	}
//	@Override
//	protected T createOverride() {
//		return this.getOverride().createThis();
//	}
//
//}
