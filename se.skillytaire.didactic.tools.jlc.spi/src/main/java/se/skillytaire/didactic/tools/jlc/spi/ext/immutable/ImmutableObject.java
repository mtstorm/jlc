package se.skillytaire.didactic.tools.jlc.spi.ext.immutable;

import java.util.Iterator;
import java.util.Optional;
import java.util.ServiceLoader;

import se.skillytaire.didactic.tools.jlc.api.JLCConfiguration;
import se.skillytaire.didactic.tools.jlc.api.TestObjectFactory;
/**
 * This checks if a type is marked immutable.
 * @author prolector
 *
 * @param <T>
 */
public interface ImmutableObject {
	
	boolean isImmutable(TestObjectFactory<?> factory);
	

	
	
	public static <T> boolean isImmutableType(JLCConfiguration<T> configuration, Class<?> type) {
		if (type == null) {
			throw new IllegalArgumentException("Type is void");
		}
		Optional<TestObjectFactory<Object>> possibleTestFactory =  configuration.resolveFactory(type);
		boolean isImmutable = false;
		if(possibleTestFactory.isPresent()) {
			Iterator<ImmutableObject> ios = ServiceLoader.load(ImmutableObject.class).iterator();
			while (ios.hasNext()) {
				ImmutableObject immutableObject = ios.next();
				boolean checked = immutableObject.isImmutable(possibleTestFactory.get());
				if(checked) {
					isImmutable = checked;
					break;
				}
			}
		} 
		return isImmutable;
	}
}
