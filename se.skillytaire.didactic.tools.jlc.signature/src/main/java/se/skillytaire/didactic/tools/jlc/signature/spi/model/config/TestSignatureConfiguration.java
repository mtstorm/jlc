package se.skillytaire.didactic.tools.jlc.signature.spi.model.config;

import java.lang.reflect.Executable;
import java.lang.reflect.InvocationTargetException;
import java.util.Optional;

import se.skillytaire.didactic.tools.jlc.signature.spi.Signature;
import se.skillytaire.didactic.tools.jlc.spi.model.config.TestConfiguration;

/**
 * Every constructor or message has this type of configuration.
 * @author Skillytaire AB
 *
 * @param <S>
 */
public interface TestSignatureConfiguration<N extends TestSignatureConfiguration<N,T,S,E>,  T,S extends Signature,E extends Executable> extends TestConfiguration<N,T> {

   S getSignature();
   boolean isDeclared();
   
   public default boolean isForSignature(S signature) {
      return this.getSignature().equals(signature);
   }
   
   /**
    * Makes a signature and adding the values to its parameters.
    * @param paramValues
    * @return
    */
   public default String getTestDescription(String... paramValues) {
      StringBuilder builder = new StringBuilder();
      String x = this.getSignature().getSignatureTestDescription(paramValues);
      builder.append(x);
      
      return builder.toString();
   }
   /**
    * Makes a pice of code having the reference name using the signature and adding the values to its parameters.
    * @param paramValues
    * @return
    */
   public default String getTestDescriptionWithReference(String referenceName, String... paramValues) {
      StringBuilder builder = new StringBuilder(referenceName);
      builder.append('.');
      builder.append(getTestDescription(paramValues));
      return builder.toString();
   }
   /**
    * Get the declaring class where this is at.
    * @return
    */
   public Optional<Class<?>> declaringClass();



int getMaximalParameterCount();
public boolean isVisible() ;

 public boolean isInvisible() ;
 
 /**
  * Invoke the underlaying signature
  * @param parameters
  * @return
 * @throws InvocationTargetException 
  */
 Object invoke(Object[] parameterValues) throws InvocationTargetException;


Class<? extends Throwable> getNullCheck();
 boolean isDbcEnabled();
E getExecutor();
boolean hasExecutor();
}
