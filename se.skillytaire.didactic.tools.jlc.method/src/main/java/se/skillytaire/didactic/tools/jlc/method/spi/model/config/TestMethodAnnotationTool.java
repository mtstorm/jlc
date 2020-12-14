package se.skillytaire.didactic.tools.jlc.method.spi.model.config;

import se.skillytaire.didactic.tools.jlc.method.api.TestMethod;
import se.skillytaire.didactic.tools.jlc.signature.spi.MethodSignature;

public class TestMethodAnnotationTool {
	private TestMethodAnnotationTool() {
		throw new AssertionError("No instances for you");
	}
   public static MethodSignature of(TestMethod testMethod) {
	   return new MethodSignature(null, testMethod.returnType(), testMethod.name(), testMethod.api(), testMethod.parameters());
   }
   
   public static boolean matches(MethodSignature signature, TestMethod obj) {
	      boolean equals = false;
	      if (obj != null) {
	         if (signature.getName().equals(obj.name())) {
	            if (obj.returnType().isAssignableFrom(signature.getReturnType())) {
	               if (signature.sameParameterSequence(obj.parameters())) {
	                  equals = true;
	               }
	            }
	         }
	      }
	      return equals;
   }
   

}
