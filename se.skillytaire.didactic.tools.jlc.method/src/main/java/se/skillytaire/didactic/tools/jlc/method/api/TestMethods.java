package se.skillytaire.didactic.tools.jlc.method.api;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.junit.jupiter.api.DisplayName;

import se.skillytaire.didactic.tools.jlc.api.TestOrder;

@Retention(RetentionPolicy.RUNTIME)
@Documented
@Target(ElementType.TYPE)

public @interface TestMethods {
	/**
	 * Methods starting with '$' or '_' will be excluded. 
	 */
	public static final String DEFAULT_EXCLUDE_PATTERN = "[$|_].*";
	/**
	 * 
	 */
   public static final int DEFAULT_PARAM_COUNT = 2;
   
   public static final String METHODS = "Methods";
   /**
    * When there are no test methods declared, 
    * all the declared messages will be resolved.
    * @return
    */
   TestMethod[] value() default {};
   
   String excludePattern() default  DEFAULT_EXCLUDE_PATTERN ;

	/**
	 * Set the display name for the 'methods'-node . Defaults to 'Methods'.
	 * @return the display name for all the 'methods'-node.
	 * @see #METHODS
	 */	

   DisplayName displayName() default @DisplayName(METHODS);
   /**
    * The order of methods in the ui.
    * @return
    */
   TestOrder order() default @TestOrder();
   
   
   int parameterCount() default DEFAULT_PARAM_COUNT;
   
   boolean enabled() default true;
   
   /**
    * When enabled we try to group the methods.
    * 
    * When overloaded and API is enabled, the overload grouping takes precedence over the api.
    * 
    * @return
    */
   TestMethodGroup[] grouping() default {};
   
   
   /**
    * When there is no value the system will auto scan the methods.
    * When there are value only the specified test methods will be used, and auto scan is disabled.
    * 
    * When merge is enabled the system will auto scan the methods, and will update the signatures having the configured testmethods.
    * 
    * @return
    */
   boolean merge() default false;
}
