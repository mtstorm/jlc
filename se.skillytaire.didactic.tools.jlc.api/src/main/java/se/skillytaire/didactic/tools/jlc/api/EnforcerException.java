package se.skillytaire.didactic.tools.jlc.api;

public abstract class EnforcerException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public EnforcerException(String message) {
		super(message);
	}

	public EnforcerException(String message, Throwable cause) {
		super(message, cause);
	}

}
