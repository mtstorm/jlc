package se.skillytaire.didactic.tools.jlc.api.a;

import java.lang.annotation.Annotation;
import java.util.stream.Stream;
import java.util.stream.Stream.Builder;

import se.skillytaire.didactic.tools.jlc.api.GreaterThen;
import se.skillytaire.didactic.tools.jlc.api.LessThen;
import se.skillytaire.didactic.tools.jlc.api.That;
import se.skillytaire.didactic.tools.jlc.api.This;
import se.skillytaire.didactic.tools.jlc.spi.internal.invoke.ClassTool;
import se.skillytaire.didactic.tools.jlc.spi.model.config.JLCConfiguration;

public class AutoWire {

	
	public AutoWire(JLCConfiguration<?> configuration, Object instance) {
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
	}
	
//	private AutoFieldInitializer<?> get(Class<? extends Annotation> annotationType) {
//		AutoFieldInitializer<?> initializer
//	}
}
