package se.skillytaire.didactic.tools.jlc.signature.spi.model.config;

import java.lang.annotation.Annotation;
import java.lang.reflect.Executable;

import se.skillytaire.didactic.tools.jlc.api.JLCConfiguration;
import se.skillytaire.didactic.tools.jlc.api.TestGroup;
import se.skillytaire.didactic.tools.jlc.signature.spi.Signature;
import se.skillytaire.didactic.tools.jlc.spi.e.AnnotatedConfiguration;
import se.skillytaire.didactic.tools.jlc.spi.e.JLCFeatureConfiguration;
import se.skillytaire.didactic.tools.jlc.spi.ext.feature.JLCFeatereTestNode;
import se.skillytaire.didactic.tools.jlc.spi.model.config.BasicTestGroupConfiguration;
import se.skillytaire.didactic.tools.jlc.spi.model.structure.AbstractRepeatableAnnotatedTestExtention;

public abstract class AbstractTestSignaturesConfiguration<
A extends Annotation, 
R extends Annotation, 
T, 
C extends AbstractSignatureFeatureConfiguration<R>,
N extends TestSignatureConfiguration<N,T,S,E,C> & Comparable<N>,
S extends Signature & Comparable<S>,
E extends Executable


> extends 
AbstractRepeatableAnnotatedTestExtention<A,R, T,C, N> {

	@Override
	public final void doBuild() {
		TestGroup[] grouping = getFeatureSettings().getGrouping();
		BasicTestGroupConfiguration groupConfig = new BasicTestGroupConfiguration(grouping );
		doBuild(groupConfig);
		
	}
	
	public abstract void doBuild(BasicTestGroupConfiguration groupConfig);
}
