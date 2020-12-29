package se.skillytaire.didactic.tools.jlc.method.internal.tests.equals.sym;

import se.skillytaire.didactic.tools.jlc.api.JLCConfiguration;
import se.skillytaire.didactic.tools.jlc.method.spi.model.config.AbstractTestMethodConfigurationTestNode;
import se.skillytaire.didactic.tools.jlc.method.spi.model.config.TestMethodConfiguration;
import se.skillytaire.didactic.tools.jlc.spi.model.naming.BasicDisplayName;
import se.skillytaire.didactic.tools.jlc.spi.model.naming.DisplayName;

public class EqualsSymetrixTest<T> extends AbstractTestMethodConfigurationTestNode<T> {
	private static final String ASSERT_MESSAGE = "It is symmetric: for any non-null reference values x and y, x.equals(y) should return true if and only if y.equals(x) returns true.";
	public EqualsSymetrixTest(TestMethodConfiguration<T> config) {
		super(config);
	}
	@Override
	public void init(JLCConfiguration<T> configuration) {
		super.init(configuration);
		add(new SymmetricEqualsTestAA<T>(getTestConfiguration(),ASSERT_MESSAGE));
		add(new SymmetricEqualsTestAB<T>(getTestConfiguration(),ASSERT_MESSAGE));
		add(new SymmetricEqualsTestAObject<T>(getTestConfiguration(),ASSERT_MESSAGE));
		
		this.children().forEach(c->c.init(configuration));
	}
	@Override
	public DisplayName getDisplayName() {
		return new BasicDisplayName("Symmetric");
	}


}
