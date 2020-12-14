package se.skillytaire.didactic.tools.jlc.method.internal.tests.equals.sanity;

import java.util.function.Supplier;

import se.skillytaire.didactic.tools.jlc.api.TestObjectFactory;
import se.skillytaire.didactic.tools.jlc.method.internal.tests.AbstractFullObjectFactoryInvokerPermutation;
import se.skillytaire.didactic.tools.jlc.signature.spi.Signature;
import se.skillytaire.didactic.tools.jlc.spi.model.naming.BasicDisplayName;
import se.skillytaire.didactic.tools.jlc.spi.model.naming.DisplayName;

public class EqualsObjectFactoryInvokerPermutation<T> extends AbstractFullObjectFactoryInvokerPermutation<T, Boolean> {

	public EqualsObjectFactoryInvokerPermutation(TestObjectFactory<T> testFactory) {
		super(testFactory);
	}

	private int getThisIndex() {
		return (this.getCounter() / getPower());
	}
	private int getThatIndex() {
		return (this.getCounter() % getPower());
	}
	@Override
	protected Supplier<T> getThisSupplier() {
		return getSupplier(getThisIndex());
	}

	@Override
	protected Supplier<T> getThatSupplier() {
		return getSupplier(getThatIndex());
	}
	
	@Override
	public String testThisRoleName() {
		return getRoleName(getThisIndex());
	}

	@Override
	public String testThatRoleName() {
		return getRoleName(getThatIndex());
	}

	@Override
	public Boolean getExpectedResult() {
		return  getCounter() % getPower() == getCounter() / getPower();
	}
	
	@Override
	public String getAssertMessage(Signature signature) {
		String caller = signature.getSignatureTestDescriptionWithRef(testThisRoleName(), testThatRoleName());
		Boolean expectedResult = getExpectedResult();
		String assertMessage = String.format("%s == %s",caller,  expectedResult);
		return assertMessage;
	}

	@Override
	public DisplayName getDisplayName(Signature signature) {
		String name = getAssertMessage(signature);
		return new BasicDisplayName(name);
	}









}
