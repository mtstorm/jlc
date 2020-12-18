package se.skillytaire.didactic.tools.jlc.core.sample;

import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;

import se.skillytaire.didactic.tools.jlc.api.JLC;
import se.skillytaire.didactic.tools.jlc.api.This;
@JLC(registries  = {
			SampleTestObjectFactoryRegistry.class
	}
)
public class BeanRegistryTest {
	@This
	private Bean thisBean;
	
	@Test
	public void testEqualsNull() {
		Bean thisBean = new Bean();
		boolean actual = thisBean.equals(null);
		assertFalse(actual);
	}
}
