package se.skillytaire.didactic.tools.jlc.spi.model.config;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Stream;
import java.util.stream.Stream.Builder;

import se.skillytaire.didactic.tools.jlc.spi.model.Archetype;
import se.skillytaire.didactic.tools.jlc.spi.model.structure.FolderTestNode;
import se.skillytaire.didactic.tools.jlc.spi.model.structure.JLCTestNode;

/**
 * Basic filter for test configurations.
 * 
 * @author Skillytaire AB
 *
 * @param <N>
 * @param <T>
 */
public class TestConfigurationNodeBuilder<N extends TestConfiguration<N, T>, T, S extends TestGroupConfiguration> {
	private final HashMap<String, List<N>> features = new HashMap<>();
	private final HashMap<Archetype, List<N>> archetypes = new HashMap<>();
	/**
	 * The container collecting the elements for ordering.
	 */
	private final List<N> collector = new ArrayList<>();
	/**
	 * The container for grouped elements.
	 */
	private final List<N> remaining = new ArrayList<>();

	private final S settings;




	public TestConfigurationNodeBuilder(S settings) {
		if(settings == null) {
			throw new IllegalArgumentException("settings is void");
		}
		this.settings = settings;
	}
	public S getSettings() {
		return settings;
	}
	/**
	 * Add the element for grouping.
	 * 
	 * @param config
	 */
	public void add(N config) {
		collectForGrouping(config);
	}

	/**
	 * Add the element to the collector.
	 * 
	 * @param config
	 */
	protected void collectForGrouping(N config) {
		this.collector.add(config);
	}

	/**
	 * Remove the configurations from the collector.
	 * 
	 * @param configs
	 */
	protected void uncollectForGrouping(Collection<N> configs) {
		this.collector.removeAll(configs);
	}

	/**
	 * Get the configurations that still needs to be grouped.
	 * 
	 * @return
	 */
	protected Stream<N> forGrouping() {
		return collector.stream();
	}

	/**
	 * Add the element to the remaining collector. The configuration will not be
	 * grouped.
	 * 
	 * @param config
	 */
	protected void collectRemaining(N config) {
		this.remaining.add(config);
	}

	/**
	 * Tries to apply the configuration to features and archetypes. when not
	 * successfully applied, it will return false, otherwise true.
	 * 
	 * @param config
	 * @return
	 */
	protected boolean group(N config) {
		boolean grouped = true;
		if (!addFeature(config)) {
			if (!addArchetype(config)) {
				// this.remaining.add(config);
				grouped = false;
			}
		}
		return grouped;
	}

	/**
	 * Analizes the element and groups it.
	 * @param config
	 */
	public final void analize(N config) {
		if(!group(config)) {
			this.remaining.add(config);
		}
	}


	private boolean addArchetype(N config) {
		boolean processed = false;
		if (settings.groupArchetype() && config.hasArchetype()) {
			Archetype archetype = config.getArchetype();
			List<N> archetypeList;
			if (archetypes.containsKey(archetype)) {
				archetypeList = features.get(archetype);
			} else {
				archetypeList = new ArrayList<N>();
				archetypes.put(archetype, archetypeList);
			}
			archetypeList.add(config);
			processed = true;
		}
		return processed;
	}

	private boolean addFeature(N config) {
		boolean processed = false;
		if (settings.groupFeature() && config.hasFeature()) {
			String feature = config.getFeature();
			List<N> featureList;
			if (features.containsKey(feature)) {
				featureList = features.get(feature);
			} else {
				featureList = new ArrayList<>();
				features.put(feature, featureList);
			}
			featureList.add(config);
			processed = true;
		}
		return processed;
	}




	// <R> Stream<R> map(Function<? super T, ? extends R> mapper);

	public final Stream<JLCTestNode<T>> build(JLCConfiguration<T> rootConfiguration,
			Function<N, JLCTestNode<T>> mapper) {
		this.collector.forEach(this::analize);
		Builder<JLCTestNode<T>> streamBuilder = Stream.builder();
		build(rootConfiguration, mapper, streamBuilder);

		return streamBuilder.build();
	}

	/**
	 * Constructs the hierarchy and applies it to the stream builder. When
	 * overriding this method, the developers should first do there stuff and then
	 * call super.
	 * 
	 * @param rootConfiguration
	 * @param mapper
	 * @param streamBuilder
	 */
	protected void build(JLCConfiguration<T> rootConfiguration, Function<N, JLCTestNode<T>> mapper,
			Builder<JLCTestNode<T>> streamBuilder) {
		if (!archetypes.isEmpty()) {
			Set<Entry<Archetype, List<N>>> entries = archetypes.entrySet();
			for (Entry<Archetype, List<N>> entry : entries) {
				List<N> configs = entry.getValue();
				FolderTestNode<T> folder = new FolderTestNode<T>(entry.getKey() + " (" + configs.size() + ")");
				folder.init(rootConfiguration);
				streamBuilder.add(folder);
				configs.stream().map(mapper).peek(n -> n.init(rootConfiguration)).forEach(folder::add);
			}
		}
		if (!features.isEmpty()) {
			Set<Entry<String, List<N>>> entries = features.entrySet();
			for (Entry<String, List<N>> entry : entries) {
				List<N> configs = entry.getValue();
				FolderTestNode<T> folder = new FolderTestNode<T>(entry.getKey() + " (" + configs.size() + ")");
				folder.init(rootConfiguration);
				streamBuilder.add(folder);
				configs.stream().map(mapper).peek(n -> n.init(rootConfiguration)).forEach(folder::add);
			}
		}
		this.remaining.stream().map(mapper).peek(n -> n.init(rootConfiguration)).forEach(streamBuilder::add);

	}

}
