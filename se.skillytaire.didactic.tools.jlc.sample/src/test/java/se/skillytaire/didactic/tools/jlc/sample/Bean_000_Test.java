package se.skillytaire.didactic.tools.jlc.sample;

import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;

import se.skillytaire.didactic.tools.jlc.sample.Bean;


public class Bean_000_Test {

	@Test
	public void testEqualsNull() {
		Bean thisBean = new Bean();
		boolean actual = thisBean.equals(null);
		assertFalse(actual);
	}

}
