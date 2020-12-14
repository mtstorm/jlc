package se.skillytaire.didactic.tools.junit.core.lint.internal.config;

import se.skillytaire.didactic.tools.junit.core.internal.config.AbstractChildNodeStructureConfiguration;
import se.skillytaire.didactic.tools.junit.core.internal.config.AbstractParentChildNodeStructureConfiguration;
import se.skillytaire.didactic.tools.junit.core.internal.config.Archetype;
import se.skillytaire.didactic.tools.junit.core.internal.ui.DisplayName;
import se.skillytaire.didactic.tools.junit.core.lint.api.Lint;

public final class LintConfiguration extends AbstractParentChildNodeStructureConfiguration implements Comparable<LintConfiguration>{
	private final Archetype archetype;


	/**
	 * Creates a default configuration that has not been declared.
	 * @param archetype
	 */
	public LintConfiguration(Archetype archetype,AbstractChildNodeStructureConfiguration childStructure) {
		super(archetype.toDisplayName(),childStructure );
		this.archetype = archetype;
	}
	/**
	 * Creates a configuration that has been declared using lint.
	 * @param lint
	 */
	public LintConfiguration(Lint lint) {
		super(  LintTool.name(lint),
				LintTool.types(lint),
				LintTool.fields(lint),
				LintTool.constructors(lint),
				LintTool.methods(lint)
		);
		String archetypeName = lint.archetype();
		this.archetype = new Archetype(archetypeName);
	}
	

	/**
	 * When the root config is configured by user and not in this config, it will apply is.
	 * @param rootConfig
	 */
	void apply(LintersConfiguration rootConfig) {
		applyTypes(rootConfig);
		applyFields(rootConfig);
		applyConstructors(rootConfig);
		applyMethods(rootConfig);
	}
	
	private void applyTypes(LintersConfiguration rootConfig) {
		DisplayName rootTypes = rootConfig.getTypes();
		if(this.getTypes().isDefault() && !rootTypes.isDefault()) {
			this.setTypes(rootTypes);
		}
	}
	private void applyFields(LintersConfiguration rootConfig) {
		DisplayName rootFields = rootConfig.getFields();
		if(this.getFields().isDefault() && !rootFields.isDefault()) {
			this.setFields(rootFields);
		}
	}
	
	private void applyConstructors(LintersConfiguration rootConfig) {
		DisplayName rootConstructor = rootConfig.getConstructors();
		if(this.getConstructors().isDefault() && !rootConstructor.isDefault()) {
			this.setConstructors(rootConstructor);
		}
	}
	private void applyMethods(LintersConfiguration rootConfig) {
		DisplayName rootMethods = rootConfig.getMethods();
		if(this.getMethods().isDefault() && !rootMethods.isDefault()) {
			this.setMethods(rootMethods);
		}
	}
	@Override
	public int compareTo(LintConfiguration that) {
		return this.getArchetype().compareTo(that.getArchetype());
	}


	@Override
	public boolean equals(Object obj) {
		boolean equals = super.equals(obj);
		if(!equals && obj instanceof LintConfiguration) {
			LintConfiguration that = (LintConfiguration) obj;
			equals = this.compareTo(that) ==0;
		}
		return equals;
	}


	@Override
	public int hashCode() {
		return this.archetype.hashCode();
	}

	public Archetype getArchetype() {
		return archetype;
	}



	
	
	
}
