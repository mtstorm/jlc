package se.skillytaire.didactic.tools.jlc.method.spi.model.config;

import se.skillytaire.didactic.tools.jlc.api.JLC;
import se.skillytaire.didactic.tools.jlc.api.JLCConfiguration;
import se.skillytaire.didactic.tools.jlc.api.TestGroup;
import se.skillytaire.didactic.tools.jlc.method.api.TestMethods;
//JLCDefaultFeatereTestConfiguration
@Deprecated
/**
 * @deprecated not used
 */
public class JLCDefaultTestMethodsConfiguration<T> {
	private final int maxParameterCount;
	private final TestGroup[] grouping;
	private final boolean simpleName;
	private final boolean merge;
	private final String excludePatterns;


	public JLCDefaultTestMethodsConfiguration(JLCConfiguration<T> configuration) {
		if(configuration == null) {
			throw new IllegalArgumentException("Configuration is void");
		}
		this.grouping = new TestGroup[0];
		this.maxParameterCount = TestMethods.DEFAULT_PARAM_COUNT;
		this.simpleName = TestMethods.SIMPLE_NAME;
		this.excludePatterns = TestMethods.DEFAULT_EXCLUDE_PATTERN;
		this.merge = JLC.MERGE;
	}
	
	public JLCDefaultTestMethodsConfiguration(TestMethods annotation, JLCConfiguration<T> configuration) {
		if(configuration == null) {
			throw new IllegalArgumentException("Configuration is void");
		}
		if(annotation == null) {
			throw new IllegalArgumentException("Annotation is void");
		}
		this.grouping = annotation.grouping();
		this.maxParameterCount = annotation.parameterCount();
		this.simpleName = annotation.simpleName();
		this.excludePatterns = annotation.excludePattern();
		this.merge = annotation.merge();
	}
	public int getMaxParameterCount() {
		return maxParameterCount;
	}
	
	
	public TestGroup[] getGrouping() {
		return grouping.clone();
	}
	
	
	public boolean isSimpleName() {
		return simpleName;
	}
	
	
	public boolean isMerge() {
		return merge;
	}
	
	
	public String getExcludePatterns() {
		return excludePatterns;
	}

}
