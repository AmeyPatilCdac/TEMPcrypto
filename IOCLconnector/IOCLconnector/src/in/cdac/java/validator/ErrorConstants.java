 package in.cdac.java.validator;

public class ErrorConstants {
   public static final String CONNECTION_SPCIFIED_ERROR = "Connector generated error. Please refer error message for more details.";
   public static final String SET_AADHAAR_NUMBER = "please set the aadhaar number or Virtual ID or UID token.";
   public static final String INVALID_AADHAAR_NUMBER = "invalid aadhaar number.";
   public static final String INVALID_VIRTUAL_ID = "invalid Virtual ID";
   public static final String INVALID_UID_TOKEN = "invalid UID Token";
   public static final String SET_OTP_REQUEST_TYPE = "please set the OTP request type as ";
   public static final String INVALID_TXN_FORMAT = "invalid transaction id format. It should be UUID";
   public static final String SET_AUA_CODE = "please set the AUA code.";
   public static final String SET_SA_CODE = "please set the SUB AUA code";
   public static final String SET_OTP_LICENSE_KEY = "please set the OTP License Key.";
   public static final String SET_AUTH_LICENSE_KEY = "please set the AUTH License Key.";
   public static final String SET_TOKEN_LICENSE_KEY = "please set the TOKEN License Key.";
   public static final String SET_KYC_LICENSE_KEY = "please set the KYC License Key.";
   public final String AUA_SUBAUA_MISMATCH = "aua code and sub-aua code is not matched.";
   public static final String SET_TIMESTAMP = "please set the timestamp.";
   public static final String SET_TRANSACTION_ID = "please set the transaction ID.";
   public static final String SET_PID_DATA = "please set any one of following for Auth request pi, pa, pfa, pv or bio.";
   public static final String SET_RESIDENT_CONSENT = "please set the resident consent as true.";
   public static final String INVALID_CERT_PATH = "invalid certificate path.";
   public static final String INVALID_OTP = "invalid OTP.";
   public static final String SET_AADHAARNUMBER = "please set the aadhaar number.";
   public static final String SET_OTP = "please set the otp.";
   public static final String PFA_CANNOT_NULL = "personal full address field can not be null.";
   public static final String SET_MATCHING_STRATEGY = "please set the matching strategy as EXACT.";
   public static final String SET_OTP_URL = "please set OTP url in the connector.properties file";
   public static final String SET_AUTH_URL = "please set AUTH url in the connector.properties file";
   public static final String SET_TOKEN_URL = "please set TOKEN url in the connector.properties file";
   public static final String SET_KYC_URL = "please set KYC url in the connector.properties file";
   public static final String SET_OTP_VERSION = "please set otpversion property in the connector.property file";
   public static final String SET_AUTH_VERSION = "please set authversion property in the connector.property file";
   public static final String SET_TOKEN_VERSION = "please set tokenversion property in the connector.property file";
   public static final String SET_KYC_VERSION = "please set kycversion property in the connector.property file";
   public final String SET_TERMINALID_PUBLIC = "please set terminalIdPublic property in the connector.property file";
   public static final String SET_TERMINALID_BIOMETRIC = "please set terminalIdBiometric property in the connector.property file";
   public static final String SET_BIOMETRIC_TYPE = "please set biometrict type in request.";
   public static final String SET_CERTIFICATE_PATH = "please set certificatePath property in the connector.property file";
   public static final String SET_PIN_VALUE_OBJECT = "please set the PinValue object with corresponding OTP or Biometric Response XML.";
   public static final String SET_KYC_REQUEST = "please set the kyc object in the request.";
   public static final String SET_KYC_AUTHENTICATION_TYPE = "please set the Authentication Type in the KYC Request.";
   public static final String SET_PID_VERSION = "please set the piDversion property in the connector.properties file.";
   public static final String SET_OTP_OPTIONS = "please set otp options as SMS_AND_EMAIL or ONLY_SMS or ONLY_EMAIL";
}
    