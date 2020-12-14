package se.skillytaire.didactic.tools.jlc.lint.spi.model.structure;

import java.util.Optional;
import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;

import se.skillytaire.didactic.tools.jlc.lint.spi.Linter;
import se.skillytaire.didactic.tools.jlc.lint.spi.LinterFactory;
import se.skillytaire.didactic.tools.jlc.lint.spi.model.config.TestLinterConfiguration;
import se.skillytaire.didactic.tools.jlc.spi.ext.feature.FeatureTestNodeFactory;
import se.skillytaire.didactic.tools.jlc.spi.model.Archetype;
import se.skillytaire.didactic.tools.jlc.spi.model.config.JLCConfiguration;
import se.skillytaire.didactic.tools.jlc.spi.model.structure.AbstractConfigurationTestNode;
/**
 * Already filtered node
 * @author prolector
 *
 * @param <T>
 */
public final class LintTestNode<T> extends AbstractConfigurationTestNode<T, TestLinterConfiguration<T>> {

	public LintTestNode(TestLinterConfiguration<T> config) {
		super(config);
		//this is a filtered root node
		this.applyFilter(config.getArchetype());
	}

	@Override
	public void init(JLCConfiguration<T> configuration) {
		super.init(configuration); 
		Optional<Linter<T>> optionalLinter = LinterFactory.find(getTestConfiguration());
		if (optionalLinter.isPresent()) {
			Linter<T> linter = optionalLinter.get();
			
			linter.enforce(getTestConfiguration());
			Stream<FeatureTestNodeFactory<T>> structure = FeatureTestNodeFactory.factories(f -> f.isRerunnable());
			// structure.forEach(System.out::println); 
//			 System.out.println(structure.count());
			structure
			       // .peek(b -> System.out.println( "gevonden textory "+  b.getClass().getName()))
					.map(f -> f.create())
					.peek(n->n.applyFilter(linter.getArchetype()))
					//.filter(n-> n.hasExecutableTest())
					.peek(n->n.init(configuration))
					.peek(n->n.build())
				
					.sorted(FeatureTestNodeFactory.getComperator()).forEach(this::add);
					//peek(n -> n.applyFilter(getArchetype()))
					//.peek(b -> System.out.println( "gevonden gg "+  b.getClass().getName())).forEach(this::add);
		} else {
			String msg = String.format("There is no linter found for achetype '%s', make sure it is in your classpath",
					getArchetype().getName());

			Assertions.fail(msg);
		}
	}

	private Archetype getArchetype() {
		return getTestConfiguration().getArchetype();
	}
//	protected void enforce() {
//		ConstructorSignature<T> noArgumentConstructor = create();
//		Optional<TestConfiguration<T>> ding = enforce(noArgumentConstructor);		
//	}
//	protected ConstructorSignature<T> create(Class<?>... paramTypes){
//		return new ConstructorSignature<T>(getConfiguration().getBeanUnderTestType().getSimpleName(), paramTypes);
//	}
//	
//	protected Optional<TestConfiguration<T>> enforce(ConstructorSignature<T> constructor){
//		return getConfiguration().enfoce(constructor, getArchetype());
//	}
}
