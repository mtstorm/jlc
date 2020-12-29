package se.skillytaire.didactic.tools.jlc.sample;

import se.skillytaire.didactic.tools.jlc.api.AbstractTestObjectFactory;
import se.skillytaire.didactic.tools.jlc.sample.Bean;

public class BeanTestObjectFactory extends AbstractTestObjectFactory<Bean>{
	@Override
	public Bean createThis() {
		Bean name = new Bean();
		name.setName("Maarten");
		name.setSurname("Storm");
		return name;
	}

	@Override
	public Bean createThat() {
		Bean name = new Bean();
		name.setName("Kim");
		name.setSurname("Storm-Raeven");
		return name;
	}
}
