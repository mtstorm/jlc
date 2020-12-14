package se.skillytaire.didactic.tools.jlc.spi.internal;

import se.skillytaire.didactic.tools.jlc.api.ComparableTestObjectFactory;
import se.skillytaire.didactic.tools.jlc.api.ImmutableType;
@ImmutableType
public class CharacterTestObjectFactory implements ComparableTestObjectFactory<Character> {

   @Override
   public Character createThis() {
      return 7;
   }

   @Override
   public Character createThat() {
      return 13;
   }

   @Override
   public boolean isTypeFor(Class<?> type) {
      return Character.class == type ||  Character.TYPE == type;
   }

   @Override
   public Class<Character> type() {
      return Character.class;
   }

   @Override
   public Character createLessThen() {
      return Character.MIN_VALUE;
   }

   @Override
   public Character createGreaterThen() {
      return Character.MAX_VALUE;
   }

}
