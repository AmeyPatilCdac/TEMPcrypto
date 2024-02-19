

import java.util.Scanner;
import in.cdac.java.parameters.KYCRequest;
import in.cdac.java.parameters.PinValue;
import in.cdac.java.parameters.ResidentAuthenticationType;
import in.cdac.java.connector.Connector;
import in.cdac.java.connector.ConnectorImpl;
import in.cdac.java.parameters.RequestObject;
import in.cdac.java.parameters.ResponseObject;
import in.cdac.java.util.TimeStampGenerator;
public class TestKYC {

	public static void main(String[] args) {
		Connector conn = new ConnectorImpl();
		RequestObject request = new RequestObject();
		
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter the Aadhar Number : \n");
		request.setAadhaarNumber(scanner.nextLine().trim());

		System.out.println("Enter the Transaction ID : \n");
		request.setTransaction(scanner.nextLine().trim());
		request.setTimeStamp(TimeStampGenerator.generateTimeStamp());
		
		PinValue pinvalue = new PinValue();
		System.out.println("Enter the OTP");
		pinvalue.setOtp(scanner.nextLine().trim());
		request.setPinValue(pinvalue);
		request.setResidentConsent(true);
		KYCRequest kycRequest = new KYCRequest();
		kycRequest.setDecryptionRequired(false);
		kycRequest.setPrintFormatRequired(false);
		kycRequest.setLocalLanguageRequired(true);
		//ch
		kycRequest.setAuthenticationType(ResidentAuthenticationType.OTP);
		request.setKycRequest(kycRequest);
		ResponseObject response = conn.requestKYC(request);
		if (response.getStatus().startsWith("N")) {
			System.out.println("Failed to generate the request.");
			System.out.println(response.getError());
			System.out.println(response.getErrorMessage());
		} else {
			System.out.println(response.getTransaction());
			System.out.println(response.getTimeStamp());
			if (response.getResponseStatus().equals("Y")) {
				System.out.println("Success");
				System.out.println("Name is :: "+response.getKycRes().getUidData().getPoi().getName());
				System.out.println("Name is :: "+response.getKycRes().getUidData().getLData().getName());
			} else {
				System.out.println(response.getError());
				System.out.println(response.getErrorMessage());
			}
		}
		scanner.close();
	}
}