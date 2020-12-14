package se.skillytaire.didactic.tools.jlc.method.internal.tests.hashcode;

import java.util.function.Supplier;

import se.skillytaire.didactic.tools.jlc.api.TestObjectFactory;
import se.skillytaire.didactic.tools.jlc.method.internal.tests.NoDifferencReferenceObjectFactory;
import se.skillytaire.didactic.tools.jlc.signature.spi.Signature;
import se.skillytaire.didactic.tools.jlc.spi.model.naming.BasicDisplayName;
import se.skillytaire.didactic.tools.jlc.spi.model.naming.DisplayName;

public class HashCodeSanityObjectFactory<T> extends NoDifferencReferenceObjectFactory<T, Boolean>{

	public HashCodeSanityObjectFactory(TestObjectFactory<T> testFactory) {
		super(testFactory);
	}

	@Override
	public String testThisRoleName() {
		return getRoleName(getCounter());
	}

	@Override
	public String testThatRoleName() {
		return getRoleName(getCounter());
	}


	@Override
	public Boolean getExpectedResult() {
		return true;
	}

	@Override
	protected Supplier<T> getThisSupplier() {
		return getSupplier(getCounter());
	}

	@Override
	protected Supplier<T> getThatSupplier() {
		return getSupplier(getCounter());
	}

	@Override
	public DisplayName getDisplayName(Signature signature) {
		String varX = testThisRoleName();
		String varY = testThisRoleName();
		String name = new StringBuilder()
				.append(signature.getSignatureTestDescriptionWithRef(varX))
				.append(" == ")
				.append(signature.getSignatureTestDescriptionWithRef(varY))
				.append(" == ")
				.append(getExpectedResult())
				.toString();
		return new BasicDisplayName(name);
	}

	@Override
	public String getAssertMessage(Signature signature) {
		return "Two different objects references with the same contents should have the same hashcode.";
	}
}
