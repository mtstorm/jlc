package se.skillytaire.didactic.tools.jlc.method.spi.model.config;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import se.skillytaire.didactic.tools.jlc.method.api.TestMethod;
import se.skillytaire.didactic.tools.jlc.method.api.TestMethodGroup;
import se.skillytaire.didactic.tools.jlc.method.api.TestMethods;
import se.skillytaire.didactic.tools.jlc.method.spi.util.MethodTool;
import se.skillytaire.didactic.tools.jlc.signature.spi.MethodSignature;
import se.skillytaire.didactic.tools.jlc.signature.spi.model.config.AbstractTestSignaturesConfiguration;
import se.skillytaire.didactic.tools.jlc.spi.ext.feature.JLCFeatereTestNode;
import se.skillytaire.didactic.tools.jlc.spi.model.config.JLCConfiguration;
import se.skillytaire.didactic.tools.jlc.spi.model.naming.BasicDisplayName;
import se.skillytaire.didactic.tools.jlc.spi.model.naming.DisplayName;

public class TestMethodsConfiguration<T>
		//extends AbstractAnnotatedTestExtention<TestMethod, TestMethods, T, TestMethodConfiguration<T>>
		extends AbstractTestSignaturesConfiguration<TestMethod, TestMethods, T, TestMethodConfiguration<T>, MethodSignature, Method>
