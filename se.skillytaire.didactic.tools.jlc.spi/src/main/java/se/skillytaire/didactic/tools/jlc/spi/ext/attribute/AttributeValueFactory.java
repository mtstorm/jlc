package se.skillytaire.didactic.tools.jlc.spi.ext.attribute;

import java.util.ServiceLoader;
import java.util.ServiceLoader.Provider;

import se.skillytaire.didactic.tools.jlc.api.JLCConfiguration;
/**
 * The attribute value factory allows you to create references as an attribute, that will
 * be registered as during the bootstrap of the jlc configuration.
 * 
 *
 * @param <T>
 */
public interface AttributeValueFactory<T> {
	AttributeValue create(JLCConfiguration<T> config);
	
	public static <T> void register(JLCConfiguration<T> jlConfiguration) {
		if(jlConfiguration == null) {
			throw new IllegalArgumentException("jlConfiguration is void");
		}
		ServiceLoader.load(AttributeValueFactory.class)
				.stream()
				.map(Provider::get)
				.map( f->f.create(jlConfiguration))
				.forEach( v-> jlConfiguration.setAttribute(v.getAttributeName(), v));

	}
}
