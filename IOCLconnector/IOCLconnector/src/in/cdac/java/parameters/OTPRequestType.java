 package in.cdac.java.parameters;

public enum OTPRequestType {
   MOBILE,
   AADHAAR,
   VIRTUAL_ID,
   ENCRYPTED_AADHAAR_NUMBER,
   UID_TOKEN;

   public String value() {
      return this.name();
   }

   public static OTPRequestType fromValue(String v) {
      return valueOf(v);
   }
}