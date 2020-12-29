package se.skillytaire.didactic.tools.jlc.spi.internal.invoke;

import java.lang.reflect.Field;

import se.skillytaire.didactic.tools.jlc.spi.internal.JLCConfigurationImpl;
//FIXME overdesign
public class InstanceFieldBuilderImpl implements InstanceFieldBuilder {
    private InstanceFieldInitializer product = new InstanceFieldInitializer();

    public InstanceFieldBuilderImpl(Object instance) {
       if (instance == null) {
          throw new IllegalArgumentException("instance of void");
       }
       
       product.setInstance(instance);
    }


    public InitializerBuilder configure(JLCConfigurationImpl<Object> conf) {
       if (conf == null) {
          throw new IllegalArgumentException("v of void");
       }
       product.setConfiguration(conf);
       return this;
    }
    
    @Override
    public InitializerBuilder using(Field field) {
       if (field == null) {
          throw new IllegalArgumentException("field of void");
       }
       product.setField(field);
       return this;
    }

    @Override
    public InstanceFieldInitializer initialize(Invoker<?> invoker) {
       if (invoker == null) {
          throw new IllegalArgumentException("invoker of void");
       }
       product.setInvoker(invoker);
       return product;
    }



 }