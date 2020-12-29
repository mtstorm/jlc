package se.skillytaire.didactic.tools.jlc.signature.spi.model.config;

import java.lang.annotation.Annotation;
import java.lang.reflect.Executable;

import se.skillytaire.didactic.tools.jlc.api.JLCConfiguration;
import se.skillytaire.didactic.tools.jlc.api.TestGroup;
import se.skillytaire.didactic.tools.jlc.signature.spi.Signature;
import se.skillytaire.didactic.tools.jlc.spi.ext.feature.JLCFeatereTestNode;
import se.skillytaire.didactic.tools.jlc.spi.model.config.BasicTestGroupConfiguration;
import se.skillytaire.didactic.tools.jlc.spi.model.structure.AbstractRepeatableAnnotatedTestExtention;

public abstract class AbstractTestSignaturesConfiguration<

A extends Annotation, R extends Annotation, T, N extends TestSignatureConfiguration<N,T,S,E> & Comparable<N>,S extends Signature & Comparable<S>,E extends Executable> extends 
AbstractRepeatableAnnotatedTestExtention<A,R, T, N>
implements JLCFeatereTestNode<T> {
 
	private int maxParameterCount;
	private TestGroup[] grouping;
	private boolean simpleName;
	private boolean merge;
	protected final boolean isMerge() {
		return merge;
	}
	protected final void setMerge(boolean merge) {
		this.merge = merge;
	}
	/**
	 * Sets the grouping
	 */
	@Override
	protected void defaultMasterInit(JLCConfiguration<T> configuration) {
		this.setGrouping(new TestGroup[0]);
	}
	protected final int getMaxParameterCount() {
		return maxParameterCount;
	}
	protected final void setMaxParameterCount(int maxParameterCount) {
		this.maxParameterCount = maxParameterCount;
	}
	protected final TestGroup[] getGrouping() {
		return grouping;
	}
	protected final void setGrouping(TestGroup[] grouping) {
		this.grouping = grouping;
	}
	protected final boolean isSimpleName() {
		return simpleName;
	}
	protected final void setSimpleName(boolean simpleName) {
		this.simpleName = simpleName;
	}
	@Override
	public final void doBuild() {
		BasicTestGroupConfiguration groupConfig = new BasicTestGroupConfiguration(grouping);
		doBuild(groupConfig);
		
	}
	public abstract void doBuild(BasicTestGroupConfiguration groupConfig);
}
