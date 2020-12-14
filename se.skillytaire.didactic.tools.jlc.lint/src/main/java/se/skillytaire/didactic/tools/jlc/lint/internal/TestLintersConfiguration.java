package se.skillytaire.didactic.tools.jlc.lint.internal;

import java.util.logging.Logger;
import java.util.stream.Stream;

import se.skillytaire.didactic.tools.jlc.lint.api.Lint;
import se.skillytaire.didactic.tools.jlc.lint.api.Lints;
import se.skillytaire.didactic.tools.jlc.lint.spi.ArchetypeResolver;
import se.skillytaire.didactic.tools.jlc.lint.spi.model.config.TestLinterConfiguration;
import se.skillytaire.didactic.tools.jlc.lint.spi.model.structure.LintTestNode;
import se.skillytaire.didactic.tools.jlc.spi.ext.feature.JLCFeatereTestNode;
import se.skillytaire.didactic.tools.jlc.spi.model.Archetype;
import se.skillytaire.didactic.tools.jlc.spi.model.config.JLCConfiguration;
import se.skillytaire.didactic.tools.jlc.spi.model.naming.BasicDisplayName;
import se.skillytaire.didactic.tools.jlc.spi.model.naming.DisplayName;
import se.skillytaire.didactic.tools.jlc.spi.model.structure.AbstractAnnotatedTestExtention;

public class TestLintersConfiguration<T>
		extends AbstractAnnotatedTestExtention<Lint, Lints, T, TestLinterConfiguration<T>>
		implements JLCFeatereTestNode<T> {
	private static final Logger log = Logger.getLogger(TestLintersConfiguration.class.getName());

	@Override
	protected DisplayName getDefaultDisplayName() {
		return new BasicDisplayName(Lints.LINTERS);
	}

//	public void init(JLCConfiguration<T> configuration) {
//		super.init(configuration);
///*	
//		Stream<FeatureTestNodeFactory<T>> factoryStream = FeatureTestNodeFactory.factories( f -> f.getClass() == LintersTestNodeFactory.class );
//		List<FeatureTestNodeFactory<T>> indexedFactories = factoryStream.collect(Collectors.toList());
//		
//		*/
//		// initialize all the underlaying children
//		this.children().forEach(n -> n.init(configuration));
//
//	}

	protected void defaultMasterInit(JLCConfiguration<T> configuration) {
	}

	@Override
	protected void init(Lints repeater, JLCConfiguration<T> configuration) {
	
	}

	@Override
	protected TestLinterConfiguration<T> createConfiguration(JLCConfiguration<T> configuration, Lint annotation) {
		TestLinterConfiguration<T> c = new TestLinterConfiguration<>(configuration, new Archetype(annotation.archetype()));
		c.setEnforced(true);
		c.setDeclared(true);
		c.setDisplayNameValue(annotation.displayName().value());
		c.setEnabled(annotation.enabled());
		return c;
	}

	public void doBuild() {
		this.getAllElementConfigurations()
				.map(LintTestNode<T>::new)               //creates the node
				.peek( n -> n.init(getConfiguration()))  //initializes the node
				.peek( e-> System.out.println("Jo detector pruts "+ e.getClass()))
				.forEach(this::add);
	}

	protected void defaultDetailsInit(JLCConfiguration<T> configuration) {
		Stream<Archetype> detectors = ArchetypeResolver.find(configuration.getTestInstance().getClass(), configuration.getBeanUnderTestType());
		detectors
			//.peek( e-> System.out.println("Jo detector "+ e.getName()))
			.map( a -> new TestLinterConfiguration<T>(configuration,a) )
			.peek( c->c.setEnabled(true))
		    .forEach(this::add);
	}



//type  = 4000
//field 3000
//constructor 2000
//method = 1000
	@Override
	public int getWeight() {
		return 0; //always at the bottom
	}




	
	
}
