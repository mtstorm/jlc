package se.skillytaire.didactic.tools.jlc.api;

public final class Archetype implements Comparable<Archetype> {
	private final String name;

	public Archetype(final String name) {
		if (name == null) {
			throw new IllegalArgumentException("name is void");
		}
		if (name.trim().isEmpty()) {
			throw new IllegalArgumentException("name is empty after trim");
		}
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	@Override
	public int hashCode() {
		return 13 * getName().hashCode();
	}

	@Override
	public boolean equals(final Object obj) {
		boolean equals = super.equals(obj);
		if (!equals && obj instanceof Archetype) {
			final Archetype that = (Archetype) obj;
			equals = compareTo(that) == 0;
		}
		return equals;
	}

	@Override
	public int compareTo(final Archetype that) {
		return getName().compareTo(that.getName());
	}

	@Override
	public String toString() {
		return String.format("Archetype [name=%s]", this.name);
	}

   public static Archetype of(String archetype) {
      return new Archetype(archetype);
   }

}
