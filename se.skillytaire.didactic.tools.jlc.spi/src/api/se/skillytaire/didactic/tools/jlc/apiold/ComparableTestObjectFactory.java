package se.skillytaire.didactic.tools.jlc.apiold;

import se.skillytaire.didactic.tools.jlc.api.TestObjectFactory;

public interface ComparableTestObjectFactory<T> extends TestObjectFactory<T> {
   /**
    * That should create an instance less then that.
    * @return
    */
   T createLessThen();
   
   /**
    * That should create an instance greater then that.
    * @return
    */
   T createGreaterThen();
   
}
