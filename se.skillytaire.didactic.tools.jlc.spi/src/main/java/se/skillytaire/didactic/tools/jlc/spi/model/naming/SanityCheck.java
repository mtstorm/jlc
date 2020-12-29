package se.skillytaire.didactic.tools.jlc.spi.model.naming;

public class SanityCheck extends SimpleDisplayName{
	private static final String SANITY_CHECK = "Sanity check";

	public SanityCheck(String feature) {
		super(SANITY_CHECK, feature);
	}

	public SanityCheck() {
		super(SANITY_CHECK);
	}

}
