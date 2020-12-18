package se.skillytaire.didactic.tools.jlc.api.a;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.util.Optional;

import se.skillytaire.didactic.tools.jlc.api.JLCConfigurationException;
import se.skillytaire.didactic.tools.jlc.api.TestObjectFactory;
import se.skillytaire.didactic.tools.jlc.spi.internal.VoidTestObjectFactory;
import se.skillytaire.didactic.tools.jlc.spi.model.config.JLCConfiguration;
import se.skillytaire.didactic.tools.jlc.spi.util.AnnotationTool;

public abstract class AutoFieldInitializer<A extends Annotation> {
	private final  Field field;
	//private  Object instance;
	private  JLCConfiguration<?> configuration;
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


	public void configure(JLCConfiguration<?> configuration, Object instance) {
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
		}
	}
	

}
