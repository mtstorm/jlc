package se.skillytaire.didactic.tools.jlc.core.sample;

import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;

import se.skillytaire.didactic.tools.jlc.api.JLC;

@JLC
public class Bean_001_Test {

	@Test
	public void testEqualsNull() {
		Bean thisBean = new Bean();
		boolean actual = thisBean.equals(null);
		assertFalse(actual);
	}

}
