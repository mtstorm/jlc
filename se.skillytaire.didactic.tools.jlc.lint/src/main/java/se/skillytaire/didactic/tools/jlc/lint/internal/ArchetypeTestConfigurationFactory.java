package se.skillytaire.didactic.tools.jlc.lint.internal;

import se.skillytaire.didactic.tools.jlc.api.Archetype;
import se.skillytaire.didactic.tools.jlc.api.JLCConfiguration;
import se.skillytaire.didactic.tools.jlc.lint.spi.model.config.TestLinterConfiguration;
import se.skillytaire.didactic.tools.jlc.spi.ext.enforcer.AbstractTestConfigurationFactory;
import se.skillytaire.didactic.tools.jlc.spi.util.ClassProperty;

public class ArchetypeTestConfigurationFactory<T>
      extends AbstractTestConfigurationFactory<TestLintersConfiguration, TestLinterConfiguration<T>, T> {

   private static final Class<?> TYPE = Archetype.class;

//@Override
//public TestLinterConfiguration<T> create(JLCConfiguration<T> configuration,TestPropertiesConfiguration defaults, Object basedOn) {
//	ClassProperty classProperty = (ClassProperty) basedOn;
//	TestPropertyConfiguration<T> conf = new TestPropertyConfiguration<>(configuration, defaults, classProperty);
//	return conf;
//}

   @Override
   public Class<?> type() {
      return TYPE;
   }

   @Override
   public TestLinterConfiguration<T> create(JLCConfiguration<T> configuration, TestLintersConfiguration defaults,
         Object basedOn) {
      Archetype archetype = (Archetype) basedOn;
      return new TestLinterConfiguration<>(configuration, defaults, archetype);
   }
}
