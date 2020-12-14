package se.skillytaire.didactic.tools.jlc.method.internal.tests;

import se.skillytaire.didactic.tools.jlc.signature.spi.MethodSignature;
import se.skillytaire.didactic.tools.jlc.spi.model.naming.DisplayName;

public class MethodTestDisplayName extends DisplayName {

	private final MethodSignature signature;
	private final String referenceName;
	private final String[] parameterValues;

	public MethodTestDisplayName(String feature, MethodSignature signature, String referenceName,
			String... parameterValues) {
		super(feature);
		this.signature = signature;
		this.referenceName = referenceName;
		this.parameterValues = parameterValues;
	}
	public MethodTestDisplayName(MethodSignature signature, String referenceName,
			String... parameterValues) {
		this(null, signature, referenceName, parameterValues);
	}

//	public MethodTestDisplayName(MethodSignature signature) {
//		this(signature, null);
//	}
//
//	public MethodTestDisplayName(MethodSignature signature, String feature) {
//		super(feature);
//		this.signature = signature;
//	}


	public final String value() {
		StringBuilder builder = new StringBuilder();
		String f = getFeature();
		if (f != null && !f.isEmpty()) {
			builder.append(f);
			builder.append(": ");
		}
		builder.append(this.signature.getSignatureTestDescriptionWithRef(referenceName, parameterValues) );
		// System.out.println(builder +" sig "+getSignature());
		

		return builder.append('.').toString();// FIXME JUnit vernachelt mijn displayname!!!
	}



	@Override
	public boolean isDefault() {
		return true;
	}
	

}
