package se.skillytaire.didactic.tools.jlc.spi.internal;

import se.skillytaire.didactic.tools.jlc.api.AbstractTestObjectFactory;
import se.skillytaire.didactic.tools.jlc.api.ImmutableType;
@ImmutableType
public class ObjectTestObjectFactory extends AbstractTestObjectFactory<Object> {

   @Override
   public Object createThis() {
      return new Object();
   }

   @Override
   public Object createThat() {
      return new Object();
   }

}
