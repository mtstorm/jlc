package se.skillytaire.didactic.tools.jlc.api;

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
