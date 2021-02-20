package se.skillytaire.didactic.tools.jlc.spi.model.config;

import se.skillytaire.didactic.tools.jlc.api.Archetype;
import se.skillytaire.didactic.tools.jlc.api.JLCConfiguration;
import se.skillytaire.didactic.tools.jlc.spi.TestConfiguration;
import se.skillytaire.didactic.tools.jlc.spi.e.AbstractConfiguration;
import se.skillytaire.didactic.tools.jlc.spi.e.JLCFeatureConfiguration;

public abstract class AbstractTestConfiguration<E extends JLCFeatureConfiguration, N extends AbstractTestConfiguration<E,N,T>,T> 
	extends AbstractConfiguration implements TestConfiguration<E,N,T> {
	private boolean inverted;
	private Archetype archetype;

	private String feature;
	/**
	 * Check if the configuration is enforced.
	 */
	private boolean enforced;
	
	/**
	 * Check if the configuration is created using an annotation.
	 */
	private boolean declared;

	private final JLCConfiguration<T> parent;
	
	private final E extentionDefaults;
	@Override
	public E getExtentionDefaults() {
		return extentionDefaults;
	}
	protected AbstractTestConfiguration(JLCConfiguration<T> parent, E extentionDefaults) {
		if(parent == null) {
			throw new IllegalArgumentException("Parent configuration has not been set");
		}
		if(extentionDefaults == null) {
			throw new IllegalArgumentException("Extention defaults  has not been set");
		}
		this.parent = parent;
		this.extentionDefaults = extentionDefaults;
	}
	/**
	 * The merge override this.
	 * Skipping the enforced property
	 */
	@Override
	public void merge(N n) {
		this.setEnabled(n.isEnabled());
		this.setInverted(n.isInverted());
		this.setArchetype(n.getArchetype());
		this.setFeature(n.getFeature());
		this.setDeclared(n.isDeclared());
		String dnn  = n.getName().trim();
		if(!dnn.isEmpty()) {
			this.setDisplayNameValue(dnn);
		}
	}
	





	public String getFeature() {
		return feature;
	}

	public boolean hasFeature() {
		return feature != null;
	}

	public void setFeature(String feature) {
		if (feature != null && !feature.trim().isEmpty()) {
			this.feature = feature;
		}
	}

	public final boolean isInverted() {
		return inverted;
	}

	public final void setInverted(boolean inverted) {
		this.inverted = inverted;
	}



	@Override
	public final Archetype getArchetype() {
		return archetype;
	}

	@Override
	public final boolean hasArchetype() {
		return archetype != null;
	}

	protected void setArchetype(Archetype archetype) {
		this.archetype = archetype;
	}

	@Override
	public final boolean enabledForType(Archetype type) {
		boolean enabledForType = false;
		if (hasArchetype()) {
			enabledForType = this.getArchetype().equals(type);
		}
		return enabledForType;
	}
	public final JLCConfiguration<T> getParent() {
		return parent;
	}
	public final boolean isEnforced() {
		return enforced;
	}

	public final void setEnforced(boolean enforced) {
		this.enforced = enforced;
	}
	public final boolean isDeclared() {
		return declared;
	}

	public final void setDeclared(boolean declared) {
		this.declared = declared;
	}

	@Override
	public void enforce(Archetype archetype) {
		if(archetype == null) {
			throw new IllegalArgumentException("Archetype is null");
		}
		setEnabled(true);
		setEnforced(true);
		setArchetype(archetype);
	}
	@Override
	public String toString() {
		return String.format(
				"%s [enabled=%s, inverted=%s, archetype=%s, feature=%s, enforced=%s, declared=%s,  displayNameValue=%s ",
				getClass().getSimpleName(), isEnabled(), inverted, archetype, feature, enforced, declared,  getDisplayNameValue());
	}
	
	
}
