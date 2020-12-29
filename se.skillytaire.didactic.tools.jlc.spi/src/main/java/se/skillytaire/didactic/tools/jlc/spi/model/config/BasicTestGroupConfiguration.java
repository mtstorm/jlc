package se.skillytaire.didactic.tools.jlc.spi.model.config;

import se.skillytaire.didactic.tools.jlc.api.TestGroup;

public final class BasicTestGroupConfiguration extends AbstractTestGroupConfiguration{

	public BasicTestGroupConfiguration (TestGroup... grouping){
		if(grouping == null) {
			throw new IllegalArgumentException("No grouping");
		}
		for (TestGroup group : grouping) {
			switch (group) {
			case Overload:
				setGroupOverload(true);
				break;			
			case Api:
				setGroupApi(true);
				break;
			case Feature:
				setGroupFeature(true);
				break;
			case Archetype:
				setGroupArchetype(true);
				break;		
			case All:
				setGroupApi(true);
				setGroupFeature(true);
				setGroupArchetype(true);
				setGroupOverload(true);
				break;
			case None:
				break;				
			default:
				throw new IllegalArgumentException("Unhandled option");
			}
		}
		
	}


}
