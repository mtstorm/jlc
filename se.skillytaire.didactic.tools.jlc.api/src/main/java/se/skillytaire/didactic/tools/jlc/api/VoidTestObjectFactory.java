package se.skillytaire.didactic.tools.jlc.api;

/**
 * The void test object factory is used for the annotations having test object
 * factories. This is the only test object factory that may return null, since
 * it is void. All the other implementations may never return null. This is used
 * for the annotations to have a default value, meaning search the corresponding
 * test object factory.
 *
 */
@ImmutableType
public class VoidTestObjectFactory implements TestObjectFactory<Void> {

	@Override
	public Void createThis() {
		return null;
	}

	@Override
	public Void createThat() {
		return null;
	}

	@Override
	public Class<?>[] types() {
		return new Class[] { type(), Void.TYPE };
	}

	@Override
	public Class<Void> type() {
		return Void.class;
	}

}
