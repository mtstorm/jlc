module se.skillytaire.didactic.tools.jlc.lint {
   requires org.junit.jupiter.api;
   requires java.logging;
   requires transitive se.skillytaire.didactic.tools.jlc.api;
   requires transitive se.skillytaire.didactic.tools.jlc.spi;
   requires transitive se.skillytaire.didactic.tools.jlc.method;
   requires transitive se.skillytaire.didactic.tools.jlc.constructor;
   requires transitive se.skillytaire.didactic.tools.jlc.signature;
   exports se.skillytaire.didactic.tools.jlc.lint.api;
   exports se.skillytaire.didactic.tools.jlc.lint.spi;
   exports se.skillytaire.didactic.tools.jlc.lint.spi.model.config;
   exports se.skillytaire.didactic.tools.jlc.lint.spi.model.naming;
   exports se.skillytaire.didactic.tools.jlc.lint.spi.model.structure;
   uses se.skillytaire.didactic.tools.jlc.lint.spi.ArchetypeResolver;
   uses se.skillytaire.didactic.tools.jlc.lint.spi.LinterFactory;
   provides se.skillytaire.didactic.tools.jlc.spi.ext.feature.FeatureTestNodeFactory 
   with 
   se.skillytaire.didactic.tools.jlc.lint.internal.LintersTestNodeFactory;
}