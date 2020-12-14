package se.skillytaire.didactic.tools.jlc.spi.internal;

import se.skillytaire.didactic.tools.jlc.api.ComparableTestObjectFactory;
import se.skillytaire.didactic.tools.jlc.api.ImmutableType;
@ImmutableType
public class ByteTestObjectFactory implements ComparableTestObjectFactory<Byte> {

   @Override
   public Byte createThis() {
      return 7;
   }

   @Override
   public Byte createThat() {
      return 13;
   }

   @Override
   public boolean isTypeFor(Class<?> type) {
      return Byte.class == type || Byte.TYPE == type;
   }

   @Override
   public Class<Byte> type() {
      return Byte.class;
   }

   @Override
   public Byte createLessThen() {
      return Byte.MIN_VALUE;
   }

   @Override
   public Byte createGreaterThen() {
      return Byte.MAX_VALUE;
   }

}
