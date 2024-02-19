  package in.cdac.java.auth.bio;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(
   name = "PidData"
)
@XmlAccessorType(XmlAccessType.FIELD)
public class PidData {
   @XmlElement(
      name = "Skey",
      required = true
   )
   private Skey Skey;
   @XmlElement(
      name = "Data",
      required = true
   )
   private Data Data;
   @XmlElement(
      name = "Hmac",
      required = true
   )
   private byte[] Hmac;
   @XmlElement(
      name = "DeviceInfo",
      required = true
   )
   private DeviceInfo DeviceInfo;
   @XmlElement(
      name = "Resp",
      required = true
   )
   private Resp Resp;

   public Data getData() {
      return this.Data;
   }

   public void setData(Data Data) {
      this.Data = Data;
   }

   public byte[] getHmac() {
      return this.Hmac;
   }

   public void setHmac(byte[] Hmac) {
      this.Hmac = Hmac;
   }

   public DeviceInfo getDeviceInfo() {
      return this.DeviceInfo;
   }

   public void setDeviceInfo(DeviceInfo DeviceInfo) {
      this.DeviceInfo = DeviceInfo;
   }

   public Skey getSkey() {
      return this.Skey;
   }

   public void setSkey(Skey Skey) {
      this.Skey = Skey;
   }

   public Resp getResp() {
      return this.Resp;
   }

   public void setResp(Resp Resp) {
      this.Resp = Resp;
   }

   public String toString() {
      return "ClassPojo [Data = " + this.Data + ", Hmac = " + this.Hmac + ", DeviceInfo = " + this.DeviceInfo + ", Skey = " + this.Skey + ", Resp = " + this.Resp + "]";
   }
}