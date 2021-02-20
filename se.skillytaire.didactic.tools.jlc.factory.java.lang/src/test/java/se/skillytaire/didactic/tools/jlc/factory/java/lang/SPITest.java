package se.skillytaire.didactic.tools.jlc.factory.java.lang;

import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DynamicNode;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;

import se.skillytaire.didactic.tools.jlc.api.JLC;
import se.skillytaire.didactic.tools.jlc.api.JLCConfigurationTest;
import se.skillytaire.didactic.tools.jlc.api.JLCTestBuilder;
import se.skillytaire.didactic.tools.jlc.api.PackagePresentation;

@JLC
@JLCConfigurationTest(layout = PackagePresentation.HIERARCHICAL)
public class SPITest {

	@TestFactory
	@DisplayName("JLC")
	public Stream<DynamicNode> jlc() {
		return JLCTestBuilder.build(this);
	}
}
