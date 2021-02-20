package se.skillytaire.didactic.tools.jlc.lint.spi.model.config;

import java.net.URI;
import java.util.Optional;

import se.skillytaire.didactic.tools.jlc.api.Archetype;
import se.skillytaire.didactic.tools.jlc.api.JLCConfiguration;
import se.skillytaire.didactic.tools.jlc.lint.api.Lint;
import se.skillytaire.didactic.tools.jlc.lint.internal.TestLintersConfiguration;
import se.skillytaire.didactic.tools.jlc.spi.model.config.AbstractTestConfiguration;

public class TestLinterConfiguration<T> 

extends AbstractTestConfiguration<TestLintersConfiguration,  TestLinterConfiguration<T>,T> implements Comparable<TestLinterConfiguration<T>>{
   public TestLinterConfiguration(JLCConfiguration<T> parent,TestLintersConfiguration defaults, Lint lint) {
      this(parent,defaults, Archetype.of(lint.archetype()));
      this.setEnabled(lint.enabled());
      this.setDeclared(true);
   }	
	public TestLinterConfiguration(JLCConfiguration<T> parent,TestLintersConfiguration defaults, Archetype archetype) {
		super(parent,defaults);
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
