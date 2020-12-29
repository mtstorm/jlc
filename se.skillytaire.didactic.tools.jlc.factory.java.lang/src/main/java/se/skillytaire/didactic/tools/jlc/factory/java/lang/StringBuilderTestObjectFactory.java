package se.skillytaire.didactic.tools.jlc.factory.java.lang;

import se.skillytaire.didactic.tools.jlc.api.AbstractTestObjectFactory;
import se.skillytaire.didactic.tools.jlc.api.JLCTestFactory;

@JLCTestFactory(path = "java/lang")
public class StringBuilderTestObjectFactory extends AbstractTestObjectFactory<StringBuilder> {

	@Override
	public StringBuilder createThis() {
		return new StringBuilder("this");
	}

	@Override
	public StringBuilder createThat() {
		return new StringBuilder("that");
	}

}
