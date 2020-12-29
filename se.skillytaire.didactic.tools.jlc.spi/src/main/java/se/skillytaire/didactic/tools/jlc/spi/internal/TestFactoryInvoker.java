package se.skillytaire.didactic.tools.jlc.spi.internal;

import java.lang.reflect.Method;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.function.Supplier;

import se.skillytaire.didactic.tools.jlc.api.Pooled;
import se.skillytaire.didactic.tools.jlc.api.TestObjectFactory;

public abstract class TestFactoryInvoker <F extends TestObjectFactory<?>> implements Supplier<Object>{ //implements Function<TestObjectFactory<Object> , Object>{
	private final F factory;
	F getFactory() {
		return factory;
	}
	protected TestFactoryInvoker(F factory) {
		if(factory == null) throw new IllegalArgumentException("factory is void");
		this.factory = factory;
	}
	@Override
	public final Object get() {
		return invoke(factory);
	}	
	
	protected abstract Object invoke(F factory) ;
	protected abstract String getMessageName();
	
	public URI toUri() {
		StringBuilder builder = new StringBuilder();
		String uri = builder.append("method:").append(qualifiedName()).toString();
		URI result = null;
		try {
			result = new URI(uri);
		} catch (URISyntaxException e) {
			throw new AssertionError(e);
		}
		return result;
	}
	private String qualifiedName() {
		StringBuilder builder = new StringBuilder();
		builder.append(this.factory.getClass().getName());
		builder.append('#');
		builder.append(getMessageName());
		builder.append("()");
		return builder.toString();
	}

	public final boolean isNotPooled() {
		return !this.isPooled();
	}
	public final boolean isPooled() {
		boolean isPooled;
		try {
			Method declaredMethod = this.factory.getClass().getDeclaredMethod(getMessageName());
			isPooled = declaredMethod.isAnnotationPresent(Pooled.class);
		} catch (NoSuchMethodException | SecurityException e) {
			isPooled = false;
		}
		return isPooled;
	}
}
