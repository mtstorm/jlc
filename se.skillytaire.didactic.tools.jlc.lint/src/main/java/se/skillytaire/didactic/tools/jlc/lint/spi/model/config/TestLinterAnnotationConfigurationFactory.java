package se.skillytaire.didactic.tools.jlc.lint.spi.model.config;

import se.skillytaire.didactic.tools.jlc.api.JLCConfiguration;
import se.skillytaire.didactic.tools.jlc.lint.api.Lint;
import se.skillytaire.didactic.tools.jlc.lint.internal.TestLintersConfiguration;
import se.skillytaire.didactic.tools.jlc.method.api.TestMethod;
import se.skillytaire.didactic.tools.jlc.spi.ext.enforcer.AbstractTestConfigurationFactory;

public class TestLinterAnnotationConfigurationFactory <T> 

extends AbstractTestConfigurationFactory<TestLintersConfiguration, TestLinterConfiguration<T>,T>{
   private static final Class<?> TYPE = Lint.class;

   @Override
   public TestLinterConfiguration<T> create(JLCConfiguration<T> configuration, TestLintersConfiguration defaults,
         Object basedOn) {
      Lint lint = (Lint) basedOn;
      return new TestLinterConfiguration<>(configuration, defaults, lint);
   }

   @Override
   public Class<?> type() {
      return TYPE;
   }

}
