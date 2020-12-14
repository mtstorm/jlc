package se.skillytaire.didactic.tools.jlc.spi.internal;

import se.skillytaire.didactic.tools.jlc.api.ImmutableType;
import se.skillytaire.didactic.tools.jlc.api.TestObjectFactory;
@ImmutableType
public class VoidTestObjectFactory implements TestObjectFactory<Void>{

   @Override
   public Void createThis() {
      return null;
   }

   @Override
   public Void createThat() {
      return null;
   }

   @Override
   public boolean isTypeFor(Class<?> type) {
      return Void.class == type || Void.TYPE == type;
   }

   @Override
   public Class<Void> type() {
      return  Void.class;
   }

}
