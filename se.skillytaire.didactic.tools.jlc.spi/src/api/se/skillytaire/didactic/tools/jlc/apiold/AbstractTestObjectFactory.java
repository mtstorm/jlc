package se.skillytaire.didactic.tools.jlc.apiold;

import java.lang.reflect.ParameterizedType;

import se.skillytaire.didactic.tools.jlc.api.TestObjectFactory;

public abstract class AbstractTestObjectFactory<T> implements TestObjectFactory<T> {

   private final Class<T> testType;

   @SuppressWarnings("unchecked")
   protected AbstractTestObjectFactory() {
      super();
      this.testType = (Class<T>) ((ParameterizedType) getClass()
            .getGenericSuperclass()).getActualTypeArguments()[0];
   }
   @Override
   public final Class<T> type() {
      return testType;
   }


   @Override
   public final boolean isTypeFor(Class<?> type) {
      return type().equals(type);
   }
   
}
