  package in.cdac.java.token.core;

import generated.TokenizeData;
import in.cdac.java.parameters.RequestObject;

public class CreateTokenizeData {
   public TokenizeData createTokenDataObject(RequestObject requestObject) {
      TokenizeData tokenizeData = new TokenizeData();
      if (requestObject.getAadhaarNumber() != null) {
         tokenizeData.setId(requestObject.getAadhaarNumber());
      }

      return tokenizeData;
   }
}