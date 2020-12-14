package se.skillytaire.didactic.tools.jlc.constructor.spi.model.config;

import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.util.function.Function;
import java.util.logging.Logger;

import se.skillytaire.didactic.tools.jlc.api.TestGroup;
import se.skillytaire.didactic.tools.jlc.constructor.api.TestConstructor;
import se.skillytaire.didactic.tools.jlc.constructor.api.TestConstructors;
import se.skillytaire.didactic.tools.jlc.constructor.spi.model.structure.TestConstructorConfigurationTestSPINode;
import se.skillytaire.didactic.tools.jlc.signature.spi.ConstructorSignature;
import se.skillytaire.didactic.tools.jlc.signature.spi.model.config.AbstractTestSignaturesConfiguration;
import se.skillytaire.didactic.tools.jlc.spi.model.config.BasicTestGroupConfiguration;
import se.skillytaire.didactic.tools.jlc.spi.model.config.JLCConfiguration;
import se.skillytaire.didactic.tools.jlc.spi.model.config.TestConfigurationNodeBuilder;
import se.skillytaire.didactic.tools.jlc.spi.model.naming.BasicDisplayName;
import se.skillytaire.didactic.tools.jlc.spi.model.naming.DisplayName;
import se.skillytaire.didactic.tools.jlc.spi.model.structure.JLCTestNode;

public class TestConstructorsConfiguration<T>
		extends AbstractTestSignaturesConfiguration<TestConstructor, TestConstructors, T, TestConstructorConfiguration<T>, ConstructorSignature<T>, Constructor<T>>
		{


	private static final Logger log = Logger.getLogger(TestConstructorsConfiguration.class.getName());
	private int maxParameterCount;
	private TestGroup[] grouping;
	private boolean displaySimpleName;
	
	public void init(JLCConfiguration<T> configuration) {
		super.init(configuration);
	}

	@Override
	protected DisplayName getDefaultDisplayName() {
		return new BasicDisplayName(TestConstructors.CONSTRUCTORS);
	}

//	public void init(JLCConfiguration<T> configuration) {
//		super.init(configuration);
//
//		// initialize all the underlaying children
//		this.children().forEach(n -> n.init(configuration));
//
//	}

	protected void defaultMasterInit(JLCConfiguration<T> configuration) {
		maxParameterCount = TestConstructors.DEFAULT_PARAM_COUNT;
		displaySimpleName = TestConstructors.SIMPLE_NAME;
		grouping = new TestGroup[0];
	}

	@Override
	protected void init(TestConstructors repeater, JLCConfiguration<T> configuration) {
		maxParameterCount = repeater.parameterCount();
		displaySimpleName = repeater.simpleName();
		grouping = repeater.grouping();
	}
	@Override
	protected TestConstructorConfiguration<T> createConfiguration(JLCConfiguration<T> configuration,
			TestConstructor annotation) {
		Class<T> beanUnderTestType = configuration.getBeanUnderTestType();

			TestConstructorConfiguration<T> c = new TestConstructorConfiguration<>(configuration, annotation,maxParameterCount);
			c.setSimpleName(displaySimpleName);
			c.apply(beanUnderTestType);


			return c;
		
	}

	protected void defaultDetailsInit(JLCConfiguration<T> configuration) {
		Class<T> beanUnderTestType = configuration.getBeanUnderTestType();
			Arrays.stream(beanUnderTestType.getDeclaredConstructors())
			.map(c -> (Constructor<T>)c)
			.map(c -> new TestConstructorConfiguration<T>(configuration, c))
			.peek(c -> c.setEnabled(true) )
			.peek( c -> c.setMaximalParameterCount(getMaxParameterCount()) )
			.peek( c -> c.setSimpleName(displaySimpleName) )
			//.peek(System.out::println)
			.forEach(this::add);
	}	
	@Override
	public void doBuild() {
		BasicTestGroupConfiguration groupConfig = new BasicTestGroupConfiguration(grouping);
		Function<TestConstructorConfiguration<T>, JLCTestNode<T>> mapper = TestConstructorConfigurationTestSPINode<T>::new;
		if(groupConfig.isEnabled()) {
			TestConfigurationNodeBuilder<TestConstructorConfiguration<T>, T, BasicTestGroupConfiguration> builder = new TestConfigurationNodeBuilder<TestConstructorConfiguration<T>, T, BasicTestGroupConfiguration>(groupConfig);
			getElementConfigurations().forEachOrdered(builder::add);
			
			builder.build(getConfiguration(), mapper).forEach(this::add);
			
		}else {
			getElementConfigurations().map(mapper)
			.peek(x->x.init(getConfiguration()))
		    .forEach(this::add);
		}
		
		

	}



	public int getMaxParameterCount() {
		return maxParameterCount;
	}

//type  = 4000
//field 3000
//constructor 2000
//method = 1000
	@Override
	public int getWeight() {
		return 2000;
	}



	




	/**
	 * Per method wil ik weer een node hebben.
	 */

	
	
	
	
	
	
}