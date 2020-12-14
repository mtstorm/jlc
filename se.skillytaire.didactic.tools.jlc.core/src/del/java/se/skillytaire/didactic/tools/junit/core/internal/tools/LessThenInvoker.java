//package se.skillytaire.didactic.tools.junit.core.internal.tools;
//
//import se.skillytaire.didactic.tools.junit.core.api.ComparableTestObjectFactory;
//import se.skillytaire.didactic.tools.junit.core.api.LessThen;
//import se.skillytaire.didactic.tools.junit.core.api.TestObjectFactories;
//
//public final class LessThenInvoker<T> extends AnnotatedInvoker<LessThen, T,ComparableTestObjectFactory<T>>{
//	
//	public LessThenInvoker(ComparableTestObjectFactory<T> override) {
//		super(LessThen.class, override);
//	}
//	public LessThenInvoker() {
//		super(LessThen.class);
//	}
//	@SuppressWarnings("unchecked")
//	public T doCreate(Class<?> type) {
//		return (T) TestObjectFactories.getLessThenInstance(type);
//	}
//	@Override
//	protected T createOverride() {
//		return this.getOverride().createLessThen();
//	}
//
//}
