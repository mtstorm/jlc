import se.skillytaire.didactic.tools.jlc.lint.spi.ArchetypeResolver;
import se.skillytaire.didactic.tools.jlc.lint.spi.LinterFactory;
import se.skillytaire.didactic.tools.junit.core.javabean.spi.JavaBeanLinterFactory;
import se.skillytaire.didactic.tools.junit.core.javabean.spi.JavaBeanResolver;
module se.skillytaire.didactic.tools.junit.javabean {
   requires transitive se.skillytaire.didactic.tools.jlc.api;

   requires org.junit.jupiter.api;
   requires se.skillytaire.didactic.tools.jlc.lint;

  // requires se.skillytaire.didactic.tools.junit.lint;
   exports se.skillytaire.didactic.tools.junit.core.javabean.api;
   exports se.skillytaire.didactic.tools.junit.core.javabean.spi;
   
   provides LinterFactory with JavaBeanLinterFactory;
   provides ArchetypeResolver with JavaBeanResolver;
}