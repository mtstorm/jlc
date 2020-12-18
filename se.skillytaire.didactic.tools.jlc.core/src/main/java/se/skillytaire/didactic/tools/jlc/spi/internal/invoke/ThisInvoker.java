package se.skillytaire.didactic.tools.jlc.spi.internal.invoke;

import se.skillytaire.didactic.tools.jlc.api.TestObjectFactory;
import se.skillytaire.didactic.tools.jlc.api.This;
import se.skillytaire.didactic.tools.jlc.spi.model.config.JLCConfiguration;

public final class ThisInvoker<T> extends AnnotatedInvoker<This, T, TestObjectFactory<T>>{
    
    public ThisInvoker(TestObjectFactory<T> override) {
       super(This.class, override);
    }

    public ThisInvoker() {
       super(This.class);
    }
    public T doCreate(JLCConfiguration<?> configuration,  Class<?> type) {
       return configuration.getThisInstance(type);
    }
    @Override
    protected T createOverride() {
       return this.getOverride().createThis();
    }

 }