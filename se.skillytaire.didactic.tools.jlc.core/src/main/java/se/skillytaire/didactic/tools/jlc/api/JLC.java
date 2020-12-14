package se.skillytaire.didactic.tools.jlc.api;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.junit.jupiter.api.extension.ExtendWith;

import se.skillytaire.didactic.tools.jlc.spi.internal.VoidTestObjectFactory;
/**
 * The JLC builder uses a specific folder structure for the tests.
 * This annotation allows you to change the folder structures names.
 * 
 * <ul>
 * 	<li>
 * 		'jlc'-node {@code Lint#name()}
 * 			<ul>
 * 				<li>'types'-node {@code Lint#types()}</li>
 * 				<li>'fields'-node {@code Lint#fields()}</li>
 * 				<li>'constructors'-node {@code Lint#constructors()}</li>
 * 				<li>'methods'-node {@code Lint#methods()}</li>
 * 			</ul>
 * 	</li>
 * </ul>
 * @author Skillytaire AB
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Target(ElementType.TYPE)
@ExtendWith(JLCTestBuilder.class)
public @interface JLC {
	
	
	String EMPTY = "";


   /**
	 * When declared the system will use this test factory and will skip the service provider interface.
	 * @return
	 */
	Class<? extends TestObjectFactory<?>> testFactory() default VoidTestObjectFactory.class;
	/**
	 * The class to test.
	 * 
	 * @return
	 */
	Class<?> value();

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
	 * @return
	 */
	boolean showEmptyTests() default false;
	
	
   /**
    * The order of the feature nodes in the ui.
    * @return
    */
   TestOrder order() default @TestOrder();
	
   /**
    * When there is no value for featured annotation, the system will auto scan the feature.
    * When there are value, only the specified feature values will be used, and auto scan is disabled.
    * 
    * When merge is enabled the system will auto scan the features, and will update the features having the configured feature values.
    * 
    * @return
    */
    boolean merge() default false;
    
    /**
     * When group is enabled all the features will enable grouping to ALL.
     * @return default to false;
     */
    boolean group() default false;
 	
}
