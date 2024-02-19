 package in.cdac.java.validator;

import in.cdac.java.parameters.RequestObject;
import in.cdac.java.util.PropertyHandler;
import java.io.File;

public class KycReqValidator extends CommonValidator {
   public String validateRequestKyc(RequestObject requestObject) {
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
      if (requestObject.getKycLicenseKey() != null && !requestObject.getKycLicenseKey().isEmpty()) {
         if (PropertyHandler.getKYCVersion() != null && !PropertyHandler.getKYCVersion().isEmpty()) {
            if (PropertyHandler.getAuthReqVersion() != null && !PropertyHandler.getAuthReqVersion().isEmpty()) {
               if (requestObject.getBiometricResponse() == null && requestObject.getPinValue() == null) {
                  return "please set the PinValue object with corresponding OTP or Biometric Response XML.";
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
                     } else if (PropertyHandler.getKYCUrl() == null) {
                        return "please set KYC url in the connector.properties file";
                     } else if (requestObject.getKycRequest() == null) {
                        return "please set the kyc object in the request.";
                     } else {
                        return requestObject.getKycRequest().getAuthenticationType() == null ? "please set the Authentication Type in the KYC Request." : null;
                     }
                  }
               }
            } else {
               return "please set authversion property in the connector.property file";
            }
         } else {
            return "please set kycversion property in the connector.property file";
         }
      } else {
         return "please set the KYC License Key.";
      }
   }
}
    