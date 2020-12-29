package se.skillytaire.didactic.tools.jlc.spi.internal.invoke;

import se.skillytaire.didactic.tools.jlc.api.TestObjectFactory;
import se.skillytaire.didactic.tools.jlc.api.That;
import se.skillytaire.didactic.tools.jlc.spi.internal.JLCConfigurationImpl;

public final class ThatInvoker<T> extends AnnotatedInvoker<That, T, TestObjectFactory<T>>{

    public ThatInvoker(TestObjectFactory<T> override) {
       super(That.class, override);
    }
    public ThatInvoker() {
       super(That.class);
    }

    public T doCreate(JLCConfigurationImpl<?> configuration, Class<?> type) {
       return configuration.getThatInstance(type);
    }
    @Override
    protected T createOverride() {
       return this.getOverride().createThat();
    }

 }