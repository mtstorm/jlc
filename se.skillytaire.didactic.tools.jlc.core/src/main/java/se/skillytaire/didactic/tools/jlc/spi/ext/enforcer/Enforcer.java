package se.skillytaire.didactic.tools.jlc.spi.ext.enforcer;

import java.util.ServiceLoader;

import se.skillytaire.didactic.tools.jlc.spi.model.Archetype;
import se.skillytaire.didactic.tools.jlc.spi.model.config.JLCConfiguration;
import se.skillytaire.didactic.tools.jlc.spi.model.config.TestConfiguration;

//het is een archetyped collection..... 
public interface Enforcer<N extends TestConfiguration<N, T>, T> {
   /**
    * Finds the config for the config. Implementer do not have to enforce the
    * return type, this will be done by te static method. only
    * 
    * @param jlcConfiguration
    * @param configurable
    * @param type
    * @return
    */
   TestConfiguration<N, T> enfoceConfiguration(JLCConfiguration<T> jlcConfiguration, Object configurable,
         Archetype type);

   /**
    * Checks if the type can be handled by the enforcer or not.
    * 
    * @param configurable
    * @return
    */
   boolean matches(Class<?> configurable);

   /**
    * 
    * @param <T>
    * @param configuration
    * @param configurable
    * @param archetype
    * @return
    * @throws EnforcerNotResolvedException  when there is no enforcer configured
    *                                       for the type in the spi
    * @throws EnforceConfigurationException when the enforcer can not enforce a
    *                                       configuration.
    */
   static <T> TestConfiguration<?, T> enforce(JLCConfiguration<T> configuration, Object configurable,
         Archetype archetype) throws EnforcerNotResolvedException, EnforceConfigurationException {
      if (configuration == null) {
         throw new IllegalArgumentException("configuration is void");
      }
      if (configurable == null) {
         throw new IllegalArgumentException("configurable is void");
      }
      if (archetype == null) {
         throw new IllegalArgumentException("archetype is void");
      }

      Class<?> elementType = configurable.getClass();
      TestConfiguration<?, T> result = null;

      ClassLoader loader = Thread.currentThread().getContextClassLoader();
      @SuppressWarnings("rawtypes")
      ServiceLoader<Enforcer> serviceLoader = ServiceLoader.load(Enforcer.class, loader);
      for (Enforcer<?, T> enforcer : serviceLoader) {
         if (enforcer.matches(elementType)) {
            try {
               result = enforcer.enfoceConfiguration(configuration, configurable, archetype);
               result.enforce(archetype);
            } catch (RuntimeException e) {
               String msg = String.format("There is an enforcer for the type %s but it throws an exception.",
                     elementType.getName());
               throw new EnforceConfigurationException(msg, e);
            }
            break;
         }
      }
      if (result == null) {
         String msg = String.format("There is no enforcer for the type %s.", elementType.getName());
         throw new EnforcerNotResolvedException(msg);

      }
      return result;

   }

}
