package se.skillytaire.didactic.tools.jlc.spi.model.config;
//package se.skillytaire.didactic.tools.junit.core.internal.config;
//
//import se.skillytaire.didactic.tools.junit.core.api.JLC;
//import se.skillytaire.didactic.tools.junit.core.internal.ui.BasicDisplayName;
//import se.skillytaire.didactic.tools.junit.core.internal.ui.ConstructorsDisplayName;
//import se.skillytaire.didactic.tools.junit.core.internal.ui.DisplayName;
//import se.skillytaire.didactic.tools.junit.core.internal.ui.FieldsDisplayName;
//import se.skillytaire.didactic.tools.junit.core.internal.ui.MethodsDisplayName;
//import se.skillytaire.didactic.tools.junit.core.internal.ui.TypesDisplayName;
//
//public class JLCTool {
//	private JLCTool() {throw new AssertionError("no instances for you");}
//
//	
//	public static DisplayName types(JLC jlc) {
//		DisplayName types;
//		String typesName = jlc.types().value();
//		if(JLC.EMPTY.equals(typesName)) {
//			types = new TypesDisplayName();
//		} else {
//			types = new BasicDisplayName(typesName);
//		}
//		return types;
//	}
//	public static DisplayName fields(JLC jlc) {
//		DisplayName fields;
//		String fieldsName = jlc.fields().value();
//		if(JLC.EMPTY.equals(fieldsName)) {
//			fields = new FieldsDisplayName();
//		} else {
//			fields = new BasicDisplayName(fieldsName);
//		}
//		return fields;
//	}
//	public static DisplayName constructors(JLC jlc) {
//		DisplayName constructors;
//		String constructorsName = jlc.constructors().value();
//		if(JLC.EMPTY.equals(constructorsName)) {
//			constructors = new ConstructorsDisplayName();
//		} else {
//			constructors = new BasicDisplayName(constructorsName);
//		}
//		return constructors;
//	}
//	
//	public static DisplayName methods(JLC jlc) {
//		DisplayName methods;
//		String methodsName = jlc.methods().value();
//		if(JLC.EMPTY.equals(methodsName)) {
//			methods = new MethodsDisplayName();
//		} else {
//			methods = new BasicDisplayName(methodsName);
//		}
//		return methods;
//	}
//}
