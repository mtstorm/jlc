package se.skillytaire.didactic.tools.junit.core.javabean.api;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Repeatable(JavaBean.class)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Target(ElementType.TYPE)
public @interface JavaBeanProperty {
	/**
	 * The name of the property, must match the name of the field.
	 * @return the name of the property
	 */
   String name();

   /**
    * The name of the method that marks the property optional.
    * @return the name of the method has{$name}
    */
   String optionalMethod() default "";
   
   boolean readOnly() default false;
   
   //FIXME model van de java bean model bekijken  indexed property, collection property, derived property
   

}
