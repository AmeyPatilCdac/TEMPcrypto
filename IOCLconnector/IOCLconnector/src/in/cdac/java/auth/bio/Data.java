    package in.cdac.java.auth.bio;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;

//@XmlAccessorType(XmlAccessType.FIELD)
@XmlAccessorType(XmlAccessType.NONE)
@XmlType(
   name = "Data",
   propOrder = {"content", "type"}
)
public class Data {
   @XmlValue
   private byte[] content;
   @XmlAttribute(
      name = "type"
   )
   private String type;

   public byte[] getContent() {
      return this.content;
   }

   public void setContent(byte[] content) {
      this.content = content;
   }

   public String getType() {
      return this.type;
   }

   public void setType(String type) {
      this.type = type;
   }

   public String toString() {
      return "ClassPojo [content = " + this.content + ", type = " + this.type + "]";
   }
}