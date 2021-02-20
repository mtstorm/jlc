package se.skillytaire.didactic.tools.jlc.signature.spi;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

import se.skillytaire.didactic.tools.jlc.spi.PredicateBuilder;


public final class MethodSignature extends Signature implements Comparable<MethodSignature>{
	private Class<?> returnType;
   
	private Method method;
	/**
	 * Gets the underlaying method if it exist
	 * @return null when this is not based on a method.
	 */
   public Method getMethod() {
		return method;
	}

/**
    * After this has been invoked it is not considered an API call
    * @param method
    */
   public MethodSignature(Method method) {
      this(method.getDeclaringClass(), method.getReturnType(), method.getName(),false,
            method.getParameterTypes());
      this.method = method;
   }

   public MethodSignature(Class<?> declaringClass, Class<?> returnType, String name,boolean api,
                          Class<?>... paramTypes) {
      super(declaringClass, name, paramTypes);
      setApi(api);
      this.returnType = returnType;
   
   }
   
   public MethodSignature(Class<?> declaringClass, Class<?> returnType, String name,
           Class<?>... paramTypes) {
	   this(declaringClass, returnType, name,true, paramTypes);
   }
   public MethodSignature(Class<?> declaringClass, String name, Class<?>... paramTypes) {
      this(declaringClass, void.class, name,true, paramTypes);
   }
   public MethodSignature(Class<?> declaringClass, Class<?> returnType, String name) {
      this(declaringClass, returnType, name,true, new Class<?>[0]);
   }
   public MethodSignature(Class<?> declaringClass, String name) {
      this(declaringClass, name, new Class<?>[0]);
   }


   // public MethodSignature(Class<?> returnType2, String name,
   // TypeVariable<Method>[] typeParameters) {
   // // typeParameters[0].
   // }

   public String getMethodDescription() {
      StringBuilder builder = new StringBuilder();
      builder.append(this.returnType.getName());
      builder.append(' ');
      builder.append(this.toString(true));
      return builder.toString();
   }

   /**
    * The basic predicate for the method
    * <ol>
    * <li>public: the method must be public.</li>
    * <li>name: the name of the method must match.</li>
    * <li>return type: the return type of the message is never null and must be
    * assignable to the given return type.</li>
    * <li>parameter count: the number of parameter must match.</li>
    * </ol>
    * 
    * @param beanType
    *           The type of the actual bean.
    * @return
    */
   public Predicate<Method> getMethodPredicate() {
      // Class<?> returnType = getReturnType(beanType);
      // String messageName = this.getMessage();
      Predicate<Method> publicMethodPredicate =
            (m) -> !Modifier.isPrivate(m.getModifiers());
      // FIXME visibility aanleggen?
      publicMethodPredicate = (m) -> !Modifier.isAbstract(m.getModifiers());
      Predicate<Method> r = publicMethodPredicate
            .and((m) -> m.getName().equals(getName()))
            .and((m) -> m.getReturnType() != null)
            .and((m) -> m.getParameterCount() == getParameterCount())
            .and((m) -> sameParameterSequence(m.getParameterTypes()))
            .and((m) -> m.getReturnType().isAssignableFrom(this.returnType));
      return r;
   }

   /**
    * @return the returnType
    */
   public Class<?> getReturnType() {
      return returnType;
   }

   /**
    * Can forces the return type to be covariant.
    * 
    * @param returnType
    *           the returnType to set
    */
   public void setReturnType(Class<?> returnType) {
      this.returnType = returnType;
   }

//   public <T> TestMethodConfiguration<T> toTestMethodConfiguration() {
//      MethodSignature clone = (MethodSignature) super.clone();
//      return new TestMethodConfiguration<>(getparent clone);
//   }




   @Override
   public String toString() {
      return getReturnType().getName() + " " + super.toString();
   }

@Override
public int compareTo(MethodSignature that) {
	int compareTo = this.getName().compareTo(that.getName());
	if(compareTo == 0) {
		compareTo = compareParameterSequence(that.getParameterTypes());
		if(compareTo == 0) {
			compareTo = this.getReturnType().getName().compareTo(that.getReturnType().getName());
		}
	}
	return compareTo;
}


private static final String TO_STRING = "toString";
private static final String HASH_CODE = "hashCode";
private static final String EQUALS = "equals";
private static final String CLONE = "clone";
private static final String FINALIZE = "finalize";
private static final String GET_CLASS = "getClass";
private static final String NOTIFY = "notify";
private static final String NOTIFY_ALL = "notifyAll";
private static final String WAIT = "wait";
private static final String REGISTER_NATIVES = "registerNatives";


public static final MethodSignature TO_STRING_METHOD_SIGNATURE =
	         new MethodSignature(null, String.class, TO_STRING);
	   public static final MethodSignature HASH_CODE_METHOD_SIGNATURE =
	         new MethodSignature(null,int.class, HASH_CODE);
	   public static final MethodSignature EQUALS_METHOD_SIGNATURE =
	         new MethodSignature(null,boolean.class, EQUALS, Object.class);
	   public static final MethodSignature CLONE_METHOD_SIGNATURE =
	         new MethodSignature(null,Object.class, CLONE);
	   /**
	    * Method signature for the protected finalize-method. 
	    */
	   public static final MethodSignature FINALIZE_METHOD_SIGNATURE =
	         new MethodSignature(null,FINALIZE);
	   /**
	    * Method signature for getClass. 
	    * @see Object#getClass()
	    */
	   public static final MethodSignature GET_CLASS_METHOD_SIGNATURE =
		         new MethodSignature(null,Class.class, GET_CLASS);
	   
