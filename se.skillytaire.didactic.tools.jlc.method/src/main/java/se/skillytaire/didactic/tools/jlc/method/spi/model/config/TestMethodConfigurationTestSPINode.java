package se.skillytaire.didactic.tools.jlc.method.spi.model.config;

import java.util.stream.Stream;

import se.skillytaire.didactic.tools.jlc.api.JLCConfiguration;
import se.skillytaire.didactic.tools.jlc.method.internal.spi.TestMethodsConfiguration;
import se.skillytaire.didactic.tools.jlc.method.spi.MethodTestFactory;
import se.skillytaire.didactic.tools.jlc.signature.spi.model.naming.SignatureDisplayName;
import se.skillytaire.didactic.tools.jlc.spi.model.naming.BasicDisplayName;
import se.skillytaire.didactic.tools.jlc.spi.model.naming.DisplayName;
import se.skillytaire.didactic.tools.jlc.spi.model.structure.AbstractConfigurationTestNode;
import se.skillytaire.didactic.tools.jlc.spi.model.structure.JLCTestNode;

/**
 * The parent node for method signature based tests 
 * @author prolector
 *
 * @param <T>
 */
public class TestMethodConfigurationTestSPINode <T> extends AbstractConfigurationTestNode<T,TestMethodsConfiguration, TestMethodConfiguration<T>>{


	public TestMethodConfigurationTestSPINode(TestMethodConfiguration<T> config) {
		super(config);
	}
//	@Override
//	public void init(JLCConfiguration<T> configuration) {
//		super.init(configuration);
//		Stream<JLCTestNode<T>> nodes =MethodTestFactory.find(getTestConfiguration());
//		nodes.peek( n->n.init(configuration))
//			 .forEach(this::add);
//	}
	
	@Override
	public DisplayName getDisplayName() {
		return new BasicDisplayName(getTestConfiguration().getName());
	}
	
	@Override
	public void build() {
		Stream<JLCTestNode<T>> nodes =MethodTestFactory.find(getTestConfiguration());
		nodes.peek( n->n.init(getConfiguration()))
			 .forEach(this::add);
		
	}

	
}
