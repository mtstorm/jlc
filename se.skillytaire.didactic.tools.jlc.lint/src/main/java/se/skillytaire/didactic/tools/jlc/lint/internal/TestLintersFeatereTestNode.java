package se.skillytaire.didactic.tools.jlc.lint.internal;

import java.util.logging.Logger;
import java.util.stream.Stream;

import se.skillytaire.didactic.tools.jlc.api.Archetype;
import se.skillytaire.didactic.tools.jlc.api.JLCConfiguration;
import se.skillytaire.didactic.tools.jlc.lint.api.Lint;
import se.skillytaire.didactic.tools.jlc.lint.api.Lints;
import se.skillytaire.didactic.tools.jlc.lint.spi.ArchetypeResolver;
import se.skillytaire.didactic.tools.jlc.lint.spi.model.config.TestLinterConfiguration;
import se.skillytaire.didactic.tools.jlc.lint.spi.model.structure.LintTestNode;
import se.skillytaire.didactic.tools.jlc.spi.ext.feature.JLCFeatereTestNode;
import se.skillytaire.didactic.tools.jlc.spi.model.naming.BasicDisplayName;
import se.skillytaire.didactic.tools.jlc.spi.model.naming.DisplayName;
import se.skillytaire.didactic.tools.jlc.spi.model.structure.AbstractRepeatableAnnotatedTestExtention;

public class TestLintersFeatereTestNode<T>
		extends AbstractRepeatableAnnotatedTestExtention<Lint, Lints, T, TestLintersConfiguration, TestLinterConfiguration<T>>
		implements JLCFeatereTestNode<T> {
	private static final Logger log = Logger.getLogger(TestLintersFeatereTestNode.class.getName());

	@Override
	protected DisplayName getDefaultDisplayName() {
		return new BasicDisplayName(Lints.LINTERS);
	}



//	@Override
//	protected TestLinterConfiguration<T> createConfiguration(JLCConfiguration<T> configuration, Lint annotation) {
//		TestLinterConfiguration<T> c = new TestLinterConfiguration<>(configuration,getFeatureSettings(), new Archetype(annotation.archetype()));
//		c.setEnforced(true);
//		c.setDeclared(true);
//		c.setDisplayNameValue(annotation.displayName().value());
//		c.setEnabled(annotation.enabled());
//		return c;
//	}

	public void doBuild() {
    this.getElementConfigurations()
        .map(LintTestNode<T>::new)
        .peek( n -> n.init(getConfiguration()))  //initializes the node
        .peek( n -> n.build())
        .forEach(this::add);    
	   
//		try {
			//this.getAllTestConfigurations().forEach(System.out::println);
//		this.getElementConfigurations()
//				.map(LintTestNode<T>::new)               //creates the node
//				.peek( e-> System.out.println("Jo detector pruts "+ e.getClass()))
//				.peek( n -> n.init(getConfiguration()))  //initializes the node
//				.forEach(this::add);
//		}catch(Exception e) {
//			throw new AssertionError("Lint configuration failed", e);
//		}
	}

//	protected void defaultDetailsInit(JLCConfiguration<T> configuration) {
//		//Stream<Archetype> detectors = ArchetypeResolver.find(configuration.getTestInstance().getClass(), configuration.getBeanUnderTestType());
////		detectors
////			//.peek( e-> System.out.println("Jo detector "+ e.getName()))
////			.map( a -> new TestLinterConfiguration<T>(configuration, getFeatureSettings(),a) )
////			.peek( c->c.setEnabled(true))
////			.forEach(configuration::enforce);
//	}



//type  = 4000
//field 3000
//constructor 2000
//method = 1000
	@Override
	public int getWeight() {
		return 0; //always at the bottom
	}

   @SuppressWarnings("unchecked")
   @Override
   protected Stream<Archetype> getConfiguratables() {
  
      Stream<Archetype> anno = annotations().map( a-> Archetype.of(a.archetype()));
      Stream<Archetype> resolver = ArchetypeResolver.find(getConfiguration());
      Stream<Archetype> resultingStream = Stream.concat(anno, resolver);
      return resultingStream.distinct().peek(x -> System.out.println(x));
   }










	
	
}
