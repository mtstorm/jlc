package se.skillytaire.didactic.tools.jlc.method.spi.model.config;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import se.skillytaire.didactic.tools.jlc.api.JLCConfiguration;
import se.skillytaire.didactic.tools.jlc.method.api.TestMethod;
import se.skillytaire.didactic.tools.jlc.method.api.TestMethods;
import se.skillytaire.didactic.tools.jlc.method.spi.util.MethodTool;
import se.skillytaire.didactic.tools.jlc.signature.spi.MethodSignature;
import se.skillytaire.didactic.tools.jlc.signature.spi.model.config.AbstractTestSignaturesConfiguration;
import se.skillytaire.didactic.tools.jlc.spi.ext.feature.JLCFeatereTestNode;
import se.skillytaire.didactic.tools.jlc.spi.model.config.BasicTestGroupConfiguration;
import se.skillytaire.didactic.tools.jlc.spi.model.naming.BasicDisplayName;
import se.skillytaire.didactic.tools.jlc.spi.model.naming.DisplayName;

public class TestMethodsConfiguration<T>
		extends AbstractTestSignaturesConfiguration<TestMethod, TestMethods, T, TestMethodConfiguration<T>, MethodSignature, Method>
		implements JLCFeatereTestNode<T> {
	private static final Logger log = Logger.getLogger(TestMethodsConfiguration.class.getName());
	
	private String excludePatterns;
	
	private List<MethodSignature> apiSignatures = MethodSignature.defaultApi().collect(Collectors.toList());
	

	@Override
	protected DisplayName getDefaultDisplayName() {
		return new BasicDisplayName(TestMethods.METHODS);
	}

	@Override
	protected void init() {
		super.init();
	}

	@Override
	protected void defaultMasterInit(JLCConfiguration<T> configuration) {
		super.defaultMasterInit(configuration);
		setMaxParameterCount(TestMethods.DEFAULT_PARAM_COUNT);
		setSimpleName(TestMethods.SIMPLE_NAME);
		excludePatterns = TestMethods.DEFAULT_EXCLUDE_PATTERN;
		setMerge(TestMethods.MERGE);
		
	}

	@Override
	protected void init(TestMethods repeater, JLCConfiguration<T> configuration) {
		setMaxParameterCount(repeater.parameterCount());
		setSimpleName(repeater.simpleName());
		setGrouping(repeater.grouping());
		excludePatterns = repeater.excludePattern();
		setMerge(repeater.merge());
	}

	@Override
	protected TestMethodConfiguration<T> createConfiguration(JLCConfiguration<T> configuration, TestMethod annotation) {
		Class<?> beanUnderTestType = configuration.getBeanUnderTestType();
		TestMethodConfiguration<T> c = new TestMethodConfiguration<>(configuration, annotation,getMaxParameterCount());
		c.apply(beanUnderTestType);
		return c;
	}

	public void doBuild(BasicTestGroupConfiguration grouping) {
	
		//Add Signature node
		
		
		this.getElementConfigurations().filter(c -> apiSignatures.contains(c.getSignature()))
				.forEach(c -> c.getSignature().setApi(true));

		//grouping here!!
		
		TestMethodConfigurationNodeBuilder<T> b = new TestMethodConfigurationNodeBuilder<>(grouping);
		
		//this.getElementConfigurations().forEach(b::add);
		this.getElementConfigurations().forEach(b::add);
		b.build(getConfiguration(),TestMethodConfigurationTestSPINode<T>::new)
		//.peek(n->n.init(getConfiguration()))
		.forEach(this::add);

	}
	@Override
	protected void defaultDetailsInit(JLCConfiguration<T> configuration) {
		if(this.excludePatterns == null) {
			throw new IllegalStateException("exclude pattern is void");
		}
		Class<?> beanUnderTestType = configuration.getBeanUnderTestType();

		// use default reflection for testable methods
		Stream<TestMethodConfiguration<T>> stream = MethodTool.getConcreteMethods(configuration, beanUnderTestType);

		stream.filter((conf) -> MethodSignature.NON_TESTABLES.test(conf.getSignature()))

				.filter((conf) -> {
					int modifiers = conf.getExecutor().getModifiers();
					return !(!Modifier.isPublic(modifiers) && Modifier.isNative(modifiers));
				})

				.filter((conf) -> !conf.getSignature().getName().matches(this.excludePatterns))

				.peek((m) -> log.info(() -> m.getSignature().toString(true)))
				.peek(c -> c.setEnabled(true) )
				.peek( (c) -> c.setMaximalParameterCount(getMaxParameterCount()) )
				.peek(c -> c.setSimpleName( this.isSimpleName()))
				.peek( c -> c.setDbcEnabled(true))
				.forEach(this::add);

	}


//type  = 4000
//field 3000
//constructor 2000
//method = 1000
	@Override
	public int getWeight() {
		return 1000;
	}
	
	
}
