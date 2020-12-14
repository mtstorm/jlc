package se.skillytaire.didactic.tools.jlc.constructor.spi;

import java.util.ArrayList;
import java.util.ServiceLoader;
import java.util.stream.Stream;

import se.skillytaire.didactic.tools.jlc.constructor.spi.model.config.TestConstructorConfiguration;
import se.skillytaire.didactic.tools.jlc.spi.model.structure.JLCTestNode;

/**
 * The method test factory is enabled for zero or more signatures. When
 * 
 * @author prolector
 *
 */
public interface ConstructorTestFactory<T> {
   /**
    * Checks if this test is enabled for the constructor based on it's
    * configuration.
    * 
    * @param configuration
    * @return
    */
   boolean matches(TestConstructorConfiguration<T> configuration);

   /**
    * Creates a new test for the method having the test method configuration.
    */
   JLCTestNode<T> create(TestConstructorConfiguration<T> configuration);

   @SuppressWarnings("unchecked")
   static <T> Stream<JLCTestNode<T>> find(TestConstructorConfiguration<T> configuration) {
      if (configuration == null) {
         throw new IllegalArgumentException("configuration is void");
      }
      Stream<JLCTestNode<T>> result;
      if (configuration.isEnabled()) {
         ClassLoader loader = Thread.currentThread().getContextClassLoader();
         @SuppressWarnings("rawtypes")
         ServiceLoader<ConstructorTestFactory> serviceLoader = ServiceLoader.load(ConstructorTestFactory.class, loader);

         ArrayList<JLCTestNode<T>> nodes = new ArrayList<>();
         for (ConstructorTestFactory<T> factory : serviceLoader) {
            if (factory.matches(configuration)) {
               nodes.add((JLCTestNode<T>) factory.create(configuration));
            }
         }

         result = nodes.stream();
      } else {
         result = Stream.empty();
      }
      return result;

   }
}
