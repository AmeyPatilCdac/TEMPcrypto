import java.util.Scanner;

// 
// Decompiled by Procyon v0.5.36
// 

public class EntryClass {
	public static void main(final String[] args) {
		final Scanner scanner = new Scanner(System.in);
		System.out.print("Option -1 for Auth PI\n");
		System.out.print("Option -2 for Auth PA\n");
		System.out.print("Option -3 for Auth PFA\n");
		System.out.print("Option -4 for Auth PV\n");
		System.out.print("Option -5 for Auth OTP\n");
		System.out.print("Option -6 for Auth KYC\n");
		System.out.print("Option -7 for Auth Local Language\n");
		System.out.print("Enter Option\n");
		final int option = scanner.nextInt();
		switch (option) {
		case 1: {
			TestAuthPi.main((String[]) null);
			break;
		}
		case 2: {
			TestAuthPa.main((String[]) null);
			break;
		}
		case 3: {
			TestAuthPfa.main((String[]) null);
			break;
		}
		case 4: {
			TestAuthPv.main((String[]) null);
			break;
		}
		case 5: {
			TestOTP.main((String[]) null);
			break;
		}
		case 6: {
			TestKYC.main((String[]) null);
			break;
		}
		case 7: {
			TestLocalLanguage.main((String[]) null);
			break;
		}
		}
	}
}