

import java.util.Scanner;
import in.cdac.java.parameters.PinValue;
import in.cdac.java.connector.Connector;
import in.cdac.java.connector.ConnectorImpl;
import in.cdac.java.parameters.RequestObject;
import in.cdac.java.parameters.ResponseObject;
import in.cdac.java.util.TimeStampGenerator;

public class TestAuthPv {
	public static void main(String[] args) {
		Connector conn = new ConnectorImpl();
		RequestObject request = new RequestObject();
		
		Scanner scanner = new Scanner(System.in);
	    System.out.print("Enter AADHAAR NUMBER\n");
	    String uid = scanner.nextLine().trim();
		request.setAadhaarNumber(uid);
		request.setResidentConsent(true);

		System.out.print("Set Transaction");
	    String trans = scanner.nextLine();

	    request.setTransaction(trans);
		request.setTimeStamp(TimeStampGenerator.generateTimeStamp());
		
		System.out.print("Set OTP\n");
	    String otp = scanner.nextLine().trim();
		PinValue pinValue = new PinValue();
		pinValue.setOtp(otp);
		request.setPinValue(pinValue);

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