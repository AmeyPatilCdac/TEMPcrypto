package in.cdac.java.connector;

import java.nio.charset.StandardCharsets;

import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import generated.TokenData;
import generated.Tokenize;
import generated.TokenizeRes;
import in.cdac.java.auth.core.AuthXmlCreator;
import in.cdac.java.kyc.core.KycXmlCreator;
import in.cdac.java.otp.core.OtpXmlCreator;
import in.cdac.java.parameters.RequestObject;
import in.cdac.java.parameters.ResponseObject;
import in.cdac.java.token.core.TokenXmlCreator;
import in.cdac.java.util.LogUtil;
import in.cdac.java.util.PropertyHandler;
import in.cdac.java.util.RequestType;
import in.cdac.java.util.Utility;
import in.cdac.java.validator.AuthReqValidator;
import in.cdac.java.validator.KycReqValidator;
import in.cdac.java.validator.OtpReqValidator;
import in.cdac.java.validator.TokenReqValidator;
import in.cdac.otp_v1.Otp;
import in.cdac.otp_v11.OtpRes;
import in.gov.uidai.authentication.uid_auth_request._2.Auth;
import in.gov.uidai.authentication.uid_auth_response._2.AuthRes;
import in.gov.uidai.kyc.request.KycReq;
import in.gov.uidai.kyc.response.Resp;
import in.gov.uidai.kyc.response.Ret;
import in.gov.uidai.kyc.result.KycRes;

/**
 * @author CDAC This class contains the major methods to call the requests
 */
public class ConnectorImpl implements Connector 
{
	static PropertyHandler propertyHandler = null;

	static 
	{
		propertyHandler = new PropertyHandler();
	}
	


	/**
	 * Method to handle OTP API request
	 */
	@Override
	public ResponseObject requestOTP(RequestObject requestObject) 
	{
		ResponseObject responseObject = null;
		setParameters(requestObject, RequestType.OTP);
		// Validate OTP request parameters
		String resp = new OtpReqValidator().validateRequestOTP(requestObject);
		if (resp != null) 
		{
			return setErrorMessage(resp);
		}
		responseObject = new ResponseObject();
		try 
		{
			Otp otp = new OtpXmlCreator().prepareOtpObject(requestObject);
			RestTemplate restTemplate = new RestTemplate();
			LogUtil.logXML(Otp.class, otp, "otp_request", requestObject.getLogPath());
			OtpRes otpResponse = restTemplate.postForObject(requestObject.getServiceURL(), otp, OtpRes.class);
			if (PropertyHandler.isLogEnabled()) 
			{
				LogUtil.logXML(Otp.class, otp, "otp_request", requestObject.getLogPath());
				LogUtil.logXML(OtpRes.class, otpResponse, "otp_response", requestObject.getLogPath());
			}
			if (otpResponse.getErr() != null) 
			{
				responseObject.setError(otpResponse.getErr());
				responseObject.setErrorMessage("Kindly refer the manual for error description of the error code.");
			}
			responseObject.setTimeStamp(otpResponse.getTs());
			responseObject.setTransaction(otpResponse.getTxn());
			responseObject.setCode(otpResponse.getCode());
			responseObject.setInfo(otpResponse.getInfo());
			responseObject.setResponseStatus(otpResponse.getRet().toString());
			responseObject.setStatus("Y");
		} 
		catch (HttpClientErrorException httpException) 
		{
			httpException.printStackTrace();
			return setErrorMessage("Unable to reach AUA server - " + httpException.getLocalizedMessage());
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return setErrorMessage(e.getLocalizedMessage());
		}
		return responseObject;
	}
	/**
	 * Method to handle the Auth API request
	 */
	@Override
	public ResponseObject requestAuth(RequestObject requestObject) 
	{
		ResponseObject responseObject = null;
		setParameters(requestObject, RequestType.AUTH);
		// validation
		String resp = new AuthReqValidator().validateRequestAuth(requestObject);
		if (resp != null) 
		{
			return setErrorMessage(resp);
		}
		// generate pid
		AuthXmlCreator authXmlCreator = new AuthXmlCreator();
		responseObject = new ResponseObject();
		try
		{
			Auth auth = authXmlCreator.prepareAuthObject(requestObject);
			LogUtil.logXML(Auth.class, auth, "auth_request", requestObject.getLogPath());
			RestTemplate restTemplate = new RestTemplate();
			AuthRes authResponse = restTemplate.postForObject(requestObject.getServiceURL(), auth, AuthRes.class);
			if (PropertyHandler.isLogEnabled()) 
			{
				LogUtil.logXML(Auth.class, auth, "auth_request", requestObject.getLogPath());
				LogUtil.logXML(AuthRes.class, authResponse, "auth_response", requestObject.getLogPath());
			}
			responseObject.setError(authResponse.getErr());
			responseObject.setAuthError(authResponse.getErr());
			responseObject.setTimeStamp(authResponse.getTs());
			responseObject.setTransaction(authResponse.getTxn());
			responseObject.setCode(authResponse.getCode());
			responseObject.setInfo(authResponse.getInfo());
			responseObject.setResponseStatus(authResponse.getRet().toString());
			responseObject.setStatus("Y");
			if (authResponse.getInfo() != null) 
			{
				String uidToken = authResponse.getInfo();
				uidToken = uidToken.substring(uidToken.indexOf("{") + 1, uidToken.length());
				uidToken = uidToken.substring(0, uidToken.indexOf(","));
				responseObject.setUIDToken(uidToken);
			}
			responseObject.setActionCode(authResponse.getActn());
			responseObject.setAuthActionCode(authResponse.getActn());
			
		} 
		catch (HttpClientErrorException httpException) 
		{
			httpException.printStackTrace();
			responseObject.setStatus("N");
			responseObject.setErrorMessage("Unable to reach AUA server - " + httpException.getLocalizedMessage());
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			responseObject.setStatus("N");
			responseObject.setErrorMessage(e.getLocalizedMessage());
		}
		return responseObject;
	}

