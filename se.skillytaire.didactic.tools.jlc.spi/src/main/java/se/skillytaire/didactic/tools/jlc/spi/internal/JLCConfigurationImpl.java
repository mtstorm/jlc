package se.skillytaire.didactic.tools.jlc.spi.internal;

import java.lang.annotation.Annotation;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Optional;
import java.util.ServiceLoader;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.DynamicContainer;
import org.junit.jupiter.api.DynamicNode;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.function.Executable;

import se.skillytaire.didactic.tools.jlc.api.Archetype;
import se.skillytaire.didactic.tools.jlc.api.ArchetypedCollection;
import se.skillytaire.didactic.tools.jlc.api.ComparableTestObjectFactory;
import se.skillytaire.didactic.tools.jlc.api.Enforcer;
import se.skillytaire.didactic.tools.jlc.api.ImmutableType;
import se.skillytaire.didactic.tools.jlc.api.JLC;
import se.skillytaire.didactic.tools.jlc.api.JLCConfiguration;
import se.skillytaire.didactic.tools.jlc.api.JLCConfigurationException;
import se.skillytaire.didactic.tools.jlc.api.JLCConfigurationTest;
import se.skillytaire.didactic.tools.jlc.api.TestConfiguration;
import se.skillytaire.didactic.tools.jlc.api.TestObjectFactory;
import se.skillytaire.didactic.tools.jlc.api.TestObjectFactoryRegistry;
import se.skillytaire.didactic.tools.jlc.api.TestOrderDescription;
import se.skillytaire.didactic.tools.jlc.api.VoidTestObjectFactory;
import se.skillytaire.didactic.tools.jlc.spi.ext.feature.FeatureTestNodeFactory;
import se.skillytaire.didactic.tools.jlc.spi.ext.feature.JLCFeatereTestNode;
import se.skillytaire.didactic.tools.jlc.spi.ext.immutable.ImmutableObject;
import se.skillytaire.didactic.tools.jlc.spi.internal.invoke.AnnotatedInvoker;
import se.skillytaire.didactic.tools.jlc.spi.internal.invoke.InstanceFieldInitializer;
import se.skillytaire.didactic.tools.jlc.spi.model.config.AutoWire;
import se.skillytaire.didactic.tools.jlc.spi.model.naming.DisplayName;
import se.skillytaire.didactic.tools.jlc.spi.model.structure.CompositeTestNode;
import se.skillytaire.didactic.tools.jlc.spi.model.structure.JLCTestNode;
import se.skillytaire.didactic.tools.jlc.spi.util.ClassTool;

public class JLCConfigurationImpl<T> implements JLCConfiguration<T>{
	private static final Class<?> DEFAULT_VALUE = Void.class;
	private static final Class<? extends TestObjectFactory<?>> DEFAULT_TEST_FACTORY = VoidTestObjectFactory.class;

	private Logger log = Logger.getLogger(JLCConfigurationImpl.class.getName());
	private final Object testInstance;
	private final boolean showEmpyTests;

	private TestObjectFactory<T> objectFactory;
	private final boolean displayTid;
	private final boolean includeBestPractices;
	private final boolean autoInject;
	private  Class<T> beanUnderTestType;
	private final Map<String, ArchetypedCollection<T, ?>> extensionConfigurations = new HashMap<>();

	private final TestOrderDescription order;

	private final boolean merge;

	private boolean configuredObjectFactory;

	private final List<TestObjectFactory<?>> factories = new ArrayList<TestObjectFactory<?>>(10);

	private final Class<?> testClass;

