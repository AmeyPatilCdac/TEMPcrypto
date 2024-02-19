  package in.cdac.java.otp.core;

import in.cdac.java.parameters.OTPRequestType;
import in.cdac.java.parameters.OtpOptions;
import in.cdac.java.parameters.RequestObject;
import in.cdac.java.util.MACAddressUtil;
import in.cdac.otp_v1.Opts;
import in.cdac.otp_v1.Otp;
import in.cdac.otp_v1.Type;

public class OtpXmlCreator {
   public Otp prepareOtpObject(RequestObject requestObject) throws Exception {
      Otp otp = new Otp();
      if (requestObject.getAadhaarNumber() != null && requestObject.getOtpRequestType().equals(OTPRequestType.AADHAAR)) {
         otp.setUid(requestObject.getAadhaarNumber());
         otp.setType(Type.A);
      } else if (requestObject.getVirtualID() != null && requestObject.getOtpRequestType().equals(OTPRequestType.VIRTUAL_ID)) {
         otp.setUid(requestObject.getVirtualID());
         otp.setType(Type.V);
      } else if (requestObject.getUIDToken() != null && requestObject.getOtpRequestType().equals(OTPRequestType.UID_TOKEN)) {
         otp.setUid(requestObject.getUIDToken());
         otp.setType(Type.T);
      }

      otp.setAc(requestObject.getAuaCode());
      otp.setLk(requestObject.getOtpLicenseKey());
      otp.setSa(requestObject.getSubAUACode());
      otp.setTid(MACAddressUtil.getMacAddress());
      otp.setTxn(requestObject.getTransaction());
      otp.setVer(requestObject.getVersion());
      otp.setTs(requestObject.getTimeStamp());
      Opts opts = new Opts();
      if (requestObject.getOptions().equals(OtpOptions.SMS_AND_EMAIL)) {
         opts.setCh("00");
      } else if (requestObject.getOptions().equals(OtpOptions.ONLY_SMS)) {
         opts.setCh("01");
      } else {
         opts.setCh("02");
      }

      otp.setOpts(opts);
      return otp;
   }
}