	/**
	 * Method to handle the KYC API request
	 */
	@Override
	public ResponseObject requestKYC(RequestObject requestObject) 
	{
		ResponseObject responseObject = null;
		setParameters(requestObject, RequestType.KYC);
		// validation
		String resp = new KycReqValidator().validateRequestKyc(requestObject);
		if (resp != null) 
		{
			return setErrorMessage(resp);
		}
		// preparing kyc request
		KycXmlCreator kycXmlCreator = new KycXmlCreator();
		responseObject = new ResponseObject();
		try 
		{
			if (!requestObject.getTransaction().startsWith("UKC:")) 
			{
				requestObject.setTransaction("UKC:" + requestObject.getTransaction());
			}
			KycReq kycReq = kycXmlCreator.prepareKycObject(requestObject);
			LogUtil.logXML(KycReq.class, kycReq, "kyc_request", requestObject.getLogPath());
			RestTemplate restTemplate = new RestTemplate();
			Resp kycResponse = restTemplate.postForObject(requestObject.getServiceURL(), kycReq, Resp.class);
			if (PropertyHandler.isLogEnabled()) 
			{
				LogUtil.logXML(KycReq.class, kycReq, "kyc_request", requestObject.getLogPath());
				LogUtil.logXML(Resp.class, kycResponse, "kyc_resposne", requestObject.getLogPath());
			}
			Utility util = new Utility();
			responseObject = new ResponseObject();
			responseObject.setError(kycResponse.getErr());
			responseObject.setTimeStamp(kycResponse.getTs());
			responseObject.setTransaction(kycResponse.getTxn());
			responseObject.setResponseStatus(kycResponse.getRet().toString());
			responseObject.setCode(kycResponse.getCode());
			responseObject.setStatus("Y");
			if (responseObject.getError() != null
					&& (responseObject.getError().startsWith("AUA") || responseObject.getError().startsWith("ASA"))) {
				return responseObject;
			}
			KycRes kycRes = null;
			AuthRes authRes = null;
			if (kycResponse.getKycRes() != null) 
			{
				kycRes = (KycRes) util.unmarshallXml(KycRes.class, new String(kycResponse.getKycRes()));
				responseObject.setActionCode(kycRes.getActn());
				if (kycRes.getRar() != null) 
				{
					authRes = (AuthRes) util.unmarshallXml(AuthRes.class, new String(kycRes.getRar()));
				}
				if (authRes != null) 
				{
					String uidToken = authRes.getInfo();
					uidToken = uidToken.substring(uidToken.indexOf("{") + 1, uidToken.length());
					uidToken = uidToken.substring(0, uidToken.indexOf(","));
					responseObject.setUIDToken(uidToken);
					responseObject.setAuthActionCode(authRes.getActn());
				}
			}
			if (kycResponse.getRet().equals(Ret.Y)) 
			{
				try 
				{
					responseObject.setKycRes(kycRes);
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
					responseObject.setKycRes(null);
				}
			} 
			else if (kycResponse.getErr() != null && kycResponse.getErr() != "") 
			{
				try 
				{
					if (authRes != null) 
					{
						responseObject.setError(kycResponse.getErr() + "-AUTH-" + authRes.getErr());
					}
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
					responseObject.setKycRes(null);
				}
			} 
			else 
			{
				responseObject.setError(kycResponse.getErr());
			}
			return responseObject;
		} 
		catch (HttpClientErrorException httpException) 
		{
			httpException.printStackTrace();
			responseObject.setStatus("N");
			responseObject.setErrorMessage("Unable to reach AUA server - " + httpException.getLocalizedMessage());
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			responseObject.setStatus("N");
			responseObject.setErrorMessage(e.getLocalizedMessage());
		}
		return responseObject;
	}

