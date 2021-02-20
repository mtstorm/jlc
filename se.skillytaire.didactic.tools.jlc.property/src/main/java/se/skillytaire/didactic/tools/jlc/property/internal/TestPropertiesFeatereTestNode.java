package se.skillytaire.didactic.tools.jlc.property.internal;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import se.skillytaire.didactic.tools.jlc.property.api.TestProperties;
import se.skillytaire.didactic.tools.jlc.property.api.TestProperty;
import se.skillytaire.didactic.tools.jlc.spi.ext.feature.JLCFeatereTestNode;
import se.skillytaire.didactic.tools.jlc.spi.model.naming.BasicDisplayName;
import se.skillytaire.didactic.tools.jlc.spi.model.naming.DisplayName;
import se.skillytaire.didactic.tools.jlc.spi.model.structure.AbstractRepeatableAnnotatedTestExtention;
import se.skillytaire.didactic.tools.jlc.spi.model.structure.FolderTestNode;
import se.skillytaire.didactic.tools.jlc.spi.util.ClassProperty;
import se.skillytaire.didactic.tools.jlc.spi.util.ClassTool;

public class TestPropertiesFeatereTestNode<T>
		extends AbstractRepeatableAnnotatedTestExtention<TestProperty, TestProperties, T, TestPropertiesConfiguration,TestPropertyConfiguration<T>>
		implements JLCFeatereTestNode<T> {
	private static final Logger log = Logger.getLogger(TestPropertiesFeatereTestNode.class.getName());

	private boolean hasOptionalProperties;

	private boolean hasRequiredProperties;

	@Override
	protected DisplayName getDefaultDisplayName() {
		return new BasicDisplayName(TestProperties.PROPERTIES);
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

//	protected void defaultMasterInit(JLCConfiguration<T> configuration) {
//		optionalNodeName = TestProperties.OPTIONAL;
//		requiredNodeName = TestProperties.REQUIRED;
//		grouping = TestProperties.GROUP;
//	}

//	@Override
//	protected void init(TestProperties repeater, JLCConfiguration<T> configuration) {
//		optionalNodeName = repeater.optionalGroupName().value();
//		requiredNodeName = repeater.requiredGroupName().value();
//		grouping = repeater.grouping();
//	}

//	@Override
//	protected TestPropertyConfiguration<T> createConfiguration(JLCConfiguration<T> configuration,
//			TestProperty annotation) {
//		TestPropertyConfiguration<T> c = new TestPropertyConfiguration<>(configuration,getFeatureSettings(), annotation);
////fixme via factory
//		return c;
//	}

//	@Override
//	public void init(JLCConfiguration<T> configuration) {
//
//		super.init(configuration);
////ENFORCE PROPERY?????
//
////		ClassTool cl = new ClassTool(getConfiguration().getBeanUnderTestType());
////		List<ClassProperty> properties = cl.getProperties();
////
////		if (this.grouping) {
////			FolderTestNode<T> requiredProperties = null;
////			FolderTestNode<T> optionalProperties = null;
////			if (hasRequiredProperties) {
////				requiredProperties = new FolderTestNode<>(this.requiredNodeName);
////				requiredProperties.init(getConfiguration());
////				this.add(requiredProperties);
////			}
////			if (hasOptionalProperties) {
////				optionalProperties = new FolderTestNode<>(this.optionalNodeName);
////				optionalProperties.init(getConfiguration());
////				this.add(optionalProperties);
////			}
////			for (ClassProperty classProperty : properties) {
////				PropertyTestNode<T> propertyNode = new PropertyTestNode<>(classProperty);
////				propertyNode.init(getConfiguration());
////				boolean isOptional = OptionalPropertyValidator.isOptional(classProperty);
////				if (isOptional) {
////					optionalProperties.add(propertyNode);
////				} else {
////					requiredProperties.add(propertyNode);
////				}
////			}
////		}
//
////		this.getAllElementConfigurations()
////				.map(LintTestNode<T>::new)               //creates the node
////				.peek( n -> n.init(getConfiguration()))  //initializes the node
////				.peek( e-> System.out.println("Jo detector pruts "+ e.getClass()))
////				.forEach(this::add);
//	}

//	protected void defaultDetailsInit(JLCConfiguration<T> configuration) {
//		// load the properties having there signatures
//		ClassTool cl = new ClassTool(configuration.getBeanUnderTestType());
//		List<ClassProperty> properties = cl.getProperties();
//		for (ClassProperty classProperty : properties) {
////			boolean isOptional = OptionalPropertyValidator.isOptional(classProperty);
////			if (isOptional) {
////				this.hasOptionalProperties = true;
////			} else {
////				this.hasRequiredProperties = true;
////			}
//			TestPropertyConfiguration<T> config  = configuration.enforce(classProperty);
//			config.setDisplayNameValue(classProperty.getName());
//		//	TestPropertyConfiguration<T> config = new TestPropertyConfiguration<>(configuration, property);
//			// -> reconfigure the methods and fields.
//		}
//
////		Stream<Archetype> detectors = ArchetypeResolver.find(configuration.getTestInstance().getClass(), configuration.getBeanUnderTestType());
////		detectors
////			//.peek( e-> System.out.println("Jo detector "+ e.getName()))
////			.map( a -> new TestPropertyConfiguration<T>(configuration,a) )
////			.peek( c->c.setEnabled(true))
////		    .forEach(this::add);
//	}

//type  = 4000
//field 3000
//constructor 2000
//method = 1000
	@Override
	public int getWeight() {
		return 300; // always at the bottom
	}

	@Override
	public void doBuild() {
		if (this.getFeatureSettings().isGrouping()) {
			FolderTestNode<T> requiredProperties = new FolderTestNode<>(this.getFeatureSettings().getRequiredNodeName());
			FolderTestNode<T> optionalProperties = new FolderTestNode<>(this.getFeatureSettings().getOptionalNodeName());
			
			List<TestPropertyConfiguration<T>> elements = this.getElementConfigurations().collect(Collectors.toList());
			//this.getElementConfigurations().forEach(TestPropertyConfigurationTestNode::new);
			for (TestPropertyConfiguration<T> config : elements) {
				TestPropertyConfigurationTestNode<T> propertyNode = new TestPropertyConfigurationTestNode<>(config);
				propertyNode.init(getConfiguration());
				propertyNode.build();
				if (config.isOptional()) {
					optionalProperties.add(propertyNode);
				} else {
					requiredProperties.add(propertyNode);
				}
			}
			if(requiredProperties.hasChildren()) {
				requiredProperties.init(getConfiguration());
				this.add(requiredProperties);
			}
			if(optionalProperties.hasChildren()) {
				optionalProperties.init(getConfiguration());
				this.add(optionalProperties);
			}
		} else {
			this.getElementConfigurations().forEach(TestPropertyConfigurationTestNode::new);
		}

	}

	@Override
	protected  Stream<ClassProperty> getConfiguratables() {
		Class<?> beanUnderTestType = getConfiguration().getBeanUnderTestType();
		ClassTool cl = new ClassTool(beanUnderTestType);
		return cl.properties();
	}

}
