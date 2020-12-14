package se.skillytaire.didactic.tools.jlc.method.internal.tests;

import java.util.function.Function;
import java.util.function.Supplier;

import se.skillytaire.didactic.tools.jlc.method.spi.model.config.AbstractTestMethodConfigurationTestNode;
import se.skillytaire.didactic.tools.jlc.method.spi.model.config.TestMethodConfiguration;
import se.skillytaire.didactic.tools.jlc.spi.model.config.JLCConfiguration;
import se.skillytaire.didactic.tools.jlc.spi.model.naming.DisplayName;
import se.skillytaire.didactic.tools.jlc.spi.model.naming.SanityCheck;

public abstract class AbstractSanityTest<T,R,C extends AbstractSanityPartTest<T,R>> extends AbstractTestMethodConfigurationTestNode<T> implements Supplier<TestPartObjectFactory<T, R>>, Function<TestPartObjectFactory<T, R>, C>{

	public AbstractSanityTest(TestMethodConfiguration<T> config) {
		super(config);
	}

	@Override
	public final DisplayName getDisplayName() {
		return new SanityCheck();
	}

	@Override
	public void init(JLCConfiguration<T> configuration) {
		super.init(configuration);
	
//		TestObjectFactory<T> testFactory = configuration.getObjectFactory();
//
//		EqualsObjectFactoryInvokerPermutation<T> permutation 
//			= new EqualsObjectFactoryInvokerPermutation<T>(testFactory );
		
		TestPartObjectFactory<T, R> permutation = get();
		permutation.stream()
		           .map(this)
		           .forEach(this::add);

		this.children().forEach(c->c.init(configuration));
	}
}
