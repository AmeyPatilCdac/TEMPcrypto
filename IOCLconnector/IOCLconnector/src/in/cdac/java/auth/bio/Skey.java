 package in.cdac.java.auth.bio;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "Skey",
   propOrder = {"value"}
)
public class Skey {
   @XmlValue
   protected byte[] value;
   @XmlAttribute(
      name = "ci"
   )
   protected String ci;
   @XmlAttribute(
      name = "ki"
   )
   protected String ki;

   public byte[] getValue() {
      return this.value;
   }

   public void setValue(byte[] value) {
      this.value = value;
   }

   public String getCi() {
      return this.ci;
   }

   public void setCi(String value) {
      this.ci = value;
   }

   public String getKi() {
      return this.ki;
   }

   public void setKi(String value) {
      this.ki = value;
   }

   public String toString() {
      return "value = " + this.value + " ci = " + this.ci;
   }
}