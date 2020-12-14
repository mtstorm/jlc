package se.skillytaire.didactic.tools.junit.core.lint.internal.config;

import java.util.stream.Stream;

import se.skillytaire.didactic.tools.junit.core.internal.config.AbstractConfigurationTestNode;
import se.skillytaire.didactic.tools.junit.core.internal.config.JLCConfiguration;
import se.skillytaire.didactic.tools.junit.core.lint.internal.spi.LintersTestNodeFactory;
import se.skillytaire.didactic.tools.junit.core.spi.JLCTestNode;

/**
 * The parent node for method signature based tests 
 * @author prolector
 *
 * @param <T>
 */
public class TestLintConfigurationTestSPINode <T> extends AbstractConfigurationTestNode<T, TestLinterConfiguration<T>>{


	public TestLintConfigurationTestSPINode(TestLinterConfiguration<T> config) {
		super(config);
	}
	@Override
	public void init(JLCConfiguration<T> configuration) {
		super.init(configuration);
		Stream<JLCTestNode<T>> nodes =LintersTestNodeFactory.find(getTestConfiguration());
		nodes.peek( n->n.init(configuration))
			 .forEach(this::add);
	}

	


}
