package se.skillytaire.didactic.tools.jlc.method.spi.model.config;

import se.skillytaire.didactic.tools.jlc.signature.spi.model.config.ITestSignaturesConfiguration;

public interface ITestMethodsConfiguration extends ITestSignaturesConfiguration {
	public String getExcludePatterns();
}
