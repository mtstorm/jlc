package se.skillytaire.didactic.tools.jlc.spi.e;

import java.util.HashMap;
import java.util.Map;

import se.skillytaire.didactic.tools.jlc.api.JLCConfiguration;
import se.skillytaire.didactic.tools.jlc.api.JLCConfigurationException;
import se.skillytaire.didactic.tools.jlc.spi.ext.attribute.AttributeValue;
import se.skillytaire.didactic.tools.jlc.spi.ext.enforcer.TestConfigurationFactory;

/**
 * This registry hold the configuratable classifications together with the
 * feature configuration.
 * 
 */
public final class JLCFeatureRegistry implements AttributeValue {

   private static final String ATTRIBUTE_NAME = JLCFeatureRegistry.class.getName();
   private Map<Class<?>, JLCFeatureConfiguration> registry = new HashMap<>();

   JLCFeatureRegistry() {
   }

   public void register(JLCFeatureConfiguration feature) {

      TestConfigurationFactory.configuratableTypes(feature).forEach(c -> this.registry.put(c, feature));

   }

   public <D extends JLCFeatureConfiguration> D get(Object configuratable) {
      Class<?> key = configuratable.getClass();
      JLCFeatureConfiguration result = registry.get(key);
      if (result == null) {
         throw new JLCConfigurationException(
               "There is no feature configuration found for configuratable type " + key.getName());
      }
      return (D) result;
   }

   @Override
   public String getAttributeName() {
      return this.getClass().getName();
   }

   public static <T> JLCFeatureRegistry getInstance(JLCConfiguration<T> config) {
      return config.getAttribute(ATTRIBUTE_NAME);
   }
}
