 package in.cdac.java.validator;

import in.cdac.java.parameters.RequestObject;
import in.cdac.java.parameters.ResponseObject;
import java.util.UUID;

public class CommonValidator {
   ResponseObject responseObject = null;
   String regexOTP = "\\d{6}";
   String regexAadharNo = "\\d{12}";
   String regexVID = "\\d{16}";

   protected String validateCommonParam(RequestObject requestObject) {
      if (requestObject.getAuaCode() != null && !requestObject.getAuaCode().isEmpty()) {
         if (requestObject.getSubAUACode() != null && !requestObject.getSubAUACode().isEmpty()) {
            if (requestObject.getTransaction() != null && !requestObject.getTransaction().isEmpty()) {
               if (!this.isValidTxnId(requestObject.getTransaction())) {
                  return "invalid transaction id format. It should be UUID";
               } else {
                  return requestObject.getTimeStamp() == null ? "please set the timestamp." : null;
               }
            } else {
               return "please set the transaction ID.";
            }
         } else {
            return "please set the SUB AUA code";
         }
      } else {
         return "please set the AUA code.";
      }
   }

   protected String validateAadhaar(RequestObject requestObject) {
      return !requestObject.getAadhaarNumber().matches(this.regexAadharNo) && !AadhaarValidator.ValidateVerhoeff(requestObject.getAadhaarNumber()) ? "invalid aadhaar number." : null;
   }

   protected String validateVID(RequestObject requestObject) {
      return !requestObject.getVirtualID().matches(this.regexVID) ? "invalid Virtual ID" : null;
   }

   protected String validateUIDToken(RequestObject requestObject) {
      return requestObject.getUIDToken().length() != 72 ? "invalid UID Token" : null;
   }

   private boolean isValidTxnId(String txnId) {
      try {
         UUID.fromString(txnId);
         return true;
      } catch (Exception var3) {
         return false;
      }
   }
}
    