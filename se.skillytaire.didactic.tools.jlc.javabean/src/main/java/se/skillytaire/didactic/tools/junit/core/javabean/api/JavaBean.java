/**
 * 
 */
package se.skillytaire.didactic.tools.junit.core.javabean.api;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import se.skillytaire.didactic.tools.jlc.lint.api.Lint;
import se.skillytaire.didactic.tools.jlc.lint.api.Lints;

/**
 * This will mark your test to run the JavaBean basic tests.
 * It is a standalone annotation and is the same as:
 * <code>
 * @Lints( 
 *    {
 *       @Lint(archetype = "JavaBean")
 *    }
 * )
 *  
 * </code>
 * @author Skillytaire
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Target(ElementType.TYPE)
public @interface JavaBean {
	JavaBeanProperty[] value() default {};   
}
