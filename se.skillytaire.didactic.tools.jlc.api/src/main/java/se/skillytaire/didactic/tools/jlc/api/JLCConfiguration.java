package se.skillytaire.didactic.tools.jlc.api;

import java.lang.annotation.Annotation;
import java.util.Optional;
import java.util.ServiceLoader;
import java.util.stream.Stream;

import org.junit.jupiter.api.DynamicNode;

public interface JLCConfiguration<T> {
	Object getTestInstance();
	/**
	 * Handles the auto wirering for the annotations.
	 * @see This
	 * @see That
	 * @see LessThen
	 * @see GreaterThen
	 */
	void autowire();
	/**
	 * Streams the tests.
	 * @return
	 */
	Stream<DynamicNode> stream();

	/**
	 * Tries to resolve an test object factory for the {@value type}.
	 * @param type The type to look for
	 * @return a possible test object factory.
	 */
	<F extends TestObjectFactory<E>, E> Optional<F> resolveFactory(Class<?> type);
	
	
	/**
	 * Get all the test object factories that are configured.
	 * @return
	 */
	Stream<TestObjectFactory<?>> factories();
	/**
	 * Get the instance for the configuration having a test instance.
	 * @param testInstance
	 * @return
	 * @throws JLCConfigurationException when there is no configuration provider found.
	 */

	public static JLCConfiguration<Object> of(Object testInstance) throws JLCConfigurationException {
		Optional<JLCConfigurationFactory> factory = ServiceLoader.load(JLCConfigurationFactory.class).findFirst();
		if(factory.isEmpty()) {
			throw new JLCConfigurationException("No configuration factory found in your enviroment.");
		}
		return factory.get().create(testInstance);
	}

	/**
	 * Get the possible annotation that is declared in your test instance.
	 * @param <A>
	 * @param annotationClass
	 * @return
	 */
	public <A extends Annotation> Optional<A> getTestAnnotation(Class<A> annotationClass);
	/**
	 * Get the possible annotation that is declared in your bean under test class.
	 * @param <A>
	 * @param annotationClass
	 * @return
	 */
	public <A extends Annotation> Optional<A> getBeanUnderTestAnnotationx(Class<A> annotationClass);
	/**
	 * Checks if the {@value type} is immutable.
	 * @param type
	 * @return
	 */
	boolean isImmutableType(Class<T> type);
	
	<E> E getThisInstance(Class<?> type);
	<E> E getThatInstance(Class<?> type);
	<E> E getLessThenInstance(Class<?> type);
	<E> E getGreaterThenInstance(Class<?> type);
	boolean hasObjectFactory();
	TestObjectFactory<T> getObjectFactory();
	/**
	 * Checks if the user has configured the value in JLC
	 * @return 
	 * @see JLC#value()
	 */
	boolean hasBeanUnderTestType();
	Class<T> getBeanUnderTestType();
	boolean isMerge();


	TestOrderDescription getOrder();

	boolean isShowEmpyTests();
	
	<N extends TestConfiguration<N, T>> void register(ArchetypedCollection<T, N> extensionConfiguration,
			Class<? extends TestConfiguration<N, T>> key);

	
	boolean containsConfiguration(Class<? extends TestConfiguration<?, T>> key);
	
	<N extends TestConfiguration<N, T>> ArchetypedCollection<T, N> getExtensionConfiguration(Class<?> key);
	
	default TestConfiguration<?, T> enfoce(Object configurable, Archetype archetype) {
		return Enforcer.enforce(this, configurable, archetype);
	}
}
