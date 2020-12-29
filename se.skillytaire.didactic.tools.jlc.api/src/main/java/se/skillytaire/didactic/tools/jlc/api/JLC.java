package se.skillytaire.didactic.tools.jlc.api;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.junit.jupiter.api.extension.ExtendWith;

/**
 * The JLC builder builds your test.
 * 
 * @author Skillytaire AB
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Target(ElementType.TYPE)
@ExtendWith(JLCTestBuilder.class)
public @interface JLC {

	String EMPTY = "";
	Class<?> DEFAULT_VALUE = Void.class;
	/**
	 * When declared the system will use this test factory for your bean under test.
	 * It must match the {@link JLC#value()} for it's type.
	 * 
	 * @return Default to VoidTestObjectFactory.class
	 */
	Class<? extends TestObjectFactory<?>> testFactory() default VoidTestObjectFactory.class;

	/**
	 * Allows you to add additional test factory classes. These will become a
	 * registry of it's own. Predefined registries can also be used.
	 * 
	 * @return
	 */

	Class<? extends TestObjectFactory<?>>[] registry() default {};

	/**
	 * Allows you to add one or more registries of TestObjectFactories.
	 * 
	 * @return
	 */
	Class<? extends TestObjectFactoryRegistry>[] registries() default {};

	/**
	 * When set to true, the annotated fields in your test will be initialized and
	 * the dependencies in other factories.
	 * 
	 * @return
	 */
	boolean autoInject() default true;

	/**
	 * When you do not specify the testFactory, it will try to resolve the test
	 * factory based on the value. It will auto lookup.
	 * 
	 */
	boolean autoLookUpTestFactory() default true;

	/**
	 * The class to test, only required when using the builder of JLC.
	 * 
	 * @return
	 */
	Class<?> value() default Void.class;

	/**
	 * Display the TID (Test IDentifier)
	 * 
	 * @return
	 */
	boolean displayTid() default false;

	/**
	 * Enables the best practice tests.
	 * 
	 * @return run the best practice tests.
	 */
	boolean bestPractices() default true;

	/**
	 * Hide composite empty tests.
	 * 
	 * @return
	 */
	boolean showEmptyTests() default false;

	/**
	 * The order of the feature nodes in the ui.
	 * 
	 * @return
	 */
	TestOrder order() default @TestOrder();

	/**
	 * When there is no value for featured annotation, the system will auto scan the
	 * feature. When there are value, only the specified feature values will be
	 * used, and auto scan is disabled.
	 * 
	 * When merge is enabled the system will auto scan the features, and will update
	 * the features having the configured feature values.
	 * 
	 * @return
	 */
	boolean merge() default false;

	/**
	 * When group is enabled all the features will enable grouping to ALL.
	 * 
	 * @return default to false;
	 */
	boolean group() default false;

}
