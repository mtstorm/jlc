package se.skillytaire.didactic.tools.jlc.property.internal;

import se.skillytaire.didactic.tools.jlc.spi.ext.feature.TestConfigurationNodeFactory;
import se.skillytaire.didactic.tools.jlc.spi.model.structure.AbstractConfigurationTestNode;

public class TestPropertyConfigurationTestNode<T> extends AbstractConfigurationTestNode<T,TestPropertiesConfiguration,TestPropertyConfiguration<T>>{

	public TestPropertyConfigurationTestNode(TestPropertyConfiguration<T> config) {
		super(config);
	}

	@Override
	public void build() {
		this.getTestConfiguration()
		.getProperty()
	    .methods()
	    //hmmmm 
	    .map(m -> TestConfigurationNodeFactory.create(getConfiguration(), m))
	   // .peek(n -> System.out.println("->> "+n.getTestConfiguration())) 
	    .peek( n-> n.init(getConfiguration()))
	    .peek( n->n.build())
	    .forEach(this::add);
		
		
		
	}

}
