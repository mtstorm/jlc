package se.skillytaire.didactic.tools.jlc.constructor.spi.util;

import se.skillytaire.didactic.tools.jlc.constructor.api.TestConstructor;
import se.skillytaire.didactic.tools.jlc.signature.spi.ConstructorSignature;

public class TestConstructorAnnotationTool {
	private TestConstructorAnnotationTool() {
		throw new AssertionError("No instances for you");
	}

	public static <T> ConstructorSignature<T> of(Class<T> beanUnderTest, TestConstructor testConstructor) {
		return new ConstructorSignature<T>(beanUnderTest, testConstructor.parameters());
	}

	public static <T> boolean matches(ConstructorSignature<T> signature, String name, TestConstructor obj) {
		boolean equals = false;
		if (obj != null) {
			if (signature.getName().equals(name)) {
				if (signature.sameParameterSequence(obj.parameters())) {
					equals = true;
				}
			}
		}
		return equals;
	}

}
