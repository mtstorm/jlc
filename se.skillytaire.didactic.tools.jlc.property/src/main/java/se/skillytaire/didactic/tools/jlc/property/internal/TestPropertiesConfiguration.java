package se.skillytaire.didactic.tools.jlc.property.internal;

import java.util.List;
import java.util.logging.Logger;

import se.skillytaire.didactic.tools.jlc.api.JLCConfiguration;
import se.skillytaire.didactic.tools.jlc.property.api.TestProperties;
import se.skillytaire.didactic.tools.jlc.property.api.TestProperty;
import se.skillytaire.didactic.tools.jlc.property.spi.OptionalPropertyValidator;
import se.skillytaire.didactic.tools.jlc.spi.ext.feature.JLCFeatereTestNode;
import se.skillytaire.didactic.tools.jlc.spi.model.naming.BasicDisplayName;
import se.skillytaire.didactic.tools.jlc.spi.model.naming.DisplayName;
import se.skillytaire.didactic.tools.jlc.spi.model.structure.AbstractRepeatableAnnotatedTestExtention;
import se.skillytaire.didactic.tools.jlc.spi.model.structure.FolderTestNode;
import se.skillytaire.didactic.tools.jlc.spi.util.ClassProperty;
import se.skillytaire.didactic.tools.jlc.spi.util.ClassTool;

public class TestPropertiesConfiguration<T>
		extends AbstractRepeatableAnnotatedTestExtention<TestProperty, TestProperties, T, TestPropertyConfiguration<T>>
		implements JLCFeatereTestNode<T> {
	private static final Logger log = Logger.getLogger(TestPropertiesConfiguration.class.getName());

	private boolean hasOptionalProperties;

	private boolean hasRequiredProperties;

	private String optionalNodeName;
	private String requiredNodeName;
	private boolean grouping;

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

	protected void defaultMasterInit(JLCConfiguration<T> configuration) {
		optionalNodeName = TestProperties.OPTIONAL;
		requiredNodeName = TestProperties.REQUIRED;
		grouping = TestProperties.GROUP;
	}

	@Override
	protected void init(TestProperties repeater, JLCConfiguration<T> configuration) {
		optionalNodeName = repeater.optionalGroupName().value();
		requiredNodeName = repeater.requiredGroupName().value();
		grouping = repeater.grouping();
	}

	@Override
	protected TestPropertyConfiguration<T> createConfiguration(JLCConfiguration<T> configuration,
			TestProperty annotation) {
		TestPropertyConfiguration<T> c = new TestPropertyConfiguration<>(configuration, annotation);
		c.setEnforced(true);
		c.setDeclared(true);
		c.setDisplayNameValue(annotation.displayName().value());
		c.setEnabled(annotation.enabled());
		return c;
	}

	public void doBuild() {

		ClassTool cl = new ClassTool(getConfiguration().getBeanUnderTestType());
		List<ClassProperty> properties = cl.getProperties();

		if (this.grouping) {
			FolderTestNode<T> requiredProperties = null;
			FolderTestNode<T> optionalProperties = null;
			if (hasRequiredProperties) {
				requiredProperties = new FolderTestNode<>(this.requiredNodeName);
				requiredProperties.init(getConfiguration());
				this.add(requiredProperties);
			}
			if (hasOptionalProperties) {
				optionalProperties = new FolderTestNode<>(this.optionalNodeName);
				optionalProperties.init(getConfiguration());
				this.add(optionalProperties);
			}
			for (ClassProperty classProperty : properties) {
				PropertyTestNode<T> propertyNode = new PropertyTestNode<>(classProperty);
				propertyNode.init(getConfiguration());
				boolean isOptional = OptionalPropertyValidator.isOptional(classProperty);
				if (isOptional) {
					optionalProperties.add(propertyNode);
				} else {
					requiredProperties.add(propertyNode);
				}
			}
		}

//		this.getAllElementConfigurations()
//				.map(LintTestNode<T>::new)               //creates the node
//				.peek( n -> n.init(getConfiguration()))  //initializes the node
//				.peek( e-> System.out.println("Jo detector pruts "+ e.getClass()))
//				.forEach(this::add);
	}

	protected void defaultDetailsInit(JLCConfiguration<T> configuration) {
		// load the properties having there signatures
		ClassTool cl = new ClassTool(configuration.getBeanUnderTestType());
		List<ClassProperty> properties = cl.getProperties();
		for (ClassProperty classProperty : properties) {
			boolean isOptional = OptionalPropertyValidator.isOptional(classProperty);
			if (isOptional) {
				this.hasOptionalProperties = true;
			} else {
				this.hasRequiredProperties = true;
			}

			// -> reconfigure the methods and fields.
		}

//		Stream<Archetype> detectors = ArchetypeResolver.find(configuration.getTestInstance().getClass(), configuration.getBeanUnderTestType());
//		detectors
//			//.peek( e-> System.out.println("Jo detector "+ e.getName()))
//			.map( a -> new TestPropertyConfiguration<T>(configuration,a) )
//			.peek( c->c.setEnabled(true))
//		    .forEach(this::add);
	}

//type  = 4000
//field 3000
//constructor 2000
//method = 1000
	@Override
	public int getWeight() {
		return 1500; // always at the bottom
	}

}