	   /**
	    * Method signature for notify. 
	    * @see Object#notify()
	    */
	   public static final MethodSignature NOTIFY_METHOD_SIGNATURE =
		         new MethodSignature(null,NOTIFY);
	   /**
	    * Method signature for notifyAll. 
	    * @see Object#notifyAll()
	    */
	   public static final MethodSignature NOTIFY_ALL_METHOD_SIGNATURE =
		         new MethodSignature(null,NOTIFY_ALL);
	   /**
	    * Method signature for wait with parameter using a long for the timeout. 
	    * @see Object#wait(long)
	    */
	   public static final MethodSignature WAIT_TIMEOUT_METHOD_SIGNATURE =
		         new MethodSignature(null,WAIT,long.class);
	   
	   /**
	    * Method signature for wait with parameter using a long for the timeout. 
	    * @see Object#wait(long, int)
	    */
	   public static final MethodSignature WAIT_TIMEOUT_2_METHOD_SIGNATURE =
		         new MethodSignature(null,WAIT,long.class,int.class);
	   
	   /**
	    * Method signature for wait without parameters. 
	    * @see Object#wait()
	    */
	   public static final MethodSignature WAIT_METHOD_SIGNATURE =
		         new MethodSignature(null,WAIT);
	   /**
	    * Method signature for private method registerNatives. 
	    */
		public static final MethodSignature REGISTER_NATIVES_METHOD_SIGNATURE =
		         new MethodSignature(null,REGISTER_NATIVES);
	   
		
		public static final Predicate<MethodSignature> NON_TESTABLES;
		
		static {
			PredicateBuilder<MethodSignature> builder =
					PredicateBuilder.nof(FINALIZE_METHOD_SIGNATURE::equals);
			builder.nand(GET_CLASS_METHOD_SIGNATURE::equals)
			       .nand(NOTIFY_METHOD_SIGNATURE::equals)
			       .nand(NOTIFY_ALL_METHOD_SIGNATURE::equals)
			       .nand(WAIT_TIMEOUT_METHOD_SIGNATURE::equals)
			       .nand(WAIT_TIMEOUT_2_METHOD_SIGNATURE::equals)
			       .nand(WAIT_METHOD_SIGNATURE::equals)
			       .nand(REGISTER_NATIVES_METHOD_SIGNATURE::equals);
			NON_TESTABLES = builder.build();
		}
		
		
		private static List<MethodSignature> apiSignatures = new ArrayList<MethodSignature>();
		static {
			apiSignatures.add(MethodSignature.CLONE_METHOD_SIGNATURE);
			apiSignatures.add(MethodSignature.EQUALS_METHOD_SIGNATURE);
			apiSignatures.add(MethodSignature.FINALIZE_METHOD_SIGNATURE);
			apiSignatures.add(MethodSignature.GET_CLASS_METHOD_SIGNATURE);
			apiSignatures.add(MethodSignature.HASH_CODE_METHOD_SIGNATURE);
			apiSignatures.add(MethodSignature.NOTIFY_METHOD_SIGNATURE);
			apiSignatures.add(MethodSignature.NOTIFY_ALL_METHOD_SIGNATURE);
			apiSignatures.add(MethodSignature.REGISTER_NATIVES_METHOD_SIGNATURE);
			apiSignatures.add(MethodSignature.TO_STRING_METHOD_SIGNATURE);
			apiSignatures.add(MethodSignature.WAIT_METHOD_SIGNATURE);
			apiSignatures.add(MethodSignature.WAIT_TIMEOUT_METHOD_SIGNATURE);
			apiSignatures.add(MethodSignature.WAIT_TIMEOUT_2_METHOD_SIGNATURE);
		}
		
		public static Stream<MethodSignature> defaultApi() {
			return apiSignatures.stream();
		}
		

}
