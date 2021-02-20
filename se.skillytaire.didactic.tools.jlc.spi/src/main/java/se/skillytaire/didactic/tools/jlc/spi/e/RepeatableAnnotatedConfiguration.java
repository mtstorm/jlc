package se.skillytaire.didactic.tools.jlc.spi.e;

import java.lang.annotation.Annotation;
import java.lang.annotation.Repeatable;
//FIXME is een ding om op te zoeken...
public interface RepeatableAnnotatedConfiguration 
	< R extends Annotation & Repeatable,E extends Annotation> extends AnnotatedConfiguration<R>{

}
