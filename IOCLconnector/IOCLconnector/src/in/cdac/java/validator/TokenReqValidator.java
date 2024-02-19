package in.cdac.java.validator;

import in.cdac.java.parameters.RequestObject;
import in.cdac.java.util.PropertyHandler;
import java.io.File;

public class TokenReqValidator extends CommonValidator {
   public String validateRequestToken(RequestObject requestObject) {
      String resp = null;
      if (requestObject.getAadhaarNumber() != null && !requestObject.getAadhaarNumber().isEmpty()) {
         resp = this.validateAadhaar(requestObject);
         if (resp != null) {
            return resp;
         } else {
            resp = this.validateCommonParam(requestObject);
            if (resp != null) {
               return resp;
            } else {
               resp = this.TokenParam(requestObject);
               return resp != null ? resp : null;
            }
         }
      } else {
         return "please set the aadhaar number.";
      }
   }

   private String TokenParam(RequestObject requestObject) {
      if (requestObject.getTokenLicenseKey() != null && requestObject.getTokenLicenseKey() != "") {
         if (PropertyHandler.getTokenReqVersion() != null && PropertyHandler.getTokenReqVersion() != "") {
            if (PropertyHandler.getCertificatePath() != null && PropertyHandler.getCertificatePath() != "") {
               File file = new File(PropertyHandler.getCertificatePath());
               if (!file.exists()) {
                  return "invalid certificate path.";
               } else {
                  return PropertyHandler.getTokenUrl() != null && PropertyHandler.getTokenUrl() != "" ? null : "please set TOKEN url in the connector.properties file";
               }
            } else {
               return "please set certificatePath property in the connector.property file";
            }
         } else {
            return "please set tokenversion property in the connector.property file";
         }
      } else {
         return "please set the TOKEN License Key.";
      }
   }
}
    