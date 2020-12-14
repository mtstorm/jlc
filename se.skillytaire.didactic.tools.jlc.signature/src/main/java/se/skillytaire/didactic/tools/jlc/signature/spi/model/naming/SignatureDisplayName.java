package se.skillytaire.didactic.tools.jlc.signature.spi.model.naming;

import se.skillytaire.didactic.tools.jlc.signature.spi.Signature;
import se.skillytaire.didactic.tools.jlc.spi.model.naming.DisplayName;

public class SignatureDisplayName extends DisplayName {

	private final Signature signature;
	private boolean shortSignature;

	public SignatureDisplayName(Signature signature) {
		this(signature, null);
	}

	public SignatureDisplayName(Signature signature, String feature) {
		super(feature);
		this.signature = signature;
	}

	private String referenceName;
	public final String value() {
		StringBuilder builder = new StringBuilder();
		String f = getFeature();
		if (f != null && !f.isEmpty()) {
			builder.append(f);
			builder.append(": ");
		}
		if(referenceName != null) {
			builder.append(referenceName);
			builder.append('.');
		}
		builder.append(this.signature.toString(isShortSignature()));
		// System.out.println(builder +" sig "+getSignature());
		return builder.append('.').toString();// FIXME JUnit vernachelt mijn displayname!!!
	}

	/**
	 * @return the shortSignature
	 */
	public boolean isShortSignature() {
		return shortSignature;
	}

	/**
	 * @param shortSignature the shortSignature to set
	 */
	public void setShortSignature(boolean shortSignature) {
		this.shortSignature = shortSignature;
	}

	public Signature getSignature() {
		return signature;
	}

	@Override
	public boolean isDefault() {
		return true;
	}
}
