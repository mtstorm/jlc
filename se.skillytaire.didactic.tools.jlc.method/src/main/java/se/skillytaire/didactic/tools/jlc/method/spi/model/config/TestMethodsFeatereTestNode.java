package se.skillytaire.didactic.tools.jlc.method.spi.model.config;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import se.skillytaire.didactic.tools.jlc.api.JLC;
import se.skillytaire.didactic.tools.jlc.api.JLCConfiguration;
import se.skillytaire.didactic.tools.jlc.method.api.TestMethod;
import se.skillytaire.didactic.tools.jlc.method.api.TestMethods;
import se.skillytaire.didactic.tools.jlc.method.internal.spi.TestMethodsConfiguration;
import se.skillytaire.didactic.tools.jlc.method.spi.util.MethodTool;
import se.skillytaire.didactic.tools.jlc.signature.spi.MethodSignature;
import se.skillytaire.didactic.tools.jlc.signature.spi.model.config.AbstractTestSignaturesConfiguration;
import se.skillytaire.didactic.tools.jlc.spi.array.UniqueArrayBuilder;
import se.skillytaire.didactic.tools.jlc.spi.ext.enforcer.TestConfigurationFactory;
import se.skillytaire.didactic.tools.jlc.spi.ext.feature.BuildableTestNode;
import se.skillytaire.didactic.tools.jlc.spi.ext.feature.JLCFeatereTestNode;
import se.skillytaire.didactic.tools.jlc.spi.model.config.BasicTestGroupConfiguration;
import se.skillytaire.didactic.tools.jlc.spi.model.naming.BasicDisplayName;
import se.skillytaire.didactic.tools.jlc.spi.model.naming.DisplayName;

public class TestMethodsFeatereTestNode<T>
		extends AbstractTestSignaturesConfiguration<TestMethod, TestMethods, T,TestMethodsConfiguration, TestMethodConfiguration<T>, MethodSignature, Method>
		{
	private static final Logger log = Logger.getLogger(TestMethodsFeatereTestNode.class.getName());
	
	
	
	private List<MethodSignature> apiSignatures = MethodSignature.defaultApi().collect(Collectors.toList());
	

	@Override
	protected DisplayName getDefaultDisplayName() {
		return new BasicDisplayName(TestMethods.METHODS);
	}
//
//	@Override
//	protected TestMethodConfiguration<T> createConfiguration(JLCConfiguration<T> configuration, TestMethod annotation) {
//		TestMethodConfiguration<T> c = super.createConfiguration(configuration, annotation);
//		Class<?> beanUnderTestType = configuration.getBeanUnderTestType();
//		c.apply(beanUnderTestType);
//		return c;
//	}

	public void doBuild(BasicTestGroupConfiguration grouping) {
		
		//Add Signature node
		
		
		this.getElementConfigurations().filter(c -> apiSignatures.contains(c.getSignature()))
				.forEach(c -> c.getSignature().setApi(true));

		//grouping here!!
		
		TestMethodConfigurationNodeBuilder<T> b = new TestMethodConfigurationNodeBuilder<>(grouping);
		
		//this.getElementConfigurations().forEach(b::add);
		this.getElementConfigurations()
		.peek(n-> log.fine("methods -> "+n))
		.forEach(b::add);
		b.build(getConfiguration(),TestMethodConfigurationTestSPINode<T>::new)
		
		.peek(n->n.init(getConfiguration()))
		.peek(n ->{ 
			//FIXME BAD Design!!!
			if(n instanceof BuildableTestNode) {
				((BuildableTestNode)n).build();
			}
			})
		.forEach(this::add);

	}
	
	private static Stream<Method> getUniqueDeclaredMethods(Class<?> type) {	
		UniqueArrayBuilder<Method> methods = new UniqueArrayBuilder<Method>();
		getDeclaredMethods(type, methods);
		return methods.stream();
	}
	
	private static void getDeclaredMethods(Class<?> type, UniqueArrayBuilder<Method> arrayBuilder) {
		Method[] declaredMethods = type.getDeclaredMethods();
		arrayBuilder.append(declaredMethods);
		if(type != Object.class) {
			getDeclaredMethods(type.getSuperclass(), arrayBuilder);
		}
	}
	
	@Override
	protected Stream<MethodSignature> getConfiguratables() {
		Class<?> beanUnderTestType = getConfiguration().getBeanUnderTestType();
		return getUniqueDeclaredMethods(beanUnderTestType)
				.filter( method -> {
					int modifiers = method.getModifiers();
					return !(!Modifier.isPublic(modifiers) && Modifier.isNative(modifiers));
				})				
				.map(MethodSignature::new)
				.filter(MethodSignature.NON_TESTABLES);				
	}
	
//	@Override
//	protected void defaultDetailsInit(JLCConfiguration<T> configuration) {
//
//		Class<?> beanUnderTestType = configuration.getBeanUnderTestType();
//
//		// use default reflection for testable methods
//		getUniqueDeclaredMethods(beanUnderTestType)
//				.map(method -> (TestMethodConfiguration<T>) TestConfigurationFactory.createNew(configuration, getFeatureSettings(), method))
//				.filter((conf) -> MethodSignature.NON_TESTABLES.test(conf.getSignature()))
//				.filter((conf) -> {
//					int modifiers = conf.getExecutor().getModifiers();
//					return !(!Modifier.isPublic(modifiers) && Modifier.isNative(modifiers));
//				})
//				.filter((conf) -> !conf.getSignature().getName().matches(getFeatureSettings().getExcludePatterns()))
//				.peek((m) -> log.info(() -> m.getSignature().toString(true)))
//				.peek(c -> c.setEnabled(true) )
//				.peek( (c) -> c.setMaximalParameterCount(getFeatureSettings().getMaxParameterCount()) )
//				.peek(c -> c.setSimpleName( getFeatureSettings().isSimpleName()))
//				.peek( c -> c.setDbcEnabled(true))
//				//.forEach(this::add);
//				//.forEach(configuration::enforce);
//				.map(this::createEnforcer)
//				.forEach(e -> e.enforce());
//
//	}


//type  = 4000
//field 3000
//constructor 2000
//method = 1000
	@Override
	public int getWeight() {
		return 1000;
	}


	
	
}
