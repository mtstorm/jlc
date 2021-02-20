package se.skillytaire.didactic.tools.jlc.signature.spi.model.config;

import se.skillytaire.didactic.tools.jlc.api.TestGroup;
import se.skillytaire.didactic.tools.jlc.spi.e.JLCFeatureConfiguration;

public interface ITestSignaturesConfiguration extends JLCFeatureConfiguration {
	public int getMaxParameterCount();
	
	
	public TestGroup[] getGrouping() ;
	
	
	public boolean isSimpleName();
	
	
	public boolean isMerge();
	
	

	
	//iets met merge hier?
}
