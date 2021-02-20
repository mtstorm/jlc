package se.skillytaire.didactic.tools.jlc.api;

/**
 * This exception should be raised by JLC extensions when there is something
 * wrong in there configuration.
 *
 */
public class JLCConfigurationException extends IllegalStateException {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public JLCConfigurationException(final String detailMessage) {
		super(detailMessage);
	}

	public JLCConfigurationException(final String message, final Exception cause) {
		super(message, cause);
	}

}
