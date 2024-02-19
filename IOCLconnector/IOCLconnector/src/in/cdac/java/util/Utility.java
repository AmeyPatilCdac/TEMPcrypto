  package in.cdac.java.util;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.UUID;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.transform.stream.StreamSource;

public class Utility {
   public static String getTransactionID(RequestType type) {
      return type == RequestType.KYC ? "UKC:" + UUID.randomUUID() : UUID.randomUUID().toString();
   }

   public String marshallObj(Class clazz, Object obj) throws JAXBException, XMLStreamException {
      StringWriter xml = new StringWriter();
      JAXBContext.newInstance(clazz).createMarshaller().marshal(obj, xml);
      JAXBContext jc = JAXBContext.newInstance(clazz);
      Marshaller marshaller = jc.createMarshaller();
      marshaller.setProperty("jaxb.formatted.output", true);
      marshaller.marshal(obj, System.out);
      return xml.toString();
   }

   public Object unmarshallXml(Class clazz, String xmlToParse) throws XMLStreamException, JAXBException {
      Object obj = null;
      JAXBContext jc = null;
      jc = JAXBContext.newInstance(clazz);
      XMLInputFactory xif = XMLInputFactory.newFactory();
      XMLStreamReader xsr = xif.createXMLStreamReader(new StreamSource(new StringReader(xmlToParse)));
      Unmarshaller unmarshaller = jc.createUnmarshaller();
      obj = unmarshaller.unmarshal(xsr);
      return obj;
   }
}
    