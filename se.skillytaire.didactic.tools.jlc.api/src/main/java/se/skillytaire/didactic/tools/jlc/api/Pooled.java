package se.skillytaire.didactic.tools.jlc.api;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * JLC provides automatic tests for the test object factories. Sometimes objects
 * are pooled so reference equals or not depends on that. One of the rules is
 * that a test object factory normally resolve different instances that are
 * equals. When object are pooled than this is not the case.
 *
 * When placed on a type, all the factory methods will be marked as pooled. When
 * placed on a method, only that factory method will be marked as pooled.
 */
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Target({ ElementType.METHOD, ElementType.TYPE })
public @interface Pooled {

}
