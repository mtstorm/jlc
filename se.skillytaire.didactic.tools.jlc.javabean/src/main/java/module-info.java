module se.skillytaire.didactic.tools.junit.javabean {
   requires transitive se.skillytaire.didactic.tools.jlc.api;

   requires org.junit.jupiter.api;
   requires se.skillytaire.didactic.tools.jlc.lint;
   requires se.skillytaire.didactic.tools.jlc.signature;
   requires se.skillytaire.didactic.tools.jlc.constructor;

  // requires se.skillytaire.didactic.tools.junit.lint;
   exports se.skillytaire.didactic.tools.junit.core.javabean.api;
   exports se.skillytaire.didactic.tools.junit.core.javabean.spi;
   
   provides se.skillytaire.didactic.tools.jlc.lint.spi.LinterFactory with se.skillytaire.didactic.tools.junit.core.javabean.spi.JavaBeanLinterFactory;
   provides se.skillytaire.didactic.tools.jlc.lint.spi.ArchetypeResolver with se.skillytaire.didactic.tools.junit.core.javabean.spi.JavaBeanResolver;
}