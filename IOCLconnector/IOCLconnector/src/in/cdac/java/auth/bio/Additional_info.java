  package in.cdac.java.auth.bio;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "Additional_info"
)
public class Additional_info {
   private Param[] Param;

   public Param[] getParam() {
      return this.Param;
   }

   public void setParam(Param[] Param) {
      this.Param = Param;
   }

   public String toString() {
      return "ClassPojo [Param = " + this.Param + "]";
   }
}
    
