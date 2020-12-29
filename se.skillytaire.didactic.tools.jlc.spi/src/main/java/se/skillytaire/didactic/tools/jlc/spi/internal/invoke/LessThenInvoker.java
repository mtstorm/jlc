package se.skillytaire.didactic.tools.jlc.spi.internal.invoke;

import se.skillytaire.didactic.tools.jlc.api.ComparableTestObjectFactory;
import se.skillytaire.didactic.tools.jlc.api.LessThen;
import se.skillytaire.didactic.tools.jlc.spi.internal.JLCConfigurationImpl;

public final class LessThenInvoker<T> extends AnnotatedInvoker<LessThen, T,ComparableTestObjectFactory<T>>{
    
    public LessThenInvoker(ComparableTestObjectFactory<T> override) {
       super(LessThen.class, override);
    }
    public LessThenInvoker() {
       super(LessThen.class);
    }
    public T doCreate(JLCConfigurationImpl<?> configuration, Class<?> type) {
       return configuration.getLessThenInstance(type);
    }
    @Override
    protected T createOverride() {
       return this.getOverride().createLessThen();
    }
}
