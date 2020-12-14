package se.skillytaire.didactic.tools.jlc.method.api;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.junit.jupiter.api.DisplayName;

import se.skillytaire.didactic.tools.jlc.api.JLC;



@Repeatable(TestMethods.class)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Target(ElementType.TYPE)

public @interface TestMethod {
   

   int NOT_CONFIGURED = -1;
   String name();
   
   Class<?>[] parameters() default {};
   
   boolean enabled() default true;
   /**
    * When invert is true, then the framework will check if this method is not declared.
    * @return
    */
   boolean invert() default false;
   /**
    * Get the return type of the method. Defaults to 'void'.
    * @return the return type of the method
    */
   Class<?> returnType() default void.class;
   
   /** Add an extra feature to the display */
   String feature() default "";
   /** Display a short version of the signature, defaults to true, so not showing the fqmn (Full Qualified Method Name) */
   boolean shortSignature() default true;
   /**
    * Allows you to add the method to an archetype.
    * @return
    */
   String archetype() default "";
   /**
    * The maximum parameter count for this method.
    * @return The maximum parameter count. 
    */
   int parameterCount() default TestMethod.NOT_CONFIGURED;
   /**
    * The expected exception for design by contract when using a null reference for the parameters.
    * (Note: private methods expect assertion / AssertionError)
    * @return
    */
   Class<? extends Exception> dbc() default IllegalArgumentException.class;
   /**
    * Is this signature an API declared method. For instance {@code equals(Object)} is declared by Java, so specific tests like parameter count are not included.
    * @return
    */
   boolean api() default false;
   
	/**
	 * Set the display name for the 'method'-node. Defaults to 'the actual signature'.
	 * @return the display name for the 'method'-node.
	 * @see #METHODS
	 */	
	DisplayName displayName() default @DisplayName(JLC.EMPTY);
}
