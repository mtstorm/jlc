package se.skillytaire.didactic.tools.jlc.lint.spi;

import java.util.function.Supplier;
import java.util.stream.Stream;

import se.skillytaire.didactic.tools.jlc.api.JLCConfiguration;
import se.skillytaire.didactic.tools.jlc.constructor.internal.spi.TestConstructorsConfiguration;
import se.skillytaire.didactic.tools.jlc.constructor.spi.model.config.TestConstructorConfiguration;
import se.skillytaire.didactic.tools.jlc.lint.internal.LintersTestNodeFactory;
import se.skillytaire.didactic.tools.jlc.lint.spi.model.config.TestLinterConfiguration;
import se.skillytaire.didactic.tools.jlc.method.internal.spi.TestMethodsConfiguration;
import se.skillytaire.didactic.tools.jlc.method.spi.model.config.TestMethodConfiguration;
import se.skillytaire.didactic.tools.jlc.signature.spi.ConstructorSignature;
import se.skillytaire.didactic.tools.jlc.signature.spi.MethodSignature;
import se.skillytaire.didactic.tools.jlc.spi.TestConfiguration;
import se.skillytaire.didactic.tools.jlc.spi.e.JLCFeatureConfiguration;
import se.skillytaire.didactic.tools.jlc.spi.ext.feature.Enforcer;
import se.skillytaire.didactic.tools.jlc.spi.ext.feature.FeatureTestNodeFactory;
import se.skillytaire.didactic.tools.jlc.spi.ext.feature.JLCFeatereTestNode;

public abstract class AbstractLinter<T> implements Linter<T> {
	private TestLinterConfiguration<T> testConfiguration;



	@Override
	public void enforce(TestLinterConfiguration<T> testConfiguration) {
		if(testConfiguration == null) {
			throw new IllegalArgumentException("Test configuration for linter is void");
		}
		this.testConfiguration = testConfiguration;

		enforce();
	}
	//klinkt als builder hier....
	public static interface TestConfigurationSupplier
	   <E extends JLCFeatureConfiguration, N extends TestConfiguration<E,N,T>,T> extends Supplier<N> {
	}

   private class TestConstructorConfigurationSupplier
           implements TestConfigurationSupplier<TestConstructorsConfiguration, TestConstructorConfiguration<T>,T> {
      
      private final ConstructorSignature<T> constructorSignature;
      
      TestConstructorConfigurationSupplier(ConstructorSignature<T> constructor) {
         this.constructorSignature = constructor;
      }

      @Override
      public TestConstructorConfiguration<T> get() {
         Enforcer<T, TestConstructorsConfiguration, TestConstructorConfiguration<T>> enforcer =
               Enforcer.of(getConfiguration(), constructorSignature);
         return enforcer.force(getArchetype());
      }
   }	
   
   private class TestMethodConfigurationSupplier implements Supplier<TestMethodConfiguration<T>> {
      private final MethodSignature methodSignature;
      
      TestMethodConfigurationSupplier(MethodSignature methodSignature) {
         this.methodSignature = methodSignature;
      }

      @Override
      public TestMethodConfiguration<T> get() {
         Enforcer<T, TestMethodsConfiguration, TestMethodConfiguration<T>> enforcer =
               Enforcer.of(getConfiguration(), methodSignature);
         return enforcer.force(getArchetype());
      }
   }	
   
   
	protected TestConstructorConfiguration<T> enforce(ConstructorSignature<T> constructor) {
	   return new TestConstructorConfigurationSupplier(constructor).get();
	}
	
   protected TestMethodConfiguration<T> enforce(MethodSignature method) {
      return new TestMethodConfigurationSupplier(method).get();
   }	
	
	protected final JLCConfiguration<T> getConfiguration(){
	   return this.testConfiguration.getParent();
	}
	/**
	 * Enforce here.
	 */
	protected abstract void enforce();
	
	protected ConstructorSignature<T> create(Class<?>... paramTypes){
		Class<T> clazz = testConfiguration.getParent().getBeanUnderTestType();
		return new ConstructorSignature<T>(clazz, paramTypes);
	}
////FIXME ZIE Config	
//	protected TestConfiguration<?,T> enforce(ConstructorSignature<T> constructor){
////		Optional<TestConfigurationRegistry<T, TestConstructorConfiguration<T>>> optionalConfig = this.testConfiguration.getParent().find(TestConstructorConfiguration.class);
////		if(optionalConfig.isPresent()) {
////			TestConfigurationRegistry<T, TestConstructorConfiguration<T>> config = optionalConfig.get();
////			Archetype archetype = getArchetype();
////			//FIXME
////			TestConstructorConfiguration<T> testConstructorConfig = null;//
////			
////			//new TestConstructorConfiguration<>(parent, constructor);
////			config.enforce(testConstructorConfig, archetype);
////		} else {
////			throw new AssertionError("There is no constructor configuration");
////		}
//		
//		return null;
//	}
	//FIXME JLC Model for beans
   protected void enforce(se.skillytaire.didactic.tools.jlc.spi.util.ClassProperty classproperty) {
      Enforcer.of(getConfiguration(), classproperty).enforce(getArchetype());
   }
	
	@Override
	public Stream<JLCFeatereTestNode<T>> features() {
	   return FeatureTestNodeFactory
	         .factories(getConfiguration())
	         .filter( f->f.getClass() != LintersTestNodeFactory.class)
	         .filter(f->f.isEnabledFor(getConfiguration()))
	         .map(f -> f.create())
	         .peek( n-> n.applyFilter(getArchetype()))
	         ;
	   
	   
//	   stream.forEach( f-> System.out.println("node "+ f.getClass()) );
//	   
//	   return FeatureTestNodeFactory
//	         .structure(getConfiguration())
//	         .peek( n-> n.applyFilter(getArchetype()))
//	        //.filter(n -> n.hasChildren())
//	               
//	         ;
	
	}
}
