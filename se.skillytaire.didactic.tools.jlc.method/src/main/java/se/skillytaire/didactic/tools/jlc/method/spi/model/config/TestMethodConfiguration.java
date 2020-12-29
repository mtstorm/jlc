package se.skillytaire.didactic.tools.jlc.method.spi.model.config;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Optional;

import se.skillytaire.didactic.tools.jlc.api.Archetype;
import se.skillytaire.didactic.tools.jlc.api.JLCConfiguration;
import se.skillytaire.didactic.tools.jlc.method.api.TestMethod;
import se.skillytaire.didactic.tools.jlc.method.api.TestMethods;
import se.skillytaire.didactic.tools.jlc.method.spi.util.MethodTool;
import se.skillytaire.didactic.tools.jlc.signature.spi.MethodSignature;
import se.skillytaire.didactic.tools.jlc.signature.spi.model.config.AbstractTestSignatureConfiguration;



public class TestMethodConfiguration<T> extends AbstractTestSignatureConfiguration<TestMethodConfiguration<T>,T, MethodSignature, Method>
		 {


	/**
	 * Creates the configuration based on a test method annotation.
	 * 
	 * @param testMethod
	 */
	public TestMethodConfiguration(JLCConfiguration<T> parent, TestMethod testMethod, int globalMaxParameterCount) {
		this(parent, TestMethodAnnotationTool.of(testMethod), testMethod.dbc());
		apply(testMethod, globalMaxParameterCount);

	}

	/**
	 * Creates the configuration based on a method signature.
	 * 
	 * @param testMethod
	 */
	public TestMethodConfiguration(JLCConfiguration<T> parent, MethodSignature message) {
		this(parent, message, IllegalArgumentException.class);
	}

	
	public TestMethodConfiguration(JLCConfiguration<T> parent, MethodSignature message,
			Class<? extends Exception> nullCheck) {
		super(parent, message, nullCheck);
		setMaximalParameterCount(TestMethods.DEFAULT_PARAM_COUNT);
	}

	public boolean matches(TestMethod obj) {
		return TestMethodAnnotationTool.matches(getSignature(), obj);
	}

	@Override
	public final Object invoke(Object[] parameterValues) throws InvocationTargetException {
		T newInstance = this.getParent().getObjectFactory().createThis();
		return invoke(newInstance, parameterValues);
	}

	public Object invoke(Object instance, Object[] parameterValues) throws InvocationTargetException {
		Object returnValue;
		if (hasExecutor()) {

			try {
				Method exe = getExecutor();
				exe.setAccessible(true);
				returnValue = exe.invoke(instance, parameterValues);
			} catch (IllegalAccessException | IllegalArgumentException e) {
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
		if (!equals && obj instanceof TestMethodConfiguration) {
			@SuppressWarnings("unchecked")
			TestMethodConfiguration<T> that = (TestMethodConfiguration<T>) obj;
			equals = this.getSignature().equals(that.getSignature());
		}
		return equals;
	}

	@Override
	public int compareTo(TestMethodConfiguration<T> that) {
		int compareTo = this.getSignature().compareTo(that.getSignature());
		return compareTo;
	}

	/**
	 * Apply the type when invoked using the test method.
	 * 
	 * @param t
	 * @see #TestMethodConfiguration(TestMethod)
	 */
	public void apply(Class<?> t) {
		if (!hasExecutor()) {
			Optional<Method> optionalMethod = MethodTool.findMethod(t, this.getSignature().getMethodPredicate());
			if (optionalMethod.isPresent()) {
				this.setExecutor(optionalMethod.get());
			}
		}
	}

	/**
	 * apply the enablement of this configuration based on the annotation.
	 * 
	 * @see #TestMethodConfiguration(MethodSignature, Class)
	 * 
	 * @param obj
	 */
	public void apply(TestMethod testMethod,int globalMaxParameterCount) {
		if (!matches(testMethod)) {
			throw new IllegalArgumentException("Test method does not match this.");
		}
		int maxParameterCount = testMethod.parameterCount();
		if (maxParameterCount <= TestMethod.NOT_CONFIGURED) {
			maxParameterCount = globalMaxParameterCount;
		}
		setMaximalParameterCount(maxParameterCount);
		setEnabled(testMethod.enabled());
		setInverted(testMethod.invert());
		setFeature(testMethod.feature());
		String archetypeName = testMethod.archetype().trim();
		if (!archetypeName.isEmpty()) {
			Archetype archetype = new Archetype(archetypeName);
			setArchetype(archetype);
		}
		setEnforced(true);
		setDeclared(true);
		setEnabled(testMethod.enabled());
		setDbcEnabled(!testMethod.disableDBC());
	}




}
