package se.skillytaire.didactic.tools.jlc.spi.internal;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Comparator;
import java.util.Optional;

import se.skillytaire.didactic.tools.jlc.api.TestDisplayOrder;
import se.skillytaire.didactic.tools.jlc.api.TestOrder;
import se.skillytaire.didactic.tools.jlc.api.TestOrderDescription;

public class ConfigurationTool {

	private ConfigurationTool() {
		throw new AssertionError("No instance here");
	}
	private static final String ATTRIBUTE_TEST_ORDER = "order";
	
	public static <A extends Annotation, T extends Comparable<T>> TestOrderDescription getSort(Class<?> testClass, Class<? extends Annotation> annotationClass) {
		TestOrderDescription result;
		Optional<TestOrder> optionalTestOrder = 
				getConfiguredTestAnnotationValue(testClass,annotationClass,
				ATTRIBUTE_TEST_ORDER);
		
		if(optionalTestOrder.isPresent()) {
			TestOrder testOrderConfiguration = optionalTestOrder.get();
			result = new TestOrderDescription(testOrderConfiguration);
		} else {
			result = new TestOrderDescription();
		}
		return result;
	}
	
	public static <A extends Annotation, T extends Comparable<T>> Optional<Comparator<T>> doIt(Class<?> testClass, Class<? extends Annotation> annotationClass) {
		Comparator<T> comparator = null;
		TestOrderDescription result = getSort(testClass, annotationClass);

		TestDisplayOrder sortType = result.getDisplayOrder();
		switch (sortType ) {
		case Natural:
			 comparator = (a,b) -> a.compareTo(b);
			 if(result.isReversed()) {
				 comparator = comparator.reversed();
			 }
			break;

		default:
			break;
		}
		 
		return Optional.ofNullable(comparator);
	}
	
	
	
	@SuppressWarnings("unchecked")
	public  static final <V> Optional<V> getConfiguredTestAnnotationValue(Class<?> testClass,  Class<? extends Annotation> annotationClass,
			String methodName) {
		V value = null;
		Annotation annotation = testClass.getAnnotation(annotationClass);
		if (annotation != null) {
			Method method;
			try {
				method = annotation.annotationType().getDeclaredMethod(methodName);
				value = (V)method.invoke(annotation);
			} catch (Exception e) {
				String msg = String.format("There is no '%s' method found for annotation type %s.",methodName, annotationClass.getName());
				throw new IllegalStateException(msg, e);
			}
		}
		return Optional.ofNullable(value);
	}
}
