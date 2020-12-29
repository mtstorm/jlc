package se.skillytaire.didactic.tools.jlc.factory.java.lang;

import se.skillytaire.didactic.tools.jlc.api.AbstractTestObjectFactory;
import se.skillytaire.didactic.tools.jlc.api.JLCTestFactory;

@JLCTestFactory(path = "java/lang")
public class StringBufferTestObjectFactory extends AbstractTestObjectFactory<StringBuffer> {

	@Override
	public StringBuffer createThis() {
		return new StringBuffer("this");
	}

	@Override
	public StringBuffer createThat() {
		return new StringBuffer("that");
	}

}
