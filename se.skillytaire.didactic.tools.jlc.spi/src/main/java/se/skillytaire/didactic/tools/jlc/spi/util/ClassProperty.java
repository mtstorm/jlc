package se.skillytaire.didactic.tools.jlc.spi.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public final class ClassProperty {

    private final Method setMethod;
    private final Method getMethod;
    private Method hasMethod;
    private final Field field;


    public ClassProperty(Class<?> target, Field field) {

       this.field = field;

       String fieldName = field.getName();
       Class<?> fieldType = field.getType();
       char firstChar = fieldName.charAt(0);
       String fieldUp = Character.toUpperCase(firstChar)
             + fieldName.substring(1, fieldName.length());
       String setMethodName =
             new StringBuilder("set").append(fieldUp).toString();
       String hasMethodName =
             new StringBuilder("has").append(fieldUp).toString();
       String getMethodName;
       if (fieldType == boolean.class) {
          getMethodName = new StringBuilder("is").append(fieldUp).toString();
       } else {
          getMethodName = new StringBuilder("get").append(fieldUp).toString();
       }

       Method possibleSetMethod;

       try {
          possibleSetMethod = target.getMethod(setMethodName, fieldType);
          if (possibleSetMethod.getReturnType() != void.class) {
             throw new AssertionError("set method " + setMethodName
                   + " has not a correct return type, must be void");
          }

       } catch (NoSuchMethodException | SecurityException e) {
          possibleSetMethod = null;
       }
       this.setMethod = possibleSetMethod;

       Method possibleGetMethod;
       try {
          possibleGetMethod = target.getMethod(getMethodName);
          if (possibleGetMethod.getReturnType() != fieldType) {
             // covairant return type niet meegenomen nog
             possibleGetMethod = null;
          }
       } catch (NoSuchMethodException | SecurityException e) {
          possibleGetMethod = null;
       }
       this.getMethod = possibleGetMethod;

       Method possibleHasMethod;
       try {
          possibleHasMethod = target.getMethod(hasMethodName);
          setHasMethod(possibleHasMethod);
       } catch (NoSuchMethodException | SecurityException e) {
          possibleHasMethod = null;
       }

    }

    public Field getField() {
       return field;
    }
    public void setHasMethod(Method method) {
       if(method == null) {
          throw new IllegalArgumentException("has method is void");
       }
       if(method.getParameterCount() != 0) {
          throw new IllegalArgumentException("has method may not hava parameters");
       }
       if (method.getReturnType() != boolean.class) {
          throw new IllegalArgumentException("has method must have a boolean return type");
       } 
       this.hasMethod = method;
    }
    
    public boolean isWriteOnly() {
       return this.getMethod == null && hasSetMethod();
    }

    public boolean isReadOnly() {
       return !hasSetMethod() && this.getMethod != null;
    }

    public boolean hasSetMethod() {
       return this.setMethod != null;
    }

    public boolean hasGetMethod() {
       return this.getMethod != null;
    }

    public void set(Object instance, Object ding)
          throws IllegalAccessException, InvocationTargetException {
       if (hasSetMethod()) {
          // setten bij method
          this.setMethod.setAccessible(true);
        
          this.setMethod.invoke(instance, ding);
        
       } else {
          // set by field
          field.setAccessible(true);
          field.set(instance, ding);
       }
    }

    public Object get(Object instance)
          throws InvocationTargetException, IllegalAccessException {
       Object returnValue;
       if (hasGetMethod()) {
          this.getMethod.setAccessible(true);
          returnValue = this.getMethod.invoke(instance);
       } else {
          field.setAccessible(true);
          returnValue = field.get(instance);
       }
       return returnValue;
    }

    public boolean has(Object instance) throws InvocationTargetException, IllegalAccessException {
       boolean returnValue;
       if (hasHasMethod()) {
          this.hasMethod.setAccessible(true);
          returnValue = (boolean) this.hasMethod.invoke(instance);
       } else {
          field.setAccessible(true);
          Object instanceValue = field.get(instance);
          returnValue = instanceValue != null;
       }  
       return returnValue;
    }
    public String getName() {
       return this.field.getName();
    }   
    
    //private static Logger log = Logger.getLogger(GetSetRunner.class.getName());
//    /**
//     * optional is using validation or the identity. Id is optional also
//     * 
//     * @return
//     */
//    public boolean isOptional() {
//       return OptionalPropertyValidator.isOptional(this);
//    }
//    public boolean isNotOptionalByValidationFramework() {
//       return this.field.isAnnotationPresent(NotNull.class); 
//    }
    public Method getGetMethod() {
       return getMethod;
    }

    public Method getSetMethod() {
       return setMethod;
    }
    
    public boolean hasHasMethod() {
       return this.hasMethod != null;
    }
    public Method getHasMethod() {
       return this.hasMethod;
    }
    public Class<?> getType(){
       return this.field.getType();
    }
//FIXME bad design    
//    /**
//     * Uses the SPI to set the value.
//     * 
//     * @return
//     * @throws InvocationTargetException
//     * @throws IllegalAccessException
//     */
//    public Object invokeSetMethod(Object beanInstance, JLCConfiguration<?> config)
//          throws IllegalAccessException, InvocationTargetException {
//       Class<?> type = this.field.getType();
//       Object ding = config.getThisInstance(type);
//       this.set(beanInstance, ding);
//       return ding;
//    }

    public boolean isPrimitive() {
       return getType().isPrimitive();
    }

 }
 