//package se.skillytaire.didactic.tools.jlc.spi.internal;
//
//import static org.junit.jupiter.api.Assertions.assertFalse;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//
//import java.net.URI;
//import java.util.Optional;
//
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//
//import se.skillytaire.didactic.tools.jlc.api.JLC;
//import se.skillytaire.didactic.tools.jlc.api.JLCConfiguration;
//import se.skillytaire.didactic.tools.jlc.api.TestConfiguration;
//import se.skillytaire.didactic.tools.jlc.api.TestConfigurationRegistry;
//import se.skillytaire.didactic.tools.jlc.spi.model.config.AbstractTestConfiguration;
//
//public class JLCConfigurationImplTest {
//	@Test
//	void testJLCAnnotationMissing() {
//		
//		  Assertions.assertThrows(IllegalStateException.class, () -> {
//			  new JLCConfigurationImpl<>(Integer.class);
//			  });
//	}
//	@Test
//	void testIsRegistered_NothingRegistered() {
//		JLCType jlcType = new JLCType();
//		JLCConfigurationImpl<String> config = new JLCConfigurationImpl<>(jlcType);
//		assertFalse(config.isRegistered(TestConfigurationImpl.class));
//	}
//	@Test
//	void testFind_NothingRegistered() {
//		JLCType jlcType = new JLCType();
//		JLCConfigurationImpl<String> config = new JLCConfigurationImpl<>(jlcType);
//		Optional<TestConfigurationRegistry<String, TestConfigurationImpl<String>>> or = config.find(TestConfigurationImpl.class);
//		assertFalse(or.isPresent());
//	}
//	@Test
//	void testIsRegistered_Registered() {
//		JLCType jlcType = new JLCType();
//		JLCConfigurationImpl<String> config = new JLCConfigurationImpl<>(jlcType);
//		config.init(TestConfigurationImpl.class);
//		assertTrue(config.isRegistered(TestConfigurationImpl.class));
//	}
//	@Test
//	void testFind_Registered() {
//		JLCType jlcType = new JLCType();
//		JLCConfigurationImpl<String> config = new JLCConfigurationImpl<>(jlcType);
//		config.init(TestConfigurationImpl.class);
//		Optional<TestConfigurationRegistry<String, TestConfigurationImpl<String>>> or = config.find(TestConfigurationImpl.class);
//		assertTrue(or.isPresent());
//	}	
//	
//	@Test
//	void testFind_NotRegistered() {
//		JLCType jlcType = new JLCType();
//		JLCConfigurationImpl<String> config = new JLCConfigurationImpl<>(jlcType);
//		config.init(TestConfigurationImpl.class);
//		Optional<TestConfigurationRegistry<String, TestConfigurationImpl<String>>> or = config.find(TestConfigurationImpl2.class);
//		assertFalse(or.isPresent());
//	}		
//	@JLC
//	private static class JLCType {
//		
//	}
//	private static class TestConfigurationImpl2<T> extends AbstractTestConfiguration<TestConfigurationImpl<T>,T> implements TestConfiguration<TestConfigurationImpl<T>,T> {
//
//		protected TestConfigurationImpl2(JLCConfiguration<T> parent) {
//			super(parent);
//		}
//
//		@Override
//		public String getName() {
//			// TODO Auto-generated method stub
//			return null;
//		}
//
//		@Override
//		public Optional<URI> toUri() {
//			// TODO Auto-generated method stub
//			return null;
//		}
//
//		@Override
//		public int compareTo(TestConfigurationImpl<T> o) {
//			// TODO Auto-generated method stub
//			return 0;
//		}
//		
//	}
//	private static class TestConfigurationImpl<T> extends AbstractTestConfiguration<TestConfigurationImpl<T>,T> implements TestConfiguration<TestConfigurationImpl<T>,T> {
//
//		protected TestConfigurationImpl(JLCConfiguration<T> parent) {
//			super(parent);
//		}
//
//		@Override
//		public String getName() {
//			// TODO Auto-generated method stub
//			return null;
//		}
//
//		@Override
//		public Optional<URI> toUri() {
//			// TODO Auto-generated method stub
//			return null;
//		}
//
//		@Override
//		public int compareTo(TestConfigurationImpl<T> o) {
//			// TODO Auto-generated method stub
//			return 0;
//		}
//		
//	}
//}
