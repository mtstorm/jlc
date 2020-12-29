package se.skillytaire.didactic.tools.jlc.method.spi.model.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream.Builder;

import se.skillytaire.didactic.tools.jlc.api.JLCConfiguration;
import se.skillytaire.didactic.tools.jlc.spi.model.config.BasicTestGroupConfiguration;
import se.skillytaire.didactic.tools.jlc.spi.model.config.TestConfigurationNodeBuilder;
import se.skillytaire.didactic.tools.jlc.spi.model.structure.FolderTestNode;
import se.skillytaire.didactic.tools.jlc.spi.model.structure.JLCTestNode;
//FIXME BasicTestGroupConfiguration is generic now, remove param
public class TestMethodConfigurationNodeBuilder<T> extends TestConfigurationNodeBuilder<TestMethodConfiguration<T>,T,BasicTestGroupConfiguration>{
	public TestMethodConfigurationNodeBuilder(BasicTestGroupConfiguration settings) {
		super(settings);
	}

	private HashMap<String, List<TestMethodConfiguration<T>>> overloaded = new HashMap<>();
	private List<TestMethodConfiguration<T>> apis = new ArrayList<TestMethodConfiguration<T>>();

	
	/**
	 * 
	 */
	@Override
	public void add(TestMethodConfiguration<T> config) {
		boolean isOverloadedConfig = false;
		if(getSettings().groupOverload()) {
			String methodName = config.getSignature().getName();
			if(overloaded.containsKey(methodName)) {
				List<TestMethodConfiguration<T>> other = overloaded.get(methodName);
				other.add(config);
				isOverloadedConfig = true;
			} else {
				List<TestMethodConfiguration<T>> result = forGrouping().filter(c-> c.isOverloaded(config) ).collect(Collectors.toList());
				if(!result.isEmpty()) {
					ArrayList<TestMethodConfiguration<T>> over = new ArrayList<TestMethodConfiguration<T>>(result);
					over.add(config);
					overloaded.put(methodName, over);
					this.uncollectForGrouping(result);
					isOverloadedConfig = true;
				}
			}
		} 
		if(!isOverloadedConfig) {
			this.collectForGrouping(config);
		}
	}
	
	@Override
	protected boolean group(TestMethodConfiguration<T> config) {
		boolean grouped = true;
		if(!addAPI(config)) {
			grouped = super.group(config);
		}
		return grouped;
	}
	private boolean addAPI(TestMethodConfiguration<T> config) {
		boolean processed = false;
		if(getSettings().groupApi() && config.getSignature().isApi()) {
			apis.add(config);
			processed = true;
		}
		return processed;
	}
	

	
	
	@Override
	protected void build(JLCConfiguration<T> rootConfiguration,
			Function<TestMethodConfiguration<T>, JLCTestNode<T>> mapper, Builder<JLCTestNode<T>> streamBuilder) {
		if(!overloaded.isEmpty()) {
			Set<Entry<String, List<TestMethodConfiguration<T>>>> entries = overloaded.entrySet();
			for (Entry<String, List<TestMethodConfiguration<T>>> entry : entries) {

				List<TestMethodConfiguration<T>> configs = entry.getValue();
				
				FolderTestNode<T> folder = new FolderTestNode<T>(entry.getKey()+" ("+configs.size()+")");
				folder.init(rootConfiguration);
				streamBuilder.add(folder);		
				configs.stream()
				       .map(mapper)
				       .peek(n->n.init(rootConfiguration))
				       .forEach(folder::add);
			}
		}
		if(!apis.isEmpty()) {
			FolderTestNode<T> folder = new FolderTestNode<T>("Api ("+apis.size()+")");
			folder.init(rootConfiguration);
			streamBuilder.add(folder);		
			apis.stream()
			       .map(mapper)
			       .peek(n->n.init(rootConfiguration))
			       .forEach(folder::add);				
		}
		super.build(rootConfiguration, mapper, streamBuilder);
	}


}
