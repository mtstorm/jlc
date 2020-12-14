package se.skillytaire.didactic.tools.jlc.spi.model.structure;

import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.annotation.Repeatable;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.TypeVariable;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import se.skillytaire.didactic.tools.jlc.spi.ext.feature.JLCFeatereTestNode;
import se.skillytaire.didactic.tools.jlc.spi.model.Archetype;
import se.skillytaire.didactic.tools.jlc.spi.model.config.ArchetypedCollection;
import se.skillytaire.didactic.tools.jlc.spi.model.config.ConfigurationTool;
import se.skillytaire.didactic.tools.jlc.spi.model.config.JLCConfiguration;
import se.skillytaire.didactic.tools.jlc.spi.model.config.TestConfiguration;
import se.skillytaire.didactic.tools.jlc.spi.model.config.TestOrderDescription;
import se.skillytaire.didactic.tools.jlc.spi.model.naming.BasicDisplayName;
import se.skillytaire.didactic.tools.jlc.spi.model.naming.DisplayName;

/**
 * Abstraction for repeating test extensions.
 * 
 * @author Skillytaire AB
 *
 * @param <A>
 * @param <T>
 * @param <N>
 */
public abstract class AbstractAnnotatedTestExtention<A extends Annotation, R extends Annotation, T, N extends TestConfiguration<N,T>>
		extends AbstractJLCCompositeTestNode<T>  implements JLCFeatereTestNode<T>{
	static final Logger log = Logger.getLogger(AbstractAnnotatedTestExtention.class.getName());
	
	private static final String ATTRIBUTE_DISPLAY_NAME = "displayName";
	private static final String ATTRIBUTE_ENABLED = "enabled";
	private static final String ATTRIBUTE_MERGE = "merge";
	private ArchetypedCollection<T, N> elementConfigurations;

	private final Class<A> annotationType;

	private final Class<R> repeaterType;

	private final Class<N> testConfigType;

	private TestOrderDescription order;
	
	
	
	@SuppressWarnings("unchecked")
	protected AbstractAnnotatedTestExtention() {
		this.annotationType = (Class<A>) ((ParameterizedType) getClass().getGenericSuperclass())
				.getActualTypeArguments()[0];
		ParameterizedType f = (ParameterizedType) ((ParameterizedType) getClass().getGenericSuperclass())
				.getActualTypeArguments()[3];

		TypeVariable<?> tv = (TypeVariable<?>) f.getActualTypeArguments()[0];

		testConfigType = (Class<N>) tv.getGenericDeclaration();
		System.out.println(tv.getGenericDeclaration());
//		testConfigType = (Class<N>) ((ParameterizedType) getClass().getGenericSuperclass())
//				.getActualTypeArguments()[3];
		if (this.annotationType.isAnnotationPresent(Repeatable.class)) {
			Repeatable repeatable = this.annotationType.getAnnotation(Repeatable.class);
			repeaterType = (Class<R>) repeatable.value();
		} else {
			repeaterType = null;
		}

	}

	/**
	 * Initializes the specific configuration needed to build the structure.
	 * When the structure is already there it will not reload the configuration.
	 */
	@Override
	public void init(JLCConfiguration<T> configuration) {
		super.init(configuration);
		order =configuration.getOrder().override(
				ConfigurationTool.getSort(configuration.getTestInstance().getClass(), repeaterType)
				);
		
		if (configuration.containsConfiguration(testConfigType)) {
			System.out.println("REUSE   "+ getClass().getName());
			this.elementConfigurations = configuration.getExtensionConfiguration(testConfigType);
		} else {
			this.elementConfigurations = new ArchetypedCollection<>();
			if (isRepeaterEnabled()) {

				configuration.register(elementConfigurations, testConfigType);
				Optional<R> optionalRepeater = getRepeatingValue();
				if (optionalRepeater.isPresent()) {
					// log.info(()-> "Repeater found ");
					R repeatable = optionalRepeater.get();
					init(repeatable, configuration);
				} else {
					// log.info(()-> "no repeater found ");
					defaultMasterInit(configuration);
				}

				if (hasConfiguredAnnotations()) {
					if(mergeConfiguration()) {
						log.info(()-> "merge configuration "+ this.getClass().getName());
						defaultDetailsInit(configuration);
						//zoek door alle configuraties heen 
						Stream<N> ano = this.annotations()
								.map(a -> this.createConfiguration(configuration, a));
						
						List<N> annotatedConfigurations = this.annotations().map(a -> this.createConfiguration(configuration, a)).collect(Collectors.toList());
	
						for (N n : annotatedConfigurations) {
							Optional<N> match = elementConfigurations.resolve(n);
							if(match.isPresent()) {
								N defaultInited = match.get();
								defaultInited.merge(n);
							} else {
								elementConfigurations.add(n);
							}
						}
						
						//this.elementConfigurations.stream().flatMap( (ano, c) -> c);
					
					}else {
						this.annotations()
						.filter(this::isEnabled)
						.map(a -> this.createConfiguration(configuration, a))
						.forEach(this::add);			
					}
				} else {
					defaultDetailsInit(configuration);
				}

				init();

			}
		}
		//log System.out.println(this.elementConfigurations);
	}

	public final boolean isEnabled() {
		boolean enabled = true;
		Optional<R> repeater = this.getRepeatingValue();
		if(repeater.isPresent()) {
			enabled = isEnabled(repeater.get());
		}
		return enabled;
	}
	public final void build() {
		if(isEnabled()) {
			doBuild();
		}
		
	}
	
	public abstract void doBuild();
//	protected abstract boolean isGroupingEnabled();

	/**
	 * Adds the configuration to this.
	 * 
	 * @param c
	 */
	protected final void add(N c) {
		if (c.hasArchetype()) {
			elementConfigurations.add(c, c.getArchetype());
		} else {
			addDefault(c);

		}

	}

	protected final void addDefault(N c) {
		elementConfigurations.add(c);
	}



	
//	protected Optional<Comparator<N>> getComperator(){
//		Comparator<N> comparator = null;
//		Optional<R> declaredRepeater = getRepeatingValue();
//		if (declaredRepeater.isPresent()) {
////			Optional<Object> optionalTestOrder = getConfiguredTestAnnotationValue(repeaterType,
////					ATTRIBUTE_TEST_ORDER);
////			if (optionalTestOrder.isPresent()) {
////				TestOrder testOrderConfiguration = (TestOrder) optionalTestOrder.get();
////				TestDisplayOrder sortType = testOrderConfiguration.sort();
////				boolean inverse = testOrderConfiguration.inverse();
////				switch (sortType) {
////				case Natural:
////					 comparator = (a,b) -> a.compareTo(b);
////					 if(inverse) {
////						 comparator = comparator.reversed();
////					 }
////					break;
////
////				default:
////					break;
////				}
////			} else {
////				throw new IllegalStateException("No order attribute found on repeater type "+ this.repeaterType.getName());
////			}
//		} 
//		return Optional.ofNullable(comparator);
//	}
	
	/**
	 * When there is no archetype enabled, it will return the defaults. When not the
	 * archetyped configurations will be returned.
	 * 
	 * @return
	 */
	protected Stream<N> getElementConfigurations() {
		ensureInitialized();
		Stream<N> result;
//		String msg = String.format("Class %s using archetype %s is %b", getClass().getName(), getFilter(),
//				isFiltered());
//		System.out.println(msg);
		if (this.isFiltered()) {
			result = this.elementConfigurations.archetypes(getFilter());
		} else {
			result = this.elementConfigurations.defaults();
		}
		
		return order.apply(result);
	}

	private void ensureInitialized() {
		if (this.elementConfigurations == null) {
			throw new IllegalArgumentException("Extension point has not been initialized");
		}
	}

	protected Stream<N> getAllElementConfigurations() {
		ensureInitialized();
		return this.elementConfigurations.stream();
	}

	protected abstract N createConfiguration(JLCConfiguration<T> configuration, A annotation);

	protected final boolean hasConfiguredAnnotations() {
		return this.getAnnotations().length != 0;
	}

	protected final Stream<A> annotations() {
		return Arrays.stream(getAnnotations());
	}

	/**
	 * When there is no repeater, this will be invoked to handle your default
	 * configuration.
	 * 
	 * @param configuration
	 */
	protected void defaultMasterInit(JLCConfiguration<T> configuration) {
	}

	/**
	 * When there are no detail annotations, this will be invoked to handle your
	 * default children.
	 * 
	 * @param configuration
	 */
	protected void defaultDetailsInit(JLCConfiguration<T> configuration) {
	}

	/**
	 * When the configuration has been handled by the init method haven the config,
	 * this is the last method . 
	 * 
	 * @param configuration
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
	 * This method is called optional
	 * 
	 * @param repeater
	 * @param configuration
	 */
	protected abstract void init(R repeater, JLCConfiguration<T> configuration);

	/**
	 * Always called
	 * 
	 * @param annotations
	 * @param configuration
	 */
	// protected abstract void init(JLCConfiguration<T> configuration , A
	// annotations);

	private final Optional<R> getRepeatingValue() {

		R repeatingAnnotation = null;
		// if (hasRepeater()) {
		// Class<R> annotationClass = getRepeater();
		repeatingAnnotation = getTestInstance().getClass().getAnnotation(repeaterType);
		// }

		return Optional.ofNullable(repeatingAnnotation);
	}

//	/**
//	 * Get the repeating class if it exist for the given annotation <code>A</code>.
//	 * 
//	 * @return
//	 */
//	protected final Class<R> getRepeater() {
//		return repeaterType;
//	}

//	protected final boolean hasRepeater() {
//		return repeaterType != null;
//	}

	// protected final R getRepeater()

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

	protected <T extends Annotation>boolean isEnabled(T annotation) {
		boolean isEnabled;
		Optional<Boolean> r = getValue(annotation, ATTRIBUTE_ENABLED);
		if (r.isPresent()) {
			isEnabled = r.get();
		} else {
			isEnabled = false;
		}
		return isEnabled;
	}

	@SuppressWarnings("unchecked")
	private static <T> Optional<T> getValue(Annotation annotation, String methodName) {
		T result;
		Method method;
		try {
			method = annotation.annotationType().getDeclaredMethod(methodName);
			result = (T) method.invoke(annotation);
		} catch (Exception e) {
		   log.log(Level.SEVERE, String.format("Error getting the value of %s from annotation %s", methodName, annotation),e);
			result = null;
		}
		return Optional.ofNullable(result);
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
			// Class<? extends Annotation> annotationClass = getRepeater();
			Optional<Object> configuredTestAnnotationValue = getConfiguredTestAnnotationValue(this.repeaterType,
					ATTRIBUTE_ENABLED);
			// if(configuredTestAnnotationValue.isPresent()) {
			isRepeaterEnabled = (boolean) configuredTestAnnotationValue.get();
			// }
//			Annotation repeatingAnnotation = getTestInstance().getClass().getAnnotation(annotationClass);
//			if (repeatingAnnotation != null) {
//				Method method;
//				try {
//					method = repeatingAnnotation.annotationType().getDeclaredMethod(ATTRIBUTE_ENABLED);
//					isRepeaterEnabled = (Boolean) method.invoke(repeatingAnnotation);
//				} catch (Exception e) {
//					isRepeaterEnabled = false;
//				}
//			} 
//			else {
//				isRepeaterEnabled = false;
//			}
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
				if (!EMPTY.equals(configuredDisplayNameValue)) {
					configuredDisplayName = new BasicDisplayName(configuredDisplayNameValue);
				}
			}
		}
		return Optional.ofNullable(configuredDisplayName);
	}
	public static final String EMPTY = "";
	private final A[] getAnnotations() {
		return getTestInstance().getClass().getAnnotationsByType(getAnnotationType());
	}

	@Override // FIXME uri gaat fout
	public final Optional<URI> toUri() {
		URI uri = null;
		StringBuilder builder = new StringBuilder();

		String uriValue = builder.append("classpath:").append(Serializable.class.getName().replace('.', '/'))
				.append(".class")
				// .append(toString(false))
				.toString();
		URI result = null;
		// ClassSource cs = ClassSource.from(Serializable.class);
		// System.out.println(cs);
		// System.out.println(uriValue);
		try {
			result = new URI("classpath:/properties.txt");
			// System.out.println(result);
			//FIXME Au...
		//	ClasspathResourceSource c = ClasspathResourceSource.from("classpath:/properties.txt");
			// ClasspathResourceSource s = ClasspathResourceSource .from(result);
			// System.out.println(c);
		} catch (URISyntaxException e) {
			throw new AssertionError(e);
		}
		return Optional.ofNullable(uri);
	}
	@Override
	public void peek() {
		System.out.println("PEEK ");
		System.out.println(elementConfigurations);
		
	}
}
