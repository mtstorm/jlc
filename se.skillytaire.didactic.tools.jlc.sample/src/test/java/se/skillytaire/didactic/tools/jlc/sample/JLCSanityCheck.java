package se.skillytaire.didactic.tools.jlc.sample;

import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DynamicNode;
import org.junit.jupiter.api.TestFactory;

import se.skillytaire.didactic.tools.jlc.api.JLC;
import se.skillytaire.didactic.tools.jlc.api.JLCConfigurationTest;
import se.skillytaire.didactic.tools.jlc.api.JLCTestBuilder;
import se.skillytaire.didactic.tools.jlc.api.TestGroup;
import se.skillytaire.didactic.tools.jlc.api.TestOrder;
import se.skillytaire.didactic.tools.jlc.constructor.api.TestConstructors;
import se.skillytaire.didactic.tools.jlc.method.api.TestMethods;
import se.skillytaire.didactic.tools.jlc.property.api.TestProperties;
@JLC(showEmptyTests = true, testFactory = BeanTestObjectFactory.class)
@JLCConfigurationTest
@TestConstructors(enabled = false)
@TestMethods(enabled = false)
@TestProperties(grouping  = true)
public class JLCSanityCheck {
//	  @BeforeEach
//	  public void changeLogLevel() {
//	    Configurator.setAllLevels("", Level.ALL);
//	  }
	@TestFactory
	@DisplayName("JLC")
	public Stream<DynamicNode> jlc() {
		return JLCTestBuilder.build(this);
	}
}
