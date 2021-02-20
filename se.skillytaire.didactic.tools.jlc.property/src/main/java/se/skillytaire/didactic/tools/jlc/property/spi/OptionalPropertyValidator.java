package se.skillytaire.didactic.tools.jlc.property.spi;

import java.lang.reflect.Field;
import java.util.ServiceLoader;
import java.util.logging.Level;
import java.util.logging.Logger;

import se.skillytaire.didactic.tools.jlc.api.TestObjectFactory;
import se.skillytaire.didactic.tools.jlc.spi.ext.field.PropertyType;
import se.skillytaire.didactic.tools.jlc.spi.util.ClassProperty;

/**
 * A field type validator determines the type of a field.
 * @author prolector
 *
 */
//@FunctionalInterface 
public interface OptionalPropertyValidator {
//   Logger log = Logger.getLogger(TestObjectFactory.class.getName());
   /**
    * Checks what kind of field it is.
    * @param field
    * @return
    */
   PropertyType getType(ClassProperty property);
   
   static boolean isOptional(ClassProperty property) {
      ClassLoader loader = Thread.currentThread()
            .getContextClassLoader();
      ServiceLoader<OptionalPropertyValidator> serviceLoader =
            ServiceLoader.load(OptionalPropertyValidator.class, loader);
      
      boolean isOptional = false;
      for (OptionalPropertyValidator optionalFieldValidator : serviceLoader) {
    	  PropertyType type = optionalFieldValidator.getType(property);
         if(type.equals(PropertyType.Optional) ) {
            isOptional = true;
            break;
         }
      }
      return isOptional;

   }
}
