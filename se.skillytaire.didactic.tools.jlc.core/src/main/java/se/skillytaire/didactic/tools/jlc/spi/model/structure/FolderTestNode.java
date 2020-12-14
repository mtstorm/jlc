package se.skillytaire.didactic.tools.jlc.spi.model.structure;

import se.skillytaire.didactic.tools.jlc.spi.model.naming.BasicDisplayName;
import se.skillytaire.didactic.tools.jlc.spi.model.naming.DisplayName;

/**
 * The folder test node is only meant for grouping test.
 * @author prolector
 *
 * @param <T>
 */
public class FolderTestNode<T> extends AbstractJLCCompositeTestNode<T>{
	private final DisplayName displayName;
	public FolderTestNode(DisplayName displayName) {
		this.displayName = displayName;
	}
	public FolderTestNode(String folderName) {
		this( new BasicDisplayName(folderName));
	}
	
	@Override
	public DisplayName getDisplayName() {
		return displayName;
	}

}
