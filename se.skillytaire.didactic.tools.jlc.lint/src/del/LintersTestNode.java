package se.skillytaire.didactic.tools.junit.core.lint.internal;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import se.skillytaire.didactic.tools.junit.core.api.JLC;
import se.skillytaire.didactic.tools.junit.core.internal.config.JLCConfiguration;
import se.skillytaire.didactic.tools.junit.core.internal.config.JLCNodeStructureConfiguration;
import se.skillytaire.didactic.tools.junit.core.internal.ui.DisplayName;
import se.skillytaire.didactic.tools.junit.core.lint.api.Lint;
import se.skillytaire.didactic.tools.junit.core.lint.api.Linters;
import se.skillytaire.didactic.tools.junit.core.lint.internal.config.LintersConfiguration;
import se.skillytaire.didactic.tools.junit.core.lint.internal.spi.LintersTestNodeFactory;
import se.skillytaire.didactic.tools.junit.core.spi.AbstractJLCCompositeTestNode;
import se.skillytaire.didactic.tools.junit.core.spi.FeatureTestNodeFactory;
import se.skillytaire.didactic.tools.junit.core.spi.JLCFeatereTestNode;
import se.skillytaire.didactic.tools.junit.core.spi.JLCTestNode;
@Deprecated
public class LintersTestNode<T> extends AbstractJLCCompositeTestNode<T> implements JLCFeatereTestNode<T>{

	public static final String LINT_CONFIG = LintersTestNode.class.getName();
	
	
	
	private LintersConfiguration lintersConfiguration;
	@Override
	public void init(JLCConfiguration<T> configuration) {
		super.init(configuration);
		Class<?> testClass = configuration.getTestInstance().getClass();
		Stream<FeatureTestNodeFactory<T>> factoryStream = FeatureTestNodeFactory.factories( f -> f.getClass() == LintersTestNodeFactory.class );
		List<FeatureTestNodeFactory<T>> indexedFactories = factoryStream.collect(Collectors.toList());
		//wat dan nu nodig?
		//Filtering toe passen op deze nodes.
		
		
		
		
	//	JLCNodeStructureConfiguration<T> jlcNodeStructure = configuration.getNodeStructure();
		if(testClass.isAnnotationPresent(Linters.class)) {
			Linters linters = testClass.getAnnotation(Linters.class);
			lintersConfiguration = new LintersConfiguration(jlcNodeStructure, linters);
		} else {
			lintersConfiguration = new LintersConfiguration(jlcNodeStructure);
		}
		Lint[] lintjes = testClass.getAnnotationsByType(Lint.class);
		lintersConfiguration.apply(lintjes);
		configuration.registerExtensionPointConfiguration(LINT_CONFIG, lintersConfiguration);
		//spi 
	}

	@Override
	public DisplayName getDisplayName() {
		return lintersConfiguration.getParent();
	}

	@Override
	public int getWeight() {
		return 10;
	}




}
