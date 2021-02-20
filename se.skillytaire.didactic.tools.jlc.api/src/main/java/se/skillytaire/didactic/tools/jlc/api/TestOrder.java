package se.skillytaire.didactic.tools.jlc.api;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Documented
@Target(ElementType.TYPE)
@Deprecated
public @interface TestOrder {

	/**
	 * The order of the child nodes.
	 *
	 * @return default to compiler order
	 */
	TestDisplayOrder sort() default TestDisplayOrder.NONE;

	/**
	 * Should the sequence be inversed.
	 *
	 * @return default to false
	 */
	boolean inverse() default false;

}
