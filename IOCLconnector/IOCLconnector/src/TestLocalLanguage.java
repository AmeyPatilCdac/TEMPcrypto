

import java.util.Scanner;
import java.util.UUID;

import in.cdac.java.parameters.GenderType;
import in.cdac.java.parameters.PersonalIdentity;
import in.cdac.java.connector.Connector;
import in.cdac.java.connector.ConnectorImpl;
import in.cdac.java.parameters.RequestObject;
import in.cdac.java.parameters.ResponseObject;
import in.cdac.java.util.TimeStampGenerator;

public class TestLocalLanguage {
	public static void main(String[] args) {
		Connector conn = new ConnectorImpl();
		RequestObject request = new RequestObject();
		
		Scanner scanner = new Scanner(System.in);
	    System.out.print("Enter AADHAAR NUMBER\n");
	    String uid = scanner.nextLine();
	    
	    request.setAadhaarNumber(uid);
	    
		request.setTransaction(UUID.randomUUID().toString());
		request.setTimeStamp(TimeStampGenerator.generateTimeStamp());
		request.setResidentConsent(true);
		PersonalIdentity personalIdentity = new PersonalIdentity();
		
		System.out.println("Enter LANGUAGE CODE\n");
		String langCode = scanner.nextLine();
		request.setLanguageCode(langCode);
		
		System.out.println("Enter NAME IN LOCAL LANGUAGE\n");
		String nameInLocLang = scanner.nextLine();
		personalIdentity.setNameInIndianLanguage(nameInLocLang);
		personalIdentity.setGender(GenderType.MALE);
		personalIdentity.setLocalMatchValue(100);
		request.setPersonalIdentity(personalIdentity);
		ResponseObject response = conn.requestAuth(request);
		if(response.getStatus().startsWith("N")){
			System.out.println("Failed to generate the request.");
			System.out.println(response.getError());
			System.out.println(response.getErrorMessage());
		}else{
			System.out.println(response.getTransaction());
			System.out.println(response.getTimeStamp());
			if(response.getResponseStatus().equals("Y")){
				System.out.println("Success");	
			}else{
				System.out.println(response.getError());
				System.out.println(response.getErrorMessage());
			}
		}
	}
}