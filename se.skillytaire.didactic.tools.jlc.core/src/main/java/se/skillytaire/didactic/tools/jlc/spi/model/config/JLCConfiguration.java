package se.skillytaire.didactic.tools.jlc.spi.model.config;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Logger;

import se.skillytaire.didactic.tools.jlc.api.JLC;
import se.skillytaire.didactic.tools.jlc.api.TestObjectFactories;
import se.skillytaire.didactic.tools.jlc.api.TestObjectFactory;
import se.skillytaire.didactic.tools.jlc.spi.ext.enforcer.Enforcer;
import se.skillytaire.didactic.tools.jlc.spi.internal.VoidTestObjectFactory;
import se.skillytaire.didactic.tools.jlc.spi.model.Archetype;

public class JLCConfiguration<T> {
	private Logger log = Logger.getLogger(JLCConfiguration.class.getName());
	private final Object testInstance;
	private final boolean showEmpyTests;
	public boolean isShowEmpyTests() {
		return showEmpyTests;
	}

	private TestObjectFactory<T> objectFactory;
	private final boolean displayTid;
	private final boolean includeBestPractices;
	private final Class<T> beanUnderTestType;
	private final Map<String, ArchetypedCollection<T, ?>> extensionConfigurations = new HashMap<>();
	
	private final TestOrderDescription order;
	
	private final boolean merge;
	
	private boolean configuredObjectFactory;
	
	//runtime config
//	private TestMethodsConfiguration<T> methodConfiguration = new TestMethodsConfiguration<>();
	
//	private JLCNodeStructureConfiguration nodeStructure;
//	
//	public JLCNodeStructureConfiguration getNodeStructure() {
//		return nodeStructure;
//	}
	/**
	 * Checks if there is a object factory configured outside the SPI.
	 * @return
	 */
	public boolean isConfiguredObjectFactory() {
		return configuredObjectFactory;
	}

	public boolean isMerge() {
		return merge;
	}

	public TestOrderDescription getOrder() {
		return order;
	}

	public JLCConfiguration(Object testInstance) {
		if (testInstance == null) {
			throw new IllegalArgumentException("The test instance is void");
		}
		if (!testInstance.getClass().isAnnotationPresent(JLC.class)) {
			throw new IllegalStateException("The test instance is not annotated with JLC");
		}
		this.testInstance = testInstance;
		final Class<?> testClass = testInstance.getClass();
		JLC jlc = testClass.getAnnotation(JLC.class);
	//	nodeStructure = new JLCNodeStructureConfiguration(jlc);
		
		this.beanUnderTestType = (Class<T>) jlc.value();
		this.displayTid = jlc.displayTid();
		this.includeBestPractices = jlc.bestPractices();
		Class<?> declaredFactory = jlc.testFactory();
		this.showEmpyTests = jlc.showEmptyTests();
		this.order = ConfigurationTool.getSort(testClass, JLC.class);
		this.merge = jlc.merge();
		
		if(declaredFactory == VoidTestObjectFactory.class) {
			 log.info("TestFactory will be resolved having the service provider interface.");
	         Optional<?> factory = TestObjectFactories.resolveFactory(beanUnderTestType);
	         if (factory.isPresent()) {
	        	 objectFactory=
	                  (TestObjectFactory<T>) factory.get();
	         }else {
	        	 throw new IllegalStateException("There is no object factory configured for type "
	                     + beanUnderTestType.getName());
	         }
	         configuredObjectFactory = false;
		} else {
			 log.info("TestFactory will not be resolved having the service provider interface, but the declared is used.");

			try {
				configuredObjectFactory = true;
				objectFactory = (TestObjectFactory<T>) declaredFactory.newInstance();
				if(!objectFactory.isTypeFor(beanUnderTestType)) {
					String msg = String.format("The declared testFactory %s can not be used to create test instances for type %s, the factory is for type %s", declaredFactory.getName(), beanUnderTestType.getName(),objectFactory.type().getName());
					throw new IllegalStateException(msg);
				} else {
					//initialize the factory
					TestObjectFactories.setFields(objectFactory, objectFactory.getClass());
				}
			} catch (Exception e) {
				String msg = String.format("The declared testFactory %s can not be used to create test instances for type %s", declaredFactory.getName(), beanUnderTestType.getName());
				throw new IllegalStateException(msg, e);
			}
		}

		
		


	}
	
//	public <C> void registerExtensionPointConfiguration(String key, C config) {
//		extensionConfigurations.put(key, config);
//	}
//	
//	public <C> Optional<C> findExtensionPointConfiguration(String key) {
//		C config = null;
//		if(extensionConfigurations.containsKey(key)) {
//			config = (C) extensionConfigurations.get(key);
//		}
//		return Optional.ofNullable(config);
//	}
//	
	
	

	
	
	public Object getTestInstance() {
		return testInstance;
	}
	public TestObjectFactory<T> getObjectFactory() {
		return objectFactory;
	}
	public boolean isDisplayTid() {
		return displayTid;
	}
	public boolean isIncludeBestPractices() {
		return includeBestPractices;
	}
	public Class<T> getBeanUnderTestType() {
		return beanUnderTestType;
	}
//	public boolean hasTypeNodes(Archetype archetype) {
//		// TODO Auto-generated method stub
//		return false;
//	}
//
//	public boolean hasFieldNodes(Archetype archetype) {
//		// TODO Auto-generated method stub
//		return false;
//	}
//
//	public boolean hasConstructorNodes(Archetype archetype) {
//		// TODO Auto-generated method stub
//		return false;
//	}

	public TestConfiguration<?,T> enfoce(Object configurable, Archetype archetype) {

		return Enforcer.enforce(this, configurable, archetype);
		
	}

	
	public  < N extends TestConfiguration<N,T> > void register(ArchetypedCollection<T, N> extensionConfiguration, Class<? extends TestConfiguration<N,T>> key) {
		//System.out.println("REGISTAR!!!!" + key.getName());
		ArchetypedCollection<T, ?> result = extensionConfigurations.put(key.getName(), extensionConfiguration);
		if(result != null) {
			throw new IllegalStateException("The key "+ key.getName() +" has already been taken.");
		}
	}
	public boolean containsConfiguration(Class<? extends TestConfiguration<?,T>> key) {
		return this.extensionConfigurations.containsKey(key.getName());
	}
	public < N extends TestConfiguration<N,T>> ArchetypedCollection<T, N> getExtensionConfiguration(Class<?> key){
		return (ArchetypedCollection<T, N>) this.extensionConfigurations.get(key.getName());
	}

	@Override
	public String toString() {
		return String.format(
				"JLCConfiguration [testInstance=%s, objectFactory=%s, displayTid=%s, includeBestPractices=%s, beanUnderTestType=%s, orderDescription=%s,  extensionConfigurations=%s]",
				testInstance, objectFactory, displayTid, includeBestPractices, beanUnderTestType,this.order,
				extensionConfigurations);
	}


	
	


}