implements JLCFeatereTestNode<T> {
	private static final Logger log = Logger.getLogger(TestMethodsConfiguration.class.getName());
	private int maxParameterCount;
	private String excludePatterns;
	//private MethodOrderer order;
	private TestMethodGroup[] grouping;
	private List<MethodSignature> apiSignatures = MethodSignature.defaultApi().collect(Collectors.toList());


	@Override
	protected DisplayName getDefaultDisplayName() {
		return new BasicDisplayName(TestMethods.METHODS);
	}

	@Override
	protected void init() {
		super.init();
	}


	protected void defaultMasterInit(JLCConfiguration<T> configuration) {
		maxParameterCount = TestMethods.DEFAULT_PARAM_COUNT;
		excludePatterns = TestMethods.DEFAULT_EXCLUDE_PATTERN;
		grouping = new TestMethodGroup[0];
	}

	@Override
	protected void init(TestMethods repeater, JLCConfiguration<T> configuration) {
		maxParameterCount = repeater.parameterCount();
		excludePatterns = repeater.excludePattern();
		grouping = repeater.grouping();
	}

	@Override
	protected TestMethodConfiguration<T> createConfiguration(JLCConfiguration<T> configuration, TestMethod annotation) {
		Class<?> beanUnderTestType = configuration.getBeanUnderTestType();
		TestMethodConfiguration<T> c = new TestMethodConfiguration<>(configuration, annotation,maxParameterCount);
		c.apply(beanUnderTestType);
		return c;
	}

//	private class GroupingNodeBuilder {
////		private HashMap<String, List<TestMethodConfiguration<T>>> overloaded = new HashMap<>();
////		private List<TestMethodConfiguration<T>> apis = new ArrayList<TestMethodConfiguration<T>>();
////		private HashMap<String, List<TestMethodConfiguration<T>>> features = new HashMap<>();
////		private HashMap<Archetype, List<TestMethodConfiguration<T>>> archetypes = new HashMap<>();
//
////		private final boolean overload;
////		private final boolean api;
////		private final boolean feature;
////		private final boolean archetype;
////		private List<TestMethodConfiguration<T>> history = new ArrayList<TestMethodConfiguration<T>>(); 
////		
////		private List<TestMethodConfiguration<T>> leftOver= new ArrayList<TestMethodConfiguration<T>>(); 
////		public GroupingNodeBuilder() {
////			if(Arrays.stream(TestMethodsConfiguration.this.grouping).filter(p->p.equals(TestMethodGroup.ALL)).findFirst().isPresent()) {
////				overload = true;
////				api = true;
////				
////				
////				feature = true;
////				archetype = true;
////				
////				
////			}else {
////				overload = Arrays.stream(TestMethodsConfiguration.this.grouping).filter(p->p.equals(TestMethodGroup.Overload)).findFirst().isPresent();
////				api = Arrays.stream(TestMethodsConfiguration.this.grouping).filter(p->p.equals(TestMethodGroup.Api)).findFirst().isPresent();
////				feature = Arrays.stream(TestMethodsConfiguration.this.grouping).filter(p->p.equals(TestMethodGroup.Feature)).findFirst().isPresent();
////				archetype = Arrays.stream(TestMethodsConfiguration.this.grouping).filter(p->p.equals(TestMethodGroup.Archetype)).findFirst().isPresent();
////				
////			}
////			
////			
////		}
//		
//
//	
//		
//		private void analize(TestMethodConfiguration<T> config) {
//			if(!addAPI(config)) {
//				if(!addFeature(config)) {
//					if(!addArchetype(config)) {
//						this.leftOver.add(config);
//					}
//				}
//			}
//		}
//
////		private boolean addArchetype(TestMethodConfiguration<T> config) {
////			boolean processed = false;
////			if(archetype && config.hasArchetype()) {
////				Archetype archetype = config.getArchetype();
////				List<TestMethodConfiguration<T>> archetypeList;
////				if(archetypes.containsKey(feature)) {
////					archetypeList = features.get(feature);
////				}else {
////					archetypeList = new ArrayList<TestMethodConfiguration<T>>();
////					archetypes.put(archetype, archetypeList);
////				}
////				archetypeList.add(config);
////				processed = true;
////			}
////			return processed;
////		}		
////		private boolean addFeature(TestMethodConfiguration<T> config) {
////			boolean processed = false;
////			if(feature && config.hasFeature()) {
////				String feature = config.getFeature();
////				List<TestMethodConfiguration<T>> featureList;
////				if(features.containsKey(feature)) {
////					featureList = features.get(feature);
////				}else {
////					featureList = new ArrayList<TestMethodConfiguration<T>>();
////					features.put(feature, featureList);
////				}
////				featureList.add(config);
////				processed = true;
////			}
////			return processed;
////		}
////		private boolean addAPI(TestMethodConfiguration<T> config) {
////			boolean processed = false;
////			if(api && config.getSignature().isApi()) {
////				apis.add(config);
////				processed = true;
////			}
////			return processed;
////		}
////		public void add(TestMethodConfiguration<T> config) {
////			if(overload) {
////				String methodName = config.getSignature().getName();
////				if(overloaded.containsKey(methodName)) {
////					List<TestMethodConfiguration<T>> other = overloaded.get(methodName);
////					other.add(config);
////				} else {
////					List<TestMethodConfiguration<T>> result = history.stream().filter(c-> c.isOverloaded(config) ).collect(Collectors.toList());
////					if(result.isEmpty()) {
////						this.history.add(config);
////					} else {
////						ArrayList<TestMethodConfiguration<T>> over = new ArrayList<TestMethodConfiguration<T>>(result);
////						over.add(config);
////						overloaded.put(methodName, over);
////						this.history.removeAll(result);
////					}
////				}
////			}else {
////				this.history.add(config);
////			}
////
////		}
//		
//
//		public Stream<JLCTestNode<T>> build() {
//			this.history.forEach(this::analize);
//			Builder<JLCTestNode<T>> streamBuilder = Stream.builder();
//			if(!overloaded.isEmpty()) {
//				Set<Entry<String, List<TestMethodConfiguration<T>>>> entries = overloaded.entrySet();
//				for (Entry<String, List<TestMethodConfiguration<T>>> entry : entries) {
//
//					List<TestMethodConfiguration<T>> configs = entry.getValue();
//					
//					FolderTestNode<T> folder = new FolderTestNode<T>(entry.getKey()+" ("+configs.size()+")");
//					streamBuilder.add(folder);		
//					configs.stream()
//					       .map(TestMethodConfigurationTestSPINode<T>::new)
//					       .peek(n->n.init(getConfiguration()))
//					       .forEach(folder::add);
//				}
//			}
//			if(!apis.isEmpty()) {
//				FolderTestNode<T> folder = new FolderTestNode<T>("Api ("+apis.size()+")");
//				streamBuilder.add(folder);		
//				apis.stream()
//				       .map(TestMethodConfigurationTestSPINode<T>::new)
//				       .peek(n->n.init(getConfiguration()))
//				       .forEach(folder::add);				
//			}
//			if(!archetypes.isEmpty()) {
//				Set<Entry<Archetype, List<TestMethodConfiguration<T>>>> entries = archetypes.entrySet();
//				for (Entry<Archetype, List<TestMethodConfiguration<T>>> entry : entries) {
//					List<TestMethodConfiguration<T>> configs = entry.getValue();
//					FolderTestNode<T> folder = new FolderTestNode<T>(entry.getKey()+" ("+configs.size()+")");
//					streamBuilder.add(folder);		
//					configs.stream()
//					       .map(TestMethodConfigurationTestSPINode<T>::new)
//					       .peek(n->n.init(getConfiguration()))
//					       .forEach(folder::add);
//				}
//			}			
//			if(!features.isEmpty()) {
//				Set<Entry<String, List<TestMethodConfiguration<T>>>> entries = features.entrySet();
//				for (Entry<String, List<TestMethodConfiguration<T>>> entry : entries) {
//					List<TestMethodConfiguration<T>> configs = entry.getValue();
//					FolderTestNode<T> folder = new FolderTestNode<T>(entry.getKey()+" ("+configs.size()+")");
//					streamBuilder.add(folder);		
//					configs.stream()
//					       .map(TestMethodConfigurationTestSPINode<T>::new)
//					       .peek(n->n.init(getConfiguration()))
//					       .forEach(folder::add);
//				}
//			}	
//			
//			this.leftOver.stream()
//		       .map(TestMethodConfigurationTestSPINode<T>::new)
//		       .peek(n->n.init(getConfiguration()))
//		       .forEach(streamBuilder::add);
//			return streamBuilder.build();
//		}
//
//	}
	public void doBuild() {
	
		//Add Signature node
		
		
		this.getElementConfigurations().filter(c -> apiSignatures.contains(c.getSignature()))
				.forEach(c -> c.getSignature().setApi(true));

		//grouping here!!
		TestMethodGroupConfiguration gc = new TestMethodGroupConfiguration(grouping);
		TestMethodConfigurationNodeBuilder<T> b = new TestMethodConfigurationNodeBuilder<>(gc);
		
		//this.getElementConfigurations().forEach(b::add);
		this.getElementConfigurations().forEach(b::add);
		b.build(getConfiguration(),TestMethodConfigurationTestSPINode<T>::new)
		//.peek(n->n.init(getConfiguration()))
		.forEach(this::add);
		
		
		
//		this.getElementConfigurations()
//		//	.sorted( (a,b) -> a.getSignature().compareTo(b.getSignature()) )
//			.map(TestMethodConfigurationTestSPINode<T>::new)
//			
//			.peek(n->n.init(getConfiguration()))
//		
//.peek(  e-> System.out.println("Method node " + e.getDisplayName() ))
//				.forEach(this::add);
	}

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

				// hier gebleven
				.filter((conf) -> !conf.getSignature().getName().matches(this.excludePatterns))

				// HIER GEBLEVEN filteren op niet uit te werken msgs
				.peek((m) -> log.info(() -> m.getSignature().toString(true)))
				.peek(c -> c.setEnabled(true) )
				.peek( (c) -> c.setMaximalParameterCount(getMaxParameterCount()) )
				.forEach(this::add);

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
		return 1000;
	}
	
	
}
