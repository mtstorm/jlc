package se.skillytaire.didactic.tools.jlc.spi.ext.field;

import java.lang.reflect.Field;
import java.util.ServiceLoader;
import java.util.logging.Level;
import java.util.logging.Logger;

import se.skillytaire.didactic.tools.jlc.api.TestObjectFactory;

/**
 * A field type validator determines the type of a field.
 * @author prolector
 *
 */
@FunctionalInterface
public interface FieldTypeValidator {
//   Logger log = Logger.getLogger(TestObjectFactory.class.getName());
   /**
    * Checks what kind of field it is.
    * @param field
    * @return
    */
   FieldType getType(Field field);
   
   static boolean isOptional(Field field) {
      ClassLoader loader = Thread.currentThread()
            .getContextClassLoader();
      ServiceLoader<FieldTypeValidator> serviceLoader =
            ServiceLoader.load(FieldTypeValidator.class, loader);
      
      boolean isOptional = true;
      for (FieldTypeValidator optionalFieldValidator : serviceLoader) {
//         log.log(Level.FINE, String
//               .format("The OptionalFieldValidator '%s' has been found", pptionalFieldValidator
//                     .getClass().getName()));
         if(!optionalFieldValidator.getType(field).equals(FieldType.Required) ) {
            isOptional = false;
            break;
         }
      }
      return isOptional;

   }
}
