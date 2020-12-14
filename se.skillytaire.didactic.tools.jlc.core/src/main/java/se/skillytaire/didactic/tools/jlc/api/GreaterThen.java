package se.skillytaire.didactic.tools.jlc.api;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/**
 * Injects the instance from the factory-method
 * {@code ComparableTestObjectFactory#createGreaterThen()} before a custom test has run.
 * 
 * @author Skillytaire AB
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Target(ElementType.FIELD)

public @interface GreaterThen {

}
