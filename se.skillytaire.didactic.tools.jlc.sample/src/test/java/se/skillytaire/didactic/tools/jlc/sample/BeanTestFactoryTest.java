package se.skillytaire.didactic.tools.jlc.sample;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

import se.skillytaire.didactic.tools.jlc.api.JLC;
import se.skillytaire.didactic.tools.jlc.api.This;
import se.skillytaire.didactic.tools.jlc.sample.Bean;
@JLC(testFactory = BeanTestObjectFactory.class)
public class BeanTestFactoryTest {
	@This
	private Bean thisBean;
	
	@Test
	public void testEqualsNull() {
		Bean thisBean = new Bean();
		boolean actual = thisBean.equals(null);
		assertFalse(actual);
	}
}
