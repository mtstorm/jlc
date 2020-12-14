module se.skillytaire.didactic.tools.jlc.method {
   requires java.logging;
   requires transitive org.junit.jupiter.api;
   requires transitive se.skillytaire.didactic.tools.jlc.core;
   requires se.skillytaire.didactic.tools.jlc.signature;
   
   exports se.skillytaire.didactic.tools.jlc.method.api;
   exports se.skillytaire.didactic.tools.jlc.method.spi;
   exports se.skillytaire.didactic.tools.jlc.method.spi.util;
   exports se.skillytaire.didactic.tools.jlc.method.spi.model.config;
   exports se.skillytaire.didactic.tools.jlc.method.spi.model.structure;
   
}