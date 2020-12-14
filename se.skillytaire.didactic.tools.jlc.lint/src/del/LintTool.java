package se.skillytaire.didactic.tools.junit.core.lint.internal.config;

import se.skillytaire.didactic.tools.junit.core.api.JLC;
import se.skillytaire.didactic.tools.junit.core.internal.ui.BasicDisplayName;
import se.skillytaire.didactic.tools.junit.core.internal.ui.ConstructorsDisplayName;
import se.skillytaire.didactic.tools.junit.core.internal.ui.DisplayName;
import se.skillytaire.didactic.tools.junit.core.internal.ui.FieldsDisplayName;
import se.skillytaire.didactic.tools.junit.core.internal.ui.MethodsDisplayName;
import se.skillytaire.didactic.tools.junit.core.internal.ui.TypesDisplayName;
import se.skillytaire.didactic.tools.junit.core.lint.api.Lint;

public class LintTool {
	private LintTool() {throw new AssertionError("no instances for you");}

	public static DisplayName name(Lint lint) {
		DisplayName name;
		String archetypeName = lint.archetype();
		String configuredName = lint.name().value();
		if(JLC.EMPTY.equals(configuredName)) {
			name = new BasicDisplayName(archetypeName);
		} else {
			name = new BasicDisplayName(configuredName);
		}
		return name;
	}
	
	public static DisplayName types(Lint lint) {
		DisplayName types;
		String typesName = lint.types().value();
		if(JLC.EMPTY.equals(typesName)) {
			types = new TypesDisplayName();
		} else {
			types = new BasicDisplayName(typesName);
		}
		return types;
	}
	public static DisplayName fields(Lint lint) {
		DisplayName fields;
		String fieldsName = lint.fields().value();
		if(JLC.EMPTY.equals(fieldsName)) {
			fields = new FieldsDisplayName();
		} else {
			fields = new BasicDisplayName(fieldsName);
		}
		return fields;
	}
	public static DisplayName constructors(Lint lint) {
		DisplayName constructors;
		String constructorsName = lint.constructors().value();
		if(JLC.EMPTY.equals(constructorsName)) {
			constructors = new ConstructorsDisplayName();
		} else {
			constructors = new BasicDisplayName(constructorsName);
		}
		return constructors;
	}
	
	public static DisplayName methods(Lint lint) {
		DisplayName methods;
		String methodsName = lint.methods().value();
		if(JLC.EMPTY.equals(methodsName)) {
			methods = new MethodsDisplayName();
		} else {
			methods = new BasicDisplayName(methodsName);
		}
		return methods;
	}
}
