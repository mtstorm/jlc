package se.skillytaire.didactic.tools.junit.core.lint.internal.config;

import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

import se.skillytaire.didactic.tools.junit.core.internal.config.AbstractChildNodeStructureConfiguration;
import se.skillytaire.didactic.tools.junit.core.internal.config.AbstractParentChildNodeStructureConfiguration;
import se.skillytaire.didactic.tools.junit.core.internal.config.Archetype;
import se.skillytaire.didactic.tools.junit.core.internal.config.JLCConfiguration;
import se.skillytaire.didactic.tools.junit.core.internal.config.JLCNodeStructureConfiguration;
import se.skillytaire.didactic.tools.junit.core.internal.ui.BasicDisplayName;
import se.skillytaire.didactic.tools.junit.core.lint.api.Lint;
import se.skillytaire.didactic.tools.junit.core.lint.api.Linters;

public class LintersConfiguration extends AbstractParentChildNodeStructureConfiguration {
	private final Map<Archetype, LintConfiguration> lintersConfiguration = new TreeMap<>();
	private final JLCNodeStructureConfiguration childNodeStructure;
	public LintersConfiguration(JLCNodeStructureConfiguration nodeStructure) {
		super(new LintersDisplayName(), nodeStructure);
		this.childNodeStructure = nodeStructure;
	}

	public LintersConfiguration(JLCNodeStructureConfiguration nodeStructure, Linters linters) {
		super(LintersTool.name(linters), nodeStructure);
		this.childNodeStructure = nodeStructure;
	}

	public void apply(Lint[] lintjes) {
		Arrays.stream(lintjes).map(LintConfiguration::new).peek(c -> c.apply(this))
				.forEach(c -> lintersConfiguration.put(c.getArchetype(), c));
	}

	public LintConfiguration getLintConfiguration(Archetype archetype) {
		LintConfiguration lintConfiguration;
		if (this.lintersConfiguration.containsKey(archetype)) {
			// was configured
			lintConfiguration = this.lintersConfiguration.get(archetype);
		} else {
			lintConfiguration = new LintConfiguration(archetype,childNodeStructure);
		}
		return lintConfiguration;
	}
}
