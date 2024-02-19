   package in.cdac.java.token.core;

import generated.Flags;
import generated.Skey;
import generated.Tokenize;
import generated.TokenizeData;
import in.cdac.java.auth.core.AESCipher;
import in.cdac.java.auth.helper.Encrypter;
import in.cdac.java.parameters.RequestObject;
import in.cdac.java.util.PropertyHandler;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

public class TokenXmlCreator {
   public Tokenize prepareTokenObject(RequestObject requestObject) throws Exception {
      Skey skey = new Skey();
      byte[] hMac = null;
      byte[] encXMLTOKENData = null;
      TokenizeData tokenizeData = (new CreateTokenizeData()).createTokenDataObject(requestObject);
      String tokenizeDataXML = this.createTokenizeDataXML(tokenizeData);


      try {
         Encrypter encrypter = new Encrypter(PropertyHandler.getCertificatePath());
         byte[] sessionKey = encrypter.generateSessionKey();
         byte[] encryptedSessionKey = encrypter.encryptUsingPublicKey(sessionKey);
         AESCipher aesCipher = new AESCipher();
         encXMLTOKENData = aesCipher.dataEncrypter(tokenizeDataXML, requestObject.getTimeStamp().toString(), sessionKey);
         byte[] hmac = aesCipher.generateHash(tokenizeDataXML.getBytes(StandardCharsets.UTF_8));
         byte[] iv = aesCipher.generateIv(requestObject.getTimeStamp().toString());
         byte[] aad = aesCipher.generateAad(requestObject.getTimeStamp().toString());
         byte[] encryptedHmacBytes = aesCipher.encryptDecryptUsingSessionKey(true, sessionKey, iv, aad, hmac);
         String certificateIdentifier = encrypter.getCertificateIdentifier();
         skey.setCi(certificateIdentifier);
         skey.setValue(encryptedSessionKey);
         hMac = encryptedHmacBytes;
      } catch (Exception var16) {
         var16.printStackTrace();
         throw new RuntimeException();
      }

      return this.generateToken(requestObject, skey, encXMLTOKENData, hMac);
   }

   private String createTokenizeDataXML(TokenizeData tokenizeData) throws JAXBException {
      StringWriter tokenizeDataXML = new StringWriter();
      JAXBContext.newInstance(TokenizeData.class).createMarshaller().marshal(tokenizeData, tokenizeDataXML);
      return tokenizeDataXML.toString();
   }

   private Tokenize generateToken(RequestObject requestObject, Skey skey, byte[] data, byte[] hMac) {
      Tokenize token = new Tokenize();
      token.setAc(requestObject.getAuaCode());
      token.setSa(requestObject.getSubAUACode());
      token.setVer(requestObject.getVersion());
      token.setTxn(requestObject.getTransaction());
      token.setLk(requestObject.getTokenLicenseKey());
      token.setTs(requestObject.getTimeStamp());
      token.setEkycFlag(Flags.N);
      token.setSkey(skey);
      token.setData(data);
      token.setHmac(hMac);
      return token;
   }
}