/**
 * 
 */
package se.skillytaire.didactic.tools.junit.core.javabean.api;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * This will mark your test to run the JavaBean basic tests.
 * @author Skillytaire
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Target(ElementType.TYPE)
public @interface JavaBean {
	JavaBeanProperty[] value() default {};   
}
