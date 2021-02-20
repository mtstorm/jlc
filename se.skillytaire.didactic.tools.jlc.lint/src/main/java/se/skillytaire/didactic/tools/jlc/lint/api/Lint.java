package se.skillytaire.didactic.tools.jlc.lint.api;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.junit.jupiter.api.DisplayName;

import se.skillytaire.didactic.tools.jlc.api.JLC;

/**
 * When a lint is detected it uses a specific folder structure for the tests.
 * This annotation allows you to change the folder structures names.
 * 
 * <ul>
 * 	<li>
 * 		'lint'-node {@code Lint#name()}
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
@Repeatable(Lints.class)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Target(ElementType.TYPE)
public @interface Lint {
	boolean enabled() default true;
	/**
	 * Set the display name for the 'lint'-node.
	 * @return the display name for the 'lint'-node. 
	 * @see JLC#EMPTY
	 */	
	DisplayName displayName() default @DisplayName(JLC.EMPTY);
	
	/**
	 * The name of the archetype of the linter to configure.
	 * @return
	 */
	String archetype();
	
}
