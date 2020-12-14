package se.skillytaire.didactic.tools.jlc.spi.internal;

import se.skillytaire.didactic.tools.jlc.api.AbstractTestObjectFactory;

public class StringBufferTestObjectFactory extends AbstractTestObjectFactory<StringBuffer> {

   @Override
   public StringBuffer createThis() {
      return new StringBuffer("this");
   }

   @Override
   public StringBuffer createThat() {
      return new StringBuffer("that");
   }

 

}
