 package in.cdac.java.parameters;

public class KYCRequest {
   private ResidentAuthenticationType authenticationType = null;
   private boolean localLanguageRequired = false;
   private boolean decryptionRequired = false;
   private boolean printFormatRequired = false;

   public ResidentAuthenticationType getAuthenticationType() {
      return this.authenticationType;
   }

   public void setAuthenticationType(ResidentAuthenticationType authenticationType) {
      this.authenticationType = authenticationType;
   }

   public boolean isLocalLanguageRequired() {
      return this.localLanguageRequired;
   }

   public void setLocalLanguageRequired(boolean localLanguageRequired) {
      this.localLanguageRequired = localLanguageRequired;
   }

   public boolean isDecryptionRequired() {
      return this.decryptionRequired;
   }

   public void setDecryptionRequired(boolean decryptionRequired) {
      this.decryptionRequired = decryptionRequired;
   }

   public boolean isPrintFormatRequired() {
      return this.printFormatRequired;
   }

   public void setPrintFormatRequired(boolean printFormatRequired) {
      this.printFormatRequired = printFormatRequired;
   }
}