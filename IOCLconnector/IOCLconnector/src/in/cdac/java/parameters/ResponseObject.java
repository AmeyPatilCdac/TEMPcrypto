package in.cdac.java.parameters;

import javax.xml.datatype.XMLGregorianCalendar;

import in.gov.uidai.kyc.result.KycRes;

/**
 * @author CDAC
 * This class contains the values of response from UIDAI or AUA.
 */
public class ResponseObject {
	
	private String status = null;
	private String errorMessage = null;
	private String error = null;
	private XMLGregorianCalendar timeStamp = null;
	private String transaction = null;
	private String code = null;
	private String info = null;
	private String responseStatus = null;
	private KycRes kycRes = null;
	private String UIDToken = null;
	private String actionCode = null;
	private String authActionCode=null;
	private String authError=null;
	
	public String getAuthError() {
		return authError;
	}
	public void setAuthError(String authError) {
		this.authError = authError;
	}
	public String getAuthActionCode() {
		return authActionCode;
	}
	public void setAuthActionCode(String authActionCode) {
		this.authActionCode = authActionCode;
	}
	public ResponseObject(){
		status = "Y";
	}
	/**
	 * This method returns Error message.
	 * Connector validates the request object and gives this error message in case of any invalid request attributes.
	 * @return String
	 */
	public String getErrorMessage() {
		return errorMessage;
	}
	/**
	 * This method sets the Error message.
	 * Mostly connector uses this method.
	 * @param errorMessage
	 */
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	/**
	 * This method returns the Error from UIDAI or AUA.
	 * UIDAI error codes will be like 100, 110, 200, 720 etc...
	 * AUA error codes will be like AUA-AUTH27, AUA-OTP23 etc...
	 * More details for the error codes please refer the API documents shared by UIDAI/AUA.
	 * @return String
	 */
	public String getError() {
		return error;
	}
	/**
	 * This method sets Error from the UIDAI or AUA.
	 * Mostly connector uses this method.
	 * @param error String
	 */
	public void setError(String error) {
		this.error = error;
	}
	/**
	 * This method returns the Response TimeStamp.
	 * The TimeStamp is in the format of XMLGregorianCalendar.
	 * The TimeStamp holds the time when the response generated from UIDAI or AUA.
	 * @return XMLGregorianCalendar
	 */
	public XMLGregorianCalendar getTimeStamp() {
		return timeStamp;
	}
	/**
	 * This method sets the Response TimeStamp.
	 * Mostly connector uses this method.
	 * @param timeStamp XMLGregorianCalendar
	 */
	public void setTimeStamp(XMLGregorianCalendar timeStamp) {
		this.timeStamp = timeStamp;
	}
	/**
	 * This method returns the Response Transaction ID.
	 * This is the same transaction id which is sent in the request.
	 * So request and response should have the same transaction ID.
	 * @return String
	 */
	public String getTransaction() {
		return transaction;
	}
	/**
	 * This method sets the Transaction ID.
	 * Mostly connector uses this method.
	 * @param transaction String
	 */
	public void setTransaction(String transaction) {
		this.transaction = transaction;
	}
	/**
	 * This method returns the code from Response.
	 * @return String
	 */
	public String getCode() {
		return code;
	}
	/**
	 * This method sets the code.
	 * Mostly connector uses this method.
	 * @param code String
	 */
	public void setCode(String code) {
		this.code = code;
	}
	/**
	 * This method returns the info from Response.
	 * @return String
	 */
	public String getInfo() {
		return info;
	}
	/**
	 * This method sets the info.
	 * Mostly connector uses this method.
	 * @param info String
	 */
	public void setInfo(String info) {
		this.info = info;
	}
	/**
	 * This method returns the status of the request.
	 * Y means the connector successfully forwards the request to UIDAI.
	 * N means there is some problem while generating the request.
	 * @return String
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * This method sets Status for the request.
	 * Mostly connector uses this method.
	 * @param status String
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * This method returns the status of the response from UIDAI.
	 * This will return Y for success else N.
	 * In case of N please refer error field for more details.
	 * @return String
	 */
	public String getResponseStatus() {
		return responseStatus;
	}

	/**
	 * This method sets Status for the resposne from UIDAI
	 * Mostly connector uses this method.
	 * @param status String
	 */
	public void setResponseStatus(String responseStatus) {
		this.responseStatus = responseStatus;
	}
	public KycRes getKycRes() {
		return kycRes;
	}
	public void setKycRes(KycRes kycRes) {
		this.kycRes = kycRes;
	}
	public String getUIDToken() {
		return UIDToken;
	}
	public void setUIDToken(String uIDToken) {
		UIDToken = uIDToken;
	}
	public String getActionCode() {
		return actionCode;
	}
	public void setActionCode(String actionCode) {
		this.actionCode = actionCode;
	}
}