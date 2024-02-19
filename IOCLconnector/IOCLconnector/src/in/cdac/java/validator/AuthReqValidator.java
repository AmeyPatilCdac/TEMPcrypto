 package in.cdac.java.validator;

import in.cdac.java.parameters.MatchingStrategy;
import in.cdac.java.parameters.RequestObject;
import in.cdac.java.util.PropertyHandler;
import java.io.File;

public class AuthReqValidator extends CommonValidator {
   public String validateRequestAuth(RequestObject requestObject) {
      String resp = null;
      if (requestObject.getAadhaarNumber() != null && !requestObject.getAadhaarNumber().isEmpty()) {
         resp = this.validateAadhaar(requestObject);
         if (resp != null) {
            return resp;
         }
      } else if (requestObject.getVirtualID() != null) {
         resp = this.validateVID(requestObject);
         if (resp != null) {
            return resp;
         }
      } else {
         if (requestObject.getUIDToken() == null) {
            return "please set the aadhaar number or Virtual ID or UID token.";
         }

         resp = this.validateUIDToken(requestObject);
         if (resp != null) {
            return resp;
         }
      }

      resp = this.validateCommonParam(requestObject);
      if (resp != null) {
         return resp;
      } else {
         resp = this.AuthParam(requestObject);
         return resp != null ? resp : null;
      }
   }

   private String AuthParam(RequestObject requestObject) {
      if (requestObject.getAuthLicenseKey() != null && !requestObject.getAuthLicenseKey().isEmpty()) {
         if (PropertyHandler.getAuthReqVersion() == null) {
            return "please set authversion property in the connector.property file";
         } else if (requestObject.getPersonalAddress() == null && requestObject.getPersonalFullAddress() == null && requestObject.getPersonalIdentity() == null && requestObject.getPinValue() == null && requestObject.getBiometricResponse() == null) {
            return "please set any one of following for Auth request pi, pa, pfa, pv or bio.";
         } else if (!requestObject.isResidentConsent()) {
            return "please set the resident consent as true.";
         } else {
            if (requestObject.getBiometricResponse() != null) {
               if (PropertyHandler.getTerminalIdBiometric() == null) {
                  return "please set terminalIdBiometric property in the connector.property file";
               }

               if (requestObject.getBioMetricType() == null) {
                  return "please set biometrict type in request.";
               }
            }

            if (requestObject.getPersonalAddress() != null) {
               MatchingStrategy strategy = requestObject.getPersonalAddress().getMatchStrategy();
               if (strategy == MatchingStrategy.PARTIAL) {
                  return "please set the matching strategy as EXACT.";
               }
            }

            if (requestObject.getPersonalFullAddress() != null && requestObject.getPersonalFullAddress().getFullAddress() == null && requestObject.getPersonalFullAddress().getLocalAddressValue() == null) {
               return "personal full address field can not be null.";
            } else {
               if (requestObject.getPinValue() != null) {
                  if (requestObject.getPinValue().getOtp() == null) {
                     return "please set the otp.";
                  }

                  if (!requestObject.getPinValue().getOtp().matches(this.regexOTP)) {
                     return "invalid OTP.";
                  }
               }

               if (PropertyHandler.getPidVersion() == null) {
                  return "please set the piDversion property in the connector.properties file.";
               } else if (PropertyHandler.getCertificatePath() == null) {
                  return "please set certificatePath property in the connector.property file";
               } else {
                  File file = new File(PropertyHandler.getCertificatePath());
                  if (!file.exists()) {
                     return "invalid certificate path.";
                  } else {
                     return PropertyHandler.getAuthUrl() == null ? "please set AUTH url in the connector.properties file" : null;
                  }
               }
            }
         }
      } else {
         return "please set the AUTH License Key.";
      }
   }
}
    