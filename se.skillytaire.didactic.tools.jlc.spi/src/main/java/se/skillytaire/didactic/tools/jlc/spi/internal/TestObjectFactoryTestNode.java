package se.skillytaire.didactic.tools.jlc.spi.internal;

import java.net.URI;
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Stream;
import java.util.stream.Stream.Builder;

import se.skillytaire.didactic.tools.jlc.api.ComparableTestObjectFactory;
import se.skillytaire.didactic.tools.jlc.api.JLCConfiguration;
import se.skillytaire.didactic.tools.jlc.api.Pooled;
import se.skillytaire.didactic.tools.jlc.api.TestObjectFactory;
import se.skillytaire.didactic.tools.jlc.spi.model.naming.BasicDisplayName;
import se.skillytaire.didactic.tools.jlc.spi.model.naming.DisplayName;
import se.skillytaire.didactic.tools.jlc.spi.model.structure.AbstractJLCCompositeTestNode;
import se.skillytaire.didactic.tools.jlc.spi.model.structure.FolderTestNode;

public class TestObjectFactoryTestNode<T> extends AbstractJLCCompositeTestNode<T>{
	private final TestObjectFactory<?> factory;

	private String name;


	public TestObjectFactoryTestNode(TestObjectFactory<?> factory) {
		super();
		this.factory = factory;

		
	}
	
	private Stream<TestFactoryInvoker<?>> invokers(){
		Builder<TestFactoryInvoker<?>> builder = Stream.builder();
		ThisReference thisReference = new ThisReference(factory);
		ThatReference thatReference = new ThatReference(factory);
		builder.add(thisReference);
		builder.add(thatReference);
		if(factory instanceof ComparableTestObjectFactory) {
			ComparableTestObjectFactory<?> c = (ComparableTestObjectFactory<?>) factory;
			LessThenReference lessThenReference = new LessThenReference(c);
			GreaterThenReference greaterThenReference = new GreaterThenReference(c);
			builder.add(lessThenReference);
			builder.add(greaterThenReference);
		}
		return builder.build();
	}
	@Override
	public void init(JLCConfiguration<T> configuration) {
		super.init(configuration);

		
		if(factory.isFor(Void.class)) {
			FolderTestNode<T> nullCheck = createFolder("null checks");
			invokers()
		       .map(p -> new TestFactoryNullReferenceNode<T>(p))
		       .peek( n-> nullCheck.add(n))
		       .forEach(n->n.init(configuration));			
		}else {
		
			FolderTestNode<T> nullCheck = createFolder("null checks");
			invokers()
			       .map(p -> new TestFactoryNonNullReferenceNode<T>(p))
			       .peek( n-> nullCheck.add(n))
			       .forEach(n->n.init(configuration));
			FolderTestNode<T> sameCheck = new FolderTestNode<>("Same references");       
			FolderTestNode<T> diffCheck = new FolderTestNode<>("Differend references");
			       
			Pooled allPooled = this.factory.getClass().getAnnotation(Pooled.class);     
			if( allPooled != null) {			
				invokers()
			       .map(p -> new TestFactorySameReferenceNode<T>(p))
			       .peek( n-> sameCheck.add(n))
			       .forEach(n->n.init(configuration));       
				}			
			 else {
					invokers()
					   .filter(TestFactoryInvoker::isNotPooled )
				       .map(i -> new TestFactoryDifferendReferenceNode<T>(i))
				       .peek( n-> diffCheck.add(n))
				       .forEach(n->n.init(configuration));    
					invokers()
					   .filter(TestFactoryInvoker::isPooled )
				       .map(i -> new TestFactorySameReferenceNode<T>(i))
				       .peek( n-> sameCheck.add(n))
				       .forEach(n->n.init(configuration));  
			} 				 
 			boolean hasDif = diffCheck.hasChildren();
 			boolean hasRef = sameCheck.hasChildren();
 			if(hasDif && hasRef) {
 				FolderTestNode references = createFolder("Reference Tests");
 				references.add(sameCheck);
 				references.add(diffCheck);
 				sameCheck.init(getConfiguration());
 				diffCheck.init(getConfiguration());
 			} else if(hasDif) {
 				this.add(diffCheck);
 				diffCheck.init(getConfiguration());
 			} else {
 				this.add(sameCheck);
 				sameCheck.init(getConfiguration());
 			}
		}
		       //peek( n-> nullCheck.add(n)).forEach(n->n.init(configuration));
		
//		TestFactoryNonNullReference<T> testThis = new TestFactoryNonNullReference<>(thisReference);
//		nullCheck.add(testThis);
//		testThis.init(configuration);
//		
//		TestFactoryNonNullReference<T> testThat = new TestFactoryNonNullReference<>(thatReference);
//		nullCheck.add(testThat);
//		testThat.init(configuration);
		

		
	}

	@Override
	public DisplayName getDisplayName() {
		DisplayName displayName;
		Object[] types = Arrays.stream(factory.types()).filter( c -> c != factory.type() ).map( c ->c.getSimpleName() ).toArray(size-> new String[size]);
		if(types.length == 0) {
			displayName = new BasicDisplayName(this.getName());
		} else {
			displayName = new BasicDisplayName(this.getName() +" " + Arrays.toString(types) +"" );
		}
		return displayName;
	}


	public Optional<URI> toUri() {
		return Optional.empty();
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
