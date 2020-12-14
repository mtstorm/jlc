package se.skillytaire.didactic.tools.jlc.method.spi.model.config;

import java.net.URI;
import java.net.URISyntaxException;

public class MethodUriBuilder {
	private static final String METHOD_SCHEME = "classpath";
	//classpath method
	private String className = "pruts.Name";
	
	private String methodName ="toString()";
	
	//MethodSourceSupport
	public URI Build() throws URISyntaxException {
		URI uri = new URI(METHOD_SCHEME, className, methodName);
		return uri;
	}
	
	
	public static void main(String[] args) throws URISyntaxException {
		System.out.println(new MethodUriBuilder().Build());
	}
}
