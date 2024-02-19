 package in.cdac.java.parameters;

public enum DateOfBirthType {
   VERIFIED,
   DECLARED,
   APPROXIMATE;

   public String value() {
      return this.name();
   }

   public static DateOfBirthType fromValue(String v) {
      return valueOf(v);
   }
}
    