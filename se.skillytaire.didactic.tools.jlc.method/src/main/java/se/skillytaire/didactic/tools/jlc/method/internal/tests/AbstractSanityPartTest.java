package se.skillytaire.didactic.tools.jlc.method.internal.tests;

import se.skillytaire.didactic.tools.jlc.method.spi.model.config.TestMethodConfiguration;
import se.skillytaire.didactic.tools.jlc.method.spi.model.structure.AbstractSingleMethodTestNode;
import se.skillytaire.didactic.tools.jlc.signature.spi.Signature;
import se.skillytaire.didactic.tools.jlc.spi.model.naming.DisplayName;

public abstract class AbstractSanityPartTest<T,R> extends  AbstractSingleMethodTestNode<T> {
	protected final T x;
	protected final T y;
	protected final R expected;
	private final String msg;
	private final DisplayName dn;
	protected AbstractSanityPartTest(TestMethodConfiguration testConfiguration,TestPartObjectFactory<T, R> factory) {
		super(testConfiguration);
		this.x = factory.createThis();
		this.y = factory.createThat();
		this.expected = factory.getExpectedResult();
		Signature signature = testConfiguration.getSignature();
		msg = factory.getAssertMessage(signature);//testConfiguration.getTestDescriptionWithReference("x", "y")+" == "+ expected;
		dn = factory.getDisplayName(signature);
	}
	@Override
	public final String getAssertMessage() {
		return msg;
	}

	@Override
	public final DisplayName getDisplayName() {
		return dn;
	}
}
