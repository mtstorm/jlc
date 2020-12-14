package se.skillytaire.didactic.tools.jlc.spi.model.config;

import se.skillytaire.didactic.tools.jlc.api.TestGroup;

public final class BasicTestGroupConfiguration extends AbstractTestGroupConfiguration{

	public BasicTestGroupConfiguration (TestGroup... grouping){
		if(grouping == null) {
			throw new IllegalArgumentException("No grouping");
		}
		for (TestGroup group : grouping) {
			switch (group) {
			case Feature:
				setGroupFeature(true);
				break;
			case Archetype:
				setGroupArchetype(true);
				break;		
			case ALL:
				setGroupFeature(true);
				setGroupArchetype(true);
				break;
			default:
				throw new IllegalArgumentException("Unhandled option");
			}
		}
		
	}


}
