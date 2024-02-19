   package in.cdac.java.auth.bio;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "Resp"
)
public class Resp {
   private String iCount;
   private String nmPoints;
   private String ts;
   private String errCode;
   private String qScore;
   private String pCount;
   private String fType;

   public String getICount() {
      return this.iCount;
   }

   public void setICount(String iCount) {
      this.iCount = iCount;
   }

   public String getNmPoints() {
      return this.nmPoints;
   }

   public void setNmPoints(String nmPoints) {
      this.nmPoints = nmPoints;
   }

   public String getTs() {
      return this.ts;
   }

   public void setTs(String ts) {
      this.ts = ts;
   }

   public String getErrCode() {
      return this.errCode;
   }

   public void setErrCode(String errCode) {
      this.errCode = errCode;
   }

   public String getQScore() {
      return this.qScore;
   }

   public void setQScore(String qScore) {
      this.qScore = qScore;
   }

   public String getPCount() {
      return this.pCount;
   }

   public void setPCount(String pCount) {
      this.pCount = pCount;
   }

   public String getFType() {
      return this.fType;
   }

   public void setFType(String fType) {
      this.fType = fType;
   }

   public String toString() {
      return "ClassPojo [iCount = " + this.iCount + ", nmPoints = " + this.nmPoints + ", ts = " + this.ts + ", errCode = " + this.errCode + ", qScore = " + this.qScore + ", pCount = " + this.pCount + ", fType = " + this.fType + "]";
   }
}