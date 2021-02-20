package se.skillytaire.didactic.tools.jlc.sample;

import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DynamicNode;
import org.junit.jupiter.api.TestFactory;

import se.skillytaire.didactic.tools.jlc.api.JLC;
import se.skillytaire.didactic.tools.jlc.api.JLCConfigurationTest;
import se.skillytaire.didactic.tools.jlc.api.JLCTestBuilder;
import se.skillytaire.didactic.tools.jlc.constructor.api.TestConstructor;
import se.skillytaire.didactic.tools.jlc.constructor.api.TestConstructors;
import se.skillytaire.didactic.tools.jlc.lint.api.Lint;
import se.skillytaire.didactic.tools.jlc.lint.api.Lints;
import se.skillytaire.didactic.tools.jlc.method.api.TestMethods;
import se.skillytaire.didactic.tools.jlc.property.api.TestProperties;
import se.skillytaire.didactic.tools.junit.core.javabean.api.JavaBean;
//import se.skillytaire.didactic.tools.jlc.method.api.TestMethods;
//@TestMethods(enabled = false)
//@TestConstructors(enabled = false)
//@TestProperties(enabled = false)
@JLC(showEmptyTests = false, failEmptyFeature = false, testFactory = BeanTestObjectFactory.class, group = true)
//@JLCConfigurationTest
//@TestConstructors(enabled = false)
//@TestProperties(grouping  = true)

//@Lints({
////      @Lint(archetype = "JavaBean")
//})
//@JavaBean
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
