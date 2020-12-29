//package se.skillytaire.didactic.tools.jlc.method.spi.model.config;
//
//import se.skillytaire.didactic.tools.jlc.api.TestGroup;
//import se.skillytaire.didactic.tools.jlc.spi.model.config.AbstractTestGroupConfiguration;
//
//public final class TestMethodGroupConfiguration extends AbstractTestGroupConfiguration{
//	private boolean groupOverload;
//	private boolean groupApi;
//	public TestMethodGroupConfiguration (TestGroup... grouping){
//		for (TestGroup group : grouping) {
//			switch (group) {
//			//TODO Override???
//			case Overload:
//				groupOverload = true;
//				break;
//			case Api:
//				groupApi = true;
//			case Feature:
//				setGroupFeature(true);
//				break;
//			case Archetype:
//				setGroupArchetype(true);
//				break;		
//			case All:
//				groupOverload = true;
//				groupApi = true;
//				setGroupFeature(true);
//				setGroupArchetype(true);
//				break;
//			default:
//				throw new IllegalArgumentException("Unhandled option");
//			}
//		}
//	}
//	
//	public boolean groupOverload() {
//		return this.groupOverload;
//	}
//	
//	public boolean groupApi() {
//		return this.groupApi;
//	}
//
//	@Override
//	public boolean isEnabled() {
//		return super.isEnabled() || this.groupApi() || this.groupOverload();
//	}
//}
