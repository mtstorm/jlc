package se.skillytaire.didactic.tools.jlc.method.internal.tests.signature;

import se.skillytaire.didactic.tools.jlc.api.JLCConfiguration;
import se.skillytaire.didactic.tools.jlc.method.spi.model.config.AbstractTestMethodConfigurationTestNode;
import se.skillytaire.didactic.tools.jlc.method.spi.model.config.TestMethodConfiguration;
import se.skillytaire.didactic.tools.jlc.signature.spi.SignatureTestFactory;
import se.skillytaire.didactic.tools.jlc.spi.model.naming.BasicDisplayName;
import se.skillytaire.didactic.tools.jlc.spi.model.naming.DisplayName;

public class MethodSignatureTest<T> extends AbstractTestMethodConfigurationTestNode<T>{

	public MethodSignatureTest(TestMethodConfiguration<T> config) {
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
