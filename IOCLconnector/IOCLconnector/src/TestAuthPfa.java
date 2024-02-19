

import java.util.Scanner;
import java.util.UUID;
import in.cdac.java.parameters.PersonalFullAddress;
import in.cdac.java.connector.Connector;
import in.cdac.java.connector.ConnectorImpl;
import in.cdac.java.parameters.MatchingStrategy;
import in.cdac.java.parameters.RequestObject;
import in.cdac.java.parameters.ResponseObject;
import in.cdac.java.util.TimeStampGenerator;

public class TestAuthPfa {
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
		
		PersonalFullAddress personalFullAddress = new PersonalFullAddress();
	    System.out.print("Enter the Full Address\n");
	    // get their input as a String
	    String address = scanner.nextLine();
	    
	    personalFullAddress.setFullAddress(address);
		personalFullAddress.setMatchStrategy(MatchingStrategy.EXACT);
		//personalFullAddress.setMatchValue(10);
		request.setPersonalFullAddress(personalFullAddress);
		
		ResponseObject response = conn.requestAuth(request);
		if(response.getStatus().startsWith("N")){
			System.out.println("Failed to generate the request.");
			System.out.println(response.getError());
			System.out.println(response.getErrorMessage());
		}else{
			System.out.println(response.getTransaction());
			System.out.println(response.getTimeStamp());
			if(response.getResponseStatus().equals("Y")){
				System.out.println("Success`");	
			}else{
				System.out.println(response.getError());
				System.out.println(response.getErrorMessage());
			}
		}
		scanner.close();
	}
}