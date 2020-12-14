package se.skillytaire.didactic.tools.jlc.method.internal.tests;

import java.util.Iterator;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Stream;

import se.skillytaire.didactic.tools.jlc.api.TestObjectFactory;
import se.skillytaire.didactic.tools.jlc.signature.spi.Signature;
import se.skillytaire.didactic.tools.jlc.spi.model.naming.DisplayName;

public interface TestPartObjectFactory<T, R> extends TestObjectFactory<T>, 
Iterator< TestPartObjectFactory<T, R>>,
Supplier<Optional<TestObjectFactory<T>>> {
	String testThisRoleName();
	String testThatRoleName();
	String getAssertMessage(Signature signature);
	
	DisplayName getDisplayName(Signature signature);
	R getExpectedResult();
	Stream<TestPartObjectFactory<T, R>> stream();
}
