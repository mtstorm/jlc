package se.skillytaire.didactic.tools.jlc.api;

import java.net.URI;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.DynamicContainer;
import org.junit.jupiter.api.DynamicNode;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestInstancePostProcessor;
import org.junit.jupiter.api.function.Executable;

import se.skillytaire.didactic.tools.jlc.spi.ext.feature.FeatureTestNodeFactory;
import se.skillytaire.didactic.tools.jlc.spi.ext.feature.JLCFeatereTestNode;
import se.skillytaire.didactic.tools.jlc.spi.model.config.JLCConfiguration;
import se.skillytaire.didactic.tools.jlc.spi.model.naming.DisplayName;
import se.skillytaire.didactic.tools.jlc.spi.model.structure.CompositeTestNode;
import se.skillytaire.didactic.tools.jlc.spi.model.structure.JLCTestNode;

public class JLCTestBuilder implements BeforeEachCallback, TestInstancePostProcessor  {
	private static JLCConfiguration<Object> CONFIGURATION;
	@Override
	public void postProcessTestInstance(Object testInstance, ExtensionContext context) throws Exception {
		CONFIGURATION = new JLCConfiguration<>(testInstance);
	}   
	
   @Override
   public void beforeEach(ExtensionContext context) throws Exception {
	   Optional<Object> optionalTestInstance = context.getTestInstance();
	   if(optionalTestInstance.isPresent()) {
		   //autowire the instances
		   CONFIGURATION.autowire();
	   }
	   
	   
	   //TestObjectFactories.setFields(testInstance, testInstance.getClass());
       
//FIXME
//      if (configuration.isConfiguredObjectFactory()) {
//         //System.out.println("Configuration outside SPI");
//         TestObjectFactory<Object> objectFactory = configuration.getObjectFactory();
//         TestObjectFactories.setFields(testInstance, testInstance.getClass(), objectFactory);
//
//      } else {
//         TestObjectFactories.setFields(testInstance, testInstance.getClass());
//      }

   }

   public static Stream<DynamicNode> build(Object testInstance) {
      JLCConfiguration<Object> configuration = new JLCConfiguration<>(testInstance);
      Stream<JLCFeatereTestNode<Object>> featureNodes = FeatureTestNodeFactory.structure(configuration);
      List<JLCFeatereTestNode<Object>> ding = featureNodes.sorted(FeatureTestNodeFactory.getComperator())
            .collect(Collectors.toList());
      SweepIterator<JLCFeatereTestNode<Object>> sweep = new SweepIterator<JLCFeatereTestNode<Object>>(
            ding.listIterator(), e -> e.init(configuration), e -> e.build());
      // sweep.setPeek(e->e.peek());
      while (sweep.hasNext()) {
         sweep.next();
      }
      Predicate<JLCTestNode<Object>> filter = n -> {
         boolean ok = true;
         if (n instanceof CompositeTestNode) {
            ok = n.hasChildren(); // FIXME plus executables
         }
         return ok;
      };
      if (configuration.isShowEmpyTests()) {
         filter = n -> true;
      }
      final Predicate<JLCTestNode<Object>> filterToApply = filter;
      return ding.stream().filter(filter).map(n -> JLCTestBuilder.convert(n, filterToApply));
   }

   /*
    * Rerunnable
    * 
    * Cloneable / serializable moeten de huidige tests met een andere object
    * factory kunnen uitvoeren
    * 
    * Niet elke test is relevant bijvoorbeeld rerunnable
    * 
    * Alle rerunnable tests moeten erin, echter niet zichzelf ivm recursie
    * 
    * Alle permutaties ook met orgineel versus rerunner combineren.
    * 
    * 
    * 
    */
   private static <T> DynamicNode convert(JLCTestNode<T> node, Predicate<JLCTestNode<T>> filter) {
      DynamicNode test;
      DisplayName displayName = node.getDisplayName();
      // System.out.println("Node -> "+ displayName);
      Optional<URI> uri = node.toUri();
      if (node instanceof CompositeTestNode) {
         Stream<? extends DynamicNode> tests = Stream.empty();
         CompositeTestNode<T> composite = (CompositeTestNode<T>) node;
         tests = composite.children().filter(filter).map(n -> JLCTestBuilder.convert(n, filter));// .flatMap(JLCTestBuilder::convert);
         if (uri.isPresent()) {
            test = DynamicContainer.dynamicContainer(displayName.value(), uri.get(), tests);
         } else {
            test = DynamicContainer.dynamicContainer(displayName.value(), tests);
         }
      } else {
         // single tests JLCSingleTestNode
         // JLCExecutable
         Executable executor = (Executable) node;

         if (uri.isPresent()) {
            test = DynamicTest.dynamicTest(displayName.value(), uri.get(), executor);
         } else {
            test = DynamicTest.dynamicTest(displayName.value(), executor);
         }
      }
      return test;
   }

//FACTORY


   /**
    * When the type T is used as a nemic value type, you can greate a specific
    * instance using the value.
    * 
    * @param type
    * @param numericValue
    * @return
    */
   public static <T> Optional<T> getNumericInstance(Class<T> type, long value) {
      Optional<TestObjectFactory<T>> factory =  CONFIGURATION.resolveFactory(type);
      Optional<T> result;
      if (factory.isPresent()) {
         result = factory.get().create(value);
      } else {
         result = Optional.empty();
      }
      return result;
   }

   static final class SweepIterator<E> implements Iterator<E> {

      private final ListIterator<E> listIterator;

      private final Consumer<E> start;
      private final Consumer<E> back;
      private boolean backwards;
      private Consumer<E> peek;

      public SweepIterator(ListIterator<E> listIterator, Consumer<E> start, Consumer<E> back) {
         if (listIterator == null) {
            throw new IllegalArgumentException("List iterator is void");
         }
         this.listIterator = listIterator;
         this.start = start;
         this.back = back;
      }

      @Override
      public boolean hasNext() {
         return backwards ? listIterator.hasPrevious() : listIterator.hasNext();
      }

      @Override
      public E next() {
         E element = backwards ? listIterator.previous() : listIterator.next();
         apply(element);
         if (!backwards && !hasNext()) {
            backwards = true;
         }
         return element;
      }

      private void apply(E e) {
         if (e == null) {
            throw new IllegalArgumentException("Element is void " + listIterator.previousIndex());
         }
         if (backwards){
            this.back.accept(e);
         } else {
            this.start.accept(e);
         }
         if (hasPeek()) {
            getPeek().accept(e);
         }
      }

      public Consumer<E> getPeek() {
         return peek;
      }

      public boolean hasPeek() {
         return peek != null;
      }

      public void setPeek(Consumer<E> peek) {
         this.peek = peek;
      }
   }


}
