package se.skillytaire.didactic.tools.jlc.spi.internal.invoke;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.Stream.Builder;

public class ClassTool {
    private static Logger log = Logger.getLogger(ClassTool.class.getName());
    private final Class<?> target;
    private List<Field> instanceFields = new ArrayList<>();
    private List<Field> allFields = new ArrayList<>();
    public List<Field> getInstanceFields() {
       return instanceFields;
    }

    private List<ClassProperty> properties = new ArrayList<>();

    public ClassTool(Class<?> target) {
       this.target = target;
       findInstanceFields(target);
       findALLFields(target);
    }

    public List<ClassProperty> getProperties() {
       return properties;
    }

    private void findInstanceFields(Class<?> clazz) {
       Field[] fields = clazz.getDeclaredFields();
       for (Field field : fields) {
          int modifiers = field.getModifiers();
          if (!Modifier.isStatic(modifiers)) {
             instanceFields.add(field);
             log.fine("Adding instance field " + field.getName());
             ClassProperty property = new ClassProperty(this.target, field);
             properties.add(property);
          }
       }
       if (clazz.getSuperclass() != Object.class) {
          findInstanceFields(clazz.getSuperclass());
       }
    }
    private void findALLFields(Class<?> clazz) {
       Field[] fields = clazz.getDeclaredFields();
       for (Field field : fields) {
          int modifiers = field.getModifiers();
          
          allFields.add(field);
          log.fine("Adding instance field " + field.getName());
//          ClassProperty property = new ClassProperty(this.target, field);
//          properties.add(property);

       }
       if (clazz.getSuperclass() != Object.class) {
          findALLFields(clazz.getSuperclass());
       }
    }
    @Deprecated
    public List<Field> findField(Class<? extends Annotation> annotationClass) {
       return this.fields(annotationClass)
             .collect(Collectors.toList());
    }

    public Stream<Field> fields(Class<? extends Annotation> annotationClass) {
       return this.getInstanceFields().stream()
             .filter(field -> field.isAnnotationPresent(annotationClass));
       
    }
    

    
    public List<Field> findAllFields(
          Class<? extends Annotation> annotationClass) {
       return this.allFields.stream()
             .filter(field -> field.isAnnotationPresent(annotationClass))
             .collect(Collectors.toList());
    }

    public boolean isAnnotationPresentForPackage(String packageName) {

       List<Annotation> list = new ArrayList<>();
       findClassAnnotations(target, list);

       for (ClassProperty property : properties) {
          Annotation[] fieldAnnotations = property.getField().getAnnotations();
          for (Annotation annotation : fieldAnnotations) {
             list.add(annotation);
          }

          if (property.hasGetMethod()) {
             Method get = property.getGetMethod();
             Annotation[] getAnnotations = get.getAnnotations();
             for (Annotation annotation : getAnnotations) {
                list.add(annotation);
             }
          }
          if (property.hasSetMethod()) {
             Method set = property.getSetMethod();
             Annotation[] setAnnotations = set.getAnnotations();
             for (Annotation annotation : setAnnotations) {
                list.add(annotation);
             }
          }
       }

       return list.stream()
             .filter(a -> a.annotationType().getName().startsWith(packageName))
             .count() > 0;
    }

    private void findClassAnnotations(Class<?> type, List<Annotation> list) {
       Annotation[] clazzAnnotations = type.getAnnotations();
       for (Annotation annotation : clazzAnnotations) {
          list.add(annotation);
       }
       if (type.getSuperclass() != Object.class) {
          findClassAnnotations(type.getSuperclass(), list);
       }
    }

    public Method getToString() {
       Method m = null;
       try {
          m = this.target.getDeclaredMethod("toString");
       } catch (NoSuchMethodException | SecurityException e) {
          throw new AssertionError(e);
       }
       return m;
    }

    
    public final Stream<Field> mutualExclusive(Class<? extends Annotation>[] stream) {
       Builder<Field> builder = Stream.builder();
          List<Field> fields = this.getInstanceFields();
          for (Field field : fields) {
          int counter = 0;
          for (Class<? extends Annotation> annotation : stream) {
             if(field.isAnnotationPresent(annotation)) {
                counter++;
             }
          }
          if(counter > 1) {
             builder.add(field);
          }
       }
          return builder.build();
    }


 }
 


