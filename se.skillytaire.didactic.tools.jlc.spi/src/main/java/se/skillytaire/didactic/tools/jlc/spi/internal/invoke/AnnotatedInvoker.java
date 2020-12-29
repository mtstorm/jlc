package se.skillytaire.didactic.tools.jlc.spi.internal.invoke;

import java.lang.annotation.Annotation;

import se.skillytaire.didactic.tools.jlc.api.TestObjectFactory;
import se.skillytaire.didactic.tools.jlc.spi.internal.JLCConfigurationImpl;
@Deprecated
public abstract class AnnotatedInvoker<A extends Annotation, T, F extends TestObjectFactory<T>> implements Invoker<T> {
    private final Class<A> annotation;
    private final F override;
    
    protected AnnotatedInvoker(Class<A> annotation) {
       this(annotation, null);
    }
    protected AnnotatedInvoker(Class<A> annotation,F override) {
       if(annotation == null) {
          throw new IllegalArgumentException("annotation is void");
       }
       this.annotation = annotation;
       this.override = override;
    }
    
    public final Class<A> getAnnotation() {
       return annotation;
    }

    public boolean hasOverride() {
       return this.override != null;
    }
    protected F getOverride() {
       return override;
    }
    
    protected abstract T createOverride();
    protected abstract T doCreate(JLCConfigurationImpl<?> configuration,Class<?> type);
    
    public final T create(JLCConfigurationImpl<?> configuration, Class<?> type) {
    //public final T create(Class<?> type) {
    	T result;
       if(hasOverride() && this.override.isTypeFor(type)) {
          result = createOverride();
       }else {
          result = doCreate(configuration,type);
       }
       return result;
    }
 }