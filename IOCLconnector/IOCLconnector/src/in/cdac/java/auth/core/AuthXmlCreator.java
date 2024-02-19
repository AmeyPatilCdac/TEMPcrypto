package in.cdac.java.auth.core;

import in.cdac.java.auth.bio.DeviceInfo;
import in.cdac.java.auth.bio.PidData;
import in.cdac.java.auth.helper.Encrypter;
import in.cdac.java.parameters.RequestObject;
import in.cdac.java.util.LogUtil;
import in.cdac.java.util.MACAddressUtil;
import in.cdac.java.util.PropertyHandler;
import in.gov.uidai.authentication.common.types._2.Meta;
import in.gov.uidai.authentication.common.types._2.Rc;
import in.gov.uidai.authentication.uid_auth_request._2.Auth;
import in.gov.uidai.authentication.uid_auth_request._2.DataType;
import in.gov.uidai.authentication.uid_auth_request._2.Skey;
import in.gov.uidai.authentication.uid_auth_request._2.Uses;
import in.gov.uidai.authentication.uid_auth_request._2.UsesFlag;
import in.gov.uidai.authentication.uid_auth_request._2.Auth.Data;
import in.gov.uidai.authentication.uid_auth_request_data._2.Pid;
import java.io.StringReader;
import java.io.StringWriter;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

public class AuthXmlCreator {
	public Auth prepareAuthObject(RequestObject requestObject) throws Exception {
		Uses uses = this.createUses(requestObject);
		new Meta();
		Skey skey = new Skey();
		Data data = new Data();
		byte[] hMac = null;
		Meta meta;
		
		if (requestObject.getBiometricResponse() != null) {
			PidData pidDataObject = this.unMarshallBiometricResponse(requestObject.getBiometricResponse());
			meta = this.createMeta(pidDataObject.getDeviceInfo(), requestObject.getUdc());
			skey.setCi(pidDataObject.getSkey().getCi());
			skey.setValue(pidDataObject.getSkey().getValue());
			data.setType(DataType.X);
			data.setValue(pidDataObject.getData().getContent());
			hMac = pidDataObject.getHmac();
		} else {
			Pid pid = (new CreatePid()).createPidObject(requestObject, requestObject.getTimeStamp());
			String pidXML = this.createPidXML(pid);
			pidXML = pidXML.replaceAll("ns2:", "");
			pidXML = pidXML.replaceAll(":ns2", "");
			if (PropertyHandler.isLogEnabled()) {
				LogUtil.logXML(pidXML, "pid_xml", PropertyHandler.getLogPath());
			}

			meta = this.createMeta((DeviceInfo) null, requestObject.getUdc());

			try {
				Encrypter encrypter = new Encrypter(PropertyHandler.getCertificatePath());
				byte[] sessionKey = encrypter.generateSessionKey();
				byte[] encryptedSessionKey = encrypter.encryptUsingPublicKey(sessionKey);
				AESCipher aesCipher = new AESCipher();
				byte[] encXMLPIDData = aesCipher.dataEncrypter(pidXML, requestObject.getTimeStamp().toString(),
						sessionKey);
				byte[] hmac = aesCipher.generateHash(pidXML.getBytes());
				byte[] iv = aesCipher.generateIv(requestObject.getTimeStamp().toString());
				byte[] aad = aesCipher.generateAad(requestObject.getTimeStamp().toString());
				byte[] encryptedHmacBytes = aesCipher.encryptDecryptUsingSessionKey(true, sessionKey, iv, aad, hmac);
				String certificateIdentifier = encrypter.getCertificateIdentifier();
				skey.setCi(certificateIdentifier);
				skey.setValue(encryptedSessionKey);
				data.setType(DataType.X);
				data.setValue(encXMLPIDData);
				hMac = encryptedHmacBytes;
			} catch (Exception var19) {
				var19.printStackTrace();
				throw new RuntimeException();
			}
		}

		return this.generateAuth(requestObject, uses, meta, skey, data, hMac);
	}

	private String createPidXML(Pid pid) throws JAXBException {
		StringWriter pidXML = new StringWriter();
		JAXBContext.newInstance(Pid.class).createMarshaller().marshal(pid, pidXML);
		return pidXML.toString();
	}

	private Meta createMeta(DeviceInfo deviceInfo, String udc) {
		Meta meta = new Meta();
		meta.setUdc(MACAddressUtil.getMacAddress());
		if (deviceInfo != null) {
			meta.setDc(deviceInfo.getDc());
			meta.setDpId(deviceInfo.getDpId());
			meta.setMc(deviceInfo.getMc());
			meta.setMi(deviceInfo.getMi());
			meta.setRdsId(deviceInfo.getRdsId());
			meta.setRdsVer(deviceInfo.getRdsVer());
		} else {
			meta.setDc("");
			meta.setDpId("");
			meta.setMc("");
			meta.setMi("");
			meta.setRdsId("");
			meta.setRdsVer("");
		}

		return meta;
	}

	private Uses createUses(RequestObject requestObject) {
		Uses uses = new Uses();
		uses.setPi(UsesFlag.N);
		uses.setPin(UsesFlag.N);
		uses.setPa(UsesFlag.N);
		uses.setPfa(UsesFlag.N);
		uses.setOtp(UsesFlag.N);
		uses.setBio(UsesFlag.N);
		uses.setBt("");
		if (requestObject.getPersonalIdentity() != null) {
			uses.setPi(UsesFlag.Y);
		} else if (requestObject.getPersonalAddress() != null) {
			uses.setPa(UsesFlag.Y);
		} else if (requestObject.getPersonalFullAddress() != null) {
			uses.setPfa(UsesFlag.Y);
		} else if (requestObject.getPinValue() != null) {
			uses.setOtp(UsesFlag.Y);
		} else if (requestObject.getBiometricResponse() != null) {
			uses.setBt(requestObject.getBioMetricType().value());
			uses.setBio(UsesFlag.Y);
		}

		return uses;
	}

	private PidData unMarshallBiometricResponse(String biometricResponse) throws JAXBException {
		JAXBContext jaxbContext = JAXBContext.newInstance(PidData.class);
		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		return (PidData) jaxbUnmarshaller.unmarshal(new StringReader(biometricResponse));
		
	}

	private Auth generateAuth(RequestObject requestObject, Uses uses, Meta meta, Skey skey, Data data, byte[] hMac) {
		Auth auth = new Auth();
		if (requestObject.getAadhaarNumber() != null) {
			auth.setUid(requestObject.getAadhaarNumber());
		} else if (requestObject.getVirtualID() != null) {
			auth.setUid(requestObject.getVirtualID());
		} else if (requestObject.getUIDToken() != null) {
			auth.setUid(requestObject.getUIDToken());
		}

		auth.setSa(requestObject.getSubAUACode());
		auth.setTxn(requestObject.getTransaction());
		auth.setTid(requestObject.getTerminalId());
		auth.setAc(requestObject.getAuaCode());
		auth.setVer(requestObject.getVersion());
		auth.setLk(requestObject.getAuthLicenseKey());
		if (requestObject.isResidentConsent()) {
			auth.setRc(Rc.Y);
		}

		auth.setMeta(meta);
		auth.setUses(uses);
		auth.setSkey(skey);
		auth.setData(data);
		auth.setHmac(hMac);
		return auth;
	}
}