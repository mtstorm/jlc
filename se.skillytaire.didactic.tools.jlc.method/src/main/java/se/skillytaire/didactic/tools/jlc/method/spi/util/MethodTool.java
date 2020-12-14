package se.skillytaire.didactic.tools.jlc.method.spi.util;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.Predicate;
import java.util.logging.Logger;
import java.util.stream.Stream;

import se.skillytaire.didactic.tools.jlc.method.spi.model.config.TestMethodConfiguration;
import se.skillytaire.didactic.tools.jlc.signature.spi.MethodSignature;
import se.skillytaire.didactic.tools.jlc.spi.model.config.JLCConfiguration;

public class MethodTool {
   private static final Logger log = Logger.getLogger(MethodTool.class.getName());
	private MethodTool() {
		throw new AssertionError("No instance for you");
	}

	public static Method findMethodByName(Class<?> type, String methodName) throws NoSuchMethodException {
		Method method = null;
		Method[] declaredMethods = type.getDeclaredMethods();
		for (Method declaredMethod : declaredMethods) {
			if (declaredMethod.getName().equals(methodName)) {
				method = declaredMethod;
				break;
			}
		}
		if (method == null) {
			if (type == Object.class) {
				throw new NoSuchMethodException(methodName);
			}
			method = findMethodByName(type.getSuperclass(), methodName);
		}

		return method;
	}

	private static void allInterfaces(Class<?> type, Set<Class<?>> interfaceCollector) {
		int mod = type.getModifiers();
		if (Modifier.isInterface(mod)) {
			interfaceCollector.add(type);
		}
		Class<?>[] interfaces = type.getInterfaces();
		for (Class<?> class1 : interfaces) {
			allInterfaces(class1, interfaceCollector);
		}
		Class<?> parent = type.getSuperclass();
		if (parent != null) {
			allInterfaces(parent, interfaceCollector);
		}
	}

	/**
	 * Get all the methods to test for a specific type. It will not include the
	 * following methods since they are native of Java
	 * 
	 * @see MethodSignature#FINALIZE will not be included.
	 * @see MethodSignature#GET_CLASS will not be included.
	 * @see MethodSignature#NOTIFY will not be included.
	 * @see MethodSignature#NOTIFY_ALL will not be included.
	 * @see MethodSignature#WAIT will not be included.
	 * @see MethodSignature#WAIT_TIMEOUT will not be included.
	 * @see MethodSignature#WAIT_TIMEOUT_2 will not be included.
	 * @param type
	 * @return
	 */
	public static <T> Stream<TestMethodConfiguration<T>> getConcreteMethods(JLCConfiguration<T> parentConfig,Class<?> type) {

		TreeSet<TestMethodConfiguration<T>> collector = new TreeSet<>();
		inheritedDeclaredMethods(parentConfig,type, collector);
		return collector.stream();
	}

	private static <T> void inheritedDeclaredMethods(JLCConfiguration<T> parentConfig, Class<?> type, Set<TestMethodConfiguration<T>> collector) {
		Method[] methods = type.getDeclaredMethods();
		for (Method method : methods) {
			MethodSignature signature = new MethodSignature(method);
			log.config(()-> signature.toString());
			TestMethodConfiguration<T> conf = new TestMethodConfiguration<>(parentConfig,signature);
			if(!collector.contains(conf)) {
				collector.add(conf);
				conf.setExecutor(method);
			}
		}
		if(type != Object.class) {
			Class<?> parentType = type.getSuperclass();
			inheritedDeclaredMethods(parentConfig, parentType, collector);
		}
	}

//	/**
//	 * Get the concrete methods declared by the type (filtering all the interface
//	 * methods).
//	 * 
//	 * @param type
//	 * @return
//	 */
//	public static <T> Stream<TestMethodConfiguration<T>> getConcreteMethodsX(Class<?> type) {
//		Method[] m = type.getDeclaredMethods();
//
////      return Arrays.stream(m)
////            .filter(method -> !Modifier.isPrivate(method.getModifiers()))
////            .filter(method -> !Modifier.isAbstract(method.getModifiers()))
////            .map(MethodSignature::new);
//
//		List<MethodSignature> interfaceMethods = new ArrayList<>();
//		HashSet<Class<?>> superInterfaces = new HashSet<>();
//		allInterfaces(type, superInterfaces);
//		Class<?>[] interfaces = new Class<?>[superInterfaces.size()];
//		interfaces = superInterfaces.toArray(interfaces);
//		for (Class<?> interfaze : interfaces) {
//			System.out.println("interface -> " + interfaze.getName());
//			Method[] interfacem = interfaze.getDeclaredMethods();
//			for (Method im : interfacem) {
//				MethodSignature s = new MethodSignature(im);
//				// interfaceMethods.add(s);
//
//			}
//		}
//		List<TestMethodConfiguration<T>> concreteMethods = new ArrayList<>();
//		for (Method candidate : m) {
//
//			MethodSignature s = new MethodSignature(candidate);
//			if (!interfaceMethods.contains(s)) {
//				TestMethodConfiguration<T> conf = new TestMethodConfiguration<>(s);
//				conf.setMethod(candidate);
//				concreteMethods.add(conf);
//			}
//		}
//		return concreteMethods.stream();
//	}

	public static Optional<Method> findMethod(Class<?> type, Predicate<Method> predicate) {
		
		Method method = null;
		Method[] declaredMethods = type.getDeclaredMethods();
		for (Method declaredMethod : declaredMethods) {
			if (predicate.test(declaredMethod)) {
				method = declaredMethod;
				break;
			}
		}
		if (method == null) {
			if (type != Object.class) {
				Optional<Method> result = findMethod(type.getSuperclass(), predicate);
				if(result.isPresent()) {
					method = result.get();
				}
			}

		}
		return Optional.ofNullable(method);
	}
}
