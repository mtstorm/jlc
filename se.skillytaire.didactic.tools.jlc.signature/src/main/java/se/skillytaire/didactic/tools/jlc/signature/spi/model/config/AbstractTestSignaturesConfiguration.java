package se.skillytaire.didactic.tools.jlc.signature.spi.model.config;

import java.lang.annotation.Annotation;
import java.lang.reflect.Executable;

import se.skillytaire.didactic.tools.jlc.signature.spi.Signature;
import se.skillytaire.didactic.tools.jlc.spi.ext.feature.JLCFeatereTestNode;
import se.skillytaire.didactic.tools.jlc.spi.model.structure.AbstractAnnotatedTestExtention;

public abstract class AbstractTestSignaturesConfiguration<

A extends Annotation, R extends Annotation, T, N extends TestSignatureConfiguration<N,T,S,E> & Comparable<N>,S extends Signature & Comparable<S>,E extends Executable> extends 
AbstractAnnotatedTestExtention<A,R, T, N>
implements JLCFeatereTestNode<T> {
 

//	protected abstract boolean displaySimpleName();
	
//	private Optional<DisplayName> displaySimpleName() {
//		boolean configuredDisplayName = null;
//		Optional<R> declaredRepeater = getRepeatingValue();
//		if (declaredRepeater.isPresent()) {
//			Optional<Object> optionalDisplayName = getConfiguredTestAnnotationValue(repeaterType,
//					ATTRIBUTE_DISPLAY_NAME);
//			if (optionalDisplayName.isPresent()) {
//				org.junit.jupiter.api.DisplayName dn = (org.junit.jupiter.api.DisplayName) optionalDisplayName.get();
//				String configuredDisplayNameValue = dn.value();
//				if (!JLC.EMPTY.equals(configuredDisplayNameValue)) {
//					configuredDisplayName = new BasicDisplayName(configuredDisplayNameValue);
//				}
//			}
//		}
//		return Optional.ofNullable(configuredDisplayName);
//	}
}
