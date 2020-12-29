package se.skillytaire.didactic.tools.jlc.property.internal;

import java.net.URI;
import java.util.Optional;

import se.skillytaire.didactic.tools.jlc.api.Archetype;
import se.skillytaire.didactic.tools.jlc.api.JLCConfiguration;
import se.skillytaire.didactic.tools.jlc.property.api.TestProperty;
import se.skillytaire.didactic.tools.jlc.spi.model.config.AbstractTestConfiguration;
//Has field and methods
public class TestPropertyConfiguration<T> extends AbstractTestConfiguration<TestPropertyConfiguration<T>,T> implements Comparable<TestPropertyConfiguration<T>>{
	private Property<T> property;
	
	
	public TestPropertyConfiguration(JLCConfiguration<T> parent, TestProperty annotation) {
		super(parent);
	}
	public TestPropertyConfiguration(JLCConfiguration<T> parent, Property<T> property) {
		super(parent);
		this.property = property;
	}

	@Override
	public String getName() {
		String value;
		if(hasDisplayNameValue()) {
			value = getDisplayNameValue() +" ("+getArchetype().getName()+")";
		}else {
			value = getArchetype().getName();
		}
		return value;
	}

	@Override
	public int compareTo(TestPropertyConfiguration<T> that) {
		return this.getArchetype().compareTo(that.getArchetype());
	}

	@Override
	public Optional<URI> toUri() {
		// TODO Auto-generated method stub
		return Optional.empty();//FIXME to class reference
	}
	
	
}
