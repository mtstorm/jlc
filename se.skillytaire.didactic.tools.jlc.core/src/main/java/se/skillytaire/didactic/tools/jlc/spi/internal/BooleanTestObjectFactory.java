package se.skillytaire.didactic.tools.jlc.spi.internal;

import java.util.function.Predicate;

import se.skillytaire.didactic.tools.jlc.api.ImmutableType;
import se.skillytaire.didactic.tools.jlc.api.TestObjectFactory;
@ImmutableType
public class BooleanTestObjectFactory implements TestObjectFactory<Boolean> {
   public static final Predicate<Class<?>> BOOLEAN_PRIMITIVE_TYPE = c-> Boolean.TYPE == c;
   public static final Predicate<Class<?>> BOOLEAN_WRAPPER_TYPE = c->  Boolean.class == c;
   public static final Predicate<Class<?>> BOOLEAN_TYPE = BOOLEAN_PRIMITIVE_TYPE.or(BOOLEAN_WRAPPER_TYPE);
   @Override
   public Boolean createThis() {
      return true;
   }

   @Override
   public Boolean createThat() {
      return false;
   }

   @Override
   public boolean isTypeFor(Class<?> type) {
      return BOOLEAN_TYPE.test(type);
   }

   @Override
   public Class<Boolean> type() {
      return Boolean.class;
   }

}
