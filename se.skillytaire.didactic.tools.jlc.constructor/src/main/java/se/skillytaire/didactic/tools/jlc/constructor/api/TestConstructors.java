package se.skillytaire.didactic.tools.jlc.constructor.api;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.junit.jupiter.api.DisplayName;

import se.skillytaire.didactic.tools.jlc.api.JLC;
import se.skillytaire.didactic.tools.jlc.api.TestGroup;
import se.skillytaire.didactic.tools.jlc.api.TestOrder;



@Retention(RetentionPolicy.RUNTIME)
@Documented
@Target(ElementType.TYPE)
public @interface TestConstructors {
	boolean enabled() default true;
	/**
	 * Set the display name for the 'Constructors'-node . Defaults to 'Constructors'.
	 * @return the display name for all the 'methods'-node.
	 * @see #CONSTRUCTORS
	 */	
	
	DisplayName displayName() default @DisplayName(CONSTRUCTORS);
	/**
	 * @return
	 */
	boolean merge() default JLC.MERGE;


	
	
	
	
	
	
	
	
	
	
	
	/**
	 * 
	 */
   public static final int DEFAULT_PARAM_COUNT = 2;
   
   /**
    * The default value for {@link #simpleName()} is {@value true}.
    */
   public static final boolean SIMPLE_NAME = true;
   /**
    * The default value for {@link #displayName()}.
    */
   public static final String CONSTRUCTORS = "Constructors";
   /**
    * When there are no test constructors declared, 
    * all the declared messages will be resolved.
    * @return
    */
   TestConstructor[] value() default {};
   
   
   
   /**
    * The order of constructors in the ui.
    * @return
    */
   TestOrder order() default @TestOrder();
   
   int parameterCount() default DEFAULT_PARAM_COUNT;
   
   
   TestGroup[] grouping() default {};
   
   /**
    * Should the display name for the constructor signature be simple or qualified.
    * Meaning should the name of the constructor and the possible types of the parameter be simple of qualified.
    * @return
    */
   boolean simpleName() default SIMPLE_NAME;
}
