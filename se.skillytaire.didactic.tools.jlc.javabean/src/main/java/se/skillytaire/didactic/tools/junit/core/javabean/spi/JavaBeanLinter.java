package se.skillytaire.didactic.tools.junit.core.javabean.spi;

import se.skillytaire.didactic.tools.jlc.api.Archetype;
import se.skillytaire.didactic.tools.jlc.api.TestConfiguration;
import se.skillytaire.didactic.tools.jlc.lint.spi.AbstractLinter;
import se.skillytaire.didactic.tools.jlc.signature.spi.ConstructorSignature;

public class JavaBeanLinter<T> extends AbstractLinter<T> {

	public static final Archetype ARCHETYPE = new Archetype("JavaBean");

 
	public Archetype getArchetype() {
		return ARCHETYPE;
	}


	@Override
	public void enforce() {
		ConstructorSignature<T> noArgumentConstructor = create();
		
		TestConfiguration<?,T> ding = enforce(noArgumentConstructor);	

		
		
		
	}





}
