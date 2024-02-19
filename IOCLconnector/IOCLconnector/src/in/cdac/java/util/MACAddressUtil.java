   package in.cdac.java.util;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;

public class MACAddressUtil {
   public static String getMacAddress() {
      try {
         InetAddress currentIp = getLocalHostLANAddress();
         NetworkInterface network = NetworkInterface.getByInetAddress(currentIp);
         byte[] mac = network.getHardwareAddress();
         StringBuilder sb = new StringBuilder();

         for(int i = 0; i < mac.length; ++i) {
            sb.append(String.format("%02X%s", mac[i], i < mac.length - 1 ? "-" : ""));
         }

         return sb.toString();
      } catch (UnknownHostException var5) {
         var5.printStackTrace();
      } catch (SocketException var6) {
         var6.printStackTrace();
      }

      return null;
   }

   public static InetAddress getLocalHostLANAddress() throws UnknownHostException {
      try {
         InetAddress candidateAddress = null;
         Enumeration ifaces = NetworkInterface.getNetworkInterfaces();

         while(ifaces.hasMoreElements()) {
            NetworkInterface iface = (NetworkInterface)ifaces.nextElement();
            Enumeration inetAddrs = iface.getInetAddresses();

            while(inetAddrs.hasMoreElements()) {
               InetAddress inetAddr = (InetAddress)inetAddrs.nextElement();
               if (!inetAddr.isLoopbackAddress()) {
                  if (inetAddr.isSiteLocalAddress()) {
                     return inetAddr;
                  }

                  if (candidateAddress == null) {
                     candidateAddress = inetAddr;
                  }
               }
            }
         }

         if (candidateAddress != null) {
            return candidateAddress;
         } else {
            InetAddress jdkSuppliedAddress = InetAddress.getLocalHost();
            if (jdkSuppliedAddress == null) {
               throw new UnknownHostException("The JDK InetAddress.getLocalHost() method unexpectedly returned null.");
            } else {
               return jdkSuppliedAddress;
            }
         }
      } catch (Exception var5) {
         UnknownHostException unknownHostException = new UnknownHostException("Failed to determine LAN address: " + var5);
         unknownHostException.initCause(var5);
         throw unknownHostException;
      }
   }
}
    