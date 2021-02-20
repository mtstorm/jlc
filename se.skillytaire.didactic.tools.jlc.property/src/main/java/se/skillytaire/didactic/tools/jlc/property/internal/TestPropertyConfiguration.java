package se.skillytaire.didactic.tools.jlc.property.internal;

import java.lang.reflect.Method;
import java.net.URI;
import java.util.Optional;

import se.skillytaire.didactic.tools.jlc.api.Archetype;
import se.skillytaire.didactic.tools.jlc.api.JLCConfiguration;
import se.skillytaire.didactic.tools.jlc.property.api.TestProperty;
import se.skillytaire.didactic.tools.jlc.property.spi.OptionalPropertyValidator;
import se.skillytaire.didactic.tools.jlc.signature.spi.model.naming.SignatureDisplayName;
import se.skillytaire.didactic.tools.jlc.spi.model.config.AbstractTestConfiguration;
import se.skillytaire.didactic.tools.jlc.spi.util.ClassProperty;
//Has field and methods
public class TestPropertyConfiguration<T> extends AbstractTestConfiguration<TestPropertiesConfiguration, TestPropertyConfiguration<T>,T> implements Comparable<TestPropertyConfiguration<T>>{
	//FIXME binding aan model maken->extern project
	private ClassProperty property;
	
	
	protected ClassProperty getProperty() {
		return property;
	}
	public TestPropertyConfiguration(JLCConfiguration<T> parent,TestPropertiesConfiguration defaults, TestProperty annotation) {
		super(parent, defaults);
		this.setEnforced(true);
		this.setDeclared(true);
		this.setDisplayNameValue(annotation.displayName().value());
		this.setEnabled(annotation.enabled());
	}
	public TestPropertyConfiguration(JLCConfiguration<T> parent,TestPropertiesConfiguration defaults, ClassProperty property) {
		super(parent,defaults);
		this.property = property;
	}


	
	
	@Override
   public int hashCode() {
      return this.property.getName().hashCode();
   }
   @Override
   public boolean equals(Object obj) {

      boolean equals = super.equals(obj);
      if(!equals && obj instanceof TestPropertyConfiguration that) {
         equals = this.property.getName().equals(that.property.getName());
      }
      return equals;
   }
   @Override
	public final String getName() {
		String displayName;
		if(hasDisplayNameValue()) {
			displayName = getDisplayNameValue();
		}else {
			displayName = this.property.getName();
		}

		return displayName;
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
	
	
	



	public boolean isOptional() {
		return OptionalPropertyValidator.isOptional(this.property);
	}
   @Override
   public String toString() {
      // TODO Auto-generated method stub
      return String.format( "%s  propertyName=%s", super.toString(), getName());
   }

}
