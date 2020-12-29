package se.skillytaire.didactic.tools.jlc.spi.internal;

import java.util.Comparator;
import java.util.Optional;
import java.util.stream.Stream;

import se.skillytaire.didactic.tools.jlc.api.JLC;
import se.skillytaire.didactic.tools.jlc.api.JLCConfigurationTest;
import se.skillytaire.didactic.tools.jlc.api.JLCTestFactory;
import se.skillytaire.didactic.tools.jlc.api.PackagePresentation;
import se.skillytaire.didactic.tools.jlc.api.TestObjectFactory;
import se.skillytaire.didactic.tools.jlc.spi.ext.feature.JLCFeatereTestNode;
import se.skillytaire.didactic.tools.jlc.spi.model.naming.BasicDisplayName;
import se.skillytaire.didactic.tools.jlc.spi.model.naming.DisplayName;
import se.skillytaire.didactic.tools.jlc.spi.model.structure.AbstractJLCCompositeTestNode;
import se.skillytaire.didactic.tools.jlc.spi.model.structure.CompositeTestNode;
import se.skillytaire.didactic.tools.jlc.spi.model.structure.FolderTestNode;
import se.skillytaire.didactic.tools.jlc.spi.model.structure.JLCTestNode;

public class TestFactoriesTestNode<T> extends AbstractJLCCompositeTestNode<T> implements JLCFeatereTestNode<T> {
	Comparator<? super JLCTestNode<T>> sorter = (a,b) -> 1 * a.getDisplayName().value().compareTo(b.getDisplayName().value());
	{
		this.setSorter(sorter);
	}
	@Override
	public DisplayName getDisplayName() {
		return new BasicDisplayName("Configuration");
	}

	@Override
	public int getWeight() {
		return 250;
	}

	@Override
	public void build() {
		this.getConfiguration()
			.factories()
			.forEach(this::add);

	}

	public void add(TestObjectFactory<?> factory) {
	
		Class<?> clazz = factory.type();
		Package pkg = clazz.getPackage();
		String path = clazz.getPackageName().replace('.', JLCTestFactory.PATH_SEPARATOR);
		Class<?> enclosingClass = clazz.getEnclosingClass();
		if(enclosingClass != null) {
			path += JLCTestFactory.PATH_SEPARATOR + enclosingClass.getSimpleName();
		}
		String name = clazz.getSimpleName();
		if (factory.getClass().isAnnotationPresent(JLCTestFactory.class)) {
			JLCTestFactory info = factory.getClass().getAnnotation(JLCTestFactory.class);
			String aPath = info.path();
			String aName = info.displayName().value();
			if(!JLC.EMPTY.equals(aPath)) {
				path = aPath;
			}
			if(!JLC.EMPTY.equals(aName)) {
				name = aName;
			}			
		}

		
		Optional<JLCConfigurationTest> annotation = this.getConfiguration().getTestAnnotation(JLCConfigurationTest.class);
		CompositeTestNode<T> root;
		if(annotation.isPresent()) {
			JLCConfigurationTest a = annotation.get();
			TestObjectFactoryTestNode<T> childNode = new TestObjectFactoryTestNode<>(factory);
			childNode.setName(name);
			if(a.layout().equals(PackagePresentation.HIERARCHICAL)) {
				root = ensureHierarchicalPath(this, path);
			} else {
				char separator = a.separator();
				String nodeName;
				if(separator != JLCTestFactory.PATH_SEPARATOR) {
					nodeName = path.replace(JLCTestFactory.PATH_SEPARATOR, separator);
				} else {
					nodeName = path;
				}
				root = ensureChildNode(this, nodeName);
			}
			root.add(childNode);
			childNode.init(getConfiguration());
		}

		
		

		
	}
	
	
	private CompositeTestNode<T> ensureChildNode(CompositeTestNode<T> rootNode, String nodeName){
		Optional<JLCTestNode<T>> possibleChildNode = rootNode.children().filter(c->c.getDisplayName().value().equals(nodeName)).findFirst();

		CompositeTestNode<T> newParent;
		
		if(possibleChildNode.isEmpty()) {
			FolderTestNode<T> folder = new FolderTestNode<>(nodeName);
			folder.setSorter(sorter );
			folder.init(getConfiguration());
			newParent = folder;
			rootNode.add(newParent);
		} else {
			newParent = (CompositeTestNode<T>) possibleChildNode.get();
		}	
		return newParent;
	}
	
	
	private CompositeTestNode<T> ensureHierarchicalPath(CompositeTestNode<T> rootNode, String path) {
		
		int index = path.indexOf(JLCTestFactory.PATH_SEPARATOR);
		String nodeName;
		if(index == -1) {
			nodeName = path;
		} else {
			nodeName = path.substring(0, index);
		}
//		Optional<JLCTestNode<T>> possibleChildNode = rootNode.children().filter(c->c.getDisplayName().value().equals(nodeName)).findFirst();
//
//		CompositeTestNode<T> newParent;
//		
//		if(possibleChildNode.isEmpty()) {
//			FolderTestNode<T> folder = new FolderTestNode<>(nodeName);
//			folder.setSorter(sorter );
//			folder.init(getConfiguration());
//			newParent = folder;
//			rootNode.add(newParent);
//		} else {
//			newParent = (CompositeTestNode<T>) possibleChildNode.get();
//		}
		CompositeTestNode<T> newParent = ensureChildNode(rootNode, nodeName);
		if(index != -1 ) {
			int length = path.length();
			int nextStart = index+1;
			String resultingPath = path.substring(nextStart, length);
			newParent = ensureHierarchicalPath(newParent, resultingPath);
		}
		
		
		return newParent;
	}
	
	@Override
	public void peek() {

	}

}
