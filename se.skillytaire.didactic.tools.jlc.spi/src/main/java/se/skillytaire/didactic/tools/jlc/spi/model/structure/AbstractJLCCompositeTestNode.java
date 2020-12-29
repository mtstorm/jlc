package se.skillytaire.didactic.tools.jlc.spi.model.structure;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;

import se.skillytaire.didactic.tools.jlc.api.Archetype;
import se.skillytaire.didactic.tools.jlc.spi.model.naming.DisplayName;

/**
 * A test composite is a place holder for testable elements.
 * 
 * @author prolector
 *
 */
public abstract class AbstractJLCCompositeTestNode<T> extends AbstractJLCTestNode<T> implements CompositeTestNode<T> {
	static final Logger log = Logger.getLogger(AbstractJLCCompositeTestNode.class.getName());
	private List<JLCTestNode<T>> children = new ArrayList<>();
	private Comparator<? super JLCTestNode<T>> sorter;

	public Comparator<? super JLCTestNode<T>> getSorter() {
		return sorter;
	}
	public boolean hasSorter() {
		return sorter != null;
	}
	public void setSorter(Comparator<? super JLCTestNode<T>> sorter) {
		this.sorter = sorter;
	}

	public AbstractJLCCompositeTestNode() {
		super();
	}

	/**
	 * Only adds the child to this node, will not apply the archetype filter.
	 */
	public void add(JLCTestNode<T> childNode) {
		children.add(childNode);
	}

	public boolean hasChildren() {
		return !children.isEmpty();
	}

	public boolean remove(JLCTestNode<T> childNode) {
		return children.remove(childNode);
	}
	@Override
	public Stream<JLCTestNode<T>> children() {
		Stream<JLCTestNode<T>> cs = children.stream();
		if(hasSorter()) {
			cs = cs.sorted(getSorter());
		}
		return cs;
	}
	
	
	@Override
	public void clear() {
		this.children.clear();
	}

	/**
	 * Applys the filter also to the children.
	 * {@inheritDoc}
	 */
	@Override
	public void applyFilter(Archetype archetype) {
		super.applyFilter(archetype);
		applyFilter();
	}
	/**
	 * Apply the archetype to the children.
	 */
	protected void applyFilter() {
		if(isFiltered()) {
			children().forEach(c -> c.applyFilter(getFilter()));
		}
	}



//	@Override
//	public void init(JLCConfiguration<T> configuration) {
//		super.init(configuration);
//		children().forEach( c->c.init(configuration));
//	}

	@Override
	public Optional<URI> toUri() {
		StringBuilder uriString = new StringBuilder();
		Class<?> beanType = this.getConfiguration().getBeanUnderTestType();

		String packagePath = beanType.getName();//.replace('.', '/');
		//uriString.append(packagePath);// .append(".java");
		//uriString.append("#toString()");


		//URI fileURI = file.toURI();
		uriString.append("method:pruts.Name#toString()");
		URI fileURI = null;
		try {
			fileURI = new URI(uriString.toString());
		} catch (URISyntaxException e) {
		   String msg = String.format("Error in resolving the uri %s for the class %s", uriString,beanType.getName());
		   log.log(Level.SEVERE, msg, e);
		}
//		try {
//			fileURI.toURL();
//		} catch (MalformedURLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			log.error(e, () -> "No URL");
//		}
	//	System.out.println(" EXT URI " + fileURI);
		// FIXME linken naar de Java Source
		return Optional.ofNullable(fileURI);
	}



	@Override
	public Iterator<JLCTestNode<T>> iterator() {
		return children.iterator();
	}

	public FolderTestNode<T> createFolder(String folderName){
		FolderTestNode<T> folderNode = new FolderTestNode<>(folderName);
		return handleFolder(folderNode);
	}
	public FolderTestNode<T> createFolder(DisplayName folderName){
		FolderTestNode<T> folderNode = new FolderTestNode<>(folderName);
		return handleFolder(folderNode);
	}
	private FolderTestNode<T> handleFolder(FolderTestNode<T> folderNode) {
		folderNode.init(getConfiguration());
		this.add(folderNode);
		return folderNode;
	}
}
