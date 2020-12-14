package se.skillytaire.didactic.tools.jlc.spi.internal;

import se.skillytaire.didactic.tools.jlc.api.ComparableTestObjectFactory;
import se.skillytaire.didactic.tools.jlc.api.ImmutableType;
@ImmutableType
public class IntegerTestObjectFactory implements ComparableTestObjectFactory<Integer> {

   @Override
   public Integer createThis() {
      return 7;
   }

   @Override
   public Integer createThat() {
      return 13;
   }

   @Override
   public boolean isTypeFor(Class<?> type) {
      return Integer.class == type || Integer.TYPE == type;
   }

   @Override
   public Class<Integer> type() {
      return Integer.class;
   }

   @Override
   public Integer createLessThen() {
      return Integer.MIN_VALUE;
   }

   @Override
   public Integer createGreaterThen() {
      return Integer.MAX_VALUE;
   }

}
