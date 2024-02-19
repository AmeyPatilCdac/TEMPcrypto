package in.cdac.java.kyc.core;

import java.io.StringWriter;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import in.cdac.java.auth.core.AuthXmlCreator;
import in.cdac.java.parameters.RequestObject;
import in.cdac.java.util.LogUtil;
import in.cdac.java.util.PropertyHandler;
import in.gov.uidai.authentication.uid_auth_request._2.Auth;
import in.gov.uidai.kyc.request.De;
import in.gov.uidai.kyc.request.KycReq;
import in.gov.uidai.kyc.request.Lr;
import in.gov.uidai.kyc.request.Pfr;
import in.gov.uidai.kyc.request.Ra;
import in.gov.uidai.kyc.request.Rc;

/**
 * 
 * @author C-DAC
 *
 */
public class KycXmlCreator 
{
	/**
	 * Method to prepare KYC Request Object
	 * @param requestObject
	 * @param propertyHandler
	 * @return
	 * @throws Exception
	 */
	public KycReq prepareKycObject(RequestObject requestObject) throws Exception
	{
		KycReq kycReq = new KycReq();
		kycReq.setVer(requestObject.getVersion());
		if(requestObject.getKycRequest().isDecryptionRequired())
		{
			kycReq.setDe(De.Y);
		}
		else
		{
			kycReq.setDe(De.N);
		}
		
		if(requestObject.getKycRequest().isLocalLanguageRequired())
		{
			kycReq.setLr(Lr.Y);
		}
		else
		{
			kycReq.setLr(Lr.N);
		}
		if(requestObject.getKycRequest().isPrintFormatRequired())
		{
			kycReq.setPfr(Pfr.Y);
		}
		else
		{
			kycReq.setPfr(Pfr.N);
		}
		
		if(requestObject.isResidentConsent())
		{
		kycReq.setRc(Rc.Y);
		}
		switch (requestObject.getKycRequest().getAuthenticationType()) 
		{
			case OTP:
				kycReq.setRa(Ra.O);
				break;
			case FINGERPRINT:
				kycReq.setRa(Ra.F);
				break;
			case IRIS:
				kycReq.setRa(Ra.I);
				break;
			case OTP_FINGERPRINT:
				kycReq.setRa(Ra.FO);
				break;
			case OTP_IRIS:
				kycReq.setRa(Ra.IO);
				break;
			case FINGERPRINT_IRIS:
				kycReq.setRa(Ra.FI);
				break;
			case OTP_FINGERPRINT_IRIS:
				kycReq.setRa(Ra.FIO);
				break;
			case FACE:
				kycReq.setRa(Ra.P);
				break;
			case FACE_OTP:
				kycReq.setRa(Ra.PO);
				break;
			case FINGERPRINT_FACE:
				kycReq.setRa(Ra.FP);
				break;
			case FINGERPRINT_FACE_OTP:
				kycReq.setRa(Ra.FOP);
				break;
			case FINGERPRINT_IRIS_FACE:
				kycReq.setRa(Ra.FIP);
				break;
			case FINGERPRINT_IRIS_FACE_OTP:
				kycReq.setRa(Ra.FIOP);
				break;
			case IRIS_FACE:
				kycReq.setRa(Ra.IP);
				break;
			case IRIS_FACE_OTP:
				kycReq.setRa(Ra.IOP);
				break;
			default:
				kycReq.setRa(Ra.O);
				break;
		}
		
		requestObject.setVersion(PropertyHandler.getAuthReqVersion());
		requestObject.setAuthLicenseKey(requestObject.getKycLicenseKey());
		AuthXmlCreator authXmlCreator = new AuthXmlCreator();
		Auth auth = authXmlCreator.prepareAuthObject(requestObject);
		JAXBContext jaxbContext = JAXBContext.newInstance(Auth.class);
		Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
		StringWriter stringWriter = new StringWriter();
		jaxbMarshaller.marshal(auth, stringWriter);
		String authXML = stringWriter.toString();
		if(PropertyHandler.isLogEnabled())
		{
			LogUtil.logXML(authXML, "kyc_auth_xml", PropertyHandler.getLogPath());
		}
		kycReq.setRad(authXML.getBytes());
		return kycReq;
	}
}