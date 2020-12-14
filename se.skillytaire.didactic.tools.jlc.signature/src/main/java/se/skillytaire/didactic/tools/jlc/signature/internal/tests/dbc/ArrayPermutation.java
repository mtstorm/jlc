package se.skillytaire.didactic.tools.jlc.signature.internal.tests.dbc;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import se.skillytaire.didactic.tools.jlc.api.TestObjectFactories;
import se.skillytaire.didactic.tools.jlc.signature.spi.Signature;

public class ArrayPermutation implements Iterator< Object[] > {
   private final Object[] instance;
   
   private final int size;

   private int counter;
   private final Class<?>[] parameterTypes;
   ArrayPermutation(Signature signature){
    //  System.out.println("permutation "+ signature);
      parameterTypes = signature.getParameterTypes();
      instance = Arrays.stream(parameterTypes).map(c -> TestObjectFactories.getThisInstance(c)).toArray();
      int elementCount = (int) Arrays.stream(parameterTypes).filter( c-> !c.isPrimitive()).count();
      if(elementCount == 0) {
         size = 0;
      }else {
         size = (int) Math.pow(2, elementCount) -1;//last step is always all non null
      }
   }
   @Override
   public boolean hasNext() {
      return counter < size;
   }

   @Override
   public Object[] next() {
      Object[] next = new Object[instance.length];
      System.arraycopy(instance, 0, next, 0, instance.length);
      
      BitField bitField = new IntegerBitField(counter);
      
      for (int i = 0, j = 0; i < next.length; i++) {
         if(!parameterTypes[i].isPrimitive()) {
            boolean x = bitField.getValue(j++);
            if(!x) {
               next[i] = null;
            }
         }
      }
      counter++;
      return next;
   }

   public boolean isEmpty() {
      return size == 0;
   }
   
   public boolean isPresent() {
      return !isEmpty();
   }
   int getSize() {
      return size;
   }
   public static Stream<Object[]> asStream(Signature signature){
      ArrayPermutation iterator = new ArrayPermutation(signature);
      Stream<Object[]> stream = StreamSupport.stream(
            Spliterators.spliterator(iterator, iterator.getSize(), Spliterator.ORDERED)
            , false);
      return stream;
   }

//   public static void main(String[] args) {
//      Class<?>[] paramTypes = new Class[]{int.class,Boolean.class};
//      ConstructorSignature signature = new ConstructorSignature(String.class, paramTypes );
//      ArrayPermutation ap = new ArrayPermutation(signature);
//      
//      System.out.println(" size "+ ap.getSize());
//      System.out.println(" p "+ ap.isPresent());
//      while (ap.hasNext()) {
//         Object[] objects = (Object[]) ap.next();
//         System.out.println(Arrays.toString(objects));
//      }
//   }
}
