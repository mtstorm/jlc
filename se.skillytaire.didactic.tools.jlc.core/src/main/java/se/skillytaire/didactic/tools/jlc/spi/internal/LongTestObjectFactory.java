package se.skillytaire.didactic.tools.jlc.spi.internal;

import se.skillytaire.didactic.tools.jlc.api.ComparableTestObjectFactory;
import se.skillytaire.didactic.tools.jlc.api.ImmutableType;
@ImmutableType
public class LongTestObjectFactory implements ComparableTestObjectFactory<Long> {

   @Override
   public Long createThis() {
      return 7L;
   }

   @Override
   public Long createThat() {
      return 13L;
   }

   @Override
   public boolean isTypeFor(Class<?> type) {
      return Long.class == type || Long.TYPE == type;
   }

   @Override
   public Class<Long> type() {
      return Long.class;
   }

   @Override
   public Long createLessThen() {
      return Long.MIN_VALUE;
   }

   @Override
   public Long createGreaterThen() {
      return Long.MAX_VALUE;
   }

}