	@SuppressWarnings("unchecked")
	JLCConfigurationImpl(Object testInstance) {
		if (testInstance == null) {
			throw new IllegalArgumentException("The test instance is void");
		}
		if (!testInstance.getClass().isAnnotationPresent(JLC.class)) {
			throw new IllegalStateException("The test instance is not annotated with JLC");
		}
		this.testInstance = testInstance;
		this.testClass = testInstance.getClass();
		JLC jlc = testClass.getAnnotation(JLC.class);

		this.beanUnderTestType = (Class<T>) jlc.value();
		this.displayTid = jlc.displayTid();
		this.autoInject = jlc.autoInject();
		this.includeBestPractices = jlc.bestPractices();
		// Class<?> declaredFactory = jlc.testFactory();
		this.showEmpyTests = jlc.showEmptyTests();
		this.order = ConfigurationTool.getSort(testClass, JLC.class);
		this.merge = jlc.merge();
		loadDeclaredTestFactory(jlc);
		loadDeclaredRegistry(jlc);
		loadDeclaredRegistries(jlc);
		loadSPI();
		if (jlc.autoLookUpTestFactory() && this.hasBeanUnderTestType() && !hasObjectFactory()) {
			Optional<TestObjectFactory<?>> optionalTestObjectFactory = this.factories.stream()
					.filter(f -> f.isTypeFor(beanUnderTestType)).findFirst();
			if (optionalTestObjectFactory.isPresent()) {
				this.objectFactory = (TestObjectFactory<T>) optionalTestObjectFactory.get();
			}
		}

//		if(this.autoInject) {
//			List<TestObjectFactory<?>> factories = new ArrayList<>();
//			
//			if(declaredFactory == VoidTestObjectFactory.class) {
//				 log.config("TestFactory will be resolved having the service provider interface.");
//		         Optional<?> factory = TestObjectFactories.resolveFactory(beanUnderTestType);
//		         if (factory.isPresent()) {
//		        	 objectFactory=
//		                  (TestObjectFactory<T>) factory.get();
//		         }else {
//		        	 throw new IllegalStateException("There is no object factory configured for type "
//		                     + beanUnderTestType.getName());
//		         }
//		         configuredObjectFactory = false;
//			} else {
//				
//				log.config("TestFactory will not be resolved having the service provider interface, but the declared is used.");
//				try {
//					configuredObjectFactory = true;
//					objectFactory = (TestObjectFactory<T>) declaredFactory.newInstance();
//					if(!objectFactory.isTypeFor(beanUnderTestType)) {
//						String msg = String.format("The declared testFactory %s can not be used to create test instances for type %s, the factory is for type %s", declaredFactory.getName(), beanUnderTestType.getName(),objectFactory.type().getName());
//						throw new IllegalStateException(msg);
//					} else {
//						//initialize the factory
//						TestObjectFactories.setFields(objectFactory, objectFactory.getClass());
//					}
//				} catch (Exception e) {
//					String msg = String.format("The declared testFactory %s can not be used to create test instances for type %s", declaredFactory.getName(), beanUnderTestType.getName());
//					throw new IllegalStateException(msg, e);
//				}
//			}
//		}
	}

	@SuppressWarnings("unchecked")
	private void loadDeclaredTestFactory(JLC jlc) {

		if (TestObjectFactory.class.isAssignableFrom(this.testClass)) {
			this.objectFactory = (TestObjectFactory<T>) this.testInstance;
			this.factories.add(objectFactory);
			this.beanUnderTestType = objectFactory.type();
		} else {
			Class<?> valueClass = jlc.value();
			Class<? extends TestObjectFactory<?>> declaredFactory = jlc.testFactory();
			
			if(declaredFactory != VoidTestObjectFactory.class) {
				try {
					this.objectFactory = (TestObjectFactory<T>) declaredFactory.getDeclaredConstructor().newInstance();
					this.factories.add(objectFactory);
					
				} catch (Exception e) {
					String msg = String.format("Unable to load the declared testFactory %s in class %s",
							declaredFactory.getName(), testClass.getName());
					throw new JLCConfigurationException(msg, e);
				}	
				//check for matching value == factory.type
				this.beanUnderTestType = objectFactory.type();
				if(valueClass!=DEFAULT_VALUE) {
					if(this.beanUnderTestType != valueClass) {
						String msg = String.format("The value '%s' is not produced by the factory '%s' but is of type '%s'",
								valueClass.getName(), declaredFactory.getName(), beanUnderTestType.getName());
						throw new JLCConfigurationException(msg);						
					}
				}
			} else {
				this.beanUnderTestType = (Class<T>) valueClass;
			}
			
		}
	}

