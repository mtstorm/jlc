package se.skillytaire.didactic.tools.jlc.constructor.spi.util;

import java.lang.reflect.Constructor;
import java.util.Optional;

import se.skillytaire.didactic.tools.jlc.signature.spi.ConstructorSignature;

public class ConstructorTool {
   
   private ConstructorTool() {
      throw new AssertionError("No instances for you");
   }
	public static <T> Optional<Constructor<T>> findConstructor(Class<T> beanUnderTestType, ConstructorSignature<T> signature) {
		Constructor<T> result;
		try {
			result = beanUnderTestType.getDeclaredConstructor(signature.getParameterTypes());
		} catch (NoSuchMethodException | SecurityException e) {
			result = null;
		}
		return Optional.ofNullable(result);
	}

}
