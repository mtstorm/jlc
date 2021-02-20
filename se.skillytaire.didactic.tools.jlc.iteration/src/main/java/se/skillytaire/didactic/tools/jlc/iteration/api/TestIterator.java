package se.skillytaire.didactic.tools.jlc.iteration.api;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.ConcurrentModificationException;

/**
 * This will mark your test to run the Iterator basic tests. It is a standalone
 * annotation and is the same as: <code>
 * &#64;Lints( 
 *    {
 *       &#64;Lint(archetype = "Iterator")
 *    }
 * )
 * When your class undertest implements Iterator, this will be automaticly be enabled.
 * </code>
 * @author prolector
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Target(ElementType.TYPE)
public @interface TestIterator {
   /**
    * Checks if the iterator supports remove actions on the iterator.
    * 
    * @return
    */
   boolean remove() default false;

   /**
    * Iterators are <i>fail-fast</i> iterators when the underlying iterable is
    * modified during iteration.
    * 
    * @return
    */
   boolean isFailFast() default true;

   /**
    * The expected runtime exception for the remove message, when the iterable is
    * modified during iteration.
    * 
    * @return
    */
   Class<? extends RuntimeException> failFastException() default ConcurrentModificationException.class;
}
