package se.skillytaire.didactic.tools.jlc.api;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Adds additional tests to JLC to check the preconditions off the
 * configuration.
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Target(ElementType.TYPE)
public @interface JLCConfigurationTest {
	/**
	 * The test factories will be checked, should the layout be package based, or
	 * flat packages.
	 *
	 * @return
	 */
	PackagePresentation layout() default PackagePresentation.FLAT;

	/**
	 * When thepayout is flat, use this separator between the node names. Defaults
	 * '.' for the package separator.
	 *
	 * @return
	 */
	char separator() default '.';
}
