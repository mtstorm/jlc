package se.skillytaire.didactic.tools.jlc.sample;

import se.skillytaire.didactic.tools.jlc.api.AbstractTestObjectFactory;
import se.skillytaire.didactic.tools.jlc.api.TestObjectFactory;
/**
 * Needs to be in this package for the module-info class
 *
 */
public class NameTestFactory extends AbstractTestObjectFactory<Name>{

	@Override
	public Name createThis() {
		Name name = new Name();
		name.setName("Maarten");
		name.setSurname("Storm");
		return name;
	}

	@Override
	public Name createThat() {
		Name name = new Name();
		name.setName("Kim");
		name.setSurname("Storm-Raeven");
		return name;
	}

}
