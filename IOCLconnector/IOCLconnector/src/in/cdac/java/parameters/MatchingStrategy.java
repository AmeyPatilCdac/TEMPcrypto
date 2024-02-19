 package in.cdac.java.parameters;

public enum MatchingStrategy {
   PARTIAL,
   EXACT;

   public String value() {
      return this.name();
   }

   public static MatchingStrategy fromValue(String v) {
      return valueOf(v);
   }
}
    