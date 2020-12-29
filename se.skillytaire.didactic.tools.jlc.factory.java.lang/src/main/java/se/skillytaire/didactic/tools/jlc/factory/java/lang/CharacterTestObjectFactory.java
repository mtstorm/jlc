package se.skillytaire.didactic.tools.jlc.factory.java.lang;

import se.skillytaire.didactic.tools.jlc.api.ComparableTestObjectFactory;
import se.skillytaire.didactic.tools.jlc.api.ImmutableType;
import se.skillytaire.didactic.tools.jlc.api.JLCTestFactory;
import se.skillytaire.didactic.tools.jlc.api.Pooled;

@ImmutableType
@JLCTestFactory(path = "java/lang")
public class CharacterTestObjectFactory implements ComparableTestObjectFactory<Character> {
	@Pooled
	@Override
	public Character createThis() {
		return 7;
	}
	@Pooled
	@Override
	public Character createThat() {
		return 13;
	}

	@Override
	public boolean isTypeFor(Class<?> type) {
		return Character.class == type || Character.TYPE == type;
	}
	public Class<?>[] types() {
		return new Class[] { type(), Character.TYPE };
	}
	@Override
	public Class<Character> type() {
		return Character.class;
	}
	@Pooled
	@Override
	public Character createLessThen() {
		return Character.MIN_VALUE;
	}
	
	@Override
	public Character createGreaterThen() {
		return Character.MAX_VALUE;
	}

}
