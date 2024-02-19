

import java.util.Scanner;
import java.util.UUID;
import in.cdac.java.parameters.OTPRequestType;
import in.cdac.java.parameters.OtpOptions;
import in.cdac.java.connector.Connector;
import in.cdac.java.connector.ConnectorImpl;
import in.cdac.java.parameters.RequestObject;
import in.cdac.java.parameters.ResponseObject;
import in.cdac.java.util.TimeStampGenerator;

public class TestOTP {
	public static void main(String[] args) {
		Connector conn = new ConnectorImpl();
		RequestObject request = new RequestObject();
		
		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter AADHAAR NUMBER\n");
		request.setAadhaarNumber(scanner.nextLine().trim());
		request.setTransaction(UUID.randomUUID().toString());
		request.setTimeStamp(TimeStampGenerator.generateTimeStamp());
		request.setOtpRequestType(OTPRequestType.AADHAAR);
		request.setOptions(OtpOptions.ONLY_SMS);
		
		ResponseObject response = conn.requestOTP(request);
		
		if(response.getStatus().equals("N")){
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
		scanner.close();
	}
}