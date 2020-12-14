package se.skillytaire.didactic.tools.jlc.spi.internal;

import se.skillytaire.didactic.tools.jlc.api.AbstractComparableTestObjectFactory;
import se.skillytaire.didactic.tools.jlc.api.ImmutableType;
@ImmutableType
public class StringTestObjectFactory extends AbstractComparableTestObjectFactory<String> {

   @Override
   public String createThis() {
      return "Midas";
   }

   @Override
   public String createThat() {
      return "Tiecho";
   }

   @Override
   public String createLessThen() {
      return "Joseline";
   }

   @Override
   public String createGreaterThen() {
      return "Vince";
   }



}
