package se.skillytaire.didactic.tools.jlc.method.api;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.junit.jupiter.api.DisplayName;

import se.skillytaire.didactic.tools.jlc.api.TestGroup;
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
   /**
    * The default value for {@link #simpleName()} is {@value true}.
    */
   public static final boolean SIMPLE_NAME = true;
   
   /**
    * The default value for {@link #merge()} is {@value false}.
    */
   public static final boolean MERGE = false;
   public static final String METHODS = "Methods";
   /**
    * When there are no test methods declared, 
    * all the declared messages will be resolved.
    * @return
    */
   TestMethod[] value() default {};
   /**
    * Set the display name for the 'methods'-node . Defaults to 'Methods'.
    * @return the display name for all the 'methods'-node.
    * @see #METHODS
    */	
   
   DisplayName displayName() default @DisplayName(METHODS);
   
   String excludePattern() default  DEFAULT_EXCLUDE_PATTERN ;

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
   TestGroup[] grouping() default {};
   
   /**
    * Should the display name for the constructor signature be simple or qualified.
    * Meaning should the name of the constructor and the possible types of the parameter be simple of qualified.
    * @return
    */
   boolean simpleName() default SIMPLE_NAME;
   
   /**
    * When there is no value the system will auto scan the methods.
    * When there are value only the specified test methods will be used, and auto scan is disabled.
    * 
    * When merge is enabled the system will auto scan the methods, and will update the signatures having the configured testmethods.
    * 
    * @return
    */
   boolean merge() default MERGE;
}
