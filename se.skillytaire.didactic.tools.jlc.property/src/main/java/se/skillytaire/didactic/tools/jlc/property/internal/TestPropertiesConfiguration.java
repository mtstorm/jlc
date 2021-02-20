package se.skillytaire.didactic.tools.jlc.property.internal;

import java.util.Arrays;

import se.skillytaire.didactic.tools.jlc.api.TestGroup;
import se.skillytaire.didactic.tools.jlc.property.api.TestProperties;
import se.skillytaire.didactic.tools.jlc.spi.e.AbstractAnnotatedConfiguration;

public class TestPropertiesConfiguration extends AbstractAnnotatedConfiguration<TestProperties> {
	private String optionalNodeName;
	private String requiredNodeName;
	
	public TestPropertiesConfiguration() {
		this.setOptionalNodeName( TestProperties.OPTIONAL);
		this.setRequiredNodeName( TestProperties.REQUIRED);
		this.setGrouping(new TestGroup[] {TestGroup.All}); 
	}
	public TestPropertiesConfiguration(TestProperties annotation) {
		super(annotation);
		this.setOptionalNodeName( annotation.optionalGroupName().value());
		this.setRequiredNodeName( annotation.requiredGroupName().value());
		this.setGrouping(annotation.grouping());
		this.setEnabled(annotation.enabled());

	}
	public String getOptionalNodeName() {
		return optionalNodeName;
	}
	public void setOptionalNodeName(String optionalNodeName) {
		this.optionalNodeName = optionalNodeName;
	}
	public String getRequiredNodeName() {
		return requiredNodeName;
	}
	public void setRequiredNodeName(String requiredNodeName) {
		this.requiredNodeName = requiredNodeName;
	}
	
	
	public boolean isGrouping() {
		return Arrays.stream(this.getGrouping())
				.filter(TestGroup.All::equals )
				.findFirst()
				.isPresent();
	}
}