	private void loadDeclaredRegistry(JLC jlc) {
		Class<? extends TestObjectFactory<?>>[] declaredFactory = jlc.registry();
		add(declaredFactory);
	}

	private void add(Class<? extends TestObjectFactory<?>>[] declaredFactory) {
		for (Class<? extends TestObjectFactory<?>> factoryClass : declaredFactory) {
			try {
				if (factoryClass == null) {
					continue;
				}
				TestObjectFactory<?> factory = factoryClass.getDeclaredConstructor().newInstance();
				this.factories.add(factory);
			} catch (Exception e) {
				String msg = String.format("Unable to load test factory %s in class %s.", factoryClass.getName(),
						testClass.getName());
				throw new IllegalStateException(msg, e);
			}
		}
	}

	private void loadDeclaredRegistries(JLC jlc) {
		Class<? extends TestObjectFactoryRegistry>[] registries = jlc.registries();
		for (Class<? extends TestObjectFactoryRegistry> registryClass : registries) {
			try {
				TestObjectFactoryRegistry registry = registryClass.getDeclaredConstructor().newInstance();
				add(registry.getTestObjectFactories());
			} catch (Exception e) {
				String msg = String.format(
						"Unable to load test object factory registry %s in class %s declared in the registries.",
						registryClass.getName(), testClass.getName());
				throw new IllegalStateException(msg, e);
			}
		}
	}

	private void loadSPI() {
		TestObjectFactory.resolve().forEach(this.factories::add);
	}
	@Override
	public Object getTestInstance() {
		return testInstance;
	}

	/**
	 * 
	 * @return
	 */
	public TestObjectFactory<T> getObjectFactory() {
		return this.objectFactory;
	}

	public boolean hasObjectFactory() {
		return this.objectFactory != null;
	}

	public boolean isDisplayTid() {
		return displayTid;
	}

	public boolean isIncludeBestPractices() {
		return includeBestPractices;
	}

	public boolean hasBeanUnderTestType() {
		return beanUnderTestType != DEFAULT_VALUE;
	}

	public Class<T> getBeanUnderTestType() {
		return beanUnderTestType;
	}

//	public TestConfiguration<?, T> enfoce(Object configurable, Archetype archetype) {
//
//		return Enforcer.enforce(this, configurable, archetype);
//
//	}

	public <N extends TestConfiguration<N, T>> void register(ArchetypedCollection<T, N> extensionConfiguration,
			Class<? extends TestConfiguration<N, T>> key) {
		// System.out.println("REGISTAR!!!!" + key.getName());
		ArchetypedCollection<T, ?> result = extensionConfigurations.put(key.getName(), extensionConfiguration);
		if (result != null) {
			throw new IllegalStateException("The key " + key.getName() + " has already been taken.");
		}
	}

	public boolean containsConfiguration(Class<? extends TestConfiguration<?, T>> key) {
		return this.extensionConfigurations.containsKey(key.getName());
	}

	@SuppressWarnings("unchecked")
	public <N extends TestConfiguration<N, T>> ArchetypedCollection<T, N> getExtensionConfiguration(Class<?> key) {
		return (ArchetypedCollection<T, N>) this.extensionConfigurations.get(key.getName());
	}

	@Override
	public String toString() {
		return String.format(
				"JLCConfiguration [testInstance=%s, displayTid=%s, includeBestPractices=%s, beanUnderTestType=%s, orderDescription=%s,  extensionConfigurations=%s]",
				testInstance, displayTid, includeBestPractices, beanUnderTestType, this.order, extensionConfigurations);
	}

	/**
	 * Checks if there is a object factory configured outside the SPI.
	 * 
	 * @return
	 */
	public boolean isConfiguredObjectFactory() {
		return configuredObjectFactory;
	}

	public boolean isMerge() {
		return merge;
	}

	public TestOrderDescription getOrder() {
		return order;
	}

	public boolean isShowEmpyTests() {
		return showEmpyTests;
	}

