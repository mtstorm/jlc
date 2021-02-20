package se.skillytaire.didactic.tools.jlc.property.internal;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;

import se.skillytaire.didactic.tools.jlc.method.spi.MethodTestFactory;
import se.skillytaire.didactic.tools.jlc.method.spi.model.config.TestMethodConfigurationTestSPINode;
import se.skillytaire.didactic.tools.jlc.spi.ext.feature.TestConfigurationNodeFactory;
import se.skillytaire.didactic.tools.jlc.spi.model.naming.BasicDisplayName;
import se.skillytaire.didactic.tools.jlc.spi.model.naming.DisplayName;
import se.skillytaire.didactic.tools.jlc.spi.model.structure.AbstractJLCCompositeTestNode;
import se.skillytaire.didactic.tools.jlc.spi.model.structure.JLCTestNode;
import se.skillytaire.didactic.tools.jlc.spi.util.ClassProperty;
@Deprecated
public class PropertyTestNode<T> extends AbstractJLCCompositeTestNode<T>{
	private final ClassProperty classProperty;

	
	public PropertyTestNode(ClassProperty classProperty) {
		super();
		this.classProperty = classProperty;
	}

	@Override
	public DisplayName getDisplayName() {
		return new BasicDisplayName(classProperty.getName());
	}
//	
//	@Override
//	public void init(JLCConfiguration<T> configuration) {
//		super.init(configuration);
//		//find all the signatures and fields, should be enforced
////		if(classProperty.hasSetMethod()) {
////			Method method = classProperty.getSetMethod();
////			enfore(method);
////		}
////		if(classProperty.hasGetMethod()) {
////			Method method = classProperty.getGetMethod();
////			enfore(method);
////		}
////		if(classProperty.hasHasMethod()) {
////			Method method = classProperty.getHasMethod();
////			enfore(method);			
////		}
//		//FIXME Fields toevoegen
//		//configuration.enforce(classProperty.getField(), PROPERTY_ARCHETYPE);
//	}
	


	@Override
	public Optional<URI> toUri() {
		StringBuilder builder = new StringBuilder();
		Field field = classProperty.getField();
		builder.append("method:").append(field.getDeclaringClass().getName()).append('#').append(field.getName());
		URI uri = null;
		try {
			uri = new URI(builder.toString());
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return Optional.ofNullable(uri);
	}
	
	public void build() {
		this.classProperty
		    .methods()
		    .map(m -> TestConfigurationNodeFactory.create(getConfiguration(),get() , m, true))
		    .forEach(this::add);

	}
}
