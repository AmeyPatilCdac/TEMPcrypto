 package in.cdac.java.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

public class LogUtil {
   public static void logXML(String xml, String type, String logPath) {
      if (logPath != null && logPath.trim().length() != 0) {
         try {
            File dir = new File(logPath);
            if (dir.exists()) {
               String filePath = type + "_" + System.currentTimeMillis() + ".xml";
               File file = new File(dir, filePath);
               FileWriter fileWriter = new FileWriter(file);
               fileWriter.write(xml);
               fileWriter.close();
            } else {
               System.out.println("Invalid log path");
               System.out.println(type);
               System.out.println(xml);
            }

            System.out.println();
         } catch (IOException var7) {
            var7.printStackTrace();
         }

      }
   }

   public static void logXML(Class<?> aclasss, Object obj, String type, String logPath) {
      if (logPath != null && logPath.trim().length() != 0) {
         try {
            File dir = new File(logPath);
            JAXBContext jaxbContext = JAXBContext.newInstance(aclasss);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            if (dir.exists()) {
               String filePath = type + "_" + System.currentTimeMillis() + ".xml";
               File file = new File(dir, filePath);
               jaxbMarshaller.marshal(obj, file);
            } else {
               System.out.println("Invalid log path");
               System.out.println(type);
               jaxbMarshaller.marshal(obj, System.out);
            }

            System.out.println();
         } catch (JAXBException var9) {
            var9.printStackTrace();
         }

      }
   }
}