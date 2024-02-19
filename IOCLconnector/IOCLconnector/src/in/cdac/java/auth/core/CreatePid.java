package in.cdac.java.auth.core;

import javax.xml.datatype.XMLGregorianCalendar;

import in.cdac.java.parameters.DateOfBirthType;
import in.cdac.java.parameters.GenderType;
import in.cdac.java.parameters.MatchingStrategy;
import in.cdac.java.parameters.PersonalAddress;
import in.cdac.java.parameters.PersonalFullAddress;
import in.cdac.java.parameters.PersonalIdentity;
import in.cdac.java.parameters.RequestObject;
import in.gov.uidai.authentication.uid_auth_request_data._2.Demo;
import in.gov.uidai.authentication.uid_auth_request_data._2.Gender;
import in.gov.uidai.authentication.uid_auth_request_data._2.Pa;
import in.gov.uidai.authentication.uid_auth_request_data._2.Pfa;
import in.gov.uidai.authentication.uid_auth_request_data._2.Pi;
import in.gov.uidai.authentication.uid_auth_request_data._2.Pid;
import in.gov.uidai.authentication.uid_auth_request_data._2.Pv;

public class CreatePid {
	public Pid createPidObject(RequestObject requestObject, XMLGregorianCalendar xmlGregorianCalendar) {
		Pid pid = new Pid();
		Demo demo;
		if (requestObject.getPersonalIdentity() != null) {
			demo = new Demo();
			Pi pi = new Pi();
			PersonalIdentity personalIdentity = requestObject.getPersonalIdentity();
			if (personalIdentity.getName() != null) {
				pi.setName(personalIdentity.getName());
				if (personalIdentity.getMatchStrategy().equals(MatchingStrategy.PARTIAL)) {
					pi.setMs(in.gov.uidai.authentication.uid_auth_request_data._2.MatchingStrategy.P);
					pi.setMv(personalIdentity.getMatchValue());

				} else {
					pi.setMs(in.gov.uidai.authentication.uid_auth_request_data._2.MatchingStrategy.E);
					pi.setMv(100);
				}
			}

			if (personalIdentity.getGender() != null) {
				if (personalIdentity.getGender().equals(GenderType.MALE)) {
					pi.setGender(Gender.M);
				} else if (personalIdentity.getGender().equals(GenderType.FEMALE)) {
					pi.setGender(Gender.F);
				} else {
					pi.setGender(Gender.T);
				}
			}

			if (personalIdentity.getAge() != null) {
				pi.setAge(personalIdentity.getAge());
			}

			if (personalIdentity.getDateOfBirthType() != null) {
				if (personalIdentity.getDateOfBirthType().equals(DateOfBirthType.VERIFIED)) {
					pi.setDobt("V");
				} else if (personalIdentity.getDateOfBirthType().equals(DateOfBirthType.DECLARED)) {
					pi.setDobt("D");
				} else {
					pi.setDobt("A");
				}
			}

			if (personalIdentity.getDateOfBirth() != null) {
				pi.setDob(personalIdentity.getDateOfBirth());
			}

			if (personalIdentity.getPhone() != null) {
				pi.setPhone(personalIdentity.getPhone());
			}

			if (personalIdentity.getEmail() != null) {
				pi.setEmail(personalIdentity.getEmail());
			}

			if (personalIdentity.getNameInIndianLanguage() != null) {
				pi.setLname(personalIdentity.getNameInIndianLanguage());
				if (personalIdentity.getLocalMatchValue() == null) {
					pi.setLmv(100);
				} else {
					pi.setLmv(personalIdentity.getLocalMatchValue());
				}

				demo.setLang(requestObject.getLanguageCode());
			}

			demo.setPi(pi);
			pid.setDemo(demo);
		} else if (requestObject.getPersonalFullAddress() != null) {
			demo = new Demo();
			PersonalFullAddress personalFullAddress = requestObject.getPersonalFullAddress();
			Pfa pfa = new Pfa();
			if (personalFullAddress.getFullAddress() != null) {
				pfa.setAv(personalFullAddress.getFullAddress());
				if (personalFullAddress.getMatchStrategy() == MatchingStrategy.EXACT) {
					pfa.setMs(in.gov.uidai.authentication.uid_auth_request_data._2.MatchingStrategy.E);
					pfa.setMv(100);
				} else {
					pfa.setMs(in.gov.uidai.authentication.uid_auth_request_data._2.MatchingStrategy.P);
					pfa.setMv(personalFullAddress.getMatchValue());
				}
			}

			if (personalFullAddress.getLocalAddressValue() != null) {
				pfa.setLav(personalFullAddress.getLocalAddressValue());
				pfa.setLmv(personalFullAddress.getLocalMatchValue());
				demo.setLang(requestObject.getLanguageCode());
			}

			demo.setPfa(pfa);
			pid.setDemo(demo);
		} else if (requestObject.getPersonalAddress() != null) {
			demo = new Demo();
			PersonalAddress personalAddress = requestObject.getPersonalAddress();
			Pa pa = new Pa();
			pa.setMs(in.gov.uidai.authentication.uid_auth_request_data._2.MatchingStrategy.E);
			if (personalAddress.getStreetName() != null) {
				pa.setStreet(personalAddress.getStreetName());
			}

			if (personalAddress.getHouseIdentifier() != null) {
				pa.setHouse(personalAddress.getHouseIdentifier());
			}

			if (personalAddress.getDistrictName() != null) {
				pa.setDist(personalAddress.getDistrictName());
			}

			if (personalAddress.getPostalPinCode() != null) {
				pa.setPc(personalAddress.getPostalPinCode());
			}

			if (personalAddress.getStateName() != null) {
				pa.setState(personalAddress.getStateName());
			}

			if (personalAddress.getCareOfPersonName() != null) {
				pa.setCo(personalAddress.getCareOfPersonName());
			}

			if (personalAddress.getLandmark() != null) {
				pa.setLm(personalAddress.getLandmark());
			}

			if (personalAddress.getLocality() != null) {
				pa.setLoc(personalAddress.getLocality());
			}

			if (personalAddress.getNameOfVillageTownCity() != null) {
				pa.setVtc(personalAddress.getNameOfVillageTownCity());
			}

			if (personalAddress.getSubDistrictName() != null) {
				pa.setSubdist(personalAddress.getSubDistrictName());
			}

			if (personalAddress.getPostOfficeName() != null) {
				pa.setPo(personalAddress.getPostOfficeName());
			}

			demo.setPa(pa);
			pid.setDemo(demo);
		} else if (requestObject.getPinValue() != null) {
			Pv pv = new Pv();
			pv.setOtp(requestObject.getPinValue().getOtp());
			pv.setPin((String) null);
			pid.setPv(pv);
		}

		pid.setTs(xmlGregorianCalendar);
		pid.setVer(requestObject.getPidVersion());
		return pid;
	}
}