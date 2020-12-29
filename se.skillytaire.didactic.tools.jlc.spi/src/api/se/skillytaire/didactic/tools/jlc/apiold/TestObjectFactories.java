package se.skillytaire.didactic.tools.jlc.apiold;
//package se.skillytaire.didactic.tools.jlc.api;
//
//import java.lang.annotation.Annotation;
//import java.lang.reflect.Field;
//import java.lang.reflect.InvocationTargetException;
//import java.lang.reflect.Method;
//import java.lang.reflect.Modifier;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//import java.util.ServiceLoader;
//import java.util.logging.Logger;
//import java.util.stream.Collectors;
//import java.util.stream.Stream;
//import java.util.stream.Stream.Builder;
//
//import se.skillytaire.didactic.tools.jlc.spi.ext.field.FieldTypeValidator;
//
//public class TestObjectFactories {
////   private TestObjectFactories() {throw new AssertionError("No instances for you");}
////   @SuppressWarnings("unchecked")
////   public
////   static <F extends TestObjectFactory<T>,T> Optional<F> resolveFactory(Class<T> type) {
////      if (type == null) {
////         throw new IllegalArgumentException("Type is void");
////      }
////
////      F factory = null;
////      ClassLoader loader = Thread.currentThread().getContextClassLoader();
////      @SuppressWarnings("rawtypes")
////      ServiceLoader<TestObjectFactory> serviceLoader = ServiceLoader.load(TestObjectFactory.class);
////      for (TestObjectFactory<?> testObjectFactory : serviceLoader) {
////         if (testObjectFactory.isTypeFor(type)) {
////            factory = (F) testObjectFactory;
////            break;
////         }
////      }
////
////      if (factory == null && type.isArray()) {
////         System.out.println(type);
////         factory = (F) new ComparableTestObjectFactory<T>() {
////
////            private T array(Object element) {
////               Class<?> componentType = type.getComponentType();
////               System.out.println(componentType.isPrimitive());
////
////               T[] r = (T[]) java.lang.reflect.Array.newInstance(type.getComponentType(), 1);
////               r[0] = (T) element;
////               return (T) r;
////            }
////
////            @Override
////            public T createThis() {
////               Class<?> componentType = type.getComponentType();
////               Object element = TestObjectFactories.getThisInstance(componentType);
////               return array(element);
////            }
////
////            @Override
////            public T createThat() {
////               Class<?> componentType = type.getComponentType();
////               Object element = TestObjectFactories.getThatInstance(componentType);
////               return array(element);
////            }
////
////            @Override
////            public Class<T> type() {
////               return type;
////            }
////
////            @Override
////            public boolean isTypeFor(Class<?> aType) {
////               return type == aType;
////            }
////
////            @Override
////            public T createLessThen() {
////               Class<?> componentType = type.getComponentType();
////               Object element = TestObjectFactories.getLessThenInstance(componentType);
////               return array(element);
////            }
////
////            @Override
////            public T createGreaterThen() {
////               Class<?> componentType = type.getComponentType();
////               Object element = TestObjectFactories.getGreaterThenInstance(componentType);
////               return array(element);
////            }
////         };
////
////      }
////      if (factory != null) {
////         TestObjectFactories.setFields(factory, factory.getClass());
////      }
////
////      return Optional.ofNullable(factory);
////
////   }
////   public static <T> boolean isImmutableType(Class<T> type) {
////
////      boolean isImmutableType;
////      Optional<TestObjectFactory<T>> optionalFactory = resolveFactory(type);
////      if (optionalFactory.isPresent()) {
////         TestObjectFactory<T> factory = optionalFactory.get();
////         isImmutableType = factory.getClass().isAnnotationPresent(ImmutableType.class);
////      } else {
////         throw new IllegalStateException("There is no test factory found for the class " + type.getName());
////      }
////      return isImmutableType;
////   }
//
////   public static <T> T getThisInstance(Class<T> type) {
////
////      Optional<TestObjectFactory<T>> resolvedResult = TestObjectFactories.resolveFactory(type);
////      if (!resolvedResult.isPresent()) {
////         throw new IllegalStateException("There is no test factory found for the class " + type.getName());
////      }
////      TestObjectFactory<T> factory = resolvedResult.get();
////      T thisInstance = factory.createThis();
////      if (thisInstance == null) {
////         String message = String.format(
////               "The test factory %s creates a null reference for the method createThis() for the type %s",
////               factory.getClass().getName(), type.getName());
////         throw new IllegalStateException(message);
////      }
////      return thisInstance;
////   }
////
////   public static <T> T getThatInstance(Class<T> type) {
////
////      Optional<TestObjectFactory<T>> resolvedResult = TestObjectFactories.resolveFactory(type);
////      if (!resolvedResult.isPresent()) {
////         throw new IllegalStateException("There is no test factory found for the class " + type.getName());
////      }
////      TestObjectFactory<T> factory = resolvedResult.get();
////      T thatInstance = factory.createThat();
////      if (thatInstance == null) {
////         String message = String.format(
////               "The test factory %s creates a null reference for the method createThat() for the type %s",
////               factory.getClass().getName(), type.getName());
////         throw new IllegalStateException(message);
////      }
////      return thatInstance;
////   }
////
////   public static <T> T getLessThenInstance(Class<T> type) {
////      if (type == null) {
////         throw new IllegalArgumentException("Type is void");
////      }
////      Optional<ComparableTestObjectFactory<T>> resolvedResult = TestObjectFactories.resolveFactory(type);
////      if (!resolvedResult.isPresent()) {
////         throw new IllegalStateException("There is no test factory found for the class " + type.getName());
////      }
////      ComparableTestObjectFactory<T> factory = resolvedResult.get();
////      T thatInstance = factory.createLessThen();
////      if (thatInstance == null) {
////         String message = String.format(
////               "The test factory %s creates a null reference for the method createLessThen() for the type %s",
////               factory.getClass().getName(), type.getName());
////         throw new IllegalStateException(message);
////      }
////      return thatInstance;
////
////   }
////
////   public static <T> T getGreaterThenInstance(Class<T> type) {
////      if (type == null) {
////         throw new IllegalArgumentException("Type is void");
////      }
////      Optional<ComparableTestObjectFactory<T>> resolvedResult = TestObjectFactories.resolveFactory(type);
////      if (!resolvedResult.isPresent()) {
////         throw new IllegalStateException("There is no test factory found for the class " + type.getName());
////      }
////      ComparableTestObjectFactory<T> factory = resolvedResult.get();
////      T thatInstance = factory.createGreaterThen();
////      if (thatInstance == null) {
////         String message = String.format(
////               "The test factory %s creates a null reference for the method createGreaterThen() for the type %s",
////               factory.getClass().getName(), type.getName());
////         throw new IllegalStateException(message);
////      }
////      return thatInstance;
////   }
//
////    static <T> Optional<ComparableTestObjectFactory<T>>  resolveFactory(Class<T> type) {
////       Optional<TestObjectFactory<T>> result = TestObjectFactory.resolveFactory(type);
////       
////       Optional<ComparableTestObjectFactory<T>> newResult;
////       if(result.isPresent()) {
////          ComparableTestObjectFactory<T> c = (ComparableTestObjectFactory<T>) result.get();
////          newResult = Optional.ofNullable(c);
////       } else {
////          newResult = Optional.empty();
////       }
////       return newResult;
////    }
//
//   public static void setFields(Object instance) {
//      setFields(instance, instance.getClass());
//   }
//
//   public static <A extends Annotation> void setAnnotatedFields(Object instance, Class<?> type,
//         AnnotatedInvoker<Annotation, ?, ?> invoker) {
//
//      ClassTool tool = new ClassTool(type);
//
//      tool.fields(invoker.getAnnotation())
//            .map(field -> InstanceFieldInitializer.forInstance(instance).using(field).initialize(invoker))
//            .forEach(InstanceFieldInitializer::invoke);
//
//   }
//
//   public static void setFields(Object instance, Class<?> type, TestObjectFactory<Object> objectFactory) {
//      InstanceTool tool = new InstanceTool(instance, objectFactory);
//      tool.autoInitialize();
//
//      tool.validate();
//
//   }
//
//   public static void setFields(Object instance, Class<?> type) {
//      setFields(instance, type, null);
//   }
//   
//   
//   
//   
////   private static class InstanceTool {
////      private final Object instance;
////      private final TestObjectFactory<Object> objectFactory;
////      private ClassTool ct;
////      
//////      public InstanceTool(Object instance)   {
//////         this(instance, null);
//////      }
////      
////      public InstanceTool(Object instance,TestObjectFactory<Object> defaultObjectFactory) {
////         if(instance == null) {
////            throw new IllegalArgumentException("instance is void");
////         }
////         this.instance = instance;
////         this.objectFactory = defaultObjectFactory;
////         ct= new ClassTool(instance.getClass());
////      }
////
////      public Stream<InstanceFieldInitializer> instanceFieldInitializers(AnnotatedInvoker<?, ?,?> ai){
////            return ct.fields(ai.getAnnotation())
////                    .map( field -> InstanceFieldInitializer
////                        .forInstance(instance)
////                        .using(field)
////                        .initialize(ai));
////      }
////      
////      public void autoInitialize() {
////         Builder<AnnotatedInvoker<?,?,?>> streamBuilder = Stream.builder();
////         
////         streamBuilder.add(new ThisInvoker<Object>(this.objectFactory))
////                   .add(new ThatInvoker<Object>(this.objectFactory));
////         if(this.objectFactory instanceof ComparableTestObjectFactory ) {
////            ComparableTestObjectFactory<Object> compF = (ComparableTestObjectFactory<Object>) this.objectFactory;
////            streamBuilder.add(new LessThenInvoker<Object>(compF))
////                      .add(new GreaterThenInvoker<Object>(compF));         
////         }
////
////         streamBuilder.build()
////                      .flatMap( ai -> instanceFieldInitializers(ai))
////                      .forEach(InstanceFieldInitializer::invoke);
////      }
////
////      public void validate() {
////         if(this.objectFactory != null && !(this.objectFactory instanceof ComparableTestObjectFactory) ) {
////            Builder<Class<? extends Annotation>> streamBuilder = Stream.builder();
////            streamBuilder.add(LessThen.class);
////            streamBuilder.add(GreaterThen.class);
////
////            StringBuilder msg = streamBuilder
////                  .build()
////                  .flatMap( c -> ct.fields(c))
////                  .reduce(new StringBuilder(""), (builder, s) -> new StringBuilder(builder).append("The field '").append(s).append("' has not been initialized. \n\t"),
////                    (builder1, builder2) -> new StringBuilder(builder1).append(builder2));
////            if(msg.length() != 0) {
////               msg.append("Fields have @LessThen and/or @GreaterThen annotations, these are not supported for a non ComparableTestObjectFactory\n");
////               throw new IllegalStateException(msg.toString());
////            }
////
////         }
////         
////         Builder<Class<? extends Annotation>> streamBuilder = Stream.builder();
////         streamBuilder.add(This.class);
////         streamBuilder.add(That.class);
////         streamBuilder.add(LessThen.class);
////         streamBuilder.add(GreaterThen.class);
////         
////         
////         Class<Annotation>[] annotations = streamBuilder.build().toArray(Class[]::new);
////         StringBuilder msg = ct.mutualExclusive(annotations).reduce(new StringBuilder(""), (builder, s) -> new StringBuilder(builder).append("The field '").append(s).append("' may only hava @This or @That or @LessThen or @GreaterThen, but not a combination. \n\t"),
////                 (builder1, builder2) -> new StringBuilder(builder1).append(builder2));
////         if(msg.length() != 0) {
////            //msg.append("Fields have @LessThen and/or @GreaterThen annotations, these are not supported for a non ComparableTestObjectFactory\n");
////            throw new IllegalStateException(msg.toString());
////         }
////      }
////      
////   }
////   
//   
////   private static class ClassTool {
////      private static Logger log = Logger.getLogger(ClassTool.class.getName());
////      private final Class<?> target;
////      private List<Field> instanceFields = new ArrayList<>();
////      private List<Field> allFields = new ArrayList<>();
////      public List<Field> getInstanceFields() {
////         return instanceFields;
////      }
////
////      private List<ClassProperty> properties = new ArrayList<>();
////
////      public ClassTool(Class<?> target) {
////         this.target = target;
////         findInstanceFields(target);
////         findALLFields(target);
////      }
////
////      public List<ClassProperty> getProperties() {
////         return properties;
////      }
////
////      private void findInstanceFields(Class<?> clazz) {
////         Field[] fields = clazz.getDeclaredFields();
////         for (Field field : fields) {
////            int modifiers = field.getModifiers();
////            if (!Modifier.isStatic(modifiers)) {
////               instanceFields.add(field);
////               log.fine("Adding instance field " + field.getName());
////               ClassProperty property = new ClassProperty(this.target, field);
////               properties.add(property);
////            }
////         }
////         if (clazz.getSuperclass() != Object.class) {
////            findInstanceFields(clazz.getSuperclass());
////         }
////      }
////      private void findALLFields(Class<?> clazz) {
////         Field[] fields = clazz.getDeclaredFields();
////         for (Field field : fields) {
////            int modifiers = field.getModifiers();
////            
////            allFields.add(field);
////            log.fine("Adding instance field " + field.getName());
//////            ClassProperty property = new ClassProperty(this.target, field);
//////            properties.add(property);
////
////         }
////         if (clazz.getSuperclass() != Object.class) {
////            findALLFields(clazz.getSuperclass());
////         }
////      }
////      @Deprecated
////      public List<Field> findField(Class<? extends Annotation> annotationClass) {
////         return this.fields(annotationClass)
////               .collect(Collectors.toList());
////      }
////
////      public Stream<Field> fields(Class<? extends Annotation> annotationClass) {
////         return this.getInstanceFields().stream()
////               .filter(field -> field.isAnnotationPresent(annotationClass));
////         
////      }
////      
////
////      
////      public List<Field> findAllFields(
////            Class<? extends Annotation> annotationClass) {
////         return this.allFields.stream()
////               .filter(field -> field.isAnnotationPresent(annotationClass))
////               .collect(Collectors.toList());
////      }
////
////      public boolean isAnnotationPresentForPackage(String packageName) {
////
////         List<Annotation> list = new ArrayList<>();
////         findClassAnnotations(target, list);
////
////         for (ClassProperty property : properties) {
////            Annotation[] fieldAnnotations = property.getField().getAnnotations();
////            for (Annotation annotation : fieldAnnotations) {
////               list.add(annotation);
////            }
////
////            if (property.hasGetMethod()) {
////               Method get = property.getGetMethod();
////               Annotation[] getAnnotations = get.getAnnotations();
////               for (Annotation annotation : getAnnotations) {
////                  list.add(annotation);
////               }
////            }
////            if (property.hasSetMethod()) {
////               Method set = property.getSetMethod();
////               Annotation[] setAnnotations = set.getAnnotations();
////               for (Annotation annotation : setAnnotations) {
////                  list.add(annotation);
////               }
////            }
////         }
////
////         return list.stream()
////               .filter(a -> a.annotationType().getName().startsWith(packageName))
////               .count() > 0;
////      }
////
////      private void findClassAnnotations(Class<?> type, List<Annotation> list) {
////         Annotation[] clazzAnnotations = type.getAnnotations();
////         for (Annotation annotation : clazzAnnotations) {
////            list.add(annotation);
////         }
////         if (type.getSuperclass() != Object.class) {
////            findClassAnnotations(type.getSuperclass(), list);
////         }
////      }
////
////      public Method getToString() {
////         Method m = null;
////         try {
////            m = this.target.getDeclaredMethod("toString");
////         } catch (NoSuchMethodException | SecurityException e) {
////            throw new AssertionError(e);
////         }
////         return m;
////      }
////
////      
////      public final Stream<Field> mutualExclusive(Class<? extends Annotation>[] stream) {
////         Builder<Field> builder = Stream.builder();
////            List<Field> fields = this.getInstanceFields();
////            for (Field field : fields) {
////            int counter = 0;
////            for (Class<? extends Annotation> annotation : stream) {
////               if(field.isAnnotationPresent(annotation)) {
////                  counter++;
////               }
////            }
////            if(counter > 1) {
////               builder.add(field);
////            }
////         }
////            return builder.build();
////      }
////
////
//////      public void applyTestClass(Class<?> aTestClass) {
////   //
//////         if (aTestClass.isAnnotationPresent(JavaBean.class)) {
//////            JavaBean bean = aTestClass.getAnnotation(JavaBean.class);
//////            JavaBeanProperty[] properties = bean.extraInfo();
//////            for (JavaBeanProperty javaBeanProperty : properties) {
//////               String propertyName = javaBeanProperty.name();
//////               if (!javaBeanProperty.optionalMethod().equals("")) {
//////                  Optional<ClassProperty> property = this.properties.stream()
//////                        .filter(p -> p.getName().equals(propertyName))
//////                        .findFirst();
//////                  if (property.isPresent()) {
//////                     String optionalMethodName =
//////                           javaBeanProperty.optionalMethod();
//////                     Method method;
//////                     try {
//////                        method = target.getMethod(optionalMethodName);
//////                        ClassProperty cp = property.get();
//////                        cp.setHasMethod(method);
//////                        log.fine("Property "+ propertyName +" has optional method "+ optionalMethodName + " optional is "+ cp.isOptional());
//////                     } catch (Exception e) {
//////                        throw new AssertionError(String
//////                              .format("Property '%s'  in JavaBean annotation in testclass '%s' has an optional method %s that cannot be found.", propertyName, aTestClass.getName(), optionalMethodName, aTestClass
//////                                    .getName()));
//////                     }
////   //
//////                  } else {
//////                     throw new AssertionError(String
//////                           .format("Property '%s' not found in JavaBean annotation in testclass %s", propertyName, aTestClass
//////                                 .getName()));
//////                  }
//////               }
//////            }
//////         }
//////      }
////
////   }
////   
//   
////   private static class ClassProperty {
////
////      private final Method setMethod;
////      private final Method getMethod;
////      private Method hasMethod;
////      private final Field field;
////
////
////      public ClassProperty(Class<?> target, Field field) {
////
////         this.field = field;
////
////         String fieldName = field.getName();
////         Class<?> fieldType = field.getType();
////         char firstChar = fieldName.charAt(0);
////         String fieldUp = Character.toUpperCase(firstChar)
////               + fieldName.substring(1, fieldName.length());
////         String setMethodName =
////               new StringBuilder("set").append(fieldUp).toString();
////         String hasMethodName =
////               new StringBuilder("has").append(fieldUp).toString();
////         String getMethodName;
////         if (fieldType == boolean.class) {
////            getMethodName = new StringBuilder("is").append(fieldUp).toString();
////         } else {
////            getMethodName = new StringBuilder("get").append(fieldUp).toString();
////         }
////
////         Method possibleSetMethod;
////
////         try {
////            possibleSetMethod = target.getMethod(setMethodName, fieldType);
////            if (possibleSetMethod.getReturnType() != void.class) {
////               throw new AssertionError("set method " + setMethodName
////                     + " has not a correct return type, must be void");
////            }
////
////         } catch (NoSuchMethodException | SecurityException e) {
////            possibleSetMethod = null;
////         }
////         this.setMethod = possibleSetMethod;
////
////         Method possibleGetMethod;
////         try {
////            possibleGetMethod = target.getMethod(getMethodName);
////            if (possibleGetMethod.getReturnType() != fieldType) {
////               // covairant return type niet meegenomen nog
////               possibleGetMethod = null;
////            }
////         } catch (NoSuchMethodException | SecurityException e) {
////            possibleGetMethod = null;
////         }
////         this.getMethod = possibleGetMethod;
////
////         Method possibleHasMethod;
////         try {
////            possibleHasMethod = target.getMethod(hasMethodName);
////            setHasMethod(possibleHasMethod);
////         } catch (NoSuchMethodException | SecurityException e) {
////            possibleHasMethod = null;
////         }
////
////      }
////
////      public Field getField() {
////         return field;
////      }
////      public void setHasMethod(Method method) {
////         if(method == null) {
////            throw new IllegalArgumentException("has method is void");
////         }
////         if(method.getParameterCount() != 0) {
////            throw new IllegalArgumentException("has method may not hava parameters");
////         }
////         if (method.getReturnType() != boolean.class) {
////            throw new IllegalArgumentException("has method must have a boolean return type");
////         } 
////         this.hasMethod = method;
////      }
////      
////      public boolean isWriteOnly() {
////         return this.getMethod == null && hasSetMethod();
////      }
////
////      public boolean isReadOnly() {
////         return !hasSetMethod() && this.getMethod != null;
////      }
////
////      public boolean hasSetMethod() {
////         return this.setMethod != null;
////      }
////
////      public boolean hasGetMethod() {
////         return this.getMethod != null;
////      }
////
////      public void set(Object instance, Object ding)
////            throws IllegalAccessException, InvocationTargetException {
////         if (hasSetMethod()) {
////            // setten bij method
////            this.setMethod.setAccessible(true);
////          
////            this.setMethod.invoke(instance, ding);
////          
////         } else {
////            // set by field
////            field.setAccessible(true);
////            field.set(instance, ding);
////         }
////      }
////
////      public Object get(Object instance)
////            throws InvocationTargetException, IllegalAccessException {
////         Object returnValue;
////         if (hasGetMethod()) {
////            this.getMethod.setAccessible(true);
////            returnValue = this.getMethod.invoke(instance);
////         } else {
////            field.setAccessible(true);
////            returnValue = field.get(instance);
////         }
////         return returnValue;
////      }
////
////      public boolean has(Object instance) throws InvocationTargetException, IllegalAccessException {
////         boolean returnValue;
////         if (hasHasMethod()) {
////            this.hasMethod.setAccessible(true);
////            returnValue = (boolean) this.hasMethod.invoke(instance);
////         } else {
////            field.setAccessible(true);
////            Object instanceValue = field.get(instance);
////            returnValue = instanceValue != null;
////         }  
////         return returnValue;
////      }
////      public String getName() {
////         return this.field.getName();
////      }   
////      
////      //private static Logger log = Logger.getLogger(GetSetRunner.class.getName());
////      /**
////       * optional is using validation or the identity. Id is optional also
////       * 
////       * @return
////       */
////      public boolean isOptional() {
////         return FieldTypeValidator.isOptional(field);
////      }
//////      public boolean isNotOptionalByValidationFramework() {
//////         return this.field.isAnnotationPresent(NotNull.class); 
//////      }
////      public Method getGetMethod() {
////         return getMethod;
////      }
////
////      public Method getSetMethod() {
////         return setMethod;
////      }
////      
////      public boolean hasHasMethod() {
////         return this.hasMethod != null;
////      }
////      public Method getHasMethod() {
////         return this.hasMethod;
////      }
////      public Class<?> getType(){
////         return this.field.getType();
////      }
////      /**
////       * Uses the SPI to set the value.
////       * 
////       * @return
////       * @throws InvocationTargetException
////       * @throws IllegalAccessException
////       */
////      public Object invokeSetMethod(Object beanInstance)
////            throws IllegalAccessException, InvocationTargetException {
////         Class<?> type = this.field.getType();
////         Object ding = TestObjectFactories.getThisInstance(type);
////         this.set(beanInstance, ding);
////         return ding;
////      }
////
////      public boolean isPrimitive() {
////         return getType().isPrimitive();
////      }
////
////   }
////   
//   
//   
////   private static interface Invoker<T> {
////      T create(Class<?> type);
////   }
////   private static abstract class AnnotatedInvoker<A extends Annotation, T, F extends TestObjectFactory<T>> implements Invoker<T> {
////      private final Class<A> annotation;
////
////      private final F override;
////      protected AnnotatedInvoker(Class<A> annotation) {
////         this(annotation, null);
////      }
////      protected AnnotatedInvoker(Class<A> annotation,F override) {
////         if(annotation == null) {
////            throw new IllegalArgumentException("annotation is void");
////         }
////         this.annotation = annotation;
////         this.override = override;
////      }
////      
////      public final Class<A> getAnnotation() {
////         return annotation;
////      }
////
////      public boolean hasOverride() {
////         return this.override != null;
////      }
////      protected F getOverride() {
////         return override;
////      }
////      
////      protected abstract T createOverride();
////      protected abstract T doCreate(Class<?> type);
////      
////      public final T create(Class<?> type) {
////         T result;
////         if(hasOverride() && this.override.isTypeFor(type)) {
////            result = createOverride();
////         }else {
////            result = doCreate(type);
////         }
////         return result;
////      }
////   }
////   private static final class GreaterThenInvoker<T> extends AnnotatedInvoker<GreaterThen, T,ComparableTestObjectFactory<T>>{
////      public GreaterThenInvoker(ComparableTestObjectFactory<T> override) {
////         super(GreaterThen.class, override);
////      }
////      public GreaterThenInvoker() {
////         super(GreaterThen.class);
////      }
////      @SuppressWarnings("unchecked")
////      public T doCreate(Class<?> type) {
////         return (T) TestObjectFactories.getGreaterThenInstance(type);
////      }
////      @Override
////      protected T createOverride() {
////         return this.getOverride().createGreaterThen();
////      }
////
////   }
////   private static final class LessThenInvoker<T> extends AnnotatedInvoker<LessThen, T,ComparableTestObjectFactory<T>>{
////      
////      public LessThenInvoker(ComparableTestObjectFactory<T> override) {
////         super(LessThen.class, override);
////      }
////      public LessThenInvoker() {
////         super(LessThen.class);
////      }
////      @SuppressWarnings("unchecked")
////      public T doCreate(Class<?> type) {
////         return (T) TestObjectFactories.getLessThenInstance(type);
////      }
////      @Override
////      protected T createOverride() {
////         return this.getOverride().createLessThen();
////      }
////
////   }
////   private static final class ThatInvoker<T> extends AnnotatedInvoker<That, T, TestObjectFactory<T>>{
////
////      public ThatInvoker(TestObjectFactory<T> override) {
////         super(That.class, override);
////      }
////      public ThatInvoker() {
////         super(That.class);
////      }
////      @SuppressWarnings("unchecked")
////      public T doCreate(Class<?> type) {
////         return (T) TestObjectFactories.getThatInstance(type);
////      }
////      @Override
////      protected T createOverride() {
////         return this.getOverride().createThat();
////      }
////
////   }
////   private static final class ThisInvoker<T> extends AnnotatedInvoker<This, T, TestObjectFactory<T>>{
////      
////      public ThisInvoker(TestObjectFactory<T> override) {
////         super(This.class, override);
////      }
////
////      public ThisInvoker() {
////         super(This.class);
////      }
////      @SuppressWarnings("unchecked")
////      public T doCreate(Class<?> type) {
////         return (T) TestObjectFactories.getThisInstance(type);
////      }
////      @Override
////      protected T createOverride() {
////         return this.getOverride().createThis();
////      }
////
////   }
//   
////   private static  interface InitializerBuilder {
////      InstanceFieldInitializer initialize(Invoker<?> invoker);
////   }
//   
////   private static interface FieldBuilder {
////      InitializerBuilder using(Field field);
////   }
////   private static final class InstanceFieldInitializer {
////      private Field field;
////      private Object instance;
////      private Invoker<?> invoker;
////
////      private InstanceFieldInitializer() {
////      }
////
////      
////      public void invoke()  {
////         field.setAccessible(true);
////         try {
////            Object value = invoker.create(field.getType());
////            field.set(instance, value);
////         } catch (IllegalArgumentException | IllegalAccessException e) {
////            String msg = String.format("Unable to set the instance field '%s' in instance of class '%s'",
////                  field.getName(), instance.getClass().getName());
////            throw new AssertionError(msg, e);
////         }
////      }
////
////      public static FieldBuilder forInstance(Object instance) {
////         return new InstanceFieldBuilderImpl(instance);
////      }
////
////
////
////
////
////      private interface InstanceFieldBuilder extends FieldBuilder, InitializerBuilder {
////
////      }
////
////      private static final class InstanceFieldBuilderImpl implements InstanceFieldBuilder {
////         private InstanceFieldInitializer product = new InstanceFieldInitializer();
////
////         public InstanceFieldBuilderImpl(Object instance) {
////            if (instance == null) {
////               throw new IllegalArgumentException("instance of void");
////            }
////            product.setInstance(instance);
////         }
////
////         @Override
////         public InitializerBuilder using(Field field) {
////            if (field == null) {
////               throw new IllegalArgumentException("field of void");
////            }
////            product.setField(field);
////            return this;
////         }
////
////         @Override
////         public InstanceFieldInitializer initialize(Invoker<?> invoker) {
////            if (invoker == null) {
////               throw new IllegalArgumentException("invoker of void");
////            }
////            product.setInvoker(invoker);
////            return product;
////         }
////
////
////
////      }
////      // public Field getField() {
////
////      private void setField(Field field) {
////         this.field = field;
////      }
////
//////      public Object getInstance() {
//////         return instance;
//////      }
////
////      private void setInstance(Object instance) {
////         this.instance = instance;
////      }
////
//////      public Invoker<?> getInvoker() {
//////         return invoker;
//////      }
////
////      private void setInvoker(Invoker<?> invoker) {
////         this.invoker = invoker;
////      }
////
////   }
//
//}
