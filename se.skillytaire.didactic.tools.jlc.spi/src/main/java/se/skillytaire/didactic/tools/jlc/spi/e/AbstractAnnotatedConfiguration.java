package se.skillytaire.didactic.tools.jlc.spi.e;

import java.lang.annotation.Annotation;

import se.skillytaire.didactic.tools.jlc.api.TestGroup;

/**
 * Annotations are used to declare the configuration. Since annotations are not
 * polymorph, it is bounded to a configuration type, so we have polymorfism
 * again.
 * 
 * @author prolector
 *
 * @param <A>
 */
public class AbstractAnnotatedConfiguration<A extends Annotation> extends AbstractConfiguration
		implements AnnotatedConfiguration<A> {

	private boolean merge;
	private TestGroup[] grouping;

	private A annotation;

	/**
	 * Creates the configuration based on the annotation used to configure this.
	 * 
	 * @param annotation
	 */
	public AbstractAnnotatedConfiguration(A annotation) {
		if (annotation == null) {
			throw new IllegalArgumentException("annotation is void");
		}
		this.annotation = annotation;
	}
	/**
	 * Creates a configuration based on default values.
	 */
	public AbstractAnnotatedConfiguration() {
		super();
		this.setGrouping(new TestGroup[] {TestGroup.None});
	}

	public boolean isMerge() {
		return merge;
	}

	public void setMerge(boolean merge) {
		this.merge = merge;
	}

	@Override
	public boolean isDefault() {
		return this.annotation == null;
	}
	public TestGroup[] getGrouping() {
		return grouping;
	}
	public void setGrouping(TestGroup[] grouping) {
		this.grouping = grouping;
	}
}
