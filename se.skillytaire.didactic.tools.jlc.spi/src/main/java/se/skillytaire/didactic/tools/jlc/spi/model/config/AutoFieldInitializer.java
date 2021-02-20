package se.skillytaire.didactic.tools.jlc.spi.model.config;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.Optional;

import se.skillytaire.didactic.tools.jlc.api.JLCConfigurationException;
import se.skillytaire.didactic.tools.jlc.api.TestObjectFactory;
import se.skillytaire.didactic.tools.jlc.api.VoidTestObjectFactory;
import se.skillytaire.didactic.tools.jlc.spi.internal.JLCConfigurationImpl;
import se.skillytaire.didactic.tools.jlc.spi.util.AnnotationTool;

public abstract class AutoFieldInitializer<A extends Annotation> {
	private final  Field field;
	private  JLCConfigurationImpl<?> configuration;
	private  Class<A> annotationType;
	private A annotation;





	private TestObjectFactory<?> factory;
	
	public AutoFieldInitializer(Field field) {
		super();
		this.field = field;
	    this.annotationType = (Class<A>) ((ParameterizedType) getClass()
	              .getGenericSuperclass()).getActualTypeArguments()[0];
	    this.annotation = field.getAnnotation(annotationType);
	}


	public final void configure(JLCConfigurationImpl<?> configuration, Object instance) {
		this.configuration = configuration;
		resolveFactory();
		Object value = invoke(this.factory);
		field.setAccessible(true);
		try {
			field.set(instance, value);
		} catch (Exception e) {
			throw new JLCConfigurationException("Unable to access the field "+ field.getName(), e);
		}
	}

	protected abstract Object invoke(TestObjectFactory<?> factory);
//invoker?????



	private void resolveFactory() {
		Optional<Class<? extends TestObjectFactory<?>>> optionalFactoryClass = AnnotationTool.getValue(annotation, "override");
		if(optionalFactoryClass.isPresent()) {
			Class<? extends TestObjectFactory<?>> factoryClass = optionalFactoryClass.get();
			if(factoryClass == VoidTestObjectFactory.class) {
				Optional<TestObjectFactory<Object>>  r = configuration.resolveFactory(field.getType());
				if(r.isPresent()) {
					this.factory = r.get();
				} else {
					String message = String.format("The field %s is annotated with %s, but there is no object factory found for type %s",
							   field.getName(), annotationType.getName(), field.getType());
					throw new JLCConfigurationException(message);
				}
			} else {
				try {
					this.factory = (TestObjectFactory<?>) factoryClass.getDeclaredConstructor().newInstance();
				} catch (Exception e) {
					String message = String.format("The field %s is annotated with %s, having declared an override test object factory %s, for type %s",
							   field.getName(), annotationType.getClass().getName(),factoryClass.getName(), field.getType());
					throw new JLCConfigurationException(message, e);
				}
			}
		} else {
			String msg = String.format("The field '%s' in test class '%s' is of type '%s'. JLC could not find a test object factory for it!", this.field.getName(), configuration.getTestInstance().getClass().getName(), this.field.getType());
			throw new JLCConfigurationException(msg);
		}
	}
	

}
