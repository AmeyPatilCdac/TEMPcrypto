  package in.cdac.java.auth.helper;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PublicKey;
import java.security.Security;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import java.util.logging.Logger;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

public final class Encrypter {
   public static final Logger log = Logger.getLogger(Encrypter.class.getName());
 
   private PublicKey publicKey;
   private Date certExpiryDate;

   static {
      Security.addProvider(new BouncyCastleProvider());
   }

   public Encrypter(String publicKeyFileName) {
      FileInputStream fileInputStream = null;

      try {
         CertificateFactory certFactory = CertificateFactory.getInstance("X.509", "BC");
         fileInputStream = new FileInputStream(new File(publicKeyFileName));
         X509Certificate cert = (X509Certificate)certFactory.generateCertificate(fileInputStream);
         this.publicKey = cert.getPublicKey();
         this.certExpiryDate = cert.getNotAfter();
      } catch (Exception var13) {
         var13.printStackTrace();
      } finally {
         if (fileInputStream != null) {
            try {
               fileInputStream.close();
            } catch (IOException var12) {
               var12.printStackTrace();
            }
         }

      }

   }

   public byte[] generateSessionKey() throws NoSuchAlgorithmException, NoSuchProviderException {
      KeyGenerator kgen = KeyGenerator.getInstance("AES", "BC");
      kgen.init(256);
      SecretKey key = kgen.generateKey();
      return key.getEncoded();
       
   }

   public byte[] encryptUsingPublicKey(byte[] data) throws IOException, GeneralSecurityException {
      Cipher pkCipher = Cipher.getInstance("RSA/ECB/PKCS1Padding", "BC");
      pkCipher.init(1, this.publicKey);
      return pkCipher.doFinal(data);
     
   }

   public String getCertificateIdentifier() {
      SimpleDateFormat ciDateFormat = new SimpleDateFormat("yyyyMMdd");
      ciDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
      return  ciDateFormat.format(this.certExpiryDate);
 
   }
}