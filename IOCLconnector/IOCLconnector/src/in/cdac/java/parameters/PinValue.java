package in.cdac.java.parameters;

public class PinValue {
   private String otp;
   private String pin;
   private String pidData = null;

   public String getOtp() {
      return this.otp;
   }

   public void setOtp(String otp) {
      this.otp = otp;
   }

   public String getPin() {
      return this.pin;
   }

   public void setPin(String pin) {
      this.pin = pin;
   }

   public String getPidData() {
      return this.pidData;
   }

   public void setPidData(String pidData) {
      this.pidData = pidData;
   }
}