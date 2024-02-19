 package in.cdac.java.parameters;

public enum GenderType {
   MALE,
   FEMALE,
   TRANSGENDER;

   public String value() {
      return this.name();
   }

   public static GenderType fromValue(String v) {
      return valueOf(v);
   }
}
    