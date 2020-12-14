package se.skillytaire.didactic.tools.jlc.spi.internal;

import se.skillytaire.didactic.tools.jlc.api.AbstractTestObjectFactory;

public class StringBuilderTestObjectFactory extends AbstractTestObjectFactory<StringBuilder> {

   @Override
   public StringBuilder createThis() {
      return new StringBuilder("this");
   }

   @Override
   public StringBuilder createThat() {
      return new StringBuilder("that");
   }

 

}
