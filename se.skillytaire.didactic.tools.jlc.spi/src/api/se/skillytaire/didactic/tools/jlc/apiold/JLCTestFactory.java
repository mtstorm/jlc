package se.skillytaire.didactic.tools.jlc.apiold;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.junit.jupiter.api.DisplayName;

import se.skillytaire.didactic.tools.jlc.api.JLC;

@Retention(RetentionPolicy.RUNTIME)
@Documented
@Target(ElementType.TYPE)
public @interface JLCTestFactory {
	/**
	 * The JLC tests for factories can be organized having a path. The root is an empty.
	 * Use absolute paths for this, for example {@linkplain /java/lang} of {@linkplain /model}
	 * @return
	 */
	String path() default JLC.EMPTY;
	
	/**
	 * Set the display name for the for this factory. Defaults to 'the name of the class'.
	 * @return the display name 
	 * @see 
	 */	
	DisplayName displayName() default @DisplayName(JLC.EMPTY);
	/**
	 * Should this factory be used, defaults to true.
	 * @return
	 */
	boolean enabled() default true;
}
