package se.skillytaire.didactic.tools.jlc.signature.spi;

import java.lang.reflect.Constructor;

public final class ConstructorSignature<T> extends Signature implements Comparable<ConstructorSignature<T>>{
	public ConstructorSignature(Constructor<T> constructor) {
		this(constructor.getDeclaringClass(),constructor.getParameterTypes());
	}

	public ConstructorSignature(Class<?> declaringClass, Class<?>... paramTypes) {
		super(declaringClass, declaringClass.getSimpleName(), paramTypes);
	}

	@Override
	public int compareTo(ConstructorSignature<T> that) {	
		int compareTo = this.getDeclaredClass().getName().compareTo(this.getDeclaredClass().getName());
		if (compareTo == 0) {
			compareTo = this.getName().compareTo(that.getName());
			if (compareTo == 0) {
				compareTo = compareParameterSequence(that.getParameterTypes());
			}
		}
		return compareTo;
	}


}
