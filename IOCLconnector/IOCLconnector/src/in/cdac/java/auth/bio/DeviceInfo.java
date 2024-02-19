  package in.cdac.java.auth.bio;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "DeviceInfo"
)
public class DeviceInfo {
   @XmlAttribute(
      name = "dc"
   )
   private String dc;
   @XmlAttribute(
      name = "rdsId"
   )
   private String rdsId;
   @XmlAttribute(
      name = "dpId"
   )
   private String dpId;
   @XmlAttribute(
      name = "mc"
   )
   private String mc;
   @XmlAttribute(
      name = "error"
   )
   private String error;
   private Additional_info additional_info;
   @XmlAttribute(
      name = "rdsVer"
   )
   private String rdsVer;
   @XmlAttribute(
      name = "mi"
   )
   private String mi;

   public String getDc() {
      return this.dc;
   }

   public void setDc(String dc) {
      this.dc = dc;
   }

   public String getRdsId() {
      return this.rdsId;
   }

   public void setRdsId(String rdsId) {
      this.rdsId = rdsId;
   }

   public String getDpId() {
      return this.dpId;
   }

   public void setDpId(String dpId) {
      this.dpId = dpId;
   }

   public String getMc() {
      return this.mc;
   }

   public void setMc(String mc) {
      this.mc = mc;
   }

   public String getError() {
      return this.error;
   }

   public void setError(String error) {
      this.error = error;
   }

   public Additional_info getAdditional_info() {
      return this.additional_info;
   }

   public void setAdditional_info(Additional_info additional_info) {
      this.additional_info = additional_info;
   }

   public String getRdsVer() {
      return this.rdsVer;
   }

   public void setRdsVer(String rdsVer) {
      this.rdsVer = rdsVer;
   }

   public String getMi() {
      return this.mi;
   }

   public void setMi(String mi) {
      this.mi = mi;
   }

   public String toString() {
      return "ClassPojo [dc = " + this.dc + ", rdsId = " + this.rdsId + ", dpId = " + this.dpId + ", mc = " + this.mc + ", error = " + this.error + ", additional_info = " + this.additional_info + ", rdsVer = " + this.rdsVer + ", mi = " + this.mi + "]";
   }
}