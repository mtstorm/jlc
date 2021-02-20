package se.skillytaire.didactic.tools.jlc.spi.model.structure;

import java.lang.annotation.Annotation;
import java.lang.annotation.Repeatable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.stream.Stream;

import se.skillytaire.didactic.tools.jlc.api.Archetype;
import se.skillytaire.didactic.tools.jlc.api.JLC;
import se.skillytaire.didactic.tools.jlc.api.JLCConfiguration;
import se.skillytaire.didactic.tools.jlc.api.JLCConfigurationException;
import se.skillytaire.didactic.tools.jlc.api.TestOrderDescription;
import se.skillytaire.didactic.tools.jlc.spi.TestConfiguration;
import se.skillytaire.didactic.tools.jlc.spi.e.AnnotatedConfiguration;
import se.skillytaire.didactic.tools.jlc.spi.e.AnnotatedConfigurationFactory;
import se.skillytaire.didactic.tools.jlc.spi.e.JLCFeatureConfiguration;
import se.skillytaire.didactic.tools.jlc.spi.ext.feature.Enforcer;
import se.skillytaire.didactic.tools.jlc.spi.ext.feature.ExtensionConfigurations;
import se.skillytaire.didactic.tools.jlc.spi.ext.feature.JLCFeatereTestNode;
import se.skillytaire.didactic.tools.jlc.spi.internal.ConfigurationTool;
import se.skillytaire.didactic.tools.jlc.spi.model.naming.BasicDisplayName;
import se.skillytaire.didactic.tools.jlc.spi.model.naming.DisplayName;
import se.skillytaire.didactic.tools.jlc.spi.util.AnnotationTool;

/**
 * Abstraction for repeating test extensions.
 * 
 * @author Skillytaire AB
 *
 * @param <A>
 * @param <T>
 * @param <N>
 */
