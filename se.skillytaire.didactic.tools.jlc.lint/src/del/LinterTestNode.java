package se.skillytaire.didactic.tools.junit.core.lint.internal;

import java.util.Optional;

import se.skillytaire.didactic.tools.junit.core.internal.config.Archetype;
import se.skillytaire.didactic.tools.junit.core.internal.config.JLCConfiguration;
import se.skillytaire.didactic.tools.junit.core.internal.ui.DisplayName;
import se.skillytaire.didactic.tools.junit.core.lint.internal.config.LintConfiguration;
import se.skillytaire.didactic.tools.junit.core.lint.internal.config.LintersConfiguration;
import se.skillytaire.didactic.tools.junit.core.spi.AbstractJLCCompositeTestNode;
import se.skillytaire.didactic.tools.junit.core.spi.FolderTestNode;

public class LinterTestNode<T> extends AbstractJLCCompositeTestNode<T>{
	private Archetype archetype;
	
	private DisplayName displayName;
	
	

	
	@Override
	public void init(JLCConfiguration<T> configuration) {
		super.init(configuration);
		Optional<LintersConfiguration> optionalLintersConfiguration = configuration.findExtensionPointConfiguration(LintersTestNode.LINT_CONFIG);
		if(!optionalLintersConfiguration.isPresent()) {
			throw new IllegalStateException("Linters configuration is not present.");
		}
		LintersConfiguration lintersConfiguration = optionalLintersConfiguration.get();
		LintConfiguration lintConfiguration = lintersConfiguration.getLintConfiguration(archetype);
		displayName = lintConfiguration.getParent();
		//verschillende nodes per lint
		//lintConfiguration.create
		//types
		if(configuration.hasTypeNodes(archetype)) {
			DisplayName folderName = lintConfiguration.getTypes();
			FolderTestNode<T> types = createFolder(folderName);
			//add the testnodes there
		}
		if(configuration.hasFieldNodes(archetype)) {
			DisplayName folderName = lintConfiguration.getFields();
			FolderTestNode<T> types = createFolder(folderName);
			//add the testnodes there
		}		
		if(configuration.hasConstructorNodes(archetype)) {
			DisplayName folderName = lintConfiguration.getFields();
			FolderTestNode<T> types = createFolder(folderName);
			//add the testnodes there
		}		
		//constructors
		
		//fields
		
		//methods
		
		
	}

	@Override
	public DisplayName getDisplayName() {
		return displayName;
	}

	@Override
	public boolean isReRunnable() {
		return false;
	}

}
