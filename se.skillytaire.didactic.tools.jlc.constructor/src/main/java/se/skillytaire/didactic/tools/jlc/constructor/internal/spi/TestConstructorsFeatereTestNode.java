package se.skillytaire.didactic.tools.jlc.constructor.internal.spi;

import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.util.function.Function;
import java.util.logging.Logger;
import java.util.stream.Stream;

import se.skillytaire.didactic.tools.jlc.api.JLCConfiguration;
import se.skillytaire.didactic.tools.jlc.constructor.api.TestConstructor;
import se.skillytaire.didactic.tools.jlc.constructor.api.TestConstructors;
import se.skillytaire.didactic.tools.jlc.constructor.spi.model.config.TestConstructorConfiguration;
import se.skillytaire.didactic.tools.jlc.constructor.spi.model.structure.TestConstructorConfigurationTestSPINode;
import se.skillytaire.didactic.tools.jlc.signature.spi.ConstructorSignature;
import se.skillytaire.didactic.tools.jlc.signature.spi.model.config.AbstractTestSignaturesConfiguration;
import se.skillytaire.didactic.tools.jlc.spi.ext.feature.BuildableTestNode;
import se.skillytaire.didactic.tools.jlc.spi.ext.feature.Enforcer;
import se.skillytaire.didactic.tools.jlc.spi.model.config.BasicTestGroupConfiguration;
import se.skillytaire.didactic.tools.jlc.spi.model.config.TestConfigurationNodeBuilder;
import se.skillytaire.didactic.tools.jlc.spi.model.naming.BasicDisplayName;
import se.skillytaire.didactic.tools.jlc.spi.model.naming.DisplayName;
import se.skillytaire.didactic.tools.jlc.spi.model.structure.JLCTestNode;

public class TestConstructorsFeatereTestNode<T>
		extends AbstractTestSignaturesConfiguration<TestConstructor, TestConstructors, T,TestConstructorsConfiguration, TestConstructorConfiguration<T>, ConstructorSignature<T>, Constructor<T>>
		{
	private static final Logger log = Logger.getLogger(TestConstructorsFeatereTestNode.class.getName());


	@Override
	protected DisplayName getDefaultDisplayName() {
		return new BasicDisplayName(TestConstructors.CONSTRUCTORS);
	}

//	@Override
//	protected void defaultMasterInit(JLCConfiguration<T> configuration) {
//		super.defaultMasterInit(configuration);
//		setMaxParameterCount(TestConstructors.DEFAULT_PARAM_COUNT);
//		setSimpleName(TestConstructors.SIMPLE_NAME);
//		setMerge(JLC.MERGE);
//	}
//
//	@Override
//	protected void init(TestConstructors repeater, JLCConfiguration<T> configuration) {
//		setMaxParameterCount(repeater.parameterCount());
//		setSimpleName(repeater.simpleName());
//		setGrouping(repeater.grouping());
//		setMerge(repeater.merge());
//	}
	
	@Override
	protected  Stream<Constructor<?>> getConfiguratables() {
		Class<T> beanUnderTestType = getConfiguration().getBeanUnderTestType();
		return Arrays
				.stream(beanUnderTestType.getDeclaredConstructors())
				.peek(System.out::println);

	}
//	
//	@Override
//	protected TestConstructorConfiguration<T> createConfiguration(JLCConfiguration<T> configuration,
//			TestConstructor annotation) {
//		TestConstructorConfiguration<T> c = super.createConfiguration(configuration, annotation);
//		Class<T> beanUnderTestType = configuration.getBeanUnderTestType();
////		TestConstructorConfiguration<T> c = new TestConstructorConfiguration<>(configuration, annotation,getMaxParameterCount());
//		c.setSimpleName(this.getFeatureSettings().isSimpleName());
//		c.apply(beanUnderTestType);
//		return c;
//		
//	}
//	@Override
//	protected void defaultDetailsInit(JLCConfiguration<T> configuration) {
//		Class<T> beanUnderTestType = configuration.getBeanUnderTestType();
//			Arrays.stream(beanUnderTestType.getDeclaredConstructors())
//			//.map(c -> (Constructor<T>)c)
//			.map(this::createEnforcer )
//			.forEach(Enforcer::enforce);
//			
////			//FIXME niet via Factory????
////			.map(c -> new TestConstructorConfiguration<T>(configuration, getFeatureSettings(), c))
////			//FIXME peeks kunnen er nu uit :-)
////			.peek(c -> c.setEnabled(true) )
////			.peek( c -> c.setMaximalParameterCount(this.getFeatureSettings().getMaxParameterCount()) )
////			.peek( c -> c.setSimpleName(this.getFeatureSettings().isSimpleName()) )
////			.peek( c -> c.setDbcEnabled(true))
////			//.peek(System.out::println)
////			.forEach( config -> Enforcer.of(configuration, defaults, configurable));
//////			.forEach(configuration::enforce);
//	}	
	

	@Override
	public void doBuild(BasicTestGroupConfiguration groupConfig) {
		Function<TestConstructorConfiguration<T>, JLCTestNode<T>> mapper = TestConstructorConfigurationTestSPINode<T>::new;
		if(groupConfig.isEnabled()) {
			TestConfigurationNodeBuilder<TestConstructorsConfiguration, TestConstructorConfiguration<T>, T, BasicTestGroupConfiguration> builder = new TestConfigurationNodeBuilder<>(groupConfig);
			getElementConfigurations().forEachOrdered(builder::add);
			
			builder.build(getConfiguration(), mapper).forEach(this::add);
			
		}else {
			getElementConfigurations().map(mapper)
			.peek(x->x.init(getConfiguration()))
			.peek(n ->{ 
				//FIXME BAD Design!!!
				if(n instanceof BuildableTestNode) {
					((BuildableTestNode)n).build();
				}
				})
			.peek(System.out::println)
		    .forEach(this::add);
		}
		
		

	}


//type  = 4000
//field 3000
//constructor 2000
//method = 1000
	@Override
	public int getWeight() {
		return 2000;
	}


}
