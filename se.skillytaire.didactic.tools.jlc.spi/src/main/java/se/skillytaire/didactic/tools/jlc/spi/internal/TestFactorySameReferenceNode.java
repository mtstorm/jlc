package se.skillytaire.didactic.tools.jlc.spi.internal;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.net.URI;
import java.util.Optional;

import se.skillytaire.didactic.tools.jlc.spi.model.naming.BasicDisplayName;
import se.skillytaire.didactic.tools.jlc.spi.model.naming.DisplayName;
import se.skillytaire.didactic.tools.jlc.spi.model.structure.AbstractJLCTestNode;
import se.skillytaire.didactic.tools.jlc.spi.model.structure.JLCSingleTestNode;

public class TestFactorySameReferenceNode<T> extends AbstractJLCTestNode<T> implements JLCSingleTestNode<T>{
	private final TestFactoryInvoker<?> function;
	
	public TestFactorySameReferenceNode(TestFactoryInvoker<?> function) {
		super();
		this.function = function;
	}

	@Override
	public DisplayName getDisplayName() {
	
		return new BasicDisplayName(getAssertMessage());
	}

	@Override
	public Optional<URI> toUri() {
		return Optional.of(function.toUri());
	}

	@Override
	public void execute() throws Throwable {
		Object a = function.get();
		Object b = function.get();
		assertTrue(a == b, getAssertMessage());
	}

	@Override
	public String getAssertMessage() {
		return function.getMessageName() +" == " + function.getMessageName();
	}


}
