  package in.cdac.java.util;

import java.io.InputStream;
import java.util.Properties;

public class PropertyHandler {
   private String filename = "connector.properties";
   private static Properties prop = null;
   private InputStream input = null;

   public PropertyHandler() {
      try {
         this.input = PropertyHandler.class.getClassLoader().getResourceAsStream(this.filename);
         if (this.input != null) {
            prop = new Properties();
            prop.load(this.input);
            this.input.close();
         } else {
            System.out.println("Unable to find " + this.filename + " file.");
         }
      } catch (Exception var2) {
         var2.printStackTrace();
      }

   }

   public static String getOtpVersion() {
      return prop != null && prop.containsKey("otpversion") ? prop.getProperty("otpversion").trim() : null;
   }

   public static String getTerminalIdPublic() {
      return prop != null && prop.containsKey("terminalIdPublic") ? prop.getProperty("terminalIdPublic").trim() : null;
   }

   public static String getTerminalIdBiometric() {
      return prop != null && prop.containsKey("terminalIdBiometric") ? prop.getProperty("terminalIdBiometric").trim() : null;
   }

   public static String getAuthReqVersion() {
      return prop != null && prop.containsKey("authversion") ? prop.getProperty("authversion").trim() : null;
   }

   public static String getTokenReqVersion() {
      return prop != null && prop.containsKey("tokenversion") ? prop.getProperty("tokenversion").trim() : null;
   }

   public static String getCertificatePath() {
      return prop != null && prop.containsKey("certificatePath") ? prop.getProperty("certificatePath").trim() : null;
   }

   public static String getOtpUrl() {
      return prop != null && prop.containsKey("otpUrl") ? prop.getProperty("otpUrl").trim() : null;
   }

   public static String getAuthUrl() {
      return prop != null && prop.containsKey("authUrl") ? prop.getProperty("authUrl").trim() : null;
   }

   public static String getTokenUrl() {
      return prop != null && prop.containsKey("tokenUrl") ? prop.getProperty("tokenUrl").trim() : null;
   }

   public static String getOtpLicenseKey() {
      return prop != null && prop.containsKey("otpLicenseKey") ? prop.getProperty("otpLicenseKey").trim() : null;
   }

   public static String getTokenLicenseKey() {
      return prop != null && prop.containsKey("tokenLicenseKey") ? prop.getProperty("tokenLicenseKey").trim() : null;
   }

   public static String getAuthLicenseKey() {
      return prop != null && prop.containsKey("authLicenseKey") ? prop.getProperty("authLicenseKey").trim() : null;
   }

   public static String getKycLicenseKey() {
      return prop != null && prop.containsKey("kycLicenseKey") ? prop.getProperty("kycLicenseKey").trim() : null;
   }

   public static String getAUACode() {
      return prop != null && prop.containsKey("auaCode") ? prop.getProperty("auaCode").trim() : null;
   }

   public static String getKYCVersion() {
      return prop != null && prop.containsKey("kycversion") ? prop.getProperty("kycversion") : null;
   }

   public static String getKYCUrl() {
      return prop != null && prop.containsKey("kycUrl") ? prop.getProperty("kycUrl") : null;
   }

   public static String getPidVersion() {
      return prop != null && prop.containsKey("pidVersion") ? prop.getProperty("pidVersion") : null;
   }

   public static boolean isLogEnabled() {
      return prop != null && prop.containsKey("log") ? Boolean.parseBoolean(prop.getProperty("log")) : false;
   }

   public static boolean isReadLkfromProperty() {
      return prop != null && prop.containsKey("lk") ? Boolean.parseBoolean(prop.getProperty("lk")) : false;
   }

   public static String getLogPath() {
      return prop != null && prop.containsKey("path") ? prop.getProperty("path") : null;
   }

   public static String getSubAUACode() {
      return prop != null && prop.containsKey("subAuaCode") ? prop.getProperty("subAuaCode").trim() : null;
   }

//   public Integer getTotalHttpConnPool() {
//      return prop != null && prop.containsKey("totalHttpConnPool") ? Integer.parseInt(prop.getProperty("totalHttpConnPool").trim()) : null;
//   }

//   public Integer getMaxPerRoute() {
//      return prop != null && prop.containsKey("maxPerRoute") ? Integer.parseInt(prop.getProperty("maxPerRoute").trim()) : null;
//   }
//
//   public Integer getReadTimeout() {
//      return prop != null && prop.containsKey("readTimeout") ? Integer.parseInt(prop.getProperty("readTimeout").trim()) : null;
//   }

//   public Integer getConnectTimeout() {
//      return prop != null && prop.containsKey("connectTimeout") ? Integer.parseInt(prop.getProperty("connectTimeout").trim()) : null;
//   }
}