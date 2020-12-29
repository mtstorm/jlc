package se.skillytaire.didactic.tools.jlc.property.internal;

import java.lang.reflect.Field;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;

import se.skillytaire.didactic.tools.jlc.api.JLCConfiguration;
import se.skillytaire.didactic.tools.jlc.spi.model.naming.BasicDisplayName;
import se.skillytaire.didactic.tools.jlc.spi.model.naming.DisplayName;
import se.skillytaire.didactic.tools.jlc.spi.model.structure.AbstractJLCCompositeTestNode;
import se.skillytaire.didactic.tools.jlc.spi.util.ClassProperty;

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
	
	
	
	
	
	@Override
	public void init(JLCConfiguration<T> configuration) {
		super.init(configuration);
		//find all the signatures and fields, should be enforced
	}

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
}
