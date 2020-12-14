package se.skillytaire.didactic.tools.jlc.signature.spi;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

public abstract class Signature implements Cloneable, Iterable<Class<?>> {
	private Class<?> declaredClass;
	private String name;
	private List<Class<?>> parameters;

	public Signature(Class<?> declaredClass, String name) {
		this(declaredClass, name, new Class<?>[0]);
	}

	public Signature(Class<?> declaredClass, String simpleName, Class<?>... paramTypes) {
		this.declaredClass = declaredClass;
		this.name = simpleName;
		setParameterTypes(paramTypes);
	}

	protected void setParameterTypes(Class<?>... paramTypes) {
		this.parameters = Arrays.asList(paramTypes);
	}

	public String getName() {
		return name;
	}



	@Override
	public Signature clone() {
		Signature clone;
		try {
			clone = (Signature) super.clone();
			clone.parameters = new ArrayList<>(this.parameters);
		} catch (CloneNotSupportedException e) {
			throw new AssertionError("Not possible", e);
		}
		return clone;
	}

	@Override
	public Iterator<Class<?>> iterator() {
		return this.parameters.iterator();
	}

	public Stream<Class<?>> parameters() {
		return parameters.stream();
	}

	public int getParameterCount() {
		return this.parameters.size();
	}

	public boolean sameParameterSequence(Class<?>... params) {
		return Arrays.equals(this.parameters.toArray(), params);
	}

	public Class<?>[] getParameterTypes() {
		Class<?>[] types = new Class<?>[this.parameters.size()];
		types = this.parameters.toArray(types);
		return types;
	}

	protected int compareParameterSequence(Class<?>... params) {
		int compare = Integer.compare(getParameterCount(), params.length);
		if (compare == 0) {
			for (int i = 0; i < params.length; i++) {
				String paramNameThis = parameters.get(i).getName();
				String paramNameThat = params[i].getName();
				compare += paramNameThis.compareTo(paramNameThat);
			}
		}
		return compare;
	}

	/**
	 * Checks if this signature is overloaded.
	 * 
	 * @param that
	 * @return
	 */
	public boolean isOverloaded(Signature that) {
		boolean isOverloaded = false;
		if(this.hasDeclaredClass() && that.hasDeclaredClass()) {
			boolean sameType = this.getDeclaredClass() == that.getDeclaredClass();
			return sameType && this.getName().equals(that.getName())
					&& !this.sameParameterSequence(that.getParameterTypes());
		}
		return isOverloaded;
	}

	public Class<?> getDeclaredClass() {
		return declaredClass;
	}
	
	public boolean hasDeclaredClass() {
		return declaredClass != null;
	}
	
	
//   

//   public final String getDisplayName() {
//      StringBuilder builder = new StringBuilder();
//      String f = getFeature();
//      if(f != null && !f.isEmpty()) {
//         builder.append(f);
//         builder.append(": ");
//      } 
//      builder.append(getSignature(this.isShortSignature()));
//      //System.out.println(builder  +"   sig "+getSignature());
//      return builder.append('.').toString();//FIXME JUnit vernachelt mijn displayname!!!
//   }

	public String getSignatureTestDescriptionWithRef(String referenceName, String... paramValues) {
		return referenceName + "." + getSignatureTestDescription(paramValues);
	}

	public String getSignatureTestDescription(String... paramValues) {
		StringBuilder builder = new StringBuilder();
		builder.append(name);
		builder.append('(');
		Iterator<String> iterator = Arrays.asList(paramValues).iterator();
		while (iterator.hasNext()) {
			String aValue = iterator.next();
			builder.append(aValue);
			if (iterator.hasNext()) {
				builder.append(',');
			}

		}
		builder.append(')');
		return builder.toString();
	}

	/**
	 * @return true when the name and parameters matches.
	 */
	@Override
	public boolean equals(Object obj) {
		boolean equals = super.equals(obj);
		if (!equals && obj instanceof Signature) {
			Signature that = (Signature) obj;
			if (this.getName().equals(that.getName())) {
				if (this.sameParameterSequence(that.getParameterTypes())) {
					equals = true;
				}
			}
		}
		return equals;
	}

	/**
	 * When a parameter is an array type
	 * 
	 * @return
	 */
	public boolean hasArrayParameter() {
		return this.parameters.stream().filter(p -> p.isArray()).findFirst().isPresent();
	}

	public boolean hasParameters() {
		return this.getParameterCount() > 0;
	}

	/**
	 * Checks if there are non primitive parameter types, aka Object or its
	 * children.
	 * 
	 * @return
	 */
	public boolean hasNonPrimitiveParameters() {
		return this.parameters.stream().filter(c -> !c.isPrimitive()).findFirst().isPresent();
	}

	public String toString(boolean shortVersion) {
		StringBuilder builder = new StringBuilder();
		if(!shortVersion) {
			builder.append(getDeclaredClass().getName());
			builder.append('#');
		}
		builder.append(name);
		builder.append(paramString(shortVersion));
		return builder.toString();
	}

	private StringBuilder paramString(boolean shortVersion) {
		StringBuilder builder = new StringBuilder();
		builder.append('(');
		Iterator<Class<?>> iterator = parameters.iterator();
		while (iterator.hasNext()) {
			Class<?> class1 = iterator.next();
			builder.append(shortVersion ? class1.getSimpleName() : class1.getName());
			if (iterator.hasNext()) {
				builder.append(',');
			}

		}
		
		builder.append(')');
		return builder;
	}
	
	public String qualifiedName() {
		StringBuilder builder = new StringBuilder();
		builder.append(getDeclaredClass().getName());
		builder.append('#');
		builder.append(name);
		builder.append(paramString(true));
		return builder.toString();
	}
	@Override

	public String toString() {
		return toString(false);
	}

	// naar de config

	/**
	 * Create an URI that links to the message.
	 * 
	 * @param type
	 * @return
	 */
	public URI toUri(Class<?> type) {
		StringBuilder builder = new StringBuilder();
//append(type.getName()).append('#')
		String uri = builder.append("method:").append(qualifiedName()).toString();
		URI result = null;
		try {
			result = new URI(uri);
		} catch (URISyntaxException e) {
			throw new AssertionError(e);
		}
		// System.out.println("URI in signature "+ uri);
		return result;
	}

	/**
	 * Create an URI that links to the class.
	 * 
	 * @param type
	 * @return
	 */
	public URI toTypeUri(Class<?> type) {
		StringBuilder builder = new StringBuilder();

		String uri = builder.append("method:").append(type.getName()).append('#').toString();
		// System.out.println("type URI "+uri);
		URI result = null;
		try {
			result = new URI(uri);
		} catch (URISyntaxException e) {
			throw new AssertionError(e);
		}

		// result = type.getClassLoader().

		return result;
	}

	private boolean api;

	public boolean isApi() {
		return api;
	}

	public void setApi(boolean api) {
		this.api = api;
	}
}
