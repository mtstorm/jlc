package se.skillytaire.didactic.tools.jlc.spi.e;
/**
 * This is the root interface for all the items that can be configured.
 *
 */
public interface Configuration {
   /**
    * Is this configuration enabled or not.
    * @return true when this is enabled.
    */
   boolean isEnabled();
   
   String getDisplayNameValue();
}