public abstract class AbstractRepeatableAnnotatedTestExtention
		<
		 A extends Annotation, 
		 R extends Annotation, 
		 T, 
		 C extends AnnotatedConfiguration<R>,
		 N extends TestConfiguration<C,N,T>
        >
		extends AbstractJLCCompositeTestNode<T>  implements JLCFeatereTestNode<T>{
	static final Logger log = Logger.getLogger(AbstractRepeatableAnnotatedTestExtention.class.getName());
	
	private static final String ATTRIBUTE_DISPLAY_NAME = "displayName";
	private static final String ATTRIBUTE_ENABLED = "enabled";
	private static final String ATTRIBUTE_MERGE = "merge";
	
	private final Class<A> annotationType;

	private final Class<R> repeaterType;

	private final Class<? extends JLCFeatureConfiguration> testConfigType;

	private C featureSettings;

	public C getFeatureSettings() {
		return featureSettings;
	}

	private TestOrderDescription order;
	/**
	 * Creates the enforcer
	 * @param configuratable
	 * @return
	 */
	protected final Enforcer<T, C, N> createEnforcer(Object configuratable){
		return Enforcer.of( getConfiguration(), getFeatureSettings(), configuratable);
	}
	
//	private static Logger log = Logger.getLogger(AbstractRepeatableAnnotatedTestExtention.class.getName());
	@SuppressWarnings("unchecked")
	protected AbstractRepeatableAnnotatedTestExtention() {
		ParameterizedType pt = (ParameterizedType) getClass().getGenericSuperclass();
		Type[] args = pt.getActualTypeArguments();
		this.annotationType = (Class<A>) args[0];
		testConfigType =  (Class<? extends JLCFeatureConfiguration>) args[3];
		log.fine("Test Configuration type " + testConfigType.getName());
		if (this.annotationType.isAnnotationPresent(Repeatable.class)) {
			Repeatable repeatable = this.annotationType.getAnnotation(Repeatable.class);
			repeaterType = (Class<R>) repeatable.value();
		} else {
			throw new IllegalStateException("This implementation needs to have a repeatable!");
		}
	}

	
	private ExtensionConfigurations<T> extensionConfiguration;
	
	/**
	 * Initializes the specific configuration needed to build the structure.
	 * When the structure is already there it will not reload the configuration.
	 */
	@Override
	public void init(JLCConfiguration<T> configuration) {
		super.init(configuration);
		extensionConfiguration = configuration.getAttribute(ExtensionConfigurations.ATTRIBUTE_NAME);

		order =configuration.getOrder().override(
				ConfigurationTool.getSort(configuration.getTestInstance().getClass(), repeaterType)
				);
		//FIXME MEthods off -> property on!!!!
//			if (isRepeaterEnabled()) {
				configureRepeater(configuration);
				configureRepeatingElement(configuration);
				init();
//			}else {
//				log.warning("The extention for "+testConfigType.getName()+ " is disabled.");
//			}
	}

	private void configureRepeater(JLCConfiguration<T> configuration) {
		log.fine("Repeater enabled for "+ testConfigType.getName());
		//configuration.register(elementConfigurations, testConfigType);
		Optional<R> optionalRepeater = getRepeatingValue();
		if (optionalRepeater.isPresent()) {
			R repeatable = optionalRepeater.get();
			log.fine("Use the configured initialization since annotation "+repeaterType.getName()+ " is found.");
			featureSettings = AnnotatedConfigurationFactory.resolve(configuration, repeatable);
			//init(repeatable, configuration);
		} else {
			//defaultMasterInit(configuration);
			log.fine("Use the default initialization like having the annotation "+repeaterType.getName());
			featureSettings = AnnotatedConfigurationFactory.resolve(configuration, repeaterType);
		}		
	}
	private void configureRepeatingElement(JLCConfiguration<T> configuration) {
		if (hasConfiguredAnnotations()) {
			if(mergeConfiguration()) {
				log.fine("Use default details initialization in order to merge with the annotations.");
				defaultDetailsInitialization();
				getConfiguredEnforcers().map( e->e.merge()).forEach(e->e.enforce());
			}else {
				getConfiguredEnforcers().forEach(e -> e.enforce());
			}
		} else {
			log.fine("Use default details initialization.");
			defaultDetailsInitialization();
		}
	}
	
	private Stream<Enforcer<T, C, N>> getConfiguredEnforcers(){
		return this.annotations()
		.filter(this::isEnabled)
		.map(a -> AnnotatedConfigurationFactory.resolve(this.getConfiguration(), a))
		.map(this::createEnforcer);		
	}

	@Override
	public final void build() {
		if(isEnabled() && isRepeaterEnabled() ) {
			if(hasElementConfigurations()) {
				doBuild();
			} else {
				String msg = "There are no element configurations for feature '" + this.getDisplayName() +"', there are no tests";
				if(getConfiguration().isFailEmptyFeature()) {
					throw new JLCConfigurationException(msg);
				} else {
					log.warning(msg);
				}
			}
		}
	}
	/**
	 * Builds the test structure, having the nodes created here.
	 */
	public abstract void doBuild();
	
	protected final boolean hasElementConfigurations() {
		return getElementConfigurations().count() > 0;
	}
	
	/**
	 * When there is no archetype enabled, it will return the defaults. When not. the
	 * archetyped configurations will be returned.
	 * 
	 * @return
	 */
	protected final Stream<N> getElementConfigurations() {
		Stream<N> result;
		
		if (this.isFiltered()) {
			result = extensionConfiguration.configurations(this.testConfigType, getFilter());
		} else {
			result = extensionConfiguration.configurations(this.testConfigType);
		}
		
		return order.apply(result);
	}
//	/**
//	 * Creates a Test Configuration based on the jlc configuration and the corresponding configured annotation.
//	 * the default implementation uses a spi.
//	 * @param configuration
//	 * @param annotation
//	 * @return
//	 */
//	protected N createConfigurationDEWL(JLCConfiguration<T> configuration, A annotation) {
//		return AnnotatedConfigurationFactory.resolve(configuration, annotation);
//	}
	/**
	 * Checks if the extension has element annotations or not.
	 * @return true when the extension has child annotations.
	 */
	protected final boolean hasConfiguredAnnotations() {
		return this.getAnnotations().length != 0;
	}

	protected final Stream<A> annotations() {
		return Arrays.stream(getAnnotations());
	}

	/**
	 * When there are no detail annotations (child annotations), this will be invoked to handle your
	 * default children.
	 * 

	 * @return the stream of child configurations.
	 */
	protected void defaultDetailsInitialization() {
		getConfiguratables()
			.map(this::createEnforcer)
			.forEach(e->e.enforce());
	}
	
	protected abstract <E> Stream<E> getConfiguratables();
	

	/**
	 * When the initialization has been completed for the annotation, this method is invoked.
	 * 
	 */
	protected void init() {
	}

	@Override
	public void applyFilter(Archetype archetype) {
		super.applyFilter(archetype);
		this.clear();
		init();
	}

	/**
	 * Always called
	 * 
	 * @param annotations
	 * @param configuration
	 */

	private final Optional<R> getRepeatingValue() {
		R repeatingAnnotation = null;
		repeatingAnnotation = getTestInstance().getClass().getAnnotation(repeaterType);
		return Optional.ofNullable(repeatingAnnotation);
	}

	/**
	 * @return the annotationType
	 */
	protected final Class<A> getAnnotationType() {
		return annotationType;
	}

	@Override
	public final DisplayName getDisplayName() {
		DisplayName displayName;
		Optional<DisplayName> configuredDisplayName = configuredDisplayName();
		if (configuredDisplayName.isPresent()) {
			displayName = configuredDisplayName.get();
		} else {
			displayName = getDefaultDisplayName();
		}
		return displayName;
	}

	/**
	 * The default display name when there is no display name configured
	 * 
	 * @return
	 */
	protected abstract DisplayName getDefaultDisplayName();

	protected <T extends Annotation> boolean isEnabled(T annotation) {
		boolean isEnabled;
		Optional<Boolean> r = AnnotationTool.getValue(annotation, ATTRIBUTE_ENABLED);
		if (r.isPresent()) {
			isEnabled = r.get();
		} else {
			isEnabled = false;
		}
		return isEnabled;
	}
	/**
	 * Checks if the repeater annotation is enabled. When there is no repeater it is
	 * default enabled.
	 * 
	 * @return
	 */
	protected boolean isRepeaterEnabled() {
		boolean isRepeaterEnabled;

		Optional<R> repeatingValue = getRepeatingValue();
		if (repeatingValue.isPresent()) {
			Optional<Object> configuredTestAnnotationValue = getConfiguredTestAnnotationValue(this.repeaterType,
					ATTRIBUTE_ENABLED);
			isRepeaterEnabled = (boolean) configuredTestAnnotationValue.get();
		} else {
			isRepeaterEnabled = true;
		}
		return isRepeaterEnabled;
	}

	private Optional<Object> getConfiguredTestAnnotationValue(Class<R> annotationClass, String methodName) {
		return ConfigurationTool.getConfiguredTestAnnotationValue(getTestInstance().getClass(), annotationClass, methodName);
	}



	private boolean mergeConfiguration() {
		boolean mergeConfiguration = getConfiguration().isMerge();
		if(!mergeConfiguration) {
			Optional<R> declaredRepeater = getRepeatingValue();
			if (declaredRepeater.isPresent()) {
				Optional<Object> optionalDisplayName = getConfiguredTestAnnotationValue(repeaterType,
						ATTRIBUTE_MERGE);
				if (optionalDisplayName.isPresent()) {
					mergeConfiguration = (boolean) optionalDisplayName.get();
				}
			}
		}
		return mergeConfiguration;
	}
	private Optional<DisplayName> configuredDisplayName() {
		DisplayName configuredDisplayName = null;
		Optional<R> declaredRepeater = getRepeatingValue();
		if (declaredRepeater.isPresent()) {
			Optional<Object> optionalDisplayName = getConfiguredTestAnnotationValue(repeaterType,
					ATTRIBUTE_DISPLAY_NAME);
			if (optionalDisplayName.isPresent()) {
				org.junit.jupiter.api.DisplayName dn = (org.junit.jupiter.api.DisplayName) optionalDisplayName.get();
				String configuredDisplayNameValue = dn.value();
				if (!JLC.EMPTY.equals(configuredDisplayNameValue)) {
					configuredDisplayName = new BasicDisplayName(configuredDisplayNameValue);
				}
			}
		}
		return Optional.ofNullable(configuredDisplayName);
	}
	private final A[] getAnnotations() {
		return getTestInstance().getClass().getAnnotationsByType(getAnnotationType());
	}

//	@Override // FIXME uri gaat fout
//	public final Optional<URI> toUri() {
//		URI uri = null;
//		StringBuilder builder = new StringBuilder();
//
//		String uriValue = builder.append("classpath:").append(Serializable.class.getName().replace('.', '/'))
//				.append(".class")
//				// .append(toString(false))
//				.toString();
//		URI result = null;
//		// ClassSource cs = ClassSource.from(Serializable.class);
//		// System.out.println(cs);
//		// System.out.println(uriValue);
//		try {
//			result = new URI("classpath:/properties.txt");
//			// System.out.println(result);
//			//FIXME Au...
//		//	ClasspathResourceSource c = ClasspathResourceSource.from("classpath:/properties.txt");
//			// ClasspathResourceSource s = ClasspathResourceSource .from(result);
//			// System.out.println(c);
//		} catch (URISyntaxException e) {
//			throw new AssertionError(e);
//		}
//		return Optional.ofNullable(uri);
//	}
	@Override
	public void peek() {
	//	System.out.println("PEEK ");
		//System.out.println(registry);
		
	}

   @Override
   public int getWeight() {
      // TODO Auto-generated method stub
      return 0;
   }

   @Override
   public boolean isEnabled() {
      return this.getFeatureSettings().isEnabled();
   }

   @Override
   public boolean isArchetyped() {
      return false;
   }
	
	
}
