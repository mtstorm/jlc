package se.skillytaire.didactic.tools.jlc.constructor.spi.model.structure;

import java.util.stream.Stream;

import se.skillytaire.didactic.tools.jlc.constructor.spi.ConstructorTestFactory;
import se.skillytaire.didactic.tools.jlc.constructor.spi.model.config.TestConstructorConfiguration;
import se.skillytaire.didactic.tools.jlc.signature.spi.model.naming.SignatureDisplayName;
import se.skillytaire.didactic.tools.jlc.spi.model.config.JLCConfiguration;
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
public class TestConstructorConfigurationTestSPINode <T> extends AbstractConfigurationTestNode<T,TestConstructorConfiguration<T>>{


	public TestConstructorConfigurationTestSPINode(TestConstructorConfiguration<T> config) {
		super(config);
	}
	@Override
	public void init(JLCConfiguration<T> configuration) {
		super.init(configuration);
		Stream<JLCTestNode<T>> nodes =ConstructorTestFactory.find(getTestConfiguration());
		nodes.peek( n->n.init(configuration))
			 .forEach(this::add);
	}
	
	@Override
	public DisplayName getDisplayName() {
		DisplayName displayName;
		if(getTestConfiguration().hasDisplayNameValue()) {
			String newDisplay = getTestConfiguration().getDisplayNameValue();
			displayName = new BasicDisplayName(newDisplay);
		}else {
			SignatureDisplayName dn = new SignatureDisplayName(getTestConfiguration().getSignature());
			dn.setShortSignature(true);
			displayName = dn;
		}
		 return displayName;
	}

}
