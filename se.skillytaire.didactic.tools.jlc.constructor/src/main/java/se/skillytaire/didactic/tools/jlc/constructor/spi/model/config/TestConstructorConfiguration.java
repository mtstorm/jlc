package se.skillytaire.didactic.tools.jlc.constructor.spi.model.config;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Optional;
import java.util.logging.Logger;

import org.junit.jupiter.api.DisplayName;

import se.skillytaire.didactic.tools.jlc.api.Archetype;
import se.skillytaire.didactic.tools.jlc.api.JLCConfiguration;
import se.skillytaire.didactic.tools.jlc.constructor.api.TestConstructor;
import se.skillytaire.didactic.tools.jlc.constructor.api.TestConstructors;
import se.skillytaire.didactic.tools.jlc.constructor.spi.util.ConstructorTool;
import se.skillytaire.didactic.tools.jlc.constructor.spi.util.TestConstructorAnnotationTool;
import se.skillytaire.didactic.tools.jlc.signature.spi.ConstructorSignature;
import se.skillytaire.didactic.tools.jlc.signature.spi.MethodSignature;
import se.skillytaire.didactic.tools.jlc.signature.spi.model.config.AbstractTestSignatureConfiguration;

public class TestConstructorConfiguration<T> extends AbstractTestSignatureConfiguration<TestConstructorConfiguration<T>,T,ConstructorSignature<T>, Constructor<T>> {

   private static final Logger log = Logger.getLogger(TestConstructorConfiguration.class.getName());
	
	/**
	 * Creates the configuration based on a test method annotation.
	 * @param testConstructor
	 */
	public TestConstructorConfiguration(JLCConfiguration<T> parent,TestConstructor testConstructor, int globalMaxParameterCount) {
		super(parent, TestConstructorAnnotationTool.of(parent.getBeanUnderTestType(),testConstructor) , testConstructor.dbc());
		apply(testConstructor, globalMaxParameterCount);
	
	}
	/**
	 * Creates the configuration based on a method signature.
	 * @param testMethod
	 */
	public TestConstructorConfiguration(JLCConfiguration<T> parent,Constructor<T> constructor) {
		super(parent,new ConstructorSignature<T>(constructor), IllegalArgumentException.class);
		setMaximalParameterCount(TestConstructors.DEFAULT_PARAM_COUNT);
		setExecutor(constructor);
	}
  
	public TestConstructorConfiguration(JLCConfiguration<T> parent, ConstructorSignature<T> cs) {
		super(parent,cs, IllegalArgumentException.class);
		setMaximalParameterCount(TestConstructors.DEFAULT_PARAM_COUNT);
	}
	public boolean matches(TestConstructor obj) {
		return TestConstructorAnnotationTool.matches(getSignature(), this.getParent().getBeanUnderTestType().getSimpleName(), obj);
	}

	@Override
	public final Object invoke(Object[] parameterValues) throws InvocationTargetException {
		T newInstance = this.getParent().getObjectFactory().createThis();
		return invoke(newInstance, parameterValues);
	}
	//invoker maken????
	public Object invoke(Object instance, Object[] parameterValues) throws InvocationTargetException {
		Object returnValue;
		if (hasExecutor()) {
			try {
				getExecutor().setAccessible(true);
				returnValue = getExecutor().newInstance( parameterValues);
			} catch (IllegalAccessException | IllegalArgumentException | InstantiationException e) {
				throw new IllegalStateException("error invoking the method " + getSignature(), e);
			}

		} else {
			throw new IllegalStateException("There is no method for signature " + this.getSignature());
		}
		return returnValue;
	}

	// FIXME
	@Override
	public int hashCode() {
		return super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		boolean equals = super.equals(obj);
		if (!equals && obj instanceof TestConstructorConfiguration) {
			@SuppressWarnings("unchecked")
			TestConstructorConfiguration<T> that = (TestConstructorConfiguration<T>) obj;
			equals = this.getSignature().equals(that.getSignature());
		}
		return equals;
	}




	@Override
	public int compareTo(TestConstructorConfiguration<T> that) {
		int compareTo = this.getSignature().compareTo(that.getSignature());
		return compareTo;
	}
	public void apply(Class<T> beanUnderTestType) {
		if (!hasExecutor()) {
			Optional<Constructor<T>> optionalConstructor = ConstructorTool.findConstructor(beanUnderTestType, this.getSignature());
			optionalConstructor.ifPresent((e)-> this.setExecutor(e));
		}	
	}
	
	/**
	 * apply the enablement of this configuration based on the annotation.
	 * 
	 * @see #TestMethodConfiguration(MethodSignature, Class)
	 * 
	 * @param obj
	 */
	public void apply(TestConstructor testConstructor,int globalMaxParameterCount) {
		if (!matches(testConstructor)) {
			throw new IllegalArgumentException("Test constructor does not match this." + this.toString());
		}
		int maxParameterCount = testConstructor.parameterCount();
		if (maxParameterCount <= TestConstructor.NOT_CONFIGURED) {
			maxParameterCount = globalMaxParameterCount;
		}
		setMaximalParameterCount(maxParameterCount);
		setEnabled(testConstructor.enabled());
		setInverted(testConstructor.invert());
		setFeature(testConstructor.feature());
		setDbcEnabled(!testConstructor.disableDBC());
		String archetypeName = testConstructor.archetype().trim();
		if (!archetypeName.isEmpty()) {
			Archetype archetype = new Archetype(archetypeName);
			setArchetype(archetype);
		}
		setEnforced(true);
		setDeclared(true);
		setEnabled(testConstructor.enabled());
		DisplayName configuredDisplayName = testConstructor.displayName();
		String cdn = configuredDisplayName.value();
		if(!cdn.isEmpty()) {
			setDisplayNameValue(cdn);
		}
	}


	
//
//	/**
//	 * Apply the type when invoked using the test method.
//	 * @param t
//	 *  @see #TestMethodConfiguration(TestConstructor)
//	 */
//	public void apply(Class<T> t) {
//		if(!hasExecutor()) {
//			try{
//				t.getDeclaredConstructor(getSignature().getParameterTypes());
//			}catch(Exception e) {
//				System.out.println("TODO log.debug: No constructor found for class");
//			}
//			Optional<Con> optionalMethod = ConstructorTool.findMethod(t, this.getSignature().getMethodPredicate());
//			if(optionalMethod.isPresent()) {
//				this.setMethod(optionalMethod.get());
//			}
//		}
//	}
//	   /**
//	    * apply the enablement of this configuration based on the annotation.
//	    * @see #TestMethodConfiguration(MethodSignature, Class)
//	    * 
//	    * @param obj
//	    */
//	   public void apply(TestConstructor testMethod) {
//	      if (!matches(testMethod)) {
//	         throw new IllegalArgumentException(
//	               "Test method does not match this.");
//	      }
//			int maxParameterCount = testMethod.parameterCount();
//			if(maxParameterCount < 0) {
//				throw new IllegalArgumentException("max paramter count for testmethod "+ getSignature() +" is less then zero");
//			}
//			setMaximalParameterCount(maxParameterCount);
//			setEnabled(testMethod.enabled());
//			setInverted(testMethod.invert());
//			setFeature(testMethod.feature());
//			String archetypeName = testMethod.archetype().trim();
//			if(!archetypeName.isEmpty()) {
//				Archetype archetype = new Archetype(archetypeName);
//				setArchetype(archetype);
//			}
//	   }


		


}
