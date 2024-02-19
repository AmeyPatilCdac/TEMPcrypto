   package in.cdac.java.parameters;

import in.cdac.otp_v1.Tkn;
import in.gov.uidai.authentication.uid_auth_request_data._2.BioMetricType;
import javax.xml.datatype.XMLGregorianCalendar;

public class RequestObject {
   private String logPath;
   private String serviceURL;
   private XMLGregorianCalendar timeStamp = null;
   private String transaction = null;
   private String terminalId = null;
   private String version = null;
   private String subAUACode = null;
   private String auaCode = null;
   private String aadhaarNumber = null;
   private String UIDToken = null;
   private String virtualID = null;
   private String otpLicenseKey = null;
   private String authLicenseKey = null;
   private String kycLicenseKey = null;
   private String tokenLicenseKey = null;
   private OtpOptions options = null;
   private Tkn tkn = null;
   private OTPRequestType otpRequestType = null;
   private PersonalIdentity personalIdentity = null;
   private PersonalAddress personalAddress = null;
   private PersonalFullAddress personalFullAddress = null;
   private PinValue pinValue = null;
   private String biometricResponse = null;
   private BioMetricType bioMetricType = null;
   private boolean residentConsent;
   private String udc = null;
   private String languageCode = null;
   private KYCRequest kycRequest = null;
   private String pidVersion = null;

   public RequestObject() {
      this.setResidentConsent(false);
   }

   public OtpOptions getOptions() {
      return this.options;
   }

   public void setOptions(OtpOptions options) {
      this.options = options;
   }

   public Tkn getTkn() {
      return this.tkn;
   }

   public void setTkn(Tkn tkn) {
      this.tkn = tkn;
   }

   public XMLGregorianCalendar getTimeStamp() {
      return this.timeStamp;
   }

   public void setTimeStamp(XMLGregorianCalendar timeStamp) {
      this.timeStamp = timeStamp;
   }

   public String getTransaction() {
      return this.transaction;
   }

   public void setTransaction(String transaction) {
      this.transaction = transaction;
   }

   public String getTerminalId() {
      return this.terminalId;
   }

   public void setTerminalId(String terminalId) {
      this.terminalId = terminalId;
   }

   public String getVersion() {
      return this.version;
   }

   public void setVersion(String version) {
      this.version = version;
   }

   public String getSubAUACode() {
      return this.subAUACode;
   }

   public void setSubAUACode(String subAUACode) {
      this.subAUACode = subAUACode;
   }

   public String getAuaCode() {
      return this.auaCode;
   }

   public void setAuaCode(String auaCode) {
      this.auaCode = auaCode;
   }

   public String getAadhaarNumber() {
      return this.aadhaarNumber;
   }

   public void setAadhaarNumber(String aadhaarNumber) {
      this.aadhaarNumber = aadhaarNumber;
   }

   public String getOtpLicenseKey() {
      return this.otpLicenseKey;
   }

   public void setOtpLicenseKey(String otpLicenseKey) {
      this.otpLicenseKey = otpLicenseKey;
   }

   public String getAuthLicenseKey() {
      return this.authLicenseKey;
   }

   public void setAuthLicenseKey(String authLicenseKey) {
      this.authLicenseKey = authLicenseKey;
   }

   public String getKycLicenseKey() {
      return this.kycLicenseKey;
   }

   public void setKycLicenseKey(String kycLicenseKey) {
      this.kycLicenseKey = kycLicenseKey;
   }

   public OTPRequestType getOtpRequestType() {
      return this.otpRequestType;
   }

   public void setOtpRequestType(OTPRequestType otpRequestType) {
      this.otpRequestType = otpRequestType;
   }

   public PersonalIdentity getPersonalIdentity() {
      return this.personalIdentity;
   }

   public void setPersonalIdentity(PersonalIdentity personalIdentity) {
      this.personalIdentity = personalIdentity;
   }

   public PersonalAddress getPersonalAddress() {
      return this.personalAddress;
   }

   public void setPersonalAddress(PersonalAddress personalAddress) {
      this.personalAddress = personalAddress;
   }

   public PersonalFullAddress getPersonalFullAddress() {
      return this.personalFullAddress;
   }

   public void setPersonalFullAddress(PersonalFullAddress personalFullAddress) {
      this.personalFullAddress = personalFullAddress;
   }

   public PinValue getPinValue() {
      return this.pinValue;
   }

   public void setPinValue(PinValue pinValue) {
      this.pinValue = pinValue;
   }

   public String getBiometricResponse() {
      return this.biometricResponse;
   }

   public void setBiometricResponse(String biometricResponse) {
      this.biometricResponse = biometricResponse;
   }

   public BioMetricType getBioMetricType() {
      return this.bioMetricType;
   }

   public void setBioMetricType(BioMetricType bioMetricType) {
      this.bioMetricType = bioMetricType;
   }

   public String getUdc() {
      return this.udc;
   }

   public void setUdc(String udc) {
      this.udc = udc;
   }

   public boolean isResidentConsent() {
      return this.residentConsent;
   }

   public void setResidentConsent(boolean residentConsent) {
      this.residentConsent = residentConsent;
   }

   public String getLanguageCode() {
      return this.languageCode;
   }

   public void setLanguageCode(String languageCode) {
      this.languageCode = languageCode;
   }

   public KYCRequest getKycRequest() {
      return this.kycRequest;
   }

   public void setKycRequest(KYCRequest kycRequest) {
      this.kycRequest = kycRequest;
   }

   public String getUIDToken() {
      return this.UIDToken;
   }

   public void setUIDToken(String uIDToken) {
      this.UIDToken = uIDToken;
   }

   public String getVirtualID() {
      return this.virtualID;
   }

   public void setVirtualID(String virtualID) {
      this.virtualID = virtualID;
   }

   public String getPidVersion() {
      return this.pidVersion;
   }

   public void setPidVersion(String pidVersion) {
      this.pidVersion = pidVersion;
   }

   public String getTokenLicenseKey() {
      return this.tokenLicenseKey;
   }

   public void setTokenLicenseKey(String tokenLicenseKey) {
      this.tokenLicenseKey = tokenLicenseKey;
   }

   public String getLogPath() {
      return this.logPath;
   }

   public void setLogPath(String logPath) {
      this.logPath = logPath;
   }

   public String getServiceURL() {
      return this.serviceURL;
   }

   public void setServiceURL(String serviceURL) {
      this.serviceURL = serviceURL;
   }
}