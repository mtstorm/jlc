package se.skillytaire.didactic.tools.junit.core.javabean.spi;

import java.util.Optional;

import se.skillytaire.didactic.tools.jlc.api.Archetype;
import se.skillytaire.didactic.tools.jlc.lint.spi.ArchetypeResolver;
import se.skillytaire.didactic.tools.junit.core.javabean.api.JavaBean;

public class JavaBeanResolver<T> implements ArchetypeResolver<T>{

	@Override
	public Optional<Archetype> resolve(Class<?> testInstance, Class<T> beanUnderTest) {
		Archetype result = null;
		if(testInstance.isAnnotationPresent(JavaBean.class) || beanUnderTest.isAnnotationPresent(JavaBean.class)) {
			result = JavaBeanLinter.ARCHETYPE;
		}
		return Optional.ofNullable(result);
	}

}
