package se.skillytaire.didactic.tools.jlc.core.sample;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

import se.skillytaire.didactic.tools.jlc.api.JLC;
import se.skillytaire.didactic.tools.jlc.api.This;

@JLC
public class Bean_002_Test {
	@This
	private Bean thisBean;
	
	@Test
	public void testNull() {
		assertNull(thisBean);
	}

}
