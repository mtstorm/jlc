package se.skillytaire.didactic.tools.jlc.factory.java.lang;

import se.skillytaire.didactic.tools.jlc.api.AbstractComparableTestObjectFactory;
import se.skillytaire.didactic.tools.jlc.api.ImmutableType;
import se.skillytaire.didactic.tools.jlc.api.JLCTestFactory;
import se.skillytaire.didactic.tools.jlc.api.Pooled;

@ImmutableType
@JLCTestFactory(path = "java/lang")
@Pooled
public class StringTestObjectFactory extends AbstractComparableTestObjectFactory<String> {

	@Override
	public String createThis() {
		return "Midas";
	}

	@Override
	public String createThat() {
		return "Tiecho";
	}

	@Override
	public String createLessThen() {
		return "Joseline";
	}

	@Override
	public String createGreaterThen() {
		return "Vince";
	}

}
