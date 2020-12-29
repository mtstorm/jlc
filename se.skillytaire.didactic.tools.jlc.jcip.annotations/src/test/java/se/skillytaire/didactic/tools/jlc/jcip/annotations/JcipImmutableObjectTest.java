package se.skillytaire.didactic.tools.jlc.jcip.annotations;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import net.jcip.annotations.Immutable;
import se.skillytaire.didactic.tools.jlc.api.AbstractTestObjectFactory;
import se.skillytaire.didactic.tools.jlc.api.JLC;
import se.skillytaire.didactic.tools.jlc.api.JLCConfiguration;
import se.skillytaire.didactic.tools.jlc.spi.ext.immutable.ImmutableObject;
@Immutable
@JLC
public class JcipImmutableObjectTest extends AbstractTestObjectFactory<String>{

	private JLCConfiguration<?> configuration;
	
	@Test
	public void testImmutable() {
		boolean is = ImmutableObject.isImmutableType(configuration, String.class);
		assertTrue(is, "JCIP annotation Immutable is on the factory");
	}
	
	@Override
	public String createThis() {
		return "A";
	}

	@Override
	public String createThat() {
		return "B";
	}



}
