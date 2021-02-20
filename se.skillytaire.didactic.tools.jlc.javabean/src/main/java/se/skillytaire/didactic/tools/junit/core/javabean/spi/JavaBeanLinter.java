package se.skillytaire.didactic.tools.junit.core.javabean.spi;

import se.skillytaire.didactic.tools.jlc.api.Archetype;
import se.skillytaire.didactic.tools.jlc.constructor.api.TestConstructors;
import se.skillytaire.didactic.tools.jlc.constructor.internal.spi.TestConstructorsConfiguration;
import se.skillytaire.didactic.tools.jlc.constructor.spi.model.config.TestConstructorConfiguration;
import se.skillytaire.didactic.tools.jlc.lint.spi.AbstractLinter;
import se.skillytaire.didactic.tools.jlc.signature.spi.ConstructorSignature;
import se.skillytaire.didactic.tools.jlc.spi.TestConfiguration;
import se.skillytaire.didactic.tools.jlc.spi.ext.feature.Enforcer;
import se.skillytaire.didactic.tools.jlc.spi.util.ClassTool;

public class JavaBeanLinter<T> extends AbstractLinter<T> {

	public static final Archetype ARCHETYPE = new Archetype("JavaBean");
 
	public Archetype getArchetype() {
		return ARCHETYPE;
	}


	@Override
	public void enforce() {
		ConstructorSignature<T> noArgumentConstructor = create();
		enforce(noArgumentConstructor);
	   Class<?> beanUnderTestType = getConfiguration().getBeanUnderTestType();
	   ClassTool cl = new ClassTool(beanUnderTestType);
	   cl.properties().forEach(this::enforce);
		
		//builder for the init
	}








}
