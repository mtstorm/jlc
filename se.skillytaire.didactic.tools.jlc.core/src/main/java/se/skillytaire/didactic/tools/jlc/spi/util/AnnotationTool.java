package se.skillytaire.didactic.tools.jlc.spi.util;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AnnotationTool {
	static final Logger log = Logger.getLogger(AnnotationTool.class.getName());

	private AnnotationTool() {
		throw new AssertionError("No instances for you");
	}
	@SuppressWarnings("unchecked")
	public static <T> Optional<T> getValue(Annotation annotation, String methodName) {
		T result;
		Method method;
		try {
			method = annotation.annotationType().getDeclaredMethod(methodName);
			result = (T) method.invoke(annotation);
		} catch (Exception e) {
		   log.log(Level.SEVERE, String.format("Error getting the value of %s from annotation %s", methodName, annotation),e);
			result = null;
		}
		return Optional.ofNullable(result);
	}
}
