package se.skillytaire.didactic.tools.jlc.sample;

import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;

import se.skillytaire.didactic.tools.jlc.api.AbstractTestObjectFactory;
import se.skillytaire.didactic.tools.jlc.api.JLC;
import se.skillytaire.didactic.tools.jlc.api.This;
import se.skillytaire.didactic.tools.jlc.sample.Bean;

@JLC
public class BeanImplTest extends AbstractTestObjectFactory<Bean>{
	@This
	private Bean thisBean;
	
	@Test
	public void testNull() {
		boolean actual = thisBean.equals(null);
		assertFalse(actual);
	}
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
