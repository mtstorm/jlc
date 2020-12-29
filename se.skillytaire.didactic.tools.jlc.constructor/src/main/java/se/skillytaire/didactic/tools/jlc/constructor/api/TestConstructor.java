package se.skillytaire.didactic.tools.jlc.constructor.api;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.junit.jupiter.api.DisplayName;

import se.skillytaire.didactic.tools.jlc.api.JLC;



@Repeatable(TestConstructors.class)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Target(ElementType.TYPE)

public @interface TestConstructor {
   
   int NOT_CONFIGURED = -1;
   
   Class<?>[] parameters() default {};
   
   boolean enabled() default true;
   /**
    * When invert is true, then the framework will check if this method is not declared.
    * @return
    */
   boolean invert() default false;
   
   /** Add an extra feature to the display.
    * Remember to enable the {@link TestConstructors#grouping()}
    * having {@link TestConstructorGroup#Feature} or {@link TestConstructorGroup#All}.
    */
   String feature() default "";
   /** Display a short version of the signature, defaults to true, so not showing the fqmn (Full Qualified Method Name) */
   boolean shortSignature() default true;
   /**
    * Allows you to add the constructor to an archetype.
    * Remember to enable the {@link TestConstructors#grouping()}
    * having {@link TestConstructorGroup#Archetype} or {@link TestConstructorGroup#All}.
    * @return
    */
   String archetype() default "";
   /**
    * The maximum parameter count for this method.
    * @return The maximum parameter count. 
    */
   int parameterCount() default TestConstructor.NOT_CONFIGURED;
   /**
    * The expected exception for design by contract when using a null reference for the parameters.
    * (Note: private methods expect assertion / AssertionError)
    * @return
    */
   Class<? extends Exception> dbc() default IllegalArgumentException.class;
   
   boolean disableDBC() default false;
   
	/**
	 * Set the display name for the 'method'-node. Defaults to 'the actual signature'.
	 * @return the display name for the 'method'-node.
	 * @see #CONSTRUCTORS
	 */	
	DisplayName displayName() default @DisplayName(JLC.EMPTY);
	
	
}
