 package in.cdac.java.connector;

import in.cdac.java.parameters.RequestObject;
import in.cdac.java.parameters.ResponseObject;

public interface Connector {
   ResponseObject requestAuth(RequestObject var1);

   ResponseObject requestOTP(RequestObject var1);

   ResponseObject requestKYC(RequestObject var1);

   ResponseObject requestTOKEN(RequestObject var1);
}