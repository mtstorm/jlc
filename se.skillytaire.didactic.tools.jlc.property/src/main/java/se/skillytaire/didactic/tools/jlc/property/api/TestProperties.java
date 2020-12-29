/**
 * 
 */
package se.skillytaire.didactic.tools.jlc.property.api;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.junit.jupiter.api.DisplayName;

import se.skillytaire.didactic.tools.jlc.api.TestOrder;

/**
 * This will mark your test to run property tests.
 * @author Skillytaire
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Target(ElementType.TYPE)
public @interface TestProperties {
	public static final String PROPERTIES = "Properties";
	public static final String OPTIONAL = "Optional";
	public static final String REQUIRED = "Required";
	public static final boolean GROUP = true;
	   /**
	    * Set the display name for the 'properties'-node . Defaults to 'Properties'.
	    * @return the display name for all the 'properties'-node.
	    * @see #PROPERTIES
	    */	
	   
	DisplayName displayName() default @DisplayName(PROPERTIES);
	TestProperty[] value() default {};  
	TestOrder order() default @TestOrder();
	boolean enabled() default true;
	/**
	 * When set to true, the properties will be grouped in required and optional properties.
	 * 
	 * @return
	 */
	boolean grouping() default GROUP;
	
	DisplayName optionalGroupName() default @DisplayName(OPTIONAL);
	DisplayName requiredGroupName() default @DisplayName(REQUIRED);
}
