package se.skillytaire.didactic.tools.jlc.method.internal.tests.equals.trans;

import se.skillytaire.didactic.tools.jlc.method.spi.model.config.AbstractTestMethodConfigurationTestNode;
import se.skillytaire.didactic.tools.jlc.method.spi.model.config.TestMethodConfiguration;
import se.skillytaire.didactic.tools.jlc.spi.model.config.JLCConfiguration;
import se.skillytaire.didactic.tools.jlc.spi.model.naming.BasicDisplayName;
import se.skillytaire.didactic.tools.jlc.spi.model.naming.DisplayName;

public class EqualsTransiveTest<T> extends AbstractTestMethodConfigurationTestNode<T> {
	private static final String ASSERT_MESSAGE = "It is transitive: for any non-null reference values x,y,z if x.equals(y) returns true and y.equals(z) returns true, then x.equals(z) should return true.";			
			
			
	public EqualsTransiveTest(TestMethodConfiguration config) {
		super(config);
	}
	@Override
	public void init(JLCConfiguration<T> configuration) {
		super.init(configuration);
		
		T x = this.createThis();
		T y = this.createThat();
		
		DisplayName displayName = new BasicDisplayName("[x, x, x]");
		
		add(new EqualsTransiveTester<>(getTestConfiguration(), ASSERT_MESSAGE, x, x, x, displayName));
		displayName = new BasicDisplayName("[x, x, y]");
		add(new EqualsTransiveTester<>(getTestConfiguration(), ASSERT_MESSAGE, x, x, y, displayName));
		displayName = new BasicDisplayName("[x, y, x]");
		add(new EqualsTransiveTester<>(getTestConfiguration(), ASSERT_MESSAGE, x, y, x, displayName));
		displayName = new BasicDisplayName("[y, x, x]");
		add(new EqualsTransiveTester<>(getTestConfiguration(), ASSERT_MESSAGE, y, x, x, displayName));		
//		add(new SymmetricEqualsTestAA<T>(config,ASSERT_MESSAGE));
//		add(new SymmetricEqualsTestAB<T>(config,ASSERT_MESSAGE));
//		add(new SymmetricEqualsTestAObject<T>(config,ASSERT_MESSAGE));
		
		this.children().forEach(c->c.init(configuration));
	}
	@Override
	public DisplayName getDisplayName() {
		return new BasicDisplayName("Transitive");
	}


}
