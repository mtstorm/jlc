package se.skillytaire.didactic.tools.jlc.api;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
 
public class JLCConfigurationTestCase {
	@Test
	public void noSpiTest() {
		Assertions.assertThrows(JLCConfigurationException.class, () -> {
			JLCConfiguration.of(this);
		});

	}
}
