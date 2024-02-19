   package in.cdac.java.util;

import com.sun.org.apache.xerces.internal.jaxp.datatype.XMLGregorianCalendarImpl;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.xml.datatype.XMLGregorianCalendar;

public class TimeStampGenerator {
   public static XMLGregorianCalendar generateTimeStamp() {
      Calendar calendar = GregorianCalendar.getInstance();
      return  XMLGregorianCalendarImpl.createDateTime(calendar.get(1), calendar.get(2) + 1, calendar.get(5), calendar.get(11), calendar.get(12), calendar.get(13));
   }
}
    