package se.skillytaire.didactic.tools.jlc.method.spi.model.structure;

import se.skillytaire.didactic.tools.jlc.method.spi.model.config.TestMethodConfiguration;

public abstract class AbstractPartialSingleMethodTestNode<T> extends AbstractSingleMethodTestNode<T> {


	public AbstractPartialSingleMethodTestNode(TestMethodConfiguration<T> testConfiguration,String assertMessage) {
		super(testConfiguration);
		this.assertMessage = assertMessage;
	}
	public AbstractPartialSingleMethodTestNode(TestMethodConfiguration<T> testConfiguration) {
		this(testConfiguration, null);
	}
	@Override
	public final String getAssertMessage() {
		return assertMessage;
	}
	private String assertMessage;
	protected void setAssertMessage(String assertMessage) {
		this.assertMessage = assertMessage;
	}


}