	/**
	 * 
	 */
	@Override
	public ResponseObject requestTOKEN(RequestObject requestObject) 
	{
		ResponseObject responseObject = null;
		setParameters(requestObject, RequestType.TOKEN);
		String resp = new TokenReqValidator().validateRequestToken(requestObject);
		if (resp != null) 
		{
			return setErrorMessage(resp);
		}
		TokenXmlCreator tokenXmlCreator = new TokenXmlCreator();
		responseObject = new ResponseObject();
		try 
		{
			Tokenize token = tokenXmlCreator.prepareTokenObject(requestObject);
			LogUtil.logXML(Tokenize.class, token, "token_request", requestObject.getLogPath());
			RestTemplate restTemplate = new RestTemplate();
			TokenizeRes tokenResponse = restTemplate.postForObject(requestObject.getServiceURL(), token,
					TokenizeRes.class);
			if (PropertyHandler.isLogEnabled()) 
			{
				LogUtil.logXML(Tokenize.class, token, "token_request", requestObject.getLogPath());
				LogUtil.logXML(TokenizeRes.class, tokenResponse, "token_response", requestObject.getLogPath());
			}
			responseObject.setError(tokenResponse.getErr());
			responseObject.setTimeStamp(tokenResponse.getTs());
			responseObject.setTransaction(tokenResponse.getTxn());
			responseObject.setCode(tokenResponse.getCode());
			responseObject.setResponseStatus(tokenResponse.getRet().toString());
			responseObject.setStatus("Y");
			Utility util = new Utility();
			if (tokenResponse.getData() != null) 
			{
				String tokenDataXML = new String(tokenResponse.getData(), StandardCharsets.UTF_8);
				TokenData tokenData = (TokenData) util.unmarshallXml(TokenData.class, new String(tokenDataXML));
				responseObject.setUIDToken(tokenData.getId());
			}
		} 
		catch (HttpClientErrorException httpException) 
		{
			httpException.printStackTrace();
			responseObject.setStatus("N");
			responseObject.setErrorMessage("unable to reach AUA server - " + httpException.getLocalizedMessage());
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			responseObject.setStatus("N");
			responseObject.setErrorMessage(e.getLocalizedMessage());
		}
		return responseObject;
	}


	/**
	 * 
	 * @param requestObject
	 */
	private void setParameters(RequestObject requestObject, RequestType requestType) 
	{
		requestObject.setLogPath(PropertyHandler.getLogPath());
		if (PropertyHandler.isReadLkfromProperty()) 
		{
			requestObject.setAuaCode(PropertyHandler.getAUACode());
			requestObject.setSubAUACode(PropertyHandler.getSubAUACode());
		}
		switch (requestType) 
		{
		case OTP:
			if (PropertyHandler.isReadLkfromProperty()) {
				requestObject.setOtpLicenseKey(PropertyHandler.getOtpLicenseKey());
			}
			requestObject.setVersion(PropertyHandler.getOtpVersion());
			requestObject.setServiceURL(PropertyHandler.getOtpUrl());
			break;

		case AUTH:
			if (PropertyHandler.isReadLkfromProperty()) {
				requestObject.setAuthLicenseKey(PropertyHandler.getAuthLicenseKey());
			}
			if (requestObject.getBiometricResponse() != null) {
				requestObject.setTerminalId(PropertyHandler.getTerminalIdBiometric());
			} else {
				requestObject.setTerminalId("");
			}
			requestObject.setVersion(PropertyHandler.getAuthReqVersion());
			requestObject.setPidVersion(PropertyHandler.getPidVersion());
			requestObject.setServiceURL(PropertyHandler.getAuthUrl());
			break;
		case KYC:
			if (PropertyHandler.isReadLkfromProperty()) {
				requestObject.setKycLicenseKey(PropertyHandler.getKycLicenseKey());
			}
			if (requestObject.getBiometricResponse() != null) {
				requestObject.setTerminalId(PropertyHandler.getTerminalIdBiometric());
			} else {
				requestObject.setTerminalId("");
			}
			requestObject.setVersion(PropertyHandler.getKYCVersion());
			requestObject.setPidVersion(PropertyHandler.getPidVersion());
			requestObject.setServiceURL(PropertyHandler.getKYCUrl());
			break;
		case TOKEN:
			if (PropertyHandler.isReadLkfromProperty()) 
			
			{
				requestObject.setTokenLicenseKey(PropertyHandler.getTokenLicenseKey());
			}
			requestObject.setServiceURL(PropertyHandler.getTokenUrl());
			requestObject.setVersion(PropertyHandler.getTokenReqVersion());
			break;

		default:
			break;
		}
	}
	
	
	/**
	 * 
	 * @param errorMessage
	 * @return
	 */
	protected ResponseObject setErrorMessage(String errorMessage) 
	{
		ResponseObject responseObject = new ResponseObject();
		responseObject.setErrorMessage(errorMessage);
		responseObject.setStatus("N");
		return responseObject;
	}
}