package se.skillytaire.didactic.tools.jlc.api;

public final class Archetype implements Comparable<Archetype>{

 //  public static final Archetype JAVA_BEAN= new Archetype("JavaBean");
   private final String name;
   public Archetype(String name) {
	   if(name == null) {
		   throw new IllegalArgumentException("name is void");
	   }
	   if(name.trim().isEmpty()) {
		   throw new IllegalArgumentException("name is empty after trim");
	   }
      this.name = name;
   }

   public String getName() {
      return name;
   }

   @Override
   public int hashCode() {
      return 13 * this.getName().hashCode();
   }
 
   @Override
   public boolean equals(Object obj) {
      boolean equals = super.equals(obj);
      if(!equals && obj instanceof Archetype) {
         Archetype that = (Archetype) obj;
         equals = this.compareTo(that) == 0;
      }
      return equals;
   }

   @Override
   public int compareTo(Archetype that) {
      return this.getName().compareTo(that.getName());
   }

   @Override
   public String toString() {
      return String.format("Archetype [name=%s]", name);
   }

//   public DisplayName toDisplayName() {
//	   return new BasicDisplayName(getName());
//   }
}
