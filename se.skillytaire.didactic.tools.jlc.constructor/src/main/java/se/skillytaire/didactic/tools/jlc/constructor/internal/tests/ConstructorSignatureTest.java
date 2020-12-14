package se.skillytaire.didactic.tools.jlc.constructor.internal.tests;

import java.lang.reflect.Constructor;

import se.skillytaire.didactic.tools.jlc.constructor.spi.model.config.AbstractTestConfigurationTestNode;
import se.skillytaire.didactic.tools.jlc.constructor.spi.model.config.TestConstructorConfiguration;
import se.skillytaire.didactic.tools.jlc.signature.spi.ConstructorSignature;
import se.skillytaire.didactic.tools.jlc.signature.spi.SignatureTestFactory;
import se.skillytaire.didactic.tools.jlc.spi.model.config.JLCConfiguration;
import se.skillytaire.didactic.tools.jlc.spi.model.naming.BasicDisplayName;
import se.skillytaire.didactic.tools.jlc.spi.model.naming.DisplayName;

public class ConstructorSignatureTest<T> extends AbstractTestConfigurationTestNode<T, TestConstructorConfiguration<T>, ConstructorSignature<T>, Constructor<T>>{

	public ConstructorSignatureTest(TestConstructorConfiguration<T> config) {
		super(config);
	}

	@Override
	public void init(JLCConfiguration<T> configuration) {
		super.init(configuration);

		SignatureTestFactory.find(getTestConfiguration()).forEach(this::add);

		this.children().forEach(c->c.init(configuration));
	}
	@Override
	public DisplayName getDisplayName() {

		return new BasicDisplayName("Message");
	}

}