	/**
	 * Searches for a test object factory that supports the type.
	 * 
	 * @param <F>
	 * @param <T>
	 * @param type
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <F extends TestObjectFactory<E>, E> Optional<F> resolveFactory(Class<?> type) {
		if (type == null) {
			throw new IllegalArgumentException("Type is void");
		}
		F factory;
		Optional<TestObjectFactory<?>> optionalFactory = this.factories.stream().filter(f -> f.isTypeFor(type))

				.findFirst();
		if (optionalFactory.isPresent()) {
			factory = (F) optionalFactory.get();
		} else if (type.isArray()) {

			factory = (F) new ComparableTestObjectFactory<E>() {

				private E array(Object element) {
					Class<?> componentType = type.getComponentType();
					E[] r = (E[]) java.lang.reflect.Array.newInstance(type.getComponentType(), 1);
					r[0] = (E) element;
					return (E) r;
				}

				@Override
				public E createThis() {
					Class<?> componentType = type.getComponentType();
					Object element = getThisInstance(componentType);
					return array(element);
				}

				@Override
				public E createThat() {
					Class<?> componentType = type.getComponentType();
					Object element = getThatInstance(componentType);
					return array(element);
				}

				@Override
				public Class<E> type() {
					return (Class<E>) type;
				}

				@Override
				public boolean isTypeFor(Class<?> aType) {
					return type == aType;
				}

				@Override
				public E createLessThen() {
					Class<?> componentType = type.getComponentType();
					Object element = getLessThenInstance(componentType);
					return array(element);
				}

				@Override
				public E createGreaterThen() {
					Class<?> componentType = type.getComponentType();
					Object element = getGreaterThenInstance(componentType);
					return array(element);
				}
			};

		} else {
			factory = null;
		}

		return Optional.ofNullable(factory);
	}

	public boolean isImmutableType() {
		return this.isImmutableType(this.beanUnderTestType);
	}

	/**
	 * Checks if the type is immutable.
	 * 
	 * @param type
	 * @return
	 */
	@Override
	public boolean isImmutableType(Class<T> type) {
		if (type == null) {
			throw new IllegalArgumentException("Type is void");
		}
		boolean isImmutableType = ImmutableObject.isImmutableType(this, type);
//		Optional<TestObjectFactory<T>> optionalFactory = resolveFactory(type);
//		if (optionalFactory.isPresent()) {
//			TestObjectFactory<T> factory = optionalFactory.get();
//			isImmutableType = factory.getClass().isAnnotationPresent(ImmutableType.class);
//		} else {
//			throw new IllegalStateException("There is no test factory found for the class " + type.getName());
//		}
		return isImmutableType;
	}

	private <F extends TestObjectFactory<E>, E> F getFactory(Class<?> type){
		Optional<F> resolvedResult = resolveFactory(type);
		if (!resolvedResult.isPresent()) {
			throw new IllegalStateException("There is no test factory found for the class " + type.getName());
		}
		F factory = resolvedResult.get();
		return factory;
	}
	
	private <E> void ensureNotNull(TestObjectFactory<E> factory, String methodName, E instance) {
		if (instance == null) {
			String message = String.format(
					"The test factory %s creates a null reference for the method %s() for the type %s",
					factory.getClass().getName(), methodName, factory.type());
			throw new IllegalStateException(message);
		}
	}
	@Override
	public <E> E getThisInstance(Class<?> type) {
		TestObjectFactory<E> factory = getFactory(type);
		E thisInstance = factory.createThis();
		ensureNotNull(factory, "createThis", thisInstance);
		return thisInstance;
	}
	@Override
	public <E> E getThatInstance(Class<?> type) {
		TestObjectFactory<E> factory = getFactory(type);
		E thatInstance = factory.createThat();
		ensureNotNull(factory, "createThat", thatInstance);
		return thatInstance;
	}
	@Override
	public <E> E getLessThenInstance(Class<?> type) {
		ComparableTestObjectFactory<E> factory = getFactory(type);
		E lessThanInstance = factory.createLessThen();
		ensureNotNull(factory, "createLessThen", lessThanInstance);
		return lessThanInstance;
	}
	@Override
	public <E> E getGreaterThenInstance(Class<?> type) {
		ComparableTestObjectFactory<E> factory = getFactory(type);
		E createGreaterThen = factory.createGreaterThen();
		ensureNotNull(factory, "createGreaterThen", createGreaterThen);
		return createGreaterThen;
	}

//	public static void setFields(Object instance) {
//		setFields(instance, instance.getClass());
//	}

