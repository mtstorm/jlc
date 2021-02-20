package se.skillytaire.didactic.tools.jlc.spi.e;

import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
/**
 * A convenient abstraction for the annotated configuration factory.
 * @author prolector
 *
 * @param <T>
 * @param <C>
 * @param <A>
 */
public abstract class AbstractAnnotatedConfigurationFactory<T, C extends AnnotatedConfiguration<A>, A extends Annotation> 
	implements AnnotatedConfigurationFactory<T,C,A> {
	private  Class<A> type;
	public AbstractAnnotatedConfigurationFactory() {
		ParameterizedType x = (ParameterizedType) getClass().getGenericSuperclass();
		type =  (Class<A>) x.getActualTypeArguments()[2];
//		type = (Class<A>) f.getRawType();

	}
	@Override
	public final boolean isFor(Class<?> aType) {
		return type == aType;
	}
   @Override
   public Class<?> type() {
      // TODO Auto-generated method stub
      return type;
   }
}
