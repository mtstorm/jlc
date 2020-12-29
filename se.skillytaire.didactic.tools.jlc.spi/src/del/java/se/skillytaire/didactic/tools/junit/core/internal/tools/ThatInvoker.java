//package se.skillytaire.didactic.tools.junit.core.internal.tools;
//
//import se.skillytaire.didactic.tools.junit.core.api.TestObjectFactories;
//import se.skillytaire.didactic.tools.junit.core.api.TestObjectFactory;
//import se.skillytaire.didactic.tools.junit.core.api.That;
//
//public final class ThatInvoker<T> extends AnnotatedInvoker<That, T, TestObjectFactory<T>>{
//
//	public ThatInvoker(TestObjectFactory<T> override) {
//		super(That.class, override);
//	}
//	public ThatInvoker() {
//		super(That.class);
//	}
//	@SuppressWarnings("unchecked")
//	public T doCreate(Class<?> type) {
//		return (T) TestObjectFactories.getThatInstance(type);
//	}
//	@Override
//	protected T createOverride() {
//		return this.getOverride().createThat();
//	}
//
//}