	public static <A extends Annotation> void setAnnotatedFields(Object instance, AnnotatedInvoker<Annotation, ?, ?> invoker) {
		ClassTool tool = new ClassTool(instance.getClass());
		tool.fields(invoker.getAnnotation())
				.map(field -> InstanceFieldInitializer.forInstance(instance)
				.using(field)
				.initialize(invoker))
				.forEach(InstanceFieldInitializer::invoke);
	}

//	public static void setFields(Object instance, Class<?> type, TestObjectFactory<Object> objectFactory) {
//		InstanceTool tool = new InstanceTool(instance, objectFactory);
//		tool.autoInitialize();
//
//		tool.validate();
//
//	}

//	public static void setFields(Object instance, Class<?> type) {
//		setFields(instance, type, null);
//	}

//	public void defaultInvokers() {
//		Builder<AnnotatedInvoker<?,?,?>> streamBuilder = Stream.builder();
//	     streamBuilder.add(new ThisInvoker<Object>())
//         .add(new ThatInvoker<Object>())
//         .add(new LessThenInvoker<Object>())
//         .add(new GreaterThenInvoker<Object>()); 
//	}
	public void autowire() {
		new AutoWire(this, this.testInstance);
		
//		
//		
//		//load all the fields having the annotations for the factory.
//	       Builder<Class<? extends Annotation>> streamBuilder = Stream.builder();
//	       streamBuilder.add(This.class);
//	       streamBuilder.add(That.class);
//	       streamBuilder.add(LessThen.class);
//	       streamBuilder.add(GreaterThen.class);
//	       
//	       
//	    //zoek alle fields met annotatie @This   
//	       ClassTool classTool = new ClassTool(testClass);
//	       classTool.fields(This.class).
	}

	@Override
	public Stream<TestObjectFactory<?>> factories(){
		return this.factories.stream();
	}


	@Override
	public Stream<DynamicNode> stream() {
      Stream<JLCFeatereTestNode<Object>> featureNodes = FeatureTestNodeFactory.structure((JLCConfigurationImpl<Object>) this);
      List<JLCFeatereTestNode<Object>> ding = featureNodes.sorted(FeatureTestNodeFactory.getComperator())
            .collect(Collectors.toList());
      SweepIterator<JLCFeatereTestNode<Object>> sweep = new SweepIterator<JLCFeatereTestNode<Object>>(
            ding.listIterator(), e -> e.init((JLCConfigurationImpl<Object>) this), e -> e.build());
       sweep.setPeek(e->e.peek());
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
      if (this.isShowEmpyTests()) {
         filter = n -> true;
      }
      final Predicate<JLCTestNode<Object>> filterToApply = filter;
      return ding.stream().filter(filter).map(n -> JLCConfigurationImpl.convert(n, filterToApply));
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
       tests = composite.children().filter(filter).map(n -> JLCConfigurationImpl.convert(n, filter));// .flatMap(JLCTestBuilder::convert);
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
private static final class SweepIterator<E> implements Iterator<E> {

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
@Override
public <A extends Annotation> Optional<A> getTestAnnotation(Class<A> annotationClass) {
	A annotation =  this.getTestInstance().getClass().getAnnotation(annotationClass);
	return Optional.ofNullable(annotation);
}
@Override
public <A extends Annotation> Optional<A> getBeanUnderTestAnnotationx(Class<A> annotationClass) {
	A annotation =  this.getBeanUnderTestType().getAnnotation(annotationClass);
	return Optional.ofNullable(annotation);
}

}
