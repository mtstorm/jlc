package se.skillytaire.didactic.tools.jlc.property.internal;

import java.lang.reflect.Method;
import java.util.logging.Logger;

import se.skillytaire.didactic.tools.jlc.api.Archetype;
import se.skillytaire.didactic.tools.jlc.api.JLCConfiguration;
import se.skillytaire.didactic.tools.jlc.method.internal.spi.TestMethodsConfiguration;
import se.skillytaire.didactic.tools.jlc.method.spi.model.config.TestMethodConfiguration;
import se.skillytaire.didactic.tools.jlc.spi.e.JLCFeatureConfiguration;
import se.skillytaire.didactic.tools.jlc.spi.e.JLCFeatureRegistry;
import se.skillytaire.didactic.tools.jlc.spi.ext.enforcer.AbstractMultiPartEnforcer;
import se.skillytaire.didactic.tools.jlc.spi.ext.feature.Enforcer;
import se.skillytaire.didactic.tools.jlc.spi.model.structure.JLCTestNode;
import se.skillytaire.didactic.tools.jlc.spi.util.ClassProperty;

public class TestPropertyMultiPartEnforcer<T> extends AbstractMultiPartEnforcer<TestPropertiesConfiguration, TestPropertyConfiguration<T>, T>{
	private Logger log = Logger.getLogger(TestPropertyMultiPartEnforcer.class.getName());
	public static final Archetype PROPERTY_ARCHETYPE = new Archetype("property");
	@Override
	public void enfoce(JLCConfiguration<T> jlcConfiguration, TestPropertyConfiguration<T> testConfiguration) {
		//find all the signatures and fields, should be enforced
		testConfiguration.getProperty()
		                 .methods()
		                 .peek(m-> log.fine("Before enforcing method to property "+m.toString()))
		                 .forEach(m ->  enfore(jlcConfiguration,m));

		//FIXME field
		
	}
	

	private void enfore(JLCConfiguration<T> jlcConfiguration, Method method) {
		
		Enforcer<T, TestMethodsConfiguration, TestMethodConfiguration<T>> enforcer = Enforcer.of(jlcConfiguration , method);
		enforcer.enforce(PROPERTY_ARCHETYPE);
		TestMethodConfiguration<T> tc =(TestMethodConfiguration<T>) enforcer.getTestConfiguration();
	//.	TestMethodConfiguration<T> config = jlcConfiguration.enforce(method, PROPERTY_ARCHETYPE);
		tc.setDbcEnabled(false);
	}
}
