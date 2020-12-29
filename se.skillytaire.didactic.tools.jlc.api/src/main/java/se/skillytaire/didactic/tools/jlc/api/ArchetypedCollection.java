package se.skillytaire.didactic.tools.jlc.api;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;
import java.util.stream.Stream;
import java.util.stream.Stream.Builder;


public class ArchetypedCollection<T, N extends TestConfiguration<N,T>> {
	private List<N> defaults = new ArrayList<>();

	private Map<Archetype, List<N>> archetypedElements = new TreeMap<Archetype, List<N>>();
	/**
	 * Add a default element.
	 * @param element to add as default
	 * @return true when the default underlying collection has been changed.
	 */
	public boolean add(N element) {
		return defaults.add(element);
	}
	/**
	 * A check if there are default elements.
	 * @return true if there are default elements.
	 */
	public boolean hasDefaults() {
		return !defaults.isEmpty();
	}
	/**
	 * Remove an element from the defaults
	 * @param element to remove as default
	 * @return true when the default underlying collection has been changed.
	 */
	public boolean remove(N element) {
		return defaults.remove(element);
	}
	/**
	 * Checks if the element is default.
	 * @param element to check.
	 * @return true if the element is default.
	 */
	public boolean isDefault(N element) {
		return defaults.contains(element);
	}
	public Stream<N> defaults() {
		return defaults.stream();
	}

	
	public Stream<N> archetypes(Archetype archetype) {
		return getCollection(archetype).stream();
	}
	/**
	 * Add an element to this, when the element already exists as default it, will be removed as default.
	 * @param element to add for the archetype
	 * @param archetype the archetype
	 * @return true when the default underlying collection has been changed.
	 */
	public boolean add(N element, Archetype archetype) {
		boolean add = false;
		//alles in default is eigenlijk autodetected.
		Optional<N> defaultConfig = this.defaults.stream().filter( e->e.equals(element)).findFirst();
		N workingElement;
		if(defaultConfig.isPresent()) {
			workingElement = defaultConfig.get();
			this.remove(workingElement);
		} else {
			workingElement = element;
		}
		workingElement.enforce(archetype);
		List<N> collection = getCollection(archetype);
		add = collection.add(workingElement);
		return add;
	}
	/**
	 * Retrieves the collection, when it does not exist it will be created.
	 * @param archetype
	 * @return
	 */
	private List<N> getCollection(Archetype archetype){
		List<N> archetypeCollection;
		if(archetypedElements.containsKey(archetype)) {
			archetypeCollection = archetypedElements.get(archetype);
		} else {
			archetypeCollection = new ArrayList<>();
			archetypedElements.put(archetype, archetypeCollection);
		}
		return archetypeCollection;
	}

	public void enforce(N config, Archetype archetype) {
		config.enforce(archetype);
		this.add(config, archetype);
	}
	
	public Stream<N> stream(){
		Builder<N> result = Stream.builder();
		defaults.stream().forEach(result::add);

		this.archetypedElements.forEach(
				(k,v) ->{
					v.stream().forEach(result::add);
				}
				);
		return result.build();
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("\n\tdefaults: \n");
		if(hasDefaults()) {
			defaults.forEach( e-> {
				builder.append("\t\t");
				builder.append(e.toString()).append("\n");
			}
			);
		}else {
			builder.append("\t\t /EMPTY/ \n");
		}
		
		this.archetypedElements.forEach( 
				(k,v) -> {
				 builder.append("\t").append(k.getName()).append(": \n");
				 v.stream().forEach(e-> builder.append("\t\t").append(e.toString()).append("\n"));
				}
				
				);
		return builder.toString();
	}
	public Optional<N> resolve(N n) {
		return stream().filter( e-> e.equals(n)).findFirst();
	}
}
