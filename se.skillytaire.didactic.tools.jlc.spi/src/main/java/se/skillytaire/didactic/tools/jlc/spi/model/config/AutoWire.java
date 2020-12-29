package se.skillytaire.didactic.tools.jlc.spi.model.config;

import se.skillytaire.didactic.tools.jlc.api.GreaterThen;
import se.skillytaire.didactic.tools.jlc.api.JLCConfiguration;
import se.skillytaire.didactic.tools.jlc.api.LessThen;
import se.skillytaire.didactic.tools.jlc.api.That;
import se.skillytaire.didactic.tools.jlc.api.This;
import se.skillytaire.didactic.tools.jlc.spi.internal.JLCConfigurationImpl;
import se.skillytaire.didactic.tools.jlc.spi.util.ClassTool;

public class AutoWire {

	
	public AutoWire(JLCConfigurationImpl<?> configuration, Object instance) {
		//FIXME would be nice if pure functional
		ClassTool classTool = new ClassTool(instance.getClass());
	       
		classTool.fields(This.class)
		.map(ThisFieldInitializer::new)
		.forEach( i -> i.configure(configuration, instance));

		
		classTool.fields(That.class)
		.map(ThatFieldInitializer::new)
		.forEach( i -> i.configure(configuration, instance));
		
		classTool.fields(LessThen.class)
		.map(LessThenFieldInitializer::new)
		.forEach( i -> i.configure(configuration, instance));
		
		classTool.fields(GreaterThen.class)
		.map(GreaterThenFieldInitializer::new)
		.forEach( i -> i.configure(configuration, instance));
		
		classTool.getInstanceFields()
				 .stream()
				 .filter( f -> JLCConfiguration.class.isAssignableFrom( f.getType()))
				 .forEach( f -> {
					 f.setAccessible(true);
					 try {
						f.set(instance, configuration);
					} catch (IllegalArgumentException | IllegalAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				 });
				
	}
	
//	private AutoFieldInitializer<?> get(Class<? extends Annotation> annotationType) {
//		AutoFieldInitializer<?> initializer
//	}
}
