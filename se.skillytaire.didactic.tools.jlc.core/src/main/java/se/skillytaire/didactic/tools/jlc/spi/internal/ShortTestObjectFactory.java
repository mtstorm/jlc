package se.skillytaire.didactic.tools.jlc.spi.internal;

import se.skillytaire.didactic.tools.jlc.api.ComparableTestObjectFactory;
import se.skillytaire.didactic.tools.jlc.api.ImmutableType;
@ImmutableType
public class ShortTestObjectFactory implements ComparableTestObjectFactory<Short> {

   @Override
   public Short createThis() {
      return 7;
   }

   @Override
   public Short createThat() {
      return 13;
   }

   @Override
   public boolean isTypeFor(Class<?> type) {
      return Short.class == type || Short.TYPE == type;
   }

   @Override
   public Class<Short> type() {
      return Short.class;
   }

   @Override
   public Short createLessThen() {
      return Short.MIN_VALUE;
   }

   @Override
   public Short createGreaterThen() {
      return Short.MAX_VALUE;
   }

}
