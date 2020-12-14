package se.skillytaire.didactic.tools.jlc.spi.internal;

import se.skillytaire.didactic.tools.jlc.api.ComparableTestObjectFactory;
import se.skillytaire.didactic.tools.jlc.api.ImmutableType;
@ImmutableType
public class FloatTestObjectFactory implements  ComparableTestObjectFactory<Float> {

   @Override
   public Float createThis() {
      return 7.77F;
   }

   @Override
   public Float createThat() {
      return 13.13F;
   }

   @Override
   public boolean isTypeFor(Class<?> type) {
      return Float.class == type || Float.TYPE == type;
   }

   @Override
   public Class<Float> type() {
      return Float.class;
   }

   @Override
   public Float createLessThen() {
      return Float.MIN_VALUE;
   }

   @Override
   public Float createGreaterThen() {
      return Float.MAX_VALUE;
   }

}
