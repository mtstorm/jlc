package se.skillytaire.didactic.tools.jlc.apiold;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import se.skillytaire.didactic.tools.jlc.api.TestObjectFactory;
import se.skillytaire.didactic.tools.jlc.spi.internal.VoidTestObjectFactory;
/**
 * Injects the instance from the factory-method
 * {@code TestObjectFactory#createThat()} before a custom test has run.
 * 
 * @author Skillytaire
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Target(ElementType.FIELD)

public @interface That {
	Class<? extends TestObjectFactory<?>> override() default VoidTestObjectFactory.class;

}
