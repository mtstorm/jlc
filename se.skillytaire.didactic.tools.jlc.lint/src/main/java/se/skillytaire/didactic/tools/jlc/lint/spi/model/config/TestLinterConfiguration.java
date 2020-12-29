package se.skillytaire.didactic.tools.jlc.lint.spi.model.config;

import java.net.URI;
import java.util.Optional;

import se.skillytaire.didactic.tools.jlc.api.Archetype;
import se.skillytaire.didactic.tools.jlc.api.JLCConfiguration;
import se.skillytaire.didactic.tools.jlc.spi.model.config.AbstractTestConfiguration;

public class TestLinterConfiguration<T> extends AbstractTestConfiguration<TestLinterConfiguration<T>,T> implements Comparable<TestLinterConfiguration<T>>{
	
	public TestLinterConfiguration(JLCConfiguration<T> parent, Archetype archetype) {
		super(parent);
		setArchetype(archetype);
		
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
	public int compareTo(TestLinterConfiguration<T> that) {
		return this.getArchetype().compareTo(that.getArchetype());
	}

	@Override
	public Optional<URI> toUri() {
		// TODO Auto-generated method stub
		return Optional.empty();//FIXME to class reference
	}
	
	
}
