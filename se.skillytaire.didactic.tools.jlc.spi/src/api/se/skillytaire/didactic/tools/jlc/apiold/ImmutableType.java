package se.skillytaire.didactic.tools.jlc.apiold;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The immutable type marks the class under test as immutable.
 * <p>When an object factory is marked with this annotation then the created objects are considered immutable</p>
 * @author Skillytaire AB
 */
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Target(ElementType.TYPE)

public @interface ImmutableType {
}
