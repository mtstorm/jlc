package se.skillytaire.didactic.tools.jlc.spi.internal;

import se.skillytaire.didactic.tools.jlc.api.ComparableTestObjectFactory;
import se.skillytaire.didactic.tools.jlc.api.ImmutableType;
@ImmutableType
public class DoubleTestObjectFactory implements  ComparableTestObjectFactory<Double> {

   @Override
   public Double createThis() {
      return 7.77D;
   }

   @Override
   public Double createThat() {
      return 13.13D;
   }

   @Override
   public boolean isTypeFor(Class<?> type) {
      return Double.class == type || Double.TYPE == type;
   }

   @Override
   public Class<Double> type() {
      return Double.class;
   }

   @Override
   public Double createLessThen() {
      return Double.MIN_VALUE;
   }

   @Override
   public Double createGreaterThen() {
      return Double.MAX_VALUE;
   }

}
