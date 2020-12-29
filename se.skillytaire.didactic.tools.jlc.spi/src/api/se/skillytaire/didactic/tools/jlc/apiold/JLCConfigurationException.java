package se.skillytaire.didactic.tools.jlc.apiold;

public class JLCConfigurationException extends AssertionError {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public JLCConfigurationException(String detailMessage) {
		super(detailMessage);
	}

	public JLCConfigurationException(String message, Throwable cause) {
		super(message, cause);
	}

}
