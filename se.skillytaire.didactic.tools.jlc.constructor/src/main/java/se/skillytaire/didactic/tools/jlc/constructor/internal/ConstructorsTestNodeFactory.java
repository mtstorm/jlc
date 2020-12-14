package se.skillytaire.didactic.tools.jlc.constructor.internal;

import se.skillytaire.didactic.tools.jlc.constructor.spi.model.config.TestConstructorsConfiguration;
import se.skillytaire.didactic.tools.jlc.spi.ext.feature.AbstractFeatureTestNodeFactory;
import se.skillytaire.didactic.tools.jlc.spi.ext.feature.FeatureTestNodeFactory;
import se.skillytaire.didactic.tools.jlc.spi.ext.feature.JLCFeatereTestNode;

/**
 * Adds the constructor feature to the core using spi of the core.
 * 
 * @author prolector
 *
 * @param <T>
 */
public class ConstructorsTestNodeFactory<T> extends AbstractFeatureTestNodeFactory<T>
      implements FeatureTestNodeFactory<T> {

   @Override
   public JLCFeatereTestNode<T> create() {
      return new TestConstructorsConfiguration<>();
   }

}
