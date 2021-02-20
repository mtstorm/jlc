package se.skillytaire.didactic.tools.jlc.lint.api;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.junit.jupiter.api.DisplayName;

import se.skillytaire.didactic.tools.jlc.api.JLC;
import se.skillytaire.didactic.tools.jlc.api.TestOrder;
/**
 * When linters are detected it uses a specific folder structure for the tests.
 * This annotation allows you to change the folder structures names.
 * 
 * <ul>
 * 	<li>
 * 		'linters'-node {@code Lint#name()}
 * 			<ul>
 * 				<li>'types'-node {@code Lint#types()}</li>
 * 				<li>'fields'-node {@code Lint#fields()}</li>
 * 				<li>'constructors'-node {@code Lint#constructors()}</li>
 * 				<li>'methods'-node {@code Lint#methods()}</li>
 * 			</ul>
 * 	</li>
 * </ul>
 * @author Skillytaire AB
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Target(ElementType.TYPE)
public @interface Lints {
	public static final String LINTERS = "Linters";

	boolean enabled() default true;
	/**
	 * Set the display name for the 'linters'-node (the root). 
	 * When not set, the default name  'Linters' is used.
	 * @return The display name for the 'linters'-node.
	 * @see #LINTERS
	 */
	DisplayName displayName() default @DisplayName(JLC.EMPTY);

	
	/**
	 * @return
	 */
	boolean merge() default JLC.MERGE;

	/**
	 * All the lint configurations
	 * @return
	 */
	Lint[] value() default {};
	
   /**
    * The order of lints in the ui.
    * @return
    */
   TestOrder order() default @TestOrder();
}
