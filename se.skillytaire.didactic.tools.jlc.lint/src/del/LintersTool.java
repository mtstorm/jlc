package se.skillytaire.didactic.tools.junit.core.lint.internal.config;

import se.skillytaire.didactic.tools.junit.core.api.JLC;
import se.skillytaire.didactic.tools.junit.core.internal.ui.BasicDisplayName;
import se.skillytaire.didactic.tools.junit.core.internal.ui.ConstructorsDisplayName;
import se.skillytaire.didactic.tools.junit.core.internal.ui.DisplayName;
import se.skillytaire.didactic.tools.junit.core.internal.ui.FieldsDisplayName;
import se.skillytaire.didactic.tools.junit.core.internal.ui.MethodsDisplayName;
import se.skillytaire.didactic.tools.junit.core.internal.ui.TypesDisplayName;
import se.skillytaire.didactic.tools.junit.core.lint.api.Lint;
import se.skillytaire.didactic.tools.junit.core.lint.api.Linters;

public class LintersTool {
	private LintersTool() {throw new AssertionError("no instances for you");}

	public static DisplayName name(Linters linters) {
		DisplayName name;

		String configuredName = linters.name().value();
		if(JLC.EMPTY.equals(configuredName)) {
			name = new LintersDisplayName();
		} else {
			name = new BasicDisplayName(configuredName);
		}
		return name;
	}

}
