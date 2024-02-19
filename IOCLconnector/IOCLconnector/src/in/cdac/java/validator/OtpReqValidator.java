  package in.cdac.java.validator;

import in.cdac.java.parameters.OTPRequestType;
import in.cdac.java.parameters.RequestObject;
import in.cdac.java.util.PropertyHandler;

public class OtpReqValidator extends CommonValidator {
   public String validateRequestOTP(RequestObject requestObject) {
      String resp = null;
      if (requestObject.getOtpRequestType() == null) {
         return "please set the OTP request type as " + OTPRequestType.AADHAAR + " or " + OTPRequestType.VIRTUAL_ID + " or " + OTPRequestType.UID_TOKEN;
      } else {
         if (requestObject.getAadhaarNumber() != null && !requestObject.getAadhaarNumber().isEmpty()) {
            resp = this.validateAadhaar(requestObject);
            if (resp != null) {
               return resp;
            }

            if (!requestObject.getOtpRequestType().equals(OTPRequestType.AADHAAR)) {
               return "please set the OTP request type as " + OTPRequestType.AADHAAR;
            }
         } else if (requestObject.getVirtualID() != null) {
            resp = this.validateVID(requestObject);
            if (resp != null) {
               return resp;
            }

            if (!requestObject.getOtpRequestType().equals(OTPRequestType.VIRTUAL_ID)) {
               return "please set the OTP request type as " + OTPRequestType.VIRTUAL_ID;
            }
         } else {
            if (requestObject.getUIDToken() == null) {
               return "please set the aadhaar number or Virtual ID or UID token.";
            }

            resp = this.validateUIDToken(requestObject);
            if (resp != null) {
               return resp;
            }

            if (!requestObject.getOtpRequestType().equals(OTPRequestType.UID_TOKEN)) {
               return "please set the OTP request type as " + OTPRequestType.UID_TOKEN;
            }
         }

         resp = this.validateCommonParam(requestObject);
         if (resp != null) {
            return resp;
         } else {
            resp = this.OtpParam(requestObject);
            return resp != null ? resp : null;
         }
      }
   }

   private String OtpParam(RequestObject requestObject) {
      if (requestObject.getOtpLicenseKey() != null && !requestObject.getOtpLicenseKey().isEmpty()) {
         if (PropertyHandler.getOtpVersion() != null && !PropertyHandler.getOtpVersion().isEmpty()) {
            if (PropertyHandler.getOtpUrl() != null && !PropertyHandler.getOtpUrl().isEmpty()) {
               return requestObject.getOptions() == null ? "please set otp options as SMS_AND_EMAIL or ONLY_SMS or ONLY_EMAIL" : null;
            } else {
               return "please set OTP url in the connector.properties file";
            }
         } else {
            return "please set otpversion property in the connector.property file";
         }
      } else {
         return "please set the OTP License Key.";
      }
   }
}